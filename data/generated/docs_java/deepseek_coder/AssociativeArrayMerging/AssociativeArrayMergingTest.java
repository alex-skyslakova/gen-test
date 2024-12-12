import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class MergeMapsTest {

    @Test
    void testMergeMaps() {
        // Arrange
        Map<String, Object> base = new HashMap<>();
        base.put("name", "Rocket Skates");
        base.put("price", 12.75);
        base.put("color", "yellow");

        Map<String, Object> update = new HashMap<>();
        update.put("price", 15.25);
        update.put("color", "red");
        update.put("year", 1974);

        // Act
        Map<String, Object> result = new HashMap<>(base);
        result.putAll(update);

        // Assert
        Map<String, Object> expected = new HashMap<>();
        expected.put("name", "Rocket Skates");
        expected.put("price", 15.25);
        expected.put("color", "red");
        expected.put("year", 1974);

        assertEquals(expected, result);
    }

    @Test
    void testMergeMapsWithEmptyBase() {
        // Arrange
        Map<String, Object> base = new HashMap<>();

        Map<String, Object> update = new HashMap<>();
        update.put("price", 15.25);
        update.put("color", "red");
        update.put("year", 1974);

        // Act
        Map<String, Object> result = new HashMap<>(base);
        result.putAll(update);

        // Assert
        Map<String, Object> expected = new HashMap<>();
        expected.put("price", 15.25);
        expected.put("color", "red");
        expected.put("year", 1974);

        assertEquals(expected, result);
    }

    @Test
    void testMergeMapsWithEmptyUpdate() {
        // Arrange
        Map<String, Object> base = new HashMap<>();
        base.put("name", "Rocket Skates");
        base.put("price", 12.75);
        base.put("color", "yellow");

        Map<String, Object> update = new HashMap<>();

        // Act
        Map<String, Object> result = new HashMap<>(base);
        result.putAll(update);

        // Assert
        Map<String, Object> expected = new HashMap<>();
        expected.put("name", "Rocket Skates");
        expected.put("price", 12.75);
        expected.put("color", "yellow");

        assertEquals(expected, result);
    }

    @Test
    void testMergeMapsWithOverlappingKeys() {
        // Arrange
        Map<String, Object> base = new HashMap<>();
        base.put("name", "Rocket Skates");
        base.put("price", 12.75);
        base.put("color", "yellow");

        Map<String, Object> update = new HashMap<>();
        update.put("price", 15.25);
        update.put("color", "red");
        update.put("year", 1974);

        // Act
        Map<String, Object> result = new HashMap<>(base);
        result.putAll(update);

        // Assert
        Map<String, Object> expected = new HashMap<>();
        expected.put("name", "Rocket Skates");
        expected.put("price", 15.25);
        expected.put("color", "red");
        expected.put("year", 1974);

        assertEquals(expected, result);
    }

    @Test
    void testMergeMapsWithDifferentTypes() {
        // Arrange
        Map<String, Object> base = new HashMap<>();
        base.put("name", "Rocket Skates");
        base.put("price", 12.75);
        base.put("color", "yellow");

        Map<String, Object> update = new HashMap<>();
        update.put("price", "15.25"); // Different type
        update.put("color", "red");
        update.put("year", 1974);

        // Act
        Map<String, Object> result = new HashMap<>(base);
        result.putAll(update);

        // Assert
        Map<String, Object> expected = new HashMap<>();
        expected.put("name", "Rocket Skates");
        expected.put("price", "15.25"); // Updated with different type
        expected.put("color", "red");
        expected.put("year", 1974);

        assertEquals(expected, result);
    }
}
