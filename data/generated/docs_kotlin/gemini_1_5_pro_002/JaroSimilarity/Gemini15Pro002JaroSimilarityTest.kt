import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class JaroTest {

    @Test
    fun testEmptyStrings() {
        assertEquals(1.0, Jaro.distance("", ""))
    }

    @Test
    fun testOneEmptyString() {
        assertEquals(0.0, Jaro.distance("", "abc"))
        assertEquals(0.0, Jaro.distance("abc", ""))
    }

    @Test
    fun testMarthaMarhta() {
        assertEquals(0.9444444444444445, Jaro.distance("MARTHA", "MARHTA"))
    }

    @Test
    fun testDixonDicksonx() {
        assertEquals(0.7666666666666666, Jaro.distance("DIXON", "DICKSONX"))
    }

    @Test
    fun testJellyfishSmellyfish() {
        assertEquals(0.8962962962962964, Jaro.distance("JELLYFISH", "SMELLYFISH"))
    }

    @Test
    fun testNoMatchingCharacters() {
        assertEquals(0.0, Jaro.distance("ABC", "XYZ"))
    }

    @Test
    fun testCompleteMatch() {
        assertEquals(1.0, Jaro.distance("ABC", "ABC"))
    }

    @Test
    fun testPartialMatch() {
        assertEquals(0.6666666666666666, Jaro.distance("AB", "AC"))
    }
    
    @Test
    fun testLongStringsWithSmallMatchDistance() {
        assertEquals(0.0, Jaro.distance("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"))
    }

    @Test
    fun testTranspositionsAtTheBeginning() {
        assertEquals(0.8333333333333334, Jaro.distance("BA", "AB"))
    }
    
    @Test
    fun testTranspositionsAtTheEnd() {
        assertEquals(0.8333333333333334, Jaro.distance("AB", "BA"))
    }

    @Test
    fun testTranspositionsInTheMiddle() {
        assertEquals(0.9444444444444445, Jaro.distance("ABCDE", "ADCBE"))

    }

    @Test
    fun testOneCharacterDifference(){
        assertEquals(0.9166666666666666, Jaro.distance("CRATE", "CRATE1"))
        assertEquals(0.9166666666666666, Jaro.distance("CRATE1", "CRATE"))
    }



}
