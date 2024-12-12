import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class DayOfTheWeekTest {

    @Test
    fun testChristmasOnSundayBetween2008And2121() {
        val expectedYears = listOf(2011, 2016, 2022, 2033, 2039, 2044, 2050, 2061, 2067, 2072, 2078, 2089, 2095, 2101, 2107, 2112, 2118)
        val actualYears = mutableListOf<Int>()

        val calendar = GregorianCalendar(2008, Calendar.DECEMBER, 25)
        for (year in 2008..2121) {
            if (Calendar.SUNDAY == calendar[Calendar.DAY_OF_WEEK]) {
                actualYears.add(year)
            }
            calendar.add(Calendar.YEAR, 1)
        }

        assertEquals(expectedYears, actualYears, "The list of years where Christmas falls on a Sunday should match the expected list.")
    }
}
