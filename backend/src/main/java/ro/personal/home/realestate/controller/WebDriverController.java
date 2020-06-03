package ro.personal.home.realestate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import ro.personal.home.realestate.enums.PageType;
import ro.personal.home.realestate.service.WebDriverService;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/webdriver")
public class WebDriverController {

    @Autowired
    private WebDriverService webDriverService;

    //TODO asta defapt nu e o metoda de get, dar face figuri cu CORS aplicatia de FE daca pun post, va trebui refactor
    @GetMapping(value = "/{startPage}/{numberOfPages}/{pageType}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public void saveApartmentsAdsFromWebDriver(
            @PathVariable(name = "startPage", required = false) String startPage,
            @PathVariable("numberOfPages") Integer numberOfPages,
            @RequestParam("pageType") @NotNull PageType pageType) {
        startPage = startPage.replaceAll("TTT", "/");
        webDriverService.saveAdsFromWebDriver(startPage, pageType, numberOfPages);
    }

    @GetMapping(value = "/numberOfAds", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public void saveNumberOfAdsFromWebDriver() {
        webDriverService.saveNumberOfAdsFromWebDriver();
    }
}

