package ro.personal.home.realestate.persistance.model.view;

import java.time.LocalDate;

public interface IAverages {
    Double getSquareMeterPriceAverage();

    LocalDate getAddedAtDate();

    Integer getNumberOfAds();
}
