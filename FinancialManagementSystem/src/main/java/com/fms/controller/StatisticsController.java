package com.fms.controller;

import com.fms.entity.Statistics;
import com.fms.entity.User;
import com.fms.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {
    /*
    个人专项总览
     */
    @Autowired
    private StatisticsService statisticsService;
    /*
    返回到个人数据总览
     */
    @RequestMapping("/personalcheck")
    public String PersonalCheck(){
       return "statistics/tongji1";
    }

    /*
      请求个人报账数据（四项），得到总计
     */
    @RequestMapping("/personaldata")
    @ResponseBody
    public Statistics PersonalData(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("User");
        Statistics statistics=statisticsService.getAllCount(user.getUserid());
        System.out.println(statistics);
        return statistics;
    }

    /*
    返回个人报账总览页面
     */
    @RequestMapping("/billcheck")
    public String BillCheck(){
        return "statistics/tongji2";
    }

    /*
    返回1-7月个人报账总览
     */
    @RequestMapping("/billdata")
    @ResponseBody
    public List<Object> BillData(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("User");
        List<Map<String,Object>> list1=statisticsService.getTrafficCount(user.getUserid());
        List<Map<String,Object>> list2=statisticsService.getPropertyCount(user.getUserid());
       List<Object> list=new ArrayList<>();
       list.add(list1);
       list.add(list2);
       return list;
    }

    /**
     * 差旅地图
     * @return
     */
    @RequestMapping("/mapdata")
    @ResponseBody
    public List<Map<String,Object>> MapData(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("User");
       List<Map<String,Object>> list=statisticsService.getTravelMap(user.getUserid());
        return list;
    }
    @RequestMapping("/travelmap")
    public String TravelMap(){
        return "statistics/tongji3";
    }
}
