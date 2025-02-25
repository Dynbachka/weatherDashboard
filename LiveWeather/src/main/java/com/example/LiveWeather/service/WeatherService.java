package com.example.LiveWeather.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import com.example.LiveWeather.model.WeatherData;
import com.example.LiveWeather.model.ForecastData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    private final RestTemplate restTemplate;

    @Value("${weatherapi.key}")
    private String apiKey;

    private Map<String, String> skyConditionTranslations;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        initializeSkyConditionTranslations();
    }

    private void initializeSkyConditionTranslations() {
        skyConditionTranslations = new HashMap<>();
        skyConditionTranslations.put("Clear", "Ясно");
        skyConditionTranslations.put("Partly cloudy", "Частично облачно");
        skyConditionTranslations.put("Overcast", "Пасмурно");
        skyConditionTranslations.put("Rain", "Дождь");
        skyConditionTranslations.put("Snow", "Снег");
        skyConditionTranslations.put("Fog", "Туман");
        skyConditionTranslations.put("Light rain", "Легкий дождь");
        skyConditionTranslations.put("Sunny", "Солнечно");

    }

    public String translateSkyCondition(String condition) {
        return skyConditionTranslations.getOrDefault(condition, condition); 
    }

    public WeatherData getWeatherByCity(String city) {
        String url = "http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city;
        try {
            WeatherData weatherData = restTemplate.getForObject(url, WeatherData.class);
            if (weatherData != null && weatherData.getCurrent() != null) {
                String skyCondition = weatherData.getCurrent().getCondition().getText();
                String translatedCondition = translateSkyCondition(skyCondition);
                weatherData.getCurrent().getCondition().setText(translatedCondition);
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