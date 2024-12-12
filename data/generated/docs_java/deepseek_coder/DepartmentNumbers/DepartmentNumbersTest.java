import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DepartmentNumbersTest {

    @Test
    public void testValidCombinations() {
        // Expected valid combinations
        int[][] expectedCombinations = {
            {2, 3, 7}, {2, 4, 6}, {2, 6, 4}, {2, 7, 3},
            {4, 1, 7}, {4, 2, 6}, {4, 3, 5}, {4, 5, 3}, {4, 6, 2}, {4, 7, 1},
            {6, 1, 5}, {6, 2, 4}, {6, 4, 2}, {6, 5, 1}
        };

        // Capture the output of the main method
        StringBuilder output = new StringBuilder();
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                output.append((char) b);
            }
        }));

        // Run the main method
        DepartmentNumbers.main(new String[]{});

        // Split the output into lines
        String[] lines = output.toString().split("\n");

        // Check the number of valid combinations
        int count = Integer.parseInt(lines[lines.length - 1].split(" ")[0]);
        assertEquals(expectedCombinations.length, count, "Number of valid combinations does not match expected count.");

        // Check each valid combination
        for (int i = 2; i < lines.length - 1; i++) {
            String[] parts = lines[i].trim().split("\\s+");
            int police = Integer.parseInt(parts[0]);
            int sanitation = Integer.parseInt(parts[1]);
            int fire = Integer.parseInt(parts[2]);

            // Ensure the combination is in the expected list
            boolean found = false;
            for (int[] expected : expectedCombinations) {
                if (police == expected[0] && sanitation == expected[1] && fire == expected[2]) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "Combination " + police + ", " + sanitation + ", " + fire + " is not expected.");
        }
    }

    @Test
    public void testPoliceNumberIsEven() {
        // Capture the output of the main method
        StringBuilder output = new StringBuilder();
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                output.append((char) b);
            }
        }));

        // Run the main method
        DepartmentNumbers.main(new String[]{});

        // Split the output into lines
        String[] lines = output.toString().split("\n");

        // Check each valid combination
        for (int i = 2; i < lines.length - 1; i++) {
            String[] parts = lines[i].trim().split("\\s+");
            int police = Integer.parseInt(parts[0]);
            assertTrue(police % 2 == 0, "Police number is not even: " + police);
        }
    }

    @Test
    public void testNumbersAreUnique() {
        // Capture the output of the main method
        StringBuilder output = new StringBuilder();
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                output.append((char) b);
            }
        }));

        // Run the main method
        DepartmentNumbers.main(new String[]{});

        // Split the output into lines
        String[] lines = output.toString().split("\n");

        // Check each valid combination
        for (int i = 2; i < lines.length - 1; i++) {
            String[] parts = lines[i].trim().split("\\s+");
            int police = Integer.parseInt(parts[0]);
            int sanitation = Integer.parseInt(parts[1]);
            int fire = Integer.parseInt(parts[2]);

            assertNotEquals(police, sanitation, "Police and Sanitation numbers are the same: " + police);
            assertNotEquals(police, fire, "Police and Fire numbers are the same: " + police);
            assertNotEquals(sanitation, fire, "Sanitation and Fire numbers are the same: " + sanitation);
        }
    }

    @Test
    public void testNumbersAddUpToTwelve() {
        // Capture the output of the main method
        StringBuilder output = new StringBuilder();
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                output.append((char) b);
            }
        }));

        // Run the main method
        DepartmentNumbers.main(new String[]{});

        // Split the output into lines
        String[] lines = output.toString().split("\n");

        // Check each valid combination
        for (int i = 2; i < lines.length - 1; i++) {
            String[] parts = lines[i].trim().split("\\s+");
            int police = Integer.parseInt(parts[0]);
            int sanitation = Integer.parseInt(parts[1]);
            int fire = Integer.parseInt(parts[2]);

            assertEquals(12, police + sanitation + fire, "Numbers do not add up to 12: " + police + ", " + sanitation + ", " + fire);
        }
    }
}
