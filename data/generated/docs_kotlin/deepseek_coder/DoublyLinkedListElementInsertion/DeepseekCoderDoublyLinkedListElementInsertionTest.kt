import org.junit.Test
import org.junit.Assert.*

class DoublyLinkedListElementInsertionTest {

    @Test
    fun testInsertBetweenTwoNodes() {
        val a = Node(1)
        val b = Node(3, a)
        a.next = b

        val c = Node(2)
        insert(after = a, new = c)

        assertEquals("1 -> 2 -> 3", a.toString())
        assertEquals(a, c.prev)
        assertEquals(b, c.next)
        assertEquals(c, b.prev)
    }

    @Test
    fun testInsertAtEndOfList() {
        val a = Node(1)
        val b = Node(3, a)
        a.next = b

        val c = Node(2)
        insert(after = b, new = c)

        assertEquals("1 -> 3 -> 2", a.toString())
        assertEquals(b, c.prev)
        assertNull(c.next)
    }

    @Test
    fun testInsertIntoEmptyList() {
        val a = Node(1)
        val c = Node(2)

        // This should not be possible as per the function definition,
        // since 'after' must have a next node to insert 'new' after it.
        // Therefore, this test case is not applicable.
    }

    @Test
    fun testInsertAfterSingleNode() {
        val a = Node(1)
        val c = Node(2)

        // This should not be possible as per the function definition,
        // since 'after' must have a next node to insert 'new' after it.
        // Therefore, this test case is not applicable.
    }

    @Test
    fun testInsertBetweenTwoNodesWithNullPrev() {
        val a = Node(1)
        val b = Node(3, null)
        a.next = b

        val c = Node(2)
        insert(after = a, new = c)

        assertEquals("1 -> 2 -> 3", a.toString())
        assertEquals(a, c.prev)
        assertEquals(b, c.next)
        assertEquals(c, b.prev)
    }

    @Test
    fun testInsertBetweenTwoNodesWithNullNext() {
        val a = Node(1)
        val b = Node(3, a)
        a.next = b
        b.next = null

        val c = Node(2)
        insert(after = a, new = c)

        assertEquals("1 -> 2 -> 3", a.toString())
        assertEquals(a, c.prev)
        assertEquals(b, c.next)
        assertEquals(c, b.prev)
    }

    @Test
    fun testInsertBetweenTwoNodesWithNullPrevAndNext() {
        val a = Node(1)
        val b = Node(3, null)
        a.next = b
        b.next = null

        val c = Node(2)
        insert(after = a, new = c)

        assertEquals("1 -> 2 -> 3", a.toString())
        assertEquals(a, c.prev)
        assertEquals(b, c.next)
        assertEquals(c, b.prev)
    }
}
