package com.netplus.catpark.dao.generator;

import com.netplus.catpark.domain.po.PublishOrderTable;
import com.netplus.catpark.domain.po.PublishOrderTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PublishOrderTableMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PublishOrderTable record);

    int insertSelective(PublishOrderTable record);

    List<PublishOrderTable> selectByExample(PublishOrderTableExample example);

    PublishOrderTable selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PublishOrderTable record, @Param("example") PublishOrderTableExample example);

    int updateByExample(@Param("record") PublishOrderTable record, @Param("example") PublishOrderTableExample example);

    int updateByPrimaryKeySelective(PublishOrderTable record);

    int updateByPrimaryKey(PublishOrderTable record);
}