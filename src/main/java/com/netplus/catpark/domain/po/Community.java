package com.netplus.catpark.domain.po;

import java.util.Date;

public class Community {
    private Long id;

    private Long userId;

    private String title;

    private String text;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Boolean deleted;

    public Community(Long id, Long userId, String title, String text, Date gmtCreate, Date gmtUpdate, Boolean deleted) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.text = text;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
        this.deleted = deleted;
    }

    public Community() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}