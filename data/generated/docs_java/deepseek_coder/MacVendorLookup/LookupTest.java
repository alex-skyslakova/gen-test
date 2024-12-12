package com.jamesdonnell.MACVendor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LookupTest {

    private HttpURLConnection mockConnection;
    private BufferedReader mockReader;

    @BeforeEach
    public void setUp() throws Exception {
        mockConnection = mock(HttpURLConnection.class);
        mockReader = mock(BufferedReader.class);

        // Mock the URL class to return our mock HttpURLConnection
        URL mockUrl = mock(URL.class);
        when(mockUrl.openConnection()).thenReturn(mockConnection);
        Lookup.class.getDeclaredField("baseURL").set(null, "http://mocked-api.com/");
    }

    @Test
    public void testGet_ValidMAC() throws Exception {
        // Arrange
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
        when(mockConnection.getInputStream()).thenReturn(new InputStreamReader("Apple, Inc.".getBytes()));
        when(mockReader.readLine()).thenReturn("Apple, Inc.", null);

        // Act
        String result = Lookup.get("00:00:00:00:00:00");

        // Assert
        assertEquals("Apple, Inc.", result);
    }

    @Test
    public void testGet_InvalidMAC() throws Exception {
        // Arrange
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_NOT_FOUND);

        // Act
        String result = Lookup.get("00:00:00:00:00:01");

        // Assert
        assertEquals("N/A", result);
    }

    @Test
    public void testGet_NetworkError() throws Exception {
        // Arrange
        when(mockConnection.getResponseCode()).thenThrow(new IOException("Network error"));

        // Act
        String result = Lookup.get("00:00:00:00:00:02");

        // Assert
        assertEquals(null, result);
    }
}
