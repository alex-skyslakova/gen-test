import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CommatizeTest {

    @Test
    fun testCommatizeBasic() {
        assertEquals("123,456,789.123,456,789", "123456789.123456789".commatize())
        assertEquals(".123,456,789", ".123456789".commatize())
        assertEquals("57,256.1D-4", "57256.1D-4".commatize())
    }

    @Test
    fun testCommatizePeriodAndSeparator() {
        assertEquals("12*34*56*78*9.12*34*56*78*9", "123456789.123456789".commatize(period = 2, sep = "*"))
        assertEquals(".123-456-789", ".123456789".commatize(period = 3, sep = "-"))
        assertEquals("5725__6.1D-4", "57256.1D-4".commatize(period = 4, sep = "__"))
    }

    @Test
    fun testCommatizePi() {
        assertEquals("3.14159 26535 89793 23846 26433 83279 50288 41971 69399 37510 58209 74944 59231", "3.14159265358979323846264338327950288419716939937510582097494459231".commatize(period = 5, sep = " "))
    }
    
    @Test
    fun testCommatizeZimbabweDollars(){
        assertEquals("The author has two Z$100.000.000.000.000 Zimbabwe notes (100 trillion).", "The author has two Z$100000000000000 Zimbabwe notes (100 trillion).".commatize(sep = "."))
    }


    @Test
    fun testCommatizeComplexStrings() {
        assertEquals("-in Aus$+1,411.8millions", "-in Aus$+1411.8millions".commatize())
        assertEquals("===US$1,744,0 millions===", "===US$0017440 millions===".commatize()) // Leading zeros not commatized
        assertEquals("123.e8000 is pretty big.", "123.e8000 is pretty big.".commatize()) // Exponent not commatized
        assertEquals("The land area of the earth is 57,268,900(29% of the surface) square miles.", "The land area of the earth is 57268900(29% of the surface) square miles.".commatize())
        assertEquals("Ain't no numbers in this here words, nohow, no way, Jose.", "Ain't no numbers in this here words, nohow, no way, Jose.".commatize())
        assertEquals("James was never known as 0000000007", "James was never known as 0000000007".commatize()) // Leading zeros not commatized
        assertEquals("Arthur Eddington wrote: I believe there are 15,747,724,136,275,002,577,605,653,961,181,555,468,044,717,914,527,116,709,366,231,425,076,185,631,031,296 protons in the universe.", "Arthur Eddington wrote: I believe there are 15747724136275002577605653961181555468044717914527116709366231425076185631031296 protons in the universe.".commatize())
        assertEquals("   $-140,000±100 millions.", "   $-140000±100 millions.".commatize()) // Leading spaces preserved
        assertEquals("6/9/1946 was a good year for some.", "6/9/1946 was a good year for some.".commatize()) // No numbers to commatize
    }

    @Test
    fun testCommatizeInvalidInput() {
        assertEquals("1234", "1234".commatize(startIndex = 5)) // Start index out of bounds
        assertEquals("1234", "1234".commatize(period = 0)) // Invalid period
        assertEquals("1234", "1234".commatize(sep = "")) // Empty separator
    }

}
