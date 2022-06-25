package com.project.cookguide.Cook.guide.project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingId;
    private String name;
    private String amount;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "foodId")
    private Food food;

    public Ingredient(){}

    public Ingredient(String name, String amount, Food food) {
        this.ingId = ingId;
        this.name = name;
        this.amount = amount;
        this.food = food;
    }

    public Long getIngId() {
        return ingId;
    }

    public void setIngId(Long ingId) {
        this.ingId = ingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
