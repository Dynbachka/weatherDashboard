package com.example.LiveWeather.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:api_keys.txt")
public class AppConfig {
}
