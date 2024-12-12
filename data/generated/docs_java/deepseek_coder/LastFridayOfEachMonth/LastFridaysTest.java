import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LastFridaysTest {

    @Test
    public void testLastFridaysOf2012() {
        int year = 2012;
        String[] expectedDates = {
            "2012-01-27", "2012-02-24", "2012-03-30", "2012-04-27",
            "2012-05-25", "2012-06-29", "2012-07-27", "2012-08-31",
            "2012-09-28", "2012-10-26", "2012-11-30", "2012-12-28"
        };

        GregorianCalendar c = new GregorianCalendar(year, 0, 1);
        int monthIndex = 0;

        for (String mon : new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}) {
            int totalDaysOfMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
            c.set(Calendar.DAY_OF_MONTH, totalDaysOfMonth);

            int daysToRollBack = (c.get(Calendar.DAY_OF_WEEK) + 1) % 7;
            int day = totalDaysOfMonth - daysToRollBack;
            c.set(Calendar.DAY_OF_MONTH, day);

            String actualDate = String.format("%d-%02d-%02d", year, c.get(Calendar.MONTH) + 1, day);
            assertEquals(expectedDates[monthIndex], actualDate);

            c.set(year, c.get(Calendar.MONTH) + 1, 1);
            monthIndex++;
        }
    }

    @Test
    public void testLastFridaysOf2023() {
        int year = 2023;
        String[] expectedDates = {
            "2023-01-27", "2023-02-24", "2023-03-31", "2023-04-28",
            "2023-05-26", "2023-06-30", "2023-07-28", "2023-08-25",
            "2023-09-29", "2023-10-27", "2023-11-24", "2023-12-29"
        };

        GregorianCalendar c = new GregorianCalendar(year, 0, 1);
        int monthIndex = 0;

        for (String mon : new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}) {
            int totalDaysOfMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
            c.set(Calendar.DAY_OF_MONTH, totalDaysOfMonth);

            int daysToRollBack = (c.get(Calendar.DAY_OF_WEEK) + 1) % 7;
            int day = totalDaysOfMonth - daysToRollBack;
            c.set(Calendar.DAY_OF_MONTH, day);

            String actualDate = String.format("%d-%02d-%02d", year, c.get(Calendar.MONTH) + 1, day);
            assertEquals(expectedDates[monthIndex], actualDate);

            c.set(year, c.get(Calendar.MONTH) + 1, 1);
            monthIndex++;
        }
    }
}
