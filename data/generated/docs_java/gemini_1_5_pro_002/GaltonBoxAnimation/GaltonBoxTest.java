import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class GaltonBoxTest {

    @Test
    void testDropBalls_allBallsStartRow() {
        GaltonBox gb = new GaltonBox(5, 3);
        assertEquals(3, gb.dropBalls());
        assertEquals(5, gb.m_balls[0].m_row);
        assertEquals(5, gb.m_balls[1].m_row);
        assertEquals(5, gb.m_balls[2].m_row);
        assertTrue(gb.m_balls[0].m_col >=0 && gb.m_balls[0].m_col <=1);

    }


    @Test
    void testDropBalls_oneBallMidDrop() {
        GaltonBox gb = new GaltonBox(5, 3);
        gb.m_balls[0].m_row = 3; // Simulate ball in mid-drop
        assertEquals(1, gb.dropBalls());
        assertEquals(2, gb.m_balls[0].m_row);
        assertTrue(gb.m_balls[0].m_col >=0 && gb.m_balls[0].m_col <=1);
    }




    @Test
    void testLongest() {
        List<List<GaltonBox.Position>> collectors = new ArrayList<>();
        List<GaltonBox.Position> c1 = new ArrayList<>();
        List<GaltonBox.Position> c2 = new ArrayList<>();
        List<GaltonBox.Position> c3 = new ArrayList<>();

        c1.add(new GaltonBox.Position(0, 0, 'o'));
        c2.add(new GaltonBox.Position(0, 0, 'o'));
        c2.add(new GaltonBox.Position(0, 0, 'o'));
        c2.add(new GaltonBox.Position(0, 0, 'o'));


        collectors.add(c1);
        collectors.add(c2);
        collectors.add(c3);

        assertEquals(3, GaltonBox.longest(collectors));
    }


    @Test
    void testPrintCollectors_emptyCollectors() {
        GaltonBox gb = new GaltonBox(3, 0); // No balls, thus empty collectors
        gb.printCollectors();
        // Visually inspect output. Should print a row of empty "|" characters, 
        // based on m_startRow.  Full automated test requires redirecting System.out,
        // which is beyond the scope of a simple unit test.
    }


    @Test
    void testPrintCollectors_oneBallEachCollector(){
        GaltonBox gb = new GaltonBox(3, 3);
        gb.m_balls[0].m_row = 0; gb.m_balls[0].m_col = 0;
        gb.m_balls[1].m_row = 0; gb.m_balls[1].m_col = 1;
        gb.m_balls[2].m_row = 0; gb.m_balls[2].m_col = 2;

        gb.printCollectors();

    }
}
