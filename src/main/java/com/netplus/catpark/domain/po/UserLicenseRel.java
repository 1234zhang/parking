package com.netplus.catpark.domain.po;

import java.util.Date;

public class UserLicenseRel {
    private Long id;

    private Long userId;

    private String licensePlate;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Boolean deleted;

    public UserLicenseRel(Long id, Long userId, String licensePlate, Date gmtCreate, Date gmtUpdate, Boolean deleted) {
        this.id = id;
        this.userId = userId;
        this.licensePlate = licensePlate;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
        this.deleted = deleted;
    }

    public UserLicenseRel() {
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

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate == null ? null : licensePlate.trim();
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