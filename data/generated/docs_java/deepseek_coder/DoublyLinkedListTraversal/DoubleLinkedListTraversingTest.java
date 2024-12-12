package com.rosettacode;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoubleLinkedListTraversingTest {

    @Test
    public void testForwardTraversal() {
        // Arrange
        LinkedList<String> doubleLinkedList = IntStream.range(1, 10)
                .mapToObj(String::valueOf)
                .collect(Collectors.toCollection(LinkedList::new));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Act
        doubleLinkedList.iterator().forEachRemaining(System.out::print);

        // Assert
        assertEquals("123456789", outContent.toString());
    }

    @Test
    public void testBackwardTraversal() {
        // Arrange
        LinkedList<String> doubleLinkedList = IntStream.range(1, 10)
                .mapToObj(String::valueOf)
                .collect(Collectors.toCollection(LinkedList::new));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Act
        doubleLinkedList.descendingIterator().forEachRemaining(System.out::print);

        // Assert
        assertEquals("987654321", outContent.toString());
    }

    @Test
    public void testMainMethod() {
        // Arrange
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Act
        DoubleLinkedListTraversing.main(new String[]{});

        // Assert
        String expectedOutput = "123456789\n987654321\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
