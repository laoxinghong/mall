package com.jitgur.mall.search.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 商品相关属性信息
 * Created by jitgur on 20230210
 */
@Data
@EqualsAndHashCode
public class EsProductRelatedInfo {

    private List<String> brandNames;
    private List<String> productCategoryNames;
    private List<ProductAttr> productAttrs;

    @Data
    @EqualsAndHashCode
    public static class ProductAttr {

        private Long attrId;
        private String attrName;
        private List<String> attrValues;
    }

}
