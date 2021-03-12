package com.blz.springsecuritydemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan( basePackages = {"com.blz.springsecuritydemo.mapper"})//开启扫描Mapper包
public class SpringsecuritydemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringsecuritydemoApplication.class, args);
    }

}
