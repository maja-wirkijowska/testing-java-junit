package noSpringPetClinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest {

    IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @DisplayName("Test Proper View Name is returned for Index page")
    @Test
    void index() {
        assertEquals("index", controller.index());
        assertNotEquals("indexd", controller.index(), "wrong view returned");

        assertEquals("index", controller.index(), () -> "Another expensive Message " +
                "Make me only if you have to");

    }

    @Test
    @DisplayName("Test exception")
    void oopsHandler() {
//        assertTrue("notimplemented".equals(controller.oopsHandler()), () -> "This is some expensive " +
//                "message to build " + "for my test");
        assertThrows(ValueNotFoundException.class, () -> {
            controller.oopsHandler();
        });
    }

    @Disabled("Demo of timeout")
    @Test
    void testTimeOut() {
        assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep(5000);
            System.out.println("At testTimeOut");
        });
    }

    @Disabled("Demo of timeout preemptive")
    @Test
    void testTimeOutPreempt() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            Thread.sleep(5000);
            System.out.println("At testTimeOutPreempt");
        });
    }
}