package com.netplus.catpark.dao.generator;

import com.netplus.catpark.domain.po.Community;
import com.netplus.catpark.domain.po.CommunityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommunityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Community record);

    int insertSelective(Community record);

    List<Community> selectByExample(CommunityExample example);

    Community selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Community record, @Param("example") CommunityExample example);

    int updateByExample(@Param("record") Community record, @Param("example") CommunityExample example);

    int updateByPrimaryKeySelective(Community record);

    int updateByPrimaryKey(Community record);
}