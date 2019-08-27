package com.netplus.catpark.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Brandon.
 * @date 2019/8/27.
 * @time 16:23.
 */

@Data
@Builder
@AllArgsConstructor
public class UserInfoBO {
    private String openId;
    private String unionId;
    private String avatar;
    private String nickName;
    private Integer gender;
    private String sessionKey;
    private Long userId;
    private String phoneNum;
}
