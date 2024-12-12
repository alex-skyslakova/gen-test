import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FizzBuzzTest {

    @Test
    public void testSingleFactor() {
        FizzBuzz.Sound sound = new FizzBuzz.Sound(3, "Fizz");
        assertEquals("Fizz", sound.generate(3));
        assertEquals("", sound.generate(4));
    }

    @Test
    public void testMultipleFactors() {
        FizzBuzz.Sound sound1 = new FizzBuzz.Sound(3, "Fizz");
        FizzBuzz.Sound sound2 = new FizzBuzz.Sound(5, "Buzz");
        FizzBuzz.Sound sound3 = new FizzBuzz.Sound(7, "Baxx");

        assertEquals("Fizz", sound1.generate(6));
        assertEquals("Buzz", sound2.generate(10));
        assertEquals("Baxx", sound3.generate(14));
    }

    @Test
    public void testCombinationOfFactors() {
        FizzBuzz.Sound sound1 = new FizzBuzz.Sound(3, "Fizz");
        FizzBuzz.Sound sound2 = new FizzBuzz.Sound(5, "Buzz");
        FizzBuzz.Sound sound3 = new FizzBuzz.Sound(7, "Baxx");

        assertEquals("FizzBuzz", generateCombined(new FizzBuzz.Sound[]{sound1, sound2}, 15));
        assertEquals("FizzBaxx", generateCombined(new FizzBuzz.Sound[]{sound1, sound3}, 21));
        assertEquals("BuzzBaxx", generateCombined(new FizzBuzz.Sound[]{sound2, sound3}, 35));
        assertEquals("FizzBuzzBaxx", generateCombined(new FizzBuzz.Sound[]{sound1, sound2, sound3}, 105));
    }

    @Test
    public void testNoFactorMatch() {
        FizzBuzz.Sound sound1 = new FizzBuzz.Sound(3, "Fizz");
        FizzBuzz.Sound sound2 = new FizzBuzz.Sound(5, "Buzz");
        FizzBuzz.Sound sound3 = new FizzBuzz.Sound(7, "Baxx");

        assertEquals("1", generateCombined(new FizzBuzz.Sound[]{sound1, sound2, sound3}, 1));
        assertEquals("2", generateCombined(new FizzBuzz.Sound[]{sound1, sound2, sound3}, 2));
    }

    @Test
    public void testBoundaryConditions() {
        FizzBuzz.Sound sound1 = new FizzBuzz.Sound(3, "Fizz");
        FizzBuzz.Sound sound2 = new FizzBuzz.Sound(5, "Buzz");
        FizzBuzz.Sound sound3 = new FizzBuzz.Sound(7, "Baxx");

        assertEquals("Fizz", generateCombined(new FizzBuzz.Sound[]{sound1, sound2, sound3}, 3));
        assertEquals("Buzz", generateCombined(new FizzBuzz.Sound[]{sound1, sound2, sound3}, 5));
        assertEquals("Baxx", generateCombined(new FizzBuzz.Sound[]{sound1, sound2, sound3}, 7));
    }

    private String generateCombined(FizzBuzz.Sound[] sounds, int number) {
        StringBuilder sb = new StringBuilder();
        for (FizzBuzz.Sound sound : sounds) {
            sb.append(sound.generate(number));
        }
        return sb.length() == 0 ? String.valueOf(number) : sb.toString();
    }
}
