package ro.personal.home.realestate.persistance.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "adPrice")
@ToString
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"adId", "addedAtDate"})})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdPrice implements Serializable {

    @EmbeddedId
    AdPriceId adPriceId;

    private Double squareMeterPrice;
}
