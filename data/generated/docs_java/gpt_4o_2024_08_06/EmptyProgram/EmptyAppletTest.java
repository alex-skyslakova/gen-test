import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;
import java.applet.Applet;

public class EmptyAppletTest {

    @Test
    public void testAppletInitialization() {
        assertDoesNotThrow(() -> {
            Applet applet = new EmptyApplet();
            applet.init();
        });
    }
}
