package com.jitgur.mall.portal.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 会员商品浏览记录信息封装
 * Created by jitgur on 20230214
 */
@Getter
@Setter
@Document
public class UmsMemberProductReadHistory {

    @Id
    private String id;
    @Indexed
    private Long memberId;
    private String memberNickname;
    private String memberIcon;
    @Indexed
    private Long productId;
    private String productName;
    private String productPic;
    private String productPrice;
    private String productSubTitle;
    private Date createTime;

}
