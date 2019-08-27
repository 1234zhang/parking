package com.netplus.catpark.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Brandon.
 * @date 2019/8/27.
 * @time 21:57.
 */

@Data
@Builder
@ApiModel("返回用户绑定手机号码状态")
public class IsBindPhoneNumDTO {
    @ApiModelProperty("0表示没有绑定，1表示绑定了手机号")
    private Integer status;
    @ApiModelProperty("绑定手机号码")
    private String phoneNum;
}
