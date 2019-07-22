package com.fms.dao;

        import com.fms.entity.Property;
        import org.apache.ibatis.annotations.Param;

        import java.util.List;
        import java.util.Map;

public interface PropertyDao {
    /**
     * 插入账单数据
     * @param property
     * @return
     */
    public int insert(Property property);
    /**
     * 修改账单数据
     * @param property
     * @return
     */
    public int update(Property property);

    /**
     * 根据账单号查询所有
     * @param billId
     * @return
     */
    public List<Map<String,Object>> selectByBillid(@Param("billId") String billId);
}