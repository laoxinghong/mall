package com.jitgur.mall.portal.service;

import com.jitgur.mall.portal.domain.OmsPortalOrderReturnApplyParam;

/**
 * 退货申请管理service
 * Created by jitgur on 20230217
 */
public interface OmsPortalOrderReturnApplyService {

    /**
     * 生成退货申请
     */
    int create(OmsPortalOrderReturnApplyParam applyParam);

}
