package holders;

import java.util.Map;

public class BoardIdValidationArgumentsHolder {

    private final Map<String, Object> pathParams;
    private final String errorMessage;


    public BoardIdValidationArgumentsHolder(Map<String, Object> pathParams, String errorMessage) {
        this.pathParams = pathParams;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Map<String, Object> getPathParams() {
        return pathParams;
    }
}
