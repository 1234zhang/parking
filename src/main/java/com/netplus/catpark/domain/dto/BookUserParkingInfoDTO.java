package com.netplus.catpark.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Brandon.
 * @date 2019/8/21.
 * @time 18:13.
 */
@ApiModel
@Data
public class BookUserParkingInfoDTO {
    @ApiModelProperty("用户共享停车位id")
    private Long userParkingId;
    @ApiModelProperty("每小时价钱")
    private Integer payment;
    @ApiModelProperty("停车时间，小时")
    private Integer time;
    @ApiModelProperty("停车总共花费")
    private Integer price;
    @ApiModelProperty("车牌id")
    private Long plateId;
}
