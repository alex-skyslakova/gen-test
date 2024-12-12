import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class MergeMapsTest {

    @Test
    void testMergeMaps() {
        Map<String, Object> base = new HashMap<>();
        base.put("name", "Rocket Skates");
        base.put("price", 12.75);
        base.put("color", "yellow");

        Map<String, Object> update = new HashMap<>();
        update.put("price", 15.25);
        update.put("color", "red");
        update.put("year", 1974);

        Map<String, Object> expected = new HashMap<>();
        expected.put("name", "Rocket Skates");
        expected.put("price", 15.25);
        expected.put("color", "red");
        expected.put("year", 1974);

        Map<String, Object> result = new HashMap<>(base);
        result.putAll(update);

        assertEquals(expected, result);
    }

    @Test
    void testBaseNotMutated() {
        Map<String, Object> base = new HashMap<>();
        base.put("name", "Rocket Skates");
        base.put("price", 12.75);
        base.put("color", "yellow");

        Map<String, Object> update = new HashMap<>();
        update.put("price", 15.25);
        update.put("color", "red");
        update.put("year", 1974);

        Map<String, Object> originalBase = new HashMap<>(base);

        Map<String, Object> result = new HashMap<>(base);
        result.putAll(update);

        assertEquals(originalBase, base);
    }

    @Test
    void testUpdateNotMutated() {
        Map<String, Object> base = new HashMap<>();
        base.put("name", "Rocket Skates");
        base.put("price", 12.75);
        base.put("color", "yellow");

        Map<String, Object> update = new HashMap<>();
        update.put("price", 15.25);
        update.put("color", "red");
        update.put("year", 1974);

        Map<String, Object> originalUpdate = new HashMap<>(update);

        Map<String, Object> result = new HashMap<>(base);
        result.putAll(update);

        assertEquals(originalUpdate, update);
    }

    @Test
    void testEmptyBase() {
        Map<String, Object> base = new HashMap<>();

        Map<String, Object> update = new HashMap<>();
        update.put("price", 15.25);
        update.put("color", "red");
        update.put("year", 1974);

        Map<String, Object> expected = new HashMap<>(update);

        Map<String, Object> result = new HashMap<>(base);
        result.putAll(update);

        assertEquals(expected, result);
    }

    @Test
    void testEmptyUpdate() {
        Map<String, Object> base = new HashMap<>();
        base.put("name", "Rocket Skates");
        base.put("price", 12.75);
        base.put("color", "yellow");

        Map<String, Object> update = new HashMap<>();

        Map<String, Object> expected = new HashMap<>(base);

        Map<String, Object> result = new HashMap<>(base);
        result.putAll(update);

        assertEquals(expected, result);
    }

    @Test
    void testBothEmpty() {
        Map<String, Object> base = new HashMap<>();
        Map<String, Object> update = new HashMap<>();

        Map<String, Object> expected = new HashMap<>();

        Map<String, Object> result = new HashMap<>(base);
        result.putAll(update);

        assertEquals(expected, result);
    }
}
