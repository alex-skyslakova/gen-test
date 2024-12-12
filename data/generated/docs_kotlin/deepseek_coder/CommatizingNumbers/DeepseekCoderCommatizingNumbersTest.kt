import org.junit.Test
import kotlin.test.assertEquals

class CommatizeTest {

    @Test
    fun testCommatizeWithDefaultParameters() {
        val tests = arrayOf(
            "123456789.123456789" to "123,456,789.123456789",
            ".123456789" to ".123456789",
            "57256.1D-4" to "57,256.1D-4",
            "pi=3.14159265358979323846264338327950288419716939937510582097494459231" to "pi=3.14159265358979323846264338327950288419716939937510582097494459231",
            "The author has two Z$100000000000000 Zimbabwe notes (100 trillion)." to "The author has two Z$100,000,000,000,000 Zimbabwe notes (100 trillion).",
            "-in Aus$+1411.8millions" to "-in Aus$+1,411.8millions",
            "===US$0017440 millions=== (in 2000 dollars)" to "===US$0017,440 millions=== (in 2000 dollars)",
            "123.e8000 is pretty big." to "123.e8000 is pretty big.",
            "The land area of the earth is 57268900(29% of the surface) square miles." to "The land area of the earth is 57,268,900(29% of the surface) square miles.",
            "Ain't no numbers in this here words, nohow, no way, Jose." to "Ain't no numbers in this here words, nohow, no way, Jose.",
            "James was never known as 0000000007" to "James was never known as 0000000007",
            "Arthur Eddington wrote: I believe there are " + 
            "15747724136275002577605653961181555468044717914527116709366231425076185631031296" +     
            " protons in the universe." to "Arthur Eddington wrote: I believe there are " + 
            "157,477,241,362,750,025,776,056,539,611,815,554,680,447,179,145,271,167,093,662,314,250,761,856,310,312,96" +     
            " protons in the universe.",
            "   $-140000±100 millions." to "   $-140,000±100 millions.",
            "6/9/1946 was a good year for some." to "6/9/1946 was a good year for some."
        )

        for ((input, expected) in tests) {
            assertEquals(expected, input.commatize())
        }
    }

    @Test
    fun testCommatizeWithCustomParameters() {
        val tests = arrayOf(
            "123456789.123456789" to "12*34*56*789.123456789",
            ".123456789" to ".123456789",
            "57256.1D-4" to "57__256.1D-4",
            "pi=3.14159265358979323846264338327950288419716939937510582097494459231" to "pi=3.14159 26535 89793 23846 26433 83279 50288 41971 69399 37510 58209 74944 59231",
            "The author has two Z$100000000000000 Zimbabwe notes (100 trillion)." to "The author has two Z$100.000.000.000.000 Zimbabwe notes (100 trillion).",
            "-in Aus$+1411.8millions" to "-in Aus$+1,411.8millions",
            "===US$0017440 millions=== (in 2000 dollars)" to "===US$0017,440 millions=== (in 2000 dollars)",
            "123.e8000 is pretty big." to "123.e8000 is pretty big.",
            "The land area of the earth is 57268900(29% of the surface) square miles." to "The land area of the earth is 57,268,900(29% of the surface) square miles.",
            "Ain't no numbers in this here words, nohow, no way, Jose." to "Ain't no numbers in this here words, nohow, no way, Jose.",
            "James was never known as 0000000007" to "James was never known as 0000000007",
            "Arthur Eddington wrote: I believe there are " + 
            "15747724136275002577605653961181555468044717914527116709366231425076185631031296" +     
            " protons in the universe." to "Arthur Eddington wrote: I believe there are " + 
            "157,477,241,362,750,025,776,056,539,611,815,554,680,447,179,145,271,167,093,662,314,250,761,856,310,312,96" +     
            " protons in the universe.",
            "   $-140000±100 millions." to "   $-140,000±100 millions.",
            "6/9/1946 was a good year for some." to "6/9/1946 was a good year for some."
        )

        assertEquals("12*34*56*789.123456789", tests[0].first.commatize(period = 2, sep = "*"))
        assertEquals(".123456789", tests[1].first.commatize(period = 3, sep = "-"))
        assertEquals("57__256.1D-4", tests[2].first.commatize(period = 4, sep = "__"))
        assertEquals("pi=3.14159 26535 89793 23846 26433 83279 50288 41971 69399 37510 58209 74944 59231", tests[3].first.commatize(period = 5, sep = " "))
        assertEquals("The author has two Z$100.000.000.000.000 Zimbabwe notes (100 trillion).", tests[4].first.commatize(sep = "."))
    }

    @Test
    fun testCommatizeWithInvalidParameters() {
        val tests = arrayOf(
            "123456789.123456789" to "123456789.123456789",
            ".123456789" to ".123456789",
            "57256.1D-4" to "57256.1D-4",
            "pi=3.14159265358979323846264338327950288419716939937510582097494459231" to "pi=3.14159265358979323846264338327950288419716939937510582097494459231",
            "The author has two Z$100000000000000 Zimbabwe notes (100 trillion)." to "The author has two Z$100000000000000 Zimbabwe notes (100 trillion).",
            "-in Aus$+1411.8millions" to "-in Aus$+1411.8millions",
            "===US$0017440 millions=== (in 2000 dollars)" to "===US$0017440 millions=== (in 2000 dollars)",
            "123.e8000 is pretty big." to "123.e8000 is pretty big.",
            "The land area of the earth is 57268900(29% of the surface) square miles." to "The land area of the earth is 57268900(29% of the surface) square miles.",
            "Ain't no numbers in this here words, nohow, no way, Jose." to "Ain't no numbers in this here words, nohow, no way, Jose.",
            "James was never known as 0000000007" to "James was never known as 0000000007",
            "Arthur Eddington wrote: I believe there are " + 
            "15747724136275002577605653961181555468044717914527116709366231425076185631031296" +     
            " protons in the universe." to "Arthur Eddington wrote: I believe there are " + 
            "15747724136275002577605653961181555468044717914527116709366231425076185631031296" +     
            " protons in the universe.",
            "   $-140000±100 millions." to "   $-140000±100 millions.",
            "6/9/1946 was a good year for some." to "6/9/1946 was a good year for some."
        )

        assertEquals("123456789.123456789", tests[0].first.commatize(startIndex = -1))
        assertEquals(".123456789", tests[1].first.commatize(period = 0))
        assertEquals("57256.1D-4", tests[2].first.commatize(sep = ""))
        assertEquals("pi=3.14159265358979323846264338327950288419716939937510582097494459231", tests[3].first.commatize(startIndex = 100))
        assertEquals("The author has two Z$100000000000000 Zimbabwe notes (100 trillion).", tests[4].first.commatize(sep = ""))
    }
}
