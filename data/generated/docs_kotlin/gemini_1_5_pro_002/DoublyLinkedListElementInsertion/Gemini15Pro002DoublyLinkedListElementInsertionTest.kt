import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DoublyLinkedListInsertionTest {

    @Test
    fun testInsertBetweenTwoNodes() {
        val a = Node(1)
        val b = Node(3)
        a.next = b
        b.prev = a
        val c = Node(2)
        insert(a, c)

        assertEquals(1, a.data)
        assertEquals(2, a.next!!.data)
        assertEquals(3, a.next!!.next!!.data)

        assertEquals(a, c.prev)
        assertEquals(b, c.next)

        assertEquals(c, b.prev)
        assertNull(b.next)


    }

    @Test
    fun testInsertAtEnd() {
        val a = Node(1)
        val c = Node(2)
        insert(a, c)

        assertEquals(1, a.data)
        assertEquals(2, a.next!!.data)

        assertEquals(a, c.prev)
        assertNull(c.next)
        
    }


    @Test
    fun testInsertEmptyList() {
        val a = Node(1)
        val b = Node(2)

        assertNull(a.next)
        assertNull(a.prev)

        insert(a,b)

        assertEquals(b, a.next)
        assertEquals(a, b.prev)
        assertNull(b.next)


    }


}
