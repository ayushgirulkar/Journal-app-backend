package com.example.springpractice.api.responce;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse
{

    private Current current;

    @Setter
    @Getter
    public class Current
    {
        private int temperature;
    }
}






