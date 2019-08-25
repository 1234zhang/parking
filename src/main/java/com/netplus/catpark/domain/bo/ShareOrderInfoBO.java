package com.netplus.catpark.domain.bo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author Brandon.
 * @date 2019/8/24.
 * @time 16:25.
 */

@Data
@Builder
public class ShareOrderInfoBO {
    private String orderId;
    private Integer parkingId;
    private String address;
    private String phoneNum;
    private Date gmtCreate;
    private Integer price;
    private String parkingTime;
    private Byte parkingType;
    private Integer payment;
    private String licensePlate;
    private Byte orderStatus;
}
