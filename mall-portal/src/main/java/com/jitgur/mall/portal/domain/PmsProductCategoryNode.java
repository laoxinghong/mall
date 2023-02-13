package com.jitgur.mall.portal.domain;

import com.jitgur.mall.mbg.model.PmsProductCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 商品分类树结点
 * Created by jitgur on 20230213
 */
@Getter
@Setter
public class PmsProductCategoryNode extends PmsProductCategory {

    @ApiModelProperty("子分类列表")
    private List<PmsProductCategoryNode> children;

}
