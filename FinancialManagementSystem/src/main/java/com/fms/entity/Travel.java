package com.fms.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Travel {
    private Integer travelid;

    private Date startdatetime;

    private Date enddatetime;

    private String startlocation;

    private String endlocation;

    private String transportation;

    private Integer countuser;

    private BigDecimal foodcost;

    private BigDecimal trafficcost;

    private BigDecimal accomcost;

    private BigDecimal othercost;

    private String billid;

    private Integer payeeuserid;

    private String invoice;

    public Integer getTravelid() {
        return travelid;
    }

    public void setTravelid(Integer travelid) {
        this.travelid = travelid;
    }

    public Date getStartdatetime() {
        return startdatetime;
    }

    public void setStartdatetime(Date startdatetime) {
        this.startdatetime = startdatetime;
    }

    public Date getEnddatetime() {
        return enddatetime;
    }

    public void setEnddatetime(Date enddatetime) {
        this.enddatetime = enddatetime;
    }

    public String getStartlocation() {
        return startlocation;
    }

    public void setStartlocation(String startlocation) {
        this.startlocation = startlocation;
    }

    public String getEndlocation() {
        return endlocation;
    }

    public void setEndlocation(String endlocation) {
        this.endlocation = endlocation;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public Integer getCountuser() {
        return countuser;
    }

    public void setCountuser(Integer countuser) {
        this.countuser = countuser;
    }

    public BigDecimal getFoodcost() {
        return foodcost;
    }

    public void setFoodcost(BigDecimal foodcost) {
        this.foodcost = foodcost;
    }

    public BigDecimal getTrafficcost() {
        return trafficcost;
    }

    public void setTrafficcost(BigDecimal trafficcost) {
        this.trafficcost = trafficcost;
    }

    public BigDecimal getAccomcost() {
        return accomcost;
    }

    public void setAccomcost(BigDecimal accomcost) {
        this.accomcost = accomcost;
    }

    public BigDecimal getOthercost() {
        return othercost;
    }

    public void setOthercost(BigDecimal othercost) {
        this.othercost = othercost;
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

    @Override
    public String toString() {
        return "Travel{" +
                "travelid=" + travelid +
                ", startdatetime=" + startdatetime +
                ", enddatetime=" + enddatetime +
                ", startlocation='" + startlocation + '\'' +
                ", endlocation='" + endlocation + '\'' +
                ", transportation='" + transportation + '\'' +
                ", countuser=" + countuser +
                ", foodcost=" + foodcost +
                ", trafficcost=" + trafficcost +
                ", accomcost=" + accomcost +
                ", othercost=" + othercost +
                ", billid='" + billid + '\'' +
                ", payeeuserid=" + payeeuserid +
                ", invoice='" + invoice + '\'' +
                '}';
    }
}