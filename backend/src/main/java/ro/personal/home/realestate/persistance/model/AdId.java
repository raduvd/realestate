package ro.personal.home.realestate.persistance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdId implements Serializable {

    private String adId;
    private Double squareMeters;
    private String pageType;
}
