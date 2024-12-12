package com.jamesdonnell.MACVendor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LookupTest {

    private Lookup lookup;

    @BeforeEach
    public void setUp() {
        lookup = new Lookup();
    }

    @Test
    public void testValidMACAddress() throws Exception {
        String macAddress = "44:38:39:ff:ef:57";
        String expectedManufacturer = "Cisco Systems, Inc";

        // Mock URL and HttpURLConnection
        URL url = mock(URL.class);
        HttpURLConnection connection = mock(HttpURLConnection.class);
        when(url.openConnection()).thenReturn(connection);
        when(connection.getInputStream()).thenReturn(new InputStreamReader(new java.io.ByteArrayInputStream(expectedManufacturer.getBytes())));

        // Mock BufferedReader
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        when(bufferedReader.readLine()).thenReturn(expectedManufacturer, null);

        // Call the method
        String result = lookup.get(macAddress);

        // Verify
        assertEquals(expectedManufacturer, result);
    }

    @Test
    public void testInvalidMACAddress() throws Exception {
        String macAddress = "00:00:00:00:00:00";
        String expectedResult = "N/A";

        // Mock URL and HttpURLConnection
        URL url = mock(URL.class);
        HttpURLConnection connection = mock(HttpURLConnection.class);
        when(url.openConnection()).thenReturn(connection);
        when(connection.getInputStream()).thenThrow(new FileNotFoundException());

        // Call the method
        String result = lookup.get(macAddress);

        // Verify
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNetworkError() throws Exception {
        String macAddress = "44:38:39:ff:ef:57";

        // Mock URL and HttpURLConnection
        URL url = mock(URL.class);
        HttpURLConnection connection = mock(HttpURLConnection.class);
        when(url.openConnection()).thenReturn(connection);
        when(connection.getInputStream()).thenThrow(new IOException());

        // Call the method
        String result = lookup.get(macAddress);

        // Verify
        assertNull(result);
    }
}
