package com.netplus.catpark.controller;

import com.netplus.catpark.domain.dto.TestDTO;
import com.netplus.catpark.domain.model.Response;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author Brandon.
 * @date 2019/8/17.
 * @time 13:51.
 */

@RestController
@RequestMapping("/test")
@Api(tags = "测试接口", description = "用于测试swagger 接口")
public class TestController {
    @ApiOperation("测试接口")
    @GetMapping("/test")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "test", value = "测试swagger", paramType = "query", example = "1",required = true),
            @ApiImplicitParam(name = "test1", value = "测试数据1", paramType = "query", example = "1",required = true)
    })
    public Response<TestDTO> test(@RequestParam int test, int test1){
        System.out.println(test);
        System.out.println(test1);
        return new Response<TestDTO>(0,"hello world",TestDTO.builder().build());
    }

    @ApiOperation("ReqeustBodyTest")
    @PostMapping("/bodyTest")
    public Response<TestDTO> test1(@RequestBody
                              @ApiParam(name = "testDTO", value = "使用json请求", required = true) TestDTO testDTO){
        return new Response<TestDTO>(0,"hello world", TestDTO.builder().build());
    }
}
