package com.fms.controller;

import com.fms.entity.PageBean;
import com.fms.entity.User;
import com.fms.entity.UserInfo;
import com.fms.service.UserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    //跳转用户列表页面
    @RequestMapping("/adminlist")
    public String adminList(){
        return "admin/admin-list";
    }

    //跳转到角色管理页面
    @RequestMapping("/adminrole")
    public String adminRole(){
        return "admin/admin-role";
    }

    //跳转到添加角色页面
    @RequestMapping("/roleadd")
    public String roleAdd(){
        return "admin/role-add";
    }

    //跳转到添加角色页面
    @RequestMapping("/roleedit")
    public String roleEdit(){
        return "admin/role-edit";
    }

    //显示用户数据,用户列表页面
    @RequestMapping("/getuser")
    @ResponseBody
    public PageBean<User> getUser(HttpServletRequest request){
        String pageNum1 = request.getParameter("page");//当前页，前台传递
        int pageNum = Integer.parseInt(pageNum1);
        int pageSize=Integer.parseInt(request.getParameter("limit"));
        if(pageNum1 == null){
            pageNum = 1;
        }
        //分页出问题....
//        pageNum = pageNum-1;//layui默认传1过来
//        pageNum = pageNum*pageSize+1-1;//limit 计算查询的开始条数，pageSize

        PageBean<User> pageBean = new PageBean<User>();
        List<Map<String, Object>> list = userService.findByPage(pageNum,pageSize);

        pageBean.setPageList(list);
        pageBean.setPageNum(pageNum);
        pageBean.setCode(200);

        int totalCount = userService.getTotalCount();

        int mod = totalCount%pageSize;
        if(mod>0){
            totalCount = (int)(totalCount/pageSize)+1;
        }else{
            totalCount = totalCount/pageSize;
        }

        pageBean.setTotalPage(totalCount);

        return pageBean;
    }

    /**
     * 批量删除用户信息
     * @return
     */
    @RequestMapping("/deletequery")
    @ResponseBody
    public int deleteQuery(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String[] arr = userId.split(",");
        String id = "";
        int result=0;
        //System.out.println(userId);
        for (int i = 0; i < arr.length; i++) {
            id=arr[i];
            result = userService.deleteQuery(Integer.parseInt(id));
        }
        return result;
    }

    /**
     * 删除单行数据
     */
    @RequestMapping("/delete")
    @ResponseBody
    public int delete(HttpServletRequest request){
        String userId = request.getParameter("userId");
        int result=userService.delete(Integer.parseInt(userId));
        return result;
    }

    /**
     * 条件查询用户信息
     */
    @RequestMapping("/conditionalquery")
    @ResponseBody
    public PageBean<User> conditionalQuery(HttpServletRequest request) throws ParseException {
        System.out.println("---------");
//        String start = request.getParameter("start");
//        String end = request.getParameter("end");
        String username = request.getParameter("username");
        System.out.println(username);
//        int pageNum = Integer.parseInt(request.getParameter("page"));//当前页，前台传递
//        int pageSize=Integer.parseInt(request.getParameter("limit"));
//        pageNum = pageNum-1;//layui默认传1过来
//        pageNum = pageNum*pageSize+1-1;//limit 计算查询的开始条数，pageSize

        //System.out.println(start+" "+end+" "+username);

//        //将字符串转化成日期
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
//        java.util.Date start1 = sdf.parse(start);
//        java.util.Date end1 = sdf.parse(end);
//        java.sql.Date sqlDateStart = new java.sql.Date(start1.getTime());
//        java.sql.Date sqlDateEnd = new java.sql.Date(end1.getTime());
        //System.out.println(sqlDateStart+" "+sqlDateEnd);
        Map<String,Object> map = new HashMap<>();
//        map.put("start",sqlDateStart);
//        map.put("end",sqlDateEnd);
        map.put("username",username);

//        String sql = " 1=1 ";
//        if (sqlDateStart!=null && sqlDateEnd!=null){
//            sql+=" and regTime between "+sqlDateStart+" and "+sqlDateEnd;
//        }
//        if (username != null){
//            sql+=" and username="+username;
//        }
//        map.put("sql",sql);
//        map.put("pageNum",pageNum);
//        map.put("pageSize",pageSize);

        PageBean<User> pageBean = new PageBean<User>();
        List<Map<String, Object>> list = userService.conditionalQuery(map);
        pageBean.setPageList(list);
//        pageBean.setPageNum(pageNum);
        pageBean.setCode(200);

//        int totalCount = list.size();
//        System.out.println(totalCount);
//        int mod = totalCount%pageSize;
//        if(mod>0){
//            totalCount = (totalCount/pageSize)+1;
//        }else{
//            totalCount = totalCount/pageSize;
//        }
//        pageBean.setTotalPage(totalCount);
        System.out.println(list);
        return pageBean;
    }


/**
 * 用户个人信息
 */
@RequestMapping("/checkuserinfo")
public String CheckUserInfo() {
    return "user/userinfo";
}

    /*
    根据Id查询用户的基本信息，返回到界面
     */
    @RequestMapping("/userinfodata")
    @ResponseBody
    public UserInfo UserInfoData() {
        return userService.getUserInfo(1);
    }

    /*
    修改个人信息界面
     */
    @RequestMapping("/updateuserinfo")
    public String UpdateUserInfo() {
        return "user/updateinfo";
    }

    /*
     返回修改信息状态码
     */
    @RequestMapping("updateuser")
    @ResponseBody
    public int UpdateUser(HttpServletRequest request, UserInfo userInfo) {
        Map<String, Object> map = new HashMap<>();
        map.put("userinfo", userInfo);
        map.put("userId", 1);
        int code = 0;
        code = userService.UpdateUserInfo(map);
        System.out.println(userInfo.getUsername());
        System.out.println(code);
        return code;
    }

    /*
    返回修改密码界面
     */
    @RequestMapping("/updatepassword")
    public String UpdatePassword() {
        return "user/updatepassword";
    }

    /*
    返回修改密码状态码
     */
    @RequestMapping("/newpassword")
    @ResponseBody
    public int NewPassword(HttpServletRequest request) {
        int code = 3;
        String oldpass = request.getParameter("oldpass");
        String newpass = request.getParameter("newpass");
        String userpass = userService.getpassword(1);
        if (!oldpass.equals(userpass)) {
            code = 2;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("newpass", newpass);
            map.put("userId", 1);
            code = userService.UpdatePass(map);
        }
        return code;
    }

    @RequestMapping("/userimg")
    public String UserImg() {
        return "user/touxiangupload";
    }

    @RequestMapping("/uploadimg")
    @ResponseBody
    public int uploadimg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String savePath = "C:/upload";
        File file = new File(savePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("utf-8");
        if (!ServletFileUpload.isMultipartContent(request)) {
            return 1;
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
                    String newname= UUID.randomUUID().toString();
                    OutputStream os = new FileOutputStream(savePath + "\\" + newname+"."+type);
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
                    Map<String,Object> map=new HashMap<>();
                    map.put("src",newname+"."+type);
                    map.put("userId",1);
                    int res=userService.SaveImg(map);
                    return 0;
                }
            }
        }catch(FileUploadException e){
            e.printStackTrace();
        }
        return 1;
    }
    @RequestMapping("/getuserimg")
    @ResponseBody
    public String getUserImg(){
        String userimg=userService.getUserImg(1);
        return userimg;
    }
}
