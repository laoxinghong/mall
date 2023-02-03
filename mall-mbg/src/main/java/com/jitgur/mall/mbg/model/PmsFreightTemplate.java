package com.jitgur.mall.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

public class PmsFreightTemplate implements Serializable {
    private Long id;

    private String name;

    @ApiModelProperty(value = "计费类型 0>按重量 1>按件数")
    private Integer chargeType;

    @ApiModelProperty(value = "初始重量")
    private BigDecimal firstWeight;

    @ApiModelProperty(value = "初始价格")
    private BigDecimal firstFee;

    private BigDecimal continueWeight;

    private BigDecimal continueFee;

    @ApiModelProperty(value = "目的地")
    private String destination;

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

    public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }

    public BigDecimal getFirstWeight() {
        return firstWeight;
    }

    public void setFirstWeight(BigDecimal firstWeight) {
        this.firstWeight = firstWeight;
    }

    public BigDecimal getFirstFee() {
        return firstFee;
    }

    public void setFirstFee(BigDecimal firstFee) {
        this.firstFee = firstFee;
    }

    public BigDecimal getContinueWeight() {
        return continueWeight;
    }

    public void setContinueWeight(BigDecimal continueWeight) {
        this.continueWeight = continueWeight;
    }

    public BigDecimal getContinueFee() {
        return continueFee;
    }

    public void setContinueFee(BigDecimal continueFee) {
        this.continueFee = continueFee;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", chargeType=").append(chargeType);
        sb.append(", firstWeight=").append(firstWeight);
        sb.append(", firstFee=").append(firstFee);
        sb.append(", continueWeight=").append(continueWeight);
        sb.append(", continueFee=").append(continueFee);
        sb.append(", destination=").append(destination);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}