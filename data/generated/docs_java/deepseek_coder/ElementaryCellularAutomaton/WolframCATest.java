import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WolframCATest {

    @Test
    public void testRules() {
        WolframCA wolframCA = new WolframCA();

        // Test rule 30
        wolframCA.rule = 0; // Index for rule 30 in ruleSet
        assertEquals(1, wolframCA.rules(0, 0, 0));
        assertEquals(1, wolframCA.rules(0, 0, 1));
        assertEquals(1, wolframCA.rules(0, 1, 0));
        assertEquals(0, wolframCA.rules(0, 1, 1));
        assertEquals(1, wolframCA.rules(1, 0, 0));
        assertEquals(0, wolframCA.rules(1, 0, 1));
        assertEquals(0, wolframCA.rules(1, 1, 0));
        assertEquals(0, wolframCA.rules(1, 1, 1));

        // Test rule 45
        wolframCA.rule = 1; // Index for rule 45 in ruleSet
        assertEquals(1, wolframCA.rules(0, 0, 0));
        assertEquals(0, wolframCA.rules(0, 0, 1));
        assertEquals(1, wolframCA.rules(0, 1, 0));
        assertEquals(1, wolframCA.rules(0, 1, 1));
        assertEquals(0, wolframCA.rules(1, 0, 0));
        assertEquals(1, wolframCA.rules(1, 0, 1));
        assertEquals(1, wolframCA.rules(1, 1, 0));
        assertEquals(0, wolframCA.rules(1, 1, 1));
    }

    @Test
    public void testDrawCa() {
        WolframCA wolframCA = new WolframCA();
        Graphics2D g = null; // Mocked or real Graphics2D object can be used here

        // Initial state setup
        wolframCA.cells[0][wolframCA.cells[0].length / 2] = 1;

        // Call drawCa to update the cells
        wolframCA.drawCa(g);

        // Verify the next generation state
        for (int c = 1; c < wolframCA.cells[0].length - 1; c++) {
            byte lhs = wolframCA.cells[0][c - 1];
            byte mid = wolframCA.cells[0][c];
            byte rhs = wolframCA.cells[0][c + 1];
            byte expected = wolframCA.rules(lhs, mid, rhs);
            assertEquals(expected, wolframCA.cells[1][c]);
        }
    }

    @Test
    public void testDrawLegend() {
        WolframCA wolframCA = new WolframCA();
        Graphics2D g = null; // Mocked or real Graphics2D object can be used here

        // Test drawLegend with different rules
        wolframCA.rule = 0; // Index for rule 30 in ruleSet
        wolframCA.drawLegend(g);

        wolframCA.rule = 1; // Index for rule 45 in ruleSet
        wolframCA.drawLegend(g);

        // Additional assertions can be made based on the expected behavior of drawLegend
    }

    @Test
    public void testPaintComponent() {
        WolframCA wolframCA = new WolframCA();
        Graphics g = null; // Mocked or real Graphics object can be used here

        // Test paintComponent
        wolframCA.paintComponent(g);

        // Additional assertions can be made based on the expected behavior of paintComponent
    }
}
