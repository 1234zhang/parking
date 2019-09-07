package com.netplus.catpark.dao.define;

import com.netplus.catpark.domain.po.UserParkingInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Brandon.
 * @date 2019/9/7.
 * @time 14:01.
 */

public interface UserParkingInfoDefineMapper {
    List<UserParkingInfo> getNearbyParking(@Param(value = "geoHash") String geoHash);


    int deletedParkingInfo(@Param(value = "parkingId") Long parkingId, @Param(value = "userId") Long useid);
}
