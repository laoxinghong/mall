package com.jitgur.mall.portal.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 会员品牌关注信息封装
 * Created by jitgur on 20230214
 */
@Getter
@Setter
@Document
public class UmsMemberBrandAttention {

    @Id
    private Long id;
    @Indexed
    private Long memberId;
    private String memberNickname;
    private String memberIcon;
    @Indexed
    private Long brandId;
    private String brandName;
    private String brandLogo;
    private Date createTime;

}
