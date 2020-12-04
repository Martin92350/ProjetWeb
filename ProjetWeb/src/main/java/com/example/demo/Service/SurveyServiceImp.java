package com.example.demo.Service;

import com.example.demo.Model.Survey;
import com.example.demo.Model.User;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SurveyServiceImp implements SurveyService {

    @Autowired
    SurveyRepository surveyRepo;

    @Override
    public List<Survey> getSurveyFromUserId(int user_id){
        List<Survey> survey = surveyRepo.findSurvey(user_id);

        return survey;
    }

    public void saveSurvey(Survey survey) {
        System.out.println("2 - " + survey.getDate());
        survey.setUser_id(1);
        survey.setCity_id(1);

        surveyRepo.save(survey);
    }

}
