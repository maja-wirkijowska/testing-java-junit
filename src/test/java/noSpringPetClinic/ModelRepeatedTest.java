package noSpringPetClinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("repeated")
public interface ModelRepeatedTest {

    @BeforeEach
    default void beforeEachConsoleOutput(TestInfo testInfo){
        System.out.println("Running Test - " + testInfo.getDisplayName());
    }
}
