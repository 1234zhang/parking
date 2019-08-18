package com.netplus.catpark.domain.dto;

import com.netplus.catpark.domain.bo.SpaceInfoBO;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @author Brandon.
 * @date 2019/8/18.
 * @time 20:25.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class SpaceListDTO {
    @ApiModelProperty
    private List<SpaceInfoBO> SpaceInfoList;
}
