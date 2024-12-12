import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MainTest {

    @Mock
    private HttpClient mockHttpClient;

    @Mock
    private HttpResponse<String> mockHttpResponse;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHttpRequest() throws Exception {
        // Arrange
        String expectedResponseBody = "Mocked response body";
        when(mockHttpResponse.body()).thenReturn(expectedResponseBody);
        CompletableFuture<HttpResponse<String>> future = CompletableFuture.completedFuture(mockHttpResponse);
        when(mockHttpClient.sendAsync(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(future);

        // Act
        var request = HttpRequest.newBuilder(URI.create("https://www.rosettacode.org"))
                .GET()
                .build();

        CompletableFuture<Void> result = mockHttpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString(Charset.defaultCharset()))
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);

        result.join();

        // Assert
        ArgumentCaptor<HttpRequest> requestCaptor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(mockHttpClient).sendAsync(requestCaptor.capture(), any(HttpResponse.BodyHandler.class));
        assertEquals("https://www.rosettacode.org", requestCaptor.getValue().uri().toString());
        assertEquals(expectedResponseBody, mockHttpResponse.body());
    }
}
