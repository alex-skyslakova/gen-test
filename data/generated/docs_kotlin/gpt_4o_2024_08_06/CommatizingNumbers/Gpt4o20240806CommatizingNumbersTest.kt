import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CommatizeTest {

    @Test
    fun testCommatizeWithDefaultParameters() {
        assertEquals("123,456,789.123,456,789", "123456789.123456789".commatize())
        assertEquals(".123,456,789", ".123456789".commatize())
        assertEquals("572,56.1D-4", "57256.1D-4".commatize())
        assertEquals("pi=3.14159 26535 89793 23846 26433 83279 50288 41971 69399 37510 58209 74944 59231", 
                     "pi=3.14159265358979323846264338327950288419716939937510582097494459231".commatize(period = 5, sep = " "))
        assertEquals("The author has two Z$100.000.000.000.000 Zimbabwe notes (100 trillion).", 
                     "The author has two Z$100000000000000 Zimbabwe notes (100 trillion).".commatize(sep = "."))
        assertEquals("-in Aus$+1,411.8millions", "-in Aus$+1411.8millions".commatize())
        assertEquals("===US$00,174,40 millions=== (in 2000 dollars)", "===US$0017440 millions=== (in 2000 dollars)".commatize())
        assertEquals("123.e8000 is pretty big.", "123.e8000 is pretty big.".commatize())
        assertEquals("The land area of the earth is 57,268,900(29% of the surface) square miles.", 
                     "The land area of the earth is 57268900(29% of the surface) square miles.".commatize())
        assertEquals("Ain't no numbers in this here words, nohow, no way, Jose.", 
                     "Ain't no numbers in this here words, nohow, no way, Jose.".commatize())
        assertEquals("James was never known as 0000000007", "James was never known as 0000000007".commatize())
        assertEquals("Arthur Eddington wrote: I believe there are 15,747,724,136,275,002,577,605,653,961,181,555,468,044,717,914,527,116,709,366,231,425,076,185,631,031,296 protons in the universe.", 
                     "Arthur Eddington wrote: I believe there are 15747724136275002577605653961181555468044717914527116709366231425076185631031296 protons in the universe.".commatize())
        assertEquals("   $-140,000±100 millions.", "   $-140000±100 millions.".commatize())
        assertEquals("6/9/1946 was a good year for some.", "6/9/1946 was a good year for some.".commatize())
    }

    @Test
    fun testCommatizeWithCustomSeparatorAndPeriod() {
        assertEquals("12*34*56*78*9.12*34*56*78*9", "123456789.123456789".commatize(period = 2, sep = "*"))
        assertEquals(".123-456-789", ".123456789".commatize(period = 3, sep = "-"))
        assertEquals("5725__6.1D-4", "57256.1D-4".commatize(period = 4, sep = "__"))
    }

    @Test
    fun testCommatizeWithInvalidParameters() {
        assertEquals("123456789.123456789", "123456789.123456789".commatize(period = 0))
        assertEquals("123456789.123456789", "123456789.123456789".commatize(sep = ""))
        assertEquals("123456789.123456789", "123456789.123456789".commatize(startIndex = -1))
        assertEquals("123456789.123456789", "123456789.123456789".commatize(startIndex = 100))
    }
}
