package com.weatherapp;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class WeatherData {
    @JsonProperty("coord")
    private Coord coord;

    @JsonProperty("weather")
    private ArrayList<Weather> weather;

    @JsonProperty("base")
    private String base;

    @JsonProperty("main")
    private Main main;

    @JsonProperty("visibility")
    private int visibility;

    @JsonProperty("wind")
    private Wind wind;

    @JsonProperty("clouds")
    private Clouds clouds;

    @JsonProperty("dt")
    private int dt;

    @JsonProperty("sys")
    private Sys sys;

    @JsonProperty("timezone")
    private int timezone;

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("cod")
    private int cod;

    @JsonProperty("rain")
    private Rain rain;

    public Coord getCoord() {
        return coord;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }

    public int getVisibility() {
        return visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public int getDt() {
        return dt;
    }

    public Sys getSys() {
        return sys;
    }

    public int getTimezone() {
        return timezone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }

    public Rain getRain() {
        return rain;
    }
}

class Clouds {
    @JsonProperty("all")
    private int all;

    public int getAll() {
        return all;
    }
}

class Sys {
    @JsonProperty("type")
    public int type;
    @JsonProperty("id")
    public int id;
    @JsonProperty("country")
    public String country;
    @JsonProperty("sunrise")
    public int sunrise;
    @JsonProperty("sunset")
    public int sunset;


    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public int getSunrise() {
        return sunrise;
    }

    public int getSunset() {
        return sunset;
    }
}

class Coord {
    @JsonProperty("lon")
    private double lon;

    @JsonProperty("lat")
    private double lat;

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }
}

class Rain {
    @JsonProperty("1h")
    private double rain ;

    public double getRain() {
        return rain;
    }
}

class Wind {
    @JsonProperty("speed")
    public double speed;
    @JsonProperty("deg")
    public int deg;

    @JsonProperty("gust")
    public int gust;

    public double getSpeed() {
        return speed;
    }

    public int getDeg() {
        return deg;
    }

    public int getGust() {
        return gust;
    }
}

class Weather {
    @JsonProperty("id")
    public int id;
    @JsonProperty("main")
    public String main;
    @JsonProperty("description")
    public String description;
    @JsonProperty("icon")
    public String icon;

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}


class Main {
    @JsonProperty("temp")
    private double temp;

    @JsonProperty("feels_like")
    private double feelsLike;

    @JsonProperty("temp_min")
    private double tempMin;

    @JsonProperty("temp_max")
    private double tempMax;

    @JsonProperty("pressure")
    private int pressure;

    @JsonProperty("humidity")
    private int humidity;

    public String getTemp() {
        double celsius = temp - 273.15;
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(celsius);
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }
}
