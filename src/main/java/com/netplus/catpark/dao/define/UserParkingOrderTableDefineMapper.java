package com.netplus.catpark.dao.define;

import org.apache.ibatis.annotations.Param;

/**
 * @author Brandon.
 * @date 2019/8/24.
 * @time 23:35.
 */

public interface UserParkingOrderTableDefineMapper {
    int cancelOrder(@Param("orderId") String orderId);
}
