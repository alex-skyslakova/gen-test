import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AbbreviationsTest {

    @Test
    void testMinimumAbbreviationLength() throws IOException {
        Path path = Paths.get("days_of_week.txt");
        List<String> readAllLines = Files.readAllLines(path);

        // Test the first five lines
        assertEquals(3, getMinimumAbbreviationLength(readAllLines.get(0)));
        assertEquals(2, getMinimumAbbreviationLength(readAllLines.get(1)));
        assertEquals(2, getMinimumAbbreviationLength(readAllLines.get(2)));
        assertEquals(2, getMinimumAbbreviationLength(readAllLines.get(3)));
        assertEquals(3, getMinimumAbbreviationLength(readAllLines.get(4)));
    }

    @Test
    void testEmptyLine() {
        assertEquals("", getMinimumAbbreviationLength(""));
    }

    @Test
    void testInvalidLine() {
        String invalidLine = "Sunday Monday Tuesday";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            getMinimumAbbreviationLength(invalidLine);
        });
        assertEquals("There aren't 7 days on line 1", exception.getMessage());
    }

    private int getMinimumAbbreviationLength(String line) {
        if (line.length() == 0) return 0;

        String[] days = line.split(" ");
        if (days.length != 7) throw new RuntimeException("There aren't 7 days on line 1");

        int len = 1;
        while (true) {
            boolean unique = true;
            for (int i = 0; i < days.length; i++) {
                for (int j = i + 1; j < days.length; j++) {
                    if (days[i].substring(0, Math.min(len, days[i].length()))
                            .equals(days[j].substring(0, Math.min(len, days[j].length())))) {
                        unique = false;
                        break;
                    }
                }
                if (!unique) break;
            }
            if (unique) return len;
            len++;
        }
    }
}
