package com.netplus.catpark.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Brandon.
 * @date 2019/8/18.
 * @time 14:13.
 */

@Data
@ApiModel
public class PositionDTO {
    @ApiModelProperty
    private Double lat;
    @ApiModelProperty
    private Double lng;
}
