package com.fms.service;

import com.fms.entity.*;

import java.util.List;
import java.util.Map;

public interface BillService {

    /**
     * 通过状态码查询
     * @param status
     * @return Map<String,Object>
     */
    public PageBeanOne<Object> selectByStatus(int status, Map<String,Object> searchMap, PageBeanOne<Object> pageBean);
    public PageBeanOne<Object> selectByStatusAndDeptno(Integer status,Integer deptId,Map<String,Object> searchMap, PageBeanOne<Object> pageBean);

    /**
     * 改变账单状态,用于审核账单
     * @param billId
     * @param type
     * @return
     */
    public Integer updateStatus(String billId,String type,String remarks);

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
     * 分类插入账单
     */
    public int insert(Bill bill, Traffic traffic);
    public int insert(Bill bill, Travel travel);
    public int insert(Bill bill, Property property);
    public int insert(Bill bill, Welfare welfare);

    /**
     * 分类修改账单
     */
    public int update(Bill bill, Traffic traffic);
    public int update(Bill bill, Travel travel);
    public int update(Bill bill, Property property);
    public int update(Bill bill, Welfare welfare);

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
}
