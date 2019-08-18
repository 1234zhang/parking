package com.netplus.catpark.dao.generator;

import com.netplus.catpark.domain.po.ParkingSpace;
import com.netplus.catpark.domain.po.ParkingSpaceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParkingSpaceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ParkingSpace record);

    int insertSelective(ParkingSpace record);

    List<ParkingSpace> selectByExample(ParkingSpaceExample example);

    ParkingSpace selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ParkingSpace record, @Param("example") ParkingSpaceExample example);

    int updateByExample(@Param("record") ParkingSpace record, @Param("example") ParkingSpaceExample example);

    int updateByPrimaryKeySelective(ParkingSpace record);

    int updateByPrimaryKey(ParkingSpace record);
}