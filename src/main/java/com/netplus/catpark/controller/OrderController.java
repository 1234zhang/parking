package com.netplus.catpark.controller;

import com.netplus.catpark.domain.dto.IsSuccessDTO;
import com.netplus.catpark.domain.dto.PageDTO;
import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Brandon.
 * @date 2019/8/21.
 * @time 18:33.
 */

@RestController
@RequestMapping("/order")
@Api(tags = "订单接口", description = "订单相关接口")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/getAllOrder")
    @ApiOperation("获取所有订单")
    public Response getAllOrder(@RequestBody
                                                  @ApiParam(name = "pageDTO", value = "分页参数，使用json请求", required = true)
                                                          PageDTO pageDTO){
        return orderService.getOrder();
    }

    @PostMapping("/getFailOrder")
    @ApiOperation("获取失败订单")
    public Response getFailOrder(@RequestBody
                                     @ApiParam(name = "pageDTO", value = "分页参数，使用json请求", required = true)
                                             PageDTO pageDTO){
        return orderService.getOrder((byte)0);
    }

    @PostMapping("/getDoingOrder")
    @ApiOperation("获取正在进行的订单")
    public Response getDoingOrder(@RequestBody
                                      @ApiParam(name = "pageDTO", value = "分页参数，使用json请求", required = true)
                                              PageDTO pageDTO){
        return orderService.getOrder((byte)1);
    }
    @PostMapping("/getSuccessOrder")
    @ApiOperation("获取成功的订单")
    public Response getSuccessOrder(@RequestBody
                                        @ApiParam(name = "pageDTO", value = "分页参数，使用json请求", required = true)
                                                PageDTO pageDTO){
        return orderService.getOrder((byte)4);
    }
    @PostMapping("/cancelOrder")
    @ApiOperation("取消订单")
    public Response<IsSuccessDTO> cancelOrder(@RequestParam
                                                  @ApiParam(name = "orderId", value = "订单id，使用url请求", required = true)
                                                          String orderId){
        return orderService.cancelOrder(orderId);
    }
}
