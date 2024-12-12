import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LastFridaysTest {

    @Test
    void test2012() {
        String expectedOutput = "2012 Jan 27\n" +
                "2012 Feb 24\n" +
                "2012 Mar 30\n" +
                "2012 Apr 27\n" +
                "2012 May 25\n" +
                "2012 Jun 29\n" +
                "2012 Jul 27\n" +
                "2012 Aug 31\n" +
                "2012 Sep 28\n" +
                "2012 Oct 26\n" +
                "2012 Nov 30\n" +
                "2012 Dec 28\n";
        testYear(2012, expectedOutput);

    }

    @Test
    void test2023() {
        String expectedOutput = "2023 Jan 27\n" +
                "2023 Feb 24\n" +
                "2023 Mar 31\n" +
                "2023 Apr 28\n" +
                "2023 May 26\n" +
                "2023 Jun 30\n" +
                "2023 Jul 28\n" +
                "2023 Aug 25\n" +
                "2023 Sep 29\n" +
                "2023 Oct 27\n" +
                "2023 Nov 24\n" +
                "2023 Dec 29\n";

        testYear(2023, expectedOutput);
    }



    @Test
    void testLeapYear() {
        String expectedOutput = "2024 Jan 26\n" +
                "2024 Feb 23\n" +
                "2024 Mar 29\n" +
                "2024 Apr 26\n" +
                "2024 May 31\n" +
                "2024 Jun 28\n" +
                "2024 Jul 26\n" +
                "2024 Aug 30\n" +
                "2024 Sep 27\n" +
                "2024 Oct 25\n" +
                "2024 Nov 29\n" +
                "2024 Dec 27\n";
        testYear(2024, expectedOutput); // 2024 is a leap year
    }


    private void testYear(int year, String expectedOutput) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try {
            String[] args = {String.valueOf(year)};
            LastFridays.main(args);
        } catch (Exception e) {
            System.err.println("Error during test execution: " + e.getMessage());
        }


        assertEquals(expectedOutput, outputStream.toString());


        System.setOut(System.out); // Restore standard output

    }
}
