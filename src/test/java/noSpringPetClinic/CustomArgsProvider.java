package noSpringPetClinic;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CustomArgsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(Arguments.of("Fl", 7, 4),
                         Arguments.of("CT", 8, 5),
                         Arguments.of("AZ", 9, 6));
    }
}
