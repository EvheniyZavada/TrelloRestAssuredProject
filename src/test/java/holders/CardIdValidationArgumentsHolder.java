package holders;

import java.util.Map;

public class CardIdValidationArgumentsHolder {

    private final Map<String, Object> pathParams;
    private final String errorMessage;
    private final Integer statusCode;

    public CardIdValidationArgumentsHolder(Map<String, Object> pathParams, String errorMessage, Integer statusCode) {
        this.pathParams = pathParams;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Map<String, Object> getPathParams() {
        return pathParams;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}
