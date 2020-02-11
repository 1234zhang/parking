package com.netplus.catpark.controller;

import com.netplus.catpark.domain.dto.*;
import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.service.BookParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Brandon.
 * @date 2019/8/17.
 * @time 16:00.
 */

@RestController
@RequestMapping("/book")
@Api(tags = "预定车位接口", description = "用于车位的预定，以及寻找附近车位")
public class BookParkingSpaceController {
    @Autowired
    BookParkingService bookParkingService;
    @PostMapping("/getNearByParking")
    @ApiOperation("获取附近停车场列表")


    public Response<ParkingListDTO> getParkingList(@RequestBody
                                                       @ApiParam(name = "positionDTO", value = "使用json请求", required = true)
                                                           PositionDTO positionDTO){
        return bookParkingService.getNearbyParking(positionDTO.getLat(), positionDTO.getLng());
    }

    @PostMapping("/getParkingSpace")
    @ApiOperation("获取空车位的列表")
    public Response<SpaceListDTO> getSpaceList(@RequestBody
                                                   @ApiParam(name = "spaceParamDTO", value = "使用json请求", required = true)
                                                       SpaceParamDTO spaceParamDTO){
        return bookParkingService.getFreeSpaceList(spaceParamDTO);
    }

    @PostMapping("/bookParkingSpace")
    @ApiOperation("预定停车位")
    public Response<BookSuccessDTO> bookParkingSpace(@RequestBody
                                                         @ApiParam(name = "bookParkingSpaceDTO",value = "使用json请求", required = true)
                                                                 BookParkingSpaceDTO bookParkingSpaceDTO){
        return bookParkingService.bookParkingSpace(bookParkingSpaceDTO);
    }

    @PostMapping("/findWay")
    @ApiOperation("寻找停车场位置")
    public Response<FindWayDTO> getPosition(@RequestBody
                                      @ApiParam(name = "findWayDTO", value = "使用json请求", required = true)
                                              PositionIdDTO positionIdDTO){
        return bookParkingService.findWay(positionIdDTO);
    }
}
