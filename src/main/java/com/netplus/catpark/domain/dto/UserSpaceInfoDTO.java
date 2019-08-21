package com.netplus.catpark.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Brandon.
 * @date 2019/8/21.
 * @time 11:06.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ApiModel
public class UserSpaceInfoDTO {
    @ApiModelProperty("共享停车位的发布者")
    private String name;
    @ApiModelProperty("共享停车位的地址")
    private String address;
    @ApiModelProperty("共享车位的类型 0 表示室内 1表示室外")
    private Byte parkingType;
    @ApiModelProperty("没小时价钱")
    private Integer payment;
    @ApiModelProperty("预定开始时间")
    private Integer beginBookTime;
    @ApiModelProperty("预定结束时间")
    private Integer endBookTime;
    @ApiModelProperty("预定位置经度")
    private Double lat;
    @ApiModelProperty("预定位置纬度")
    private Double lng;
    @ApiModelProperty("预定位置的号码")
    private String phoneNum;

}
