import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FizzBuzzTest {

    @Test
    void testSoundGenerate() {
        FizzBuzz.Sound sound = new FizzBuzz.Sound(3, "Fizz");
        assertEquals("Fizz", sound.generate(3));
        assertEquals("", sound.generate(4));
    }

    @Test
    void testFizzBuzzSimple() {
        FizzBuzz.Sound[] sounds = {new FizzBuzz.Sound(3, "Fizz")};
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            StringBuilder innerSb = new StringBuilder();
            for (FizzBuzz.Sound sound : sounds) {
                innerSb.append(sound.generate(i));
            }
            sb.append(innerSb.length() == 0 ? i : innerSb.toString()).append("\n");

        }

        assertEquals("1\n2\nFizz\n4\n5\n", sb.toString());
        
    }

    @Test
    void testFizzBuzzMultipleFactors() {
        FizzBuzz.Sound[] sounds = {new FizzBuzz.Sound(3, "Fizz"), new FizzBuzz.Sound(5, "Buzz")};
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 15; i++) {
            StringBuilder innerSb = new StringBuilder();
            for (FizzBuzz.Sound sound : sounds) {
                innerSb.append(sound.generate(i));
            }
            sb.append(innerSb.length() == 0 ? i : innerSb.toString()).append("\n");
        }

        assertEquals("1\n2\nFizz\n4\nBuzz\nFizz\n7\n8\nFizz\nBuzz\n11\nFizz\n13\n14\nFizzBuzz\n", sb.toString());

    }


        @Test
    void testFizzBuzzExample() {
        FizzBuzz.Sound[] sounds = {new FizzBuzz.Sound(3, "Fizz"), new FizzBuzz.Sound(5, "Buzz"), new FizzBuzz.Sound(7, "Baxx")};
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 20; i++) {
            StringBuilder innerSb = new StringBuilder();
            for (FizzBuzz.Sound sound : sounds) {
                innerSb.append(sound.generate(i));
            }
            sb.append(innerSb.length() == 0 ? i : innerSb.toString()).append("\n");
        }

        assertEquals("1\n2\nFizz\n4\nBuzz\nFizz\nBaxx\n8\nFizz\nBuzz\n11\nFizz\n13\nBaxx\nFizzBuzz\n16\n17\nFizz\n19\nBuzz\n", sb.toString());

    }

    @Test
    void testFizzBuzzLargeNumber() {
        FizzBuzz.Sound[] sounds = {new FizzBuzz.Sound(3, "Fizz"), new FizzBuzz.Sound(5, "Buzz"), new FizzBuzz.Sound(7, "Baxx")};
        StringBuilder sb = new StringBuilder();

            StringBuilder innerSb = new StringBuilder();
            for (FizzBuzz.Sound sound : sounds) {
                innerSb.append(sound.generate(105));
            }
            sb.append(innerSb.length() == 0 ? 105 : innerSb.toString()).append("\n");
        assertEquals("FizzBuzzBaxx\n", sb.toString());
    }
}
