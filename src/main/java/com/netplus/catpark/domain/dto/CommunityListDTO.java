package com.netplus.catpark.domain.dto;

import com.netplus.catpark.domain.bo.CommunityInfoBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @author Brandon.
 * @date 2019/8/22.
 * @time 15:06.
 */

@Data
@Builder
@ApiModel
public class CommunityListDTO {
    @ApiModelProperty("帖子列表")
    List<CommunityInfoBO> communityInfoBOList;
}
