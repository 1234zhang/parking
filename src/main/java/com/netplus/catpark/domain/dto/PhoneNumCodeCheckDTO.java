package com.netplus.catpark.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Brandon.
 * @date 2019/8/27.
 * @time 23:06.
 */

@Data
@ApiModel("提交验证码和手机号码")
public class PhoneNumCodeCheckDTO {
    @ApiModelProperty("验证码")
    private String authCode;
    @ApiModelProperty("手机号码")
    private String phoneNum;
}
