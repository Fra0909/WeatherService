package com.weatherapp;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class WeatherControllerTest {

    static String mockResponse;
    @Mock
    private CloseableHttpClient httpClient;

    @InjectMocks
    private WeatherController weatherController;

    @BeforeAll
    static void setupMockResponse() {
        mockResponse = "{\"coord\":{\"lon\":0,\"lat\":0},\"weather\":[{\"id\":0,\"main\":\"\",\"description\":\"\",\"icon\":\"\"}],\"base\":\"\",\"main\":{\"temp\":\"0\",\"feels_like\":0,\"temp_min\":0,\"temp_max\":0,\"pressure\":0,\"humidity\":0},\"visibility\":0,\"wind\":{\"speed\":0,\"deg\":0,\"gust\":0},\"clouds\":{\"all\":0},\"dt\":0,\"sys\":{\"type\":0,\"id\":0,\"country\":\"\",\"sunrise\":0,\"sunset\":0},\"timezone\":0,\"id\":0,\"name\":\"\",\"cod\":0,\"rain\":{\"1h\":0}}\n";
    }
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetWeather() throws IOException, IOException {
        // Set up the mock behavior of the httpClient
        CloseableHttpResponse response = mock(CloseableHttpResponse.class);
        HttpEntity entity = mock(HttpEntity.class);
        when(httpClient.execute(any())).thenReturn(response);
        when(response.getEntity()).thenReturn(entity);
        InputStream inputStream = new ByteArrayInputStream(mockResponse.getBytes());
        when(entity.getContent()).thenReturn(inputStream);

        // Call the getWeather method
        WeatherData weatherData = weatherController.getWeather("London");

        // Assertions
        assertTrue(weatherData instanceof WeatherData);
    }

}
