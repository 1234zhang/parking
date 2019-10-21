package com.netplus.catpark.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.netplus.catpark.dao.define.CommunityDefineMapper;
import com.netplus.catpark.dao.define.UserDefineMapper;
import com.netplus.catpark.dao.generator.CommunityMapper;
import com.netplus.catpark.dao.generator.UserMapper;
import com.netplus.catpark.domain.bo.CommunityInfoBO;
import com.netplus.catpark.domain.bo.ContextUser;
import com.netplus.catpark.domain.dto.*;
import com.netplus.catpark.domain.model.PageQueryResponse;
import com.netplus.catpark.domain.model.Pagination;
import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.domain.po.Community;
import com.netplus.catpark.domain.po.CommunityExample;
import com.netplus.catpark.domain.po.User;
import com.netplus.catpark.domain.po.UserExample;
import com.netplus.catpark.service.util.ListStreamUtil;
import com.netplus.catpark.service.util.ResponseUtil;
import com.netplus.catpark.service.util.sensitiveWord.SensitiveFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Brandon.
 * @date 2019/8/22.
 * @time 10:24.
 */

@Service
@Slf4j
public class CommunityService {

    @Autowired
    CommunityMapper communityMapper;

    @Autowired
    UserMapper  userMapper;

    @Autowired
    CommunityDefineMapper communityDefineMapper;

    @Autowired
    UserDefineMapper userDefineMapper;

    /**
     * 发布帖子
     *
     * @param communityPublishDTO
     * @return
     */
    public Response<IsSuccessDTO> communityPublish(CommunityPublishDTO communityPublishDTO) {
        if (communityPublishDTO.getText() == null || communityPublishDTO.getTitle() == null) {
            return ResponseUtil.makeFail("内容或者标题不能为空");
        }
        SensitiveFilter filter = SensitiveFilter.DEFAULT;
        String result = filter.filter(communityPublishDTO.getText(), '*');
        Long userId = ContextUser.getUserId();
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(userId).andDeletedEqualTo(false);
        User user = userMapper.selectByExample(example).get(0);
        Date date = new Date();
        Community community = new Community();
        community.setUserId(userId);
        community.setAvatar(user.getAvatar());
        community.setPhoneNum(user.getPhoneNum());
        community.setNickName(user.getNickName());
        community.setText(result);
        community.setTitle(communityPublishDTO.getTitle());
        community.setGmtCreate(date);
        community.setGmtUpdate(date);
        community.setDeleted(false);

        communityMapper.insert(community);
        return new Response<IsSuccessDTO>(0, "success", IsSuccessDTO.builder().isSuccess(true).build());
    }

    /**
     * 获取帖子列表
     *
     * @param pageDTO
     * @return
     */
    public PageQueryResponse<CommunityInfoBO> getTextList(PageDTO pageDTO){
        PageQueryResponse<Community> query = PageQueryResponse.pageQueryByPageHelper(
                new Pagination(pageDTO.getPage(), pageDTO.getCount()), 100, pagination -> {
                    PageHelper.startPage(pageDTO.getCount(), pageDTO.getPage());
                    return communityDefineMapper.getTextList();
                }
        );
        return query.transform(getFunction());
    }
    private Function<Community, CommunityInfoBO> getFunction(){
        return t -> CommunityInfoBO.
                builder().
                avatar(t.getAvatar()).
                nickName(t.getNickName()).
                phoneNum(t.getPhoneNum()).
                text(t.getText()).id(t.getId()).title(t.getTitle()).build();
    }
    /**
     * 更改帖子内容
     * @param updateTextDTO
     * @return
     */
    public Response<IsSuccessDTO> updateText(UpdateTextDTO updateTextDTO){
        if(updateTextDTO.getText() == null || updateTextDTO.getTextId() == null){
            return ResponseUtil.makeFail("参数为空");
        }
        if(!judgeUser(updateTextDTO.getTextId())) {
            return ResponseUtil.makeFail("非发帖人，不能更改该帖子");
        }
        communityDefineMapper.updateText(updateTextDTO.getText(), updateTextDTO.getTextId());
        return new Response<IsSuccessDTO>(0,"success", IsSuccessDTO.
                builder().
                isSuccess(true).
                build());
    }

    public Response<IsSuccessDTO> deletedText(Long textId){
        if(textId == null){
            return ResponseUtil.makeFail("参数为空");
        }
        if(!judgeUser(textId)){
            return ResponseUtil.makeFail("非发帖人，不能删除");
        }
        communityDefineMapper.deletedText(textId);
        return new Response<IsSuccessDTO>(0,"success", IsSuccessDTO.builder().isSuccess(true).build());
    }

    /**
     * 判断是否是本人操作
     * @param textId
     * @return
     */
    private boolean judgeUser(Long textId){
        CommunityExample example = new CommunityExample();
        example.createCriteria().andIdEqualTo(textId).andDeletedEqualTo(false);
        Community community = communityMapper.selectByExample(example).get(0);
        Long userId = ContextUser.getUserId();
        return userId.equals(community.getUserId());
    }
}
