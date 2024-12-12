import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class KnightsTourTest {

    @Test
    void testNeighbors_emptyBoard() {
        KnightsTour.grid = new int[KnightsTour.base][KnightsTour.base];
        List<int[]> neighbors = KnightsTour.neighbors(2, 2);
        assertEquals(8, neighbors.size());
    }

    @Test
    void testNeighbors_fullBoard() {
        KnightsTour.grid = new int[KnightsTour.base][KnightsTour.base];
        for(int i=0; i<KnightsTour.base; i++) {
            Arrays.fill(KnightsTour.grid[i], 1);
        }
        List<int[]> neighbors = KnightsTour.neighbors(2, 2);
        assertTrue(neighbors.isEmpty());
    }

    @Test
    void testCountNeighbors_emptyBoard() {
        KnightsTour.grid = new int[KnightsTour.base][KnightsTour.base];
        int count = KnightsTour.countNeighbors(2, 2);
        assertEquals(8, count);
    }


    @Test
    void testCountNeighbors_fullBoard() {
        KnightsTour.grid = new int[KnightsTour.base][KnightsTour.base];
        for(int i=0; i<KnightsTour.base; i++) {
            Arrays.fill(KnightsTour.grid[i], 1);
        }
        int count = KnightsTour.countNeighbors(2, 2);
        assertEquals(0, count);
    }

    @Test
    void testOrphanDetected_true() {
        KnightsTour.grid = new int[KnightsTour.base][KnightsTour.base];
        KnightsTour.grid[2][2] = 1;
        KnightsTour.grid[3][4] = 2; // Creates an orphan at [1,3] or [5,3]

        assertTrue(KnightsTour.orphanDetected(2, 3, 4));
    }

    @Test
    void testOrphanDetected_false() {
        KnightsTour.grid = new int[KnightsTour.base][KnightsTour.base];
        KnightsTour.grid[2][2] = 1;
        KnightsTour.grid[4][3] = 2; 
        assertFalse(KnightsTour.orphanDetected(2, 4, 3)); 
    }


    @Test
    void testOrphanDetected_endGame() {
        KnightsTour.grid = new int[KnightsTour.base][KnightsTour.base];
        int total = (KnightsTour.base - 4) * (KnightsTour.base - 4);

        //Simulate near end game
        for(int i=0; i<total-1; i++) {
            int row = 2 + (i / (KnightsTour.base-4));
            int col = 2 + (i % (KnightsTour.base-4));
            KnightsTour.grid[row][col] = i + 1;
        }

        KnightsTour.grid[2][3] = total-1; // Example position
        KnightsTour.grid[4][2] = total; // Example last placed piece


       assertFalse(KnightsTour.orphanDetected(total, 4, 2));
    }
}

