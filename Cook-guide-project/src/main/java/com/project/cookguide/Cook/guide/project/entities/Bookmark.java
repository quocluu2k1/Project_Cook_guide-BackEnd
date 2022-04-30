package com.project.cookguide.Cook.guide.project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long booId;
    private Date date;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "foodId")
    private Food food;

    public Bookmark(){}

    public Bookmark(Date date, User user, Food food) {
        this.date = date;
        this.user = user;
        this.food = food;
    }

    public Bookmark(Long booId, Date date) {
        this.booId = booId;
        this.date = date;
    }

    public Long getBooId() {
        return booId;
    }

    public void setBooId(Long booId) {
        this.booId = booId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
