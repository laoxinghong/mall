package com.jitgur.mall.security.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 * 有该注解的方法 ，在发生异常时直接抛出
 * Created by jitgur on 20230203
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheException {
}