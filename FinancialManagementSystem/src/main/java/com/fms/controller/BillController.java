package com.fms.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fms.entity.*;
import com.fms.service.BillService;
import com.fms.service.UserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private UserService userService;


    @RequestMapping("/travel")
    public String TravelAdd(){
        return "order/order-form1";
    }

    @RequestMapping("/welfare")
    public String WelfareAdd(){
        return "order/order-form2";
    }

    @RequestMapping("/public")
    public String PublicAdd(){
        return "order/order-form3";
    }

    @RequestMapping("/property")
    public String PropertylAdd(){
        return "order/order-form4";
    }

    @RequestMapping("/orderinsert")
    @ResponseBody
    public int OrderInsert(HttpServletRequest request) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        String reportType = request.getParameter("reportType");
        String invoice = request.getParameter("invoice");
        Bill bill = new Bill();
        bill.setStatusid(Integer.parseInt(request.getParameter("Statusid")));
        bill.setReportuserid(Integer.parseInt(request.getParameter("reportUserId")));
        bill.setDescription(request.getParameter("description"));
        switch (reportType){
            case "国内差旅":
                bill.setTypeid(1);
                Travel travel = new Travel();
                String startDatetime = request.getParameter("startDatetime");
                java.util.Date utilstartDatetime=sdf.parse(startDatetime);
                java.sql.Date sqlstartDatetime=new java.sql.Date(utilstartDatetime.getTime());
                travel.setStartdatetime(sqlstartDatetime);

                String endDatetime = request.getParameter("endDatetime");
                java.util.Date utilendDatetime=sdf.parse(endDatetime);
                java.sql.Date sqlendDatetime=new java.sql.Date(utilendDatetime.getTime());
                travel.setEnddatetime(sqlendDatetime);

                String satrtlocation1 = request.getParameter("satrtlocation1");
                String satrtlocation2 = request.getParameter("satrtlocation2");
                travel.setStartlocation(satrtlocation1+satrtlocation2);

                String endlocation1 = request.getParameter("endlocation1");
                String endlocation2 = request.getParameter("endlocation2");
                travel.setEndlocation(endlocation1+endlocation2);

                String transportation = request.getParameter("transportation");
                travel.setTransportation(transportation);

                String countUser = request.getParameter("countUser");
                travel.setCountuser(Integer.parseInt(countUser));

                String foodCost = request.getParameter("foodCost");
                travel.setFoodcost(new BigDecimal(foodCost));

                String tranfficCost = request.getParameter("tranfficCost");
                travel.setTrafficcost(new BigDecimal(tranfficCost));

                String accomCost = request.getParameter("accomCost");
                travel.setAccomcost(new BigDecimal(accomCost));

                String otherCost = request.getParameter("otherCost");
                travel.setOthercost(new BigDecimal(otherCost));
                travel.setInvoice(invoice);
                int travellog = billService.insert(bill,travel);
                return travellog;

            case "员工福利":
                bill.setTypeid(2);
                Welfare welfare = new Welfare();
                Map<String,Object> map1 = getFormInfo(request);
                welfare.setCost((BigDecimal) map1.get("Cost"));
                welfare.setUsefor((String) map1.get("useFor"));
                welfare.setDepict((String) map1.get("depict"));
                String payeeUser1 = (String)map1.get("payeeUser");
                String payeeUser_BankCardNum1 = (String)map1.get("payeeUser_BankCardNum");
                User user1 = userService.GetUser(payeeUser1,payeeUser_BankCardNum1);
                welfare.setPayeeuserid(user1.getUserid());
                welfare.setInvoice(invoice);
                int welfarelog = billService.insert(bill,welfare);
                return welfarelog;

            case "物业管理":
                bill.setTypeid(4);
                Property property = new Property();
                Map<String,Object> map2 = getFormInfo(request);
                property.setCost((BigDecimal) map2.get("Cost"));
                property.setUsefor((String) map2.get("useFor"));
                property.setDepict((String) map2.get("depict"));
                String payeeUser2 = (String)map2.get("payeeUser");
                String payeeUser_BankCardNum2 = (String)map2.get("payeeUser_BankCardNum");
                User user2 = userService.GetUser(payeeUser2,payeeUser_BankCardNum2);
                property.setPayeeuserid(user2.getUserid());
                property.setInvoice(invoice);
                int propertylog = billService.insert(bill,property);
                return propertylog;
            case "公务交通":
                bill.setTypeid(3);
                Traffic traffic = new Traffic();
                Map<String,Object> map3 = getFormInfo(request);
                traffic.setCost((BigDecimal) map3.get("Cost"));
                traffic.setUsefor((String) map3.get("useFor"));
                traffic.setDepict((String) map3.get("depict"));
                String payeeUser3 = (String)map3.get("payeeUser");
                String payeeUser_BankCardNum3 = (String)map3.get("payeeUser_BankCardNum");
                User user3 = userService.GetUser(payeeUser3,payeeUser_BankCardNum3);
                traffic.setPayeeuserid(user3.getUserid());
                traffic.setInvoice(invoice);
                int trafficlog = billService.insert(bill,traffic);
                return trafficlog;
        }
        return 0;
    }
    //---------------------------------------------------------------------
    //编辑账单后修改

    @RequestMapping("/orderupdate")
    @ResponseBody
    public int orderUpdate(HttpServletRequest request) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        String reportType = request.getParameter("reportType");
        Bill bill = new Bill();
        bill.setStatusid(0);
        bill.setReportuserid(Integer.parseInt(request.getParameter("reportUserId")));
        bill.setDescription(request.getParameter("description"));
        switch (reportType){
            case "国内差旅":
                bill.setTypeid(1);
                Travel travel = new Travel();
                String startDatetime = request.getParameter("startDatetime");
                java.util.Date utilstartDatetime=sdf.parse(startDatetime);
                java.sql.Date sqlstartDatetime=new java.sql.Date(utilstartDatetime.getTime());
                travel.setStartdatetime(sqlstartDatetime);

                String endDatetime = request.getParameter("endDatetime");
                java.util.Date utilendDatetime=sdf.parse(endDatetime);
                java.sql.Date sqlendDatetime=new java.sql.Date(utilendDatetime.getTime());
                travel.setEnddatetime(sqlendDatetime);

                String satrtlocation1 = request.getParameter("satrtlocation1");
                String satrtlocation2 = request.getParameter("satrtlocation2");
                travel.setStartlocation(satrtlocation1+satrtlocation2);

                String endlocation1 = request.getParameter("endlocation1");
                String endlocation2 = request.getParameter("endlocation2");
                travel.setEndlocation(endlocation1+endlocation2);

                String transportation = request.getParameter("transportation");
                travel.setTransportation(transportation);

                String countUser = request.getParameter("countUser");
                travel.setCountuser(Integer.parseInt(countUser));

                String foodCost = request.getParameter("foodCost");
                travel.setFoodcost(new BigDecimal(foodCost));

                String tranfficCost = request.getParameter("tranfficCost");
                travel.setTrafficcost(new BigDecimal(tranfficCost));

                String accomCost = request.getParameter("accomCost");
                travel.setAccomcost(new BigDecimal(accomCost));

                String otherCost = request.getParameter("otherCost");
                travel.setOthercost(new BigDecimal(otherCost));
                int travellog = billService.update(bill,travel);
                return travellog;

            case "员工福利":
                bill.setTypeid(2);
                Welfare welfare = new Welfare();
                Map<String,Object> map1 = getFormInfo(request);
                welfare.setCost((BigDecimal) map1.get("Cost"));
                welfare.setUsefor((String) map1.get("useFor"));
                welfare.setDepict((String) map1.get("depict"));
                String payeeUser1 = (String)map1.get("payeeUser");
                String payeeUser_BankCardNum1 = (String)map1.get("payeeUser_BankCardNum");
                User user1 = userService.GetUser(payeeUser1,payeeUser_BankCardNum1);
                welfare.setPayeeuserid(user1.getUserid());
                int welfarelog = billService.update(bill,welfare);
                return welfarelog;

            case "物业管理":
                bill.setTypeid(4);
                Property property = new Property();
                Map<String,Object> map2 = getFormInfo(request);
                property.setCost((BigDecimal) map2.get("Cost"));
                property.setUsefor((String) map2.get("useFor"));
                property.setDepict((String) map2.get("depict"));
                String payeeUser2 = (String)map2.get("payeeUser");
                String payeeUser_BankCardNum2 = (String)map2.get("payeeUser_BankCardNum");
                User user2 = userService.GetUser(payeeUser2,payeeUser_BankCardNum2);
                property.setPayeeuserid(user2.getUserid());
                int propertylog = billService.update(bill,property);
                return propertylog;
            case "公务交通":
                bill.setTypeid(3);
                Traffic traffic = new Traffic();
                Map<String,Object> map3 = getFormInfo(request);
                traffic.setCost((BigDecimal) map3.get("Cost"));
                traffic.setUsefor((String) map3.get("useFor"));
                traffic.setDepict((String) map3.get("depict"));
                String payeeUser3 = (String)map3.get("payeeUser");
                String payeeUser_BankCardNum3 = (String)map3.get("payeeUser_BankCardNum");
                User user3 = userService.GetUser(payeeUser3,payeeUser_BankCardNum3);
                traffic.setPayeeuserid(user3.getUserid());
                int trafficlog = billService.update(bill,traffic);
                return trafficlog;
        }
        return 0;
    }
    private Map<String,Object> getFormInfo(HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        String useFor = request.getParameter("useFor");
        String depict = request.getParameter("depict");
        String payeeUser = request.getParameter("payeeUser");
        String payeeUser_BankCardNum = request.getParameter("payeeUser_BankCardNum");
        String Cost = request.getParameter("Cost");
        map.put("useFor",useFor);
        map.put("depict",depict);
        map.put("payeeUser",payeeUser);
        map.put("payeeUser_BankCardNum",payeeUser_BankCardNum);
        map.put("Cost",new BigDecimal(Cost));
        return map;
    }
    @RequestMapping("/orderupdatesubmit")
    @ResponseBody
    public int orderUpdateSubmit(HttpServletRequest request)throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        String reportType = request.getParameter("reportType");
        Bill bill = new Bill();
        bill.setStatusid(1);
        bill.setReportuserid(Integer.parseInt(request.getParameter("reportUserId")));
        bill.setDescription(request.getParameter("description"));
        String billid = request.getParameter("BillId");
        bill.setBillid(billid);
        switch (reportType){
            case "国内差旅":
                bill.setTypeid(1);
                Travel travel = new Travel();
                String startDatetime = request.getParameter("startDatetime");
                java.util.Date utilstartDatetime=sdf.parse(startDatetime);
                java.sql.Date sqlstartDatetime=new java.sql.Date(utilstartDatetime.getTime());
                travel.setStartdatetime(sqlstartDatetime);

                String endDatetime = request.getParameter("endDatetime");
                java.util.Date utilendDatetime=sdf.parse(endDatetime);
                java.sql.Date sqlendDatetime=new java.sql.Date(utilendDatetime.getTime());
                travel.setEnddatetime(sqlendDatetime);

                String satrtlocation1 = request.getParameter("satrtlocation1");
                String satrtlocation2 = request.getParameter("satrtlocation2");
                travel.setStartlocation(satrtlocation1+satrtlocation2);

                String endlocation1 = request.getParameter("endlocation1");
                String endlocation2 = request.getParameter("endlocation2");
                travel.setEndlocation(endlocation1+endlocation2);

                String transportation = request.getParameter("transportation");
                travel.setTransportation(transportation);

                String countUser = request.getParameter("countUser");
                travel.setCountuser(Integer.parseInt(countUser));

                String foodCost = request.getParameter("foodCost");
                travel.setFoodcost(new BigDecimal(foodCost));

                String tranfficCost = request.getParameter("tranfficCost");
                travel.setTrafficcost(new BigDecimal(tranfficCost));

                String accomCost = request.getParameter("accomCost");
                travel.setAccomcost(new BigDecimal(accomCost));

                String otherCost = request.getParameter("otherCost");
                travel.setOthercost(new BigDecimal(otherCost));
                travel.setBillid(billid);
                int travellog = billService.update(bill,travel);
                return travellog;

            case "员工福利":
                bill.setTypeid(2);
                Welfare welfare = new Welfare();
                Map<String,Object> map1 = getFormInfo(request);
                welfare.setCost((BigDecimal) map1.get("Cost"));
                welfare.setUsefor((String) map1.get("useFor"));
                welfare.setDepict((String) map1.get("depict"));
                String payeeUser1 = (String)map1.get("payeeUser");
               // System.out.println(payeeUser1);
                String payeeUser_BankCardNum1 = (String)map1.get("payeeUser_BankCardNum");
                User user1 = userService.GetUser(payeeUser1,payeeUser_BankCardNum1);
                welfare.setPayeeuserid(user1.getUserid());
                welfare.setBillid(billid);
                int welfarelog = billService.update(bill,welfare);
                System.out.println(bill);
                System.out.println(welfarelog);
                return welfarelog;

            case "物业管理":
                bill.setTypeid(4);
                Property property = new Property();
                Map<String,Object> map2 = getFormInfo(request);
                property.setCost((BigDecimal) map2.get("Cost"));
                property.setUsefor((String) map2.get("useFor"));
                property.setDepict((String) map2.get("depict"));
                String payeeUser2 = (String)map2.get("payeeUser");
                String payeeUser_BankCardNum2 = (String)map2.get("payeeUser_BankCardNum");

                User user2 = userService.GetUser(payeeUser2,payeeUser_BankCardNum2);

                property.setPayeeuserid(user2.getUserid());
                property.setBillid(billid);
                int propertylog = billService.update(bill,property);
                return propertylog;
            case "公务交通":
                bill.setTypeid(3);
                Traffic traffic = new Traffic();
                Map<String,Object> map3 = getFormInfo(request);
                traffic.setCost((BigDecimal) map3.get("Cost"));
                traffic.setUsefor((String) map3.get("useFor"));
                traffic.setDepict((String) map3.get("depict"));
                String payeeUser3 = (String)map3.get("payeeUser");
                String payeeUser_BankCardNum3 = (String)map3.get("payeeUser_BankCardNum");
                User user3 = userService.GetUser(payeeUser3,payeeUser_BankCardNum3);

                traffic.setPayeeuserid(user3.getUserid());
                traffic.setBillid(billid);
                int trafficlog = billService.update(bill,traffic);
                return trafficlog;
        }
        return 0;
    }

    //---------------------------------------------------------------------
    //上传附件
    @RequestMapping("/uploadinvoice")
    @ResponseBody
    public Map<String, Object> uploadimg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String savePath = "C:/upload/invoice";
        Map<String,Object> map=new HashMap<>();
        File file = new File(savePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("utf-8");
        if (!ServletFileUpload.isMultipartContent(request)) {
            map.put("res",1);
            return map;
        }
        try {
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem fileItem : list) {
                if (fileItem.isFormField()) {
                    String name = fileItem.getFieldName();
                    String value = fileItem.getString("utf-8");
                    System.out.println("普通表单数据" + value);
                } else {
                    String name = fileItem.getName();

                    String type = name.substring(name.indexOf('.') + 1);
                    System.out.println("上传文件的扩展名" + type);
                    System.out.println("上传文件的名字：" + name);
                    //System.out.println("重命名："+id);
                    InputStream is = fileItem.getInputStream();

                    OutputStream os = new FileOutputStream(savePath + "\\" + name);
                    int size = is.available();
                    System.out.println("文件大小：" + size);
                    System.out.println("上传成功！保存");
                    int i = -1;
                    byte[] b = new byte[1024];
                    while ((i = is.read(b)) != -1) {
                        os.write(b, 0, i);
                    }
                    is.close();
                    os.flush();
                    os.close();
                    fileItem.delete();
                    //String code="{'code':0}";

                    map.put("invoice",savePath+"/"+name);
                    map.put("res",0);

                    return map;
                }
            }
        }catch(FileUploadException e){
            e.printStackTrace();
        }

        map.put("res",1);
        return map;
    }

    //---------------------------------------------------------------------
    //跳转历史账单页面
    @RequestMapping("/orderhistory")
    public String adminList(){
        return "order/order-history";
    }

    //跳转我的已办页面
    @RequestMapping("/orderwait")
    public String orderWari(){
        return "order/order-wait";
    }

    //跳转我的待办页面
    @RequestMapping("/ordererror")
    public String orderError(){
        return "order/order-error";
    }

    //跳转编辑页面
    @RequestMapping("/ef1")
    public String orderEdit1(){
        return "order/order-edit1";
    }
    @RequestMapping("/ef2")
    public String orderEdit2(){
        return "order/order-edit2";
    }
    @RequestMapping("/ef3")
    public String orderEdit3(){
        return "order/order-edit3";
    }
    @RequestMapping("/ef4")
    public String orderEdit4(){
        return "order/order-edit4";
    }

    /**
     * 得到历史账单数据
     * @return
     */
    @RequestMapping("/getorderhistory")
    @ResponseBody
    public PageBean<Bill> getorderHistory(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("User");
        int pageNum = Integer.parseInt(request.getParameter("page"));
        int pageSize=Integer.parseInt(request.getParameter("limit"));
        pageNum = pageNum-1;//layui默认传1过来
        pageNum = pageNum*pageSize+1-1;//limit 计算查询的开始条数，pageSize

        PageBean<Bill> pageBean = new PageBean<>();
        List<Map<String, Object>> list = billService.getorderHistory(pageNum,pageSize,user.getUserid());
        pageBean.setPageList(list);
        pageBean.setPageNum(pageNum);
        pageBean.setCode(200);

        int totalCount = billService.getTotalCount();
        int page = totalCount % pageSize;
        if(page>0){
            totalCount = totalCount/pageSize+1;
        }else{
            totalCount = totalCount/pageSize;
        }
        pageBean.setTotalPage(totalCount);

        return pageBean;
    }

    /**
     * 条件查询历史账单
     */
    @RequestMapping("/getorderhistoryquery")
    @ResponseBody
    public PageBean<Bill> getOrderHistoryQuery(HttpServletRequest request){
        String type = request.getParameter("type");
        Map<String,Object> map = new HashMap<>();
        map.put("typeName",type);
        PageBean<Bill> pageBean = new PageBean<>();
        List<Map<String, Object>> list = billService.getOrderHistoryQuery(map);
        pageBean.setPageList(list);
        pageBean.setCode(200);
        return pageBean;
    }

    /**
     * 得到已办账单信息
     */
    @RequestMapping("/getorderwait")
    @ResponseBody
    public PageBean<Bill> getOrderWait(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("User");
        int pageNum = Integer.parseInt(request.getParameter("page"));
        int pageSize=Integer.parseInt(request.getParameter("limit"));

//        pageNum = pageNum-1;//layui默认传1过来
//        pageNum = pageNum*pageSize+1-1;//limit 计算查询的开始条数，pageSize

        PageBean<Bill> pageBean = new PageBean<>();
        List<Map<String, Object>> list = billService.getOrderWait(pageNum,pageSize,user.getUserid());

 //     System.out.println("替换前："+list);
        //遍历找出状态字段，替换成待审核
        for(Map map : list){
            Set set = map.keySet();
            Iterator it = set.iterator();
            while (it.hasNext()){
                Object key = it.next();
                if (key.equals("statusName")){
                    map.put("statusName","待审核");//替换其中的值
                }
            }
        }
    //    System.out.println("替换后:"+list);
        pageBean.setPageList(list);
        pageBean.setPageNum(pageNum);
        pageBean.setCode(200);

        int totalCount = billService.getTotalWaitCount();
        int page = totalCount % pageSize;
        if(page>0){
            totalCount = totalCount/pageSize+1;
        }else{
            totalCount = totalCount/pageSize;
        }
        pageBean.setTotalPage(totalCount);

        return pageBean;
    }

    /**
     * 条件查询已办账单
     */
    @RequestMapping("/getorderwaitquery")
    @ResponseBody
    public PageBean<Bill> getOrderWaitQuery(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("User");
        System.out.println("用户ID："+user.getUserid());
        String type = request.getParameter("type");
        Map<String,Object> map = new HashMap<>();
        map.put("typeName",type);
        map.put("userId",user.getUserid());
        PageBean<Bill> pageBean = new PageBean<>();
        List<Map<String, Object>> list = billService.getOrderWaitQuery(map);
        for(Map m : list){
            Set set = m.keySet();
            Iterator it = set.iterator();
            while (it.hasNext()){
                Object key = it.next();
                if (key.equals("statusName")){
                    m.put("statusName","待审核");//替换其中的值
                }
            }
        }
        pageBean.setPageList(list);
        pageBean.setCode(200);
        return pageBean;
    }


    /**
     * 得到我的待办数据
     * @return
     */
    @RequestMapping("/getordererror")
    @ResponseBody
    public PageBean<Bill> getOrderError(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("User");
        int pageNum = Integer.parseInt(request.getParameter("page"));
        int pageSize=Integer.parseInt(request.getParameter("limit"));
        pageNum = pageNum-1;//layui默认传1过来
        pageNum = pageNum*pageSize+1-1;//limit 计算查询的开始条数，pageSize
        PageBean<Bill> pageBean = new PageBean<>();
        List<Map<String, Object>> list = billService.getOrderError(pageNum,pageSize,user.getUserid());

        pageBean.setPageList(list);
        pageBean.setPageNum(pageNum);
        pageBean.setCode(200);

        int totalCount = billService.getTotalErrorCount();
        int page = totalCount % pageSize;
        if(page>0){
            totalCount = totalCount/pageSize+1;
        }else{
            totalCount = totalCount/pageSize;
        }
        pageBean.setTotalPage(totalCount);

        return pageBean;
    }

    /**
     * 条件查询我的待办
     */
    @RequestMapping("/getordererrorquery")
    @ResponseBody
    public PageBean<Bill> getOrderErrorQuery(HttpServletRequest request){
        String type = request.getParameter("type");
        System.out.println(type);
        Map<String,Object> map = new HashMap<>();
        map.put("typeName",type);
        PageBean<Bill> pageBean = new PageBean<>();
        List<Map<String, Object>> list = billService.getOrderErrorQuery(map);
        //System.out.println(list);
        pageBean.setPageList(list);
        pageBean.setCode(200);
        return pageBean;
    }

    /**
     * 删除单行数据
     */
    @RequestMapping("/delete")
    @ResponseBody
    public int delete(HttpServletRequest request){
        String billId = request.getParameter("billId");
        String typeName = request.getParameter("typeName");
        if (typeName.equals("国内差旅")){
            int travel = billService.deleteTravel(billId);
            int bill  = billService.deleteBill(billId);
            if(travel==0||bill==0){
                return 0;
            }
        }else if (typeName.equals("员工福利")){
            int welfare = billService.deleteWelfare(billId);
            int bill  = billService.deleteBill(billId);
            if(welfare==0||bill==0){
                return 0;
            }
        }else if (typeName.equals("公务交通")){
            int traffic = billService.deleteTraffic(billId);
            int bill = billService.deleteBill(billId);
            if(traffic==0||bill==0){
                return 0;
            }
        }else if (typeName.equals("物业管理")){
            int property=billService.deleteProperty(billId);
            int bill  = billService.deleteBill(billId);
            if(property==0||bill==0){
                return 0;
            }
        }
        return 1;
    }

    /**
     * 批量删除我的待办
     */
    @RequestMapping("/deletequery")
    @ResponseBody
    public int deleteQuery(HttpServletRequest request){
        String bill = request.getParameter("billId");
        String type = request.getParameter("type");
        String[]billId  = bill.split(",");
        String[] typeName = type.split(",");
        for (int i=0;i<billId.length;i++){
            Map<String,Object> map = new HashMap<>();
            map.put(billId[i],typeName[i]);
            Object value = map.get(billId[i]);
            //System.out.println(billId[i]+"  "+value);
            if(value.equals("国内差旅")){
                int travelResult = billService.deleteTravel(billId[i]);
                int billResult  = billService.deleteBill(billId[i]);
                if(travelResult==0||billResult==0){
                    return 0;
                }
            }else if (value.equals("公务交通")){
                int trafficResult = billService.deleteTraffic(billId[i]);
                int billResult = billService.deleteBill(billId[i]);
                if(trafficResult==0||billResult==0){
                    return 0;
                }
            }else if (value.equals("物业管理")){
                int propertyResult=billService.deleteProperty(billId[i]);
                int billResult = billService.deleteBill(billId[i]);
                if(propertyResult==0||billResult==0){
                    return 0;
                }
            }else if(value.equals("员工福利")){
                int welfareResult = billService.deleteWelfare(billId[i]);
                int billResult  = billService.deleteBill(billId[i]);
                if(welfareResult==0||billResult==0){
                    return 0;
                }
            }
        }

        return 1;
    }

    /**
     * 渲染编辑页面的信息
     *国内差旅
     */
    @RequestMapping("/getedit1")
    @ResponseBody
    public Map<String,Object> getEdit1(HttpServletRequest request){
        String billId = request.getParameter("billId");
        Map<String,Object> map = billService.getEdit1(billId);
        //System.out.println(map);
        return map;
    }

    /**
     * 员工福利
     */
    @RequestMapping("/getedit2")
    @ResponseBody
    public Map<String,Object> getEdit2(HttpServletRequest request){
        String billId = request.getParameter("billId");
        Map<String,Object> map = billService.getEdit2(billId);
        System.out.println(map);
        return map;
    }
    /**
     * 公务交通
     * @param request
     * @return
     */
    @RequestMapping("/getedit3")
    @ResponseBody
    public Map<String,Object> getEdit3(HttpServletRequest request){
        String billId = request.getParameter("billId");
        Map<String,Object> map = billService.getEdit3(billId);
        //System.out.println(map);
        return map;
    }
    /**
     * 物业管理
     */
    @RequestMapping("/getedit4")
    @ResponseBody
    public Map<String,Object> getEdit4(HttpServletRequest request){
        String billId = request.getParameter("billId");
        Map<String,Object> map = billService.getEdit4(billId);
        //System.out.println(map);
        return map;
    }

}
