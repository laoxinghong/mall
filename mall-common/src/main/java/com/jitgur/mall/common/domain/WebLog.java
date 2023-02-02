package com.jitgur.mall.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * controller层日志封装类
 * Created by jitgur on 20230202
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebLog {

    private String description;
    private String username;
    private Integer startTime;
    // 消耗时间
    private Integer spendTime;
    // 根路径
    private String basePath;
    private String uri;
    private String url;
    private String method;
    private String ip;
    // 请求参数
    private Object parameter;
    // 返回结果
    private Object result;

}

