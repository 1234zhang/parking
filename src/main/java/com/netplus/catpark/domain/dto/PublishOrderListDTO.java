package com.netplus.catpark.domain.dto;

import com.netplus.catpark.domain.bo.PublishOrderInfoBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Brandon.
 * @date 2019/8/26.
 * @time 20:04.
 */

@Data
@Builder
@ApiModel("返回订单列表的DTO")
public class PublishOrderListDTO {
    @ApiModelProperty("返回订单的list")
    private List<PublishOrderInfoBO> list;
}
