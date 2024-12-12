import org.junit.jupiter.api.Test;
import javax.swing.JLabel;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import static org.junit.jupiter.api.Assertions.*;

class RotateTest {

    @Test
    void testInitialText() {
        State state = new State();
        JLabel label = new JLabel(state.text);
        assertEquals("Hello World! ", label.getText());
    }

    @Test
    void testRotateRight() {
        State state = new State();
        String rotatedText = Rotate.rotate(state.text, 1);
        assertEquals(" Hello World!", rotatedText);
    }

    @Test
    void testRotateLeft() {
        State state = new State();
        String rotatedText = Rotate.rotate(state.text, state.text.length() - 1);
        assertEquals("! Hello World ", rotatedText);
    }

    @Test
    void testMouseClickReversesDirection() {
        State state = new State();
        JLabel label = new JLabel(state.text);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                state.rotateRight = !state.rotateRight;
            }
        });

        // Simulate a mouse click
        MouseEvent clickEvent = new MouseEvent(label, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false);
        label.dispatchEvent(clickEvent);

        assertFalse(state.rotateRight);
    }

    @Test
    void testTimerTaskUpdatesLabel() throws InterruptedException {
        State state = new State();
        JLabel label = new JLabel(state.text);

        TimerTask task = new TimerTask() {
            public void run() {
                int delta = state.rotateRight ? 1 : -1;
                state.startIndex = (state.startIndex + state.text.length() + delta) % state.text.length();
                label.setText(Rotate.rotate(state.text, state.startIndex));
            }
        };

        Timer timer = new Timer(false);
        timer.schedule(task, 0, 500);

        // Wait for the timer to run at least once
        Thread.sleep(600);

        assertNotEquals("Hello World! ", label.getText());

        timer.cancel();
    }

    @Test
    void testWindowClosedCancelsTimer() {
        State state = new State();
        JLabel label = new JLabel(state.text);

        TimerTask task = new TimerTask() {
            public void run() {
                int delta = state.rotateRight ? 1 : -1;
                state.startIndex = (state.startIndex + state.text.length() + delta) % state.text.length();
                label.setText(Rotate.rotate(state.text, state.startIndex));
            }
        };

        Timer timer = new Timer(false);
        timer.schedule(task, 0, 500);

        // Simulate window close event
        timer.cancel();

        // Ensure the timer is cancelled
        assertTrue(timer.purge() == 0);
    }
}
