import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class FlippingBitsGameTest {

    @Test
    void testSolved() {
        FlippingBitsGame game = new FlippingBitsGame();
        int[][] a = {{1, 0, 1}, {0, 1, 0}, {1, 1, 1}};
        int[][] b = {{1, 0, 1}, {0, 1, 0}, {1, 1, 1}};
        assertTrue(game.solved(a, b));

        b[0][0] = 0;
        assertFalse(game.solved(a, b));
    }

    @Test
    void testFlipRow() {
        FlippingBitsGame game = new FlippingBitsGame();
        game.n = 3;
        game.grid = new int[][]{{1, 0, 1}, {0, 1, 0}, {1, 1, 1}};
        game.flipRow(1);
        assertArrayEquals(new int[]{1, 0, 1}, game.grid[0]);
        assertArrayEquals(new int[]{1, 0, 1}, game.grid[1]);
        assertArrayEquals(new int[]{1, 1, 1}, game.grid[2]);
    }

    @Test
    void testFlipCol() {
        FlippingBitsGame game = new FlippingBitsGame();
        game.n = 3;
        game.grid = new int[][]{{1, 0, 1}, {0, 1, 0}, {1, 1, 1}};
        game.flipCol(1);
        assertArrayEquals(new int[]{1, 1, 1}, game.grid[0]);
        assertArrayEquals(new int[]{0, 0, 0}, game.grid[1]);
        assertArrayEquals(new int[]{1, 0, 1}, game.grid[2]);

    }


    @Test
    void testStartNewGame_EnsuresDifferentStartAndTarget() {
        FlippingBitsGame game = new FlippingBitsGame();
        game.startNewGame();
        assertFalse(game.solved(game.grid, game.target));
    }


    @Test
    void testStartNewGame_GridAndTargetInitialized() {
        FlippingBitsGame game = new FlippingBitsGame();
        game.startNewGame();
        assertNotNull(game.grid);
        assertNotNull(game.target);
        assertEquals(game.n, game.grid.length);
        assertEquals(game.n, game.target.length);
    }

    @Test
    void testStartNewGame_NChanges() {
        FlippingBitsGame game = new FlippingBitsGame();
        int initialN = game.n;
        game.startNewGame();  // First game
        game.solved = true;
        game.startNewGame();  // Second game
        assertNotEquals(initialN, game.n); // Verify n has changed

        game.n = game.maxLevel; //set n to max level
        game.solved = true;
        game.startNewGame(); //Should reset to min level
        assertEquals(game.minLevel, game.n);
    }

}
