import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkovTest {

    @Test
    void testRuleset1() throws IOException {
        String expectedOutput = "I bought a bag of apples from my brother.";
        testMarkovAlgorithm("markov_rules1.txt", "markov_tests1.txt", expectedOutput);
    }

    @Test
    void testRuleset2() throws IOException {
        String expectedOutput = "I bought a bag of apples from T shop.";
        testMarkovAlgorithm("markov_rules2.txt", "markov_tests2.txt", expectedOutput);
    }


    @Test
    void testRuleset3() throws IOException {
        String expectedOutput = "I bought a bag of apples with my money from T shop.";
        testMarkovAlgorithm("markov_rules3.txt", "markov_tests3.txt", expectedOutput);
    }

    @Test
    void testRuleset4() throws IOException {
        String expectedOutput = "11111111111111111111";
        testMarkovAlgorithm("markov_rules4.txt", "markov_tests4.txt", expectedOutput);
    }


    @Test
    void testRuleset5() throws IOException {
        String expectedOutput = "00011H1111000";
        testMarkovAlgorithm("markov_rules5.txt", "markov_tests5.txt", expectedOutput);

    }





    private void testMarkovAlgorithm(String rulesPath, String testPath, String expectedOutput) throws IOException {

        List<String[]> rules = readRules(rulesPath);
        List<String> tests = readTests(testPath);
        String test = tests.get(0);

       String result =  Markov.markovAlgorithm(rules, test);


        assertEquals(expectedOutput, result);
    }



    private List<String> readTests(String path)
            throws IOException {
        return Files.readAllLines(Paths.get(path));
    }

    private List<String[]> readRules(String path)
            throws IOException {
        String ls = System.lineSeparator();
        String lines = new String(Files.readAllBytes(Paths.get(path)));
        List<String[]> rules = new java.util.ArrayList<>();
        for (String line : lines.split(ls + ls))
            rules.add(line.split(ls));
        return rules;
    }
}
