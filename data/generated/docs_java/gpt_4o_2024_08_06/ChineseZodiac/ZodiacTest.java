import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ZodiacTest {

    @Test
    public void testGetYY() {
        assertEquals("yang", Zodiac.getYY(2022));
        assertEquals("yin", Zodiac.getYY(2023));
        assertEquals("yang", Zodiac.getYY(1984));
        assertEquals("yin", Zodiac.getYY(1985));
    }

    @Test
    public void testZodiacYear() {
        assertEquals("Wood Rat (yang). 甲子", getZodiacYear(1984));
        assertEquals("Wood Ox (yin). 乙丑", getZodiacYear(1985));
        assertEquals("Fire Tiger (yang). 丙寅", getZodiacYear(1986));
        assertEquals("Water Tiger (yang). 壬寅", getZodiacYear(2022));
        assertEquals("Earth Monkey (yang). 戊申", getZodiacYear(1968));
        assertEquals("Metal Rat (yang). 庚子", getZodiacYear(1960));
    }

    private String getZodiacYear(int year) {
        String element = Zodiac.elements[(int) Math.floor((year - 4) % 10 / 2)];
        String animal = Zodiac.animals[(year - 4) % 12];
        String yinYang = Zodiac.getYY(year);
        String elementChar = Zodiac.elementChars[year % 2][(int) Math.floor((year - 4) % 10 / 2)];
        String animalChar = Zodiac.animalChars[(year - 4) % 12];
        return element + " " + animal + " (" + yinYang + "). " + elementChar + animalChar;
    }
}
