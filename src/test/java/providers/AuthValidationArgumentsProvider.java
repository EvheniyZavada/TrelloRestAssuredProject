package providers;

import holders.AuthValidationArgumentsHolder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

public class AuthValidationArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                new AuthValidationArgumentsHolder(
                        Collections.emptyMap(),
                        "invalid key"
                ),
                new AuthValidationArgumentsHolder(
                        Map.of("key", "4d9dd97638a81eaec3d5a7f125b6b562"),
                        "invalid key"
                ),
                new AuthValidationArgumentsHolder(
                        Map.of("token", "ATTAac7d6572f06502abadc634407cef8927be972cf00c41777a053365e7ffcb6101C7527E9B"),
                        "invalid key"
                )
        ).map(Arguments::of);
    }
}

