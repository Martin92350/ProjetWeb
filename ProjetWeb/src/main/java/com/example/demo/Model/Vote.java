package com.example.demo.Model;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vote_id;

    @JoinColumn(foreignKey = @ForeignKey(name = "FK_survey_id"))
    private int survey_id;

    private int date_one_vote;

    private int date_two_vote;

    private int date_three_vote;

    private int city_one_vote;

    private int city_two_vote;

    private int city_three_vote;

    
    public int getVote_id() {
        return vote_id;
    }

    public void setVote_id(int vote_id) {
        this.vote_id = vote_id;
    }

    public int getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(int survey_id) {
        this.survey_id = survey_id;
    }

    public int getDate_one_vote() {
        return date_one_vote;
    }

    public void setDate_one_vote(int date_one_vote) {
        this.date_one_vote = date_one_vote;
    }

    public int getDate_two_vote() {
        return date_two_vote;
    }

    public void setDate_two_vote(int date_two_vote) {
        this.date_two_vote = date_two_vote;
    }

    public int getDate_three_vote() {
        return date_three_vote;
    }

    public void setDate_three_vote(int date_three_vote) {
        this.date_three_vote = date_three_vote;
    }

    public int getCity_one_vote() {
        return city_one_vote;
    }

    public void setCity_one_vote(int city_one_vote) {
        this.city_one_vote = city_one_vote;
    }

    public int getCity_two_vote() {
        return city_two_vote;
    }

    public void setCity_two_vote(int city_two_vote) {
        this.city_two_vote = city_two_vote;
    }

    public int getCity_three_vote() {
        return city_three_vote;
    }

    public void setCity_three_vote(int city_three_vote) {
        this.city_three_vote = city_three_vote;
    }
}
