import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class DoublyLinkedListInsertionTest {

    @Test
    public void testAddAfter_ExistingElement() {
        DoublyLinkedListInsertion<String> list = new DoublyLinkedListInsertion<>();
        list.add("A");
        list.add("B");
        list.add("C");

        list.addAfter("B", "New");

        assertEquals(4, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("New", list.get(2));
        assertEquals("C", list.get(3));
    }

    @Test
    public void testAddAfter_NonExistingElement() {
        DoublyLinkedListInsertion<String> list = new DoublyLinkedListInsertion<>();
        list.add("A");
        list.add("B");
        list.add("C");

        list.addAfter("D", "New");

        assertEquals(4, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
        assertEquals("New", list.get(3));
    }


    @Test
    public void testAddAfter_EmptyList() {
        DoublyLinkedListInsertion<String> list = new DoublyLinkedListInsertion<>();

        list.addAfter("D", "New");

        assertEquals(1, list.size());
        assertEquals("New", list.get(0));
    }

    @Test
    public void testAddFirst() {
        DoublyLinkedListInsertion<String> list = new DoublyLinkedListInsertion<>();
        list.addFirst("First 1");
        list.addFirst("First 2");

        assertEquals(2, list.size());
        assertEquals("First 2", list.get(0));
        assertEquals("First 1", list.get(1));
    }

    @Test
    public void testAddLast() { // While not explicitly part of addAfter, it's used internally and worth testing
        DoublyLinkedListInsertion<String> list = new DoublyLinkedListInsertion<>();
        list.addLast("Last 1");
        list.addLast("Last 2");

        assertEquals(2, list.size());
        assertEquals("Last 1", list.get(0));
        assertEquals("Last 2", list.get(1));

    }

        @Test
    public void testAddAfter_NullAfter() {
            DoublyLinkedListInsertion<String> list = new DoublyLinkedListInsertion<>();
            list.add("A");

            list.addAfter(null, "New");

            assertEquals(2, list.size());
            assertEquals("A", list.get(0));
            assertEquals("New", list.get(1));

    }

    @Test
    public void testAddAfter_NullElement() {
        DoublyLinkedListInsertion<String> list = new DoublyLinkedListInsertion<>();
        list.add("A");

        list.addAfter("A", null);

        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertNull(list.get(1));
    }


}
