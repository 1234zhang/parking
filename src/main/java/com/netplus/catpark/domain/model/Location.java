package com.netplus.catpark.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NegativeOrZero;
import java.io.Serializable;

/**
 * @program: cat-park
 * @description: 用于mongodb的存储
 * @author: brandon
 * @created: 2019/11/15 14:35
 */


@Document(collection = "location")
@Data
public class Location implements Serializable {

    private static final long serialVersionUID = 6345369208319776673L;
    @Id
    private Long id;
    @Field
    private String name;
    @Field
    private Double[] position;
    @Transient
    private Double lat;
    @Transient
    private Double lng;

    public Location(long id, String name, Double lat, Double lng){
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.position = new Double[]{lat, lng};
    }

}
