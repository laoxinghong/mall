package com.jitgur.mall.mbg.mapper;

import com.jitgur.mall.mbg.model.PmsProductVerifyRecord;
import com.jitgur.mall.mbg.model.PmsProductVerifyRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsProductVerifyRecordMapper {
    long countByExample(PmsProductVerifyRecordExample example);

    int deleteByExample(PmsProductVerifyRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsProductVerifyRecord row);

    int insertSelective(PmsProductVerifyRecord row);

    List<PmsProductVerifyRecord> selectByExample(PmsProductVerifyRecordExample example);

    PmsProductVerifyRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") PmsProductVerifyRecord row, @Param("example") PmsProductVerifyRecordExample example);

    int updateByExample(@Param("row") PmsProductVerifyRecord row, @Param("example") PmsProductVerifyRecordExample example);

    int updateByPrimaryKeySelective(PmsProductVerifyRecord row);

    int updateByPrimaryKey(PmsProductVerifyRecord row);
}