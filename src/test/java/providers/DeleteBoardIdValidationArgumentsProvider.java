package providers;

import holders.BoardIdValidationArgumentsHolder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Map;
import java.util.stream.Stream;

public class DeleteBoardIdValidationArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream provideArguments(ExtensionContext context) {
        return Stream.of(
                new BoardIdValidationArgumentsHolder(
                        Map.of("id", ""),
                        "Cannot DELETE /1/boards/?token=ATTAac7d6572f06502abadc634407cef8927be972cf00c41777a053365e7ffcb6101C7527E9B&key=4d9dd97638a81eaec3d5a7f125b6b562",
                        404),
                new BoardIdValidationArgumentsHolder(
                        Map.of("id", 123),
                        "invalid id",
                        400),
                new BoardIdValidationArgumentsHolder(
                        Map.of("id", "68499967310c9b0128bb22c"),
                        "invalid id",
                        400),
                new BoardIdValidationArgumentsHolder(
                        Map.of("id", "68499967310c9b0128bb22c11"),
                        "invalid id",
                        400)
        ).map(Arguments::of);
    }
}
