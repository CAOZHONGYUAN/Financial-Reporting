package com.fms.service.imp;

import com.fms.dao.TravelDao;
import com.fms.dao.UserDao;
import com.fms.entity.PageBeanOne;
import com.fms.service.TravelService;
import com.fms.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TravelServiceImpl implements TravelService {

    @Autowired
    private TravelDao travelDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<Map<String, Object>> getAllByBillid(String billId, PageBeanOne<Object> pageBean) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Map<String, Object>> travelList = travelDao.selectAll(billId);

        Map<String,Object> billMap = null;
        for (Map<String,Object> map:pageBean.getPageDatas()){
            if (map.get("billId").toString().equals(billId)){
                billMap = map;//从session中取出的账单信息
            }
        }

        Map<String,Object> map = new HashMap<>();//储存基本信息
            //报账人
            String trueName = (String) billMap.get("trueName");
            Integer userId  = (Integer) billMap.get("reportUserId");
            //报账人部门
            String deptName = userDao.selectDeptNameByUserid(userId);
            //报账人电话
            String phone    = userDao.selectPhonenumByUserid(userId);
            //报账类型
            String typeName = "国内差旅";
            //报账单号==>billId
            //报账说明
            String description = (String) billMap.get("description");
        //存入map
        map.put("trueName",trueName);
        map.put("userId",userId);
        map.put("deptName",deptName);
        map.put("phone",phone);
        map.put("typeName",typeName);
        map.put("billId",billId);
        map.put("description",description);
        list.add(map);
        for (Map<String,Object> travelMap:travelList){
            //出差日期
            String date = "起始日期:<br/>"+ DateUtil.dateToString((Timestamp)travelMap.get("startDatetime"))
                    +   "<br/>结束日期:<br/>"+ DateUtil.dateToString((Timestamp)travelMap.get("endDatetime"));
            //地点
            String location = "起始地点:<br/>"+ travelMap.get("startlocation")
                    +   "<br/>结束地点:<br/>"+ travelMap.get("endlocation");
            //交通工具
            String transportation = (String) travelMap.get("transportation");
            //人数
            String countUser = travelMap.get("countUser").toString();
            //餐饮费
            String foodCost = travelMap.get("foodCost").toString();
            //交通费
            String trafficCost = travelMap.get("trafficCost").toString();
            //住宿费
            String accomCost = travelMap.get("accomCost").toString();
            //其他费用
            String otherCost = travelMap.get("otherCost").toString();
            //金额总计
            String countCost = new Double(new Double(foodCost).doubleValue()+new Double(trafficCost).doubleValue()
                    +new Double(accomCost).doubleValue()+new Double(otherCost).doubleValue()).toString();
            travelMap.put("date",date);
            travelMap.put("location",location);
            travelMap.put("transportation",transportation);
            travelMap.put("countUser",countUser);
            travelMap.put("foodCost",foodCost);
            travelMap.put("trafficCost",trafficCost);
            travelMap.put("accomCost",accomCost);
            travelMap.put("otherCost",otherCost);
            travelMap.put("countCost",countCost);
        }
        //数据存入list
        Map<String,Object> mp = new HashMap<>();
        mp.put("order",travelList);
        list.add(mp);

        return list;
    }
}
