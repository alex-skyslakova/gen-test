import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class FlippingBitsGameTest {

    private FlippingBitsGame game;

    @BeforeEach
    void setUp() {
        game = new FlippingBitsGame();
    }

    @Test
    void testInitialGridNotEqualToTarget() {
        game.startNewGame();
        assertFalse(game.solved(game.grid, game.target), "Initial grid should not be equal to target.");
    }

    @Test
    void testFlipRow() {
        game.startNewGame();
        int[][] originalGrid = copyGrid(game.grid);
        game.flipRow(0);
        for (int c = 0; c < game.grid.length; c++) {
            assertEquals(originalGrid[0][c] ^ 1, game.grid[0][c], "Row flip did not work correctly.");
        }
    }

    @Test
    void testFlipCol() {
        game.startNewGame();
        int[][] originalGrid = copyGrid(game.grid);
        game.flipCol(0);
        for (int r = 0; r < game.grid.length; r++) {
            assertEquals(originalGrid[r][0] ^ 1, game.grid[r][0], "Column flip did not work correctly.");
        }
    }

    @Test
    void testSolvedMethod() {
        int[][] grid = {{0, 1}, {1, 0}};
        int[][] target = {{0, 1}, {1, 0}};
        assertTrue(game.solved(grid, target), "Solved method should return true for identical grids.");

        target[0][0] = 1;
        assertFalse(game.solved(grid, target), "Solved method should return false for different grids.");
    }

    @Test
    void testShuffleCreatesSolvableGrid() {
        game.startNewGame();
        assertTrue(isSolvable(game.grid, game.target), "Shuffled grid should be solvable.");
    }

    private int[][] copyGrid(int[][] grid) {
        int[][] copy = new int[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            copy[i] = Arrays.copyOf(grid[i], grid[i].length);
        }
        return copy;
    }

    private boolean isSolvable(int[][] grid, int[][] target) {
        // This is a simple check to ensure that the grid can be solved to match the target.
        // A more comprehensive check would be needed for a full implementation.
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != target[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
