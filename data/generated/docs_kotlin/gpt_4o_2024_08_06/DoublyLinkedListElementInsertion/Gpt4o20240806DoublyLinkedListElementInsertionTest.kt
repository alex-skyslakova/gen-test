import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DoublyLinkedListTest {

    @Test
    fun testInsertMiddle() {
        val a = Node(1)
        val b = Node(3, a)
        a.next = b

        val c = Node(2)
        insert(after = a, new = c)

        // Check the forward links
        assertEquals(1, a.data)
        assertEquals(2, a.next?.data)
        assertEquals(3, a.next?.next?.data)

        // Check the backward links
        assertEquals(2, b.prev?.data)
        assertEquals(1, c.prev?.data)
    }

    @Test
    fun testInsertAtEnd() {
        val a = Node(1)
        val b = Node(2, a)
        a.next = b

        val c = Node(3)
        insert(after = b, new = c)

        // Check the forward links
        assertEquals(1, a.data)
        assertEquals(2, a.next?.data)
        assertEquals(3, a.next?.next?.data)

        // Check the backward links
        assertEquals(2, c.prev?.data)
        assertEquals(1, b.prev?.data)
    }

    @Test
    fun testInsertAtBeginning() {
        val a = Node(2)
        val b = Node(3, a)
        a.next = b

        val c = Node(1)
        insert(after = c, new = a)

        // Check the forward links
        assertEquals(1, c.data)
        assertEquals(2, c.next?.data)
        assertEquals(3, c.next?.next?.data)

        // Check the backward links
        assertEquals(1, a.prev?.data)
        assertEquals(2, b.prev?.data)
    }

    @Test
    fun testToString() {
        val a = Node(1)
        val b = Node(2, a)
        a.next = b
        val c = Node(3, b)
        b.next = c

        assertEquals("1 -> 2 -> 3", a.toString())
    }
}
