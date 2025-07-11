package providers;

import holders.AuthValidationArgumentsHolder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

public class AuthDeleteCardValidationArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream provideArguments(ExtensionContext context) {
        return Stream.of(
                new AuthValidationArgumentsHolder(
                        Collections.emptyMap(),
                        "{\"message\":\"missing scopes\"}" // сначала оставляем пустым, после запуска теста(getBoardValidationTest) копируем лог ошибки в консоли
                ),
                new AuthValidationArgumentsHolder(//только ключ
                        Map.of("key", "4d9dd97638a81eaec3d5a7f125b6b562"),
                        "{\"message\":\"missing scopes\"}"
                ),
                new AuthValidationArgumentsHolder(//только токен
                        Map.of("token", "ATTAac7d6572f06502abadc634407cef8927be972cf00c41777a053365e7ffcb6101C7527E9B"),
                        "{\"message\":\"missing scopes\"}"
                )
        ).map(Arguments::of);
    }
}
