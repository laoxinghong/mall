package com.jitgur.mall.mbg.mapper;

import com.jitgur.mall.mbg.model.UmsMemberBrandCollection;
import com.jitgur.mall.mbg.model.UmsMemberBrandCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberBrandCollectionMapper {
    long countByExample(UmsMemberBrandCollectionExample example);

    int deleteByExample(UmsMemberBrandCollectionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberBrandCollection row);

    int insertSelective(UmsMemberBrandCollection row);

    List<UmsMemberBrandCollection> selectByExample(UmsMemberBrandCollectionExample example);

    UmsMemberBrandCollection selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UmsMemberBrandCollection row, @Param("example") UmsMemberBrandCollectionExample example);

    int updateByExample(@Param("row") UmsMemberBrandCollection row, @Param("example") UmsMemberBrandCollectionExample example);

    int updateByPrimaryKeySelective(UmsMemberBrandCollection row);

    int updateByPrimaryKey(UmsMemberBrandCollection row);
}