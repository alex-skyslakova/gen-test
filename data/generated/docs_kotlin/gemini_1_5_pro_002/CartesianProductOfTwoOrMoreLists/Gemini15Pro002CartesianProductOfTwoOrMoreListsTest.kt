import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CartesianProductTest {

    @Test
    fun testBinaryCartesianProduct() {
        assertEquals(listOf(listOf(1, 3), listOf(1, 4), listOf(2, 3), listOf(2, 4)), listOf(1, 2) * listOf(3, 4))
        assertEquals(listOf(listOf(3, 1), listOf(3, 2), listOf(4, 1), listOf(4, 2)), listOf(3, 4) * listOf(1, 2))
        assertEquals(emptyList<List<Any>>(), listOf(1, 2) * listOf<Any>())
        assertEquals(emptyList<List<Any>>(), listOf<Any>() * listOf(1, 2))
        assertEquals(listOf(listOf(1, 2), listOf(1, 'b'), listOf('a', 2), listOf('a', 'b')), listOf(1, 'a') * listOf(2, 'b'))
    }

    @Test
    fun testFlattenList() {
        assertEquals(listOf(1, 2, 3, 4), flattenList(listOf(listOf(1, 2), listOf(3, 4))))
        assertEquals(listOf(1, 2, 3, 4, 5), flattenList(listOf(1, listOf(2, listOf(3, 4)), 5)))
        assertEquals(emptyList<Any>(), flattenList(emptyList<Any>()))
    }


    @Test
    fun testNAryCartesianProduct() {
        val expected1 = listOf(
            listOf(1776, 7, 4, 0), listOf(1776, 7, 4, 1), listOf(1776, 7, 14, 0), listOf(1776, 7, 14, 1),
            listOf(1776, 7, 23, 0), listOf(1776, 7, 23, 1), listOf(1776, 12, 4, 0), listOf(1776, 12, 4, 1),
            listOf(1776, 12, 14, 0), listOf(1776, 12, 14, 1), listOf(1776, 12, 23, 0), listOf(1776, 12, 23, 1),
            listOf(1789, 7, 4, 0), listOf(1789, 7, 4, 1), listOf(1789, 7, 14, 0), listOf(1789, 7, 14, 1),
            listOf(1789, 7, 23, 0), listOf(1789, 7, 23, 1), listOf(1789, 12, 4, 0), listOf(1789, 12, 4, 1),
            listOf(1789, 12, 14, 0), listOf(1789, 12, 14, 1), listOf(1789, 12, 23, 0), listOf(1789, 12, 23, 1)
        )
        assertEquals(expected1, nAryCartesianProduct(listOf(listOf(1776, 1789), listOf(7, 12), listOf(4, 14, 23), listOf(0, 1))))

        val expected2 = listOf(
            listOf(1, 30, 500), listOf(1, 30, 100), listOf(2, 30, 500), listOf(2, 30, 100), listOf(3, 30, 500), listOf(3, 30, 100)
        )
        assertEquals(expected2, nAryCartesianProduct(listOf(listOf(1, 2, 3), listOf(30), listOf(500, 100))))

        assertEquals(emptyList<List<Any>>(), nAryCartesianProduct(listOf(listOf(1, 2, 3), listOf<Int>(), listOf(500, 100))))


        val expected4 = listOf(
            listOf(1, 30, 'a'), listOf(1, 30, 'b'), listOf(2, 30, 'a'), listOf(2, 30, 'b'), listOf(3, 30, 'a'), listOf(3, 30, 'b')
        )
        assertEquals(expected4, nAryCartesianProduct(listOf(listOf(1, 2, 3), listOf(30), listOf('a', 'b'))))

    }



}
