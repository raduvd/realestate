package ro.personal.home.realestate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import ro.personal.home.realestate.controller.model.AdAverageTO;
import ro.personal.home.realestate.enums.PageType;
import ro.personal.home.realestate.persistance.model.Ad;
import ro.personal.home.realestate.service.AdService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdService adService;

    @GetMapping(value = "/validate", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public void validateTodayAds() {
        System.out.println("Validating...");
        adService.validateTodayAds();
    }

    @GetMapping(value = "/average/{pageType}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public List<AdAverageTO> getAdAverage(@RequestParam("pageType") PageType pageType) {
        return adService.getAdAverage(pageType);
    }

    @GetMapping(value = "/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public Ad getAd(@PathVariable String id) {
        return adService.findById(id);
    }
}
