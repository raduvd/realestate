package ro.personal.home.realestate.webDriver.model;


import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ro.personal.home.realestate.enums.ErrorType;
import ro.personal.home.realestate.enums.PageType;
import ro.personal.home.realestate.webDriver.model.anunt.Anunt;
import ro.personal.home.realestate.webDriver.model.anunt.AnuntApartament;
import ro.personal.home.realestate.webDriver.model.anunt.AnuntCasa;
import ro.personal.home.realestate.webDriver.model.anunt.AnuntTeren;
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
    public static final By ELEMENT_LAST_PAGE = By.xpath("//a[@class ='butonpaginare'][last()]");

    private WebDriver webDriver;
    private Integer pageNumber;
    private PageType pageType;
    private Result result;

    public Page(PageType pageType, Result result, String startPage) {
        this.result = result;
        this.pageType = pageType;
        this.webDriver = WebDriverImobiliare.getWebDriver();
        getStartingPageLink(webDriver, startPage);
        waitForPageCookiesAndAcceptIt();
        this.pageNumber = waitForActivePageNumberAndReturnThePageNumber();
        if (pageNumber == null)
            throw new RuntimeException("CANNOT GET PAST THE FIRST PAGE");
        System.out.println("------------------------------------Page number is: " + pageNumber + "------------------------------------");
    }

    private void getStartingPageLink(WebDriver webDriver, String startPage) {

        String linkToPage = pageType.getLinkToPage();

        if (startPage != null && !startPage.isEmpty() && !startPage.equals("null"))
            linkToPage = linkToPage +"?pagina="+startPage;

        webDriver.get(linkToPage);
        System.out.println("------------------------------------ We will try to open the following link: "+ linkToPage);
    }

    private WebElement waitForActivePageNumber() {
        int secondsToWait = 60;
        final WebElement pageNumberElement;

        //asteptam pana cand elementul apare pe pagina si are o valoare in el
        WaitDriverImobiliare.
                getWaitDriver(secondsToWait).
                until((ExpectedCondition<Boolean>) webDriver -> {
                    final String text = webDriver.findElement(ELEMENT_ACTIVE_PAGE_NUMBER).getText();
                    return text != null && !text.isEmpty();
                });
        return webDriver.findElement(ELEMENT_ACTIVE_PAGE_NUMBER);
    }
    /**
     * "ActivePageNumber" is one of the last element on the page, so when the page is active, we can safely say the page is loaded.
     */
    private Integer waitForActivePageNumberAndReturnThePageNumber() {

        WebElement pageNumberElement;
        try {
            pageNumberElement = waitForActivePageNumber();
        } catch (Exception e) {
            System.out.println("------------------------------------------- refreshing page and wait another 60 seconds... -------------------------------");
            WebDriverImobiliare.refreshPage();
            WebDriverImobiliare.refreshPage();
            try {
                pageNumberElement = waitForActivePageNumber();
            } catch (Exception f) {
                result.add(ErrorType.WAITING_TIMEOUT, ELEMENT_ACTIVE_PAGE_NUMBER.toString(), null, f.getMessage(), f, GENERAL);
                return null;
            }
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

        //wait the next page to be active
        final Integer activePage = waitForActivePageNumberAndReturnThePageNumber();
        if (activePage == null || !isPageIncremented(this.pageNumber, activePage)) {
            return false;
        } else {
            System.out.println("------------------------------------New Page number is: " + activePage);
            this.pageNumber = activePage;
            result.incrementnumarulDePaginiProcesate();
            return true;
        }
    }

    boolean isPageIncremented(Integer oldPageNumber, Integer newPageNumber) {
        if (oldPageNumber.equals(newPageNumber)) {
            result.add(ErrorType.INVALID_VALUE, newPageNumber.toString(), null, "PAGE NUMBER is the same as old page number, meaning that the page did not incremented after clicking on next page", null, GENERAL);
            return false;
        }
        if (!oldPageNumber.equals(newPageNumber - 1)) {
            result.add(ErrorType.INVALID_VALUE, newPageNumber.toString(), null, "New PAGE NUMBER is not incremented after clicking on next page", null, GENERAL);
            return false;
        }
        return true;
    }

    public Integer getNumberOfPages() {
        final WebElement lastPage = webDriver.findElement(ELEMENT_LAST_PAGE);

        final String activePage = lastPage.getText();
        final Integer integer = Integer.valueOf(activePage);

        if (integer == null || integer < 10 || integer > 500)
            throw new RuntimeException("The number of pages is not valid: " + integer +
                    ". This was found using the following By: " + ELEMENT_LAST_PAGE);
        return integer;
    }

    public Integer getNumberOfAdsPerPage() {
        final Integer numberOfAds = webDriver.findElements(ELEMENT_ANUNT).size();

        if (numberOfAds == null || numberOfAds <= 0 || numberOfAds > 30)
            throw new RuntimeException("The number of ads per page, is not valid: " + numberOfAds +
                    ". This was found using the following By: " + ELEMENT_ANUNT);
        return numberOfAds;
    }
}
