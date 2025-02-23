package com.example.LiveWeather.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import com.example.LiveWeather.model.WeatherData;
import com.example.LiveWeather.model.ForecastData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class WeatherService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    private final RestTemplate restTemplate;

    @Value("${weatherapi.key}")
    private String apiKey;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherData getWeatherByCity(String city) {
        String url = "http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city;
        try {
            WeatherData weatherData = restTemplate.getForObject(url, WeatherData.class);
            if (weatherData != null && weatherData.getCurrent() != null) {
                weatherData.getCurrent().setFeelsLike(weatherData.getCurrent().getFeelsLike());
            }
            return weatherData;
        } catch (HttpClientErrorException e) {
            System.err.println("Error fetching weather data: " + e.getMessage());
            return null;
        }
    }

    public ForecastData getForecastByCity(String city) {
        String url = "http://api.weatherapi.com/v1/forecast.json?key=" + apiKey + "&q=" + city + "&days=10";
        try {
            return restTemplate.getForObject(url, ForecastData.class);
        } catch (HttpClientErrorException e) {
            System.err.println("Error fetching forecast data: " + e.getMessage());
            return null;
        }
    }
}