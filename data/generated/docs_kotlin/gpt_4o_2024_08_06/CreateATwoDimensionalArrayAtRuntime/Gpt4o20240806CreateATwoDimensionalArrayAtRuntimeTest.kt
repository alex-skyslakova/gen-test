import org.junit.Test
import kotlin.test.assertEquals

class CreateATwoDimensionalArrayAtRuntimeTest {

    @Test
    fun testArrayDimensions() {
        val dim = arrayOf(10, 15)
        val array = Array(dim[0], { IntArray(dim[1]) })

        assertEquals(10, array.size, "The number of rows should be 10")
        assertEquals(15, array[0].size, "The number of columns should be 15")
    }

    @Test
    fun testArrayValues() {
        val dim = arrayOf(10, 15)
        val array = Array(dim[0], { IntArray(dim[1]) })

        // fill
        array.forEachIndexed { i, it ->
            it.indices.forEach { j ->
                it[j] = 1 + i + j
            }
        }

        // Test some known values
        assertEquals(1, array[0][0], "The value at (0,0) should be 1")
        assertEquals(2, array[0][1], "The value at (0,1) should be 2")
        assertEquals(3, array[1][1], "The value at (1,1) should be 3")
        assertEquals(25, array[9][14], "The value at (9,14) should be 25")
    }

    @Test
    fun testArrayDestruction() {
        var array: Array<IntArray>? = Array(10, { IntArray(15) })

        // Simulate destruction
        array = null

        assertEquals(null, array, "The array should be null after destruction")
    }
}
