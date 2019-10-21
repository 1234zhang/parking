package com.netplus.catpark.domain.model;

import com.github.pagehelper.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @program: cat-park
 * @description: 分页查询返回数据
 * @author: brandon
 * @created: 2019/10/21 20:41
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class PageQueryResponse<T> implements Serializable {
    private static final long serialVersionUID = -5165061563000126996L;

    List<T> dataList = new ArrayList<T>();
    Integer pageNum;
    Long pageTotal;

    public static <U> PageQueryResponse<U> fromPageHelperResult(List<U> list){
        Page page = (Page) list;
        log.info("the query result size is : " + page.size());
        return PageQueryResponse.<U>builder().
                dataList(list).
                pageNum(page.getPageNum()).
                pageTotal(page.getTotal()).
                build();
    }

    public static <U> PageQueryResponse<U> pageQueryByPageHelper(
            Pagination pagination, int sizeMax, Function<Pagination, List<U>> function){
        pagination = Pagination.safePagination(pagination, sizeMax);
        List<U> list = function.apply(pagination);
        return fromPageHelperResult(list);
    }

    public <U> PageQueryResponse<U> transform(Function<T,U> function){
        List<U> list = dataList.stream().map(function).collect(Collectors.toList());
        return PageQueryResponse.<U> builder().
                dataList(list).
                pageTotal(pageTotal).
                pageNum(pageNum).build();
    }
}
