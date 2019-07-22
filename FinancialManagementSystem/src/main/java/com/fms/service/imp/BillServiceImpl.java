package com.fms.service.imp;

import com.fms.dao.*;
import com.fms.entity.*;
import com.fms.service.BillService;
import com.fms.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillDao billDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private TrafficDao trafficDao;
    @Autowired
    private PropertyDao propertyDao;
    @Autowired
    private WelfareDao welfareDao;
    @Autowired
    private TravelDao travelDao;

    @Override
    public PageBeanOne<Object> selectByStatus(int status, Map<String,Object> searchMap, PageBeanOne<Object> Pagebean) {
        return selectByStatusAndDeptno(status,null,searchMap,Pagebean);
    }

    @Override
    public PageBeanOne<Object> selectByStatusAndDeptno(Integer status, Integer deptId,Map<String,Object> searchMap, PageBeanOne<Object> pageBean) {

        List<Map<String, Object>> list;//数据结果集
        //设置limit分页参数
        int start   = pageBean.getPageIndex()*pageBean.getPageSize()-pageBean.getPageSize();
        int size    = pageBean.getPageSize();
        //条件查询参数
        Timestamp startDate = Timestamp.valueOf(searchMap.get("startDate").toString());//开始时间
        Timestamp endDate   = Timestamp.valueOf(searchMap.get("endDate").toString());//结束时间
        String strBillId = searchMap.get("billId").toString();//billId==null?"":billId.toString();//订单编号,按字符串模糊匹配
        strBillId = "%"+strBillId+"%";

        list = billDao.selectByStatusAndDeptno(status,deptId,startDate,endDate,strBillId,start,size);//获取所有信息
        pageBean.setTotalRecords(billDao.countPagenumByStatusAndDeptno(status,deptId,startDate,endDate,strBillId));//获取总条数,放入pageBean

        //向结果中加入报账人真实姓名,将日期格式转化为字符串,计算总金额
        for(Map<String,Object> map:list){
            Timestamp date = (Timestamp) map.get("submitDatetime");
            String strDate = DateUtil.dateToString(date,"yyyy-MM-dd HH:mm:ss");//字符串格式时间
            String trueName = userDao.selectTruenameById((Integer) map.get("reportUserId"));//报账人真实姓名
            Double cost = (Double) billDao.countAllCost((String)map.get("billId"),map.get("typeId").toString());//金额
            //更新至map集合
            map.put("submitDatetime",strDate);
            map.put("trueName",trueName);
            map.put("cost",cost);
        }
        pageBean.setPageDatas(list);
        return pageBean;
    }

    @Override
    public Integer updateStatus(String billId, String type,String remarks) {
        Integer result;
        if (type.equals("pass")){
            Integer statusId = billDao.selectStatusById(billId)+1;
            result = billDao.updateBillStatus(billId,statusId,"");
        }else{
            result = billDao.updateBillStatus(billId,-1,remarks);
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> getorderHistory(int pageNum, int pageSize,int userId) {
        return billDao.getorderHistory(pageNum,pageSize,userId);
    }

    @Override
    public int getTotalCount() {
        return billDao.getTotalCount();
    }

    @Override
    public List<Map<String, Object>> getOrderHistoryQuery(Map<String, Object> map) {
        return billDao.getOrderHistoryQuery(map);
    }

    @Override
    public List<Map<String, Object>> getOrderWait(int pageNum, int pageSize,int userId) {
        return billDao.getOrderWait(pageNum,pageSize,userId);
    }

    @Override
    public int getTotalWaitCount() {
        return billDao.getTotalWaitCount();
    }

    @Override
    public List<Map<String, Object>> getOrderWaitQuery(Map<String, Object> map) {
        return billDao.getOrderWaitQuery(map);
    }

    @Override
    public List<Map<String, Object>> getOrderError(int pageNum, int pageSize,int userId) {
        return billDao.getOrderError(pageNum,pageSize,userId);
    }

    @Override
    public int getTotalErrorCount() {
        return billDao.getTotalErrorCount();
    }

    @Override
    public List<Map<String, Object>> getOrderErrorQuery(Map<String, Object> map) {
        return billDao.getOrderErrorQuery(map);
    }

    @Override
    public Integer deleteProperty(String billId) {
        return billDao.deleteProperty(billId);
    }

    @Override
    public Integer deleteTraffic(String billId) {
        return billDao.deleteTraffic(billId);
    }

    @Override
    public Integer deleteTravel(String billId) {
        return billDao.deleteTravel(billId);
    }

    @Override
    public Integer deleteWelfare(String billId) {
        return billDao.deleteWelfare(billId);
    }

    @Override
    public Integer deleteBill(String billId) {
        return billDao.deleteBill(billId);
    }

    @Override
    public int insert(Bill bill, Traffic traffic) {
        String billid = UUID.randomUUID().toString();
        bill.setBillid(billid);
        bill.setCountcost(traffic.getCost());
        int log =  billDao.insert(bill);
        if(log>0){
            traffic.setBillid(billid);
            System.out.println(traffic);
            log =  trafficDao.insert(traffic);
        }
        System.out.println(log);
        return log;
    }

    @Override
    public int insert(Bill bill, Travel travel) {
        String billid = UUID.randomUUID().toString();
        bill.setBillid(billid);
        bill.setCountcost(travel.getAccomcost().add(travel.getFoodcost()).add(travel.getOthercost()).add(travel.getTrafficcost()));
        int log =  billDao.insert(bill);
        if(log>0){
            travel.setBillid(billid);
            System.out.println(travel);
            log =  travelDao.insert(travel);
        }
        System.out.println(log);
        return log;
    }

    @Override
    public int insert(Bill bill, Property property) {
        String billid = UUID.randomUUID().toString();
        bill.setBillid(billid);
        bill.setCountcost(property.getCost());
        int log =  billDao.insert(bill);
        if(log>0){
            property.setBillid(billid);
            System.out.println(property);
            log =  propertyDao.insert(property);
        }
        System.out.println(log);
        return log;
    }

    @Override
    public int insert(Bill bill, Welfare welfare) {
        String billid = UUID.randomUUID().toString();
        bill.setBillid(billid);
        bill.setCountcost(welfare.getCost());
        int log =  billDao.insert(bill);
        if(log>0){
            welfare.setBillid(billid);
            System.out.println(welfare);
            log =  welfareDao.insert(welfare);
        }
        System.out.println(log);
        return log;
    }

    /*
    分类修改账单
     */
    @Override
    public int update(Bill bill, Traffic traffic) {
        bill.setCountcost(traffic.getCost());
        int log =  billDao.update(bill);
        if(log>0){
            log =  trafficDao.update(traffic);
        }
        System.out.println(log);
        return log;
    }

    @Override
    public int update(Bill bill, Travel travel) {
        bill.setCountcost(travel.getAccomcost().add(travel.getFoodcost()).add(travel.getOthercost()).add(travel.getTrafficcost()));
        int log =  billDao.update(bill);
        if(log>0){
            log =  travelDao.update(travel);
        }
        System.out.println(log);
        return log;
    }

    @Override
    public int update(Bill bill, Property property) {
        bill.setCountcost(property.getCost());
        int log =  billDao.update(bill);
        if(log>0){
            log =  propertyDao.update(property);
        }
        System.out.println(log);
        return log;
    }

    @Override
    public int update(Bill bill, Welfare welfare) {
        bill.setCountcost(welfare.getCost());
        int log =  billDao.update(bill);
        if(log>0){
            log =  welfareDao.update(welfare);
        }
        System.out.println(log);
        return log;
    }

    /**
     * 渲染编辑页面信息
     * @param billId
     * @return
     */
    @Override
    public Map<String, Object> getEdit1(String billId) {
        return billDao.getEdit1(billId);
    }

    @Override
    public Map<String, Object> getEdit2(String billId) {
        return billDao.getEdit2(billId);
    }

    @Override
    public Map<String, Object> getEdit3(String billId) {
        return billDao.getEdit3(billId);
    }

    @Override
    public Map<String, Object> getEdit4(String billId) {
        return billDao.getEdit4(billId);
    }

}
