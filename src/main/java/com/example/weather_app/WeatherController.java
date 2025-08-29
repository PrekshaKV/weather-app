package com.example.weather_app;

import com.example.weather_app.WeatherDto;
import com.example.weather_app.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {
	private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> byCity(@RequestParam String city) {
        try {
            WeatherDto dto = service.getByCity(city);
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(502).body("Upstream weather service error.");
        }
    }
}
