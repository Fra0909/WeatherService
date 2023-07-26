package com.weatherapp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherAppUI extends JFrame {

    private JLabel cityLabel;
    private JTextField cityField;
    private JTextArea weatherInfoArea;
    private JButton getWeatherButton;

    private final ObjectMapper objectMapper;

    public WeatherAppUI() {
        objectMapper = new ObjectMapper();
        initComponents();
    }

    private void initComponents() {
        setTitle("Weather App");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        cityLabel = new JLabel("Enter City:");
        cityLabel.setBounds(20, 20, 100, 30);
        add(cityLabel);

        cityField = new JTextField();
        cityField.setBounds(120, 20, 200, 30);
        add(cityField);

        getWeatherButton = new JButton("Get Weather");
        getWeatherButton.setBounds(150, 70, 120, 30);
        getWeatherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = cityField.getText();
                if (!city.isEmpty()) {
                    try {
                        WeatherData weatherData = getWeatherData(city);
                        displayWeatherInfo(weatherData);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(WeatherAppUI.this, "Error fetching weather data!");
                    }
                }
            }
        });
        add(getWeatherButton);

        weatherInfoArea = new JTextArea();
        weatherInfoArea.setBounds(20, 120, 360, 130);
        add(weatherInfoArea);
    }

    private WeatherData getWeatherData(String city) throws IOException {
        WeatherController wc = new WeatherController();
        return wc.getWeather(city);
    }

    private void displayWeatherInfo(WeatherData weatherData) {
        String weatherInfo = "City: " + weatherData.getName() + "\n"
                + "Temperature: " + weatherData.getMain().getTemp() + "°C\n"
                + "Description: " + weatherData.getWeather().get(0).getDescription() + "\n"
                + "Humidity: " + weatherData.getMain().getHumidity() + "%\n"
                + "Wind Speed: " + weatherData.getWind().getSpeed() + " m/s\n"
                + "Clouds: " + weatherData.getClouds().getAll() + "%";
        weatherInfoArea.setText(weatherInfo);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WeatherAppUI().setVisible(true);
            }
        });
    }
}
