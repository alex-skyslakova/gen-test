import org.junit.Test
import org.junit.Assert.*

class LinkedListTest {

    @Test
    fun testAddFirst() {
        val ll = LinkedList<Int>()
        ll.addFirst(1)
        assertEquals("1", ll.firstToLast())
        ll.addFirst(0)
        assertEquals("0 -> 1", ll.firstToLast())
    }

    @Test
    fun testAddLast() {
        val ll = LinkedList<Int>()
        ll.addLast(1)
        assertEquals("1", ll.firstToLast())
        ll.addLast(2)
        assertEquals("1 -> 2", ll.firstToLast())
    }

    @Test
    fun testInsert() {
        val ll = LinkedList<Int>()
        ll.addFirst(1)
        ll.addLast(4)
        ll.insert(ll.first, 2)
        assertEquals("1 -> 2 -> 4", ll.firstToLast())
        ll.insert(ll.last!!.prev, 3)
        assertEquals("1 -> 2 -> 3 -> 4", ll.firstToLast())
    }

    @Test
    fun testFirstToLast() {
        val ll = LinkedList<Int>()
        assertEquals("", ll.firstToLast())
        ll.addFirst(1)
        assertEquals("1", ll.firstToLast())
        ll.addLast(2)
        assertEquals("1 -> 2", ll.firstToLast())
    }

    @Test
    fun testLastToFirst() {
        val ll = LinkedList<Int>()
        assertEquals("", ll.lastToFirst())
        ll.addFirst(1)
        assertEquals("1", ll.lastToFirst())
        ll.addLast(2)
        assertEquals("2 -> 1", ll.lastToFirst())
    }

    @Test
    fun testMixedOperations() {
        val ll = LinkedList<Int>()
        ll.addFirst(1)
        ll.addLast(4)
        ll.insert(ll.first, 2)
        ll.insert(ll.last!!.prev, 3)
        assertEquals("1 -> 2 -> 3 -> 4", ll.firstToLast())
        assertEquals("4 -> 3 -> 2 -> 1", ll.lastToFirst())
    }

    @Test
    fun testEmptyList() {
        val ll = LinkedList<Int>()
        assertNull(ll.first)
        assertNull(ll.last)
        assertEquals("", ll.firstToLast())
        assertEquals("", ll.lastToFirst())
    }

    @Test
    fun testSingleElementList() {
        val ll = LinkedList<Int>()
        ll.addFirst(1)
        assertNotNull(ll.first)
        assertNotNull(ll.last)
        assertEquals("1", ll.firstToLast())
        assertEquals("1", ll.lastToFirst())
    }

    @Test
    fun testInsertAfterNull() {
        val ll = LinkedList<Int>()
        ll.insert(null, 1)
        assertEquals("1", ll.firstToLast())
    }

    @Test
    fun testInsertAfterLast() {
        val ll = LinkedList<Int>()
        ll.addFirst(1)
        ll.addLast(2)
        ll.insert(ll.last, 3)
        assertEquals("1 -> 2 -> 3", ll.firstToLast())
    }
}
