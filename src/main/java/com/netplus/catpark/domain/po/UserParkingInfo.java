package com.netplus.catpark.domain.po;

import java.util.Date;

public class UserParkingInfo {
    private Long id;

    private Long userId;

    private Double latitude;

    private Double longitude;

    private String positionGeoHash;

    private String address;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Boolean deleted;

    public UserParkingInfo(Long id, Long userId, Double latitude, Double longitude, String positionGeoHash, String address, Date gmtCreate, Date gmtUpdate, Boolean deleted) {
        this.id = id;
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.positionGeoHash = positionGeoHash;
        this.address = address;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
        this.deleted = deleted;
    }

    public UserParkingInfo() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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