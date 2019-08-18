package com.netplus.catpark.dao.generator;

import com.netplus.catpark.domain.po.Parking;
import com.netplus.catpark.domain.po.ParkingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParkingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Parking record);

    int insertSelective(Parking record);

    List<Parking> selectByExample(ParkingExample example);

    Parking selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Parking record, @Param("example") ParkingExample example);

    int updateByExample(@Param("record") Parking record, @Param("example") ParkingExample example);

    int updateByPrimaryKeySelective(Parking record);

    int updateByPrimaryKey(Parking record);
}