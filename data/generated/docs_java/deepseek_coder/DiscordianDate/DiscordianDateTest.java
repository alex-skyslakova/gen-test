import org.junit.jupiter.api.Test;
import java.util.GregorianCalendar;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscordianDateTest {

    @Test
    public void testDiscordianDate_NormalDay() {
        GregorianCalendar date = new GregorianCalendar(2010, 6, 22);
        String expected = "Pungenday, day 57 of Confusion in the YOLD 3176";
        assertEquals(expected, DiscordianDate.discordianDate(date));
    }

    @Test
    public void testDiscordianDate_LeapYearBeforeStTibsDay() {
        GregorianCalendar date = new GregorianCalendar(2012, 1, 28);
        String expected = "Prickle-Prickle, day 59 of Chaos in the YOLD 3178";
        assertEquals(expected, DiscordianDate.discordianDate(date));
    }

    @Test
    public void testDiscordianDate_StTibsDay() {
        GregorianCalendar date = new GregorianCalendar(2012, 1, 29);
        String expected = "St. Tib's Day, in the YOLD 3178";
        assertEquals(expected, DiscordianDate.discordianDate(date));
    }

    @Test
    public void testDiscordianDate_LeapYearAfterStTibsDay() {
        GregorianCalendar date = new GregorianCalendar(2012, 2, 1);
        String expected = "Setting Orange, day 60 of Chaos in the YOLD 3178";
        assertEquals(expected, DiscordianDate.discordianDate(date));
    }

    @Test
    public void testDiscordianDate_ApostleDay() {
        GregorianCalendar date = new GregorianCalendar(2010, 0, 5);
        String expected = "Mungday, in the YOLD 3176";
        assertEquals(expected, DiscordianDate.discordianDate(date));
    }

    @Test
    public void testDiscordianDate_HolidayDay() {
        GregorianCalendar date = new GregorianCalendar(2011, 4, 3);
        String expected = "Discoflux, in the YOLD 3177";
        assertEquals(expected, DiscordianDate.discordianDate(date));
    }

    @Test
    public void testDiscordianDate_LastDayOfSeason() {
        GregorianCalendar date = new GregorianCalendar(2015, 9, 19);
        String expected = "Boomtime, day 73 of Bureaucracy in the YOLD 3181";
        assertEquals(expected, DiscordianDate.discordianDate(date));
    }
}
