package noSpringPetClinic.model;

import noSpringPetClinic.ModelRepeatedTest;
import org.junit.jupiter.api.*;

public class PersonRepeatedTest implements ModelRepeatedTest {

    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} / {totalRepetitions}")
    @DisplayName("MyRepeatedTest")
    void myRepeatedTest() {
        // todo - impl
    }

    @RepeatedTest(5)
    void myRepeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println(testInfo.getDisplayName() + " : " + repetitionInfo.getCurrentRepetition());
    }

    @Disabled
    @RepeatedTest(value = 5, name = "{displayName} : {currentRepetition} / {totalRepetitions}")
    @DisplayName("MyRepeatedTest")
    void assignmentRepeatedTest() {
        // todo - impl
    }
}
