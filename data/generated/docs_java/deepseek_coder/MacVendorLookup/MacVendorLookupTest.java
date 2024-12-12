package com.jamesdonnell.MACVendor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LookupTest {

    @Mock
    private HttpURLConnection mockConnection;

    @Mock
    private BufferedReader mockReader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGet_SuccessfulLookup() throws Exception {
        String macAddress = "00:00:5e:00:53:00";
        String expectedManufacturer = "Intel Corporate";

        URL mockUrl = new URL(Lookup.baseURL + macAddress);
        when(mockConnection.getInputStream()).thenReturn(new InputStreamReader("Intel Corporate"));
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);

        HttpURLConnection realConnection = (HttpURLConnection) mockUrl.openConnection();
        realConnection = mockConnection;

        String result = Lookup.get(macAddress);
        assertEquals(expectedManufacturer, result);
    }

    @Test
    void testGet_MACAddressNotFound() throws Exception {
        String macAddress = "00:00:00:00:00:00";
        String expectedResult = "N/A";

        URL mockUrl = new URL(Lookup.baseURL + macAddress);
        when(mockConnection.getInputStream()).thenThrow(new IOException("File not found"));
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_NOT_FOUND);

        HttpURLConnection realConnection = (HttpURLConnection) mockUrl.openConnection();
        realConnection = mockConnection;

        String result = Lookup.get(macAddress);
        assertEquals(expectedResult, result);
    }

    @Test
    void testGet_NetworkOrAPIError() throws Exception {
        String macAddress = "00:00:5e:00:53:00";
        String expectedResult = null;

        URL mockUrl = new URL(Lookup.baseURL + macAddress);
        when(mockConnection.getInputStream()).thenThrow(new IOException("Network error"));
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_INTERNAL_ERROR);

        HttpURLConnection realConnection = (HttpURLConnection) mockUrl.openConnection();
        realConnection = mockConnection;

        String result = Lookup.get(macAddress);
        assertEquals(expectedResult, result);
    }
}
