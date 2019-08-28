package com.netplus.catpark.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.netplus.catpark.dao.define.CommunityDefineMapper;
import com.netplus.catpark.dao.define.UserDefineMapper;
import com.netplus.catpark.dao.generator.CommunityMapper;
import com.netplus.catpark.domain.bo.CommunityInfoBO;
import com.netplus.catpark.domain.bo.ContextUser;
import com.netplus.catpark.domain.dto.*;
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
        Date date = new Date();
        Community community = new Community();
        community.setUserId(userId);
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
    public Response<CommunityListDTO> getTextList(PageDTO pageDTO) {
        if (pageDTO.getCount() == null || pageDTO.getPage() == null) {
            return ResponseUtil.makeFail("参数为空");
        }
        Long userId = ContextUser.getUserId();
        //获取到帖子列表
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getCount());
        List<Community> textList = communityDefineMapper.getTextList();
        PageInfo pageInfo = new PageInfo(textList);
        //获取到每个帖子的发帖人相关信息
        List<Long> list = ListStreamUtil.getList(Community::getUserId, textList);
        List<User> userList = userDefineMapper.getUserList(list);
        Map<Long, User> userAndIdMap = ListStreamUtil.getMap(userList, User::getId, user -> user);

        //组装帖子信息
        List<CommunityInfoBO> infoList = new ArrayList<>();
        textList.forEach(b -> {
            User user = userAndIdMap.get(b.getUserId());
            CommunityInfoBO build = CommunityInfoBO.builder().
                    avatar(user.getAvatar()).
                    nickName(user.getNickName()).
                    phoneNum(user.getPhoneNum()).
                    text(b.getText()).
                    title(b.getTitle()).
                    build();
            infoList.add(build);
        });
        return new Response<CommunityListDTO>(0, "success", CommunityListDTO.
                builder().
                communityInfoBOList(infoList).
                build());
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
