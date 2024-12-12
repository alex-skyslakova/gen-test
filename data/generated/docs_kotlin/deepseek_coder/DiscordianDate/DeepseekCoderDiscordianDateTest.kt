import org.junit.Test
import java.util.GregorianCalendar
import kotlin.test.assertEquals

class DiscordianDateTest {

    @Test
    fun testDiscordianDate() {
        // Test cases for non-leap years
        assertEquals("Pungenday, day 57 of Confusion in the YOLD 3176", GregorianCalendar(2010, 6, 22).discordianDate())
        assertEquals("Mungday, in the YOLD 3176", GregorianCalendar(2010, 0, 5).discordianDate())
        assertEquals("Discoflux, in the YOLD 3177", GregorianCalendar(2011, 4, 3).discordianDate())
        assertEquals("Boomtime, day 73 of Bureaucracy in the YOLD 3181", GregorianCalendar(2015, 9, 19).discordianDate())

        // Test cases for leap years
        assertEquals("Prickle-Prickle, day 59 of Chaos in the YOLD 3178", GregorianCalendar(2012, 1, 28).discordianDate())
        assertEquals("St. Tib's Day, in the YOLD 3178", GregorianCalendar(2012, 1, 29).discordianDate())
        assertEquals("Setting Orange, day 60 of Chaos in the YOLD 3178", GregorianCalendar(2012, 2, 1).discordianDate())

        // Additional test cases for edge cases and other scenarios
        assertEquals("Sweetmorn, day 1 of Chaos in the YOLD 3176", GregorianCalendar(2010, 0, 1).discordianDate())
        assertEquals("Setting Orange, day 73 of Aftermath in the YOLD 3176", GregorianCalendar(2010, 11, 31).discordianDate())
        assertEquals("Afflux, in the YOLD 3178", GregorianCalendar(2012, 11, 28).discordianDate())
        assertEquals("Maladay, in the YOLD 3178", GregorianCalendar(2012, 11, 20).discordianDate())
    }
}
