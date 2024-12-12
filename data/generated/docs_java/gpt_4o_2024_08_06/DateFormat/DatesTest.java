import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.DateFormatSymbols;
import static org.junit.jupiter.api.Assertions.*;

public class DatesTest {

    @Test
    public void testDateFormatNumeric() {
        Calendar now = new GregorianCalendar();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1; // months are 0 indexed
        int day = now.get(Calendar.DATE);

        String expectedDate = year + "-" + month + "-" + day;
        String actualDate = now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DATE);

        assertEquals(expectedDate, actualDate, "The numeric date format is incorrect.");
    }

    @Test
    public void testDateFormatWords() {
        Calendar now = new GregorianCalendar();
        DateFormatSymbols symbols = new DateFormatSymbols();

        String expectedWeekday = symbols.getWeekdays()[now.get(Calendar.DAY_OF_WEEK)];
        String expectedMonth = symbols.getMonths()[now.get(Calendar.MONTH)];
        int day = now.get(Calendar.DATE);
        int year = now.get(Calendar.YEAR);

        String expectedDate = expectedWeekday + ", " + expectedMonth + " " + day + ", " + year;
        String actualDate = symbols.getWeekdays()[now.get(Calendar.DAY_OF_WEEK)] + ", " +
                            symbols.getMonths()[now.get(Calendar.MONTH)] + " " +
                            now.get(Calendar.DATE) + ", " + now.get(Calendar.YEAR);

        assertEquals(expectedDate, actualDate, "The word date format is incorrect.");
    }
}
