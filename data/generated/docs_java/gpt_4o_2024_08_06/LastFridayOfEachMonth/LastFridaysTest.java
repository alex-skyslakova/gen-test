import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LastFridaysTest {

    @Test
    public void testLastFridaysOf2012() {
        List<String> expected = List.of(
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
        );
        List<String> actual = LastFridays.getLastFridays(2012);
        assertEquals(expected, actual);
    }

    @Test
    public void testLastFridaysOf2020() {
        List<String> expected = List.of(
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
        );
        List<String> actual = LastFridays.getLastFridays(2020);
        assertEquals(expected, actual);
    }

    @Test
    public void testLastFridaysOf2021() {
        List<String> expected = List.of(
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
        );
        List<String> actual = LastFridays.getLastFridays(2021);
        assertEquals(expected, actual);
    }

    @Test
    public void testLastFridaysOfLeapYear() {
        List<String> expected = List.of(
            "2016-01-29",
            "2016-02-26",
            "2016-03-25",
            "2016-04-29",
            "2016-05-27",
            "2016-06-24",
            "2016-07-29",
            "2016-08-26",
            "2016-09-30",
            "2016-10-28",
            "2016-11-25",
            "2016-12-30"
        );
        List<String> actual = LastFridays.getLastFridays(2016);
        assertEquals(expected, actual);
    }
}
