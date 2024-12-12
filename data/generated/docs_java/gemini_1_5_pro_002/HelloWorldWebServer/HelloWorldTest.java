import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

public class HelloWorldTest {

    @Test
    void testServerResponse() throws IOException, InterruptedException {
        // Start the server in a separate thread
        Thread serverThread = new Thread(() -> {
            try {
                HelloWorld.main(new String[0]);
            } catch (IOException e) {
                fail("Server failed to start");
            }
        });
        serverThread.start();

        // Give the server some time to start
        Thread.sleep(1000);


        try (Socket socket = new Socket("localhost", 8080);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String response = in.readLine();
            assertEquals("Goodbye, World!", response);

        } catch (ConnectException e) {
            fail("Could not connect to server. Ensure the server is running and port 8080 is available.");
        } finally {
             serverThread.interrupt(); // Attempt to stop the server thread
        }
    }


    @Test
    void testServerHandlesMultipleClients() throws IOException, InterruptedException {

        // Start the server in a separate thread
        Thread serverThread = new Thread(() -> {
            try {
                HelloWorld.main(new String[0]);
            } catch (IOException e) {
                fail("Server failed to start");
            }
        });
        serverThread.start();

        Thread.sleep(1000);

        try {
            for (int i = 0; i < 5; i++) {  // Test with 5 clients
                try (Socket socket = new Socket("localhost", 8080);
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String response = in.readLine();
                    assertEquals("Goodbye, World!", response);
                }
            }
        } finally {
            serverThread.interrupt(); // Attempt to stop the server thread
        }

    }

}



