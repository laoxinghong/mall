package com.jitgur.mall.security.config;

import com.jitgur.mall.common.config.BaseRedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Redis配置类
 * Created by jitgur on 20230204
 */
@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {
}
