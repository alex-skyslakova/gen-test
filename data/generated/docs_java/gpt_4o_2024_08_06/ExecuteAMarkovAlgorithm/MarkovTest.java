import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkovTest {

    @Test
    public void testRuleset1() {
        List<String[]> rules = new ArrayList<>();
        rules.add(new String[]{"A", "", "apple"});
        rules.add(new String[]{"B", "", "bag"});
        rules.add(new String[]{"S", "", "shop"});
        rules.add(new String[]{"T", "", "the"});
        rules.add(new String[]{"the shop", "", "my brother"});
        rules.add(new String[]{"a never used", ".", "terminating rule"});

        String input = "I bought a B of As from T S.";
        String expectedOutput = "I bought a bag of apples from my brother.";
        assertEquals(expectedOutput, applyMarkovAlgorithm(input, rules));
    }

    @Test
    public void testRuleset2() {
        List<String[]> rules = new ArrayList<>();
        rules.add(new String[]{"A", "", "apple"});
        rules.add(new String[]{"B", "", "bag"});
        rules.add(new String[]{"S", ".", "shop"});
        rules.add(new String[]{"T", "", "the"});
        rules.add(new String[]{"the shop", "", "my brother"});
        rules.add(new String[]{"a never used", ".", "terminating rule"});

        String input = "I bought a B of As from T S.";
        String expectedOutput = "I bought a bag of apples from T shop.";
        assertEquals(expectedOutput, applyMarkovAlgorithm(input, rules));
    }

    @Test
    public void testRuleset3() {
        List<String[]> rules = new ArrayList<>();
        rules.add(new String[]{"A", "", "apple"});
        rules.add(new String[]{"WWWW", "", "with"});
        rules.add(new String[]{"Bgage", "", "->.*"});
        rules.add(new String[]{"B", "", "bag"});
        rules.add(new String[]{"->.*", "", "money"});
        rules.add(new String[]{"W", "", "WW"});
        rules.add(new String[]{"S", ".", "shop"});
        rules.add(new String[]{"T", "", "the"});
        rules.add(new String[]{"the shop", "", "my brother"});
        rules.add(new String[]{"a never used", ".", "terminating rule"});

        String input = "I bought a B of As W my Bgage from T S.";
        String expectedOutput = "I bought a bag of apples with my money from T shop.";
        assertEquals(expectedOutput, applyMarkovAlgorithm(input, rules));
    }

    @Test
    public void testRuleset4() {
        List<String[]> rules = new ArrayList<>();
        rules.add(new String[]{"_+1", "", "_1+"});
        rules.add(new String[]{"1+1", "", "11+"});
        rules.add(new String[]{"1!", "", "!1"});
        rules.add(new String[]{",!", "", "!+"});
        rules.add(new String[]{"_!", "", "_"});
        rules.add(new String[]{"1*1", "", "x,@y"});
        rules.add(new String[]{"1x", "", "xX"});
        rules.add(new String[]{"X,", "", "1,1"});
        rules.add(new String[]{"X1", "", "1X"});
        rules.add(new String[]{"_x", "", "_X"});
        rules.add(new String[]{",x", "", ",X"});
        rules.add(new String[]{"y1", "", "1y"});
        rules.add(new String[]{"y_", "", "_"});
        rules.add(new String[]{"1@1", "", "x,@y"});
        rules.add(new String[]{"1@_", "", "@_"});
        rules.add(new String[]{",@_", "", "!_"});
        rules.add(new String[]{"++", "", "+"});
        rules.add(new String[]{"_1", "", "1"});
        rules.add(new String[]{"1+_", "", "1"});
        rules.add(new String[]{"_+_", "", ""});

        String input = "_1111*11111_";
        String expectedOutput = "11111111111111111111";
        assertEquals(expectedOutput, applyMarkovAlgorithm(input, rules));
    }

    @Test
    public void testRuleset5() {
        List<String[]> rules = new ArrayList<>();
        rules.add(new String[]{"A0", "", "1B"});
        rules.add(new String[]{"0A1", "", "C01"});
        rules.add(new String[]{"1A1", "", "C11"});
        rules.add(new String[]{"0B0", "", "A01"});
        rules.add(new String[]{"1B0", "", "A11"});
        rules.add(new String[]{"B1", "", "1B"});
        rules.add(new String[]{"0C0", "", "B01"});
        rules.add(new String[]{"1C0", "", "B11"});
        rules.add(new String[]{"0C1", "", "H01"});
        rules.add(new String[]{"1C1", "", "H11"});

        String input = "000000A000000";
        String expectedOutput = "00011H1111000";
        assertEquals(expectedOutput, applyMarkovAlgorithm(input, rules));
    }

    private String applyMarkovAlgorithm(String input, List<String[]> rules) {
        String test = input;
        String copy = test;
        for (int j = 0; j < rules.size(); j++) {
            String[] c = rules.get(j);
            test = test.replace(c[0], c[2]);
            if (c[1].equals("."))
                break;
            if (!test.equals(copy)) {
                j = -1; // redo loop
                copy = test;
            }
        }
        return test;
    }
}
