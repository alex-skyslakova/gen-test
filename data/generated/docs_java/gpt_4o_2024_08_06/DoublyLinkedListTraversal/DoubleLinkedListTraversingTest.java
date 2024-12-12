package com.rosettacode;

import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoubleLinkedListTraversingTest {

    @Test
    public void testForwardTraversal() {
        LinkedList<String> doubleLinkedList = IntStream.range(1, 10)
                .mapToObj(String::valueOf)
                .collect(Collectors.toCollection(LinkedList::new));

        StringBuilder result = new StringBuilder();
        doubleLinkedList.iterator().forEachRemaining(result::append);

        assertEquals("123456789", result.toString());
    }

    @Test
    public void testBackwardTraversal() {
        LinkedList<String> doubleLinkedList = IntStream.range(1, 10)
                .mapToObj(String::valueOf)
                .collect(Collectors.toCollection(LinkedList::new));

        StringBuilder result = new StringBuilder();
        doubleLinkedList.descendingIterator().forEachRemaining(result::append);

        assertEquals("987654321", result.toString());
    }
}
