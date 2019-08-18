package com.netplus.catpark.dao.define;

import com.netplus.catpark.domain.po.ParkingPosition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Brandon.
 * @date 2019/8/18.
 * @time 14:55.
 */

public interface ParkingPositionDefineMapper {
    /**
     * 根据模糊搜索查看附近的停车场
     * @param geoHash
     * @return
     */
    List<ParkingPosition> selectParkingList(@Param(value = "geoHash") String geoHash);
}
