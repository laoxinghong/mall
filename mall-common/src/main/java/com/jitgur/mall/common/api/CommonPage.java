package com.jitgur.mall.common.api;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 分页数据封装类
 * Created by jitgur on 20230202
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonPage<T> {

    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;

    /**
     * 将PageHelper分页后的list转换为分页信息
     */
    public static <T> CommonPage<T> restPage(List<T> list) {
        CommonPage<T> result = new CommonPage<>();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setTotalPage(pageInfo.getPages());
        result.setList(pageInfo.getList());
        return result;
    }


    /**
     * 将SpringDate分页后的分页信息转换为CommonPage
     */
    public static <T> CommonPage<T> restPage(Page<T> pageInfo) {
        CommonPage<T> result = new CommonPage<>();
        result.setList(pageInfo.getContent());
        result.setPageNum(pageInfo.getNumber());
        result.setPageSize(pageInfo.getSize());
        result.setTotalPage(pageInfo.getTotalPages());
        result.setTotal(pageInfo.getTotalElements());
        return result;
    }


}
