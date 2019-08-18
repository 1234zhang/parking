package com.netplus.catpark.domain.po;

import java.util.Date;

public class OrderTable {
    private Long id;

    private String orderId;

    private Long parkingId;

    private Long userId;

    private String parkingSpaceId;

    private Byte orderStatus;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Boolean deleted;

    public OrderTable(Long id, String orderId, Long parkingId, Long userId, String parkingSpaceId, Byte orderStatus, Date gmtCreate, Date gmtUpdate, Boolean deleted) {
        this.id = id;
        this.orderId = orderId;
        this.parkingId = parkingId;
        this.userId = userId;
        this.parkingSpaceId = parkingSpaceId;
        this.orderStatus = orderStatus;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
        this.deleted = deleted;
    }

    public OrderTable() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Long getParkingId() {
        return parkingId;
    }

    public void setParkingId(Long parkingId) {
        this.parkingId = parkingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getParkingSpaceId() {
        return parkingSpaceId;
    }

    public void setParkingSpaceId(String parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId == null ? null : parkingSpaceId.trim();
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
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