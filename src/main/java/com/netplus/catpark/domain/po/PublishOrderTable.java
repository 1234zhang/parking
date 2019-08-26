package com.netplus.catpark.domain.po;

import java.util.Date;

public class PublishOrderTable {
    private Long id;

    private Long rentUserId;

    private Long publishUserId;

    private Long parkingId;

    private String orderId;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Boolean deleted;

    public PublishOrderTable(Long id, Long rentUserId, Long publishUserId, Long parkingId, String orderId, Date gmtCreate, Date gmtUpdate, Boolean deleted) {
        this.id = id;
        this.rentUserId = rentUserId;
        this.publishUserId = publishUserId;
        this.parkingId = parkingId;
        this.orderId = orderId;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
        this.deleted = deleted;
    }

    public PublishOrderTable() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRentUserId() {
        return rentUserId;
    }

    public void setRentUserId(Long rentUserId) {
        this.rentUserId = rentUserId;
    }

    public Long getPublishUserId() {
        return publishUserId;
    }

    public void setPublishUserId(Long publishUserId) {
        this.publishUserId = publishUserId;
    }

    public Long getParkingId() {
        return parkingId;
    }

    public void setParkingId(Long parkingId) {
        this.parkingId = parkingId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
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