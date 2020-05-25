package ro.personal.home.realestate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import ro.personal.home.realestate.service.WebDriverService;
import ro.personal.home.realestate.webDriver.model.enums.PageType;
import ro.personal.home.realestate.webDriver.model.Result;

@RestController
@RequestMapping("/webdriver")
public class WebDriverController {

    @Autowired
    private WebDriverService webDriverService;

    @PostMapping(value = "/{numberOfPages}/{pageType}", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public Result saveApartmentsAdsFromWebDriver(
            @PathVariable("numberOfPages") Integer numberOfPages,
            @RequestParam("pageType") PageType pageType) {
        return webDriverService.saveAdsFromWebDriver(pageType, numberOfPages);
    }
}

