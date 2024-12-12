import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class DepartmentNumbersTest {

    @Test
    public void testDepartmentCombinations() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        DepartmentNumbers.main(new String[]{});

        String expectedOutput = "Police  Sanitation  Fire\r\n" +
                "------  ----------  ----\r\n" +
                "  2         3         7\r\n" +
                "  2         7         3\r\n" +
                "  4         1         7\r\n" +
                "  4         7         1\r\n" +
                "  6         1         5\r\n" +
                "  6         5         1\r\n" +
                "\n6 valid combinations";


        assertEquals(expectedOutput, outputStream.toString());
    }


    @Test
    public void testNoCombinations(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));


        DepartmentNumbers.main(new String[]{});
        assertNotEquals(0,outputStream.toString().length());
    }
}
