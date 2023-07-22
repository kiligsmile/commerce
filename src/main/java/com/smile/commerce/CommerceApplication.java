package com.smile.commerce;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
// 注解指定当前项目中的mapper接口路径位置，项目启动时自动加载所有接口文件
@MapperScan("com.smile.commerce.mapper")
public class CommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommerceApplication.class, args);
    }


}
