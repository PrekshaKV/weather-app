package com.example.weather_app;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherResponse {
    private Main main;
    private List<Weather> weather;
    private Wind wind;
    private String name; // city name

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main {
        private double temp;
        private int humidity;

        public double getTemp() { return temp; }
        public int getHumidity() { return humidity; }
        public void setTemp(double temp) { this.temp = temp; }
        public void setHumidity(int humidity) { this.humidity = humidity; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Weather {
        private String description;
        private String icon;

        public String getDescription() { return description; }
        public String getIcon() { return icon; }
        public void setDescription(String description) { this.description = description; }
        public void setIcon(String icon) { this.icon = icon; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Wind {
        private double speed;
        public double getSpeed() { return speed; }
        public void setSpeed(double speed) { this.speed = speed; }
    }

    public Main getMain() { return main; }
    public List<Weather> getWeather() { return weather; }
    public Wind getWind() { return wind; }
    public String getName() { return name; }

    public void setMain(Main main) { this.main = main; }
    public void setWeather(List<Weather> weather) { this.weather = weather; }
    public void setWind(Wind wind) { this.wind = wind; }
    public void setName(String name) { this.name = name; }
}
