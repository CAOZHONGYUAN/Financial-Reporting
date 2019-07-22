package com.fms.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Bill {
    private String billid;//账单id

    private Integer reportuserid;//报账人id(外键)

    private Integer reviewuserid;//审核人id(外键)

    private Date submitdatetime;//报账发起时间

    private String description;//报账说明

    private String remarks;//未通过理由

    private Integer statusid;//报账类型

    private BigDecimal countcost;//报账总金额

    private Integer typeid;//报账类型id

    public String getBillid() {
        return billid;
    }

    public void setBillid(String billid) {
        this.billid = billid;
    }

    public Integer getReportuserid() {
        return reportuserid;
    }

    public void setReportuserid(Integer reportuserid) {
        this.reportuserid = reportuserid;
    }

    public Integer getReviewuserid() {
        return reviewuserid;
    }

    public void setReviewuserid(Integer reviewuserid) {
        this.reviewuserid = reviewuserid;
    }

    public Date getSubmitdatetime() {
        return submitdatetime;
    }

    public void setSubmitdatetime(Date submitdatetime) {
        this.submitdatetime = submitdatetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStatusid() {
        return statusid;
    }

    public void setStatusid(Integer statusid) {
        this.statusid = statusid;
    }

    public BigDecimal getCountcost() {
        return countcost;
    }

    public void setCountcost(BigDecimal countcost) {
        this.countcost = countcost;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billid='" + billid + '\'' +
                ", reportuserid=" + reportuserid +
                ", reviewuserid=" + reviewuserid +
                ", submitdatetime=" + submitdatetime +
                ", description='" + description + '\'' +
                ", remarks='" + remarks + '\'' +
                ", statusid=" + statusid +
                ", countcost=" + countcost +
                ", typeid=" + typeid +
                '}';
    }
}