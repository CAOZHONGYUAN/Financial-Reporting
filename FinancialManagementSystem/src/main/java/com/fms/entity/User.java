package com.fms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class User {
    private Integer userid;

    private String username;

    private String userpass;

    private String truename;

    private String deptname;

    private Integer roleid;

    private String phonenum;

    private String email;

    private String idcard;

    private String bankcode;
    
    private Date regtime;

    private Role role;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public Date getRegtime() {
        return regtime;
    }


    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", userpass='" + userpass + '\'' +
                ", truename='" + truename + '\'' +
                ", deptname='" + deptname + '\'' +
                ", roleid=" + roleid +
                ", phonenum='" + phonenum + '\'' +
                ", email='" + email + '\'' +
                ", idcard='" + idcard + '\'' +
                ", bankcode='" + bankcode + '\'' +
                ", regtime=" + regtime +
                ", role=" + role +
                '}';
    }
}