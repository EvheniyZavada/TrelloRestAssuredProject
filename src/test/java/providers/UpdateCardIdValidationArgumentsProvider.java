package providers;

import holders.CardIdValidationArgumentsHolder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Map;
import java.util.stream.Stream;

public class UpdateCardIdValidationArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream provideArguments(ExtensionContext extensionContext){
        return Stream.of(
                new CardIdValidationArgumentsHolder(
                        Map.of("id", ""),
                        "Cannot PUT /1/cards/?token=ATTAac7d6572f06502abadc634407cef8927be972cf00c41777a053365e7ffcb6101C7527E9B&key=4d9dd97638a81eaec3d5a7f125b6b562",
                        404),
                new CardIdValidationArgumentsHolder(
                        Map.of("id", 123),
                        "invalid id",
                        400),
                new CardIdValidationArgumentsHolder(
                        Map.of("id", "68345fadb7caea7fdd9104e33"),
                        "invalid id",
                        400),
                new CardIdValidationArgumentsHolder(
                        Map.of("id", "68345fadb7caea7fdd9104e"),
                        "invalid id",
                        400)
                ).map(Arguments::of);
    }
}
