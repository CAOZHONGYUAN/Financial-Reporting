package com.fms.service;

import com.fms.entity.PageBeanOne;

import java.util.List;
import java.util.Map;

public interface PropertyService {
    /**
     * 查询物业管理明细
     * @param billId
     * @param pageBean
     * @return
     */
    List<Map<String, Object>> getTrafficByBillid(String billId, PageBeanOne<Object> pageBean);
}
