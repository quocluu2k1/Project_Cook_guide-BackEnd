package com.project.cookguide.Cook.guide.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class HistorySearchDto {
    private Long hisId;
    private String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Timestamp time;
    private int type;
    private Boolean isActivated;

    public HistorySearchDto(){}

    public HistorySearchDto(Long hisId, String content, Timestamp time, int type, Boolean isActivated) {
        this.hisId = hisId;
        this.content = content;
        this.time = time;
        this.type = type;
        this.isActivated = isActivated;
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
}
