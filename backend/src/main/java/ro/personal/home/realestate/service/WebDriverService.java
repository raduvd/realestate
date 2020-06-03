package ro.personal.home.realestate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.personal.home.realestate.enums.AdState;
import ro.personal.home.realestate.enums.ErrorType;
import ro.personal.home.realestate.enums.PageType;
import ro.personal.home.realestate.persistance.model.*;
import ro.personal.home.realestate.persistance.repository.AdJpaRepository;
import ro.personal.home.realestate.persistance.repository.AdPriceJpaRepository;
import ro.personal.home.realestate.persistance.repository.NumberOfAdsJpaRepository;
import ro.personal.home.realestate.webDriver.model.Errors;
import ro.personal.home.realestate.webDriver.model.Page;
import ro.personal.home.realestate.webDriver.model.Result;
import ro.personal.home.realestate.webDriver.model.anunt.Anunt;
import ro.personal.home.realestate.webDriver.webDriver.WebDriverImobiliare;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static ro.personal.home.realestate.enums.PageType.GENERAL;

@Service
@RequiredArgsConstructor
public class WebDriverService {

    @Autowired
    private AdJpaRepository adJpaRepository;

    @Autowired
    private AdPriceJpaRepository adPriceJpaRepository;

    @Autowired
    private NumberOfAdsJpaRepository numberOfAdsJpaRepository;

    public void saveNumberOfAdsFromWebDriver() {
        for (PageType pageType : PageType.values()) {
            if (pageType.equals(GENERAL)) continue;
            Result result = new Result();
            Page page = new Page(pageType, result, null);

            final NumberOfAds numberOfAds;
            try {
                numberOfAds = NumberOfAds.builder().
                        numberOfPages(page.getNumberOfPages()).
                        numberOfAdsPerPage(page.getNumberOfAdsPerPage()).
                        numberOfAdsId(NumberOfAdsId.builder().
                                addedAtDate(LocalDate.now()).
                                pageType(pageType.name()).
                                build()).
                        build();
            } catch (RuntimeException e) {
                WebDriverImobiliare.closeWebDriver();
                throw e;
            }
            WebDriverImobiliare.closeWebDriver();
            numberOfAdsJpaRepository.save(numberOfAds);
            System.out.println("Saved in DB: " + numberOfAds);
        }
    }

    public void saveAdsFromWebDriver(String startPage, PageType pageType, Integer numberOfPages) {
        Result result = new Result();
        Page page = new Page(pageType, result, startPage);

        for (int i = 0; i < numberOfPages; i++) {
            final List<Anunt> listaDeAnunturiDinPage = page.getListaDeAnunturiDinPage(result);
            persistAnunturi(listaDeAnunturiDinPage, result);
            result.addToNumarAnunturiProcesate(listaDeAnunturiDinPage.size());
            //if the nextPage() returns false (the page was not changed), so we break / finish the loop
            if (!page.nextPage()) {
                break;
            }
        }
        WebDriverImobiliare.closeWebDriver();
        printResultInConsoleLog(result);
    }

    @Transactional
    public void saveAnunt(Anunt newAnunt, Result result) {
        final Ad newAd = mapAnuntToAd(newAnunt);
        final Ad oldAd = getAd(newAd.getAdId());

        try {
            final AdPrice newAdPrice = mapAnuntToAdPrice(newAnunt);
            if (oldAd == null) {
                newAd.setAdPriceList(Collections.singletonList(newAdPrice));
                adJpaRepository.save(newAd);
            } else {
                newAdPrice.
                        setFluctuationInPercentageSinceLastDate(
                                calculateFluctuation(
                                        oldAd.getAdPriceList().get(0), //the first is the latest because it is ordered by hibernate
                                        newAdPrice));
                adPriceJpaRepository.save(newAdPrice);
            }
        } catch (Exception e) {
            result.add(ErrorType.ERROR_IN_JAVA_LOGIC, oldAd.toString(), null, e.getMessage(), e, GENERAL);
        }
    }

    @Transactional
    public Ad getAd(AdId adId) {
        return adJpaRepository.findById(adId).orElse(null);
    }

