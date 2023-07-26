package com.weatherapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseParserTest {

    @Test
    public void responseGetsParsedToObject() {
        String mockResponse = "{\"coord\":{\"lon\":0,\"lat\":0},\"weather\":[{\"id\":0,\"main\":\"\",\"description\":\"\",\"icon\":\"\"}],\"base\":\"\",\"main\":{\"temp\":\"0\",\"feels_like\":0,\"temp_min\":0,\"temp_max\":0,\"pressure\":0,\"humidity\":0},\"visibility\":0,\"wind\":{\"speed\":0,\"deg\":0,\"gust\":0},\"clouds\":{\"all\":0},\"dt\":0,\"sys\":{\"type\":0,\"id\":0,\"country\":\"\",\"sunrise\":0,\"sunset\":0},\"timezone\":0,\"id\":0,\"name\":\"\",\"cod\":0,\"rain\":{\"1h\":0}}\n";

        WeatherData wd = ResponseParser.parseWeatherDataFromJson(mockResponse);

        assertEquals(0, wd.getCoord().getLon(), 0.0001);
        assertEquals(0, wd.getCoord().getLat(), 0.0001);

        assertEquals(0, wd.getWeather().get(0).getId());
        assertEquals("", wd.getWeather().get(0).getMain());
        assertEquals("", wd.getWeather().get(0).getDescription());
        assertEquals("", wd.getWeather().get(0).getIcon());

        assertEquals("", wd.getBase());

        assertEquals("-273.1", wd.getMain().getTemp());
        assertEquals(0, wd.getMain().getFeelsLike(), 0.0001);
        assertEquals(0, wd.getMain().getTempMin(), 0.0001);
        assertEquals(0, wd.getMain().getTempMax(), 0.0001);
        assertEquals(0, wd.getMain().getPressure());
        assertEquals(0, wd.getMain().getHumidity());

        assertEquals(0, wd.getVisibility());

        assertEquals(0, wd.getWind().getSpeed(), 0.0001);
        assertEquals(0, wd.getWind().getDeg());
        assertEquals(0, wd.getWind().getGust());

        assertEquals(0, wd.getClouds().getAll());

        assertEquals(0, wd.getDt());

        assertEquals(0, wd.getSys().getType());
        assertEquals(0, wd.getSys().getId());
        assertEquals("", wd.getSys().getCountry());
        assertEquals(0, wd.getSys().getSunrise());
        assertEquals(0, wd.getSys().getSunset());

        assertEquals(0, wd.getTimezone());
        assertEquals(0, wd.getId());
        assertEquals("", wd.getName());
        assertEquals(0, wd.getCod());
    }
}
