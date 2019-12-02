package com.netplus.catpark;

import com.netplus.catpark.dao.elasticsearch.ExRepository;
import com.netplus.catpark.dao.elasticsearch.mapper.EsOrderMapper;
import com.netplus.catpark.dao.mongodb.LocationRepository;
import com.netplus.catpark.domain.entry.EsEntry;
import com.netplus.catpark.domain.entry.TestEntry;
import com.netplus.catpark.domain.model.Location;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatParkApplicationTests {

    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    EsOrderMapper mapper;
    @Test
    public void contextLoads() {
// test
    }
    @Test
    public void init(){
        //初始化一个位置索引
        mongoTemplate.indexOps(Location.class).ensureIndex(new GeospatialIndex("position"));
        //初始化数据
        mongoTemplate.save(new Location(1,"天安门", 116.4041602659, 39.9096215780));
        mongoTemplate.save(new Location(2,"东单", 116.4244857287, 39.9144951360));
        mongoTemplate.save(new Location(3,"王府井", 116.4177807251, 39.9175129885));
        mongoTemplate.save(new Location(4,"西单", 116.3834863095, 39.9133467579));
        mongoTemplate.save(new Location(5,"复兴门", 116.3631701881, 39.9129554253));
        mongoTemplate.save(new Location(6,"复兴门", 116.3631701881, 39.9129554253));
        mongoTemplate.save(new Location(7,"西四", 116.3799838526, 39.9299098531));
        mongoTemplate.save(new Location(8,"菜市口", 116.3809950293, 39.8952009239));
        mongoTemplate.save(new Location(9,"东四", 116.4239387439, 39.9306126797));
    }
    @Test
    public void findCircleNearTest() {
        List<Location> locations = locationRepository.queryByCircleNear(new Point(116.4244857287, 39.9144951360), 10);
        locations.forEach(location -> {
            System.out.println(location.toString());
        });
    }

    @Test
    public void testEs() throws IOException {
        TestEntry build = TestEntry
                .builder()
                .userId(2)
                .name("123")
                .id(2)
                .build();
        mapper.insertOrUpdateOne(EsEntry.builder().data(build).id(2 + "").build());
    }
}
