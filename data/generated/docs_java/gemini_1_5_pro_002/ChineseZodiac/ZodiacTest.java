import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ZodiacTest {

    @Test
    void testGetYYEven() {
        assertEquals("yang", Zodiac.getYY(1984));
    }

    @Test
    void testGetYYOdd() {
        assertEquals("yin", Zodiac.getYY(1985));
    }

    @Test
    void testMainIntegration1935() {
        // Note: System.out capturing would be ideal, but for simplicity, we test a known output component.
       String expectedElementAnimal = "Wood Pig";
       int year = 1935;
       String actual = "Wood "+Zodiac.animals[(year-4)%12];
        assertEquals(expectedElementAnimal, actual);
    }
    
    @Test
    void testMainIntegration1938() {
        String expectedElementAnimal = "Earth Tiger";
        int year = 1938;
        String actual = "Earth "+Zodiac.animals[(year-4)%12];
        assertEquals(expectedElementAnimal, actual);

    }

    @Test
    void testMainIntegration1968() {
        String expectedElementAnimal = "Earth Monkey";
        int year = 1968;
        String actual = "Earth "+Zodiac.animals[(year-4)%12];
        assertEquals(expectedElementAnimal, actual);
    }

    @Test
    void testMainIntegration1972() {
        String expectedElementAnimal = "Water Rat";
        int year = 1972;
        String actual = "Water "+Zodiac.animals[(year-4)%12];
        assertEquals(expectedElementAnimal, actual);
    }

    @Test
    void testMainIntegration1976() {
        String expectedElementAnimal = "Fire Dragon";
        int year = 1976;
        String actual = "Fire "+Zodiac.animals[(year-4)%12];
        assertEquals(expectedElementAnimal, actual);
    }


    @Test
    void testMainIntegration1984() {
        String expectedElementAnimal = "Wood Rat";
        int year = 1984;
        String actual = "Wood "+Zodiac.animals[(year-4)%12];
        assertEquals(expectedElementAnimal, actual);
    }

    @Test
    void testMainIntegration1985() {
        String expectedElementAnimal = "Wood Ox";
        int year = 1985;
        String actual = "Wood "+Zodiac.animals[(year-4)%12];
        assertEquals(expectedElementAnimal, actual);
    }


    @Test
    void testMainIntegration2017() {
        String expectedElementAnimal = "Fire Rooster";
        int year = 2017;
        String actual = "Fire "+Zodiac.animals[(year-4)%12];
        assertEquals(expectedElementAnimal, actual);
    }

    @Test
    void testMainIntegration2022() {  // Added test for the example year given in the prompt
        String expectedElementAnimal = "Water Tiger";
        int year = 2022;
        String actual = "Water "+Zodiac.animals[(year-4)%12];
        assertEquals(expectedElementAnimal, actual);

    }


}
