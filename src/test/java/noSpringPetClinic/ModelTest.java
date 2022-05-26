package noSpringPetClinic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("model")
public interface ModelTest {

    @BeforeAll
    default void beforeAll() {
        System.out.println("default model beforeAll()");
    }

}
