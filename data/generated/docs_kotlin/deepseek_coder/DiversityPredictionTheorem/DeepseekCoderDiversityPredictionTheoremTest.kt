import org.junit.Test
import kotlin.test.assertEquals

class DiversityPredictionTheoremTest {

    @Test
    fun testDiversityTheoremCase1() {
        val truth = 49.0
        val predictions = doubleArrayOf(48.0, 47.0, 51.0)
        val result = diversityTheorem(truth, predictions)
        
        val expected = """
            average-error :  3.000
            crowd-error   :  0.111
            diversity     :  2.889
        """.trimIndent()
        
        assertEquals(expected, result)
    }

    @Test
    fun testDiversityTheoremCase2() {
        val truth = 49.0
        val predictions = doubleArrayOf(48.0, 47.0, 51.0, 42.0)
        val result = diversityTheorem(truth, predictions)
        
        val expected = """
            average-error :  9.500
            crowd-error   :  0.040
            diversity     :  9.460
        """.trimIndent()
        
        assertEquals(expected, result)
    }
}
