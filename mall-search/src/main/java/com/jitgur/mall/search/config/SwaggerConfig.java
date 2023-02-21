package com.jitgur.mall.search.config;

import com.jitgur.mall.common.config.BaseSwaggerConfig;
import com.jitgur.mall.common.domain.SwaggerProperties;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置
 * Created  by jitgur on 20230209
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.jitgur.mall.search.controller")
                .title("商品搜索系统")
                .description("mall商品搜索系统相关文档")
                .contactName("jitgur")
                .version("1.0")
                .enableSecurity(false)
                .build();
    }

    @Bean
    public BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
        return generateBeanPostProcessor();
    }

}
