package noSpringPetClinic;

import org.junit.jupiter.api.*;

@Tag("model")
public interface ModelTest {

    @BeforeEach
    default void beforeEachConsoleOutput(TestInfo testInfo){
        System.out.println("Running Test - " + testInfo.getDisplayName());
    }
}
