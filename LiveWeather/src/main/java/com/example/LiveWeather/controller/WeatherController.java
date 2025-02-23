package com.example.LiveWeather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.LiveWeather.service.WeatherService;
import com.example.LiveWeather.model.WeatherData;
import com.example.LiveWeather.model.ForecastData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public String showWeather(@RequestParam(value = "city", required = false) String city, Model model) {
        if (city != null && !city.isEmpty()) {
            WeatherData weatherData = weatherService.getWeatherByCity(city);
            model.addAttribute("weatherData", weatherData);

            ForecastData forecastData = weatherService.getForecastByCity(city);
            model.addAttribute("forecastData", forecastData);
        }
        return "weather";
    }

    public String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE");
        return date.format(formatter);
    }

    public String formatDateForMonth(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM");
        return date.format(formatter);
    }
}
