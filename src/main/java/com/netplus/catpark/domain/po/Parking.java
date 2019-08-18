package com.netplus.catpark.domain.po;

import java.util.Date;

public class Parking {
    private Long id;

    private String parkingName;

    private String parkingPhoneNum;

    private String address;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Boolean deleted;

    public Parking(Long id, String parkingName, String parkingPhoneNum, String address, Date gmtCreate, Date gmtUpdate, Boolean deleted) {
        this.id = id;
        this.parkingName = parkingName;
        this.parkingPhoneNum = parkingPhoneNum;
        this.address = address;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
        this.deleted = deleted;
    }

    public Parking() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName == null ? null : parkingName.trim();
    }

    public String getParkingPhoneNum() {
        return parkingPhoneNum;
    }

    public void setParkingPhoneNum(String parkingPhoneNum) {
        this.parkingPhoneNum = parkingPhoneNum == null ? null : parkingPhoneNum.trim();
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