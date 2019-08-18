package com.netplus.catpark.dao.define;

import com.netplus.catpark.domain.po.Parking;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Brandon.
 * @date 2019/8/18.
 * @time 15:51.
 */

public interface ParkingDefineMapper {
    List<Parking> getParkingList(@Param(value = "list") List<Long> list);
}
