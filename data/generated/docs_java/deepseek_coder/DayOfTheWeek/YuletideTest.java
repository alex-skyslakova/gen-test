import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.junit.jupiter.api.Assertions.*;

public class YuletideTest {

    @Test
    public void testYuletideYears() {
        // Expected years when December 25th is a Sunday between 2008 and 2121
        int[] expectedYears = {2011, 2016, 2022, 2033, 2039, 2044, 2050, 2061, 2067, 2072, 2078, 2089, 2095, 2101, 2107, 2112, 2118};

        int expectedIndex = 0;
        for (int year = 2008; year <= 2121; year++) {
            Calendar cal = new GregorianCalendar(year, Calendar.DECEMBER, 25);
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                assertEquals(expectedYears[expectedIndex], year);
                expectedIndex++;
            }
        }
        assertEquals(expectedYears.length, expectedIndex);
    }

    @Test
    public void testNonYuletideYears() {
        // Years when December 25th is not a Sunday
        int[] nonExpectedYears = {2008, 2009, 2010, 2012, 2013, 2014, 2015, 2017, 2018, 2019, 2020, 2021, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032, 2034, 2035, 2036, 2037, 2038, 2040, 2041, 2042, 2043, 2045, 2046, 2047, 2048, 2049, 2051, 2052, 2053, 2054, 2055, 2056, 2057, 2058, 2059, 2060, 2062, 2063, 2064, 2065, 2066, 2068, 2069, 2070, 2071, 2073, 2074, 2075, 2076, 2077, 2079, 2080, 2081, 2082, 2083, 2084, 2085, 2086, 2087, 2088, 2090, 2091, 2092, 2093, 2094, 2096, 2097, 2098, 2099, 2100, 2102, 2103, 2104, 2105, 2106, 2108, 2109, 2110, 2111, 2113, 2114, 2115, 2116, 2117, 2119, 2120, 2121};

        for (int year : nonExpectedYears) {
            Calendar cal = new GregorianCalendar(year, Calendar.DECEMBER, 25);
            assertNotEquals(Calendar.SUNDAY, cal.get(Calendar.DAY_OF_WEEK));
        }
    }

    @Test
    public void testYearOutOfRange() {
        // Test a year outside the range 2008-2121
        int year = 2122;
        Calendar cal = new GregorianCalendar(year, Calendar.DECEMBER, 25);
        assertNotEquals(Calendar.SUNDAY, cal.get(Calendar.DAY_OF_WEEK));
    }
}
