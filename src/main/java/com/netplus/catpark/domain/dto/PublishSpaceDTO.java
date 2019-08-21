package com.netplus.catpark.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Brandon.
 * @date 2019/8/19.
 * @time 10:20.
 */

@Data
@ApiModel
public class PublishSpaceDTO {
    @ApiModelProperty("发布者手机号码")
    private String phoneNum;
    @ApiModelProperty("停车位的地址")
    private String address;
    @ApiModelProperty("验证码")
    private String authCode;
    @ApiModelProperty("发布者姓名")
    private String name;
    @ApiModelProperty("每小时价钱")
    private Integer payment;
    @ApiModelProperty("经度")
    private Double lat;
    @ApiModelProperty("纬度")
    private Double lng;
    @ApiModelProperty("预定开始时间")
    private Integer beginBookTime;
    @ApiModelProperty("预定结束时间")
    private Integer endBookTime;
    @ApiModelProperty("停车场类型 0 表示室内，1表示室外")
    private Byte parkingType;

}
