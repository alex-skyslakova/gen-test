import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class YuletideTest {

    @Test
    public void testChristmasSundays() {
        Set<Integer> expectedYears = new HashSet<>();
        expectedYears.add(2011);
        expectedYears.add(2016);
        expectedYears.add(2022);
        expectedYears.add(2033);
        expectedYears.add(2038);
        expectedYears.add(2044);
        expectedYears.add(2055);
        expectedYears.add(2060);
        expectedYears.add(2066);
        expectedYears.add(2077);
        expectedYears.add(2082);
        expectedYears.add(2088);
        expectedYears.add(2099);
        expectedYears.add(2105);
        expectedYears.add(2111);
        expectedYears.add(2122);


        Set<Integer> actualYears = new HashSet<>();
        for (int i = 2008; i <= 2121; i++) {
            Calendar cal = new GregorianCalendar(i, Calendar.DECEMBER, 25);
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                actualYears.add(i);
            }
        }

        assertEquals(expectedYears, actualYears);
    }


    @Test
    public void testSpecificYearSunday() {
        int year = 2011; // Example year known to be a Sunday
        Calendar cal = new GregorianCalendar(year, Calendar.DECEMBER, 25);
        assertEquals(Calendar.SUNDAY, cal.get(Calendar.DAY_OF_WEEK));
    }

    @Test
    public void testSpecificYearNotSunday() {
        int year = 2012; // Example year known to NOT be a Sunday
        Calendar cal = new GregorianCalendar(year, Calendar.DECEMBER, 25);
        assertNotEquals(Calendar.SUNDAY, cal.get(Calendar.DAY_OF_WEEK));
    }



}
