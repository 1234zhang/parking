package com.netplus.catpark.dao.generator;

import com.netplus.catpark.domain.po.ParkingOrderTable;
import com.netplus.catpark.domain.po.ParkingOrderTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParkingOrderTableMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ParkingOrderTable record);

    int insertSelective(ParkingOrderTable record);

    List<ParkingOrderTable> selectByExample(ParkingOrderTableExample example);

    ParkingOrderTable selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ParkingOrderTable record, @Param("example") ParkingOrderTableExample example);

    int updateByExample(@Param("record") ParkingOrderTable record, @Param("example") ParkingOrderTableExample example);

    int updateByPrimaryKeySelective(ParkingOrderTable record);

    int updateByPrimaryKey(ParkingOrderTable record);
}