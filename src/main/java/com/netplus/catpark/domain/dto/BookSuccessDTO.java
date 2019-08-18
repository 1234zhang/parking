package com.netplus.catpark.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Brandon.
 * @date 2019/8/18.
 * @time 23:13.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ApiModel
public class BookSuccessDTO {
    @ApiModelProperty
    private String parkingSpaceId;
    @ApiModelProperty
    private Long parkingId;
}
