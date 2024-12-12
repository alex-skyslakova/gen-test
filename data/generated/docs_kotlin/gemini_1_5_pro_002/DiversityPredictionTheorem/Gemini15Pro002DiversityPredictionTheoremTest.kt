import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DiversityPredictionTheoremTest {

    @Test
    fun testSquare() {
        assertEquals(25.0, square(5.0))
        assertEquals(0.0, square(0.0))
        assertEquals(4.0, square(-2.0))
    }

    @Test
    fun testAverageSquareDiff() {
        assertEquals(2.0, averageSquareDiff(5.0, doubleArrayOf(4.0, 6.0)))
        assertEquals(0.0, averageSquareDiff(5.0, doubleArrayOf(5.0, 5.0)))
        assertEquals(2.0, averageSquareDiff(5.0, doubleArrayOf(6.0, 4.0)))
        assertEquals(8.0/3.0, averageSquareDiff(2.0, doubleArrayOf(1.0, 2.0, 4.0)))

    }



    @Test
    fun testDiversityTheorem_case1() {
        val expected = "average-error : 2.667\n" +
                       "crowd-error   : 1.000\n" +
                       "diversity     : 1.667\n"
        assertEquals(expected, diversityTheorem(49.0, doubleArrayOf(48.0, 47.0, 51.0)))
    }

    @Test
    fun testDiversityTheorem_case2() {
        val expected = "average-error : 13.667\n" +
                       "crowd-error   : 4.000\n" +
                       "diversity     : 9.667\n"
        assertEquals(expected, diversityTheorem(49.0, doubleArrayOf(48.0, 47.0, 51.0, 42.0)))
    }

    @Test
    fun testDiversityTheorem_emptyPredictions() {
        val expected = "average-error : NaN\n" +
                "crowd-error   : NaN\n" +
                "diversity     : NaN\n"
        assertEquals(expected, diversityTheorem(49.0, doubleArrayOf()))

    }


    @Test
    fun testDiversityTheorem_singlePrediction(){
        val expected = "average-error : 1.000\n" +
                "crowd-error   : 1.000\n" +
                "diversity     : 0.000\n"
        assertEquals(expected, diversityTheorem(49.0, doubleArrayOf(48.0)))
    }


    private fun square(d: Double) = d * d
    
    private fun averageSquareDiff(d: Double, predictions: DoubleArray) =
        predictions.map { square(it - d) }.average()
    
    private fun diversityTheorem(truth: Double, predictions: DoubleArray): String {
        val average = predictions.average()
        val f = "%6.3f"
        return "average-error : ${f.format(averageSquareDiff(truth, predictions))}\n" +
               "crowd-error   : ${f.format(square(truth - average))}\n" +
               "diversity     : ${f.format(averageSquareDiff(average, predictions))}\n"
    }
}
