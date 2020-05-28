package ro.personal.home.realestate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.personal.home.realestate.controller.model.AdAverageTO;
import ro.personal.home.realestate.enums.AdState;
import ro.personal.home.realestate.enums.PageType;
import ro.personal.home.realestate.persistance.model.Ad;
import ro.personal.home.realestate.persistance.model.AdPrice;
import ro.personal.home.realestate.persistance.model.view.simpleaverage.Averages;
import ro.personal.home.realestate.persistance.repository.AdJpaRepository;
import ro.personal.home.realestate.persistance.repository.AdPriceJpaRepository;
import ro.personal.home.realestate.persistance.repository.view.simpleaverage.ApartmentAverageJpaRepository;
import ro.personal.home.realestate.persistance.repository.view.simpleaverage.FieldAverageJpaRepository;
import ro.personal.home.realestate.persistance.repository.view.simpleaverage.HouseAverageJpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdService {

    @Autowired
    private AdJpaRepository adJpaRepository;

    @Autowired
    private AdPriceJpaRepository adPriceJpaRepository;

    @Autowired
    ApartmentAverageJpaRepository apartmentAverageJpaRepository;

    @Autowired
    HouseAverageJpaRepository houseAverageJpaRepository;

    @Autowired
    FieldAverageJpaRepository fieldAverageJpaRepository;

    @Transactional
    public void validateTodayAds() {
        adJpaRepository.updateState(AdState.VALID.name(), LocalDate.now());
    }

    public List<AdAverageTO> getAdAverage(PageType pageType) {
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
        return mapAveragesToAdAverageTO(averages);
    }

    public Ad findById(String adId) {
        //return adJpaRepository.findById(adId).get();
        //was implemented just for test
        return null;
    }

    //Maybe use in the future?
    private List<LocalDate> getAllUniqueDates() {
        Specification<AdPrice> uniqueDates = (root, criteriaQuery, criteriaBuilder) -> {
            criteriaQuery.select(root.get("addedAtDate")).distinct(true);
            return null;
        };
        return adPriceJpaRepository.findAll(uniqueDates).stream().map(e -> e.getAdPriceId().getAddedAtDate()).collect(Collectors.toList());
    }

    private List<AdAverageTO> mapAveragesToAdAverageTO(List<? extends Averages> averages) {
        return averages.stream().
                map(a -> AdAverageTO.builder().x(a.getAddedAtDate()).y(a.getSquareMeterPriceAverage()).build()).
                collect(Collectors.toList());
    }

    //Maybe use in the future?
    private List<AdPrice> getAllPricesWithTypeAndDate(LocalDate date, PageType pageType) {
        Specification<AdPrice> adPricesWithSameTypeAndDate = (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("pageType"), pageType.name()),
                    criteriaBuilder.equal(root.get("addedAtDate"), date));

        };
        return adPriceJpaRepository.findAll(adPricesWithSameTypeAndDate);
    }
}
