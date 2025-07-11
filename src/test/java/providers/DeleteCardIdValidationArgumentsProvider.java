package providers;

import holders.BoardIdValidationArgumentsHolder;
import holders.CardIdValidationArgumentsHolder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Map;
import java.util.stream.Stream;

public class DeleteCardIdValidationArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream provideArguments(ExtensionContext context) {
        return Stream.of(
                new CardIdValidationArgumentsHolder(
                        Map.of("id", ""),
                        "Cannot DELETE /1/cards/?key=4d9dd97638a81eaec3d5a7f125b6b562&token=ATTAac7d6572f06502abadc634407cef8927be972cf00c41777a053365e7ffcb6101C7527E9B",
                        404),
                new CardIdValidationArgumentsHolder(
                        Map.of("id", 123),
                        "invalid id",
                        400),
                new CardIdValidationArgumentsHolder(
                        Map.of("id", "68345fadb7caea7fdd9104e"),
                        "invalid id",
                        400),
                new CardIdValidationArgumentsHolder(
                        Map.of("id", "68345fadb7caea7fdd9104eee"),
                        "invalid id",
                        400)
        ).map(Arguments::of);
    }
}
