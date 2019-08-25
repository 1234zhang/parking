package com.netplus.catpark.domain.dto;

import com.netplus.catpark.domain.bo.ShareOrderInfoBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Brandon.
 * @date 2019/8/24.
 * @time 16:35.
 */

@Data
@ApiModel("返回订单的列表")
@Builder
public class ShareOrderListDTO {
    @ApiModelProperty("订单列表")
    private List<ShareOrderInfoBO> orderList;
}
