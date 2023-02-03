package com.jitgur.mall.mbg.mapper;

import com.jitgur.mall.mbg.model.PmsFreightTemplate;
import com.jitgur.mall.mbg.model.PmsFreightTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsFreightTemplateMapper {
    long countByExample(PmsFreightTemplateExample example);

    int deleteByExample(PmsFreightTemplateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsFreightTemplate row);

    int insertSelective(PmsFreightTemplate row);

    List<PmsFreightTemplate> selectByExample(PmsFreightTemplateExample example);

    PmsFreightTemplate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") PmsFreightTemplate row, @Param("example") PmsFreightTemplateExample example);

    int updateByExample(@Param("row") PmsFreightTemplate row, @Param("example") PmsFreightTemplateExample example);

    int updateByPrimaryKeySelective(PmsFreightTemplate row);

    int updateByPrimaryKey(PmsFreightTemplate row);
}