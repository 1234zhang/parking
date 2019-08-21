package com.netplus.catpark.controller;

import com.netplus.catpark.domain.dto.*;
import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.service.ShareSpaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Brandon.
 * @date 2019/8/17.
 * @time 16:03.
 */

@RestController
@RequestMapping("/share")
@Api(tags = "共享车位接口合集", description = "共享车位的相关接口")
public class ShareParkingSpaceController {

    @Autowired
    ShareSpaceService shareSpaceService;

    @PostMapping("/phoneCheck")
    @ApiOperation("用于获取验证码")
    public Response<IsSuccessDTO> phoneCheck(@RequestParam
                                               @ApiParam(name = "phoneNum", value = "使用url请求", required = true)
                                                       String phoneNum){
        return shareSpaceService.phoneCheck(phoneNum);
    }
    @PostMapping("/publishSpace")
    @ApiOperation("用于发布共享停车位")
    public Response<IsSuccessDTO> publishSpace(@RequestBody
                                                        @ApiParam(name = "publishSpaceDTO", value = "使用json请求", required = true)
                                                            PublishSpaceDTO publishSpaceDTO){
        return shareSpaceService.publishSpace(publishSpaceDTO);
    }
    @PostMapping("/getNearbySpace")
    @ApiOperation("获取附近停车位信息")
    public Response<ParkingListDTO> getNearbySpace(@RequestBody
                                                         @ApiParam(name = "positionDTO", value = "使用json请求",required = true)
                                                                 PositionDTO positionDTO){
        return shareSpaceService.getNearbyParkingList(positionDTO);
    }
    @GetMapping("/getSpaceInfo")
    @ApiOperation("获取共享车位信息")
    public Response<UserSpaceInfoDTO> getSpaceInfo(@RequestParam
                                                       @ApiParam(name = "userParkingId", value = "用户共享车位id，使用URL请求", required = true)
                                                               Long userParkingId){
        return shareSpaceService.getSpaceInfo(userParkingId);
    }

    @PostMapping("/bookShareParkingSpace")
    @ApiOperation("预定车位")
    public Response<UserParkingBookDTO> bookParkingSpace(@RequestBody
                                                       @ApiParam(name = "bookUserParkingInfoDTO", value = "预定用户停车位 ，使用json请求",required = true)
                                                               BookUserParkingInfoDTO bookUserParkingInfoDTO){
        return shareSpaceService.bookUserParking(bookUserParkingInfoDTO);
    }
}
