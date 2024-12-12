import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AbbreviationsTest {

    @Test
    void testUniqueAbbreviations() throws IOException {
        Path path = Files.createTempFile("days_of_week", ".txt");
        Files.writeString(path, """
                Sunday Monday Tuesday Wednesday Thursday Friday Saturday
                Sondag Maandag Dinsdag Woensdag Donderdag Vrydag Saterdag
                E_djelë E_hënë E_martë E_mërkurë E_enjte E_premte E_shtunë
                Ehud Segno Maksegno Erob Hamus Arbe Kedame
                Al_Ahad Al_Ithinin Al_Tholatha'a Al_Arbia'a Al_Kamis Al_Gomia'a Al_Sabit
                Sun Sun Sun Sun Sun Sun Sun
                """);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Abbreviations.main(new String[]{});

        String expectedOutput = """
                1  Sunday Monday Tuesday Wednesday Thursday Friday Saturday
                2  Sondag Maandag Dinsdag Woensdag Donderdag Vrydag Saterdag
                2  E_djelë E_hënë E_martë E_mërkurë E_enjte E_premte E_shtunë
                2  Ehud Segno Maksegno Erob Hamus Arbe Kedame
                3  Al_Ahad Al_Ithinin Al_Tholatha'a Al_Arbia'a Al_Kamis Al_Gomia'a Al_Sabit
                 ∞  Sun Sun Sun Sun Sun Sun Sun
                """;
        
        assertEquals(expectedOutput, outputStream.toString());

        Files.delete(path);


    }

    @Test
    void testBlankLine() throws IOException {
        Path path = Files.createTempFile("days_of_week", ".txt");
        Files.writeString(path, "\n");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Abbreviations.main(new String[]{});

        assertEquals("", outputStream.toString());
        Files.delete(path);
    }



    @Test
    void testInvalidLine() throws IOException {
        Path path = Files.createTempFile("days_of_week", ".txt");
        Files.writeString(path, "Sunday Monday Tuesday");

        assertThrows(RuntimeException.class, () -> Abbreviations.main(new String[]{}));

        Files.delete(path);

    }
}
