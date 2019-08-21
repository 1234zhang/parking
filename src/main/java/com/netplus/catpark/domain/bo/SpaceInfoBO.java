package com.netplus.catpark.domain.bo;

import lombok.*;

/**
 * @author Brandon.
 * @date 2019/8/18.
 * @time 20:28.
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SpaceInfoBO {
    private String parkingSpaceName;
    private String parkingSpaceAddr;
    private Double lat;
    private Double lng;
    private String parkingSpaceId;
    private Long parkingId;
    private Long id;
}
