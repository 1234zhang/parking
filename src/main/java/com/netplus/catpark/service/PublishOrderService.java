package com.netplus.catpark.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.netplus.catpark.dao.define.UserDefineMapper;
import com.netplus.catpark.dao.define.UserParkingDefineMapper;
import com.netplus.catpark.dao.define.UserParkingOrderTableDefineMapper;
import com.netplus.catpark.dao.generator.*;
import com.netplus.catpark.domain.bo.ContextUser;
import com.netplus.catpark.domain.bo.PublishOrderInfoBO;
import com.netplus.catpark.domain.dto.PageDTO;
import com.netplus.catpark.domain.dto.PublishOrderListDTO;
import com.netplus.catpark.domain.enums.OrderStatusEnums;
import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.domain.po.*;
import com.netplus.catpark.service.util.ListStreamUtil;
import com.netplus.catpark.service.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Brandon.
 * @date 2019/8/26.
 * @time 20:08.
 */

@Service
@Slf4j
public class PublishOrderService {

    @Autowired
    PublishOrderTableMapper publishOrderTableMapper;

    @Autowired
    UserDefineMapper userDefineMapper;

    @Autowired
    UserParkingDefineMapper userParkingDefineMapper;

    @Autowired
    UserParkingOrderTableMapper userParkingOrderTableMapper;

    @Autowired
    UserParkingOrderTableDefineMapper userParkingOrderTableDefineMapper;

    @Autowired
    UserParkingMapper userParkingMapper;

    @Autowired
    UserParkingInfoMapper userParkingInfoMapper;

    @Autowired
    UserMapper userMapper;
    /**
     * 根据订单状态返回订单类型
     * @param pageDTO 分页参数
     * @param status 订单类型 9 表示全部， 0 表示失败 1 表示正在进行 4 表示成功
     * @return Response<PublishOrderListDTO>
     */
    public Response<PublishOrderListDTO> getAll(PageDTO pageDTO, Byte status){
        if(pageDTO.getCount() == null || pageDTO.getPage() == null){
            return ResponseUtil.makeFail("参数不能为空");
        }
        Long userId = ContextUser.getUserId();
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getCount());
        //获取订单列表
        PublishOrderTableExample example = new PublishOrderTableExample();
        example.setOrderByClause("gmt_create desc");
        example.createCriteria().andPublishUserIdEqualTo(userId).andDeletedEqualTo(false);
        List<PublishOrderTable> publishOrderTables = publishOrderTableMapper.selectByExample(example);

        PageInfo pageInfo = new PageInfo(publishOrderTables);

        //获取租赁用户的集合
        List<Long> rentUserIdList = ListStreamUtil.getList(PublishOrderTable::getPublishUserId, publishOrderTables);
        List<User> userList = userDefineMapper.getUserList(rentUserIdList);
        Map<Long, User> rentUserInfoMap = ListStreamUtil.getMap(userList, User::getId, user->user);

        // 获取共享停车位的集合
        List<Long> parkingIdList = ListStreamUtil.getList(PublishOrderTable::getParkingId, publishOrderTables);
        List<UserParking> userParkingByParkingId = userParkingDefineMapper.getUserParkingByParkingId(parkingIdList);
        Map<Long, UserParking> userParkingMap = ListStreamUtil.getMap(userParkingByParkingId, UserParking::getId, userparking->userparking);

