package com.project.cookguide.Cook.guide.project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
public class HistorySearch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hisId;
    private String content;
    private Date date;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    public HistorySearch(){}

    public HistorySearch(String content, Date date, User user) {
        this.content = content;
        this.date = date;
        this.user = user;
    }

    public Long getHisId() {
        return hisId;
    }

    public void setHisId(Long hisId) {
        this.hisId = hisId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
