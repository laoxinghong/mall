package com.jitgur.mall.mbg.mapper;

import com.jitgur.mall.mbg.model.CmsPreferenceArea;
import com.jitgur.mall.mbg.model.CmsPreferenceAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsPreferenceAreaMapper {
    long countByExample(CmsPreferenceAreaExample example);

    int deleteByExample(CmsPreferenceAreaExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsPreferenceArea row);

    int insertSelective(CmsPreferenceArea row);

    List<CmsPreferenceArea> selectByExampleWithBLOBs(CmsPreferenceAreaExample example);

    List<CmsPreferenceArea> selectByExample(CmsPreferenceAreaExample example);

    CmsPreferenceArea selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") CmsPreferenceArea row, @Param("example") CmsPreferenceAreaExample example);

    int updateByExampleWithBLOBs(@Param("row") CmsPreferenceArea row, @Param("example") CmsPreferenceAreaExample example);

    int updateByExample(@Param("row") CmsPreferenceArea row, @Param("example") CmsPreferenceAreaExample example);

    int updateByPrimaryKeySelective(CmsPreferenceArea row);

    int updateByPrimaryKeyWithBLOBs(CmsPreferenceArea row);

    int updateByPrimaryKey(CmsPreferenceArea row);
}