        List<PublishOrderInfoBO> publishOrderInfoBOS = combinationResult(publishOrderTables, rentUserInfoMap, userParkingMap, status);
        return new Response<PublishOrderListDTO>(0,"success", PublishOrderListDTO.
                builder().
                list(publishOrderInfoBOS).
                build());
    }

    /**
     * 组装订单
     * @param list
     * @param userMap
     * @param userParkingMap
     * @param status
     * @return
     */
    private List<PublishOrderInfoBO> combinationResult(List<PublishOrderTable> list,
                                                            Map<Long, User> userMap,
                                                            Map<Long, UserParking> userParkingMap,
                                                            Byte status){
        List<PublishOrderInfoBO> allOrderList = new ArrayList<>();
        List<PublishOrderInfoBO> failOrderList = new ArrayList<>();
        List<PublishOrderInfoBO> successOrderList = new ArrayList<>();
        List<PublishOrderInfoBO> doingOrderList = new ArrayList<>();

        List<String> orderIdList = ListStreamUtil.getList(PublishOrderTable::getOrderId, list);
        List<UserParkingOrderTable> orderListByOrder = userParkingOrderTableDefineMapper.getOrderListByOrder(orderIdList);
        Map<String, UserParkingOrderTable> userParkingOrderTableMap = ListStreamUtil.getMap(orderListByOrder, UserParkingOrderTable::getOrderId,userParkingOrderTable -> userParkingOrderTable);

        list.forEach(b->{
            UserParking userParking = userParkingMap.get(b.getParkingId());
            User user = userMap.get(b.getRentUserId());
            UserParkingOrderTable userParkingOrderTable = userParkingOrderTableMap.get(b.getOrderId());
            PublishOrderInfoBO build = PublishOrderInfoBO.builder().
                    orderId(b.getOrderId()).
                    gmtCreate(b.getGmtCreate()).
                    phoneNum(user.getPhoneNum()).
                    nickName(user.getNickName()).
                    parkingType(userParking.getParkingType()).
                    price(userParkingOrderTable.getPrice()).
                    payment(userParking.getPayment()).
                    orderStatus(userParkingOrderTable.getOrderStatus()).
                    build();
            PublishOrderInfoBO publishOrderInfoBO = operationDate(build, userParkingOrderTable, userParking);
            if(build.getOrderStatus().equals(OrderStatusEnums.ORDER_FAIL.getOrderStatus())
                    || build.getOrderStatus().equals(OrderStatusEnums.ORDER_CANCEL.getOrderStatus())){
                failOrderList.add(build);
            }
            if(build.getOrderStatus().equals(OrderStatusEnums.ORDER_DOING.getOrderStatus())
                    || build.getOrderStatus().equals(OrderStatusEnums.BOOK_PARKING_SPACE.getOrderStatus())
                    || build.getOrderStatus().equals(OrderStatusEnums.ORDER_FINISH_UNPAY.getOrderStatus())){
                doingOrderList.add(build);
            }
            if(build.getOrderStatus().equals(OrderStatusEnums.ORDER_SUCCESS.getOrderStatus())){
                successOrderList.add(build);
            }
            allOrderList.add(build);
        });
        if(status.equals(OrderStatusEnums.ORDER_FAIL.getOrderStatus())){
            return failOrderList;
        }else if(status.equals(OrderStatusEnums.ORDER_SUCCESS.getOrderStatus())){
            return successOrderList;
        }else if(status.equals(OrderStatusEnums.ORDER_DOING.getOrderStatus())){
            List<PublishOrderInfoBO> publishParking = getPublishParking(ContextUser.getUserId());
            doingOrderList.addAll(publishParking);
            return doingOrderList;
        }else {
            return allOrderList;
        }
    }

    /**
     * 对时间的操作
     * @param build
     * @param userParkingOrderTable
     * @param userParking
     * @return
     */
    private PublishOrderInfoBO operationDate(PublishOrderInfoBO build, UserParkingOrderTable userParkingOrderTable, UserParking userParking){
        build.setBookTime(userParking.getBeginBookTime() + "~" + userParking.getEndBookTime());
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        long beginTime = userParkingOrderTable.getBeginParkingTime().getTime();
        long endTime = beginTime + userParkingOrderTable.getParikingTime() * 60 * 60 * 1000;
        String begin = sdf.format(beginTime);
        String end = sdf.format(endTime);
        sb.append(begin).append("~").append(end);
        build.setRentTime(sb.toString());
        return build;
    }

    private List<PublishOrderInfoBO> getPublishParking(Long userId){
        List<PublishOrderInfoBO> publishOrderInfoBOS = new ArrayList<>();
        UserParkingExample example = new UserParkingExample();
        example.createCriteria().andDeletedEqualTo(false).andUserIdEqualTo(userId);
        List<UserParking> userParkings = userParkingMapper.selectByExample(example);
        userParkings.forEach(parking -> {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andIdEqualTo(userId).andDeletedEqualTo(false);
            User user = userMapper.selectByExample(userExample).get(0);

            UserParkingInfoExample userParkingInfoExample = new UserParkingInfoExample();
            userParkingInfoExample.createCriteria().andDeletedEqualTo(false).andIdEqualTo(parking.getParkingInfoId());
            UserParkingInfo userParkingInfo = userParkingInfoMapper.selectByExample(userParkingInfoExample).get(0);
            PublishOrderInfoBO build = PublishOrderInfoBO.
                    builder().
                    orderStatus((byte) 1).
                    payment(parking.getPayment()).
                    nickName(user.getNickName()).
                    phoneNum(user.getPhoneNum()).
                    address(userParkingInfo.getAddress()).
                    parkingType(parking.getParkingType()).gmtCreate(parking.getGmtCreate()).
                    build();
            publishOrderInfoBOS.add(build);
        });
        return publishOrderInfoBOS;
    }
}
