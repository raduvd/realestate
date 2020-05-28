package ro.personal.home.realestate.webDriver.model;


import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ro.personal.home.realestate.webDriver.model.anunt.Anunt;
import ro.personal.home.realestate.webDriver.model.anunt.AnuntApartament;
import ro.personal.home.realestate.webDriver.model.anunt.AnuntCasa;
import ro.personal.home.realestate.webDriver.model.anunt.AnuntTeren;
import ro.personal.home.realestate.enums.ErrorType;
import ro.personal.home.realestate.enums.PageType;
import ro.personal.home.realestate.webDriver.webDriver.WaitDriverImobiliare;
import ro.personal.home.realestate.webDriver.webDriver.WebDriverImobiliare;

import java.util.ArrayList;
import java.util.List;

import static ro.personal.home.realestate.enums.PageType.GENERAL;

@Data
public class Page {

    public static final By ELEMENT_ACCEPT_ALL_COOKIES = By.xpath("//div[@aria-hidden='false']/div/div/div/div[2]/div[2]/div[2]/a");
    public static final By ELEMENT_ANUNT = By.xpath("//div[starts-with(@id, 'anunt-')]");
    public static final By ELEMENT_PARENT_PAGINARE = By.className("index_paginare");
    public static final By ELEMENT_ACTIVE_PAGE_NUMBER = By.xpath("//a[@class ='active']");

    private WebDriver webDriver;
    private Integer pageNumber;
    private PageType pageType;
    private Result result;

    public Page(PageType pageType, Result result) {
        this.result = result;
        this.pageType = pageType;
        this.webDriver = WebDriverImobiliare.getWebDriver();
        webDriver.get(pageType.getLinkToPage());
        waitForPageCookiesAndAcceptIt();
        this.pageNumber = waitForActivePageNumberAndReturnThePageNumber();
        if (pageNumber == null)
            throw new RuntimeException("CANNOT GET PAST THE FIRST PAGE");
        System.out.println("Page number is: " + pageNumber);
    }

    /**
     * This is one of the last element on the page, so when the page is active, we can safely say the page is loaded.
     */
    private Integer waitForActivePageNumberAndReturnThePageNumber() {
        int secondsToWait = 25;
        final WebElement pageNumberElement;
        try {
            //asteptam pana cand elementul apare pe pagina si are o valoare in el
            WaitDriverImobiliare.
                    getWaitDriver(secondsToWait).
                    until((ExpectedCondition<Boolean>) webDriver -> {
                        final String text = webDriver.findElement(ELEMENT_ACTIVE_PAGE_NUMBER).getText();
                        return text != null && !text.isEmpty();
                    });
            pageNumberElement = webDriver.findElement(ELEMENT_ACTIVE_PAGE_NUMBER);
        } catch (Exception e) {
            result.add(ErrorType.WAITING_TIMEOUT, ELEMENT_ACTIVE_PAGE_NUMBER.toString(), null, e.getMessage(), e, GENERAL);
            return null;
        }

        final String activePage = pageNumberElement.getText();
        try {
            final Integer integer = Integer.valueOf(activePage);
            return integer;
        } catch (Exception e) {
            result.add(ErrorType.CASTING_EXCEPTION, ELEMENT_ACTIVE_PAGE_NUMBER.toString(), null, e.getMessage(), e, GENERAL);
            return null;
        }
    }

    private void waitForPageCookiesAndAcceptIt() {
        int sectondsToWait = 15;
        try {
            WaitDriverImobiliare.
                    getWaitDriver(sectondsToWait).
                    until(ExpectedConditions.presenceOfElementLocated(ELEMENT_ACCEPT_ALL_COOKIES));
            webDriver.findElement(ELEMENT_ACCEPT_ALL_COOKIES).click();
        } catch (Exception e) {
            result.add(ErrorType.WAITING_TIMEOUT, ELEMENT_ACCEPT_ALL_COOKIES.toString(), null, e.getMessage(), e, GENERAL);
        }
    }

    public List<Anunt> getListaDeAnunturiDinPage(Result result) {
        List<Anunt> anuntLista = new ArrayList<>();

        for (WebElement webElement : webDriver.findElements(ELEMENT_ANUNT)) {
            Anunt anunt;
            switch (pageType) {
                case APARTAMENT:
                    anunt = new AnuntApartament(webElement, pageType, result);
                    break;
                case TEREN:
                    anunt = new AnuntTeren(webElement, pageType, result);
                    break;
                case CASE:
                    anunt = new AnuntCasa(webElement, pageType, result);
                    break;
                default:
                    anunt = null;
            }
            if (!anunt.validateAnunt())
                continue;
            anuntLista.add(anunt);
        }
        return anuntLista;
    }

    /**
     * @return the success or failure of going to the next page
     */
    public boolean nextPage() {
        //Click on the next button
        try {
            webDriver.findElement(ELEMENT_PARENT_PAGINARE).
                    findElement(By.className("icon-portal-arrow-right")).click();
        } catch (Exception e) {
            result.add(ErrorType.ELEMENT_NOT_FOUND, "icon-portal-arrow-right", null, e.getMessage(), e, GENERAL);
            return false;
        }

        //wait the next page to pe active
        final Integer activePage = waitForActivePageNumberAndReturnThePageNumber();
        if (activePage == null || activePage == this.pageNumber) {
            return false;
        } else {
            System.out.println("New Page number is: " + activePage);
            this.pageNumber = activePage;
            result.incrementNumarulPaginii();
            return true;
        }
    }
}
