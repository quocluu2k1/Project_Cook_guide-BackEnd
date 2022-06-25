package com.project.cookguide.Cook.guide.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class HistoryCommentDetailDto {
    private Long cmtId;
    private Long foodId;
    private String nameFood;
    private String contentCmt;
    private String imageCmt;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Timestamp timeCmt;

    public HistoryCommentDetailDto(){}

    public HistoryCommentDetailDto(Long cmtId, Long foodId, String nameFood, String contentCmt, String imageCmt, Timestamp timeCmt) {
        this.cmtId = cmtId;
        this.foodId = foodId;
        this.nameFood = nameFood;
        this.contentCmt = contentCmt;
        this.imageCmt = imageCmt;
        this.timeCmt = timeCmt;
    }

    public Long getCmtId() {
        return cmtId;
    }

    public void setCmtId(Long cmtId) {
        this.cmtId = cmtId;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public String getContentCmt() {
        return contentCmt;
    }

    public void setContentCmt(String contentCmt) {
        this.contentCmt = contentCmt;
    }

    public String getImageCmt() {
        return imageCmt;
    }

    public void setImageCmt(String imageCmt) {
        this.imageCmt = imageCmt;
    }

    public Timestamp getTimeCmt() {
        return timeCmt;
    }

    public void setTimeCmt(Timestamp timeCmt) {
        this.timeCmt = timeCmt;
    }
}
