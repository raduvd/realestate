package ro.personal.home.realestate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import ro.personal.home.realestate.controller.model.ChartTO;
import ro.personal.home.realestate.controller.model.NumberOfAdsTO;
import ro.personal.home.realestate.enums.PageType;
import ro.personal.home.realestate.persistance.model.NumberOfAds;
import ro.personal.home.realestate.service.AdService;

import java.util.List;

@RestController
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdService adService;

    @GetMapping(value = "/validate", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public void validateTodayAds() {
        System.out.println("------------------------------------ Validating...");
        adService.validateTodayAds();
    }

    @GetMapping(value = "/average/{pageType}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public List<ChartTO> getAdAverage(@RequestParam("pageType") PageType pageType) {
        return adService.getAdAverage(pageType);
    }

    @GetMapping(value = "/latestNumberOfAds", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public List<NumberOfAds> getLatestNumberOfAds() {
        return adService.getLatestNumberOfAds();
    }

    @GetMapping(value = "/numberOfAds", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public NumberOfAdsTO getNumberOfAds() {
        return adService.getNumberOfAds();
    }

    @GetMapping(value = "fluctuation/average/{pageType}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public List<ChartTO> getAdFluctuationAverage(@RequestParam("pageType") PageType pageType) {
        return adService.getAdFluctuationAverage(pageType);
    }

    @GetMapping(value = "custom/fluctuation/average/{maxSquareMeters}/{minSquareMeters}/{maxSquareMetersPrice}/{minSquareMetersPrice}/{pageType}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public List<ChartTO> getCustomFluctuationAverage(@RequestParam("pageType") PageType pageType,
                                                     @PathVariable("maxSquareMeters") Integer maxSquareMeters,
                                                     @PathVariable("minSquareMeters") Integer minSquareMeters,
                                                     @PathVariable("maxSquareMetersPrice") Integer maxSquareMetersPrice,
                                                     @PathVariable("minSquareMetersPrice") Integer minSquareMetersPrice) {
        final List<ChartTO> chartTOS = adService.customFluctuations(pageType.name(), maxSquareMeters, minSquareMeters, maxSquareMetersPrice, minSquareMetersPrice);
        return chartTOS;
    }
}
