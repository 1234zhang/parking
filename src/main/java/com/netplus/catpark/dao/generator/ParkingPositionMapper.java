package com.netplus.catpark.dao.generator;

import com.netplus.catpark.domain.po.ParkingPosition;
import com.netplus.catpark.domain.po.ParkingPositionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParkingPositionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ParkingPosition record);

    int insertSelective(ParkingPosition record);

    List<ParkingPosition> selectByExample(ParkingPositionExample example);

    ParkingPosition selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ParkingPosition record, @Param("example") ParkingPositionExample example);

    int updateByExample(@Param("record") ParkingPosition record, @Param("example") ParkingPositionExample example);

    int updateByPrimaryKeySelective(ParkingPosition record);

    int updateByPrimaryKey(ParkingPosition record);
}