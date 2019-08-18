package com.netplus.catpark.domain.dto;

import lombok.Data;

/**
 * @author Brandon.
 * @date 2019/8/18.
 * @time 20:33.
 */

@Data
public class SpaceParamDTO {
    private Integer page;
    private Integer count;
    private Long parkingSpaceId;
}
