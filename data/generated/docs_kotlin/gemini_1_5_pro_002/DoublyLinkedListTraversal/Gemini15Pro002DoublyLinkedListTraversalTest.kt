import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class LinkedListTest {

    @Test
    fun firstToLast_empty() {
        val ll = LinkedList<Int>()
        assertEquals("", ll.firstToLast())
    }

    @Test
    fun firstToLast_singleElement() {
        val ll = LinkedList<Int>()
        ll.addFirst(1)
        assertEquals("1", ll.firstToLast())
    }

    @Test
    fun firstToLast_multipleElements() {
        val ll = LinkedList<Int>()
        ll.addFirst(1)
        ll.addLast(2)
        ll.addFirst(0)
        assertEquals("0 -> 1 -> 2", ll.firstToLast())
    }


    @Test
    fun lastToFirst_empty() {
        val ll = LinkedList<Int>()
        assertEquals("", ll.lastToFirst())
    }

    @Test
    fun lastToFirst_singleElement() {
        val ll = LinkedList<Int>()
        ll.addLast(1)
        assertEquals("1", ll.lastToFirst())
    }

    @Test
    fun lastToFirst_multipleElements() {
        val ll = LinkedList<Int>()
        ll.addLast(1)
        ll.addFirst(0)
        ll.addLast(2)

        assertEquals("2 -> 1 -> 0", ll.lastToFirst())
    }

    @Test
    fun insert_afterNull() {
        val ll = LinkedList<String>()
        ll.insert(null, "a")
        assertEquals("a", ll.firstToLast())
        assertEquals("a", ll.lastToFirst())

    }

    @Test
    fun insert_afterLast() {
        val ll = LinkedList<String>()
        ll.addLast("a")
        ll.insert(ll.last, "b")
        assertEquals("a -> b", ll.firstToLast())
        assertEquals("b -> a", ll.lastToFirst())
    }


    @Test
    fun insert_middle() {
        val ll = LinkedList<String>()
        ll.addLast("a")
        ll.addLast("c")
        ll.insert(ll.first, "b")

        assertEquals("a -> b -> c", ll.firstToLast())
        assertEquals("c -> b -> a", ll.lastToFirst())
    }

    @Test
    fun addFirst_empty() {
        val ll = LinkedList<Int>()
        ll.addFirst(1)
        assertEquals("1", ll.firstToLast())
        assertEquals("1", ll.lastToFirst())
    }

        @Test
    fun addFirst_multiple() {
        val ll = LinkedList<Int>()
        ll.addFirst(2)
        ll.addFirst(1)
        assertEquals("1 -> 2", ll.firstToLast())
        assertEquals("2 -> 1", ll.lastToFirst())
    }


    @Test
    fun addLast_empty() {
        val ll = LinkedList<Int>()
        ll.addLast(1)
        assertEquals("1", ll.firstToLast())
        assertEquals("1", ll.lastToFirst())
    }

    @Test
    fun addLast_multiple() {
        val ll = LinkedList<Int>()
        ll.addLast(1)
        ll.addLast(2)
        assertEquals("1 -> 2", ll.firstToLast())
        assertEquals("2 -> 1", ll.lastToFirst())
    }

}
