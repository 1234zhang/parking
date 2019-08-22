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
 * @time 21:41.
 */

@Data
@Builder
@ApiModel
public class OrderFailDTO {
    @ApiModelProperty("获取失败详情列表")
    private List<OrderInfoBo> orderInfoBoList;
}
