package com.fms.service.imp;

import com.fms.dao.UserDao;
import com.fms.dao.WelfareDao;
import com.fms.entity.PageBeanOne;
import com.fms.service.WelfareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WelfareServiceImpl implements WelfareService {

    @Autowired
    private WelfareDao welfareDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<Map<String, Object>> getWelfareByBillid(String billId, PageBeanOne<Object> pageBean) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Map<String, Object>> welfareList = welfareDao.selectByBillid(billId);

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
        String typeName = "员工福利";
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
        for (Map<String,Object> welfareMap:welfareList){
            //用途
            String useFor = welfareMap.get("useFor").toString();
            //详细描述
            String depict = welfareMap.get("depict").toString();
            //收款人
            String payeeUsername = userDao.selectTruenameById((Integer) welfareMap.get("payeeUserId"));
            //收款人银行卡号
            String bankCode = userDao.selectBankcodeByUserid((Integer) welfareMap.get("payeeUserId"));
            //金额==>cost
            welfareMap.put("useFor",useFor);
            welfareMap.put("depict",depict);
            welfareMap.put("payeeUsername",payeeUsername);
            welfareMap.put("bankCode",bankCode);
            welfareMap.put("cost",welfareMap.get("cost"));
        }
        //数据存入list
        Map<String,Object> mp = new HashMap<>();
        mp.put("order",welfareList);
        list.add(mp);

        return list;
    }
}
