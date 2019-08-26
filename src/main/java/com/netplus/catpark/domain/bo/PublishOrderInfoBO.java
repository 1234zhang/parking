package com.netplus.catpark.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.apiguardian.api.API;

import java.util.Date;

/**
 * @author Brandon.
 * @date 2019/8/26.
 * @time 19:53.
 */

@Data
@Builder
@ApiModel
public class PublishOrderInfoBO {
    @ApiModelProperty("订单id")
    private String orderId;
    @ApiModelProperty("停车场地址")
    private String address;
    @ApiModelProperty("停车场类型 室内还是室外，0表示室内， 1表示室外")
    private Byte parkingType;
    @ApiModelProperty("每小时价钱")
    private Integer payment;
    @ApiModelProperty("总共价钱")
    private Integer price;
    @ApiModelProperty("能被预定的时间")
    private String bookTime;
    @ApiModelProperty("被预定的时间")
    private String rentTime;
    @ApiModelProperty("手机号码")
    private String phoneNum;
    @ApiModelProperty("创建时间")
    private Date gmtCreate;
    @ApiModelProperty("租赁人的昵称")
    private String nickName;
    @ApiModelProperty("订单状态")
    private Byte orderStatus;
}
