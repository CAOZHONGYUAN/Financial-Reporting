package com.fms.service;

import org.springframework.beans.factory.annotation.Autowired;

public interface DeptService {

    /**
     * 判断该id用户是否为主管
     * @param userId
     * @return 部门号,null
     */
    public Integer supervisorDept(Integer userId);
    public boolean isSupervisor(Integer userId);
}
