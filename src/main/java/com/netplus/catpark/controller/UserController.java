package com.netplus.catpark.controller;

import com.netplus.catpark.domain.dto.*;
import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.service.ShareSpaceService;
import com.netplus.catpark.service.UserService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author Brandon.
 * @date 2019/8/27.
 * @time 14:32.
 */

@RestController
@RequestMapping("/user")
@ApiOperation("用户相关接口")
public class UserController {


    @Autowired
    UserService userService;

    @Autowired
    ShareSpaceService shareSpaceService;

    @PostMapping("/login")
    @ApiOperation("微信验证接口")
    public Response<UserReturnDTO> minUserLogin(@RequestBody
                                                    @ApiParam(name = "checkoutInfoDTO", value = "用于微信验证的用户信息存储，使用json请求" ,required = true)
                                                    @Validated CheckUserInfoDTO minUser, BindingResult result) throws IOException {
        if(result.hasErrors()){
            throw new RuntimeException(result.getFieldError().getDefaultMessage());
        }
        return userService.minLogin(minUser);
    }

@GetMapping("/inspect")
    @ApiOperation("判断用户是否绑定了手机号")
    public Response<IsBindPhoneNumDTO> checkPhoneNumBind(){
        return userService.checkBindPhoneNum();
    }

    @GetMapping("/getAuthCode")
    @ApiModelProperty("用户绑定手机时获取验证码")
    public Response<IsSuccessDTO> getAuthCode(@RequestParam
                                                  @ApiParam(name = "phoneNum", value = "获取验证码，使用url请求", required = true)
                                                          String phoneNum){
        return shareSpaceService.phoneCheck(phoneNum);
    }

    @PostMapping("/checkAuthCode")
    @ApiOperation("验证用户绑定手机时的验证码")
    public Response<IsSuccessDTO> checkAuthCode(@RequestBody
                                                    @ApiParam(name = "checkPhoneCodeCheckDTO", value = "用于验证手机号码和验证码，使用json请求", required = true)
                                                PhoneNumCodeCheckDTO phoneNumCodeCheckDTO){
        return userService.checkAuthCode(phoneNumCodeCheckDTO);
    }

    @PostMapping("/insertLicensePlate")
    @ApiOperation("添加用户车牌")
    public Response<IsSuccessDTO> insertLicense(@RequestParam
                                                    @ApiParam(name = "licensePlate", value = "添加用户车牌", required = true)
                                                            String licensePlate){
        return userService.insertLicense(licensePlate);
    }

    @PostMapping("/saveParkingInfo")
    @ApiOperation("用户添加车位信息")
    public Response<IsSuccessDTO> saveParkingPosition(@RequestBody
                                                      @ApiParam(name = "parkingPosition", value = "添加用户停车位", required = true)
                                                              ParkingInfoDTO parkingInfoDTO){
        return userService.saveParkingInfo(parkingInfoDTO);
    }

    @GetMapping("/deleteParkingInfo")
    @ApiOperation("删除用户的车位")
    public Response<IsSuccessDTO> deletedParkingInfo(@RequestParam
                                                         @ApiParam(name = "parkingId", value = "用户车位id", required =  true)
                                                            Long parkingId){
        return userService.deletedParkingInfo(parkingId);



    }
    @GetMapping("/getPlateList")
    @ApiOperation("获取用户车牌列表")
    public Response<UserPlateLianceDTO> getPlateList(){
        return userService.getPlate();
    }
}
