package com.netplus.catpark.controller;

import com.netplus.catpark.domain.dto.PageDTO;
import com.netplus.catpark.domain.dto.PublishOrderListDTO;
import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.service.PublishOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Brandon.
 * @date 2019/8/26.
 * @time 19:30.
 */

@RestController
@RequestMapping("/publish")
@Api(tags = "发布共享车位者的订单", description = "发布共享车位的人的相关订单")
public class PublishOrderTableController {

    @Autowired
    PublishOrderService publishOrderService;

    /**
     * 获取全部订单
     * @param pageDTO 分页参数
     * @return Response
     */
    @PostMapping("/getAll")
    public Response<PublishOrderListDTO> getAll(@RequestBody
                                                    @ApiParam(name = "pageDTO", value = "分页参数，使用json请求", required = true)
                                                            PageDTO pageDTO){
        return publishOrderService.getAll(pageDTO, (byte)9);
    }
}