    public void persistAnunturi(final List<Anunt> listaDeAnunturiDinPage, Result result) {

        for (Anunt newAnunt : listaDeAnunturiDinPage) {
            saveAnunt(newAnunt, result);
        }
    }

    private Double calculateFluctuation(AdPrice previousAdPrice, AdPrice newAdPrice) {
        Double oldPrice = previousAdPrice.getSquareMeterPrice();
        Double newPrice = newAdPrice.getSquareMeterPrice();

        return calculateFluctuationInPercentage(oldPrice, newPrice);
    }

    public Double calculateFluctuationInPercentage(Double oldPrice, Double newPrice) {
        Double fluctuation = newPrice - oldPrice;
        return (fluctuation / oldPrice) * 100;
    }

    private Ad mapAnuntToAd(Anunt anunt) {
        final AdId adId = AdId.builder().
                adId(anunt.getId()).
                pageType(anunt.getPageType().name()).
                squareMeters(anunt.getMetriPatrati().doubleValue()).
                build();

        return Ad.builder().
                adId(adId).
                currency(anunt.getPriceCurrency()).
                build();
    }

    private AdPrice mapAnuntToAdPrice(Anunt anunt) {
        final AdPriceId adPriceId = AdPriceId.builder().
                adId(anunt.getId()).
                pageType(anunt.getPageType().name()).
                squareMeters(anunt.getMetriPatrati() == null ? null : anunt.getMetriPatrati().doubleValue()).
                addedAtDate(LocalDate.now()).
                build();
        return AdPrice.builder().
                adPriceId(adPriceId).
                state(AdState.INVALID.name()).
                squareMeterPrice(anunt.getPretPeMetruPatrat().doubleValue()).
                build();
    }

    private void printResultInConsoleLog(Result result) {
        System.out.println("..\nSUM-UP\n..\n");
        System.out.println("TEORETIC TREBUIA SA FIE APROXIMATIV " + result.getNumarulDePaginiProcesate() * 30 + " ANUNTURI PROCESATE (30 anunturi per pagina * numarul de pagini procesate).");
        System.out.println("MOMENTAN SUNT: " + result.getNumarAnunturiProcesate() + " ANUNTURI PROCESATE.");
        System.out.println("TOTAL number of errors: " + result.getAllErrorsList().size());
        System.out.println(ErrorType.INVALID_VALUE.name() + " - number of errors: " + result.getInvalidValueList().size() + " ---- description: " + ErrorType.INVALID_VALUE.description);
        System.out.println(ErrorType.WAITING_TIMEOUT.name() + " - number of errors: " + result.getWaitingTimeoutList().size() + " ---- description: " + ErrorType.WAITING_TIMEOUT.description);
        System.out.println(ErrorType.CASTING_EXCEPTION.name() + " - number of errors: " + result.getCastingExceptionList().size() + " ---- description: " + ErrorType.CASTING_EXCEPTION.description);
        System.out.println(ErrorType.ELEMENT_NOT_FOUND.name() + " - number of errors: " + result.getElementNotFoundList().size() + " ---- description: " + ErrorType.ELEMENT_NOT_FOUND.description);
        System.out.println(ErrorType.ERROR_IN_JAVA_LOGIC.name() + " - number of errors: " + result.getErrorInJavaLogicList().size() + " ---- description: " + ErrorType.ERROR_IN_JAVA_LOGIC.description);

        final List<Errors> sortedAllErrorList = result.getAllErrorsList().stream().
                sorted(Comparator.comparing(Errors::getValue)).collect(Collectors.toList());

        System.out.println("..\nSHORT ERRORS\n..\n");
        sortedAllErrorList.forEach(e -> System.out.println(e.getValue() + " - " + e.getErrorType() + " - " + e.getErrorMessage() + " - " + e.getPageType().name()));

        Set<Errors> uniqueValueErrors = new TreeSet<>(Comparator.comparing(Errors::getValue));
        uniqueValueErrors.addAll(sortedAllErrorList);

        System.out.println("..\nSTACK TRACES\n..\n");
        uniqueValueErrors.forEach(e -> {
            if (e.getException() != null) {
                System.out.println(e.getValue() + " - " + e.getErrorType() + " - " + e.getErrorMessage() + " - " + e.getPageType().name());
                e.getException().printStackTrace();
            }
        });
    }
}
