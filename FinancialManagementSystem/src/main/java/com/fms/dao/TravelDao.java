package com.fms.dao;

import com.fms.entity.Travel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TravelDao {

    /**
     * 插入账单数据
     * @param travel
     * @return
     */
    public int insert(Travel travel);
    /**
     * 修改账单数据
     * @param travel
     * @return
     */
    public int update(Travel travel);

    /**
     * 根据billId获取所有国内差旅信息
     * @return
     */
    public List<Map<String,Object>> selectAll(@Param("billId") String billId);
}