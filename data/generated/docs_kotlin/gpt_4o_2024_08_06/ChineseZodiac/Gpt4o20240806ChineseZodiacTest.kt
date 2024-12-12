import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ChineseZodiacTest {

    @Test
    fun testYear1935() {
        val zodiac = ChineseZodiac(1935)
        assertEquals('乙', zodiac.stem)
        assertEquals('亥', zodiac.branch)
        assertEquals("yĭ-hài", zodiac.sName + "-" + zodiac.bName)
        assertEquals("Wood", zodiac.element)
        assertEquals("Pig", zodiac.animal)
        assertEquals("Yin", zodiac.aspect)
        assertEquals(12, zodiac.cycle)
    }

    @Test
    fun testYear1938() {
        val zodiac = ChineseZodiac(1938)
        assertEquals('戊', zodiac.stem)
        assertEquals('寅', zodiac.branch)
        assertEquals("wù-yín", zodiac.sName + "-" + zodiac.bName)
        assertEquals("Earth", zodiac.element)
        assertEquals("Tiger", zodiac.animal)
        assertEquals("Yang", zodiac.aspect)
        assertEquals(15, zodiac.cycle)
    }

    @Test
    fun testYear1968() {
        val zodiac = ChineseZodiac(1968)
        assertEquals('戊', zodiac.stem)
        assertEquals('申', zodiac.branch)
        assertEquals("wù-shēn", zodiac.sName + "-" + zodiac.bName)
        assertEquals("Earth", zodiac.element)
        assertEquals("Monkey", zodiac.animal)
        assertEquals("Yang", zodiac.aspect)
        assertEquals(25, zodiac.cycle)
    }

    @Test
    fun testYear1972() {
        val zodiac = ChineseZodiac(1972)
        assertEquals('壬', zodiac.stem)
        assertEquals('子', zodiac.branch)
        assertEquals("rén-zĭ", zodiac.sName + "-" + zodiac.bName)
        assertEquals("Water", zodiac.element)
        assertEquals("Rat", zodiac.animal)
        assertEquals("Yang", zodiac.aspect)
        assertEquals(29, zodiac.cycle)
    }

    @Test
    fun testYear1976() {
        val zodiac = ChineseZodiac(1976)
        assertEquals('丙', zodiac.stem)
        assertEquals('辰', zodiac.branch)
        assertEquals("bĭng-chén", zodiac.sName + "-" + zodiac.bName)
        assertEquals("Fire", zodiac.element)
        assertEquals("Dragon", zodiac.animal)
        assertEquals("Yang", zodiac.aspect)
        assertEquals(33, zodiac.cycle)
    }

    @Test
    fun testYear1984() {
        val zodiac = ChineseZodiac(1984)
        assertEquals('甲', zodiac.stem)
        assertEquals('子', zodiac.branch)
        assertEquals("jiă-zĭ", zodiac.sName + "-" + zodiac.bName)
        assertEquals("Wood", zodiac.element)
        assertEquals("Rat", zodiac.animal)
        assertEquals("Yang", zodiac.aspect)
        assertEquals(1, zodiac.cycle)
    }

    @Test
    fun testYear2017() {
        val zodiac = ChineseZodiac(2017)
        assertEquals('丁', zodiac.stem)
        assertEquals('酉', zodiac.branch)
        assertEquals("dīng-yŏu", zodiac.sName + "-" + zodiac.bName)
        assertEquals("Fire", zodiac.element)
        assertEquals("Rooster", zodiac.animal)
        assertEquals("Yin", zodiac.aspect)
        assertEquals(34, zodiac.cycle)
    }
}
