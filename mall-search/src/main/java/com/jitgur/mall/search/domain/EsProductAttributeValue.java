package com.jitgur.mall.search.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 搜索系统商品属性值信息
 * Created by jitgur on 20230209
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class EsProductAttributeValue {

    private static final Long serialVersionUID = -1L;
    private Long id;
    private Long productAttributeId;
    // 属性值
    @Field(type = FieldType.Keyword)
    private String value;
    // 属性类型 0>规格 1>参数
    private Integer type;
    // 属性名称
    @Field(type = FieldType.Keyword)
    private String name;

}
