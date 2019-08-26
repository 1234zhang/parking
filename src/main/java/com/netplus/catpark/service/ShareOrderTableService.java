package com.netplus.catpark.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.netplus.catpark.dao.define.UserDefineMapper;
import com.netplus.catpark.dao.define.UserParkingDefineMapper;
import com.netplus.catpark.dao.define.UserParkingOrderTableDefineMapper;
import com.netplus.catpark.dao.generator.UserParkingOrderTableMapper;
import com.netplus.catpark.domain.bo.ShareOrderInfoBO;
import com.netplus.catpark.domain.dto.IsSuccessDTO;
import com.netplus.catpark.domain.dto.PageDTO;
import com.netplus.catpark.domain.dto.ShareOrderListDTO;
import com.netplus.catpark.domain.enums.OrderStatusEnums;
import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.domain.model.exception.ErrorUtil;
import com.netplus.catpark.domain.po.User;
import com.netplus.catpark.domain.po.UserParking;
import com.netplus.catpark.domain.po.UserParkingOrderTable;
import com.netplus.catpark.domain.po.UserParkingOrderTableExample;
import com.netplus.catpark.service.util.ListStreamUtil;
import com.netplus.catpark.service.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Brandon.
 * @date 2019/8/24.
 * @time 22:38.
 */

@Service
@Slf4j
public class ShareOrderTableService {

    @Autowired
    UserParkingOrderTableMapper userParkingOrderTableMapper;

    @Autowired
    UserParkingDefineMapper userParkingDefineMapper;

    @Autowired
    UserDefineMapper userDefineMapper;

    @Autowired
    UserParkingOrderTableDefineMapper userParkingOrderTableDefineMapper;


    /**
     * 获取订单列表
     * @param pageDTO
     * @param status
     * @return
     */
    public Response<ShareOrderListDTO> getResponseList(PageDTO pageDTO, Byte status){
        if(pageDTO.getCount() == null || pageDTO.getCount() == null){
            return ResponseUtil.makeFail("参数为空");
        }
        Long userId = 1L;
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getCount());
        UserParkingOrderTableExample example = new UserParkingOrderTableExample();
        example.setOrderByClause("gmt_create desc");
        example.createCriteria().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        //获取订单列表
        List<UserParkingOrderTable> userParkingOrderTables = userParkingOrderTableMapper.selectByExample(example);
        PageInfo info = new PageInfo(userParkingOrderTables);

        if(userParkingOrderTables == null || userParkingOrderTables.size() == 0){
            return ResponseUtil.makeFail("没有数据");
        }


        return combinationList(userParkingOrderTables,status);
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    public Response<IsSuccessDTO> cancelOrder(String orderId){
        if(orderId == null){
            return ResponseUtil.makeFail("参数为空");
        }

        Long userId = 1L;
        UserParkingOrderTableExample example = new UserParkingOrderTableExample();
        example.createCriteria().andOrderIdEqualTo(orderId).andDeletedEqualTo(false);
        List<UserParkingOrderTable> userParkingOrderTables = userParkingOrderTableMapper.selectByExample(example);
        UserParkingOrderTable orderTable = userParkingOrderTables.get(0);
        if(!userId.equals(orderTable.getUserId())){
            return ResponseUtil.makeFail("非本人用户不能操作");
        }
        if(!orderTable.getOrderStatus().equals((byte)5)){
            return ResponseUtil.makeFail("不是预定订单，不能取消");
        }
        userParkingOrderTableDefineMapper.cancelOrder(orderId);
        return new Response<IsSuccessDTO>(0,"success", IsSuccessDTO.
                builder().
                isSuccess(true).
                build());
    }

