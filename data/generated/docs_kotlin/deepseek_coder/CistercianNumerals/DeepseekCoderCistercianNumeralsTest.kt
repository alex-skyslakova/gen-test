import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CistercianTest {

    @Test
    fun testZero() {
        val cistercian = Cistercian(0)
        val expected = """
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
        """.trimIndent()
        assertEquals(expected, cistercian.toString().trim())
    }

    @Test
    fun testOne() {
        val cistercian = Cistercian(1)
        val expected = """
            xxxxxx        
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
        """.trimIndent()
        assertEquals(expected, cistercian.toString().trim())
    }

    @Test
    fun testTwenty() {
        val cistercian = Cistercian(20)
        val expected = """
            x             
            x             
            x             
            x             
            xxxxx         
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
        """.trimIndent()
        assertEquals(expected, cistercian.toString().trim())
    }

    @Test
    fun testThreeHundred() {
        val cistercian = Cistercian(300)
        val expected = """
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            xxxxxx        
        """.trimIndent()
        assertEquals(expected, cistercian.toString().trim())
    }

    @Test
    fun testFourThousand() {
        val cistercian = Cistercian(4000)
        val expected = """
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            xxxxx         
        """.trimIndent()
        assertEquals(expected, cistercian.toString().trim())
    }

    @Test
    fun testFiveThousandFiveHundredFiftyFive() {
        val cistercian = Cistercian(5555)
        val expected = """
            xxxxxx        
            x             
            x             
            x             
            xxxxx         
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            xxxxxx        
        """.trimIndent()
        assertEquals(expected, cistercian.toString().trim())
    }

    @Test
    fun testSixThousandSevenHundredEightyNine() {
        val cistercian = Cistercian(6789)
        val expected = """
            xxxxxx        
            x             
            x             
            x             
            xxxxx         
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            xxxxxx        
        """.trimIndent()
        assertEquals(expected, cistercian.toString().trim())
    }

    @Test
    fun testNineThousandNineHundredNinetyNine() {
        val cistercian = Cistercian(9999)
        val expected = """
            xxxxxx        
            x             
            x             
            x             
            xxxxx         
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            x             
            xxxxxx        
        """.trimIndent()
        assertEquals(expected, cistercian.toString().trim())
    }
}
