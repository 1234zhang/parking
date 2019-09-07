package com.netplus.catpark.dao.generator;

import com.netplus.catpark.domain.po.UserParkingInfo;
import com.netplus.catpark.domain.po.UserParkingInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserParkingInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserParkingInfo record);

    int insertSelective(UserParkingInfo record);

    List<UserParkingInfo> selectByExample(UserParkingInfoExample example);

    UserParkingInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserParkingInfo record, @Param("example") UserParkingInfoExample example);

    int updateByExample(@Param("record") UserParkingInfo record, @Param("example") UserParkingInfoExample example);

    int updateByPrimaryKeySelective(UserParkingInfo record);

    int updateByPrimaryKey(UserParkingInfo record);
}