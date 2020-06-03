package ro.personal.home.realestate.controller.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Data
public class ChartTO implements Serializable {
    //This is representing the date in the X axix
    private LocalDate x;
    //This is representing the average in the Y axix
    private Double y;
    //This is representing the numberOfAds
    private Integer z;

}
