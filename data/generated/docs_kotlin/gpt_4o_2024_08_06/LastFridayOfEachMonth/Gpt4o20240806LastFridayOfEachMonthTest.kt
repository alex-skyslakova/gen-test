import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class LastFridayOfEachMonthTest {

    private fun getLastFridaysOfYear(year: Int): List<String> {
        val lastFridays = mutableListOf<String>()
        val calendar = GregorianCalendar(year, 0, 31)
        for (month in 1..12) {
            val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
            var offset = calendar[Calendar.DAY_OF_WEEK] - Calendar.FRIDAY
            if (offset < 0) offset += 7
            val lastFriday = daysInMonth - offset
            lastFridays.add("$year-" + "%02d-".format(month) + "%02d".format(lastFriday))
            if (month < 12) {
                calendar.add(Calendar.DAY_OF_MONTH, 1)
                calendar.add(Calendar.MONTH, 1)
                calendar.add(Calendar.DAY_OF_MONTH, -1)
            }
        }
        return lastFridays
    }

    @Test
    fun testLastFridaysOf2012() {
        val expected = listOf(
            "2012-01-27",
            "2012-02-24",
            "2012-03-30",
            "2012-04-27",
            "2012-05-25",
            "2012-06-29",
            "2012-07-27",
            "2012-08-31",
            "2012-09-28",
            "2012-10-26",
            "2012-11-30",
            "2012-12-28"
        )
        assertEquals(expected, getLastFridaysOfYear(2012))
    }

    @Test
    fun testLastFridaysOf2020() {
        val expected = listOf(
            "2020-01-31",
            "2020-02-28",
            "2020-03-27",
            "2020-04-24",
            "2020-05-29",
            "2020-06-26",
            "2020-07-31",
            "2020-08-28",
            "2020-09-25",
            "2020-10-30",
            "2020-11-27",
            "2020-12-25"
        )
        assertEquals(expected, getLastFridaysOfYear(2020))
    }

    @Test
    fun testLastFridaysOf2021() {
        val expected = listOf(
            "2021-01-29",
            "2021-02-26",
            "2021-03-26",
            "2021-04-30",
            "2021-05-28",
            "2021-06-25",
            "2021-07-30",
            "2021-08-27",
            "2021-09-24",
            "2021-10-29",
            "2021-11-26",
            "2021-12-31"
        )
        assertEquals(expected, getLastFridaysOfYear(2021))
    }

    @Test
    fun testLastFridaysOfLeapYear() {
        val expected = listOf(
            "2024-01-26",
            "2024-02-23",
            "2024-03-29",
            "2024-04-26",
            "2024-05-31",
            "2024-06-28",
            "2024-07-26",
            "2024-08-30",
            "2024-09-27",
            "2024-10-25",
            "2024-11-29",
            "2024-12-27"
        )
        assertEquals(expected, getLastFridaysOfYear(2024))
    }
}
