package com.netplus.catpark.service.util;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Brandon.
 * @date 2019/8/18.
 * @time 15:02.
 */

public class ListStreamUtil {
    /**
     * 转换为 id Object map类型
     * @param list
     * @param function
     * @param function2
     * @param <K>
     * @param <V>
     * @param <E>
     * @return
     */
    public static <K,V,E> Map<K,V> getMap(List<E> list, Function<E, K> function, Function<E, V> function2){
        return list.stream().collect(Collectors.toMap(function,function2));
    }

    /**
     * 获取list
     * @param list
     * @param function
     * @param <E>
     * @param <V>
     * @return
     */
    public static <E,V> List<V> getList(Function<E,V> function,List<E> list){
        return list.stream().map(function).collect(Collectors.toList());
    }
}
