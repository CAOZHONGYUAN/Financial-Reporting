package com.fms.service;

import com.fms.entity.PageBeanOne;

import java.util.List;
import java.util.Map;

public interface WelfareService {
    public List<Map<String,Object>> getWelfareByBillid(String billId, PageBeanOne<Object> pageBean);
}
