package com.netplus.catpark.domain.dto;

import lombok.Data;

/**
 * @author Brandon.
 * @date 2019/8/19.
 * @time 10:20.
 */

@Data
public class PublishSpaceDTO {
    private String phoneNum;
    private String address;
    private String authCode;
    private String name;
    private String checkNum;
    private Integer payment;
    private Double lat;
    private Double lng;
}
