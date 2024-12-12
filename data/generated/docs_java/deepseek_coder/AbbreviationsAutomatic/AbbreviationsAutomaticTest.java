import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AbbreviationsTest {

    @Test
    void testMain() throws IOException {
        // Create a temporary file with the test data
        Path tempFile = Files.createTempFile("test_days_of_week", ".txt");
        String testData = "Sunday Monday Tuesday Wednesday Thursday Friday Saturday\n" +
                          "Sondag Maandag Dinsdag Woensdag Donderdag Vrydag Saterdag\n" +
                          "E_djelë E_hënë E_martë E_mërkurë E_enjte E_premte E_shtunë\n" +
                          "Ehud Segno Maksegno Erob Hamus Arbe Kedame\n" +
                          "Al_Ahad Al_Ithinin Al_Tholatha'a Al_Arbia'a Al_Kamis Al_Gomia'a Al_Sabit\n" +
                          "\n" + // Blank line
                          "Guiragui Yergou_shapti Yerek_shapti Tchorek_shapti Hink_shapti Ourpat Shapat";
        Files.write(tempFile, testData.getBytes());

        // Capture the output of the main method
        Abbreviations.main(new String[]{tempFile.toString()});

        // Clean up the temporary file
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testMinimumAbbreviationLength() {
        String[] days1 = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] days2 = {"Sondag", "Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrydag", "Saterdag"};
        String[] days3 = {"E_djelë", "E_hënë", "E_martë", "E_mërkurë", "E_enjte", "E_premte", "E_shtunë"};
        String[] days4 = {"Ehud", "Segno", "Maksegno", "Erob", "Hamus", "Arbe", "Kedame"};
        String[] days5 = {"Al_Ahad", "Al_Ithinin", "Al_Tholatha'a", "Al_Arbia'a", "Al_Kamis", "Al_Gomia'a", "Al_Sabit"};

        assertEquals(3, Abbreviations.findMinimumAbbreviationLength(days1));
        assertEquals(3, Abbreviations.findMinimumAbbreviationLength(days2));
        assertEquals(3, Abbreviations.findMinimumAbbreviationLength(days3));
        assertEquals(3, Abbreviations.findMinimumAbbreviationLength(days4));
        assertEquals(3, Abbreviations.findMinimumAbbreviationLength(days5));
    }

    @Test
    void testBlankLine() {
        String[] days = {};
        assertEquals(0, Abbreviations.findMinimumAbbreviationLength(days));
    }

    @Test
    void testNotSevenDays() {
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        assertThrows(RuntimeException.class, () -> Abbreviations.findMinimumAbbreviationLength(days));
    }

    @Test
    void testDuplicateDays() {
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Sunday"};
        assertEquals(Integer.MAX_VALUE, Abbreviations.findMinimumAbbreviationLength(days));
    }
}
