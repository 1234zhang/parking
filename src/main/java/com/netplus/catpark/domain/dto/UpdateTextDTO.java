package com.netplus.catpark.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Brandon.
 * @date 2019/8/22.
 * @time 16:10.
 */

@Data
@ApiModel
public class UpdateTextDTO {
    @ApiModelProperty("帖子id")
    private Long textId;
    @ApiModelProperty("修改的帖子内容")
    private String text;
}
