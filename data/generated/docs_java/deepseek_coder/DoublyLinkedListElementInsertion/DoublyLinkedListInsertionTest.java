import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

public class DoublyLinkedListInsertionTest {

    @Test
    public void testAddAfter_ElementExists() {
        DoublyLinkedListInsertion<String> list = new DoublyLinkedListInsertion<>();
        list.addFirst("Add First 1");
        list.addFirst("Add First 2");
        list.addFirst("Add First 3");

        list.addAfter("Add First 2", "New Element");

        assertEquals("Add First 1", list.get(3));
        assertEquals("New Element", list.get(2));
        assertEquals("Add First 2", list.get(1));
        assertEquals("Add First 3", list.get(0));
    }

    @Test
    public void testAddAfter_ElementDoesNotExist() {
        DoublyLinkedListInsertion<String> list = new DoublyLinkedListInsertion<>();
        list.addFirst("Add First 1");
        list.addFirst("Add First 2");
        list.addFirst("Add First 3");

        list.addAfter("Non-existent Element", "New Element");

        assertEquals("New Element", list.get(3));
        assertEquals("Add First 1", list.get(2));
        assertEquals("Add First 2", list.get(1));
        assertEquals("Add First 3", list.get(0));
    }

    @Test
    public void testAddAfter_EmptyList() {
        DoublyLinkedListInsertion<String> list = new DoublyLinkedListInsertion<>();

        list.addAfter("Non-existent Element", "New Element");

        assertEquals(1, list.size());
        assertEquals("New Element", list.get(0));
    }

    @Test
    public void testAddAfter_SingleElementList() {
        DoublyLinkedListInsertion<String> list = new DoublyLinkedListInsertion<>();
        list.addFirst("Single Element");

        list.addAfter("Single Element", "New Element");

        assertEquals(2, list.size());
        assertEquals("New Element", list.get(1));
        assertEquals("Single Element", list.get(0));
    }

    @Test
    public void testAddAfter_MultipleElements_InsertAtEnd() {
        DoublyLinkedListInsertion<String> list = new DoublyLinkedListInsertion<>();
        list.addFirst("Add First 1");
        list.addFirst("Add First 2");
        list.addFirst("Add First 3");

        list.addAfter("Add First 1", "New Element");

        assertEquals(4, list.size());
        assertEquals("New Element", list.get(3));
        assertEquals("Add First 1", list.get(2));
        assertEquals("Add First 2", list.get(1));
        assertEquals("Add First 3", list.get(0));
    }

    @Test
    public void testAddAfter_MultipleElements_InsertInMiddle() {
        DoublyLinkedListInsertion<String> list = new DoublyLinkedListInsertion<>();
        list.addFirst("Add First 1");
        list.addFirst("Add First 2");
        list.addFirst("Add First 3");

        list.addAfter("Add First 2", "New Element");

        assertEquals(4, list.size());
        assertEquals("Add First 1", list.get(3));
        assertEquals("New Element", list.get(2));
        assertEquals("Add First 2", list.get(1));
        assertEquals("Add First 3", list.get(0));
    }
}
