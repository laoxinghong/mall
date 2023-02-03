package com.jitgur.mall.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UmsMemberStatisticsInfo implements Serializable {
    private Long id;

    @ApiModelProperty(value = "会员id")
    private Long memberId;

    @ApiModelProperty(value = "累计消费金额")
    private BigDecimal consumeAmount;

    @ApiModelProperty(value = "订单数量")
    private Integer orderCount;

    @ApiModelProperty(value = "优惠券数量")
    private Integer couponCount;

    @ApiModelProperty(value = "评论数量")
    private Integer commentCount;

    @ApiModelProperty(value = "退货订单数量")
    private Integer returnOrderCount;

    @ApiModelProperty(value = "登录次数")
    private Integer loginCount;

    @ApiModelProperty(value = "关注数量")
    private Integer attendCount;

    @ApiModelProperty(value = "粉丝数量")
    private Integer fansCount;

    @ApiModelProperty(value = "商品收藏数量")
    private Integer collectProductCount;

    @ApiModelProperty(value = "专题收藏数量")
    private Integer collectSubjectCount;

    @ApiModelProperty(value = "话题收藏数量")
    private Integer collectTopicCount;

    @ApiModelProperty(value = "评论收藏数量")
    private Integer collectCommentCount;

    private Integer inviteFriendCount;

    @ApiModelProperty(value = "最后一次下单时间")
    private Date recentOrderTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(BigDecimal consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getReturnOrderCount() {
        return returnOrderCount;
    }

    public void setReturnOrderCount(Integer returnOrderCount) {
        this.returnOrderCount = returnOrderCount;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Integer getAttendCount() {
        return attendCount;
    }

    public void setAttendCount(Integer attendCount) {
        this.attendCount = attendCount;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public Integer getCollectProductCount() {
        return collectProductCount;
    }

    public void setCollectProductCount(Integer collectProductCount) {
        this.collectProductCount = collectProductCount;
    }

    public Integer getCollectSubjectCount() {
        return collectSubjectCount;
    }

    public void setCollectSubjectCount(Integer collectSubjectCount) {
        this.collectSubjectCount = collectSubjectCount;
    }

    public Integer getCollectTopicCount() {
        return collectTopicCount;
    }

    public void setCollectTopicCount(Integer collectTopicCount) {
        this.collectTopicCount = collectTopicCount;
    }

    public Integer getCollectCommentCount() {
        return collectCommentCount;
    }

    public void setCollectCommentCount(Integer collectCommentCount) {
        this.collectCommentCount = collectCommentCount;
    }

    public Integer getInviteFriendCount() {
        return inviteFriendCount;
    }

    public void setInviteFriendCount(Integer inviteFriendCount) {
        this.inviteFriendCount = inviteFriendCount;
    }

    public Date getRecentOrderTime() {
        return recentOrderTime;
    }

    public void setRecentOrderTime(Date recentOrderTime) {
        this.recentOrderTime = recentOrderTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", memberId=").append(memberId);
        sb.append(", consumeAmount=").append(consumeAmount);
        sb.append(", orderCount=").append(orderCount);
        sb.append(", couponCount=").append(couponCount);
        sb.append(", commentCount=").append(commentCount);
        sb.append(", returnOrderCount=").append(returnOrderCount);
        sb.append(", loginCount=").append(loginCount);
        sb.append(", attendCount=").append(attendCount);
        sb.append(", fansCount=").append(fansCount);
        sb.append(", collectProductCount=").append(collectProductCount);
        sb.append(", collectSubjectCount=").append(collectSubjectCount);
        sb.append(", collectTopicCount=").append(collectTopicCount);
        sb.append(", collectCommentCount=").append(collectCommentCount);
        sb.append(", inviteFriendCount=").append(inviteFriendCount);
        sb.append(", recentOrderTime=").append(recentOrderTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}