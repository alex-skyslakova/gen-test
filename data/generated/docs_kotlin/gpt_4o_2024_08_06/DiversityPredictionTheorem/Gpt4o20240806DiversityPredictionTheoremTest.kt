import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DiversityPredictionTheoremTest {

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

    @Test
    fun testDiversityTheoremExample1() {
        val truth = 49.0
        val predictions = doubleArrayOf(48.0, 47.0, 51.0)
        val expectedOutput = "average-error :  2.667\n" +
                             "crowd-error   :  1.000\n" +
                             "diversity     :  1.667\n"
        assertEquals(expectedOutput, diversityTheorem(truth, predictions))
    }

    @Test
    fun testDiversityTheoremExample2() {
        val truth = 49.0
        val predictions = doubleArrayOf(48.0, 47.0, 51.0, 42.0)
        val expectedOutput = "average-error : 13.750\n" +
                             "crowd-error   :  6.250\n" +
                             "diversity     :  7.500\n"
        assertEquals(expectedOutput, diversityTheorem(truth, predictions))
    }
}
