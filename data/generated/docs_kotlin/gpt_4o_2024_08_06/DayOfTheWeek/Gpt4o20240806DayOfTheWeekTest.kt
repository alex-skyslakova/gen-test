import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class DayOfTheWeekTest {

    @Test
    fun testChristmasOnSunday() {
        val expectedYears = listOf(2011, 2016, 2022, 2033, 2039, 2044, 2050, 2061, 2067, 2072, 2078, 2089, 2095, 2101, 2112, 2118)
        val actualYears = getYearsWhenChristmasIsSunday(2008, 2121)
        assertEquals(expectedYears, actualYears)
    }

    private fun getYearsWhenChristmasIsSunday(startYear: Int, endYear: Int): List<Int> {
        val years = mutableListOf<Int>()
        val calendar = GregorianCalendar(startYear, Calendar.DECEMBER, 25)
        for (year in startYear..endYear) {
            if (Calendar.SUNDAY == calendar[Calendar.DAY_OF_WEEK]) {
                years.add(year)
            }
            calendar.add(Calendar.YEAR, 1)
        }
        return years
    }
}
