import com.rosettacode.DoubleLinkedListTraversing;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class DoubleLinkedListTraversingTest {

    @Test
    void testForwardTraversal() {
        LinkedList<String> doubleLinkedList = createList(10);

        List<String> forwardTraversedList = doubleLinkedList.stream().collect(Collectors.toList());

        List<String> expectedForwardList = IntStream.range(1, 11)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());

        assertIterableEquals(expectedForwardList, forwardTraversedList);


    }

    @Test
    void testBackwardTraversal() {
        LinkedList<String> doubleLinkedList = createList(10);


        List<String> backwardTraversedList = doubleLinkedList.descendingIterator().forEachRemaining(x->x).stream().collect(Collectors.toList());

        List<String> expectedBackwardList = IntStream.range(1, 11)
                .mapToObj(String::valueOf)
                .sorted((a, b) -> b.compareTo(a)) // Reverse order for comparison
                .collect(Collectors.toList());

        assertIterableEquals(expectedBackwardList, backwardTraversedList);

    }


    @Test
    void testEmptyList() {
        LinkedList<String> doubleLinkedList = new LinkedList<>();

        List<String> forwardTraversedList = doubleLinkedList.stream().collect(Collectors.toList());
        List<String> backwardTraversedList = doubleLinkedList.descendingIterator().forEachRemaining(x->x).stream().collect(Collectors.toList());


        assertIterableEquals(List.of(), forwardTraversedList);
        assertIterableEquals(List.of(), backwardTraversedList);
    }


    private LinkedList<String> createList(int size) {
        return IntStream.range(1, size + 1)
                .mapToObj(String::valueOf)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
