package com.netplus.catpark.dao.generator;

import com.netplus.catpark.domain.po.UserParking;
import com.netplus.catpark.domain.po.UserParkingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserParkingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserParking record);

    int insertSelective(UserParking record);

    List<UserParking> selectByExample(UserParkingExample example);

    UserParking selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserParking record, @Param("example") UserParkingExample example);

    int updateByExample(@Param("record") UserParking record, @Param("example") UserParkingExample example);

    int updateByPrimaryKeySelective(UserParking record);

    int updateByPrimaryKey(UserParking record);
}