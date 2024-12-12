import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoxingTheCompassTest {

    @Test
    public void testGetPoint() {
        // Test cases based on the provided headings
        assertEquals("North", BoxingTheCompass.getPoint(0.0));
        assertEquals("North by east", BoxingTheCompass.getPoint(16.87));
        assertEquals("North-northeast", BoxingTheCompass.getPoint(16.88));
        assertEquals("North-northeast", BoxingTheCompass.getPoint(33.75));
        assertEquals("Northeast by north", BoxingTheCompass.getPoint(50.62));
        assertEquals("Northeast", BoxingTheCompass.getPoint(50.63));
        assertEquals("Northeast", BoxingTheCompass.getPoint(67.5));
        assertEquals("Northeast by east", BoxingTheCompass.getPoint(84.37));
        assertEquals("East-northeast", BoxingTheCompass.getPoint(84.38));
        assertEquals("East-northeast", BoxingTheCompass.getPoint(101.25));
        assertEquals("East by north", BoxingTheCompass.getPoint(118.12));
        assertEquals("East", BoxingTheCompass.getPoint(118.13));
        assertEquals("East", BoxingTheCompass.getPoint(135.0));
        assertEquals("East by south", BoxingTheCompass.getPoint(151.87));
        assertEquals("East-southeast", BoxingTheCompass.getPoint(151.88));
        assertEquals("East-southeast", BoxingTheCompass.getPoint(168.75));
        assertEquals("Southeast by east", BoxingTheCompass.getPoint(185.62));
        assertEquals("Southeast", BoxingTheCompass.getPoint(185.63));
        assertEquals("Southeast", BoxingTheCompass.getPoint(202.5));
        assertEquals("Southeast by south", BoxingTheCompass.getPoint(219.37));
        assertEquals("South-southeast", BoxingTheCompass.getPoint(219.38));
        assertEquals("South-southeast", BoxingTheCompass.getPoint(236.25));
        assertEquals("South by east", BoxingTheCompass.getPoint(253.12));
        assertEquals("South", BoxingTheCompass.getPoint(253.13));
        assertEquals("South", BoxingTheCompass.getPoint(270.0));
        assertEquals("South by west", BoxingTheCompass.getPoint(286.87));
        assertEquals("South-southwest", BoxingTheCompass.getPoint(286.88));
        assertEquals("South-southwest", BoxingTheCompass.getPoint(303.75));
        assertEquals("Southwest by south", BoxingTheCompass.getPoint(320.62));
        assertEquals("Southwest", BoxingTheCompass.getPoint(320.63));
        assertEquals("Southwest", BoxingTheCompass.getPoint(337.5));
        assertEquals("Southwest by west", BoxingTheCompass.getPoint(354.37));
        assertEquals("West-southwest", BoxingTheCompass.getPoint(354.38));
    }

    @Test
    public void testInitialUpper() {
        // Test the initialUpper function
        assertEquals("North", BoxingTheCompass.initialUpper("north"));
        assertEquals("East", BoxingTheCompass.initialUpper("east"));
        assertEquals("South", BoxingTheCompass.initialUpper("south"));
        assertEquals("West", BoxingTheCompass.initialUpper("west"));
    }
}
