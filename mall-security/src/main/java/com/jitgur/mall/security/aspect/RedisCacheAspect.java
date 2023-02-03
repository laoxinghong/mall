package com.jitgur.mall.security.aspect;

import com.jitgur.mall.security.annotation.CacheException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * Redis缓存切面，防止redis宕机影响正常业务逻辑
 * Created by jitgur on 20230203
 */
@Aspect
@Component
@Order(2)
public class RedisCacheAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheAspect.class);

    @Pointcut("execution(public * com.jitgur.mall.*.service.*CacheService.*(..))")
    public void cacheAspect() {
    }


    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {

            // 有@CacheException注解的方法直接抛出异常
            if (method.isAnnotationPresent(CacheException.class)) {
                throw throwable;
            } else {
                LOGGER.error(throwable.getMessage());
            }
        }

        return result;
    }
}
