import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListInsertionTest {

    @Test
    void testAddFirst() {
        DoublyLinkedListInsertion<String> list = new DoublyLinkedListInsertion<>();
        list.addFirst("Element 1");
        list.addFirst("Element 2");
        list.addFirst("Element 3");

        assertEquals(3, list.size());
        assertEquals("Element 3", list.get(0));
        assertEquals("Element 2", list.get(1));
        assertEquals("Element 1", list.get(2));
    }

    @Test
    void testAddAfterExistingElement() {
        DoublyLinkedListInsertion<String> list = new DoublyLinkedListInsertion<>();
        list.addFirst("Element 1");
        list.addFirst("Element 2");
        list.addFirst("Element 3");

        list.addAfter("Element 2", "New Element");

        assertEquals(4, list.size());
        assertEquals("Element 3", list.get(0));
        assertEquals("Element 2", list.get(1));
        assertEquals("New Element", list.get(2));
        assertEquals("Element 1", list.get(3));
    }

    @Test
    void testAddAfterNonExistingElement() {
        DoublyLinkedListInsertion<String> list = new DoublyLinkedListInsertion<>();
        list.addFirst("Element 1");
        list.addFirst("Element 2");
        list.addFirst("Element 3");

        list.addAfter("Non-Existing", "New Element");

        assertEquals(4, list.size());
        assertEquals("Element 3", list.get(0));
        assertEquals("Element 2", list.get(1));
        assertEquals("Element 1", list.get(2));
        assertEquals("New Element", list.get(3));
    }

    @Test
    void testAddAfterEmptyList() {
        DoublyLinkedListInsertion<String> list = new DoublyLinkedListInsertion<>();

        list.addAfter("Non-Existing", "New Element");

        assertEquals(1, list.size());
        assertEquals("New Element", list.get(0));
    }

    @Test
    void testAddAfterNullElement() {
        DoublyLinkedListInsertion<String> list = new DoublyLinkedListInsertion<>();
        list.addFirst("Element 1");

        list.addAfter(null, "New Element");

        assertEquals(2, list.size());
        assertEquals("Element 1", list.get(0));
        assertEquals("New Element", list.get(1));
    }
}
