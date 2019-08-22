package com.netplus.catpark.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Brandon.
 * @date 2019/8/22.
 * @time 10:18.
 */

@Data
@ApiModel
public class CommunityPublishDTO {
    @ApiModelProperty("发布标题")
    private String title;
    @ApiModelProperty("发布者内容")
    private String text;
}
