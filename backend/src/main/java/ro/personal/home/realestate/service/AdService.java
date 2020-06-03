package ro.personal.home.realestate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.personal.home.realestate.controller.model.ChartTO;
import ro.personal.home.realestate.controller.model.NumberOfAdsTO;
import ro.personal.home.realestate.enums.AdState;
import ro.personal.home.realestate.enums.PageType;
import ro.personal.home.realestate.persistance.model.NumberOfAds;
import ro.personal.home.realestate.persistance.model.view.Averages;
import ro.personal.home.realestate.persistance.model.view.IAverages;
import ro.personal.home.realestate.persistance.repository.AdJpaRepository;
import ro.personal.home.realestate.persistance.repository.AdPriceJpaRepository;
import ro.personal.home.realestate.persistance.repository.NumberOfAdsJpaRepository;
import ro.personal.home.realestate.persistance.repository.view.fluctuationaverage.ApartmentFluctuationAverageJpaRepository;
import ro.personal.home.realestate.persistance.repository.view.fluctuationaverage.FieldFluctuationAverageJpaRepository;
import ro.personal.home.realestate.persistance.repository.view.fluctuationaverage.HouseFluctuationAverageJpaRepository;
import ro.personal.home.realestate.persistance.repository.view.simpleaverage.ApartmentAverageJpaRepository;
import ro.personal.home.realestate.persistance.repository.view.simpleaverage.FieldAverageJpaRepository;
import ro.personal.home.realestate.persistance.repository.view.simpleaverage.HouseAverageJpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableSpringDataWebSupport
public class AdService {

    @Autowired
    private AdJpaRepository adJpaRepository;

    @Autowired
    private AdPriceJpaRepository adPriceJpaRepository;

    @Autowired
    private ApartmentAverageJpaRepository apartmentAverageJpaRepository;

    @Autowired
    private HouseAverageJpaRepository houseAverageJpaRepository;

    @Autowired
    private FieldAverageJpaRepository fieldAverageJpaRepository;

    @Autowired
    private ApartmentFluctuationAverageJpaRepository apartmentFluctuationAverageJpaRepository;

    @Autowired
    private HouseFluctuationAverageJpaRepository houseFluctuationAverageJpaRepository;

    @Autowired
    private FieldFluctuationAverageJpaRepository fieldFluctuationAverageJpaRepository;

    @Autowired
    private NumberOfAdsJpaRepository numberOfAdsJpaRepository;

    @Transactional
    public void validateTodayAds() {
        adPriceJpaRepository.updateState(AdState.VALID.name(), LocalDate.now());
    }

    public NumberOfAdsTO getNumberOfAds() {
        return mapNumberOfAdsToTO(numberOfAdsJpaRepository.findAll());
    }

    public List<NumberOfAds> getLatestNumberOfAds() {
        return numberOfAdsJpaRepository.getLatestNumberOfAds();
    }

    NumberOfAdsTO mapNumberOfAdsToTO(List<NumberOfAds> numberOfAds) {
        return NumberOfAdsTO.builder().
                apartments(filterNumberOfAdsAndMapToChartTO(numberOfAds, PageType.APARTAMENT)).
                fields(filterNumberOfAdsAndMapToChartTO(numberOfAds, PageType.CASE)).
                houses(filterNumberOfAdsAndMapToChartTO(numberOfAds, PageType.TEREN)).
                build();
    }

    private List<ChartTO> filterNumberOfAdsAndMapToChartTO(List<NumberOfAds> numberOfAds, PageType pageType) {
        return numberOfAds.stream().
                filter(n -> pageType.name().equals(n.getNumberOfAdsId().getPageType())).
                map(n -> ChartTO.builder().
                        x(n.getNumberOfAdsId().getAddedAtDate()).
                        y(Double.valueOf(n.getNumberOfAdsPerPage() * n.getNumberOfPages())).
                        z(n.getNumberOfAdsPerPage() * n.getNumberOfPages())
                        .build()).
                collect(Collectors.toList());
    }

    public List<ChartTO> getAdAverage(PageType pageType) {
        List<? extends Averages> averages;
        switch (pageType) {
            case APARTAMENT:
                averages = apartmentAverageJpaRepository.findAll();
                break;
            case TEREN:
                averages = fieldAverageJpaRepository.findAll();
                break;
            case CASE:
                averages = houseAverageJpaRepository.findAll();
                break;
            case GENERAL:
            default:
                throw new RuntimeException("There is no averages except for Apartment, Teren or Case");
        }
        return mapAveragesToChartTO(averages);
    }

    public List<ChartTO> getAdFluctuationAverage(PageType pageType) {
        List<? extends Averages> averages;
        switch (pageType) {
            case APARTAMENT:
                averages = apartmentFluctuationAverageJpaRepository.findAll();
                break;
            case TEREN:
                averages = fieldFluctuationAverageJpaRepository.findAll();
                break;
            case CASE:
                averages = houseFluctuationAverageJpaRepository.findAll();
                break;
            case GENERAL:
            default:
                throw new RuntimeException("There is no averages except for Apartment, Teren or Case");
        }
        final List<ChartTO> chartTOS = mapAveragesToChartTO(averages);

        for (int i = 0; i < chartTOS.size(); i++) {
            if (i == 0)
                continue;
            Double oldValue = chartTOS.get(i - 1).getY();
            chartTOS.get(i).setY(chartTOS.get(i).getY() + oldValue);
        }
        return chartTOS;
    }

    public List<ChartTO> customFluctuations(String pageType, Integer maxSquareMeters, Integer minSquareMeters, Integer maxSquareMetersPrice, Integer minSquareMetersPrice) {
        final List<IAverages> customFluctuationAverages = adJpaRepository.customFluctuations(pageType, maxSquareMeters, minSquareMeters, maxSquareMetersPrice, minSquareMetersPrice);
        return mapAveragesToChartTO(customFluctuationAverages);
    }

    private List<ChartTO> mapAveragesToChartTO(List<? extends IAverages> averages) {
        return averages.stream().
                map(a -> ChartTO.builder().
                        x(a.getAddedAtDate()).
                        y(a.getSquareMeterPriceAverage()).
                        z(a.getNumberOfAds()).build()).
                collect(Collectors.toList());
    }
}
