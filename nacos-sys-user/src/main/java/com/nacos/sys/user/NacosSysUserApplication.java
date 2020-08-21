package com.nacos.sys.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.nacos.sys.user.mapper")
public class NacosSysUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosSysUserApplication.class, args);
    }

}
