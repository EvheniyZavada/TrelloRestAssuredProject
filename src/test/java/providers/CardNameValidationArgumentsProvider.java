package providers;

import holders.CardNameValidationArgumentsHolder;
import io.restassured.specification.Argument;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Map;
import java.util.stream.Stream;

public class CardNameValidationArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream provideArguments(ExtensionContext extensionContext){
        return Stream.of(
                new CardNameValidationArgumentsHolder(Map.of(
                        "idList", "684497a8f96fc4515a307c9f",
                        "name", 1.75),
                        "invalid value for name"),
                new CardNameValidationArgumentsHolder(Map.of(
                        "idList", "684497a8f96fc4515a307c9f",
                        "name", "au".repeat(18000)),
                        "invalid value for name"),
                new CardNameValidationArgumentsHolder(Map.of(
                        "idList", "684497a8f96fc4515a307c9f",
                        "name", true),
                        "invalid value for name"),
                new CardNameValidationArgumentsHolder(Map.of(
                        "idList","684497a8f96fc4515a307c9f",
                        "name", new String[]{"a", "b"}),
                        "invalid value for name")
        ).map(Arguments::of);


    }
}
