package com.netplus.catpark.domain.po;

import java.util.Date;

public class ParkingPosition {
    private Long id;

    private Double longitude;

    private Double latitude;

    private String positionGeohash;

    private Long parkingId;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Boolean deleted;

    public ParkingPosition(Long id, Double longitude, Double latitude, String positionGeohash, Long parkingId, Date gmtCreate, Date gmtUpdate, Boolean deleted) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.positionGeohash = positionGeohash;
        this.parkingId = parkingId;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
        this.deleted = deleted;
    }

    public ParkingPosition() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getPositionGeohash() {
        return positionGeohash;
    }

    public void setPositionGeohash(String positionGeohash) {
        this.positionGeohash = positionGeohash == null ? null : positionGeohash.trim();
    }

    public Long getParkingId() {
        return parkingId;
    }

    public void setParkingId(Long parkingId) {
        this.parkingId = parkingId;
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