package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity //(name = "survey")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int survey_id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_one")
    private Date date_one;

    @Column(name = "date_two")
    private Date date_two;

    @Column(name = "date_three")
    private Date date_three;

    @JoinColumn(foreignKey = @ForeignKey(name = "FK_city_id_one"))
    private int city_id_one;

    @JoinColumn(foreignKey = @ForeignKey(name = "FK_city_id_two"))
    private int city_id_two;

    @JoinColumn(foreignKey = @ForeignKey(name = "FK_city_id_three"))
    private int city_id_three;

    @JoinColumn(foreignKey = @ForeignKey(name = "FK_user_id"))
    private int user_id;

    private String activity;

    private int attendance;

    public int getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(int survey_id) {
        this.survey_id = survey_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate_one() {
        return date_one;
    }

    public void setDate_one(Date date_one) {
        this.date_one = date_one;
    }

    public Date getDate_two() {
        return date_two;
    }

    public void setDate_two(Date date_two) {
        this.date_two = date_two;
    }

    public Date getDate_three() {
        return date_three;
    }

    public void setDate_three(Date date_three) {
        this.date_three = date_three;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public int getCity_id_one() {
        return city_id_one;
    }

    public void setCity_id_one(int city_id_one) {
        this.city_id_one = city_id_one;
    }

    public int getCity_id_two() {
        return city_id_two;
    }

    public void setCity_id_two(int city_id_two) {
        this.city_id_two = city_id_two;
    }

    public int getCity_id_three() {
        return city_id_three;
    }

    public void setCity_id_three(int city_id_three) {
        this.city_id_three = city_id_three;
    }

}
