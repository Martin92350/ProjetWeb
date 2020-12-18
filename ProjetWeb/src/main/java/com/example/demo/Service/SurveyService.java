package com.example.demo.Service;


import com.example.demo.Model.Survey;
import com.example.demo.Model.User;
import com.example.demo.Model.Vote;

import java.util.List;

public interface SurveyService {

    public List<Survey> getSurveyFromUserId(int user_id);

    public void saveSurvey(Survey survey, Vote vote);

    public void deleteSurvey(int survey_id);

    public List<Survey> getAllSurvey();

    public Survey getSurvey(int survey_id);

}
