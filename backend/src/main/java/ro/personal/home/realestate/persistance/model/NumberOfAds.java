package ro.personal.home.realestate.persistance.model;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "numberOfAds")
@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NumberOfAds {
    @EmbeddedId
    private NumberOfAdsId numberOfAdsId;

    private Integer numberOfPages;
    private Integer numberOfAdsPerPage;
}
