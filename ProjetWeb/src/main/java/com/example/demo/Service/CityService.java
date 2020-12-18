package com.example.demo.Service;

import org.springframework.stereotype.Service;

import java.util.List;

public interface CityService {

    public String getCityById(int city_id);
    public List<String> getAllCities();
    public List<String> getAllCitiesOne();
    public List<String> getAllCitiesTwo();
    public List<String> getAllCitiesThree();

}
