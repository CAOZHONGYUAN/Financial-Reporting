package com.fms.service;

import com.fms.entity.User;
import com.fms.entity.UserInfo;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 获取用户列表数据的总条数
     * @return
     */
    public int getTotalCount();

    /**
     * 根据页码和每页显示条数查询用户信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<Map<String, Object>> findByPage(int pageNum, int pageSize);

    /**
     * 根据ID批量删除用户
     * @param userid
     * @return
     */
    public int deleteQuery(int userid);
    /**
     * 删除单行数据
     * @param userid
     * @return
     */
    public int delete(int userid);
    /**
     * 条件查询用户信息
     */
    public List<Map<String, Object>> conditionalQuery(Map<String,Object> map);

    /**
     * 登录验证
     */
    public User login(String username,String password);

    /**
     * 根据银行卡号和用户名判断用户是否存在
     * @param username
     * @param payeeUser_BankCardNum
     * @return
     */
    public User GetUser(String username,String payeeUser_BankCardNum);


    /**
     * 个人信息
     */
    public UserInfo getUserInfo(Integer userId);
    public int UpdateUserInfo(Map<String,Object> map);
    public String getpassword(Integer userId);
    public int UpdatePass(Map<String,Object> map);
    public int SaveImg(Map<String,Object> map);
    public String getUserImg(Integer userId);
}
