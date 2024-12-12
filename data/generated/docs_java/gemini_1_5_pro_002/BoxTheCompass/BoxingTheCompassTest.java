import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoxingTheCompassTest {

    @Test
    void getPoint_North() {
        assertEquals("north", BoxingTheCompass.getPoint(0.0));
    }

    @Test
    void getPoint_NorthByEast() {
        assertEquals("north by east", BoxingTheCompass.getPoint(16.87));
        assertEquals("north by east", BoxingTheCompass.getPoint(16.88));
    }

    @Test
    void getPoint_NorthNorthEast() {
        assertEquals("north-northeast", BoxingTheCompass.getPoint(33.75));
    }

    @Test
    void getPoint_NortheastByNorth() {
        assertEquals("northeast by north", BoxingTheCompass.getPoint(50.62));
        assertEquals("northeast by north", BoxingTheCompass.getPoint(50.63));

    }
        @Test
    void getPoint_Northeast() {
        assertEquals("northeast", BoxingTheCompass.getPoint(67.5));
    }


    @Test
    void getPoint_NortheastByEast() {
        assertEquals("northeast by east", BoxingTheCompass.getPoint(84.37));
        assertEquals("northeast by east", BoxingTheCompass.getPoint(84.38));

    }
       @Test
    void getPoint_EastNortheast() {
        assertEquals("east-northeast", BoxingTheCompass.getPoint(101.25));
    }
       @Test
    void getPoint_EastByNorth() {
        assertEquals("east by north", BoxingTheCompass.getPoint(118.12));
                assertEquals("east by north", BoxingTheCompass.getPoint(118.13));

    }
    // ... (tests for all other directions)

    @Test
    void getPoint_NorthWestByWest() {
        assertEquals("northwest by west", BoxingTheCompass.getPoint(320.62));
        assertEquals("northwest by west", BoxingTheCompass.getPoint(320.63));

    }
       @Test
    void getPoint_NorthWest() {
        assertEquals("northwest", BoxingTheCompass.getPoint(337.5));
    }
    @Test
    void getPoint_NorthWestByNorth() {
        assertEquals("northwest by north", BoxingTheCompass.getPoint(354.37));
        assertEquals("northwest by north", BoxingTheCompass.getPoint(354.38));
    }


    @Test
    void initialUpper() {
        assertEquals("Test", BoxingTheCompass.initialUpper("test"));
        assertEquals("TestTest", BoxingTheCompass.initialUpper("testTest"));
        assertEquals("", BoxingTheCompass.initialUpper(""));

    }



}
