package com.netplus.catpark.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author Brandon.
 * @date 2019/8/27.
 * @time 15:57.
 */

@Data
@Builder
@AllArgsConstructor
@ApiModel("微信登录成功之后的返回信息")
public class UserReturnDTO {
    @ApiModelProperty("性别")
    private Byte gender;
    @ApiModelProperty("用户头像")
    private String avatar;
    @ApiModelProperty("用户昵称")
    private String nickName;
    @ApiModelProperty("用户电话号码")
    private String phoneNum;
    @ApiModelProperty("登录token")
    private String token;
}
