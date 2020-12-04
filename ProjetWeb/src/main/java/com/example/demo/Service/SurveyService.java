package com.example.demo.Service;


import com.example.demo.Model.Survey;
import com.example.demo.Model.User;

import java.util.List;

public interface SurveyService {
    public List<Survey> getSurveyFromUserId(int user_id);

    public void saveSurvey(Survey survey);
}
