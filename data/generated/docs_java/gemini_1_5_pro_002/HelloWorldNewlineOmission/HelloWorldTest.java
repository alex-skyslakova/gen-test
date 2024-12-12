import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class HelloWorldTest {

    @Test
    public void testMain() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        HelloWorld.main(null);

        assertEquals("Goodbye, World!", outputStream.toString());
    }
}
