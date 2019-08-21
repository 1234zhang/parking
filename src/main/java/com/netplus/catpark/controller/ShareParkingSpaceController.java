package com.netplus.catpark.controller;

import com.netplus.catpark.domain.dto.ParkingListDTO;
import com.netplus.catpark.domain.dto.PositionDTO;
import com.netplus.catpark.domain.dto.PublishSpaceDTO;
import com.netplus.catpark.domain.dto.IsSuccessDTO;
import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.service.ShareSpaceService;
import io.swagger.annotations.Api;
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
    public Response<IsSuccessDTO> phoneCheck(@RequestParam
                                               @ApiParam(name = "phoneNum", value = "使用url请求", required = true)
                                                       String phoneNum){
        return shareSpaceService.phoneCheck(phoneNum);
    }
    @PostMapping("/publishSpace")
    public Response<IsSuccessDTO> publishSpace(@RequestBody
                                                        @ApiParam(name = "publishSpaceDTO", value = "使用json请求", required = true)
                                                            PublishSpaceDTO publishSpaceDTO){
        return shareSpaceService.publishSpace(publishSpaceDTO);
    }
    @PostMapping("/getNearbySpace")
    public Response<ParkingListDTO> getNearbySpace(@RequestBody
                                                         @ApiParam(name = "positionDTO", value = "使用json请求",required = true)
                                                                 PositionDTO positionDTO){
        return shareSpaceService.getNearbyParkingList(positionDTO);
    }
}
