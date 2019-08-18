package com.netplus.catpark.domain.po;

import java.util.Date;

public class ParkingSpace {
    private Long id;

    private Long parkingId;

    private String parkingSpaceId;

    private Byte parkingSpaceStatus;

    private Double longitude;

    private Double latitude;

    private String positionGeoHash;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Boolean deleted;

    public ParkingSpace(Long id, Long parkingId, String parkingSpaceId, Byte parkingSpaceStatus, Double longitude, Double latitude, String positionGeoHash, Date gmtCreate, Date gmtUpdate, Boolean deleted) {
        this.id = id;
        this.parkingId = parkingId;
        this.parkingSpaceId = parkingSpaceId;
        this.parkingSpaceStatus = parkingSpaceStatus;
        this.longitude = longitude;
        this.latitude = latitude;
        this.positionGeoHash = positionGeoHash;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
        this.deleted = deleted;
    }

    public ParkingSpace() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParkingId() {
        return parkingId;
    }

    public void setParkingId(Long parkingId) {
        this.parkingId = parkingId;
    }

    public String getParkingSpaceId() {
        return parkingSpaceId;
    }

    public void setParkingSpaceId(String parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId == null ? null : parkingSpaceId.trim();
    }

    public Byte getParkingSpaceStatus() {
        return parkingSpaceStatus;
    }

    public void setParkingSpaceStatus(Byte parkingSpaceStatus) {
        this.parkingSpaceStatus = parkingSpaceStatus;
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

    public String getPositionGeoHash() {
        return positionGeoHash;
    }

    public void setPositionGeoHash(String positionGeoHash) {
        this.positionGeoHash = positionGeoHash == null ? null : positionGeoHash.trim();
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