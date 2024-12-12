import static org.junit.Assert.*;
import org.junit.Test;

public class GameOfLifeTest {

    @Test
    public void testInitialGeneration() {
        String[] initialState = {
            "_#_",
            "_#_",
            "_#_"
        };
        String[] expectedState = {
            "___",
            "###",
            "___"
        };
        String[] nextState = GameOfLife.life(initialState);
        assertArrayEquals(expectedState, nextState);
    }

    @Test
    public void testSecondGeneration() {
        String[] initialState = {
            "___",
            "###",
            "___"
        };
        String[] expectedState = {
            "_#_",
            "_#_",
            "_#_"
        };
        String[] nextState = GameOfLife.life(initialState);
        assertArrayEquals(expectedState, nextState);
    }

    @Test
    public void testThirdGeneration() {
        String[] initialState = {
            "_#_",
            "_#_",
            "_#_"
        };
        String[] expectedState = {
            "___",
            "###",
            "___"
        };
        String[] nextState = GameOfLife.life(initialState);
        assertArrayEquals(expectedState, nextState);
    }

    @Test
    public void testFourthGeneration() {
        String[] initialState = {
            "___",
            "###",
            "___"
        };
        String[] expectedState = {
            "_#_",
            "_#_",
            "_#_"
        };
        String[] nextState = GameOfLife.life(initialState);
        assertArrayEquals(expectedState, nextState);
    }

    @Test
    public void testLonelyCell() {
        String[] initialState = {
            "#__",
            "___",
            "___"
        };
        String[] expectedState = {
            "___",
            "___",
            "___"
        };
        String[] nextState = GameOfLife.life(initialState);
        assertArrayEquals(expectedState, nextState);
    }

    @Test
    public void testOvercrowdedCell() {
        String[] initialState = {
            "###",
            "###",
            "###"
        };
        String[] expectedState = {
            "_#_",
            "___",
            "_#_"
        };
        String[] nextState = GameOfLife.life(initialState);
        assertArrayEquals(expectedState, nextState);
    }

    @Test
    public void testBirthOfCell() {
        String[] initialState = {
            "___",
            "_##",
            "___"
        };
        String[] expectedState = {
            "_#_",
            "_#_",
            "___"
        };
        String[] nextState = GameOfLife.life(initialState);
        assertArrayEquals(expectedState, nextState);
    }

    @Test
    public void testBarrenCell() {
        String[] initialState = {
            "___",
            "___",
            "___"
        };
        String[] expectedState = {
            "___",
            "___",
            "___"
        };
        String[] nextState = GameOfLife.life(initialState);
        assertArrayEquals(expectedState, nextState);
    }
}
