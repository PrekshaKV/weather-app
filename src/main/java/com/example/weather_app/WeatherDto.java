package com.example.weather_app;

public class WeatherDto {
	private String city;
    private double tempC;
    private int humidity;
    private double windMps;
    private String description;
    private String iconUrl;

    public WeatherDto() {}

    public WeatherDto(String city, double tempC, int humidity, double windMps, String description, String iconUrl) {
        this.city = city;
        this.tempC = tempC;
        this.humidity = humidity;
        this.windMps = windMps;
        this.description = description;
        this.iconUrl = iconUrl;
    }

    public String getCity() { return city; }
    public double getTempC() { return tempC; }
    public int getHumidity() { return humidity; }
    public double getWindMps() { return windMps; }
    public String getDescription() { return description; }
    public String getIconUrl() { return iconUrl; }

    public void setCity(String city) { this.city = city; }
    public void setTempC(double tempC) { this.tempC = tempC; }
    public void setHumidity(int humidity) { this.humidity = humidity; }
    public void setWindMps(double windMps) { this.windMps = windMps; }
    public void setDescription(String description) { this.description = description; }
    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }
}
