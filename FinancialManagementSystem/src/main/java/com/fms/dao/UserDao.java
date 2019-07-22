package com.fms.dao;

import com.fms.entity.User;
import com.fms.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface UserDao {
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
    public List<Map<String, Object>> findByPage(@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);

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
    public List<User> login(String username);

    /**
     * 查询收款人信息
     */
    public List<User> getUserBytrueName(String username);

    /**
     * 根据id查找用户真实姓名
     * @param id
     * @return trueName
     */
    public String selectTruenameById(int id);

    /**
     * 根据id查找部门名
     * @param userId
     * @return
     */
    public String selectDeptNameByUserid(@Param("userId") Integer userId);

    /**
     * 根据id查找电话
     * @param userId
     * @return
     */
    public String selectPhonenumByUserid(@Param("userId") Integer userId);
    /**
     * 根据id查找银行卡号
     * @param userId
     * @return
     */
    public String selectBankcodeByUserid(@Param("userId") Integer userId);

    /**
     *
     */

    public UserInfo getUserInfo(Integer userId);
    public int UpdateUserInfo(Map<String,Object> map);
    public String getpassword(Integer userId);
    public int UpdatePass(Map<String,Object> map);
    public int SaveImg(Map<String,Object> map);
    public String getUserImg(Integer userId);
}