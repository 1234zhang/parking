package com.netplus.catpark.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Brandon.
 * @date 2019/8/18.
 * @time 23:18.
 */

@Data
@ApiModel
public class BookParkingSpaceDTO {
    @ApiModelProperty("停车场id")
    private Long parkingId;
    @ApiModelProperty("停车场空车位的id")
    private String parkingSpaceId;
    @ApiModelProperty("用于更新车位状态的id")
    private Long id;
}
