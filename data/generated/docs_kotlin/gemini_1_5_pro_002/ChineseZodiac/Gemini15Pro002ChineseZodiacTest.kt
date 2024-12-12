import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class ChineseZodiacTest {

    @Test
    fun test1984() {
        val zodiac = ChineseZodiac(1984)
        assertEquals("Wood", zodiac.element)
        assertEquals("Rat", zodiac.animal)
        assertEquals("Yang", zodiac.aspect)
        assertEquals('甲', zodiac.stem)
        assertEquals('子', zodiac.branch)
        assertEquals("jiă", zodiac.sName)
        assertEquals("zĭ", zodiac.bName)
        assertEquals(1, zodiac.cycle)
    }

    @Test
    fun test1985() {
        val zodiac = ChineseZodiac(1985)
        assertEquals("Wood", zodiac.element)
        assertEquals("Ox", zodiac.animal)
        assertEquals("Yin", zodiac.aspect)
        assertEquals(2, zodiac.cycle)
    }

    @Test
    fun test1986() {
        val zodiac = ChineseZodiac(1986)
        assertEquals("Fire", zodiac.element)
        assertEquals("Tiger", zodiac.animal)
        assertEquals("Yang", zodiac.aspect)
        assertEquals(3, zodiac.cycle)
    }

    @Test
    fun test2022() {
        val zodiac = ChineseZodiac(2022)
        assertEquals("Water", zodiac.element)
        assertEquals("Tiger", zodiac.animal)
        assertEquals("Yang", zodiac.aspect)
        assertEquals('壬', zodiac.stem)
        assertEquals('寅', zodiac.branch)
        assertEquals("rén", zodiac.sName)
        assertEquals("yín", zodiac.bName)
        assertEquals(39, zodiac.cycle)

    }

    @Test
    fun test4() { // First cycle of the Common Era
        val zodiac = ChineseZodiac(4)
        assertEquals("Wood", zodiac.element)
        assertEquals("Rat", zodiac.animal)
        assertEquals("Yang", zodiac.aspect)
        assertEquals(1, zodiac.cycle)

    }


    @Test
    fun test1935(){ //Example year
        val zodiac = ChineseZodiac(1935)
        assertEquals("Wood", zodiac.element)
        assertEquals("Pig", zodiac.animal)
        assertEquals("Yin", zodiac.aspect)
    }
    @Test
    fun test1938(){ //Example year
        val zodiac = ChineseZodiac(1938)
        assertEquals("Earth", zodiac.element)
        assertEquals("Tiger", zodiac.animal)
        assertEquals("Yang", zodiac.aspect)

    }

    @Test
    fun test1968(){ //Example year
        val zodiac = ChineseZodiac(1968)
        assertEquals("Earth", zodiac.element)
        assertEquals("Monkey", zodiac.animal)
        assertEquals("Yang", zodiac.aspect)

    }

    @Test
    fun test1972(){ //Example year
        val zodiac = ChineseZodiac(1972)
        assertEquals("Water", zodiac.element)
        assertEquals("Rat", zodiac.animal)
        assertEquals("Yang", zodiac.aspect)

    }

    @Test
    fun test1976(){ //Example year
        val zodiac = ChineseZodiac(1976)
        assertEquals("Fire", zodiac.element)
        assertEquals("Dragon", zodiac.animal)
        assertEquals("Yang", zodiac.aspect)

    }
        @Test
    fun test2017(){ //Example year
        val zodiac = ChineseZodiac(2017)
        assertEquals("Fire", zodiac.element)
        assertEquals("Rooster", zodiac.animal)
        assertEquals("Yin", zodiac.aspect)

    }

    @Test
    fun testToString() {
        val zodiac = ChineseZodiac(2022)
        val expected = "2022    壬寅   rén-yín     Water     Tiger    Yang    39/60"
        assertEquals(expected, zodiac.toString())

    }



}
