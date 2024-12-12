import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LangtonTest {

    @Test
    void testRunAnt_smallGrid() {
        boolean[][] plane = Langton.runAnt(3, 3);
        assertTrue(plane[1][1]); // Initial position should be black
        assertFalse(plane[0][1]); // First step, move up, cell becomes white
    }

    @Test
    void testRunAnt_initialMovement() {
        boolean[][] plane = Langton.runAnt(5, 5);
        assertTrue(plane[2][2]); // Start black
        assertFalse(plane[1][2]); // Move up, white
        assertTrue(plane[1][3]); // Move right, black
        assertTrue(plane[2][3]); // Move down, black
        assertFalse(plane[2][2]); // Move left, white
    }


    @Test
    void testRunAnt_boundaryConditions() {
        boolean[][] plane = Langton.runAnt(3, 3);
        assertEquals(3, plane.length);
        assertEquals(3, plane[0].length);

        // Check that it stops at the boundary and doesn't throw exceptions
        Langton.runAnt(1, 1);
        Langton.runAnt(2, 2);

    }

    @Test
    void testRunAnt_allWhiteInitially(){
        boolean[][] plane = Langton.runAnt(5,5);
        int whiteCellsInitialCount = 0;

        //ensure all cells are initially white before runAnt changes values

        for(int y=0; y < 5; y++){
            for(int x=0; x< 5; x++){
                if(plane[y][x] == false){ // white cell
                    whiteCellsInitialCount++;
                }
            }
        }
         assertEquals(0, whiteCellsInitialCount-25); 
    }



}
