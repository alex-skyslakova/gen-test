import org.junit.jupiter.api.Test;
import java.time.Month;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testInitialChoices() {
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
        assertEquals(10, choices.size());
    }

    @Test
    public void testFirstElimination() {
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

        // The month cannot have a unique day
        List<Main.Birthday> f1List = choices.stream()
            .filter(birthday -> !List.of(Month.MAY, Month.JUNE).contains(birthday.getMonth()))
            .collect(Collectors.toList());

        assertEquals(5, f1List.size());
    }

    @Test
    public void testSecondElimination() {
        List<Main.Birthday> f1List = List.of(
            new Main.Birthday(Month.JULY, 14),
            new Main.Birthday(Month.JULY, 16),
            new Main.Birthday(Month.AUGUST, 14),
            new Main.Birthday(Month.AUGUST, 15),
            new Main.Birthday(Month.AUGUST, 17)
        );

        // Bernard now knows the answer, so the day must be unique within the remaining choices
        List<Main.Birthday> f2List = f1List.stream()
            .collect(Collectors.groupingBy(Main.Birthday::getDay))
            .values()
            .stream()
            .filter(g -> g.size() == 1)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        assertEquals(3, f2List.size());
    }

    @Test
    public void testThirdElimination() {
        List<Main.Birthday> f2List = List.of(
            new Main.Birthday(Month.JULY, 16),
            new Main.Birthday(Month.AUGUST, 15),
            new Main.Birthday(Month.AUGUST, 17)
        );

        // Albert knows the answer too, so the month must be unique within the remaining choices
        List<Main.Birthday> f3List = f2List.stream()
            .collect(Collectors.groupingBy(Main.Birthday::getMonth))
            .values()
            .stream()
            .filter(g -> g.size() == 1)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        assertEquals(1, f3List.size());
        assertEquals(new Main.Birthday(Month.JULY, 16), f3List.get(0));
    }

    @Test
    public void testFinalResult() {
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

        // The month cannot have a unique day
        Set<Month> uniqueMonths = choices.stream()
            .collect(Collectors.groupingBy(Main.Birthday::getDay))
            .values()
            .stream()
            .filter(g -> g.size() == 1)
            .flatMap(Collection::stream)
            .map(Main.Birthday::getMonth)
            .collect(Collectors.toSet());
        List<Main.Birthday> f1List = choices.stream()
            .filter(birthday -> !uniqueMonths.contains(birthday.month))
            .collect(Collectors.toList());

        // Bernard now knows the answer, so the day must be unique within the remaining choices
        List<Main.Birthday> f2List = f1List.stream()
            .collect(Collectors.groupingBy(Main.Birthday::getDay))
            .values()
            .stream()
            .filter(g -> g.size() == 1)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        // Albert knows the answer too, so the month must be unique within the remaining choices
        List<Main.Birthday> f3List = f2List.stream()
            .collect(Collectors.groupingBy(Main.Birthday::getMonth))
            .values()
            .stream()
            .filter(g -> g.size() == 1)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        assertEquals(1, f3List.size());
        assertEquals(new Main.Birthday(Month.JULY, 16), f3List.get(0));
    }
}
