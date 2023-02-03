package com.jitgur.mall.mbg.mapper;

import com.jitgur.mall.mbg.model.PmsProductDiscountLadder;
import com.jitgur.mall.mbg.model.PmsProductDiscountLadderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsProductDiscountLadderMapper {
    long countByExample(PmsProductDiscountLadderExample example);

    int deleteByExample(PmsProductDiscountLadderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsProductDiscountLadder row);

    int insertSelective(PmsProductDiscountLadder row);

    List<PmsProductDiscountLadder> selectByExample(PmsProductDiscountLadderExample example);

    PmsProductDiscountLadder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") PmsProductDiscountLadder row, @Param("example") PmsProductDiscountLadderExample example);

    int updateByExample(@Param("row") PmsProductDiscountLadder row, @Param("example") PmsProductDiscountLadderExample example);

    int updateByPrimaryKeySelective(PmsProductDiscountLadder row);

    int updateByPrimaryKey(PmsProductDiscountLadder row);
}