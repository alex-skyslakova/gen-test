import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YuletideTest {

    @Test
    public void testYearsWhenDecember25IsSunday() {
        List<Integer> expectedYears = List.of(
            2011, 2016, 2022, 2033, 2039, 2044, 2050, 2061, 2067, 2072, 2078, 2089, 2095, 2100, 2106, 2117
        );

        List<Integer> actualYears = new ArrayList<>();
        for (int i = 2008; i <= 2121; i++) {
            Calendar cal = new GregorianCalendar(i, Calendar.DECEMBER, 25);
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                actualYears.add(i);
            }
        }

        assertEquals(expectedYears, actualYears, "The years when December 25th is a Sunday do not match the expected values.");
    }
}
