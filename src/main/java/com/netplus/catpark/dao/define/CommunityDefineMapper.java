package com.netplus.catpark.dao.define;

import com.netplus.catpark.domain.po.Community;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Brandon.
 * @date 2019/8/22.
 * @time 15:21.
 */

public interface CommunityDefineMapper {
    /**
     * 根据更新时间倒叙排列帖子
     * @return
     */
    List<Community> getTextList();

    /**
     * 更改帖子内容
     * @param text
     * @param textId
     * @return
     */
    Integer updateText(@Param("text") String text, @Param("textId") Long textId);

    /**
     * 删除相关帖子
     * @param textId
     * @return
     */
    Integer deletedText(@Param("textId") Long textId);
}
