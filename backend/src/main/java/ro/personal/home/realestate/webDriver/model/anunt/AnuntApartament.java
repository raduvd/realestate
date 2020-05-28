package ro.personal.home.realestate.webDriver.model.anunt;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ro.personal.home.realestate.webDriver.model.Result;
import ro.personal.home.realestate.webDriver.Calculations;
import ro.personal.home.realestate.enums.ElementValue;
import ro.personal.home.realestate.enums.PageType;

import java.math.BigDecimal;

public class AnuntApartament extends Anunt {

    private double CONSIDER_ONLY_APARTMENTS_WITH_PRICE_LARGER_THEN = 5000;
    private double CONSIDER_ONLY_APARTMENTS_WITH_PRICE_SMALLER_THEN = 1000000;
    private double CONSIDER_ONLY_APARTMENTS_WITH_MP_LARGER_THEN = 10;
    private double CONSIDER_ONLY_APARTMENTS_WITH_MP_SMALLER_THEN = 500;

    //sunt cu . in fata pentru a cauta exact in anuntul current
    public static final By ELEMENT_CARACTERISTICI = By.xpath(".//ul[@class= 'caracteristici']");
    public static final By ELEMENT_METRI_PATRATI = By.xpath(".//ul[@class= 'caracteristici']/li[2]");
    public static final By ELEMENT_METRI_PATRATI_FALLBACK = By.xpath(".//ul[@class= 'caracteristici']/li[1]");


    public AnuntApartament(WebElement elementulAnunt, PageType pageType, Result result) {
        super(elementulAnunt, pageType, result);
        this.pret = getPretFromElement();
        this.metriPatrati = getMetripatratiFromElement();
        this.pretPeMetruPatrat = getPretPeMetruPatratFromElement();
    }

    public BigDecimal getMetripatratiFromElement() {
        return (BigDecimal) getValueFromElement(ELEMENT_METRI_PATRATI, ELEMENT_METRI_PATRATI_FALLBACK, ElementValue.METRI_PATRATI);
    }

    public String getPriceCurrencyFromElement() {
        return (String) getValueFromElement(ELEMENT_PRICE_CURENCY, ELEMENT_PRICE_CURENCY, ElementValue.TEXT);
    }

    public boolean validateAnunt() {
        return validatePret(CONSIDER_ONLY_APARTMENTS_WITH_PRICE_LARGER_THEN, CONSIDER_ONLY_APARTMENTS_WITH_PRICE_SMALLER_THEN, PageType.APARTAMENT)
                && validateMetriPatrati(CONSIDER_ONLY_APARTMENTS_WITH_MP_LARGER_THEN, CONSIDER_ONLY_APARTMENTS_WITH_MP_SMALLER_THEN, PageType.APARTAMENT)
                && validatePriceCurrency(PageType.APARTAMENT) && validatePretPeMetruPatrat(PageType.APARTAMENT)
                && validateId();
    }

    @Override
    public BigDecimal getPretPeMetruPatratFromElement() {
        return Calculations.calculatePretPeMetruPatrat(pret, metriPatrati);
    }
}
