package com.jitgur.mall.security.component;


import cn.hutool.core.util.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 动态权限元数据资源
 * Created by jitgur on 20230204
 */
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private DynamicSecurityService dynamicSecurityService;

    Map<String, ConfigAttribute> configAttributeMap = null;


    @PostConstruct
    public void loadDataSource() {
        configAttributeMap = dynamicSecurityService.loadDataSource();
    }


    public void clearDataSource() {
        configAttributeMap.clear();
        configAttributeMap = null;
    }


    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (configAttributeMap == null) this.loadDataSource();
        List<ConfigAttribute> configAttributes = new ArrayList<>();

        // 获取当前访问的路径
        String url = ((FilterInvocation) object).getRequestUrl();
        String path = URLUtil.getPath(url);

        // 获取访问当前路径所需的资源
        PathMatcher pathMatcher = new AntPathMatcher();
        for (String pattern : configAttributeMap.keySet()) {
            if (pathMatcher.match(pattern, path)) {
                configAttributes.add(configAttributeMap.get(pattern));
            }
        }

        // 返回操作请求所需资源集合
        return configAttributes;
    }

}
