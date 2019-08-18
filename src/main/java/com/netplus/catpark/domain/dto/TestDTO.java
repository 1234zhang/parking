package com.netplus.catpark.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Brandon.
 * @date 2019/8/17.
 * @time 15:14.
 */

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@ApiModel
public class TestDTO {
    @ApiModelProperty(value = "测试")
    private String test;
    @ApiModelProperty(value = "测试id")
    private String id;
}