    /**
     * 组装订单列表5
     * @param list
     * @param status
     * @return
     */
    public Response<ShareOrderListDTO> combinationList(List<UserParkingOrderTable> list, Byte status){
        //获取到共享车位的详情
        List<Long> userParkingIdList = ListStreamUtil.getList(UserParkingOrderTable::getUserParkingId, list);
        List<UserParking> userParkingByParkingId = userParkingDefineMapper.getUserParkingByParkingId(userParkingIdList);
        Map<Long, UserParking> userParkingMap = ListStreamUtil.getMap(userParkingByParkingId, UserParking::getId, userParking->userParking);

        // 获取共享车位主人的信息
        List<Long> userId = ListStreamUtil.getList(UserParking::getUserId,userParkingByParkingId);
        List<User> users = userDefineMapper.getUserList(userId);
        Map<Long, User> userIdMap = ListStreamUtil.getMap(users, User::getId, user->user);

        List<ShareOrderInfoBO> result = getResult(list, userParkingMap, userIdMap, status);
        return new Response<ShareOrderListDTO>(0,"success", ShareOrderListDTO.
                builder().
                orderList(result).
                build());
    }
    public List<ShareOrderInfoBO> getResult(List<UserParkingOrderTable> list, Map<Long, UserParking> userParkingMap, Map<Long, User> userIdMap, Byte status){
        List<ShareOrderInfoBO> getAllOrderTable = new ArrayList<>();
        List<ShareOrderInfoBO> getSuccessOrderTable = new ArrayList<>();
        List<ShareOrderInfoBO> getFailOrderTable = new ArrayList<>();
        List<ShareOrderInfoBO> getDoingOrderTable = new ArrayList<>();

        list.forEach(b->{
            User user = userIdMap.get(b.getUserId());
            UserParking userParking = userParkingMap.get(b.getUserParkingId());
            ShareOrderInfoBO build = ShareOrderInfoBO.builder().
                    address(userParking.getAddress()).
                    parkingType(userParking.getParkingType()).
                    parkingTime(dateOperation(b.getBeginParkingTime(), b.getParikingTime())).
                    price(b.getPrice()).orderId(b.getOrderId()).
                    licensePlate(b.getLicensePlate()).
                    gmtCreate(b.getGmtCreate()).
                    orderStatus(b.getOrderStatus()).payment(b.getPayment()).
                    phoneNum(user.getPhoneNum()).build();
            if(build.getOrderStatus().equals(OrderStatusEnums.ORDER_FAIL.getOrderStatus())
                    || build.getOrderStatus().equals(OrderStatusEnums.ORDER_CANCEL.getOrderStatus())){
                getFailOrderTable.add(build);
            }else if(build.getOrderStatus().equals(OrderStatusEnums.ORDER_DOING.getOrderStatus())
                    || build.getOrderStatus().equals(OrderStatusEnums.ORDER_FINISH_UNPAY.getOrderStatus())
                    || build.getOrderStatus().equals(OrderStatusEnums.BOOK_PARKING_SPACE.getOrderStatus())){
                getDoingOrderTable.add(build);
            }else if(build.getOrderStatus().equals(OrderStatusEnums.ORDER_SUCCESS.getOrderStatus())){
                getSuccessOrderTable.add(build);
            }
            getAllOrderTable.add(build);
        });
        if(status.equals(OrderStatusEnums.ORDER_FAIL.getOrderStatus())){
            return getFailOrderTable;
        }
        if(status.equals(OrderStatusEnums.ORDER_DOING.getOrderStatus())){
            return getDoingOrderTable;
        }
        if(status.equals(OrderStatusEnums.ORDER_SUCCESS.getOrderStatus())){
            return getSuccessOrderTable;
        }
        return getAllOrderTable;
    }


    private String dateOperation(Date beginParking, int time){
        long beginTime = beginParking.getTime();
        long endTime = beginTime + time * 60 * 60 * 1000;
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        String begin = sdf.format(beginTime);
        String end = sdf.format(endTime);
        StringBuilder sb = new StringBuilder();
        return sb.append(begin).append("~").append(end).toString();
    }
}
