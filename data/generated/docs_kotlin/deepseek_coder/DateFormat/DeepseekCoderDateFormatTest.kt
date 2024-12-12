import org.junit.Test
import java.util.GregorianCalendar
import java.util.Locale
import kotlin.test.assertEquals

class DateFormatTest {

    @Test
    fun testDateFormat() {
        // Arrange
        val calendar = GregorianCalendar(2007, 10, 23) // Note: Month is 0-based (10 = November)
        val expectedDate = "2007-11-23"
        val expectedDayOfWeek = "Friday"
        val expectedMonth = "November"
        val expectedDayOfMonth = "23"
        val expectedYear = "2007"

        // Act
        val formattedDate = "%tF".format(calendar)
        val formattedDayOfWeek = "%tA".format(calendar)
        val formattedMonth = "%tB".format(calendar)
        val formattedDayOfMonth = "%te".format(calendar)
        val formattedYear = "%tY".format(calendar)

        // Assert
        assertEquals(expectedDate, formattedDate)
        assertEquals(expectedDayOfWeek, formattedDayOfWeek)
        assertEquals(expectedMonth, formattedMonth)
        assertEquals(expectedDayOfMonth, formattedDayOfMonth)
        assertEquals(expectedYear, formattedYear)
    }

    @Test
    fun testFullDateFormat() {
        // Arrange
        val calendar = GregorianCalendar(2007, 10, 23) // Note: Month is 0-based (10 = November)
        val expectedFullDate = "Friday, November 23, 2007"

        // Act
        val formattedFullDate = "%tA, %1\$tB %1\$te, %1\$tY".format(calendar)

        // Assert
        assertEquals(expectedFullDate, formattedFullDate)
    }
}
