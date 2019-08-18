package com.netplus.catpark.domain.dto;

import lombok.*;

/**
 * @author Brandon.
 * @date 2019/8/18.
 * @time 23:53.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class FindWayDTO {
    private Double lat;
    private Double lng;
}
