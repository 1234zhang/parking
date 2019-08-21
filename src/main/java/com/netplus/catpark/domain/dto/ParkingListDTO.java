package com.netplus.catpark.domain.dto;

import com.netplus.catpark.domain.bo.ParkingBO;
import com.netplus.catpark.domain.po.Parking;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Brandon.
 * @date 2019/8/18.
 * @time 14:15.
 */

@Data
@ApiModel
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingListDTO {
    @ApiModelProperty
    List<Object> parkingList;
}
