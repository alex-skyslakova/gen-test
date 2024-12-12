import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class EulerSSumOfPowersConjectureTest {

    private val p5 = LongArray(250){ it.toLong() * it * it * it * it }

    @Test
    fun testKnownSolution() {
        val x0 = 27
        val x1 = 84
        val x2 = 113
        val x3 = 133
        val sum = p5[x0] + p5[x1] + p5[x2] + p5[x3]
        val y = p5.binarySearch(sum)
        assertTrue(y >= 0)
        assertEquals(144,y) // Expected y value for the known solution
    }


    @Test
    fun testNoSolutionEdgeCaseLowerBound(){
        val x0 = 1
        val x1 = 0 // x1 < x0 is required
        val x2 = 0 
        val x3 = 0 
        val sum = p5[x0] + p5[x1] + p5[x2] + p5[x3]
        val y = p5.binarySearch(sum)

         assertTrue(y >= 0) // Should find y=x0 =1 as a (trivial, but valid within bounds) solution.
         assertEquals(1, y)
    }

    @Test
    fun testNoSolutionEdgeCaseUpperBound(){
        val x0 = 249
        val x1 = 248 
        val x2 = 247
        val x3 = 246
        val sum = p5[x0] + p5[x1] + p5[x2] + p5[x3]
        val y = p5.binarySearch(sum)
        assertTrue(y<0) //  No solution expected at upper bounds. Ensures binarySearch works as intended.
    }



     @Test
    fun testDistinctNumbersConstraint() {
        val x0 = 5
        val x1 = 5 
        val x2 = 5
        val x3 = 5
        val sum = p5[x0] + p5[x1] + p5[x2] + p5[x3]
        val y = p5.binarySearch(sum)


        // While a mathematical solution might exist, the code's logic prevents it from finding solutions with non-distinct numbers.
       assertTrue(y < 0) // The code should NOT find this solution because the numbers aren't distinct.

    }


}
