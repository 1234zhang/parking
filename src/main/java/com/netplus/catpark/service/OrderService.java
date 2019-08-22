package com.netplus.catpark.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.netplus.catpark.dao.define.ParkingDefineMapper;
import com.netplus.catpark.dao.generator.OrderTableMapper;
import com.netplus.catpark.domain.bo.OrderInfoBo;
import com.netplus.catpark.domain.dto.*;
import com.netplus.catpark.domain.enums.OrderStatusEnums;
import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.domain.po.OrderTable;
import com.netplus.catpark.domain.po.OrderTableExample;
import com.netplus.catpark.domain.po.Parking;
import com.netplus.catpark.service.util.ListStreamUtil;
import com.netplus.catpark.service.util.ResponseUtil;
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
    //TODO 根据每个分钟数计算总共的时间

    private final Integer payment = 10;

    @Autowired
    OrderTableMapper orderTableMapper;

    @Autowired
    ParkingDefineMapper parkingDefineMapper;

    public  Response<Object> getResponse(PageDTO pageDTO, Byte status){
        if(pageDTO.getPage() == null || pageDTO.getCount() == null){
            return ResponseUtil.makeFail("请求参数为空");
        }
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getCount());
        Long userId = 1L;

        //获取全部订单
        OrderTableExample example = new OrderTableExample();
        example.setOrderByClause("gmt_create desc");
        example.createCriteria().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        List<OrderTable> orderTables = orderTableMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(orderTables);

        //获取停车场id，根据停车场id获取到停车场信息
        List<Long> parkingIdList = ListStreamUtil.getList(OrderTable::getParkingId, orderTables);

        //获取停车场信息
        List<Parking> parkingList = parkingDefineMapper.getParkingList(parkingIdList);
        Map<Long, Parking> idAndParkingMap  = ListStreamUtil.getMap(parkingList, Parking::getId, parking -> parking);
        return getList(orderTables,idAndParkingMap,status);
    }

    /**
     * 根据订单状态分类
     * @param orderTableList
     * @param idAndParkingMap
     * @param status
     * @param <T>
     * @return
     */
    private <T> T getList(List<OrderTable> orderTableList, Map<Long, Parking> idAndParkingMap, Byte status){
        List<OrderInfoBo> orderAllList = new ArrayList<>();
        List<OrderInfoBo> orderFailDTOList = new ArrayList<>();
        List<OrderInfoBo> orderSuccessDTOList = new ArrayList<>();
        List<OrderInfoBo> orderDoingDTOList = new ArrayList<>();

        orderTableList.forEach(b->{
            Parking parking = idAndParkingMap.get(b.getParkingId());
            OrderInfoBo build = OrderInfoBo.builder().
                    address(parking.getAddress()).
                    gmtCreate(b.getGmtCreate()).
                    licensePlate(b.getLicensePlate()).
                    orderStatus(b.getOrderStatus()).
                    build();
            OrderInfoBo orderInfoBo = operationDate(b,build);
            int orderStatus = build.getOrderStatus();
            //正在进行中的订单
            if(orderStatus == OrderStatusEnums.ORDER_DOING.getOrderStatus()
                    || orderStatus == OrderStatusEnums.ORDER_FINISH_UNPAY.getOrderStatus()
                    || orderStatus == OrderStatusEnums.BOOK_PARKING_SPACE.getOrderStatus()){
                orderDoingDTOList.add(orderInfoBo);
            }
            // 失败的订单
            if(orderStatus == OrderStatusEnums.ORDER_FAIL.getOrderStatus()
                    || orderStatus == OrderStatusEnums.ORDER_CANCEL.getOrderStatus()){
                orderFailDTOList.add(orderInfoBo);
            }
            //已经完成的订单
            if(orderStatus == OrderStatusEnums.ORDER_SUCCESS.getOrderStatus()){
                orderSuccessDTOList.add(orderInfoBo);
            }
            // 所有订单
            orderAllList.add(orderInfoBo);
        });

        if(status == OrderStatusEnums.ORDER_DOING.getOrderStatus()){
            OrderDoingDTO build = OrderDoingDTO.builder().orderInfoBoList(orderDoingDTOList).build();
            Response<OrderDoingDTO> success = new Response<>(0, "success", build);
            return (T) success;
        }else if(status == OrderStatusEnums.ORDER_FAIL.getOrderStatus()){
            OrderFailDTO build = OrderFailDTO.builder().orderInfoBoList(orderFailDTOList).build();
            Response<OrderFailDTO> success = new Response<>(0, "Success", build);
            return (T) success;
        }else if(status == OrderStatusEnums.ORDER_SUCCESS.getOrderStatus()){

            OrderSuccessDTO build = OrderSuccessDTO.builder().orderInfoBoList(orderSuccessDTOList).build();
            Response<OrderSuccessDTO> success = new Response<>(0, "success", build);
            return (T) success;
        }else{
            OrderAllDTO build = OrderAllDTO.builder().orderInfoBoList(orderAllList).build();
            Response<OrderAllDTO> success = new Response<>(0, "success", build);
            return (T) success;
        }

    }

    /**
     * 操作订单时间
     * @param b
     * @param orderInfoBo
     * @return
     */
    private OrderInfoBo operationDate(OrderTable b, OrderInfoBo orderInfoBo){
        StringBuilder stringBuilder = new StringBuilder();
        if(b.getEndParkingTime() == null){
            stringBuilder.append(b.getBeginParkingTime()).append("-");
            orderInfoBo.setParkingTime(stringBuilder.toString());
            return orderInfoBo;
        }
        stringBuilder.
                append(b.getBeginParkingTime()).
                append("-").
                append(b.getEndParkingTime());
        orderInfoBo.setParkingTime(stringBuilder.toString());
        long begin = b.getBeginParkingTime().getTime();
        long end = b.getEndParkingTime().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH时mm分ss秒");
        String total = sdf.format(end - begin);
        orderInfoBo.setTotalParkingTime(total);
        return orderInfoBo;
    }
}
