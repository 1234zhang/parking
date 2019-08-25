package com.netplus.catpark.domain.po;

import java.util.Date;

public class UserParkingOrderTable {
    private Long id;

    private String orderId;

    private Long userId;

    private Long userParkingId;

    private Integer payment;

    private Byte orderStatus;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Boolean deleted;

    private Integer price;

    private String licensePlate;

    private Integer parikingTime;

    private Date beginParkingTime;

    public UserParkingOrderTable(Long id, String orderId, Long userId, Long userParkingId, Integer payment, Byte orderStatus, Date gmtCreate, Date gmtUpdate, Boolean deleted, Integer price, String licensePlate, Integer parikingTime, Date beginParkingTime) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.userParkingId = userParkingId;
        this.payment = payment;
        this.orderStatus = orderStatus;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
        this.deleted = deleted;
        this.price = price;
        this.licensePlate = licensePlate;
        this.parikingTime = parikingTime;
        this.beginParkingTime = beginParkingTime;
    }

    public UserParkingOrderTable() {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserParkingId() {
        return userParkingId;
    }

    public void setUserParkingId(Long userParkingId) {
        this.userParkingId = userParkingId;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate == null ? null : licensePlate.trim();
    }

    public Integer getParikingTime() {
        return parikingTime;
    }

    public void setParikingTime(Integer parikingTime) {
        this.parikingTime = parikingTime;
    }

    public Date getBeginParkingTime() {
        return beginParkingTime;
    }

    public void setBeginParkingTime(Date beginParkingTime) {
        this.beginParkingTime = beginParkingTime;
    }
}