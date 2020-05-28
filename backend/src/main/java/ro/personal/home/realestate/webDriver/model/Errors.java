package ro.personal.home.realestate.webDriver.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import ro.personal.home.realestate.enums.ElementValue;
import ro.personal.home.realestate.enums.ErrorType;
import ro.personal.home.realestate.enums.PageType;

import java.io.Serializable;

@Data
@Builder
@ToString
public class Errors implements Serializable {

    ErrorType errorType;
    String value;
    ElementValue elementValue;
    String errorMessage;
    Exception exception;
    PageType pageType;
}
