package com.weatherapp;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final String API_KEY;
    private final HttpRequestHandler httpRequestHandler;
    public WeatherController(HttpRequestHandler httpRequestHandler) {
        API_KEY = System.getenv("API_KEY");
        this.httpRequestHandler = httpRequestHandler;
    }

    @GetMapping("/weather/{city}")
    public ResponseEntity<?> getWeather(@PathVariable String city) {
        try {
            String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", city.toLowerCase(), API_KEY);

            CloseableHttpResponse response = httpRequestHandler.executeGet(url);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String jsonString = EntityUtils.toString(entity);
                if (jsonString.contains("not found")) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City not found.");
                }
                WeatherData weatherData = ResponseParser.parseWeatherDataFromJson(jsonString);
                return ResponseEntity.ok(weatherData);
            }
        } catch (ClientProtocolException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error communicating with the weather service.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No response received from the weather service.");
    }
}
