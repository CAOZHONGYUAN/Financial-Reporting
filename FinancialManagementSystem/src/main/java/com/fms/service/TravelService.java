package com.fms.service;

import com.fms.entity.PageBeanOne;

import java.util.List;
import java.util.Map;

/**
 * 国内差旅业务层
 */
public interface TravelService {
    /**
     * 根据账单号获取明细
     * @param billId
     * @return
     */
    public List<Map<String,Object>> getAllByBillid(String billId, PageBeanOne<Object> pageBean);
}
