package com.jitgur.mall.portal.service;

import com.jitgur.mall.mbg.model.UmsMemberReceiveAddress;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 会员收货地址管理service
 * Created by jitgur on 20230212
 */
public interface UmsMemberReceiveAddressService {

    /**
     * 添加收货地址
     */
    int create(UmsMemberReceiveAddress memberReceiveAddress);


    /**
     * 删除收货地址
     */
    int delete(Long addressId);


    /**
     * 修改收货地址
     */
    @Transactional
    int update(Long addressId, UmsMemberReceiveAddress memberReceiveAddress);


    /**
     * 返回当前会员收货地址列表
     */
    List<UmsMemberReceiveAddress> list(Long memberId);


    /**
     * 获得指定收货地址详情
     */
    UmsMemberReceiveAddress getItem(Long addressId);

}
