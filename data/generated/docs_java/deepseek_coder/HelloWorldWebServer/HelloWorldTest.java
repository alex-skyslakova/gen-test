import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldTest {

    private static ServerSocket serverSocket;
    private static Thread serverThread;

    @BeforeAll
    public static void setUp() throws IOException {
        // Start the server in a separate thread
        serverSocket = new ServerSocket(8080);
        serverThread = new Thread(() -> {
            try {
                while (true) {
                    Socket sock = serverSocket.accept();
                    new PrintWriter(sock.getOutputStream(), true).println("Goodbye, World!");
                    sock.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.start();
    }

    @AfterAll
    public static void tearDown() throws IOException {
        // Stop the server
        serverSocket.close();
        serverThread.interrupt();
    }

    @Test
    public void testServerResponse() throws IOException {
        // Create a URL connection to the server
        URL url = new URL("http://localhost:8080/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read the response from the server
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        connection.disconnect();

        // Verify the response
        assertEquals("Goodbye, World!", content.toString());
    }
}
