package com.fms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WebController {

    //页面跳转--后台管理页面
    @RequestMapping("/index")
    public String index(){
        return "web/index";
    }


    //页面跳转--我的桌面
    @RequestMapping("/welcome")
    public String welcome(){
        return "web/welcome";
    }

    @RequestMapping("/job-wait")
    public String jobWait(){
        return "job/job-wait";
    }

    @RequestMapping("/job-pass")
    public String jobPass(){
        return "job/job-pass";
    }

    @RequestMapping("/view1")
    public String orderView1(){
        return "job/order-view1";
    }
}
