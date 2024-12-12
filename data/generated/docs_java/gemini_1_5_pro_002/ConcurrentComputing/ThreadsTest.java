import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class ThreadsTest {

    @Test
    void testOutputContainsAllStrings() throws InterruptedException, BrokenBarrierException, TimeoutException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        CyclicBarrier barrier = new CyclicBarrier(3);
        Threads.DelayedMessagePrinter p1 = new Threads.DelayedMessagePrinter(barrier, "Enjoy");
        Threads.DelayedMessagePrinter p2 = new Threads.DelayedMessagePrinter(barrier, "Rosetta");
        Threads.DelayedMessagePrinter p3 = new Threads.DelayedMessagePrinter(barrier, "Code");
        
        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        Thread t3 = new Thread(p3);

        t1.start();
        t2.start();
        t3.start();

        barrier.await(1, TimeUnit.SECONDS); // Use timeout to avoid indefinite blocking

        String output = outputStream.toString();
        List<String> lines = Arrays.asList(output.split(System.lineSeparator()));

        assertTrue(lines.contains("Enjoy"));
        assertTrue(lines.contains("Rosetta"));
        assertTrue(lines.contains("Code"));

        System.setOut(System.out); // Restore standard output
    }


    @Test
    void testDelayedMessagePrinterRun() throws InterruptedException, BrokenBarrierException, TimeoutException {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      System.setOut(new PrintStream(outputStream));

      CyclicBarrier barrier = new CyclicBarrier(1);
      Threads.DelayedMessagePrinter printer = new Threads.DelayedMessagePrinter(barrier, "Test");
      
      Thread thread = new Thread(printer);
      thread.start();
      
      barrier.await(1, TimeUnit.SECONDS);
      String output = outputStream.toString().trim();

      assertEquals("Test", output);
      System.setOut(System.out);

    }
    
     @Test
     void testDelayedMessagePrinterConstructor(){
       CyclicBarrier barrier = new CyclicBarrier(1);
       Threads.DelayedMessagePrinter printer = new Threads.DelayedMessagePrinter(barrier,"Test Message");
       
       assertEquals(barrier, printer.barrier);
       assertEquals("Test Message", printer.msg);

     }
}
