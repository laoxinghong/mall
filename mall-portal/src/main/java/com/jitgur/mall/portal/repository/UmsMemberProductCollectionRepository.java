package com.jitgur.mall.portal.repository;

import com.jitgur.mall.portal.domain.UmsMemberProductCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 会员商品收藏管理repository
 * Created by jitgur on 20230214
 */
public interface UmsMemberProductCollectionRepository extends MongoRepository<UmsMemberProductCollection, String> {

    /**
     * 获取商品收藏信息
     */
    UmsMemberProductCollection findByMemberIdAndProductId(Long memberId, Long productId);


    /**
     * 取消商品收藏
     */
   int deleteByMemberIdAndProductId(Long memberId, Long productId);


    /**
     * 分页获取用户商品收藏列表
     */
    Page<UmsMemberProductCollection> findByMemberId(Long memberId, Pageable pageable);


    /**
     * 清空用户商品收藏列表
     */
    void deleteAllByMemberId(Long memberId);


    /**
     * 获取当前用户所有商品收藏信息
     */
    List<UmsMemberProductCollection> findAllByMemberId(Long memberId);

}
