package com.netplus.catpark.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Brandon.
 * @date 2019/8/20.
 * @time 21:46.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserParkingSpaceInfoBO {
    private Long userParkingSpaceId;
    private Double lat;
    private Double lng;
}
