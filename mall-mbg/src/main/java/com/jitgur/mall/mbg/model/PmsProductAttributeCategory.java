package com.jitgur.mall.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class PmsProductAttributeCategory implements Serializable {
    private Long id;

    private String name;

    @ApiModelProperty(value = "规格数量")
    private Integer specificationCount;

    @ApiModelProperty(value = "参数数量")
    private Integer paramCount;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSpecificationCount() {
        return specificationCount;
    }

    public void setSpecificationCount(Integer specificationCount) {
        this.specificationCount = specificationCount;
    }

    public Integer getParamCount() {
        return paramCount;
    }

    public void setParamCount(Integer paramCount) {
        this.paramCount = paramCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", specificationCount=").append(specificationCount);
        sb.append(", paramCount=").append(paramCount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}