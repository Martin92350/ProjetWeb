package com.example.demo.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "survey")
public class Survey {

    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private Date date;

    @JoinColumn(foreignKey = @ForeignKey(name = "FK_city_id"))
    private int city;

    @JoinColumn(foreignKey = @ForeignKey(name = "FK_user_id"))
    private int user_id;

    @Column(name = "activity")
    private String activity;

    @Column(name = "attendance")
    private int attendance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
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
