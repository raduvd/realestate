package ro.personal.home.realestate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.personal.home.realestate.webDriver.model.Page;
import ro.personal.home.realestate.webDriver.model.enums.PageType;
import ro.personal.home.realestate.webDriver.model.Result;
import ro.personal.home.realestate.webDriver.webDriver.WebDriverImobiliare;

@Service
@RequiredArgsConstructor
public class WebDriverService {

    public Result saveAdsFromWebDriver(PageType pageType, Integer numberOfPages) {
        Result result = new Result();
        Page page = new Page(pageType, result);

        for (int i = 0; i < numberOfPages; i++) {
            page.getListaDeAnunturiDinPage(result);
            //if the nextPage() returns false (the page was not changed), so we break / finish the loop
            if (!page.nextPage()) {
                break;
            }
        }
        WebDriverImobiliare.closeWebDriver();
        return result;
    }
}
