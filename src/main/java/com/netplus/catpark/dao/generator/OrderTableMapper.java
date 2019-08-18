package com.netplus.catpark.dao.generator;

import com.netplus.catpark.domain.po.OrderTable;
import com.netplus.catpark.domain.po.OrderTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderTableMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderTable record);

    int insertSelective(OrderTable record);

    List<OrderTable> selectByExample(OrderTableExample example);

    OrderTable selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderTable record, @Param("example") OrderTableExample example);

    int updateByExample(@Param("record") OrderTable record, @Param("example") OrderTableExample example);

    int updateByPrimaryKeySelective(OrderTable record);

    int updateByPrimaryKey(OrderTable record);
}