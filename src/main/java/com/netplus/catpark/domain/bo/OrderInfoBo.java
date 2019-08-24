package com.netplus.catpark.domain.bo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author Brandon.
 * @date 2019/8/22.
 * @time 17:04.
 */

@Data
@Builder

public class OrderInfoBo {
    private String orderId;
    private Date gmtCreate;
    private String licensePlate;
    private String address;
    private String parkingTime;
    private Integer price;
    private String totalParkingTime;
    private Byte orderStatus;
}
