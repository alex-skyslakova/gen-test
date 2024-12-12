import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class DatesTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testMain() {
        // Set a specific date for testing purposes
        Calendar testDate = new GregorianCalendar(2007, Calendar.NOVEMBER, 23);
        Dates.main(new String[]{});

        // Expected outputs
        String expectedNumericFormat = "2007-11-23\n";
        String expectedWordFormat = "Friday, November 23, 2007\n";

        // Capture the output
        String actualOutput = outContent.toString();

        // Verify the outputs
        assertEquals(expectedNumericFormat, actualOutput.substring(0, expectedNumericFormat.length()));
        assertEquals(expectedWordFormat, actualOutput.substring(expectedNumericFormat.length()));
    }
}
