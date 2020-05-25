package ro.personal.home.realestate.webDriver.model.anunt;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ro.personal.home.realestate.webDriver.model.enums.ElementValue;
import ro.personal.home.realestate.webDriver.model.enums.PageType;
import ro.personal.home.realestate.webDriver.model.enums.ErrorType;
import ro.personal.home.realestate.webDriver.model.Result;

import java.math.BigDecimal;

public class AnuntTeren extends Anunt {

    //sunt cu . in fata pentru a cauta exact in anuntul current
    public static final By ELEMENT_PRET_METRU_PATRAT = By.xpath(".//div[@class= 'box-pret-mobile']/div[3]");

    private double CONSIDER_ONLY_TEREN_WITH_MP_PRICE_LARGER_THEN = 20;
    private double CONSIDER_ONLY_TEREN_WITH_MP_PRICE_SMALLER_THEN = 500;

    public AnuntTeren(WebElement elementulAnunt, PageType pageType, Result result) {
        super(elementulAnunt, pageType,result);
        this.pretPeMetruPatrat = getPretPeMetruPatratFromElement();
    }

    @Override
    public boolean validateAnunt() {
        if (pretPeMetruPatrat == null ||
                pretPeMetruPatrat.doubleValue() < CONSIDER_ONLY_TEREN_WITH_MP_PRICE_LARGER_THEN ||
                pretPeMetruPatrat.doubleValue() > CONSIDER_ONLY_TEREN_WITH_MP_PRICE_SMALLER_THEN) {
            result.add(ErrorType.INVALID_VALUE, pretPeMetruPatrat == null ? null : pretPeMetruPatrat.toString(), null, "pret pe metruPatrat non-valid", null, PageType.TEREN);
            return false;
        }
        return validatePriceCurrency(PageType.TEREN) && validatePretPeMetruPatrat(PageType.TEREN);
    }

    public BigDecimal getPretPeMetruPatratFromElement() {
        BigDecimal value;
        try {
            value = (BigDecimal) getValueFromElement(ELEMENT_PRET_METRU_PATRAT, ELEMENT_PRET_METRU_PATRAT, ElementValue.PRET_CU_VIRGULA);
        } catch (Exception e) {
            result.add(ErrorType.ELEMENT_NOT_FOUND, ELEMENT_PRET_METRU_PATRAT.toString(), ElementValue.PRET_CU_VIRGULA, e.getMessage(), e, PageType.TEREN);
            return null;
        }
        return value;
    }

    @Override
    public BigDecimal getMetripatratiFromElement() {
        return null;
    }

    @Override
    public BigDecimal getPretFromElement() {
        return null;
    }
}
