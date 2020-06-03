package ro.personal.home.realestate.controller.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class NumberOfAdsTO {

    private List<ChartTO> apartments;
    private List<ChartTO> houses;
    private List<ChartTO> fields;
}
