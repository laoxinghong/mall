package com.jitgur.mall.portal.repository;

import com.jitgur.mall.portal.domain.UmsMemberProductReadHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 会员商品浏览记录管理repository
 * Created by jitgur on 20230214
 */
public interface UmsMemberProductReadHistoryRepository extends MongoRepository<UmsMemberProductReadHistory, String> {

    /**
     * 获取当前用户所有商品浏览记录
     */
    List<UmsMemberProductReadHistory> findAllByMemberId(Long memberId);


    /**
     * 分页获取商品浏览记录
     */
    Page<UmsMemberProductReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId, Pageable pageable);


    /**
     * 清空当前用户商品浏览记录
     */
    void deleteAllByMemberId(Long memberId);

}
