package com.fms.dao;

public interface DeptDao {

    /**
     * 通过userId判断是否为部门主管
     * @param userId
     * @return  部门号
     */
    public Integer selectDeptnoByUserid(Integer userId);

}