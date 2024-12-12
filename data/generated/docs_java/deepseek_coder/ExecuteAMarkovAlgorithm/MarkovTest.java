import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkovTest {

    @Test
    public void testRuleset1() throws IOException {
        String input = "I bought a B of As from T S.";
        String expectedOutput = "I bought a bag of apples from my brother.";
        String ruleset = "# This rules file is extracted from Wikipedia:\n" +
                "# http://en.wikipedia.org/wiki/Markov_Algorithm\n" +
                "A -> apple\n" +
                "B -> bag\n" +
                "S -> shop\n" +
                "T -> the\n" +
                "the shop -> my brother\n" +
                "a never used -> .terminating rule";
        String actualOutput = applyMarkovAlgorithm(input, ruleset);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testRuleset2() throws IOException {
        String input = "I bought a B of As from T S.";
        String expectedOutput = "I bought a bag of apples from T shop.";
        String ruleset = "A -> apple\n" +
                "B -> bag\n" +
                "S -> .shop\n" +
                "T -> the\n" +
                "the shop -> my brother\n" +
                "a never used -> .terminating rule";
        String actualOutput = applyMarkovAlgorithm(input, ruleset);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testRuleset3() throws IOException {
        String input = "I bought a B of As W my Bgage from T S.";
        String expectedOutput = "I bought a bag of apples with my money from T shop.";
        String ruleset = "A -> apple\n" +
                "WWWW -> with\n" +
                "Bgage -> ->.*\n" +
                "B -> bag\n" +
                "->.* -> money\n" +
                "W -> WW\n" +
                "S -> .shop\n" +
                "T -> the\n" +
                "the shop -> my brother\n" +
                "a never used -> .terminating rule";
        String actualOutput = applyMarkovAlgorithm(input, ruleset);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testRuleset4() throws IOException {
        String input = "_1111*11111_";
        String expectedOutput = "11111111111111111111";
        String ruleset = "_+1 -> _1+\n" +
                "1+1 -> 11+\n" +
                "1! -> !1\n" +
                ",! -> !+\n" +
                "_! -> _\n" +
                "1*1 -> x,@y\n" +
                "1x -> xX\n" +
                "X, -> 1,1\n" +
                "X1 -> 1X\n" +
                "_x -> _X\n" +
                ",x -> ,X\n" +
                "y1 -> 1y\n" +
                "y_ -> _\n" +
                "1@1 -> x,@y\n" +
                "1@_ -> @_\n" +
                ",@_ -> !_\n" +
                "++ -> +\n" +
                "_1 -> 1\n" +
                "1+_ -> 1\n" +
                "_+_ -> ";
        String actualOutput = applyMarkovAlgorithm(input, ruleset);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testRuleset5() throws IOException {
        String input = "000000A000000";
        String expectedOutput = "00011H1111000";
        String ruleset = "A0 -> 1B\n" +
                "0A1 -> C01\n" +
                "1A1 -> C11\n" +
                "0B0 -> A01\n" +
                "1B0 -> A11\n" +
                "B1 -> 1B\n" +
                "0C0 -> B01\n" +
                "1C0 -> B11\n" +
                "0C1 -> H01\n" +
                "1C1 -> H11";
        String actualOutput = applyMarkovAlgorithm(input, ruleset);
        assertEquals(expectedOutput, actualOutput);
    }

    private String applyMarkovAlgorithm(String input, String ruleset) {
        List<String[]> rules = new ArrayList<>();
        Pattern pattern = Pattern.compile("^([^#]*?)\\s+->\\s+(\\.?)(.*)");

        for (String rule : ruleset.split("\n")) {
            Matcher m = pattern.matcher(rule);
            if (m.find()) {
                String[] groups = new String[m.groupCount()];
                for (int j = 0; j < groups.length; j++)
                    groups[j] = m.group(j + 1);
                rules.add(groups);
            }
        }

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
