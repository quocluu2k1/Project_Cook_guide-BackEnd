package com.project.cookguide.Cook.guide.project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cmtId;
    private String content;
    private String image;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp time;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "foodId")
    private Food food;

    public Comment(){}

    public Comment(String content, String image, Timestamp time, User user, Food food) {
        this.content = content;
        this.image = image;
        this.time = time;
        this.user = user;
        this.food = food;
    }

    public Long getCmtId() {
        return cmtId;
    }

    public void setCmtId(Long cmtId) {
        this.cmtId = cmtId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
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
