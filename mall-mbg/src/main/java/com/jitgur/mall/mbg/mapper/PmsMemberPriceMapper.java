package com.jitgur.mall.mbg.mapper;

import com.jitgur.mall.mbg.model.PmsMemberPrice;
import com.jitgur.mall.mbg.model.PmsMemberPriceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsMemberPriceMapper {
    long countByExample(PmsMemberPriceExample example);

    int deleteByExample(PmsMemberPriceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsMemberPrice row);

    int insertSelective(PmsMemberPrice row);

    List<PmsMemberPrice> selectByExample(PmsMemberPriceExample example);

    PmsMemberPrice selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") PmsMemberPrice row, @Param("example") PmsMemberPriceExample example);

    int updateByExample(@Param("row") PmsMemberPrice row, @Param("example") PmsMemberPriceExample example);

    int updateByPrimaryKeySelective(PmsMemberPrice row);

    int updateByPrimaryKey(PmsMemberPrice row);
}