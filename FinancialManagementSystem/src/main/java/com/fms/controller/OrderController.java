package com.fms.controller;

import com.fms.entity.PageBeanOne;
import com.fms.service.PropertyService;
import com.fms.service.TrafficService;
import com.fms.service.TravelService;
import com.fms.service.WelfareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @program: FinancialManagementSystem
 * @description
 * @author: Lei
 * @create: 2019-03-27 14:48
 **/
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    TravelService travelService;
    @Autowired
    WelfareService welfareService;
    @Autowired
    TrafficService trafficService;
    @Autowired
    PropertyService propertyService;

    @RequestMapping("/view1")
    public String orderView1(){
        return "job/order-view1";
    }

    @RequestMapping("/view2")
    public String orderView2(){
        return "job/order-view2";
    }

    @RequestMapping("/view3")
    public String orderView3(){
        return "job/order-view3";
    }

    @RequestMapping("/view4")
    public String orderView4(){
        return "job/order-view4";
    }

    /**
     * 当明细页面载入时,返回json信息
     * @param request
     * @return
     */
    @RequestMapping("/view")
    public @ResponseBody
    List<Map<String,Object>> orderView(HttpServletRequest request){
        List<Map<String,Object>> list = null;
        //接收参数
        String tableName    = request.getParameter("type");
        String billId       = request.getParameter("billId");
        HttpSession session = (HttpSession) request.getSession();
        PageBeanOne<Object> pageBeanOne = (PageBeanOne<Object>) session.getAttribute("waitBill");

        if (tableName.equals("travel")){    //国内差旅
            boolean flag = false;
            for(Map<String,Object> map:pageBeanOne.getPageDatas()){
                if (map.get("billId").equals(billId)){
                    flag = true;
                }
            }
            if (flag){
                list = travelService.getAllByBillid(billId, pageBeanOne);
            }else{
                pageBeanOne = (PageBeanOne<Object>) session.getAttribute("passBill");
                list = travelService.getAllByBillid(billId,pageBeanOne);
            }
        }
        if (tableName.equals("welfare")){   //员工福利
            boolean flag = false;
            for(Map<String,Object> map:pageBeanOne.getPageDatas()){
                if (map.get("billId").equals(billId)){
                    flag = true;
                }
            }
            if (flag){
                list = welfareService.getWelfareByBillid(billId, pageBeanOne);
            }else{
                pageBeanOne = (PageBeanOne<Object>) session.getAttribute("passBill");
                list = welfareService.getWelfareByBillid(billId,pageBeanOne);
            }
        }
        if (tableName.equals("traffic")){   //公务交通
            boolean flag = false;
            for(Map<String,Object> map:pageBeanOne.getPageDatas()){
                if (map.get("billId").equals(billId)){
                    flag = true;
                }
            }
            if (flag){
                list = trafficService.getTrafficByBillid(billId, pageBeanOne);
            }else{
                pageBeanOne = (PageBeanOne<Object>) session.getAttribute("passBill");
                list = trafficService.getTrafficByBillid(billId, pageBeanOne);
            }
        }
        if (tableName.equals("property")){  //物业管理
            boolean flag = false;
            for(Map<String,Object> map:pageBeanOne.getPageDatas()){
                if (map.get("billId").equals(billId)){
                    flag = true;
                }
            }
            if (flag){
                list = travelService.getAllByBillid(billId, pageBeanOne);
            }else{
                pageBeanOne = (PageBeanOne<Object>) session.getAttribute("passBill");
                list = travelService.getAllByBillid(billId, pageBeanOne);
            }
        }

        return list;
    }
}
