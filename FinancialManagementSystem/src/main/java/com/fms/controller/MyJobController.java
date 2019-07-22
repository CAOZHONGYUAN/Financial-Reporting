package com.fms.controller;

import com.fms.entity.PageBeanOne;
import com.fms.entity.User;
import com.fms.service.BillService;
import com.fms.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: FinancialManagementSystem
 * @description
 * @author: Lei
 * @create: 2019-03-25 15:12
 **/
@Controller
@RequestMapping("/job")
public class MyJobController {
    @Autowired
    private BillService billService;
    @Autowired
    private DeptService deptService;

    /**
     * 根据角色查询待审核账单
     * @param request
     * @return
     */
    @RequestMapping("/findWaitBill")
    public @ResponseBody
    PageBeanOne<Object> findWaitBill(HttpServletRequest request){
        List<Map<String, Object>> list;//数据集合
        int statusId = 2;//默认角色
        Integer deptId = null;//部门号
        Map<String,Object> searchMap = new HashMap<>();//模糊匹配参数

        //设置默认参数
        searchMap.put("startDate"   , "1990-01-01 00:00:00" );
        searchMap.put("endDate"     , "2030-01-01 00:00:00" );
        searchMap.put("billId"      , ""                     );
        //获取请求参数
        String startDate = request.getParameter("start");
        if (startDate!=null && !startDate.equals("")){
            searchMap.put("startDate", startDate);
        }
        String endDate = request.getParameter("end");
        if (endDate!=null && !endDate.equals("")){
            searchMap.put("endDate", endDate);
        }
        String billId = request.getParameter("billId");
        if (billId!=null && !billId.equals("")){
            searchMap.put("billId",billId);
        }

        //创建pagebean
        PageBeanOne<Object> pageBean = new PageBeanOne<>();
        String pageIndexStr = request.getParameter("pageIndex");
        Integer pageIndex = 1;
        if (pageIndexStr!=null){
            pageIndex = new Integer(pageIndexStr);
        }
        pageBean.setPageIndex(pageIndex);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        if (user!=null){
            statusId = user.getRoleid()-1;
            if(statusId==0){
                return pageBean;//
            }
            deptId = deptService.supervisorDept(user.getUserid());
           if (deptId!=null){
               pageBean = billService.selectByStatusAndDeptno(statusId, deptId,searchMap,pageBean);
               return pageBean;
           }
        }

        pageBean = billService.selectByStatus(statusId,searchMap,pageBean);
        request.getSession().setAttribute("waitBill",pageBean);//存入session备用
        return pageBean;
    }

    /**
     * 根据角色查询待审核账单
     * @param request
     * @return
     */
    @RequestMapping("/findPassBill")
    public @ResponseBody PageBeanOne<Object> findPassBill(HttpServletRequest request){
        List<Map<String, Object>> list;
        int statusId = 3;//默认
        Integer deptId = null;
        Map<String,Object> searchMap = new HashMap<>();//模糊匹配参数

        //设置参数
        searchMap.put("startDate"   , "1990-01-01 00:00:00" );
        searchMap.put("endDate"     , "2030-01-01 00:00:00" );
        searchMap.put("billId"      , ""                    );

        //创建pageBean
        PageBeanOne<Object> pageBean = new PageBeanOne<>();
        String pageIndexStr = request.getParameter("pageIndex");
        Integer pageIndex = 1;
        if (pageIndexStr!=null){
            pageIndex = new Integer(pageIndexStr);
        }
        pageBean.setPageIndex(pageIndex);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        if (user!=null){
            statusId = user.getRoleid();
            if(statusId==1){
                return pageBean;//
            }
            deptId = deptService.supervisorDept(user.getUserid());
            if (deptId!=null){
                pageBean = billService.selectByStatusAndDeptno(statusId, deptId,searchMap, pageBean);
                return pageBean;
            }
        }
        pageBean = billService.selectByStatus(statusId,searchMap, pageBean);
        request.getSession().setAttribute("passBill",pageBean);//存入session备用
        return pageBean;
    }
}
