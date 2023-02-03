package com.jitgur.mall.mbg.mapper;

import com.jitgur.mall.mbg.model.CmsMemberReport;
import com.jitgur.mall.mbg.model.CmsMemberReportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsMemberReportMapper {
    long countByExample(CmsMemberReportExample example);

    int deleteByExample(CmsMemberReportExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsMemberReport row);

    int insertSelective(CmsMemberReport row);

    List<CmsMemberReport> selectByExample(CmsMemberReportExample example);

    CmsMemberReport selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") CmsMemberReport row, @Param("example") CmsMemberReportExample example);

    int updateByExample(@Param("row") CmsMemberReport row, @Param("example") CmsMemberReportExample example);

    int updateByPrimaryKeySelective(CmsMemberReport row);

    int updateByPrimaryKey(CmsMemberReport row);
}