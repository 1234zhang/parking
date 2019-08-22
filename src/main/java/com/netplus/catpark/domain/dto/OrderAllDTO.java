package com.netplus.catpark.domain.dto;

import com.netplus.catpark.domain.bo.OrderInfoBo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Brandon.
 * @date 2019/8/22.
 * @time 17:03.
 */

@Data
@Builder
@ApiModel
public class OrderAllDTO {
    @ApiModelProperty("获取订单详情列表")
    private List<OrderInfoBo> orderInfoBoList;
}
