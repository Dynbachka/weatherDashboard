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
        skyConditionTranslations.put("Overcast ", "Пасмурно");
        skyConditionTranslations.put("Overcast", "Пасмурно");
        skyConditionTranslations.put("Partly cloudy", "Переменная облачность");
        skyConditionTranslations.put("Cloudy ", "Облачно");
        skyConditionTranslations.put("Clear", "Ясно");
        skyConditionTranslations.put("Rain", "Дождь");
        skyConditionTranslations.put("Snow", "Снег");
        skyConditionTranslations.put("Fog", "Туман");
        skyConditionTranslations.put("Light rain", "Легкий дождь");
        skyConditionTranslations.put("Sunny", "Солнечно");
        skyConditionTranslations.put("Showers", "Ливень");
        skyConditionTranslations.put("Thunderstorms", "Гроза");
        skyConditionTranslations.put("Partly Cloudy ", "Переменная облачность");
        skyConditionTranslations.put("Moderate snow", "Небольшой снег");
        skyConditionTranslations.put("Light freezing rain", "Мокрый снег");
        skyConditionTranslations.put("Patchy rain nearby", "Местами дождь");
        skyConditionTranslations.put("Moderate rain", "Умеренный дождь");
        skyConditionTranslations.put("Light snow", "Легкий снег");
        skyConditionTranslations.put("Mist", "Туман");
        skyConditionTranslations.put("Freezing fog", "Морозный туман");
        skyConditionTranslations.put("Moderate or heavy snow showers", "Умеренный или сильный снегопад");
        skyConditionTranslations.put("Light snow showers", "Небольшой снежный дождь");

        
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
            ForecastData forecastData = restTemplate.getForObject(url, ForecastData.class);
            if (forecastData != null && forecastData.getForecast() != null) {
                for (ForecastData.ForecastDay day : forecastData.getForecast().getForecastDays()) {
                    String forecastCondition = day.getDay().getCondition().getText();
                    String translatedForecastCondition = translateSkyCondition(forecastCondition);
                    day.getDay().getCondition().setText(translatedForecastCondition);
                }
            }
            return forecastData;
        } catch (HttpClientErrorException e) {
            System.err.println("Error fetching forecast data: " + e.getMessage());
            return null;
        }
    }
}