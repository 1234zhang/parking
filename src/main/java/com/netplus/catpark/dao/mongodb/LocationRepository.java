package com.netplus.catpark.dao.mongodb;


import com.netplus.catpark.domain.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: cat-park
 * @description: 位置仓库
 * @author: brandon
 * @created: 2019/11/15 14:42
 */

@Repository
public class LocationRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * @Description: 按照圆形查找
     * @author: Brandon
     * @Date: 2019/11/15 14:52
     */
    public List<Location> queryByCircleNear(Point point, double maxDistance){
        Query query = new Query(Criteria.where("position").near(point).maxDistance(maxDistance / 111));
        return mongoTemplate.find(query, Location.class);
    }

    /**
     * @Description: 按照方形查找，左下角+右上角表示一个方形范围
     * @author: Brandon
     * @Date: 2019/11/15 14:56
     */
    public List<Location> queryByWithin(Point lowLeft, Point upperRight){
        Query query = new Query(Criteria.where("position").within(new Box(lowLeft, upperRight)));
        return mongoTemplate.find(query, Location.class);
    }
}
