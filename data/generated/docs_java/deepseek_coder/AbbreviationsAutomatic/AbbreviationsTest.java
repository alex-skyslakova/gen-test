import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AbbreviationsTest {

    @Test
    public void testMinimumAbbreviationLength() throws IOException {
        Path path = Paths.get("days_of_week.txt");
        List<String> readAllLines = Files.readAllLines(path);

        // Test the first five lines
        for (int i = 0; i < 5; i++) {
            String line = readAllLines.get(i);
            if (line.length() == 0) {
                continue; // Skip blank lines
            }

            String[] days = line.split(" ");
            if (days.length != 7) {
                throw new RuntimeException("There aren't 7 days on line " + (i + 1));
            }

            int minAbbreviationLength = calculateMinimumAbbreviationLength(days);
            System.out.printf("Line %d: Minimum abbreviation length = %d\n", (i + 1), minAbbreviationLength);
        }
    }

    private int calculateMinimumAbbreviationLength(String[] days) {
        int len = 1;
        while (true) {
            Map<String, Integer> temp = new HashMap<>();
            for (String day : days) {
                String sd;
                if (len >= day.length()) {
                    sd = day;
                } else {
                    sd = day.substring(0, len);
                }
                Integer count = temp.getOrDefault(sd, 0);
                temp.put(sd, count + 1);
            }
            if (temp.size() == 7) {
                return len;
            }
            len++;
        }
    }

    @Test
    public void testBlankLine() throws IOException {
        Path path = Paths.get("days_of_week.txt");
        List<String> readAllLines = Files.readAllLines(path);

        // Find a blank line in the file
        for (String line : readAllLines) {
            if (line.length() == 0) {
                // Ensure that the blank line is handled correctly
                assertNull(calculateMinimumAbbreviationLength(new String[]{}));
                break;
            }
        }
    }

    @Test
    public void testInvalidLine() throws IOException {
        Path path = Paths.get("days_of_week.txt");
        List<String> readAllLines = Files.readAllLines(path);

        // Test a line with fewer than 7 days
        for (String line : readAllLines) {
            String[] days = line.split(" ");
            if (days.length < 7) {
                assertThrows(RuntimeException.class, () -> calculateMinimumAbbreviationLength(days));
                break;
            }
        }
    }

    @Test
    public void testUniqueDays() throws IOException {
        Path path = Paths.get("days_of_week.txt");
        List<String> readAllLines = Files.readAllLines(path);

        // Test a line with all unique days
        for (String line : readAllLines) {
            String[] days = line.split(" ");
            if (days.length == 7 && new HashSet<>(Arrays.asList(days)).size() == 7) {
                assertEquals(1, calculateMinimumAbbreviationLength(days));
                break;
            }
        }
    }
}
