import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.GregorianCalendar;

public class DiscordianDateTest {

    @Test
    public void testRegularDate() {
        assertEquals("Pungenday, day 57 of Confusion in the YOLD 3176", 
            DiscordianDate.discordianDate(new GregorianCalendar(2010, 6, 22)));
    }

    @Test
    public void testLeapYearBeforeStTibsDay() {
        assertEquals("Prickle-Prickle, day 59 of Chaos in the YOLD 3178", 
            DiscordianDate.discordianDate(new GregorianCalendar(2012, 1, 28)));
    }

    @Test
    public void testStTibsDay() {
        assertEquals("St. Tib's Day, in the YOLD 3178", 
            DiscordianDate.discordianDate(new GregorianCalendar(2012, 1, 29)));
    }

    @Test
    public void testLeapYearAfterStTibsDay() {
        assertEquals("Setting Orange, day 60 of Chaos in the YOLD 3178", 
            DiscordianDate.discordianDate(new GregorianCalendar(2012, 2, 1)));
    }

    @Test
    public void testApostleDay() {
        assertEquals("Mungday, in the YOLD 3176", 
            DiscordianDate.discordianDate(new GregorianCalendar(2010, 0, 5)));
    }

    @Test
    public void testHoliday() {
        assertEquals("Discoflux, in the YOLD 3177", 
            DiscordianDate.discordianDate(new GregorianCalendar(2011, 4, 3)));
    }

    @Test
    public void testEndOfSeason() {
        assertEquals("Boomtime, day 73 of Bureaucracy in the YOLD 3181", 
            DiscordianDate.discordianDate(new GregorianCalendar(2015, 9, 19)));
    }
}
