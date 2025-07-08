package holders;

import java.util.Map;

public class CardNameValidationArgumentsHolder<E> {

    private final Map<String,E> bodyParams;
    private final String errorMessage;

    public CardNameValidationArgumentsHolder(Map<String, E> bodyParams, String errorMessage) {
        this.bodyParams = bodyParams;
        this.errorMessage = errorMessage;
    }

    public Map<String, E> getBodyParams() {
        return bodyParams;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
