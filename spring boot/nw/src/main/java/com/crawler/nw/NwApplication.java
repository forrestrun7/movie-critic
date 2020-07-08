package com.crawler.nw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.crawler.nw.mapper")
@SpringBootApplication
public class NwApplication {
    public static void main(String[] args) {
        SpringApplication.run(NwApplication.class, args);
    }
}
