package com.netplus.catpark.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Brandon.
 * @date 2019/9/7.
 * @time 14:12.
 */

@Data
@ApiModel("停车位信息")
public class ParkingInfoDTO {
    @ApiModelProperty("经度")
    private Double lat;
    @ApiModelProperty("纬度")
    private Double lng;
    @ApiModelProperty("位置信息")
    private String address;
}
