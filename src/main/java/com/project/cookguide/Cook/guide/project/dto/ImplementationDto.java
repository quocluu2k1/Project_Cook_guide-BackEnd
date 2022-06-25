package com.project.cookguide.Cook.guide.project.dto;

public class ImplementationDto {
    private Long impId;
    private int stepOrder;
    private String impDetail;
    private String stepImage;
    private Long time;

    public ImplementationDto(){}

    public ImplementationDto(Long impId, int stepOrder, String impDetail, String stepImage, Long time) {
        this.impId = impId;
        this.stepOrder = stepOrder;
        this.impDetail = impDetail;
        this.stepImage = stepImage;
        this.time = time;
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
}
