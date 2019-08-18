package com.netplus.catpark.dao.generator;

import com.netplus.catpark.domain.po.UserLicenseRel;
import com.netplus.catpark.domain.po.UserLicenseRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserLicenseRelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserLicenseRel record);

    int insertSelective(UserLicenseRel record);

    List<UserLicenseRel> selectByExample(UserLicenseRelExample example);

    UserLicenseRel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserLicenseRel record, @Param("example") UserLicenseRelExample example);

    int updateByExample(@Param("record") UserLicenseRel record, @Param("example") UserLicenseRelExample example);

    int updateByPrimaryKeySelective(UserLicenseRel record);

    int updateByPrimaryKey(UserLicenseRel record);
}