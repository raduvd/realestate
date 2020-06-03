package ro.personal.home.realestate.persistance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NumberOfAdsId implements Serializable {

    private LocalDate addedAtDate;
    private String pageType;
}
