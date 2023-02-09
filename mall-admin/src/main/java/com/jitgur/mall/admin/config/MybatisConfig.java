package com.jitgur.mall.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Mybatis相关配置
 * Created by jitgur on 20230205
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.jitgur.mall.mbg.mapper", "com.jitgur.mall.admin.dao"})
public class MybatisConfig {
}
