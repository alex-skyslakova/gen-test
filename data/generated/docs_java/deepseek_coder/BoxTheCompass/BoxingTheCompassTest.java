import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoxingTheCompassTest {

    @Test
    public void testGetPoint() {
        BoxingTheCompass.buildPoints();

        // Test cases based on the provided headings
        assertEquals("North", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(0.0)));
        assertEquals("North by east", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(16.87)));
        assertEquals("North by east", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(16.88)));
        assertEquals("North-northeast", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(33.75)));
        assertEquals("Northeast by north", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(50.62)));
        assertEquals("Northeast by north", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(50.63)));
        assertEquals("Northeast", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(67.5)));
        assertEquals("Northeast by east", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(84.37)));
        assertEquals("Northeast by east", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(84.38)));
        assertEquals("East-northeast", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(101.25)));
        assertEquals("East by north", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(118.12)));
        assertEquals("East by north", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(118.13)));
        assertEquals("East", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(135.0)));
        assertEquals("East by south", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(151.87)));
        assertEquals("East by south", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(151.88)));
        assertEquals("East-southeast", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(168.75)));
        assertEquals("Southeast by east", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(185.62)));
        assertEquals("Southeast by east", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(185.63)));
        assertEquals("Southeast", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(202.5)));
        assertEquals("Southeast by south", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(219.37)));
        assertEquals("Southeast by south", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(219.38)));
        assertEquals("South-southeast", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(236.25)));
        assertEquals("South by east", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(253.12)));
        assertEquals("South by east", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(253.13)));
        assertEquals("South", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(270.0)));
        assertEquals("South by west", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(286.87)));
        assertEquals("South by west", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(286.88)));
        assertEquals("South-southwest", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(303.75)));
        assertEquals("Southwest by south", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(320.62)));
        assertEquals("Southwest by south", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(320.63)));
        assertEquals("Southwest", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(337.5)));
        assertEquals("Southwest by west", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(354.37)));
        assertEquals("Southwest by west", BoxingTheCompass.initialUpper(BoxingTheCompass.getPoint(354.38)));
    }

    @Test
    public void testInitialUpper() {
        assertEquals("North", BoxingTheCompass.initialUpper("north"));
        assertEquals("North by east", BoxingTheCompass.initialUpper("north by east"));
        assertEquals("North-northeast", BoxingTheCompass.initialUpper("north-northeast"));
        assertEquals("Northeast by north", BoxingTheCompass.initialUpper("northeast by north"));
        assertEquals("Northeast", BoxingTheCompass.initialUpper("northeast"));
        assertEquals("Northeast by east", BoxingTheCompass.initialUpper("northeast by east"));
        assertEquals("East-northeast", BoxingTheCompass.initialUpper("east-northeast"));
        assertEquals("East by north", BoxingTheCompass.initialUpper("east by north"));
        assertEquals("East", BoxingTheCompass.initialUpper("east"));
        assertEquals("East by south", BoxingTheCompass.initialUpper("east by south"));
        assertEquals("East-southeast", BoxingTheCompass.initialUpper("east-southeast"));
        assertEquals("Southeast by east", BoxingTheCompass.initialUpper("southeast by east"));
        assertEquals("Southeast", BoxingTheCompass.initialUpper("southeast"));
        assertEquals("Southeast by south", BoxingTheCompass.initialUpper("southeast by south"));
        assertEquals("South-southeast", BoxingTheCompass.initialUpper("south-southeast"));
        assertEquals("South by east", BoxingTheCompass.initialUpper("south by east"));
        assertEquals("South", BoxingTheCompass.initialUpper("south"));
        assertEquals("South by west", BoxingTheCompass.initialUpper("south by west"));
        assertEquals("South-southwest", BoxingTheCompass.initialUpper("south-southwest"));
        assertEquals("Southwest by south", BoxingTheCompass.initialUpper("southwest by south"));
        assertEquals("Southwest", BoxingTheCompass.initialUpper("southwest"));
        assertEquals("Southwest by west", BoxingTheCompass.initialUpper("southwest by west"));
        assertEquals("West-southwest", BoxingTheCompass.initialUpper("west-southwest"));
        assertEquals("West by south", BoxingTheCompass.initialUpper("west by south"));
        assertEquals("West", BoxingTheCompass.initialUpper("west"));
        assertEquals("West by north", BoxingTheCompass.initialUpper("west by north"));
        assertEquals("West-northwest", BoxingTheCompass.initialUpper("west-northwest"));
        assertEquals("Northwest by west", BoxingTheCompass.initialUpper("northwest by west"));
        assertEquals("Northwest", BoxingTheCompass.initialUpper("northwest"));
        assertEquals("Northwest by north", BoxingTheCompass.initialUpper("northwest by north"));
        assertEquals("North-northwest", BoxingTheCompass.initialUpper("north-northwest"));
        assertEquals("North by west", BoxingTheCompass.initialUpper("north by west"));
    }
}
