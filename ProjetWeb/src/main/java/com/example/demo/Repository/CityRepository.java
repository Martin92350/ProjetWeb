package com.example.demo.Repository;

import com.example.demo.Model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    @Query(value="SELECT name FROM city WHERE city_id=?1", nativeQuery = true)
    public String getCityById(int city_id);

}
