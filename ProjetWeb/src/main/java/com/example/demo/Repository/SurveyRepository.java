package com.example.demo.Repository;

import com.example.demo.Model.Survey;
import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer> {

    @Query(value = " Select * from survey where user_id=?1", nativeQuery = true)
    public List<Survey> findSurvey(int user_id);

}
