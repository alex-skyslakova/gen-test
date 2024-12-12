import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class SingletonAppTest {

    @Test
    void testFirstInstanceRuns() throws IOException, InterruptedException {
        // Redirect System.out to capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));


        // Simulate first instance running.  Hold the socket open so the second instance can't bind
        ServerSocket serverSocket1 = new ServerSocket(SingletonApp.PORT, 10, InetAddress.getLocalHost());

        // Start the main method in a separate thread
        Thread thread = new Thread(() -> SingletonApp.main(new String[]{}));
        thread.start();
        thread.join(100); //give it a moment to start

        Assertions.assertTrue(outContent.toString().contains("Application is already running"));
        serverSocket1.close();

        // Restore System.out
        System.setOut(System.out);
    }



    @Test
    void testSecondInstanceExits() throws IOException, InterruptedException {

        // Redirect System.out to capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Simulate first instance running. We close this socket quickly to allow test app to start
        try (ServerSocket serverSocket1 = new ServerSocket(SingletonApp.PORT, 10, InetAddress.getLocalHost())) {
             Thread.sleep(50); // A small delay to let the socket bind
        }


        // Start the main method in a separate thread. This represents the second attempted launch.
         Thread thread = new Thread(() -> SingletonApp.main(new String[]{}));
         thread.start();
         thread.join(1000); // Wait for it to potentially bind


        Assertions.assertTrue(outContent.toString().contains("OK, only this instance is running"));

        // Restore System.out
        System.setOut(System.out);

    }
}
