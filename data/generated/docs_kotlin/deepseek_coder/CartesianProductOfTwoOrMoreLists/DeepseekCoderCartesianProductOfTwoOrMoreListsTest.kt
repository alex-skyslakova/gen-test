import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CartesianProductTest {

    @Test
    fun testCartesianProductOfTwoLists() {
        val result = listOf(1, 2) * listOf(3, 4)
        val expected = listOf(listOf(1, 3), listOf(1, 4), listOf(2, 3), listOf(2, 4))
        assertEquals(expected, result)
    }

    @Test
    fun testCartesianProductOfTwoListsReversed() {
        val result = listOf(3, 4) * listOf(1, 2)
        val expected = listOf(listOf(3, 1), listOf(3, 2), listOf(4, 1), listOf(4, 2))
        assertEquals(expected, result)
    }

    @Test
    fun testCartesianProductWithEmptyList() {
        val result1 = listOf(1, 2) * listOf<Int>()
        val result2 = listOf<Int>() * listOf(1, 2)
        val expected = emptyList<List<Int>>()
        assertEquals(expected, result1)
        assertEquals(expected, result2)
    }

    @Test
    fun testNAryCartesianProduct() {
        val result = nAryCartesianProduct(listOf(listOf(1776, 1789), listOf(7, 12), listOf(4, 14, 23), listOf(0, 1)))
        val expected = listOf(
            listOf(1776, 7, 4, 0), listOf(1776, 7, 4, 1), listOf(1776, 7, 14, 0), listOf(1776, 7, 14, 1),
            listOf(1776, 7, 23, 0), listOf(1776, 7, 23, 1), listOf(1776, 12, 4, 0), listOf(1776, 12, 4, 1),
            listOf(1776, 12, 14, 0), listOf(1776, 12, 14, 1), listOf(1776, 12, 23, 0), listOf(1776, 12, 23, 1),
            listOf(1789, 7, 4, 0), listOf(1789, 7, 4, 1), listOf(1789, 7, 14, 0), listOf(1789, 7, 14, 1),
            listOf(1789, 7, 23, 0), listOf(1789, 7, 23, 1), listOf(1789, 12, 4, 0), listOf(1789, 12, 4, 1),
            listOf(1789, 12, 14, 0), listOf(1789, 12, 14, 1), listOf(1789, 12, 23, 0), listOf(1789, 12, 23, 1)
        )
        assertEquals(expected, result)
    }

    @Test
    fun testNAryCartesianProductWithEmptyList() {
        val result = nAryCartesianProduct(listOf(listOf(1, 2, 3), listOf<Int>(), listOf(500, 100)))
        val expected = emptyList<List<Int>>()
        assertEquals(expected, result)
    }

    @Test
    fun testNAryCartesianProductWithSingleList() {
        assertFailsWith<IllegalArgumentException> {
            nAryCartesianProduct(listOf(listOf(1, 2, 3)))
        }
    }

    @Test
    fun testNAryCartesianProductWithTwoLists() {
        val result = nAryCartesianProduct(listOf(listOf(1, 2, 3), listOf(30)))
        val expected = listOf(listOf(1, 30), listOf(2, 30), listOf(3, 30))
        assertEquals(expected, result)
    }

    @Test
    fun testNAryCartesianProductWithMixedTypes() {
        val result = nAryCartesianProduct(listOf(listOf(1, 2, 3), listOf(30), listOf('a', 'b')))
        val expected = listOf(
            listOf(1, 30, 'a'), listOf(1, 30, 'b'), listOf(2, 30, 'a'), listOf(2, 30, 'b'),
            listOf(3, 30, 'a'), listOf(3, 30, 'b')
        )
        assertEquals(expected, result)
    }
}
