import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RotateTest {

    private Rotate.State state;
    private JLabel label;

    @BeforeEach
    public void setUp() {
        state = new Rotate.State();
        label = new JLabel(state.text);
    }

    @Test
    public void testInitialText() {
        assertEquals("Hello World! ", label.getText());
    }

    @Test
    public void testRotateRight() {
        Rotate.rotate(state.text, 1);
        assertEquals("ello World! H", Rotate.rotate(state.text, 1));
    }

    @Test
    public void testRotateLeft() {
        state.rotateRight = false;
        Rotate.rotate(state.text, -1);
        assertEquals(" Hello World!", Rotate.rotate(state.text, -1));
    }

    @Test
    public void testMouseClickReversesDirection() {
        boolean initialDirection = state.rotateRight;
        label.dispatchEvent(new MouseEvent(label, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false));
        assertTrue(initialDirection != state.rotateRight);
    }

    @Test
    public void testRotateFunctionality() {
        assertEquals("Hello World! ", Rotate.rotate(state.text, 0));
        assertEquals("ello World! H", Rotate.rotate(state.text, 1));
        assertEquals("llo World! He", Rotate.rotate(state.text, 2));
        assertEquals(" World! Hello", Rotate.rotate(state.text, 6));
        assertEquals("! Hello World", Rotate.rotate(state.text, 12));
    }
}
