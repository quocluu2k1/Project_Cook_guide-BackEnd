package com.project.cookguide.Cook.guide.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class CommentDto {
    private Long cmtId;
    private String content;
    private String image;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Timestamp time;
    private Boolean statusUser;

    public CommentDto(){}

    public CommentDto(Long cmtId, String content, String image, Timestamp time, Boolean statusUser) {
        this.cmtId = cmtId;
        this.content = content;
        this.image = image;
        this.time = time;
        this.statusUser = statusUser;
    }

    public Long getCmtId() {
        return cmtId;
    }

    public void setCmtId(Long cmtId) {
        this.cmtId = cmtId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Boolean getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(Boolean statusUser) {
        this.statusUser = statusUser;
    }
}
