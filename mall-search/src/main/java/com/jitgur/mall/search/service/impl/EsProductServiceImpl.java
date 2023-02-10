package com.jitgur.mall.search.service.impl;

import cn.hutool.core.collection.ListUtil;
import com.jitgur.mall.search.dao.EsProductDao;
import com.jitgur.mall.search.domain.EsProduct;
import com.jitgur.mall.search.domain.EsProductRelatedInfo;
import com.jitgur.mall.search.repository.EsProductRepository;
import com.jitgur.mall.search.service.EsProductService;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.filter.ParsedFilter;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 商品搜索系统管理service实现类
 * Created by jitgur on 20230209
 */
@Service
public class EsProductServiceImpl implements EsProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EsProductServiceImpl.class);
    @Autowired
    private EsProductDao productDao;
    @Autowired
    private EsProductRepository productRepository;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;


    @Override
    public int importAll() {
        List<EsProduct> esProductList = productDao.getEsProductListById(null);
        Iterable<EsProduct> iterable = productRepository.saveAll(esProductList);
        Iterator<EsProduct> iterator = iterable.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        return count;
    }


    @Override
    public EsProduct create(Long id) {
        List<EsProduct> esProductList = productDao.getEsProductListById(id);
        if (esProductList != null && esProductList.size() > 0) {
            return productRepository.save(esProductList.get(0));
        }
        return null;
    }


    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }


    @Override
    public void deleteAll(List<Long> ids) {
        productRepository.deleteAllById(ids);
    }


    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return productRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
    }


    @Override
    public Page<EsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort) {

        // 自定义查询条件
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();

        // 分页
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        nativeSearchQueryBuilder.withPageable(pageable);

        // 筛选
        if (brandId != null || productCategoryId != null) {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if (brandId != null) {
                boolQueryBuilder.must(QueryBuilders.termQuery("brandId", brandId));
            }
            if (productCategoryId != null) {
                boolQueryBuilder.must(QueryBuilders.termQuery("productCategoryId", productCategoryId));
            }
            nativeSearchQueryBuilder.withFilter(boolQueryBuilder);
        }

        // 权重
        if (keyword == null) {
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
        } else {
            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("name", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(10)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("subTitle", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(8)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("keywords", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(5)));
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);
            nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);
        }

        if (sort == 1) {
            // 按新品
            nativeSearchQueryBuilder.withSorts(SortBuilders.fieldSort("id").order(SortOrder.DESC));
        } else if (sort == 2) {
            // 按销量
            nativeSearchQueryBuilder.withSorts(SortBuilders.fieldSort("sale").order(SortOrder.DESC));
        } else if (sort == 3) {
            // 按价格倒序
            nativeSearchQueryBuilder.withSorts(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        } else if (sort == 4) {
            // 按价格升序
            nativeSearchQueryBuilder.withSorts(SortBuilders.fieldSort("price").order(SortOrder.ASC));
        }
        // 按相关度
        nativeSearchQueryBuilder.withSorts(SortBuilders.scoreSort().order(SortOrder.DESC));

        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
        LOGGER.info("DSL:{}", nativeSearchQuery.getQuery().toString());

        SearchHits<EsProduct> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, EsProduct.class);
        if (searchHits.getTotalHits() <= 0) {
            return new PageImpl<>(ListUtil.empty(), pageable, 0);
        }
        List<EsProduct> esProductList = searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
        return new PageImpl<>(esProductList, pageable, searchHits.getTotalHits());
    }


    @Override
    public Page<EsProduct> recommend(Long id, Integer pageNum, Integer pageSize) {

        // 自定义查询条件
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();

        // 分页
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        nativeSearchQueryBuilder.withPageable(pageable);

        // 根据id查询指定商品
        List<EsProduct> esProductList = productDao.getEsProductListById(id);
        if (esProductList != null && esProductList.size() > 0) {
            EsProduct esProduct = esProductList.get(0);
            String name = esProduct.getName();
            Long brandId = esProduct.getBrandId();
            Long productCategoryId = esProduct.getProductCategoryId();

            // 过滤当前商品
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.mustNot(QueryBuilders.termQuery("id", id));
            nativeSearchQueryBuilder.withFilter(boolQueryBuilder);

            // 按权重设置查询条件
            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("name", name),
                    ScoreFunctionBuilders.weightFactorFunction(10)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("subTitle", name),
                    ScoreFunctionBuilders.weightFactorFunction(8)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("keywords", name),
                    ScoreFunctionBuilders.weightFactorFunction(5)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("brandId", brandId),
                    ScoreFunctionBuilders.weightFactorFunction(3)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("productCategoryId", productCategoryId),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);
            nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);

            // 排序
            nativeSearchQueryBuilder.withSorts(SortBuilders.scoreSort().order(SortOrder.DESC));

            NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
            LOGGER.info("DSL:{}", nativeSearchQuery.getQuery().toString());

            SearchHits<EsProduct> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, EsProduct.class);
            if (searchHits.getTotalHits() <= 0) {
                return new PageImpl<>(ListUtil.empty(), pageable, 0);
            }
            List<EsProduct> esProductList1 = searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
            return new PageImpl<>(esProductList1, pageable, searchHits.getTotalHits());
        }
        return new PageImpl<>(ListUtil.empty());
    }


    @Override
    public EsProductRelatedInfo searchRelatedInfo(String keyword) {

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();

        // 搜索条件
        if (keyword == null) {
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
        } else {
            nativeSearchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery(keyword, "name", "subTitle", "keywords"));
        }

        // 聚合搜索品牌名称
        nativeSearchQueryBuilder.withAggregations(AggregationBuilders.terms("brandNames").field("brandName"));
        // 聚合搜索分类名称
        nativeSearchQueryBuilder.withAggregations(AggregationBuilders.terms("productCategoryNames").field("productCategoryName"));
        // 聚合搜索商品属性信息
        NestedAggregationBuilder nestedAggregationBuilder = AggregationBuilders.nested("allAttrValues", "attrValueList")
                .subAggregation(AggregationBuilders.filter("productAttrs", QueryBuilders.termQuery("attrValueList.type", 1)))
                .subAggregation(AggregationBuilders.terms("attrIds").field("attrValueList.productAttributeId")
                        .subAggregation(AggregationBuilders.terms("attrValues").field("attrValueList.value")
                                .subAggregation(AggregationBuilders.terms("attrNames").field("attrValueList.name"))));
        nativeSearchQueryBuilder.withAggregations(nestedAggregationBuilder);

        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
        SearchHits<EsProduct> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, EsProduct.class);
        return convertProductRelatedInfo(searchHits);
    }


    /**
     * 将 SearchHits<EsProduct>转换为EsProductRelatedInfo
     */
    private EsProductRelatedInfo convertProductRelatedInfo(SearchHits<EsProduct> searchHits) {
        EsProductRelatedInfo esProductRelatedInfo = new EsProductRelatedInfo();
        Map<String, Aggregation> aggregationMap = ((Aggregations) searchHits.getAggregations().aggregations()).asMap();

        // 品牌名称
        Aggregation brandNames = aggregationMap.get("brandNames");
        List<String> brandNameList = new ArrayList<>();
        for (int i = 0; i < ((Terms) brandNames).getBuckets().size(); i++) {
            brandNameList.add(((Terms) brandNames).getBuckets().get(i).getKeyAsString());
        }
        esProductRelatedInfo.setBrandNames(brandNameList);

        // 商品分类
        Aggregation productCategoryNames = aggregationMap.get("productCategoryNames");
        List<String> productCategoryNameList = new ArrayList<>();
        for (int i = 0; i < ((Terms) productCategoryNames).getBuckets().size(); i++) {
            productCategoryNameList.add(((Terms) productCategoryNames).getBuckets().get(i).getKeyAsString());
        }
        esProductRelatedInfo.setProductCategoryNames(productCategoryNameList);

        // 商品属性
        Aggregation productAttrs = aggregationMap.get("allAttrValues");
        List<? extends Terms.Bucket> attrIdList = ((ParsedLongTerms) ((ParsedFilter) ((ParsedNested) productAttrs)
                .getAggregations().get("productAttrs"))
                .getAggregations().get("attrIds")).getBuckets();
        List<EsProductRelatedInfo.ProductAttr> attrList = new ArrayList<>();
        for (Terms.Bucket attrId : attrIdList) {
            EsProductRelatedInfo.ProductAttr productAttr = new EsProductRelatedInfo.ProductAttr();
            productAttr.setAttrId((Long) attrId.getKey());

            List<String> attrValueList = new ArrayList<>();
            List<? extends Terms.Bucket> attrValues = ((ParsedStringTerms) attrId.getAggregations().get("attrValues")).getBuckets();
            List<? extends Terms.Bucket> attrNames = ((ParsedStringTerms) attrId.getAggregations().get("attrNames")).getBuckets();

            for (Terms.Bucket attrValue : attrValues) {
                attrValueList.add(attrValue.getKeyAsString());
            }
            productAttr.setAttrValues(attrValueList);

            if (!CollectionUtils.isEmpty(attrNames)) {
                String attrName = attrNames.get(0).getKeyAsString();
                productAttr.setAttrName(attrName);
            }
            attrList.add(productAttr);
        }
        esProductRelatedInfo.setProductAttrs(attrList);
        return esProductRelatedInfo;
    }

}
