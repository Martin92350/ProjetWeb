package com.example.demo.Repository;

import com.example.demo.Model.Survey;
import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

//@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer> {

    @Query(value = "Select * FROM survey WHERE user_id=?1", nativeQuery = true)
    public List<Survey> findSurvey(int user_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM survey WHERE survey_id=?1", nativeQuery = true)
    public void deleteSurvey(int survey_id);
}
