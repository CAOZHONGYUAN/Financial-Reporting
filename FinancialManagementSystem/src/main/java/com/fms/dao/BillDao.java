package com.fms.dao;

import com.fms.entity.Bill;
import com.fms.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface BillDao {
    /**
     * 历史账单
     * @return
     */
    public List<Map<String,Object>> getorderHistory(int pageNum, int pageSize,int userId);
    /**
     * 得到历史账单总条数
     * @return
     */
    public int getTotalCount();

    /**
     * 条件查询历史账单信息
     */
    public List<Map<String, Object>> getOrderHistoryQuery(Map<String,Object> map);

    /**
     * 已办账单
     */
    public List<Map<String,Object>> getOrderWait(int pageNum, int pageSize,int userId);
    /**
     * 得到已办账单总条数
     * @return
     */
    public int getTotalWaitCount();
    /**
     * 条件查询已办账单信息
     */
    public List<Map<String, Object>> getOrderWaitQuery(Map<String,Object> map);

    /**
     * 待办账单
     * @return
     */
    public List<Map<String,Object>> getOrderError(int pageNum, int pageSize,int userId);

    /**
     * 得到我的待办总条数
     * @return
     */
    public int getTotalErrorCount();
    /**
     * 条件查询我的待办信息
     */
    public List<Map<String, Object>> getOrderErrorQuery(Map<String,Object> map);

    /**
     * 根据字账单ID除物业管理明细表信息
     * @param billId
     * @return
     */
    public Integer deleteProperty(String billId);

    /**
     * 根据账单ID删除公务交通明细表信息
     * @param billId
     * @return
     */
    public Integer deleteTraffic(String billId);

    /**
     * 根据账单ID删除国内差旅明细表信息
     * @param billId
     * @return
     */
    public Integer deleteTravel(String billId);

    /**
     * 根据账单ID删除员工福利明细表信息
     * @param billId
     * @return
     */
    public Integer deleteWelfare(String billId);
    /**
     * 根据账单ID删除账单表
     * @param billId
     * @return
     */
    public Integer deleteBill(String billId);

    /**
     * 插入账单数据
     * @param bill
     * @return
     */
    public int insert(Bill bill);
    /**
     * 渲染编辑页面信息
     * 国内差旅
     */
    public Map<String,Object> getEdit1(String billId);
    /**
     * 员工福利
     */
    public Map<String,Object> getEdit2(String billId);
    /**
     * 公务交通
     */
    public Map<String,Object> getEdit3(String billId);
    /**
     * 物业管理
     */
    public Map<String,Object> getEdit4(String billId);
    /**
     * 修改账单
     */
    public int update(Bill bill);

    /**
     * 根据状态查询
     * @param statusId
     * @return
     */
//    public List<Map<String,Object>> selectByStatus(int statusId,int start,int size);

    /**
     * 根据状态和部门查询
     * @param statusId
     * @param deptno
     * @param startDate
     * @param endDate
     * @param strBillId
     * @return
     */
    public List<Map<String,Object>> selectByStatusAndDeptno(@Param("statusId") Integer statusId,@Param("deptno") Integer deptno,@Param("startDate") Timestamp startDate,@Param("endDate") Timestamp endDate,@Param("strBillId") String strBillId,@Param("start") Integer start,@Param("size") Integer size);

    /**
     * 计算账单总金额
     * @param billId
     * @param typeId
     * @return
     */
    public Double countAllCost(String billId, String typeId);

    /**
     * 获取总条数
     * @return
     */
//    public Integer countPagenumByStatus(int statusId);
    public Integer countPagenumByStatusAndDeptno(Integer sattusId, Integer deptno, Timestamp startDate, Timestamp endDate, String strBillId);

    /**
     * 修改账单状态
     * @param billId
     * @param statusId
     * @return
     */
    public Integer updateBillStatus(@Param("billId") String billId, @Param("statusId") Integer statusId, @Param("remarks") String remarks);

    /**
     * 获取账单状态码
     * @param billId
     * @return
     */
    public Integer selectStatusById(@Param("billId") String billId);
}