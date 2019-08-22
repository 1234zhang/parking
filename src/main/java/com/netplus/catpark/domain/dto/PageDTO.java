package com.netplus.catpark.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apiguardian.api.API;

/**
 * @author Brandon.
 * @date 2019/8/22.
 * @time 15:09.
 */

@Data
@ApiModel
public class PageDTO {
    @ApiModelProperty("页码")
    private Integer page;
    @ApiModelProperty("每页数量")
    private Integer count;
}
