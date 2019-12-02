package com.netplus.catpark.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.netplus.catpark.dao.define.OrderTableDefineMapper;
import com.netplus.catpark.dao.define.ParkingDefineMapper;
import com.netplus.catpark.dao.elasticsearch.mapper.EsOrderMapper;
import com.netplus.catpark.dao.generator.OrderTableMapper;
import com.netplus.catpark.domain.bo.ContextUser;
import com.netplus.catpark.domain.bo.OrderInfoBo;
import com.netplus.catpark.domain.dto.*;
import com.netplus.catpark.domain.enums.OrderStatusEnums;
import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.domain.po.OrderTable;
import com.netplus.catpark.domain.po.OrderTableExample;
import com.netplus.catpark.domain.po.Parking;
import com.netplus.catpark.service.util.ListStreamUtil;
import com.netplus.catpark.service.util.ResponseUtil;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Brandon.
 * @date 2019/8/22.
 * @time 16:51.
 */

@Service
public class OrderService {
    //TODO 使用elasticsearch 但是没有调试

    @Autowired
    OrderTableMapper orderTableMapper;
    @Autowired
    OrderTableDefineMapper orderTableDefine;
    @Autowired
    EsOrderMapper esOrderMapper;

    /**
     * 取消预定订单
     * @param orderId
     * @return
     */
    public Response<IsSuccessDTO> cancelOrder(String orderId){
        OrderTableExample example = new OrderTableExample();
        example.createCriteria().andOrderIdEqualTo(orderId).andDeletedEqualTo(false);
        List<OrderTable> orderTables = orderTableMapper.selectByExample(example);
        Long userId = ContextUser.getUserId();
        OrderTable orderTable = orderTables.get(0);
        if(!userId.equals(orderTable.getUserId())){
            return ResponseUtil.makeFail("非本人不能取消预定订单");
        }
        if(orderTable.getOrderStatus() != 5){
            return ResponseUtil.makeFail("非预定订单不能取消");
        }
        orderTableDefine.cancelOrder(orderId);
        return new Response<IsSuccessDTO>(0,"success", IsSuccessDTO.builder().isSuccess(true).build());
    }

    /**
     * @Description: 获取全部订单
     * @author: Brandon
     * @Date: 2019/12/2 22:11
     */
    public Response<List<OrderInfoBo>> getOrder(){
        Long userId = ContextUser.getUserId();
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(new TermQueryBuilder("userId", userId));
        return ResponseUtil.makeSuccess(esOrderMapper.search(builder, OrderInfoBo.class));
    }
    /**
     * @Description: 根据订单状态获取订单
     * @author: Brandon
     * @Date: 2019/12/2 22:12
     */
    public Response<List<OrderInfoBo>> getOrder(Byte status){
        Long userId = ContextUser.getUserId();
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(new TermQueryBuilder("userId", userId));
        builder.query(new TermQueryBuilder("orderStatus", status));
        return ResponseUtil.makeSuccess(esOrderMapper.search(builder, OrderInfoBo.class));
    }
}
