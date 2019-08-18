package com.netplus.catpark.domain.bo;

import lombok.*;

/**
 * @author Brandon.
 * @date 2019/8/18.
 * @time 14:17.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ParkingBO {
    private Long id;
    private String parkingName;
    private Long emptySpace;
}
