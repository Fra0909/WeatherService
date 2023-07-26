package com.weatherapp;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseParser {

    public static WeatherData parseWeatherDataFromJson(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, WeatherData.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}