package noSpringPetClinic.model;

import noSpringPetClinic.ModelTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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
    @Test
    void myRepeatedTest() {
        // todo - impl
    }
}