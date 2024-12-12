import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MergeMapsTest {

    @Test
    void testMergeMaps_emptyBase_emptyUpdate() {
        Map<String, Object> base = new HashMap<>();
        Map<String, Object> update = new HashMap<>();
        Map<String, Object> expected = new HashMap<>();

        Map<String, Object> result = MergeMaps.mergeMaps(base, update);

        assertEquals(expected, result);
    }


    @Test
    void testMergeMaps_emptyBase_populatedUpdate() {
        Map<String, Object> base = new HashMap<>();
        Map<String, Object> update = new HashMap<>();
        update.put("price", 15.25);
        update.put("color", "red");
        update.put("year", 1974);
        Map<String, Object> expected = new HashMap<>(update);


        Map<String, Object> result = MergeMaps.mergeMaps(base, update);

        assertEquals(expected, result);
    }

    @Test
    void testMergeMaps_populatedBase_emptyUpdate() {
        Map<String, Object> base = new HashMap<>();
        base.put("name", "Rocket Skates");
        base.put("price", 12.75);
        base.put("color", "yellow");
        Map<String, Object> update = new HashMap<>();
        Map<String, Object> expected = new HashMap<>(base);


        Map<String, Object> result = MergeMaps.mergeMaps(base, update);

        assertEquals(expected, result);
    }


    @Test
    void testMergeMaps_populatedBase_populatedUpdate() {
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


        Map<String, Object> result = MergeMaps.mergeMaps(base, update);

        assertEquals(expected, result);

    }

    @Test
    void testMergeMaps_nullBase() {
        Map<String, Object> update = new HashMap<>();
        update.put("price", 15.25);

        Map<String, Object> result = MergeMaps.mergeMaps(null, update);

        assertNull(result);
    }

        @Test
    void testMergeMaps_nullUpdate() {
        Map<String, Object> base = new HashMap<>();
        base.put("name", "Rocket Skates");


        Map<String, Object> result = MergeMaps.mergeMaps(base, null);

        assertNull(result);
    }



}
