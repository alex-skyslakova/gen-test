import org.junit.jupiter.api.Test;

import java.time.Month;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private static class Birthday {
        private Month month;
        private int day;

        public Birthday(Month month, int day) {
            this.month = month;
            this.day = day;
        }

        public Month getMonth() {
            return month;
        }

        public int getDay() {
            return day;
        }

        @Override
        public String toString() {
            return month + " " + day;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Birthday birthday = (Birthday) obj;
            return day == birthday.day && month == birthday.month;
        }
    }


    @Test
    void testCherylBirthday() {
        List<Birthday> choices = List.of(
                new Birthday(Month.MAY, 15),
                new Birthday(Month.MAY, 16),
                new Birthday(Month.MAY, 19),
                new Birthday(Month.JUNE, 17),
                new Birthday(Month.JUNE, 18),
                new Birthday(Month.JULY, 14),
                new Birthday(Month.JULY, 16),
                new Birthday(Month.AUGUST, 14),
                new Birthday(Month.AUGUST, 15),
                new Birthday(Month.AUGUST, 17)
        );

        Set<Month> uniqueMonths = choices.stream()
                .collect(Collectors.groupingBy(Birthday::getDay))
                .values()
                .stream()
                .filter(g -> g.size() == 1)
                .flatMap(java.util.Collection::stream)
                .map(Birthday::getMonth)
                .collect(Collectors.toSet());

        List<Birthday> f1List = choices.stream()
                .filter(birthday -> !uniqueMonths.contains(birthday.month))
                .collect(Collectors.toList());


        List<Birthday> f2List = f1List.stream()
                .collect(Collectors.groupingBy(Birthday::getDay))
                .values()
                .stream()
                .filter(g -> g.size() == 1)
                .flatMap(java.util.Collection::stream)
                .collect(Collectors.toList());

        List<Birthday> f3List = f2List.stream()
                .collect(Collectors.groupingBy(Birthday::getMonth))
                .values()
                .stream()
                .filter(g -> g.size() == 1)
                .flatMap(java.util.Collection::stream)
                .collect(Collectors.toList());

        assertEquals(1, f3List.size());
        assertEquals(new Birthday(Month.JULY, 16), f3List.get(0));
    }


    @Test
    void testEmptyInput() {
        List<Birthday> choices = List.of();

        Set<Month> uniqueMonths = choices.stream()
                .collect(Collectors.groupingBy(Birthday::getDay))
                .values()
                .stream()
                .filter(g -> g.size() == 1)
                .flatMap(java.util.Collection::stream)
                .map(Birthday::getMonth)
                .collect(Collectors.toSet());

         List<Birthday> f1List = choices.stream()
                .filter(birthday -> !uniqueMonths.contains(birthday.month))
                .collect(Collectors.toList());

        List<Birthday> f2List = f1List.stream()
                .collect(Collectors.groupingBy(Birthday::getDay))
                .values()
                .stream()
                .filter(g -> g.size() == 1)
                .flatMap(java.util.Collection::stream)
                .collect(Collectors.toList());


        List<Birthday> f3List = f2List.stream()
                .collect(Collectors.groupingBy(Birthday::getMonth))
                .values()
                .stream()
                .filter(g -> g.size() == 1)
                .flatMap(java.util.Collection::stream)
                .collect(Collectors.toList());

        assertTrue(f3List.isEmpty());

    }
}
