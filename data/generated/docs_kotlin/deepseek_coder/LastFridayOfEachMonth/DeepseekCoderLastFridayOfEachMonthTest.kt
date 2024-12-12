import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class LastFridayOfEachMonthTest {

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
        val actual = getLastFridaysOfYear(2012)
        assertEquals(expected, actual)
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
        val actual = getLastFridaysOfYear(2020)
        assertEquals(expected, actual)
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
        val actual = getLastFridaysOfYear(2021)
        assertEquals(expected, actual)
    }

    private fun getLastFridaysOfYear(year: Int): List<String> {
        val result = mutableListOf<String>()
        val calendar = GregorianCalendar(year, 0, 31)
        for (month in 1..12) {
            val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
            var offset = calendar[Calendar.DAY_OF_WEEK] - Calendar.FRIDAY
            if (offset < 0) offset += 7
            val lastFriday = daysInMonth - offset
            result.add("$year-" + "%02d-".format(month) + "%02d".format(lastFriday))
            if (month < 12) {
                calendar.add(Calendar.DAY_OF_MONTH, 1)
                calendar.add(Calendar.MONTH, 1)
                calendar.add(Calendar.DAY_OF_MONTH, -1)
            }
        }
        return result
    }
}
