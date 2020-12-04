package com.example.demo.Model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity //(name = "survey")
//@Table(name = "survey")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int survey_id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private Date date;

    @JoinColumn(foreignKey = @ForeignKey(name = "FK_city_id"))
    private int city_id;

    @JoinColumn(foreignKey = @ForeignKey(name = "FK_user_id"))
    private int user_id;




//    private String city_name;
//
//    private String username;

//    public String getCity_name() {
//        return city_name;
//    }
//
//    public void setCity_name(String city_name) {
//        this.city_name = city_name;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }

    //@Column(name = "activity")
    private String activity;

    //@Column(name = "attendance")
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
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
}
