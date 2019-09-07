package com.netplus.catpark.domain.po;

import java.util.Date;

public class UserParking {
    private Long id;

    private Long userId;

    private Long parkingInfoId;

    private Integer payment;

    private Boolean deleted;

    private Date beginBookTime;

    private Date endBookTime;

    private Byte parkingType;

    private Date gmtUpdate;

    private Date gmtCreate;

    public UserParking(Long id, Long userId, Long parkingInfoId, Integer payment, Boolean deleted, Date beginBookTime, Date endBookTime, Byte parkingType, Date gmtUpdate, Date gmtCreate) {
        this.id = id;
        this.userId = userId;
        this.parkingInfoId = parkingInfoId;
        this.payment = payment;
        this.deleted = deleted;
        this.beginBookTime = beginBookTime;
        this.endBookTime = endBookTime;
        this.parkingType = parkingType;
        this.gmtUpdate = gmtUpdate;
        this.gmtCreate = gmtCreate;
    }

    public UserParking() {
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

    public Long getParkingInfoId() {
        return parkingInfoId;
    }

    public void setParkingInfoId(Long parkingInfoId) {
        this.parkingInfoId = parkingInfoId;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getBeginBookTime() {
        return beginBookTime;
    }

    public void setBeginBookTime(Date beginBookTime) {
        this.beginBookTime = beginBookTime;
    }

    public Date getEndBookTime() {
        return endBookTime;
    }

    public void setEndBookTime(Date endBookTime) {
        this.endBookTime = endBookTime;
    }

    public Byte getParkingType() {
        return parkingType;
    }

    public void setParkingType(Byte parkingType) {
        this.parkingType = parkingType;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}