package com.netplus.catpark.domain.dto;

import com.netplus.catpark.domain.po.UserParkingInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Brandon.
 * @date 2019/9/7.
 * @time 14:51.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class UserParkingInfoListDTO {
    @ApiModelProperty("用户停车位列表")
    List<UserParkingInfo> userParkingInfos;
}
