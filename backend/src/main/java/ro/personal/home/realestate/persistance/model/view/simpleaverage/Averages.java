package ro.personal.home.realestate.persistance.model.view.simpleaverage;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Averages implements Serializable {

    @Id
    private LocalDate addedAtDate;
    private Double squareMeterPriceAverage;
}
