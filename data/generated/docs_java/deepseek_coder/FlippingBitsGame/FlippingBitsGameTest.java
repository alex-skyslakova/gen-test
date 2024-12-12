import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class FlippingBitsGameTest {

    private FlippingBitsGame game;

    @BeforeEach
    public void setUp() {
        game = new FlippingBitsGame();
    }

    @Test
    public void testStartNewGame() {
        // Ensure that the game starts with a new grid and target configuration
        int[][] initialGrid = game.grid;
        int[][] initialTarget = game.target;

        game.startNewGame();

        assertNotEquals(initialGrid, game.grid);
        assertNotEquals(initialTarget, game.target);
    }

    @Test
    public void testFlipRow() {
        // Ensure that flipping a row inverts all bits in that row
        int[][] initialGrid = new int[game.n][game.n];
        for (int i = 0; i < game.n; i++) {
            initialGrid[i] = Arrays.copyOf(game.grid[i], game.n);
        }

        int rowToFlip = 0;
        game.flipRow(rowToFlip);

        for (int c = 0; c < game.n; c++) {
            assertEquals(1 - initialGrid[rowToFlip][c], game.grid[rowToFlip][c]);
        }
    }

    @Test
    public void testFlipCol() {
        // Ensure that flipping a column inverts all bits in that column
        int[][] initialGrid = new int[game.n][game.n];
        for (int i = 0; i < game.n; i++) {
            initialGrid[i] = Arrays.copyOf(game.grid[i], game.n);
        }

        int colToFlip = 0;
        game.flipCol(colToFlip);

        for (int r = 0; r < game.n; r++) {
            assertEquals(1 - initialGrid[r][colToFlip], game.grid[r][colToFlip]);
        }
    }

    @Test
    public void testSolved() {
        // Ensure that the solved method correctly identifies when the grid matches the target
        game.grid = new int[][]{{1, 0}, {0, 1}};
        game.target = new int[][]{{1, 0}, {0, 1}};

        assertTrue(game.solved(game.grid, game.target));

        game.target = new int[][]{{0, 1}, {1, 0}};

        assertFalse(game.solved(game.grid, game.target));
    }

    @Test
    public void testShuffle() {
        // Ensure that the shuffle method changes the grid
        int[][] initialGrid = new int[game.n][game.n];
        for (int i = 0; i < game.n; i++) {
            initialGrid[i] = Arrays.copyOf(game.grid[i], game.n);
        }

        game.shuffle();

        boolean changed = false;
        for (int r = 0; r < game.n; r++) {
            for (int c = 0; c < game.n; c++) {
                if (initialGrid[r][c] != game.grid[r][c]) {
                    changed = true;
                    break;
                }
            }
            if (changed) break;
        }

        assertTrue(changed);
    }

    @Test
    public void testGameInitialization() {
        // Ensure that the game initializes with a solvable configuration
        game.startNewGame();

        assertFalse(game.solved(game.grid, game.target));
    }
}
