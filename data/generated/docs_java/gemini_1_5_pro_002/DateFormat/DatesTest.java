import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class DatesTest {

    @Test
    void testDateOutput() throws ParseException {
        // Capture system output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        // Run the main method
        Dates.main(new String[]{});

        // Get the output
        String output = outputStream.toString();


        // Split the output into lines
        String[] lines = output.split(System.lineSeparator());

        // Assert the number of lines
        assertEquals(2, lines.length, "Incorrect number of output lines.");

        // Parse and check the first line (YYYY-MM-DD)
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = dateFormat1.parse(lines[0].trim());
            Calendar cal = Calendar.getInstance();
            cal.setTime(date1);
             
            assertEquals(Calendar.getInstance().get(Calendar.YEAR), cal.get(Calendar.YEAR), "Year mismatch");
            assertEquals(Calendar.getInstance().get(Calendar.MONTH), cal.get(Calendar.MONTH), "Month mismatch");
            assertEquals(Calendar.getInstance().get(Calendar.DAY_OF_MONTH), cal.get(Calendar.DAY_OF_MONTH), "Day mismatch");

        } catch (ParseException e) {
            fail("Invalid date format on line 1: " + lines[0].trim());
        }


        // Parse and check the second line (Day, Month DD, YYYY)
        DateFormat dateFormat2 = new SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.US);
        try {
            Date date2 = dateFormat2.parse(lines[1].trim());
            Calendar cal = Calendar.getInstance();
            cal.setTime(date2);

            assertEquals(Calendar.getInstance().get(Calendar.YEAR), cal.get(Calendar.YEAR), "Year mismatch");
            assertEquals(Calendar.getInstance().get(Calendar.MONTH), cal.get(Calendar.MONTH), "Month mismatch");
            assertEquals(Calendar.getInstance().get(Calendar.DAY_OF_MONTH), cal.get(Calendar.DAY_OF_MONTH), "Day mismatch");


        } catch (ParseException e) {
            fail("Invalid date format on line 2: " + lines[1].trim());
        }
        
        // Restore system output
        System.setOut(System.out);

    }
}
