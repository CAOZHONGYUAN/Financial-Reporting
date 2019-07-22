package com.fms.entity;

import java.math.BigDecimal;

public class Traffic {
    private Integer trafficid;

    private String usefor;

    private BigDecimal cost;

    private String depict;

    private String billid;

    private Integer payeeuserid;

    private String invoice;

    public Integer getTrafficid() {
        return trafficid;
    }

    public void setTrafficid(Integer trafficid) {
        this.trafficid = trafficid;
    }

    public String getUsefor() {
        return usefor;
    }

    public void setUsefor(String usefor) {
        this.usefor = usefor;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }

    public String getBillid() {
        return billid;
    }

    public void setBillid(String billid) {
        this.billid = billid;
    }

    public Integer getPayeeuserid() {
        return payeeuserid;
    }

    public void setPayeeuserid(Integer payeeuserid) {
        this.payeeuserid = payeeuserid;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }
}