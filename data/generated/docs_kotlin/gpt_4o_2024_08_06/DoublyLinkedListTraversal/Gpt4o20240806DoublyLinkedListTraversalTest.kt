import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LinkedListTest {

    @Test
    fun testAddFirst() {
        val ll = LinkedList<Int>()
        ll.addFirst(1)
        assertEquals("1", ll.firstToLast())
        assertEquals("1", ll.lastToFirst())
        
        ll.addFirst(0)
        assertEquals("0 -> 1", ll.firstToLast())
        assertEquals("1 -> 0", ll.lastToFirst())
    }

    @Test
    fun testAddLast() {
        val ll = LinkedList<Int>()
        ll.addLast(1)
        assertEquals("1", ll.firstToLast())
        assertEquals("1", ll.lastToFirst())
        
        ll.addLast(2)
        assertEquals("1 -> 2", ll.firstToLast())
        assertEquals("2 -> 1", ll.lastToFirst())
    }

    @Test
    fun testInsert() {
        val ll = LinkedList<Int>()
        ll.insert(null, 1) // equivalent to addFirst
        assertEquals("1", ll.firstToLast())
        assertEquals("1", ll.lastToFirst())
        
        ll.insert(ll.first, 2)
        assertEquals("1 -> 2", ll.firstToLast())
        assertEquals("2 -> 1", ll.lastToFirst())
        
        ll.insert(ll.last, 3) // equivalent to addLast
        assertEquals("1 -> 2 -> 3", ll.firstToLast())
        assertEquals("3 -> 2 -> 1", ll.lastToFirst())
        
        ll.insert(ll.first!!.next, 4)
        assertEquals("1 -> 2 -> 4 -> 3", ll.firstToLast())
        assertEquals("3 -> 4 -> 2 -> 1", ll.lastToFirst())
    }

    @Test
    fun testFirstToLast() {
        val ll = LinkedList<Int>()
        assertEquals("", ll.firstToLast())
        
        ll.addFirst(1)
        ll.addLast(2)
        ll.addLast(3)
        assertEquals("1 -> 2 -> 3", ll.firstToLast())
    }

    @Test
    fun testLastToFirst() {
        val ll = LinkedList<Int>()
        assertEquals("", ll.lastToFirst())
        
        ll.addFirst(1)
        ll.addLast(2)
        ll.addLast(3)
        assertEquals("3 -> 2 -> 1", ll.lastToFirst())
    }
}
