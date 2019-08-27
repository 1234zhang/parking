package com.netplus.catpark.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Brandon.
 * @date 2019/8/27.
 * @time 21:11.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RedisUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String openId;
    private String uninId;
    private String headUrl;
    private String sessionKey;
    private Integer sex;
    private String nickName;
    private String phoneNum;
    private Integer loginType;
    private String type;
    private String token;
}
