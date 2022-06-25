package com.project.cookguide.Cook.guide.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.util.List;

public class HistoryCommentDto {
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    private Date date;
    private List<HistoryCommentDetailDto> list;

    public HistoryCommentDto(){}

    public HistoryCommentDto(Date date, List<HistoryCommentDetailDto> list) {
        this.date = date;
        this.list = list;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<HistoryCommentDetailDto> getList() {
        return list;
    }

    public void setList(List<HistoryCommentDetailDto> list) {
        this.list = list;
    }
}
