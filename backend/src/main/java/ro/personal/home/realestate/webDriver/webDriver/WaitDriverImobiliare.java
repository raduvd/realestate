package ro.personal.home.realestate.webDriver.webDriver;

import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitDriverImobiliare {

    public static WebDriverWait getWaitDriver(int seconds) {

        return new WebDriverWait(WebDriverImobiliare.getWebDriver(), seconds);
    }
}
