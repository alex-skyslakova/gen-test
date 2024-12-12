import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CreateATwoDimensionalArrayAtRuntimeTest {

    @Test
    fun testArrayCreationAndAccess() {
        val dim = arrayOf(10, 15)
        val array = Array(dim[0], { IntArray(dim[1]) } )

        // fill (using same logic as original code)
        array.forEachIndexed { i, it ->
            it.indices.forEach { j ->
                it[j] = 1 + i + j
            }
        }

        // Test access - Check a few elements
        assertEquals(1, array[0][0])
        assertEquals(11, array[0][10])
        assertEquals(16, array[5][10])
        assertEquals(26, array[9][15-1]) // last element

         // Test dimensions
        assertEquals(dim[0], array.size)
        assertEquals(dim[1], array[0].size)
    }


    @Test
    fun testEmptyArray() {
        val dim = arrayOf(0, 0)
        val array = Array(dim[0], { IntArray(dim[1]) } )

        assertEquals(0, array.size)

    }


    @Test
    fun testSingleRowArray() {
        val dim = arrayOf(1, 5)
        val array = Array(dim[0], { IntArray(dim[1]) } )
         array.forEachIndexed { i, it ->
            it.indices.forEach { j ->
                it[j] = 1 + i + j
            }
        }

        assertEquals(1, array[0][0])
        assertEquals(5, array[0][4])
        assertEquals(dim[0], array.size)
        assertEquals(dim[1], array[0].size)
    }



    @Test
    fun testSingleColumnArray() {
        val dim = arrayOf(5, 1)
        val array = Array(dim[0], { IntArray(dim[1]) } )
         array.forEachIndexed { i, it ->
            it.indices.forEach { j ->
                it[j] = 1 + i + j
            }
        }
        assertEquals(1, array[0][0])
        assertEquals(6, array[4][0]) // last element

        assertEquals(dim[0], array.size)
        assertEquals(dim[1], array[0].size)


    }

}
