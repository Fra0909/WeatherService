package com.weatherapp;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class WeatherControllerTest {

    static String mockResponse = "{\"coord\":{\"lon\":0,\"lat\":0},\"weather\":[{\"id\":0,\"main\":\"\",\"description\":\"\",\"icon\":\"\"}],\"base\":\"\",\"main\":{\"temp\":\"0\",\"feels_like\":0,\"temp_min\":0,\"temp_max\":0,\"pressure\":0,\"humidity\":0},\"visibility\":0,\"wind\":{\"speed\":0,\"deg\":0,\"gust\":0},\"clouds\":{\"all\":0},\"dt\":0,\"sys\":{\"type\":0,\"id\":0,\"country\":\"\",\"sunrise\":0,\"sunset\":0},\"timezone\":0,\"id\":0,\"name\":\"\",\"cod\":0,\"rain\":{\"1h\":0}}\n";

    @Mock
    HttpRequestHandler httpRequestHandler;

    @Mock
    CloseableHttpResponse httpResponse;

    @Mock
    HttpEntity httpEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetWeather() throws Exception {
        when(httpRequestHandler.executeGet(anyString())).thenReturn(httpResponse);
        when(httpResponse.getEntity()).thenReturn(httpEntity);
        when(httpEntity.getContent()).thenReturn(new ByteArrayInputStream(mockResponse.getBytes()));

        WeatherController weatherController = new WeatherController(httpRequestHandler);

        ResponseEntity<?> actualResult = weatherController.getWeather("London");

        assertEquals(HttpStatus.OK, actualResult.getStatusCode());
        assertTrue(actualResult.getBody() instanceof WeatherData);
    }
}
