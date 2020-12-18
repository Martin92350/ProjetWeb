package com.example.demo.Service;
import com.example.demo.Repository.CityRepository;
import com.example.demo.Repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteImp implements VoteService{

    @Autowired
    VoteRepository voteRepository;

    public void scoreVote(List<Boolean> list, int survey_id) {
        System.out.println("survey = " + survey_id);
        System.out.println("Boolean list = " + list.toString());
        if(list.get(0) == true) {
            voteRepository.scoreVoteCity1(survey_id);
        }
        if(list.get(1) == true) {
            voteRepository.scoreVoteCity2(survey_id);
        }
        if(list.get(2) == true) {
            voteRepository.scoreVoteCity3(survey_id);
        }
        if(list.get(3) == true) {
            voteRepository.scoreVoteDate1(survey_id);
        }
        if(list.get(4) == true) {
            voteRepository.scoreVoteDate2(survey_id);
        }
        if(list.get(5) == true) {
            voteRepository.scoreVoteDate3(survey_id);
        }
    }
}
