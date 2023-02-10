package com.jitgur.mall.portal.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mapper扫描路径配置
 * Created by jitgur on 20230210
 */
@Configuration
@MapperScan({"com.jitgur.mall.mbg.mapper", "com.jitgur.mall.portal.dao"})
public class MybatisConfig {
}
