package com.jitgur.mall.mbg.mapper;

import com.jitgur.mall.mbg.model.UmsResource;
import com.jitgur.mall.mbg.model.UmsResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsResourceMapper {
    long countByExample(UmsResourceExample example);

    int deleteByExample(UmsResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsResource row);

    int insertSelective(UmsResource row);

    List<UmsResource> selectByExample(UmsResourceExample example);

    UmsResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UmsResource row, @Param("example") UmsResourceExample example);

    int updateByExample(@Param("row") UmsResource row, @Param("example") UmsResourceExample example);

    int updateByPrimaryKeySelective(UmsResource row);

    int updateByPrimaryKey(UmsResource row);
}