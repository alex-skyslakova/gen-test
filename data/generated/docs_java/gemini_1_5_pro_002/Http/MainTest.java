import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void main_printsContentToConsole() throws Exception {
        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Mock HTTP client and response to control the returned content
        String mockResponseContent = "This is a mock response";
        HttpClient mockHttpClient = new HttpClient() {
            @Override
            public <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler) {
                return CompletableFuture.completedFuture(
                        (HttpResponse<T>) new HttpResponse<>() {
                            @Override
                            public int statusCode() {
                                return 200;
                            }

                            @Override
                            public HttpRequest request() {
                                return request;
                            }

                            @Override
                            public Optional<HttpResponse<T>> previousResponse() {
                                return Optional.empty();
                            }

                            @Override
                            public HttpHeaders headers() {
                                return null;
                            }

                            @Override
                            public T body() {
                                return (T) mockResponseContent;
                            }

                            @Override
                            public Optional<SSLSession> sslSession() {
                                return Optional.empty();
                            }

                            @Override
                            public URI uri() {
                                return request.uri();
                            }

                            @Override
                            public Version version() {
                                return null;
                            }
                        });
            }

        };


        // Use reflection to access and set the HttpClient in Main
        java.lang.reflect.Field httpClientField = Main.class.getDeclaredField("httpClient");
        httpClientField.setAccessible(true);
        httpClientField.set(null, mockHttpClient);


        //Simulate Main execution (note the static httpClient injection above)
        Main.main(new String[]{});




        // Restore System.out
        System.setOut(System.out);

        // Assert that the expected content was printed
        assertEquals(mockResponseContent + System.lineSeparator(), outContent.toString());

         // Reset the static field for subsequent tests (optional but good practice)
        httpClientField.set(null, null);
    }


}
