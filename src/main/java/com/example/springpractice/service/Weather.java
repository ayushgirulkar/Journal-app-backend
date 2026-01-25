package com.example.springpractice.service;

import com.example.springpractice.api.responce.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Weather {
    private static final String apikey="a7ec84bba1221186a26aa5290342c287";

    public static final  String API="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeater(String city)
    {
        String newapi=API.replace("CITY",city).replace("API_KEY",apikey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(newapi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}
