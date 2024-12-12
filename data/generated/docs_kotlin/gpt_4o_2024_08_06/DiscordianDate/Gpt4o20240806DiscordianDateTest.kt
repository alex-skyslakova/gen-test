import org.junit.jupiter.api.Test
import java.util.GregorianCalendar
import kotlin.test.assertEquals

class DiscordianDateTest {

    @Test
    fun testRegularDates() {
        assertEquals("Pungenday, day 57 of Confusion in the YOLD 3176", GregorianCalendar(2010, 6, 22).discordianDate())
        assertEquals("Prickle-Prickle, day 59 of Chaos in the YOLD 3178", GregorianCalendar(2012, 1, 28).discordianDate())
        assertEquals("Setting Orange, day 60 of Chaos in the YOLD 3178", GregorianCalendar(2012, 2, 1).discordianDate())
        assertEquals("Boomtime, day 73 of Bureaucracy in the YOLD 3181", GregorianCalendar(2015, 9, 19).discordianDate())
    }

    @Test
    fun testStTibsDay() {
        assertEquals("St. Tib's Day, in the YOLD 3178", GregorianCalendar(2012, 1, 29).discordianDate())
    }

    @Test
    fun testApostleDays() {
        assertEquals("Mungday, in the YOLD 3176", GregorianCalendar(2010, 0, 5).discordianDate())
    }

    @Test
    fun testHolidayDays() {
        assertEquals("Discoflux, in the YOLD 3177", GregorianCalendar(2011, 4, 3).discordianDate())
    }

    @Test
    fun testLeapYearAdjustment() {
        assertEquals("Sweetmorn, day 1 of Chaos in the YOLD 3178", GregorianCalendar(2012, 0, 1).discordianDate())
        assertEquals("Boomtime, day 2 of Chaos in the YOLD 3178", GregorianCalendar(2012, 0, 2).discordianDate())
    }
}
