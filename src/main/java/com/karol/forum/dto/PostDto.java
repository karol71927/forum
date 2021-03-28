package com.karol.forum.dto;

import java.util.Date;
import java.util.Set;

public class PostDto {
    private Long ID;
    private String topic;
    private String text;
    private Date date;
    private Long CategoryId;
    private Long UserId;
    private Set<ReplyDto> replyDtoSet;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(Long categoryId) {
        CategoryId = categoryId;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Set<ReplyDto> getReplyDtoSet() {
        return replyDtoSet;
    }

    public void setReplyDtoSet(Set<ReplyDto> replyDtoSet) {
        this.replyDtoSet = replyDtoSet;
    }
}
