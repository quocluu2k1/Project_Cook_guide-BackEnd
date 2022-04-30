package com.project.cookguide.Cook.guide.project.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    @JsonManagedReference
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients;

    @JsonManagedReference
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private Set<Implementation> implementations;

    @JsonManagedReference
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @JsonManagedReference
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private Set<Bookmark> bookmarks;

    @JsonManagedReference
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private Set<Reaction> reactions;

    public Food(){}

    public Food(String name, String description, int level, Long nSavoring, Long nHearts, Long nClaps, String foodImage1, String foodImage2, String foodImage3, Date date) {
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

    public Food(Long foodId, String name, String description, int level, Long nSavoring, Long nHearts, Long nClaps, String foodImage1, String foodImage2, String foodImage3, Date date) {
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

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<Implementation> getImplementations() {
        return implementations;
    }

    public void setImplementations(Set<Implementation> implementations) {
        this.implementations = implementations;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(Set<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(Set<Reaction> reactions) {
        this.reactions = reactions;
    }
}
