import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldTest {

    private static ExecutorService executorService;
    private static ServerSocket serverSocket;

    @BeforeAll
    public static void setUp() throws IOException {
        executorService = Executors.newSingleThreadExecutor();
        serverSocket = new ServerSocket(8080);

        executorService.submit(() -> {
            try {
                while (!serverSocket.isClosed()) {
                    Socket sock = serverSocket.accept();
                    new PrintWriter(sock.getOutputStream(), true).println("Goodbye, World!");
                    sock.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @AfterAll
    public static void tearDown() throws IOException {
        serverSocket.close();
        executorService.shutdown();
    }

    @Test
    public void testServerResponse() throws IOException {
        try (Socket socket = new Socket("localhost", 8080)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = reader.readLine();
            assertEquals("Goodbye, World!", response);
        }
    }
}
