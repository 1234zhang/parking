package com.netplus.catpark.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.netplus.catpark.dao.define.ParkingDefineMapper;
import com.netplus.catpark.dao.define.ParkingPositionDefineMapper;
import com.netplus.catpark.dao.define.ParkingSpaceDefineMapper;
import com.netplus.catpark.dao.generator.OrderTableMapper;
import com.netplus.catpark.dao.generator.ParkingMapper;
import com.netplus.catpark.dao.generator.ParkingSpaceMapper;
import com.netplus.catpark.domain.bo.ContextUser;
import com.netplus.catpark.domain.bo.ParkingBO;
import com.netplus.catpark.domain.bo.SpaceInfoBO;
import com.netplus.catpark.domain.dto.*;
import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.domain.po.*;
import com.netplus.catpark.service.util.GeoHashUtil.GeoHashHelperUtil;
import com.netplus.catpark.service.util.ListStreamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Brandon.
 * @date 2019/8/18.
 * @time 14:27
 */

@Service
public class BookParkingService {

    @Autowired
    ParkingPositionDefineMapper parkingPositionDefineMapper;

    @Autowired
    ParkingDefineMapper parkingDefineMapper;

    @Autowired
    ParkingSpaceDefineMapper parkingSpaceDefineMapper;

    @Autowired
    ParkingSpaceMapper parkingSpaceMapper;

    @Autowired
    ParkingMapper parkingMapper;

    @Autowired
    OrderTableMapper orderTableMapper;

    /**
     * 获取附近停车场列表
     * @param lat
     * @param lng
     * @return
     */
    public Response<ParkingListDTO> getNearbyParking(double lat, double lng){
        List<String> positionList = GeoHashHelperUtil.around(lat, lng);
        List<ParkingBO> parkingBOList = new ArrayList<>();
        List<ParkingPosition> getNearbyList = new ArrayList<>();
        // 获取到附近的停车场位置
        positionList.forEach(b->{
            getNearbyList.addAll(parkingPositionDefineMapper.selectParkingList(b.substring(0,5)));
        });
        //获取到停车场id
        List<Long> list = ListStreamUtil.getList(ParkingPosition::getParkingId, getNearbyList);
        if(list.isEmpty()){
            return new Response(1,"fail","数据为空");
        }
        // 获取停车场信息
        List<Parking> parkingList = parkingDefineMapper.getParkingList(list);
        //根据停车场id获取到停车场空闲车位数量
        List<ParkingSpace> parkingSpaceList = parkingSpaceDefineMapper.getEmptySpace(list);
        Map<Long, Long> emptySpaceMap = parkingSpaceList.
                stream().
                collect(Collectors.
                        groupingBy(ParkingSpace::getParkingId,Collectors.counting()));
        parkingList.forEach(b->{
            ParkingBO build = ParkingBO.
                    builder().
                    id(b.getId()).
                    parkingName(b.getParkingName()).
                    emptySpace(emptySpaceMap.get(b.getId())).build();
            parkingBOList.add(build);
        });
        return new Response<ParkingListDTO>(0,"success", ParkingListDTO.builder().
                parkingList(Collections.singletonList(parkingBOList)).
                build());
    }

    /**
     * 获取空闲停车位
     * @param spaceParamDTO
     * @return
     */
    public Response<SpaceListDTO> getFreeSpaceList(SpaceParamDTO spaceParamDTO){
        if(spaceParamDTO.getCount() == 0 || spaceParamDTO.getPage() == 0 || spaceParamDTO.getParkingSpaceId() == null){
            return new Response(1,"fail","数据为空");
        }
        PageHelper.startPage(spaceParamDTO.getPage(),spaceParamDTO.getCount());
        ParkingSpaceExample example = new ParkingSpaceExample();
        example.createCriteria().
                andParkingIdEqualTo(spaceParamDTO.getParkingSpaceId()).
                andDeletedEqualTo(false).
                andParkingSpaceStatusEqualTo((byte)0);
        //获取到停车场的空闲车位
        List<ParkingSpace> parkingSpaceList = parkingSpaceMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(parkingSpaceList);
        //获取该停车场的相关信息
        ParkingExample parkingExample = new ParkingExample();
        parkingExample.createCriteria().andIdEqualTo(spaceParamDTO.getParkingSpaceId());
        List<Parking> parkingList = parkingMapper.selectByExample(parkingExample);
        List<SpaceInfoBO> spaceInfoBOList = new ArrayList<>();
        parkingSpaceList.forEach(b->{
            SpaceInfoBO build = SpaceInfoBO.builder().
                    parkingSpaceAddr(parkingList.get(0).getAddress()).
                    parkingSpaceId(b.getParkingSpaceId()).
                    parkingId(b.getParkingId()).
                    lat(b.getLatitude()).
                    lng(b.getLongitude()).
                    parkingSpaceName(parkingList.get(0).getParkingName()).
                    id(b.getId()).
                    build();
            spaceInfoBOList.add(build);
        });
        return new Response<SpaceListDTO>(0,
                "success",
                SpaceListDTO.
                        builder().
                        SpaceInfoList(spaceInfoBOList).
                        build());
    }

    /**
     * 预定车位
     * @param bookParkingSpaceDTO
     * @return
     */
    public Response<BookSuccessDTO> bookParkingSpace(BookParkingSpaceDTO bookParkingSpaceDTO){
        Date date = new Date();
        if(bookParkingSpaceDTO.getParkingId() == null || bookParkingSpaceDTO.getParkingSpaceId() == null){
            return new Response(1,"fail", "数据为空");
        }
        Long userId = ContextUser.getUserId();


        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        OrderTable orderTable = new OrderTable();
        orderTable.setDeleted(false);
        orderTable.setGmtCreate(date);
        orderTable.setGmtUpdate(date);
        orderTable.setOrderStatus((byte)1);
        orderTable.setOrderId(uuid);
        orderTable.setUserId(userId);
        orderTable.setParkingId(bookParkingSpaceDTO.getParkingId());
        orderTable.setParkingSpaceId(bookParkingSpaceDTO.getParkingSpaceId());

        orderTableMapper.insert(orderTable);
        //更新停车位状态
        parkingSpaceDefineMapper.updateParkingSpaceStatus((byte)3,
                bookParkingSpaceDTO.getParkingId(),
                bookParkingSpaceDTO.getParkingSpaceId());
        BookSuccessDTO build = BookSuccessDTO.
                builder().
                parkingId(bookParkingSpaceDTO.getParkingId()).
                parkingSpaceId(bookParkingSpaceDTO.getParkingSpaceId()).
                build();
        return new Response<>(0,"success", build);
    }

    /**
     * 寻路
     * @param positionIdDTO
     * @return
     */
    public Response<FindWayDTO> findWay(PositionIdDTO positionIdDTO){
        if(positionIdDTO.getParkingId() == null || positionIdDTO.getParkingSpaceId() == null){
            return new Response(1,"fail", "参数为空");
        }
        ParkingSpaceExample example = new ParkingSpaceExample();
        example.
                createCriteria().
                andParkingIdEqualTo(positionIdDTO.getParkingId()).
                andParkingSpaceIdEqualTo(positionIdDTO.getParkingSpaceId()).
                andDeletedEqualTo(false);
        List<ParkingSpace> parkingSpaces = parkingSpaceMapper.selectByExample(example);
        FindWayDTO build = FindWayDTO.builder().lat(parkingSpaces.get(0).getLatitude()).lng(parkingSpaces.get(0).getLongitude()).build();
        return new Response<>(0,"success",build);
    }
}
