package com.netplus.catpark.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Brandon.
 * @date 2019/8/18.
 * @time 23:50.
 */

@Data
@ApiModel
public class PositionIdDTO {
    @ApiModelProperty
    private Long parkingId;
    @ApiModelProperty
    private String parkingSpaceId;
}
