package com.example.demo.Service;

import com.example.demo.Model.Survey;
import com.example.demo.Model.User;
import com.example.demo.Model.Vote;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.SurveyRepository;
import com.example.demo.Repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SurveyServiceImp implements SurveyService {

    @Autowired
    SurveyRepository surveyRepo;

    @Autowired
    VoteRepository voteRepository;

    @Override
    public List<Survey> getSurveyFromUserId(int user_id){
        List<Survey> survey = surveyRepo.findSurvey(user_id);

        return survey;
    }

    public void saveSurvey(Survey survey, Vote vote) {
        surveyRepo.save(survey);
        vote.setSurvey_id(survey.getSurvey_id());
        voteRepository.save(vote);

    }

    public void deleteSurvey(int survey_id) {
        surveyRepo.deleteSurvey(survey_id);
    }

    public List<Survey> getAllSurvey() {
        List<Survey> allSurvey = surveyRepo.findAllSurvey();
        return allSurvey;
    }

    public Survey getSurvey(int survey_id){
        Survey survey = surveyRepo.getSurvey(survey_id);
        return survey;
    }



}
