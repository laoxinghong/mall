package com.jitgur.mall.portal.config;

import com.jitgur.mall.common.config.BaseSwaggerConfig;
import com.jitgur.mall.common.domain.SwaggerProperties;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger config
 * Created by jitgur on 20230210
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.jitgur.mall.portal.controller")
                .title("前台商城系统")
                .description("前端调用相关接口文档")
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
