import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InputTest {

    // Mocking Whipser API 

    @Test
    void testTypeParserValidInput() {
        assertEquals("breakfast", Input.typeParser("I would like a breakfast recipe"));
        assertEquals("lunch", Input.typeParser("Let's have lunch"));
        assertEquals("dinner", Input.typeParser("Dinner sounds great"));
    }

    @Test
    void testTypeParserInvalidInput() {
        assertEquals("Invalid", Input.typeParser("I would like a snack"));
        assertEquals("Invalid", Input.typeParser("breakfast or lunch"));
    }
}