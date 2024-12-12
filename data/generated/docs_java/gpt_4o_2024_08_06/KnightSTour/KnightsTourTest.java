import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class KnightsTourTest {

    @Test
    public void testSolveFromRandomStart() {
        KnightsTour.main(new String[]{});
        // Since the starting position is random, we can't predict the exact path.
        // However, we can check if the grid is filled correctly.
        int[][] grid = KnightsTour.getGrid();
        int total = KnightsTour.getTotal();
        int count = 0;

        for (int r = 2; r < 10; r++) {
            for (int c = 2; c < 10; c++) {
                if (grid[r][c] > 0) {
                    count++;
                }
            }
        }
        assertEquals(total, count, "The knight should visit every square exactly once.");
    }

    @Test
    public void testNeighborsFunction() {
        int[][] grid = new int[12][12];
        grid[4][4] = 1; // Starting point
        List<int[]> neighbors = KnightsTour.neighbors(4, 4);

        // A knight at (4,4) should have 8 possible moves on an empty board
        assertEquals(8, neighbors.size(), "The knight should have 8 possible moves from the center.");
    }

    @Test
    public void testCountNeighborsFunction() {
        int[][] grid = new int[12][12];
        grid[4][4] = 1; // Starting point
        int count = KnightsTour.countNeighbors(4, 4);

        // A knight at (4,4) should have 8 possible moves on an empty board
        assertEquals(8, count, "The knight should have 8 possible neighbors from the center.");
    }

    @Test
    public void testOrphanDetection() {
        int[][] grid = new int[12][12];
        grid[4][4] = 1; // Starting point
        boolean orphan = KnightsTour.orphanDetected(1, 4, 4);

        // Initially, there should be no orphan squares
        assertFalse(orphan, "There should be no orphan squares at the start.");
    }

    @Test
    public void testPrintResult() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        KnightsTour.main(new String[]{});
        String output = outContent.toString();

        // Check if the output contains a valid tour
        assertTrue(output.contains("1"), "Output should contain the starting position.");
        assertTrue(output.contains("64"), "Output should contain the last position.");
    }
}
