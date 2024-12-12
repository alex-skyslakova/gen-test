import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainTest {

    @Test
    public void testSuccessfulRequest() throws Exception {
        // Mock HttpClient
        HttpClient httpClient = mock(HttpClient.class);
        HttpClient.Builder httpClientBuilder = mock(HttpClient.Builder.class);
        when(httpClientBuilder.build()).thenReturn(httpClient);

        // Mock HttpRequest
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://www.rosettacode.org")).GET().build();

        // Mock HttpResponse
        HttpResponse<String> httpResponse = mock(HttpResponse.class);
        when(httpResponse.body()).thenReturn("Mocked response body");

        // Mock sendAsync method
        when(httpClient.sendAsync(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(CompletableFuture.completedFuture(httpResponse));

        // Capture System.out to verify printed output
        System.setOut(new java.io.PrintStream(System.out) {
            @Override
            public void println(String x) {
                // Verify the printed output
                assert x.equals("Mocked response body");
            }
        });

        // Call the main method
        Main.main(new String[]{});
    }

    @Test
    public void testFailedRequest() throws Exception {
        // Mock HttpClient
        HttpClient httpClient = mock(HttpClient.class);
        HttpClient.Builder httpClientBuilder = mock(HttpClient.Builder.class);
        when(httpClientBuilder.build()).thenReturn(httpClient);

        // Mock HttpRequest
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://www.rosettacode.org")).GET().build();

        // Mock sendAsync method to throw an exception
        when(httpClient.sendAsync(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(CompletableFuture.failedFuture(new RuntimeException("Request failed")));

        // Capture System.err to verify error output
        System.setErr(new java.io.PrintStream(System.err) {
            @Override
            public void println(String x) {
                // Verify the error output
                assert x.equals("java.lang.RuntimeException: Request failed");
            }
        });

        // Call the main method
        Main.main(new String[]{});
    }
}
