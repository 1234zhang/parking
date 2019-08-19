package com.netplus.catpark.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Brandon.
 * @date 2019/8/19.
 * @time 10:27.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ApiModel
public class SuccessDTO {
    @ApiModelProperty
    private Boolean isSuccess;
}
