package com.project.cookguide.Cook.guide.project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
public class HistorySearch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hisId;
    private String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp time;
    private int type;
    private Boolean isActivated;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    public HistorySearch(){}

    public HistorySearch(String content, Timestamp time, int type, Boolean isActivated, User user) {
        this.content = content;
        this.time = time;
        this.type = type;
        this.isActivated = isActivated;
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Boolean getActivated() {
        return isActivated;
    }

    public void setActivated(Boolean activated) {
        isActivated = activated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
