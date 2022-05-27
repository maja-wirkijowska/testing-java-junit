package noSpringPetClinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class AnnotatedMockTests {

    @Mock
    Map<String, Object> mapMock;

    @BeforeEach
    void setUp() {
        // deprecated, now @ExtendWith(MockitoExtension.class)
        // MockitoAnnotations.initMocks(this));
    }

    @Test
    void testMock() {
        mapMock.put("key", "value");
    }
}
