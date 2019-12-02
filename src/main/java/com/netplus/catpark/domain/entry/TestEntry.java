package com.netplus.catpark.domain.entry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @program: cat-park
 * @description: elasticsearch 测试类
 * @author: brandon
 * @created: 2019/12/02 17:09
 */

@Data
@Builder
@AllArgsConstructor
public class TestEntry {
    @Id
    private Integer id;
    private Integer userId;
    private String name;
}
