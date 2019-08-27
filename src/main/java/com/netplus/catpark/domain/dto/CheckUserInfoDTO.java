package com.netplus.catpark.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Brandon.
 * @date 2019/8/27.
 * @time 16:01.
 */

@Data
@ApiModel("获取用户加密信息")
public class CheckUserInfoDTO {
    @ApiModelProperty("code")
    @NotNull(message = "code不能为空")
    private String code;
    @ApiModelProperty("解密偏移量")
    private String vi;
    @ApiModelProperty("需要解密的数据")
    private String encryptedData;
}
