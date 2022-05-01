package com.project.cookguide.Cook.guide.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class BookmarkDto {
    private Long booId;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
    private Long UserId;
    private Long FoodId;

    public BookmarkDto(){}

    public BookmarkDto(Long booId, Date date, Long userId, Long foodId) {
        this.booId = booId;
        this.date = date;
        UserId = userId;
        FoodId = foodId;
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

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Long getFoodId() {
        return FoodId;
    }

    public void setFoodId(Long foodId) {
        FoodId = foodId;
    }
}
