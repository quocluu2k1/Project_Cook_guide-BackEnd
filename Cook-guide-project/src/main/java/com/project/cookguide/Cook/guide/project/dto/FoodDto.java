package com.project.cookguide.Cook.guide.project.dto;

import com.project.cookguide.Cook.guide.project.entities.Food;

import java.util.Date;

public class FoodDto {
    private Long foodId;
    private String name;
    private String description;
    private int level;
    private Long nSavoring;
    private Long nHearts;
    private Long nClaps;
    private String foodImage1;
    private String foodImage2;
    private String foodImage3;
    private Date date;

    public FoodDto(){}

    public FoodDto(Long foodId, String name, String description, int level, Long nSavoring, Long nHearts, Long nClaps, String foodImage1, String foodImage2, String foodImage3, Date date) {
        this.foodId = foodId;
        this.name = name;
        this.description = description;
        this.level = level;
        this.nSavoring = nSavoring;
        this.nHearts = nHearts;
        this.nClaps = nClaps;
        this.foodImage1 = foodImage1;
        this.foodImage2 = foodImage2;
        this.foodImage3 = foodImage3;
        this.date = date;
    }
    public FoodDto(Food food) {
        this.foodId = food.getFoodId();
        this.name = food.getName();
        this.description = food.getDescription();
        this.level = food.getLevel();
        this.nSavoring = food.getnSavoring();
        this.nHearts = food.getnHearts();
        this.nClaps = food.getnClaps();
        this.foodImage1 = food.getFoodImage1();
        this.foodImage2 = food.getFoodImage2();
        this.foodImage3 = food.getFoodImage3();
        this.date = food.getDate();
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Long getnSavoring() {
        return nSavoring;
    }

    public void setnSavoring(Long nSavoring) {
        this.nSavoring = nSavoring;
    }

    public Long getnHearts() {
        return nHearts;
    }

    public void setnHearts(Long nHearts) {
        this.nHearts = nHearts;
    }

    public Long getnClaps() {
        return nClaps;
    }

    public void setnClaps(Long nClaps) {
        this.nClaps = nClaps;
    }

    public String getFoodImage1() {
        return foodImage1;
    }

    public void setFoodImage1(String foodImage1) {
        this.foodImage1 = foodImage1;
    }

    public String getFoodImage2() {
        return foodImage2;
    }

    public void setFoodImage2(String foodImage2) {
        this.foodImage2 = foodImage2;
    }

    public String getFoodImage3() {
        return foodImage3;
    }

    public void setFoodImage3(String foodImage3) {
        this.foodImage3 = foodImage3;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
