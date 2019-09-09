package com.netplus.catpark.domain.dto;

import com.netplus.catpark.domain.po.UserLicenseRel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Brandon.
 * @date 2019/9/9.
 * @time 16:15.
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPlateLianceDTO {
    List<UserLicenseRel> userLicenseRels;
}
