package com.netplus.catpark;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.netplus.catpark.dao")
public class CatParkApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatParkApplication.class, args);



    }

}
