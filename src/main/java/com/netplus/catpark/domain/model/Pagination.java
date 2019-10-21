package com.netplus.catpark.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: cat-park
 * @description: 用户pagehelper分页参数的修改
 * @author: brandon
 * @created: 2019/10/21 20:51
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagination implements Serializable {
    public static final Integer MAX_PAGE_SIZE = 100;
    private static final long serialVersionUID = 7575192656710478833L;

    private Integer pageNum;
    private Integer pageSize;

    public static Pagination safePagination(Pagination pagination, int pageSize){
        if(pagination == null){
            return Pagination.builder().pageNum(1).pageSize(pageSize).build();
        }
        if(pagination.pageSize == null || pagination.pageSize > pageSize){
            pagination.setPageSize(pageSize);
        }
        if(pagination.pageNum == null || pagination.pageNum <= 0){
            pagination.setPageNum(1);
        }
        return pagination;
    }

}
