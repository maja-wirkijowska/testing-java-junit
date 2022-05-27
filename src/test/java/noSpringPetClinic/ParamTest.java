package noSpringPetClinic;

import noSpringPetClinic.model.OwnerType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

public class ParamTest {

    @ParameterizedTest
    @ValueSource(strings = {"Maja", "JUnit", "Test"})
    void testValueSource(String value) {
        System.out.println(value);
    }

    @DisplayName("Display name source test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @ValueSource(strings = {"Maja", "JUnit", "Test"})
    void displayNameSourceTest(String value) {
        System.out.println(value);
    }

    @DisplayName("ENUM source test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @EnumSource(OwnerType.class)
    void enumSourceTest(OwnerType ownerType) {
        System.out.println(ownerType);
    }

    @DisplayName("CSV input test - comma seperated values")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @CsvSource({"FL, 1, 1", "CT, 2, 2", "AZ, 3, 3"})
    void csvInputTest(String stateName, int value1, int value2) {
        System.out.println(stateName + " = " + value1 + ":" + value2);
    }

    @DisplayName("CSV FILE input test - comma seperated values")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
    void csvFileInputTest(String stateName, int value1, int value2) {
        System.out.println(stateName + " = " + value1 + ":" + value2);
    }

    @DisplayName("method provider test - comma seperated values")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @MethodSource("getArgs")
    void fromMethodTest(String stateName, int value1, int value2) {
        System.out.println(stateName + " = " + value1 + ":" + value2);
    }

    static Stream<Arguments> getArgs() {
        return Stream.of(Arguments.of("Fl", 1, 4),
                Arguments.of("CT", 2, 5),
                Arguments.of("AZ", 3, 6));
    }

    @DisplayName("custom provider test - comma seperated values")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @ArgumentsSource(CustomArgsProvider.class)
    void fromCustomProviderTest(String stateName, int value1, int value2) {
        System.out.println(stateName + " = " + value1 + ":" + value2);
    }
}
