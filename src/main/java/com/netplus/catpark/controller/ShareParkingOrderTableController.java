package com.netplus.catpark.controller;

import com.netplus.catpark.domain.dto.IsSuccessDTO;
import com.netplus.catpark.domain.dto.PageDTO;
import com.netplus.catpark.domain.dto.ShareOrderListDTO;
import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.service.ShareOrderTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Brandon.
 * @date 2019/8/24.
 * @time 16:17.
 */

@RestController
@RequestMapping("/rent")
@Api(tags = "获取共享订单", description = "获取用户租赁的共享车位的订单列表")
public class ShareParkingOrderTableController {

    @Autowired
    ShareOrderTableService shareOrderTableService;

    @PostMapping("/getAll")
    @ApiOperation("获取所有订单")
    public Response<ShareOrderListDTO> getAllOrderList(@RequestBody
                                                           @ApiParam(name = "pageDTO", value = "分页参数，使用json请求", required = true)
                                                                   PageDTO pageDTO){
        return shareOrderTableService.getResponseList(pageDTO, (byte)9);
    }

    @PostMapping("/getFail")
    @ApiOperation("获取失败订单")
    public Response<ShareOrderListDTO> getFailListOrder(@RequestBody
                                                            @ApiParam(name = "pageDTO", value = "分页参数，使用json请求", required = true)
                                                                    PageDTO pageDTO){
        return shareOrderTableService.getResponseList(pageDTO, (byte)0);
    }


    @PostMapping("/getDoing")
    @ApiOperation("获取正在进行中的订单")
    public Response<ShareOrderListDTO> getDoingOrder(@RequestBody
                                                         @ApiParam(name = "pageDTO", value = "分页参数，使用json请求", required = true)
                                                                 PageDTO pageDTO){
        return shareOrderTableService.getResponseList(pageDTO, (byte)1);
    }


    @PostMapping("/getSuccess")
    @ApiOperation("获取成功订单")
    public Response<ShareOrderListDTO> getSuccessOrder(@RequestBody
                                                           @ApiParam(name = "pageDTO", value = "分页参数，使用json请求", required = true)
                                                                   PageDTO pageDTO){
        return shareOrderTableService.getResponseList(pageDTO, (byte)4);
    }

    @PostMapping("/cancel")
    @ApiOperation("取消订单")
    public Response<IsSuccessDTO> cancelOrder(@RequestParam
                                                  @ApiParam(name = "orderId", value = "订单id，使用URL请求", required = true)
                                                          String orderId){
        return shareOrderTableService.cancelOrder(orderId);
    }
}
