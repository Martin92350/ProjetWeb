package com.example.demo.Model;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vote_id;

    @JoinColumn(foreignKey = @ForeignKey(name = "FK_survey_id"))
    private int survey_id;

    private int nb_vote;

    
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

    public int getNb_vote() {
        return nb_vote;
    }

    public void setNb_vote(int nb_vote) {
        this.nb_vote = nb_vote;
    }
}
