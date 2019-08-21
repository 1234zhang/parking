package com.netplus.catpark.dao.generator;

import com.netplus.catpark.domain.po.UserParkingOrderTable;
import com.netplus.catpark.domain.po.UserParkingOrderTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserParkingOrderTableMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserParkingOrderTable record);

    int insertSelective(UserParkingOrderTable record);

    List<UserParkingOrderTable> selectByExample(UserParkingOrderTableExample example);

    UserParkingOrderTable selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserParkingOrderTable record, @Param("example") UserParkingOrderTableExample example);

    int updateByExample(@Param("record") UserParkingOrderTable record, @Param("example") UserParkingOrderTableExample example);

    int updateByPrimaryKeySelective(UserParkingOrderTable record);

    int updateByPrimaryKey(UserParkingOrderTable record);
}