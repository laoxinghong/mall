package com.jitgur.mall.security.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * 动态权限相关业务类
 * Created by jitgur on 20230204
 */
public interface DynamicSecurityService {

    /**
     * 加载资源ANT通配符和资源对应的map
     */
    Map<String, ConfigAttribute> loadDataSource();
}
