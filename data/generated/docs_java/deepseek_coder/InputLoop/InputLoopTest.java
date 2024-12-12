import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class InputLoopTest {

    @Test
    public void testWordByWordReading() {
        // Arrange
        String input = "Hello world this is a test";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner mockScanner = Mockito.spy(new Scanner(inputStream));

        // Act
        while (mockScanner.hasNext()) {
            String word = mockScanner.next();
            // Assert
            assert word != null && !word.isEmpty();
            System.out.println(word); // Simulate the processing
        }

        // Verify
        verify(mockScanner, times(5)).next(); // "Hello", "world", "this", "is", "a", "test"
    }

    @Test
    public void testLineByLineReading() {
        // Arrange
        String input = "Line 1\nLine 2\nLine 3";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner mockScanner = Mockito.spy(new Scanner(inputStream));

        // Act
        while (mockScanner.hasNext()) {
            String line = mockScanner.nextLine();
            // Assert
            assert line != null && !line.isEmpty();
            System.out.println(line); // Simulate the processing
        }

        // Verify
        verify(mockScanner, times(3)).nextLine(); // "Line 1", "Line 2", "Line 3"
    }

    @Test
    public void testEmptyInput() {
        // Arrange
        String input = "";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner mockScanner = Mockito.spy(new Scanner(inputStream));

        // Act
        while (mockScanner.hasNext()) {
            String word = mockScanner.next();
            // Assert
            assert word != null && !word.isEmpty();
            System.out.println(word); // Simulate the processing
        }

        // Verify
        verify(mockScanner, never()).next(); // No calls to next() should be made
    }

    @Test
    public void testMixedInput() {
        // Arrange
        String input = "Hello\nworld\nthis is a test";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner mockScanner = Mockito.spy(new Scanner(inputStream));

        // Act
        while (mockScanner.hasNext()) {
            String word = mockScanner.next();
            // Assert
            assert word != null && !word.isEmpty();
            System.out.println(word); // Simulate the processing
        }

        // Verify
        verify(mockScanner, times(6)).next(); // "Hello", "world", "this", "is", "a", "test"
    }
}
