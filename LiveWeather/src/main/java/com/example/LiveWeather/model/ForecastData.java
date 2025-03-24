package com.example.LiveWeather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ForecastData {
    @JsonProperty("forecast")
    private Forecast forecast;

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    public static class Forecast {
        @JsonProperty("forecastday")
        private List<ForecastDay> forecastDays;

        public List<ForecastDay> getForecastDays() {
            return forecastDays;
        }

        public void setForecastDays(List<ForecastDay> forecastDays) {
            this.forecastDays = forecastDays;
        }
    }

    public static class ForecastDay {
        @JsonProperty("date")
        private LocalDate date;

        @JsonProperty("day")
        private Day day;

        @JsonProperty("hour")
        private List<Hour> hour;

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public Day getDay() {
            return day;
        }

        public void setDay(Day day) {
            this.day = day;
        }

        public List<Hour> getHour() {
            return hour;
        }

        public void setHour(List<Hour> hour) {
            this.hour = hour;
        }
    }

    public static class Day {
        @JsonProperty("maxtemp_c")
        private double maxTemp;

        @JsonProperty("mintemp_c")
        private double minTemp;

        @JsonProperty("condition")
        private Condition condition;

        public void setMaxTemp(double maxTemp) {
            this.maxTemp = maxTemp;
        }

        public void setMinTemp(double minTemp) {
            this.minTemp = minTemp;
        }

        public int getMaxTemp() {
            return (int) Math.round(maxTemp);
        }

        public int getMinTemp() {
            return (int) Math.round(minTemp);
        }

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

    public static class Hour {
        @JsonProperty("time")
        private String time;

        @JsonProperty("temp_c")
        private double temp;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }
    }
}
