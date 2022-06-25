package com.project.cookguide.Cook.guide.project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reactId;

    @Column(columnDefinition = "boolean default false")
    private Boolean rSavoring;

    @Column(columnDefinition = "boolean default false")
    private Boolean rHearts;

    @Column(columnDefinition = "boolean default false")
    private Boolean rClaps;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "foodId")
    private Food food;

    public Reaction(){}

    public Reaction(Long reactId, Boolean rSavoring, Boolean rHearts, Boolean rClaps, User user, Food food) {
        this.reactId = reactId;
        this.rSavoring = rSavoring;
        this.rHearts = rHearts;
        this.rClaps = rClaps;
        this.user = user;
        this.food = food;
    }

    public Long getReactId() {
        return reactId;
    }

    public void setReactId(Long reactId) {
        this.reactId = reactId;
    }

    public Boolean getrSavoring() {
        return rSavoring;
    }

    public void setrSavoring(Boolean rSavoring) {
        this.rSavoring = rSavoring;
    }

    public Boolean getrHearts() {
        return rHearts;
    }

    public void setrHearts(Boolean rHearts) {
        this.rHearts = rHearts;
    }

    public Boolean getrClaps() {
        return rClaps;
    }

    public void setrClaps(Boolean rClaps) {
        this.rClaps = rClaps;
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
