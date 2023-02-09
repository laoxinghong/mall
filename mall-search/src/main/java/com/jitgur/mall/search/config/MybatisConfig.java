package com.jitgur.mall.search.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis mapper扫描路径配置
 * Created by jitgur on 20230209
 */
@Configuration
@MapperScan({"com.jitgur.mall.mbg.mapper", "com.jitgur.mall.search.dao"})
public class MybatisConfig {
}
