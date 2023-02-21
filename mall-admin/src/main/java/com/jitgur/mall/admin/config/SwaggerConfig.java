package com.jitgur.mall.admin.config;


import com.jitgur.mall.common.config.BaseSwaggerConfig;
import com.jitgur.mall.common.domain.SwaggerProperties;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger api文档相关配置
 * Created by jitgur on 20230205
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.jitgur.mall.admin.controller")
                .title("后台系统")
                .description("后台相关接口文档")
                .contactName("jitgur")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }


    @Bean
    public BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
        return generateBeanPostProcessor();
    }

}
