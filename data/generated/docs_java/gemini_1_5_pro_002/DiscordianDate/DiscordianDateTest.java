import org.junit.jupiter.api.Test;
import java.util.GregorianCalendar;
import static org.junit.jupiter.api.Assertions.*;

public class DiscordianDateTest {

    @Test
    void testRegularDay() {
        assertEquals("Pungenday, day 57 of Confusion in the YOLD 3176", DiscordianDate.discordianDate(new GregorianCalendar(2010, 5, 22)));
    }

    @Test
    void testLeapYearRegularDayBeforeLeapDay() {
        assertEquals("Prickle-Prickle, day 59 of Chaos in the YOLD 3178", DiscordianDate.discordianDate(new GregorianCalendar(2012, 0, 28)));
    }

    @Test
    void testLeapYearStTibsDay() {
        assertEquals("St. Tib's Day, in the YOLD 3178", DiscordianDate.discordianDate(new GregorianCalendar(2012, 1, 29)));
    }

    @Test
    void testLeapYearRegularDayAfterLeapDay() {
        assertEquals("Setting Orange, day 60 of Chaos in the YOLD 3178", DiscordianDate.discordianDate(new GregorianCalendar(2012, 1, 1)));
    }

    @Test
    void testApostleDay() {
        assertEquals("Mungday, in the YOLD 3176", DiscordianDate.discordianDate(new GregorianCalendar(2010, 0, 5)));
    }

    @Test
    void testHoliday() {
        assertEquals("Discoflux, in the YOLD 3177", DiscordianDate.discordianDate(new GregorianCalendar(2011, 4, 3)));
    }

    @Test
    void testLastDayOfSeason() {
        assertEquals("Boomtime, day 73 of Bureaucracy in the YOLD 3181", DiscordianDate.discordianDate(new GregorianCalendar(2015, 8, 19)));
    }

    @Test
    void testFirstDayOfYear() {
        assertEquals("Sweetmorn, day 1 of Chaos in the YOLD 3184", DiscordianDate.discordianDate(new GregorianCalendar(2018, 0, 1)));

    }

    @Test
    void testLastDayOfYearNonLeap() {
        assertEquals("Setting Orange, day 73 of The Aftermath in the YOLD 3184", DiscordianDate.discordianDate(new GregorianCalendar(2018, 11, 31)));
    }

     @Test
    void testLastDayOfYearLeap() {
        assertEquals("Setting Orange, day 73 of The Aftermath in the YOLD 3180", DiscordianDate.discordianDate(new GregorianCalendar(2016, 11, 31)));
    }



}
