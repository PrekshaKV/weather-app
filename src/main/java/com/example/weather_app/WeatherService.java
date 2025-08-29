package com.example.weather_app;

import com.example.weather_app.WeatherDto;
import com.example.weather_app.OpenWeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class WeatherService {
	  private final RestTemplate restTemplate = new RestTemplate();

	    @Value("${app.weather.apiKey:}")
	    private String apiKey;

	    public WeatherDto getByCity(String city) {
	        if (apiKey == null || apiKey.isBlank()) {
	            throw new IllegalStateException("OpenWeather API key is missing. Set OPENWEATHER_API_KEY environment variable.");
	        }
	        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
	        String url = "https://api.openweathermap.org/data/2.5/weather?q="
	                + encodedCity + "&appid=" + apiKey + "&units=metric";

	        try {
	            ResponseEntity<OpenWeatherResponse> resp =
	                    restTemplate.getForEntity(url, OpenWeatherResponse.class);

	            OpenWeatherResponse body = resp.getBody();
	            if (body == null || body.getMain() == null) {
	                throw new RuntimeException("Unexpected response from weather API");
	            }

	            String description = (body.getWeather() != null && !body.getWeather().isEmpty())
	                    ? body.getWeather().get(0).getDescription()
	                    : "N/A";
	            String iconCode = (body.getWeather() != null && !body.getWeather().isEmpty())
	                    ? body.getWeather().get(0).getIcon()
	                    : null;
	            String iconUrl = (iconCode != null)
	                    ? "https://openweathermap.org/img/wn/" + iconCode + "@2x.png"
	                    : null;

	            return new WeatherDto(
	                    body.getName() != null ? body.getName() : city,
	                    body.getMain().getTemp(),
	                    body.getMain().getHumidity(),
	                    body.getWind() != null ? body.getWind().getSpeed() : 0.0,
	                    description,
	                    iconUrl
	            );

	        } catch (HttpClientErrorException.NotFound e) {
	            throw new IllegalArgumentException("City not found: " + city);
	        }
	    }
}
