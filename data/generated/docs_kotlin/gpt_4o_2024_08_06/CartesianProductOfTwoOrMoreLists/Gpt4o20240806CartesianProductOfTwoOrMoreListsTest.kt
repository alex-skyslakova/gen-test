import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CartesianProductTest {

    @Test
    fun `test Cartesian product of two lists`() {
        val list1 = listOf(1, 2)
        val list2 = listOf(3, 4)
        val expected = listOf(
            listOf(1, 3),
            listOf(1, 4),
            listOf(2, 3),
            listOf(2, 4)
        )
        assertEquals(expected, list1 * list2)
    }

    @Test
    fun `test Cartesian product of two lists reversed`() {
        val list1 = listOf(3, 4)
        val list2 = listOf(1, 2)
        val expected = listOf(
            listOf(3, 1),
            listOf(3, 2),
            listOf(4, 1),
            listOf(4, 2)
        )
        assertEquals(expected, list1 * list2)
    }

    @Test
    fun `test Cartesian product with an empty list`() {
        val list1 = listOf(1, 2)
        val list2 = listOf<Any>()
        val expected = listOf<List<Any>>()
        assertEquals(expected, list1 * list2)
    }

    @Test
    fun `test Cartesian product with another empty list`() {
        val list1 = listOf<Any>()
        val list2 = listOf(1, 2)
        val expected = listOf<List<Any>>()
        assertEquals(expected, list1 * list2)
    }

    @Test
    fun `test n-ary Cartesian product`() {
        val lists = listOf(
            listOf(1776, 1789),
            listOf(7, 12),
            listOf(4, 14, 23),
            listOf(0, 1)
        )
        val expected = listOf(
            listOf(1776, 7, 4, 0),
            listOf(1776, 7, 4, 1),
            listOf(1776, 7, 14, 0),
            listOf(1776, 7, 14, 1),
            listOf(1776, 7, 23, 0),
            listOf(1776, 7, 23, 1),
            listOf(1776, 12, 4, 0),
            listOf(1776, 12, 4, 1),
            listOf(1776, 12, 14, 0),
            listOf(1776, 12, 14, 1),
            listOf(1776, 12, 23, 0),
            listOf(1776, 12, 23, 1),
            listOf(1789, 7, 4, 0),
            listOf(1789, 7, 4, 1),
            listOf(1789, 7, 14, 0),
            listOf(1789, 7, 14, 1),
            listOf(1789, 7, 23, 0),
            listOf(1789, 7, 23, 1),
            listOf(1789, 12, 4, 0),
            listOf(1789, 12, 4, 1),
            listOf(1789, 12, 14, 0),
            listOf(1789, 12, 14, 1),
            listOf(1789, 12, 23, 0),
            listOf(1789, 12, 23, 1)
        )
        assertEquals(expected, nAryCartesianProduct(lists))
    }

    @Test
    fun `test n-ary Cartesian product with single element list`() {
        val lists = listOf(
            listOf(1, 2, 3),
            listOf(30),
            listOf(500, 100)
        )
        val expected = listOf(
            listOf(1, 30, 500),
            listOf(1, 30, 100),
            listOf(2, 30, 500),
            listOf(2, 30, 100),
            listOf(3, 30, 500),
            listOf(3, 30, 100)
        )
        assertEquals(expected, nAryCartesianProduct(lists))
    }

    @Test
    fun `test n-ary Cartesian product with an empty list in between`() {
        val lists = listOf(
            listOf(1, 2, 3),
            listOf<Any>(),
            listOf(500, 100)
        )
        val expected = listOf<List<Any>>()
        assertEquals(expected, nAryCartesianProduct(lists))
    }
}
