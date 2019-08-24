package com.netplus.catpark.domain.po;

import java.util.Date;

public class UserParking {
    private Long id;

    private Long userId;

    private String address;

    private Double latitude;

    private Double longitude;

    private String positionGeoHash;

    private Integer payment;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Boolean deleted;

    private Date beginBookTime;

    private Date endBookTime;

    private Byte parkingType;

    public UserParking(Long id, Long userId, String address, Double latitude, Double longitude, String positionGeoHash, Integer payment, Date gmtCreate, Date gmtUpdate, Boolean deleted, Date beginBookTime, Date endBookTime, Byte parkingType) {
        this.id = id;
        this.userId = userId;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.positionGeoHash = positionGeoHash;
        this.payment = payment;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
        this.deleted = deleted;
        this.beginBookTime = beginBookTime;
        this.endBookTime = endBookTime;
        this.parkingType = parkingType;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPositionGeoHash() {
        return positionGeoHash;
    }

    public void setPositionGeoHash(String positionGeoHash) {
        this.positionGeoHash = positionGeoHash == null ? null : positionGeoHash.trim();
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
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
}