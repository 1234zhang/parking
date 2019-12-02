package com.netplus.catpark.domain.entry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @program: cat-park
 * @description:
 * @author: brandon
 * @created: 2019/12/02 19:52
 */


@Data
@Builder
@AllArgsConstructor
public class EsEntry<T> {
    private String id;
    private T data;
}
