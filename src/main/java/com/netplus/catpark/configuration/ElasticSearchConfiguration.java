package com.netplus.catpark.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @program: cat-park
 * @description: 配置es
 * @author: brandon
 * @created: 2019/12/02 17:44
 */

@Slf4j
@Component
public class ElasticSearchConfiguration implements InitializingBean {
    static {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("*****************es_config*************************");
        log.info("es.set.netty.runtime.available.processors:{}", System.getProperty("es.set.netty.runtime.available.processors"));
        log.info("***************************************************");
    }
    /**
     * 原因是Lucene改进了数值类型的索引数据结构，使用了block k-d 树。
     * 这种树的叶结点上文档不是有序排列的，
     * 所以查询的时候需要获取所有的文档id，
     * 访问 docValues构造 bitset，进行其它条件的联合查询。
     * 当查询的结果集很大的时候，大量时间会用于构造bitset，导致了慢查询。
     *
     * 解决方式：在不需要进行 rangeQuery 的时候，尽量不要选择数值类型作为存储格式，使用 keyword 替代，尤其是在重复值数量很大的时候。
     *
     */
}
