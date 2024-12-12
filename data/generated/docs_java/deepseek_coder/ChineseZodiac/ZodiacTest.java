import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ZodiacTest {

    @Test
    public void testGetYY_YangYear() {
        assertEquals("yang", Zodiac.getYY(2022));
    }

    @Test
    public void testGetYY_YinYear() {
        assertEquals("yin", Zodiac.getYY(2021));
    }

    @Test
    public void testMainOutput() {
        // Capture the output of the main method
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        Zodiac.main(new String[]{});

        String expectedOutput = 
            "1935 is the year of the Wood Pig (yin). 乙亥\n" +
            "1938 is the year of the Earth Tiger (yang). 戊寅\n" +
            "1968 is the year of the Earth Monkey (yang). 戊申\n" +
            "1972 is the year of the Water Rat (yang). 壬子\n" +
            "1976 is the year of the Fire Dragon (yang). 丙辰\n" +
            "1984 is the year of the Wood Rat (yang). 甲子\n" +
            "1985 is the year of the Wood Ox (yin). 乙丑\n" +
            "2017 is the year of the Fire Rooster (yin). 丁酉\n";

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testElementCycle() {
        assertEquals("Wood", Zodiac.elements[(int) Math.floor((1984 - 4) % 10 / 2)]);
        assertEquals("Wood", Zodiac.elements[(int) Math.floor((1985 - 4) % 10 / 2)]);
        assertEquals("Fire", Zodiac.elements[(int) Math.floor((1986 - 4) % 10 / 2)]);
        assertEquals("Water", Zodiac.elements[(int) Math.floor((2022 - 4) % 10 / 2)]);
    }

    @Test
    public void testAnimalCycle() {
        assertEquals("Rat", Zodiac.animals[(1984 - 4) % 12]);
        assertEquals("Ox", Zodiac.animals[(1985 - 4) % 12]);
        assertEquals("Tiger", Zodiac.animals[(1986 - 4) % 12]);
        assertEquals("Tiger", Zodiac.animals[(2022 - 4) % 12]);
    }

    @Test
    public void testElementChars() {
        assertEquals("甲", Zodiac.elementChars[0][(int) Math.floor((1984 - 4) % 10 / 2)]);
        assertEquals("乙", Zodiac.elementChars[1][(int) Math.floor((1985 - 4) % 10 / 2)]);
        assertEquals("丙", Zodiac.elementChars[0][(int) Math.floor((1986 - 4) % 10 / 2)]);
        assertEquals("壬", Zodiac.elementChars[0][(int) Math.floor((2022 - 4) % 10 / 2)]);
    }

    @Test
    public void testAnimalChars() {
        assertEquals("子", Zodiac.animalChars[(1984 - 4) % 12]);
        assertEquals("丑", Zodiac.animalChars[(1985 - 4) % 12]);
        assertEquals("寅", Zodiac.animalChars[(1986 - 4) % 12]);
        assertEquals("寅", Zodiac.animalChars[(2022 - 4) % 12]);
    }
}
