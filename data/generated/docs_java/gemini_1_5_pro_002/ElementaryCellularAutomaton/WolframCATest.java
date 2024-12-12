import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WolframCATest {

    @Test
    void testRules() {
        WolframCA ca = new WolframCA();

        // Test all 8 possible neighborhood configurations for rule 13 (0b00001101)
        ca.rule = findRuleIndex(13, ca.ruleSet);
        assertEquals((byte)1, ca.rules(0, 0, 0));
        assertEquals((byte)0, ca.rules(0, 0, 1));
        assertEquals((byte)1, ca.rules(0, 1, 0));
        assertEquals((byte)1, ca.rules(0, 1, 1));
        assertEquals((byte)0, ca.rules(1, 0, 0));
        assertEquals((byte)0, ca.rules(1, 0, 1));
        assertEquals((byte)0, ca.rules(1, 1, 0));
        assertEquals((byte)0, ca.rules(1, 1, 1));


        // Test all 8 possible neighborhood configurations for rule 30 (0b00011110)
        ca.rule = findRuleIndex(30, ca.ruleSet);
        assertEquals((byte)0, ca.rules(0, 0, 0));
        assertEquals((byte)1, ca.rules(0, 0, 1));
        assertEquals((byte)1, ca.rules(0, 1, 0));
        assertEquals((byte)1, ca.rules(0, 1, 1));
        assertEquals((byte)1, ca.rules(1, 0, 0));
        assertEquals((byte)0, ca.rules(1, 0, 1));
        assertEquals((byte)0, ca.rules(1, 1, 0));
        assertEquals((byte)0, ca.rules(1, 1, 1));

        // Test a rule not explicitly listed in example
        ca.rule = findRuleIndex(255, ca.ruleSet); // If it exists within ruleSet
        assertEquals((byte)1, ca.rules(0, 0, 0));
        assertEquals((byte)1, ca.rules(0, 0, 1));
        assertEquals((byte)1, ca.rules(0, 1, 0));
        assertEquals((byte)1, ca.rules(0, 1, 1));
        assertEquals((byte)1, ca.rules(1, 0, 0));
        assertEquals((byte)1, ca.rules(1, 0, 1));
        assertEquals((byte)1, ca.rules(1, 1, 0));
        assertEquals((byte)1, ca.rules(1, 1, 1));

    }

     private int findRuleIndex(int ruleToFind, int[] ruleSet) {
        for (int i = 0; i < ruleSet.length; i++) {
            if (ruleSet[i] == ruleToFind) {
                return i;
            }
        }
        return -1; // Or throw an exception if rule not found is an error condition.
    }



}
