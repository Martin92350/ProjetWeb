package com.example.demo.Service;

import com.example.demo.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImp implements CityService{

    @Autowired
    CityRepository cityRepo;

    public String getCityById(int city_id){
        String cityName = cityRepo.getCityById(city_id);
        return cityName;
    }

    public List<String> getAllCities() {
        return cityRepo.getAllCities();
    }

    public List<String> getAllCitiesOne() {
        System.out.println("CITIES ONE : " + cityRepo.getAllCitiesOne());
        return cityRepo.getAllCitiesOne();
    }
    public List<String> getAllCitiesTwo() {
        System.out.println("CITIES TWO : " + cityRepo.getAllCitiesTwo());
        return cityRepo.getAllCitiesTwo();
    }
    public List<String> getAllCitiesThree() {
        System.out.println("CITIES THREE : " + cityRepo.getAllCitiesThree());
        return cityRepo.getAllCitiesThree();
    }

}
