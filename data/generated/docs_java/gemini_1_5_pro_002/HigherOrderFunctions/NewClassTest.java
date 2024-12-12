import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class NewClassTest {

    @Test
    public void testConstructorCallsSecond() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        new NewClass();

        assertEquals("Second\n", outContent.toString());
    }

    @Test
    public void testFirstCallsCallback() {
        final boolean[] called = {false};
        AnEventOrCallback mockCallback = new AnEventOrCallback() {
            @Override
            public void call() {
                called[0] = true;
            }
        };

        NewClass nc = new NewClass();
        nc.first(mockCallback);

        assertTrue(called[0]);
    }


}
