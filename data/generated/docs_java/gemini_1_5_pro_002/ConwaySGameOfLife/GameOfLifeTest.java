import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameOfLifeTest {

    @Test
    void testLife_blinker() {
        String[] dish = {
                "_#_",
                "_#_",
                "_#_",
        };
        String[] expectedGen1 = {
                "___",
                "###",
                "___",
        };
        String[] expectedGen2 = {
                "_#_",
                "_#_",
                "_#_",
        };
        String[] expectedGen3 = {
                "___",
                "###",
                "___",
        };


        String[] gen1 = GameOfLife.life(dish);
        assertArrayEquals(expectedGen1, gen1);

        String[] gen2 = GameOfLife.life(gen1);
        assertArrayEquals(expectedGen2, gen2);
        
        String[] gen3 = GameOfLife.life(gen2);
        assertArrayEquals(expectedGen3, gen3);

    }

    @Test
    void testLife_block() {
        String[] dish = {
                "##",
                "##",
        };
        String[] expected = {
                "##",
                "##",
        };
        String[] nextGen = GameOfLife.life(dish);
        assertArrayEquals(expected, nextGen);
    }

    @Test
    void testLife_beehive() {
        String[] dish = {
                "___",
                "_##_",
                "#__#",
                "_##_",
                "___"
        };
         String[] expected = {
                "___",
                "_#_",
                "#__#",
                "_#_",
                "___"
        };
        String[] nextGen = GameOfLife.life(dish);
        assertArrayEquals(expected, nextGen);


    }


    @Test
    void testLife_empty() {
        String[] dish = {
                "___",
                "___",
                "___",
        };
        String[] expected = {
                "___",
                "___",
                "___",
        };
        String[] nextGen = GameOfLife.life(dish);
        assertArrayEquals(expected, nextGen);
    }

    @Test
    void testGetNeighbors() {
        assertEquals(3, GameOfLife.getNeighbors("###", null, null));
        assertEquals(3, GameOfLife.getNeighbors(null, "###", null));
        assertEquals(3, GameOfLife.getNeighbors(null, null, "###"));

        assertEquals(5, GameOfLife.getNeighbors("##", "#_#", "##"));
        assertEquals(8, GameOfLife.getNeighbors("###", "###", "###"));

        assertEquals(0, GameOfLife.getNeighbors(null, null, null));
        assertEquals(0, GameOfLife.getNeighbors("___", null, null));

    }


}
