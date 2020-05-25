package ro.personal.home.realestate.webDriver.model;


import lombok.Data;
import ro.personal.home.realestate.webDriver.model.enums.ElementValue;
import ro.personal.home.realestate.webDriver.model.enums.ErrorType;
import ro.personal.home.realestate.webDriver.model.enums.PageType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Result implements Serializable {

    //TODO sa pun un obiect de genul ca si parametru pestr o metoda si sa il populez si apoi sa il returnez
    //TODO Sa initializez primele 3 fielduri, iar restul de fielduri voi folosi metoda add
    //TODO sa iau numarul asta de pe pagina
    private Integer numarAnunturiPerPagina;
    private Integer numarulPaginii;
    private Integer numarAnunturiProcesate;
    private List<Errors> allErrorsList = new ArrayList<>();
    private List<Errors> invalidValueList = new ArrayList<>();
    private List<Errors> castingExceptionList = new ArrayList<>();
    private List<Errors> elementNotFoundList = new ArrayList<>();
    private List<Errors> waitingTimeoutList = new ArrayList<>();

    public void add(ErrorType errorType, String value, ElementValue elementValue, String errorMessage, Exception exception, PageType impobilType) {
        if (value == null)
            value = "null";

        if (errorMessage.length() > 130) {
            errorMessage = errorMessage.substring(0, 129);
        }
        final Errors error = Errors.builder().
                errorType(errorType).
                value(value).
                elementValue(elementValue).
                errorMessage(errorMessage).
                exception(exception).
                pageType(impobilType).
                build();

        allErrorsList.add(error);

        switch (errorType) {
            case INVALID_VALUE:
                invalidValueList.add(error);
                break;
            case WAITING_TIMEOUT:
                waitingTimeoutList.add(error);
                break;
            case CASTING_EXCEPTION:
                castingExceptionList.add(error);
                break;
            case ELEMENT_NOT_FOUND:
                elementNotFoundList.add(error);
                break;
        }
    }
}
