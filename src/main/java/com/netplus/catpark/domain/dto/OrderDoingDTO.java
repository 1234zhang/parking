package com.netplus.catpark.domain.dto;

import com.netplus.catpark.domain.bo.OrderInfoBo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Brandon.
 * @date 2019/8/22.
 * @time 21:44.
 */

@Data
@Builder
@ApiModel
public class OrderDoingDTO implements Serializable {
    private static final long serialVersionUID = 1869686247048442693L;

    @ApiModelProperty("获取正在进行的订单详情列表")
    private List<OrderInfoBo> orderInfoBoList;
}

