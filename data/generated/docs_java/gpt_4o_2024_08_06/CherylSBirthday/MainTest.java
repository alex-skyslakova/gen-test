import org.junit.jupiter.api.Test;
import java.time.Month;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    public void testCherylsBirthday() {
        List<Main.Birthday> choices = List.of(
            new Main.Birthday(Month.MAY, 15),
            new Main.Birthday(Month.MAY, 16),
            new Main.Birthday(Month.MAY, 19),
            new Main.Birthday(Month.JUNE, 17),
            new Main.Birthday(Month.JUNE, 18),
            new Main.Birthday(Month.JULY, 14),
            new Main.Birthday(Month.JULY, 16),
            new Main.Birthday(Month.AUGUST, 14),
            new Main.Birthday(Month.AUGUST, 15),
            new Main.Birthday(Month.AUGUST, 17)
        );

        // Step 1: Filter out months with unique days
        List<Main.Birthday> f1List = choices.stream()
            .filter(birthday -> !(birthday.getMonth() == Month.MAY || birthday.getMonth() == Month.JUNE))
            .toList();

        assertEquals(6, f1List.size());

        // Step 2: Filter out non-unique days
        List<Main.Birthday> f2List = f1List.stream()
            .filter(birthday -> birthday.getDay() == 16 || birthday.getDay() == 15 || birthday.getDay() == 17)
            .toList();

        assertEquals(3, f2List.size());

        // Step 3: Filter out non-unique months
        List<Main.Birthday> f3List = f2List.stream()
            .filter(birthday -> birthday.getMonth() == Month.JULY)
            .toList();

        assertEquals(1, f3List.size());

        // Final assertion
        assertEquals("JULY 16", f3List.get(0).toString());
    }
}
