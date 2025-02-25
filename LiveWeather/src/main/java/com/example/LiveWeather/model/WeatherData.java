package com.example.LiveWeather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherData {
    @JsonProperty("location")
    private Location location;

    @JsonProperty("current")
    private CurrentWeather current;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public CurrentWeather getCurrent() {
        return current;
    }

    public void setCurrent(CurrentWeather current) {
        this.current = current;
    }

    public static class Location {
        @JsonProperty("name")
        private String name;

        @JsonProperty("region")
        private String region;

        @JsonProperty("country")
        private String country;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }

    public static class CurrentWeather {
        @JsonProperty("temp_c")
        private double temperature;

        @JsonProperty("feelslike_c")
        private double feelsLike;

        @JsonProperty("humidity")
        private int humidity;

        @JsonProperty("wind_kph")
        private double windSpeed;

        @JsonProperty("condition")
        private Condition condition;

        public int getTemperature() {
            return (int) Math.round(temperature);
        }

        public int getFeelsLike() {
            return (int) Math.round(feelsLike);
        }

        

        public void setFeelsLike(double feelsLike) {
            this.feelsLike = feelsLike;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public double getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(double windSpeed) {
            this.windSpeed = windSpeed;
        }

        public Condition getCondition() {
            return condition;
        }

        public void setCondition(Condition condition) {
            this.condition = condition;
        }
    }

    @JsonProperty("condition")
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public static class Condition {
        @JsonProperty("text")
        private String text;

        @JsonProperty("icon")
        private String icon;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
} 