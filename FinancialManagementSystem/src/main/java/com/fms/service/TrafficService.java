package com.fms.service;

import com.fms.entity.PageBeanOne;

import java.util.List;
import java.util.Map;

public interface TrafficService {
    /**
     * 查询公务交通明细
     * @param billId
     * @param pageBean
     * @return
     */
    List<Map<String, Object>> getTrafficByBillid(String billId, PageBeanOne<Object> pageBean);
}
