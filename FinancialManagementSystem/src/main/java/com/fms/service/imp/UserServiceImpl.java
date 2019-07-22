package com.fms.service.imp;

import com.fms.dao.UserDao;
import com.fms.entity.User;
import com.fms.entity.UserInfo;
import com.fms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public int getTotalCount() {
        return userDao.getTotalCount();
    }

    @Override
    public List<Map<String, Object>> findByPage(int pageNum, int pageSize) {
        return userDao.findByPage(pageNum,pageSize);
    }

    @Override
    public int deleteQuery(int userid) {
        return userDao.deleteQuery(userid);
    }

    @Override
    public int delete(int userid) {
        return userDao.delete(userid);
    }

    @Override
    public List<Map<String, Object>> conditionalQuery(Map<String,Object> map) {
        return userDao.conditionalQuery(map);
    }

    @Override
    public User login(String username, String password) {
        List<User> userList = userDao.login(username);
        if (userList.size()==0){
            return null;
        }
        for (User user : userList) {
            if (password.equals(user.getUserpass())){
                return user;
            }
        }
        return null;
    }

    @Override
    public User GetUser(String username,String payeeUser_BankCardNum) {
        List<User> list = userDao.getUserBytrueName(username);
        User user = new User();
        user.setUserid(0);
        if(list.size()==0){
            return user;
        }
        for (User user1 : list) {
            if (payeeUser_BankCardNum.equals(user1.getBankcode())){
                return user1;
            }
        }
        return user;
    }


    /**
     * 个人信息
     */

    @Override
    public UserInfo getUserInfo(Integer userId) {
        return userDao.getUserInfo(userId);
    }

    @Override
    public int UpdateUserInfo(Map<String, Object> map) {
        return userDao.UpdateUserInfo(map);
    }

    @Override
    public String getpassword(Integer userId) {
        return userDao.getpassword(userId);
    }

    @Override
    public int UpdatePass(Map<String, Object> map) {
        return userDao.UpdatePass(map);
    }

    @Override
    public int SaveImg(Map<String, Object> map) {
        return userDao.SaveImg(map);
    }

    @Override
    public String getUserImg(Integer userId) {
        return userDao.getUserImg(userId);
    }
}
