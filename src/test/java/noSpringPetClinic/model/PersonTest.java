package noSpringPetClinic.model;

import noSpringPetClinic.ModelTest;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest implements ModelTest {

    @Test
    void groupAssertions() {
        //given
        Person person = new Person(1L, "Joe", "Buck");

        //then
        assertAll("Test Props Set",
                () -> assertEquals("Joe", person.getFirstName()),
                () -> assertEquals("Buck", person.getLastName()));
    }

    @Test
    void groupAssertionsMsg() {
        //given
        Person person = new Person(1L, "Joe", "Buck");

        //then
        assertAll("Test Props Set",
                () -> assertEquals("Joe", person.getFirstName(), "First Name Failed"),
                () -> assertEquals("Buck", person.getLastName(), "Last Name Failed"));
    }

    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} / {totalRepetitions}")
    @DisplayName("MyRepeatedTest")
    void myRepeatedTest() {
        // todo - impl
    }

    @RepeatedTest(5)
    void myRepeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println(testInfo.getDisplayName() + " : " + repetitionInfo.getCurrentRepetition());
    }
}