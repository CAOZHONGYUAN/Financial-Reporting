package com.fms.dao;

import com.fms.entity.Traffic;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TrafficDao {
    /**
     * 插入账单数据
     * @param traffic
     * @return
     */
    public int insert(Traffic traffic);
    /**
     * 修改账单数据
     * @param traffic
     * @return
     */
    public int update(Traffic traffic);

    /**
     * 根据账单号查询
     * @param billId
     * @return
     */
    public List<Map<String,Object>> selectByBillid(@Param("billId") String billId);
}