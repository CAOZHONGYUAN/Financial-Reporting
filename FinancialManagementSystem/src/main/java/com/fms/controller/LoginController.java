package com.fms.controller;

import com.fms.entity.User;
import com.fms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Login")
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username"+username+"password"+password);
        User user = userService.login(username,password);
        if (user!=null){
            request.getSession().setAttribute("User",user);
            return "redirect:../web/index.do";
        }else{

            return "redirect:../login.html";
        }
    }
    @RequestMapping("/exit")
    public String exit(HttpServletRequest request){
        request.getSession().setAttribute("User",null);
        return "redirect:../login.html";
    }
    @RequestMapping("/getUser")
    @ResponseBody
    public User GetUser(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("User");
        return user;
    }
}
