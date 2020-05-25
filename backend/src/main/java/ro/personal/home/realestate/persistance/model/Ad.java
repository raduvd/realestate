package ro.personal.home.realestate.persistance.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ad")
@ToString
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"adId", "squareMeters", "pageType"})})
public class Ad implements Serializable {

    @Id
    private String adId;

    private Double squareMeters;

    private String pageType;

    private String currency;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "adId")
    private List<AdPrice> adPriceList = new ArrayList<>();
}
