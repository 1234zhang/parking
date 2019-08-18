package com.netplus.catpark.dao.define;

import com.netplus.catpark.domain.po.ParkingSpace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Brandon.
 * @date 2019/8/18.
 * @time 16:17.
 */

public interface ParkingSpaceDefineMapper {
    List<ParkingSpace> getEmptySpace(@Param(value = "list") List<Long> list);
}
