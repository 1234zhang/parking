package com.netplus.catpark.domain.bo;

import lombok.*;

/**
 * @author Brandon.
 * @date 2019/8/22.
 * @time 14:37.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class CommunityInfoBO {
    private String nickName;
    private String phoneNum;
    private String title;
    private String text;
    private String avatar;
}
