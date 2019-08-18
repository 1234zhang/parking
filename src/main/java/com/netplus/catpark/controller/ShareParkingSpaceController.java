package com.netplus.catpark.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Brandon.
 * @date 2019/8/17.
 * @time 16:03.
 */

@RestController
@RequestMapping("/share")
@Api(tags = "共享车位接口合集", description = "共享车位的相关接口")
public class ShareParkingSpaceController {

    @GetMapping("/test")
    public void test(){

    }
}
