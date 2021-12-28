package com.zd.esearch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zd.esearch.mapper")
public class EsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsearchApplication.class, args);
    }

}
