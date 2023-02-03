package com.jitgur.mall.mbg.mapper;

import com.jitgur.mall.mbg.model.SmsFlashPromotionProductRelation;
import com.jitgur.mall.mbg.model.SmsFlashPromotionProductRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsFlashPromotionProductRelationMapper {
    long countByExample(SmsFlashPromotionProductRelationExample example);

    int deleteByExample(SmsFlashPromotionProductRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsFlashPromotionProductRelation row);

    int insertSelective(SmsFlashPromotionProductRelation row);

    List<SmsFlashPromotionProductRelation> selectByExample(SmsFlashPromotionProductRelationExample example);

    SmsFlashPromotionProductRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") SmsFlashPromotionProductRelation row, @Param("example") SmsFlashPromotionProductRelationExample example);

    int updateByExample(@Param("row") SmsFlashPromotionProductRelation row, @Param("example") SmsFlashPromotionProductRelationExample example);

    int updateByPrimaryKeySelective(SmsFlashPromotionProductRelation row);

    int updateByPrimaryKey(SmsFlashPromotionProductRelation row);
}