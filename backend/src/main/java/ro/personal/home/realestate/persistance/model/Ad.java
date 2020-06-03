package ro.personal.home.realestate.persistance.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ad")
@ToString
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"adId", "squareMeters", "pageType"})})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ad implements Serializable {

    @EmbeddedId
    AdId adId;

    private String currency;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("addedAtDate asc")
    @JoinColumns({
            @JoinColumn(name = "adId", referencedColumnName = "adId"),
            @JoinColumn(name = "squareMeters", referencedColumnName = "squareMeters"),
            @JoinColumn(name = "pageType", referencedColumnName = "pageType")
    })
    private List<AdPrice> adPriceList = new ArrayList<>();
}
