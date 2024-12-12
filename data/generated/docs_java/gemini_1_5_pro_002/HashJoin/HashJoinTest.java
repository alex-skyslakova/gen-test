import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class HashJoinTest {

    @Test
    void testHashJoin_basic() {
        String[][] table1 = {{"27", "Jonah"}, {"18", "Alan"}, {"28", "Glory"},
                {"18", "Popeye"}, {"28", "Alan"}};

        String[][] table2 = {{"Jonah", "Whales"}, {"Jonah", "Spiders"},
                {"Alan", "Ghosts"}, {"Alan", "Zombies"}, {"Glory", "Buffy"}};

        List<String[][]> result = HashJoin.hashJoin(table1, 1, table2, 0);

        assertEquals(7, result.size());
        assertTrue(containsResult(result, new String[][]{{"27", "Jonah"}, {"Jonah", "Whales"}}));
        assertTrue(containsResult(result, new String[][]{{"27", "Jonah"}, {"Jonah", "Spiders"}}));
        assertTrue(containsResult(result, new String[][]{{"18", "Alan"}, {"Alan", "Ghosts"}}));
        assertTrue(containsResult(result, new String[][]{{"18", "Alan"}, {"Alan", "Zombies"}}));
        assertTrue(containsResult(result, new String[][]{{"28", "Glory"}, {"Glory", "Buffy"}}));
        assertTrue(containsResult(result, new String[][]{{"28", "Alan"}, {"Alan", "Ghosts"}}));
        assertTrue(containsResult(result, new String[][]{{"28", "Alan"}, {"Alan", "Zombies"}}));
    }

    @Test
    void testHashJoin_noMatch() {
        String[][] table1 = {{"27", "Jonah"}, {"18", "Alan"}};
        String[][] table2 = {{"Peter", "Whales"}, {"Paul", "Spiders"}};

        List<String[][]> result = HashJoin.hashJoin(table1, 1, table2, 0);

        assertTrue(result.isEmpty());
    }


    @Test
    void testHashJoin_emptyTable() {
        String[][] table1 = {{"27", "Jonah"}, {"18", "Alan"}};
        String[][] table2 = {};

        List<String[][]> result = HashJoin.hashJoin(table1, 1, table2, 0);
        assertTrue(result.isEmpty());

        result = HashJoin.hashJoin(table2, 0, table1, 1);
        assertTrue(result.isEmpty());


        String[][] emptyTable = {};

        result = HashJoin.hashJoin(emptyTable, 0, emptyTable, 0);

        assertTrue(result.isEmpty());

    }

    private boolean containsResult(List<String[][]> results, String[][] expected) {
        return results.stream().anyMatch(r -> Arrays.deepEquals(r, expected));
    }

}
