package com.netplus.catpark.dao.define;

import com.netplus.catpark.domain.po.UserParking;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Brandon.
 * @date 2019/8/20.
 * @time 22:22.
 */

public interface UserParkingDefineMapper {
    List<UserParking> getNearbyParking(@Param(value = "geoHash") String geoHash);
}
