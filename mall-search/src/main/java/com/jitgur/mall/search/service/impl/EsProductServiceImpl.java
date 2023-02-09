package com.jitgur.mall.search.service.impl;

import com.jitgur.mall.search.dao.EsProductDao;
import com.jitgur.mall.search.domain.EsProduct;
import com.jitgur.mall.search.repository.EsProductRepository;
import com.jitgur.mall.search.service.EsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * 商品搜索系统管理service实现类
 * Created by jitgur on 20230209
 */
@Service
public class EsProductServiceImpl implements EsProductService {

    @Autowired
    private EsProductDao productDao;
    @Autowired
    private EsProductRepository productRepository;


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


}
