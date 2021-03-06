package com.example.demo.Repository;

import com.example.demo.Model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    @Query(value="SELECT name FROM city WHERE city_id=?1", nativeQuery = true)
    public String getCityById(int city_id);

    @Query(value="SELECT name FROM city", nativeQuery = true)
    public List<String> getAllCities();

    @Query(value="SELECT city.name FROM city INNER JOIN survey ON city.city_id=survey.city_id_one ORDER BY survey_id", nativeQuery = true)
    public List<String> getAllCitiesOne();

    @Query(value="SELECT city.name FROM city INNER JOIN survey ON city.city_id=survey.city_id_two ORDER BY survey_id", nativeQuery = true)
    public List<String> getAllCitiesTwo();

    @Query(value="SELECT city.name FROM city INNER JOIN survey ON city.city_id=survey.city_id_three ORDER BY survey_id", nativeQuery = true)
    public List<String> getAllCitiesThree();

}
