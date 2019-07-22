package com.fms.service.imp;

import com.fms.dao.DeptDao;
import com.fms.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;


    @Override
    public Integer supervisorDept(Integer userId) {
        return deptDao.selectDeptnoByUserid(userId);
    }

    @Override
    public boolean isSupervisor(Integer userId) {
        return supervisorDept(userId)==null?false:true;
    }
}
