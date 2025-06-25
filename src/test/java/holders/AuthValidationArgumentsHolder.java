package holders;

import java.util.Map;

public class AuthValidationArgumentsHolder {

    private final Map<String,String> authParams;// поле для ключа-токена
    private final String errorMessage;// для лога ошшибки(исп в assert)

    public AuthValidationArgumentsHolder(Map<String, String> authParams, String errorMessage) {
        this.authParams = authParams;
        this.errorMessage = errorMessage;
    }

    public Map<String,String> getAuthParams(){
        return authParams;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}
