package providers;

import holders.BoardIdValidationArgumentsHolder;
import io.restassured.specification.Argument;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Map;
import java.util.stream.Stream;

public class UpdateBoardIdValidationArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                new BoardIdValidationArgumentsHolder(
                        Map.of("id", ""),// no id
                        "Cannot PUT /1/boards/?key=4d9dd97638a81eaec3d5a7f125b6b562&token=ATTAac7d6572f06502abadc634407cef8927be972cf00c41777a053365e7ffcb6101C7527E9B"),
                new BoardIdValidationArgumentsHolder(
                        Map.of("id", "id"),// wrong id
                        "invalid id"),
                new BoardIdValidationArgumentsHolder(
                        Map.of("id", 123),// wrong data type
                        "invalid id"),
                new BoardIdValidationArgumentsHolder(
                        Map.of("id","68345f6639f73183f84a5dfbb"),// +extra char
                        "invalid id")
        ).map(Arguments::of);
    }
}
