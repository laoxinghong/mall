package com.jitgur.mall.portal.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * jackson相关配置——配置JSON不返回null字段
 * Created by jitgur on 20230210
 */
@Configuration
public class JacksonConfig {

    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {

        ObjectMapper mapper = builder.createXmlMapper(false).build();

        /**
         * Include.NON_NULL：为null的字段不序列化，返回的JSON不包含该字段
         * Include.NON_EMPTY：为" "或null的字段不序列化
         * Include.NON_DEFAULT：为默认值的字段不序列化
         * Include.ALWAYS：默认
         */
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

}
