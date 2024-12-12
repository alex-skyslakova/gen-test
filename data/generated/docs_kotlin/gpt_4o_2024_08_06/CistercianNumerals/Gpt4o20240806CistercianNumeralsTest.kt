import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CistercianTest {

    @Test
    fun testCistercianNumeral0() {
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
        assertEquals(expected, cistercian.toString())
    }

    @Test
    fun testCistercianNumeral1() {
        val cistercian = Cistercian(1)
        val expected = """
            x    x
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
        assertEquals(expected, cistercian.toString())
    }

    @Test
    fun testCistercianNumeral20() {
        val cistercian = Cistercian(20)
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
        assertEquals(expected, cistercian.toString())
    }

    @Test
    fun testCistercianNumeral300() {
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
            x    
            
        """.trimIndent()
        assertEquals(expected, cistercian.toString())
    }

    @Test
    fun testCistercianNumeral4000() {
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
            x    
            
        """.trimIndent()
        assertEquals(expected, cistercian.toString())
    }

    @Test
    fun testCistercianNumeral5555() {
        val cistercian = Cistercian(5555)
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
        assertEquals(expected, cistercian.toString())
    }

    @Test
    fun testCistercianNumeral6789() {
        val cistercian = Cistercian(6789)
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
        assertEquals(expected, cistercian.toString())
    }

    @Test
    fun testCistercianNumeral9999() {
        val cistercian = Cistercian(9999)
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
        assertEquals(expected, cistercian.toString())
    }

    @Test
    fun testCistercianNumeralCustom() {
        val cistercian = Cistercian(1234)
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
        assertEquals(expected, cistercian.toString())
    }
}
