package ro.personal.home.realestate.webDriver.model.anunt;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ro.personal.home.realestate.enums.ElementValue;
import ro.personal.home.realestate.enums.ErrorType;
import ro.personal.home.realestate.enums.PageType;
import ro.personal.home.realestate.webDriver.Calculations;
import ro.personal.home.realestate.webDriver.model.Result;

import java.math.BigDecimal;

public class AnuntTeren extends Anunt {

    //sunt cu . in fata pentru a cauta exact in anuntul current
    public static final By ELEMENT_METRI_PATRATI = By.xpath(".//ul[@class= 'caracteristici']/li[1]");

    private double CONSIDER_ONLY_FIELDS_WITH_PRICE_LARGER_THEN = 2000;
    private double CONSIDER_ONLY_FIELDS_WITH_PRICE_SMALLER_THEN = 10000000;
    private double CONSIDER_ONLY_FIELDS_WITH_MP_LARGER_THEN = 100;
    private double CONSIDER_ONLY_FIELDS_WITH_MP_SMALLER_THEN = 50000;

    public AnuntTeren(WebElement elementulAnunt, PageType pageType, Result result) {
        super(elementulAnunt, pageType, result);
        this.pret = getPretFromElement();
        this.metriPatrati = getMetripatratiFromElement();
        this.pretPeMetruPatrat = getPretPeMetruPatratFromElement();
    }

    @Override
    public boolean validateAnunt() {
        return validatePret(CONSIDER_ONLY_FIELDS_WITH_PRICE_LARGER_THEN, CONSIDER_ONLY_FIELDS_WITH_PRICE_SMALLER_THEN, PageType.TEREN)
                && validateMetriPatrati(CONSIDER_ONLY_FIELDS_WITH_MP_LARGER_THEN, CONSIDER_ONLY_FIELDS_WITH_MP_SMALLER_THEN, PageType.TEREN)
                && validatePriceCurrency(PageType.TEREN) && validatePretPeMetruPatrat(PageType.TEREN)
                && validateId();
    }

    public BigDecimal getPretPeMetruPatratFromElement() {
        return Calculations.calculatePretPeMetruPatrat(pret, metriPatrati);
    }

    @Override
    public BigDecimal getMetripatratiFromElement() {
        BigDecimal value;
        try {
            value = (BigDecimal) getValueFromElement(ELEMENT_METRI_PATRATI, ELEMENT_METRI_PATRATI, ElementValue.PRET_CU_VIRGULA);
        } catch (Exception e) {
            result.add(ErrorType.ELEMENT_NOT_FOUND, ELEMENT_METRI_PATRATI.toString(), ElementValue.PRET_CU_VIRGULA, e.getMessage(), e, PageType.TEREN);
            return null;
        }
        return value;
    }
}
