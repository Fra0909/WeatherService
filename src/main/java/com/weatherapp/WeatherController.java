package com.weatherapp;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private static final String API_KEY = System.getenv("API_KEY");

    @GetMapping("/weather/{city}")
    public WeatherData getWeather(@PathVariable String city) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", city.toLowerCase(), API_KEY));

            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String jsonString = EntityUtils.toString(entity);
                if (jsonString.contains("not found")) {
                    return new WeatherData();
                }
                WeatherData wd = ResponseParser.parseWeatherDataFromJson(jsonString);
                System.out.println(wd);
                return wd;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
