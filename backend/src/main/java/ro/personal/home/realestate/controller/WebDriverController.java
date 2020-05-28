package ro.personal.home.realestate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import ro.personal.home.realestate.service.WebDriverService;
import ro.personal.home.realestate.enums.PageType;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/webdriver")
public class WebDriverController {

    @Autowired
    private WebDriverService webDriverService;

    //TODO asta defapt nu e o metoda de get, dar face figuri cu CORS aplicatia de FE daca pun post, va trebui refactor
    @GetMapping(value = "/{numberOfPages}/{pageType}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public void saveApartmentsAdsFromWebDriver(
            @PathVariable("numberOfPages") Integer numberOfPages,
            @RequestParam("pageType") @NotNull PageType pageType) {
        webDriverService.saveAdsFromWebDriver(pageType, numberOfPages);
    }
}

