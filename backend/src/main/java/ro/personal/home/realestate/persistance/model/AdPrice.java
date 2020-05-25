package ro.personal.home.realestate.persistance.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "adPrice")
@ToString
@Data
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"adId", "addedAtDate"})})
public class AdPrice implements Serializable {

    @Id
    private String adId;

    @Id
    private LocalDate addedAtDate;

    private Double  squareMeterPrice;

    private Boolean valid;

}
