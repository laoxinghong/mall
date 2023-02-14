package com.jitgur.mall.portal.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 会员商品收藏信息封装
 * Created by jitgur on 20230214
 */
@Getter
@Setter
@Document
public class UmsMemberProductCollection {

    @Id
    private String id;
    @Indexed
    private Long memberId;
    private String memberIcon;
    private String memberNickname;
    @Indexed
    private Long productId;
    private String productName;
    private String productPic;
    private String productSubTitle;
    private String productPrice;
    private Date createTime;

}
