package com.jitgur.mall.mbg.mapper;

import com.jitgur.mall.mbg.model.SmsHomeAdvertise;
import com.jitgur.mall.mbg.model.SmsHomeAdvertiseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsHomeAdvertiseMapper {
    long countByExample(SmsHomeAdvertiseExample example);

    int deleteByExample(SmsHomeAdvertiseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsHomeAdvertise row);

    int insertSelective(SmsHomeAdvertise row);

    List<SmsHomeAdvertise> selectByExample(SmsHomeAdvertiseExample example);

    SmsHomeAdvertise selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") SmsHomeAdvertise row, @Param("example") SmsHomeAdvertiseExample example);

    int updateByExample(@Param("row") SmsHomeAdvertise row, @Param("example") SmsHomeAdvertiseExample example);

    int updateByPrimaryKeySelective(SmsHomeAdvertise row);

    int updateByPrimaryKey(SmsHomeAdvertise row);
}