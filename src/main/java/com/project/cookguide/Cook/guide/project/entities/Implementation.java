package com.project.cookguide.Cook.guide.project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Implementation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long impId;
    private int stepOrder;
    private String impDetail;
    private String stepImage;
    private Long time;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "foodId")
    private Food food;

    public Implementation(){}

    public Implementation(int stepOrder, String impDetail, String stepImage, Long time, Food food) {
        this.impId = impId;
        this.stepOrder = stepOrder;
        this.impDetail = impDetail;
        this.stepImage = stepImage;
        this.time = time;
        this.food = food;
    }

    public Long getImpId() {
        return impId;
    }

    public void setImpId(Long impId) {
        this.impId = impId;
    }

    public int getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(int stepOrder) {
        this.stepOrder = stepOrder;
    }

    public String getImpDetail() {
        return impDetail;
    }

    public void setImpDetail(String impDetail) {
        this.impDetail = impDetail;
    }

    public String getStepImage() {
        return stepImage;
    }

    public void setStepImage(String stepImage) {
        this.stepImage = stepImage;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
