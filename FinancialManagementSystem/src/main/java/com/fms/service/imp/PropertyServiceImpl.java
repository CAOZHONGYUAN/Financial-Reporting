package com.fms.service.imp;

import com.fms.dao.PropertyDao;
import com.fms.dao.UserDao;
import com.fms.entity.PageBean;
import com.fms.entity.PageBeanOne;
import com.fms.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyDao propertyDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<Map<String, Object>> getTrafficByBillid(String billId, PageBeanOne<Object> pageBean) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Map<String, Object>> propertyList = propertyDao.selectByBillid(billId);

        Map<String, Object> billMap = null;
        for (Map<String, Object> map : pageBean.getPageDatas()) {
            if (map.get("billId").toString().equals(billId)) {
                billMap = map;//从session中取出的账单信息
            }
        }

        Map<String, Object> map = new HashMap<>();//储存基本信息
        //报账人
        String trueName = (String) billMap.get("trueName");
        Integer userId = (Integer) billMap.get("reportUserId");
        //报账人部门
        String deptName = userDao.selectDeptNameByUserid(userId);
        //报账人电话
        String phone = userDao.selectPhonenumByUserid(userId);
        //报账类型
        String typeName = "员工福利";
        //报账单号==>billId
        //报账说明
        String description = (String) billMap.get("description");
        //存入map
        map.put("trueName", trueName);
        map.put("userId", userId);
        map.put("deptName", deptName);
        map.put("phone", phone);
        map.put("typeName", typeName);
        map.put("billId", billId);
        map.put("description", description);
        list.add(map);
        for (Map<String, Object> propertyMap : propertyList) {
            //用途
            String useFor = propertyMap.get("useFor").toString();
            //详细描述
            String depict = propertyMap.get("depict").toString();
            //收款人
            String payeeUsername = userDao.selectTruenameById((Integer) propertyMap.get("payeeUserId"));
            //收款人银行卡号
            String bankCode = userDao.selectBankcodeByUserid((Integer) propertyMap.get("payeeUserId"));
            //金额==>cost
            propertyMap.put("useFor", useFor);
            propertyMap.put("depict", depict);
            propertyMap.put("payeeUsername", payeeUsername);
            propertyMap.put("bankCode", bankCode);
            propertyMap.put("cost", propertyMap.get("cost"));
        }
        //数据存入list
        Map<String, Object> mp = new HashMap<>();
        mp.put("order", propertyList);
        list.add(mp);

        return list;
    }

}
