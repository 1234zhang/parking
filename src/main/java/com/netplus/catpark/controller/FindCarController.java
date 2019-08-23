package com.netplus.catpark.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Brandon.
 * @date 2019/8/17.
 * @time 16:04.
 */

@RestController
@RequestMapping("/find")

@Api(tags = "寻找车接口", description = "寻找自己车停的位置")
public class FindCarController {


}
