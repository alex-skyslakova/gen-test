import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class LettersTest {

    @Test
    public void testOutput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Letters.main(new String[]{});

        String output = outputStream.toString();

        // Check for uppercase letters A-Z
        Pattern uppercasePattern = Pattern.compile("Upper case: ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Matcher uppercaseMatcher = uppercasePattern.matcher(output);
        assertTrue(uppercaseMatcher.find(), "Output should contain uppercase letters A-Z");

        // Check for lowercase letters a-z
        Pattern lowercasePattern = Pattern.compile("Lower case: abcdefghijklmnopqrstuvwxyz");
        Matcher lowercaseMatcher = lowercasePattern.matcher(output);
        assertTrue(lowercaseMatcher.find(), "Output should contain lowercase letters a-z");


        // Check for the presence of "..." after both uppercase and lowercase
        assertTrue(output.contains("...\n"), "Output should contain '...' after the letters.");


    }
}
