package com.netplus.catpark.dao.define;

import com.netplus.catpark.domain.po.UserParkingOrderTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Brandon.
 * @date 2019/8/24.
 * @time 23:35.
 */

public interface UserParkingOrderTableDefineMapper {
    int cancelOrder(@Param("orderId") String orderId);

    List<UserParkingOrderTable> getOrderListByOrder(@Param("list") List<String> list);
}
