package com.example;

import cn.dev33.satoken.SaManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.tinylog.Logger;

/**
 * @version: v1.0
 * @description： 本模块蛀主要负责用户
 * 用户信息管理
 * @author: zhangconglong on 2021/11/24 15:05
 */
@SpringBootApplication
//@EnableDiscoveryClient
@EnableCaching //开启缓存功能，作用于缓存配置类上或者作用于springboot启动类上
@MapperScan("com.example.dao.mapper")
public class SpringCloudUserServer8081 {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudUserServer8081.class, args);
        Logger.info("启动成功：Sa-Token配置如下：" + SaManager.getConfig());
    }
}
