package ro.personal.home.realestate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.personal.home.realestate.enums.AdState;
import ro.personal.home.realestate.enums.ErrorType;
import ro.personal.home.realestate.enums.PageType;
import ro.personal.home.realestate.persistance.model.Ad;
import ro.personal.home.realestate.persistance.model.AdId;
import ro.personal.home.realestate.persistance.model.AdPrice;
import ro.personal.home.realestate.persistance.model.AdPriceId;
import ro.personal.home.realestate.persistance.repository.AdJpaRepository;
import ro.personal.home.realestate.persistance.repository.AdPriceJpaRepository;
import ro.personal.home.realestate.webDriver.model.Errors;
import ro.personal.home.realestate.webDriver.model.Page;
import ro.personal.home.realestate.webDriver.model.Result;
import ro.personal.home.realestate.webDriver.model.anunt.Anunt;
import ro.personal.home.realestate.webDriver.webDriver.WebDriverImobiliare;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WebDriverService {

    @Autowired
    AdJpaRepository adJpaRepository;

    @Autowired
    AdPriceJpaRepository adPriceJpaRepository;

    public void saveAdsFromWebDriver(PageType pageType, Integer numberOfPages) {
        Result result = new Result();
        Page page = new Page(pageType, result);

        for (int i = 0; i < numberOfPages; i++) {
            final List<Anunt> listaDeAnunturiDinPage = page.getListaDeAnunturiDinPage(result);
            persistAnunturi(listaDeAnunturiDinPage);
            result.addToNumarAnunturiProcesate(listaDeAnunturiDinPage.size());
            //if the nextPage() returns false (the page was not changed), so we break / finish the loop
            if (!page.nextPage()) {
                break;
            }
        }
        WebDriverImobiliare.closeWebDriver();
        printResultInConsoleLog(result);
    }

    private void persistAnunturi(final List<Anunt> listaDeAnunturiDinPage) {
        List<Ad> adList = new ArrayList<>();
        List<AdPrice> adPriceList = new ArrayList<>();

        for (Anunt anunt : listaDeAnunturiDinPage) {
            final Ad ad = adJpaRepository.findById(mapAnuntToAd(anunt).getAdId()).orElse(null);
            if (ad == null)
                adList.add(mapAnuntToAd(anunt));
            final AdPrice adPrice = mapAnuntToAdPrice(anunt);
            addPriceFluctuationSinceLastDateInPercentage(anunt);
            adPriceList.add(adPrice);
        }
        adJpaRepository.saveAll(adList);
        adPriceJpaRepository.saveAll(adPriceList);
    }

    private void addPriceFluctuationSinceLastDateInPercentage(Anunt anunt) {

    }

    private Ad mapAnuntToAd(Anunt anunt) {
        final AdId adId = AdId.builder().
                adId(anunt.getId()).
                pageType(anunt.getPageType().name()).
                squareMeters(anunt.getMetriPatrati() == null ? null : anunt.getMetriPatrati().doubleValue()).
                build();

        return Ad.builder().
                adId(adId).
                currency(anunt.getPriceCurrency()).
                state(AdState.INVALID.name()).
                build();
    }

    private AdPrice mapAnuntToAdPrice(Anunt anunt) {
        final AdPriceId adPriceId = AdPriceId.builder().
                adId(anunt.getId()).
                pageType(anunt.getPageType().name()).
                squareMeters(anunt.getMetriPatrati() == null ? null : anunt.getMetriPatrati().doubleValue()).
                addedAtDate(LocalDate.now().minusMonths(1)).
                build();
        return AdPrice.builder().
                adPriceId(adPriceId).
                squareMeterPrice(anunt.getPretPeMetruPatrat().doubleValue()).
                build();
    }

    private void printResultInConsoleLog(Result result) {
        System.out.println("..\nSUM-UP\n..\n");
        System.out.println("TEORETIC TREBUIA SA FIE APROXIMATIV " + result.getNumarulPaginii() * 30 + " ANUNTURI PROCESATE (30 anunturi per pagina * numarul de pagini procesate).");
        System.out.println("MOMENTAN SUNT: " + result.getNumarAnunturiProcesate() + " ANUNTURI PROCESATE.");
        System.out.println("TOTAL number of errors: " + result.getAllErrorsList().size());
        System.out.println(ErrorType.INVALID_VALUE.name() + " - number of errors: " + result.getInvalidValueList().size() + " ---- description: " + ErrorType.INVALID_VALUE.description);
        System.out.println(ErrorType.WAITING_TIMEOUT.name() + " - number of errors: " + result.getWaitingTimeoutList().size() + " ---- description: " + ErrorType.WAITING_TIMEOUT.description);
        System.out.println(ErrorType.CASTING_EXCEPTION.name() + " - number of errors: " + result.getCastingExceptionList().size() + " ---- description: " + ErrorType.CASTING_EXCEPTION.description);
        System.out.println(ErrorType.ELEMENT_NOT_FOUND.name() + " - number of errors: " + result.getElementNotFoundList().size() + " ---- description: " + ErrorType.ELEMENT_NOT_FOUND.description);
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
