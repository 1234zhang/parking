package com.netplus.catpark.controller;

import com.netplus.catpark.domain.dto.*;
import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.service.CommunityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apiguardian.api.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Brandon.
 * @date 2019/8/21.
 * @time 22:06.
 */

@RestController
@RequestMapping("/community")
@Api(tags = "社区接口", description = "用于展示和发布相关内容")
public class CommunityController {

    @Autowired
    CommunityService communityService;


    @PostMapping("/publish")
    @ApiOperation("发布相关帖子")
    public Response<IsSuccessDTO> publish(@RequestBody
                                              @ApiParam(name = "communityPublishDTO", value = "使用json请求", required = true)
                                                  CommunityPublishDTO communityPublishDTO){
        return communityService.communityPublish(communityPublishDTO);
    }

    @PostMapping("/show")
    @ApiOperation("展示相关帖子")
    public Response<CommunityListDTO> getCommunityList(@RequestBody
                                                           @ApiParam(name = "page", value = "分页参数，使用json请求", required = true)
                                                                   PageDTO pageDTO){
        return communityService.getTextList(pageDTO);
    }

    @PostMapping("/update")
    @ApiOperation("更改自己的帖子")
    public Response<IsSuccessDTO> updateText(@RequestBody
                                                 @ApiParam(name = "updateTextDTO", value = "修改帖子内容, 使用json请求", required = true)
                                                         UpdateTextDTO updateTextDTO){
        return communityService.updateText(updateTextDTO);
    }

    @GetMapping("/delete")
    @ApiOperation("删除相关帖子")
    public Response<IsSuccessDTO> deletedText(@RequestParam
                                                  @ApiParam(name = "textId", value = "删除帖子的id，使用URL请求", required = true)
                                                          Long textId){
        return communityService.deletedText(textId);
    }
}
