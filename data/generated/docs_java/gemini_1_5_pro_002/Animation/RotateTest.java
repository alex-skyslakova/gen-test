import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RotateTest {

    @Test
    void testRotate() {
        String text = "Hello World! ";
        assertEquals("Hello World! ", Rotate.rotate(text, 0));
        assertEquals("! Hello World ", Rotate.rotate(text, 1));
        assertEquals(" !Hello World", Rotate.rotate(text, 2));
        assertEquals("Hello World! ", Rotate.rotate(text, text.length()));
        assertEquals(" World! Hello", Rotate.rotate(text, -1)); 
        assertEquals("orld! Hello W", Rotate.rotate(text, -2));

    }

    @Test
    void testState() {
        Rotate.State state = new Rotate.State();
        assertEquals("Hello World! ", state.text);
        assertEquals(0, state.startIndex);
        assertTrue(state.rotateRight);

        state.rotateRight = false;
        assertFalse(state.rotateRight);

        state.startIndex = 5;
        assertEquals(5, state.startIndex);

    }


    // Note: Testing the main method and GUI interaction directly is difficult 
    // with unit tests.  These elements would typically be tested with UI testing 
    // frameworks or integration tests.  The provided tests focus on the core 
    // logic within the rotate and State classes.

}
