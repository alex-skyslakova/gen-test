import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class SundialTest {

    @Test
    void testSundialCalculations_LincolnIsland() {
        String input = "4.95\n150.5\n150\n";
        String expectedOutput = String.format("sine of latitude: 0.086\ndiff longitude: 0.500\n\n" +
                "Hour, sun hour angle, dial hour line angle from 6am to 6pm\n" +
                "HR= -6;  \t  HRA= -89.500;  \t  HLA= -8.229\n" +
                "HR= -5;  \t  HRA= -74.500;  \t  HLA= -6.857\n" +
                "HR= -4;  \t  HRA= -59.500;  \t  HLA= -5.486\n" +
                "HR= -3;  \t  HRA= -44.500;  \t  HLA= -4.114\n" +
                "HR= -2;  \t  HRA= -29.500;  \t  HLA= -2.743\n" +
                "HR= -1;  \t  HRA= -14.500;  \t  HLA= -1.371\n" +
                "HR=  0;  \t  HRA=  0.500;  \t  HLA= 0.043\n" +
                "HR=  1;  \t  HRA= 15.500;  \t  HLA= 1.457\n" +
                "HR=  2;  \t  HRA= 30.500;  \t  HLA= 2.871\n" +
                "HR=  3;  \t  HRA= 45.500;  \t  HLA= 4.286\n" +
                "HR=  4;  \t  HRA= 60.500;  \t  HLA= 5.700\n" +
                "HR=  5;  \t  HRA= 75.500;  \t  HLA= 7.114\n" +
                "HR=  6;  \t  HRA= 90.500;  \t  HLA= 8.529\n");


        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Sundial.main(new String[]{});

        assertEquals(expectedOutput, outContent.toString());

        System.setIn(System.in); // Restore System.in
        System.setOut(System.out); // Restore System.out
    }


    @Test
    void testSundialCalculations_ZeroLatitudeLongitudeMeridian() {
        String input = "0\n0\n0\n";
        String expectedOutput = String.format("sine of latitude: 0.000\ndiff longitude: 0.000\n\n" +
                "Hour, sun hour angle, dial hour line angle from 6am to 6pm\n" +
                "HR= -6;  \t  HRA= -90.000;  \t  HLA= -0.000\n" +
                "HR= -5;  \t  HRA= -75.000;  \t  HLA= -0.000\n" +
                "HR= -4;  \t  HRA= -60.000;  \t  HLA= -0.000\n" +
                "HR= -3;  \t  HRA= -45.000;  \t  HLA= -0.000\n" +
                "HR= -2;  \t  HRA= -30.000;  \t  HLA= -0.000\n" +
                "HR= -1;  \t  HRA= -15.000;  \t  HLA= -0.000\n" +
                "HR=  0;  \t  HRA=  0.000;  \t  HLA= 0.000\n" +
                "HR=  1;  \t  HRA= 15.000;  \t  HLA= 0.000\n" +
                "HR=  2;  \t  HRA= 30.000;  \t  HLA= 0.000\n" +
                "HR=  3;  \t  HRA= 45.000;  \t  HLA= 0.000\n" +
                "HR=  4;  \t  HRA= 60.000;  \t  HLA= 0.000\n" +
                "HR=  5;  \t  HRA= 75.000;  \t  HLA= 0.000\n" +
                "HR=  6;  \t  HRA= 90.000;  \t  HLA= 0.000\n");

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Sundial.main(new String[]{});

        assertEquals(expectedOutput, outContent.toString());

        System.setIn(System.in); // Restore System.in
        System.setOut(System.out); // Restore System.out
    }



}
