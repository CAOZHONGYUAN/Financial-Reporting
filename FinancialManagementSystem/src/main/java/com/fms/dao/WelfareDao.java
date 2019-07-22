package com.fms.dao;


import com.fms.entity.Welfare;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WelfareDao {
    /**
     * 插入账单数据
     * @param welfare
     * @return
     */
    public int insert(Welfare welfare);
    /**
     * 修改账单数据
     * @param welfare
     * @return
     */
    public int update(Welfare welfare);

    public List<Map<String,Object>> selectByBillid(@Param("billId") String billId);
}