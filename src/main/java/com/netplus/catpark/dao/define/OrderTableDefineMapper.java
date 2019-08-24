package com.netplus.catpark.dao.define;

import org.apache.ibatis.annotations.Param;

/**
 * @author Brandon.
 * @date 2019/8/24.
 * @time 10:53.
 */

public interface OrderTableDefineMapper {

    int cancelOrder(@Param("orderId") String orderId);
}
