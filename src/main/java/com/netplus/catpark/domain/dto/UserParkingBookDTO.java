package com.netplus.catpark.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.jfr.BooleanFlag;
import lombok.*;

/**
 * @author Brandon.
 * @date 2019/8/21.
 * @time 16:58.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ApiModel
public class UserParkingBookDTO {
    @ApiModelProperty("用户共享车位id")
    private Long userParkingId;
}
