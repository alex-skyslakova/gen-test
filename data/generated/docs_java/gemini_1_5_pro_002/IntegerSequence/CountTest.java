import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class CountTest {

    @Test
    void testInitialOutput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Redirect System.out to capture printed output
        Count.main(new String[0]);


        String expectedOutputStart = "1\n2\n3\n4\n5\n"; // Check initial numbers

        String actualOutput = outputStream.toString();
        assertTrue(actualOutput.startsWith(expectedOutputStart), "Output should start with 1 to 5");
     
        //Restore System.out
        System.setOut(System.out);


    }



    @Test
    void testLargeNumberReached() throws InterruptedException {


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));


        Thread countThread = new Thread(() -> Count.main(new String[0]));
        countThread.start();
        Thread.sleep(100); // Allow some time for execution

        countThread.interrupt();  //Stop the infinite loop

        String output = outputStream.toString().trim();
        String[] numbers = output.split("\n");
        long lastNumber = Long.parseLong(numbers[numbers.length-1]);


        assertTrue(lastNumber > 1000, "Should reach a large number within the timeout");

         //Restore System.out
         System.setOut(System.out);


    }


}
