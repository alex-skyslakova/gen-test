import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzTest {

    @Test
    void testFizzBuzzWithDefaultValues() {
        Sound[] sounds = {new Sound(3, "Fizz"), new Sound(5, "Buzz"), new Sound(7, "Baxx")};
        String[] expectedOutput = {
            "1", "2", "Fizz", "4", "Buzz", "Fizz", "Baxx", "8", "Fizz", "Buzz",
            "11", "Fizz", "13", "Baxx", "FizzBuzz", "16", "17", "Fizz", "19", "Buzz"
        };

        for (int i = 1; i <= 20; i++) {
            StringBuilder sb = new StringBuilder();
            for (Sound sound : sounds) {
                sb.append(sound.generate(i));
            }
            String result = sb.length() == 0 ? String.valueOf(i) : sb.toString();
            assertEquals(expectedOutput[i - 1], result, "Failed at index: " + i);
        }
    }

    @Test
    void testFizzBuzzWithDifferentFactors() {
        Sound[] sounds = {new Sound(2, "Foo"), new Sound(4, "Bar"), new Sound(6, "Baz")};
        String[] expectedOutput = {
            "1", "Foo", "3", "FooBar", "5", "FooBaz", "7", "FooBar", "9", "Foo",
            "11", "FooBarBaz", "13", "Foo", "15", "FooBar", "17", "FooBaz", "19", "FooBar"
        };

        for (int i = 1; i <= 20; i++) {
            StringBuilder sb = new StringBuilder();
            for (Sound sound : sounds) {
                sb.append(sound.generate(i));
            }
            String result = sb.length() == 0 ? String.valueOf(i) : sb.toString();
            assertEquals(expectedOutput[i - 1], result, "Failed at index: " + i);
        }
    }

    @Test
    void testFizzBuzzWithNoFactors() {
        Sound[] sounds = {};
        for (int i = 1; i <= 20; i++) {
            StringBuilder sb = new StringBuilder();
            for (Sound sound : sounds) {
                sb.append(sound.generate(i));
            }
            String result = sb.length() == 0 ? String.valueOf(i) : sb.toString();
            assertEquals(String.valueOf(i), result, "Failed at index: " + i);
        }
    }

    @Test
    void testFizzBuzzWithSingleFactor() {
        Sound[] sounds = {new Sound(3, "Fizz")};
        String[] expectedOutput = {
            "1", "2", "Fizz", "4", "5", "Fizz", "7", "8", "Fizz", "10",
            "11", "Fizz", "13", "14", "Fizz", "16", "17", "Fizz", "19", "20"
        };

        for (int i = 1; i <= 20; i++) {
            StringBuilder sb = new StringBuilder();
            for (Sound sound : sounds) {
                sb.append(sound.generate(i));
            }
            String result = sb.length() == 0 ? String.valueOf(i) : sb.toString();
            assertEquals(expectedOutput[i - 1], result, "Failed at index: " + i);
        }
    }
}
