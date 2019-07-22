package com.fms.controller;

import com.fms.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: FinancialManagementSystem
 * @description
 * @author: Lei
 * @create: 2019-03-28 11:07
 **/
@Controller
@RequestMapping("/deal")
public class DealController {
    @Autowired
    BillService billService;

    @RequestMapping("/review")//该方法用于审核账单(通过,退回)
    public @ResponseBody Map<String,Object> reviewBill(HttpServletRequest request){
        Map<String,Object> res = new HashMap<>();
        //获取参数
        String billId   = request.getParameter("billId");
        String type     = request.getParameter("type");
        String remark   = request.getParameter("remark");
        remark          = remark==null?"":remark;

        Integer code = billService.updateStatus(billId,type,remark);
        res.put("code",code>0?"success":"failure");
        return res;
    }

    @RequestMapping("/passes")//该方法用于审核账单(通过,退回)
    public @ResponseBody Map<String,Object> passesBill(HttpServletRequest request){
        Map<String,Object> res = new HashMap<>();
        Integer code = 0;
        Integer i = 0;
        String billId = request.getParameter(i.toString());

        do {
            code += billService.updateStatus(billId,"pass",null);
            i ++;
            billId = request.getParameter(i.toString());
        }while (billId!=null);

        res.put("code",code);
        return res;
    }
}
