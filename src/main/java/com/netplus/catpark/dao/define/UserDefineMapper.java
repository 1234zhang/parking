package com.netplus.catpark.dao.define;

import com.netplus.catpark.domain.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Brandon.
 * @date 2019/8/22.
 * @time 15:32.
 */

public interface UserDefineMapper {
    List<User> getUserList(@Param("list") List<Long> list);
}

