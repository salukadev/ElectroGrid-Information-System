package com.electrogrid.billing.model;

import java.sql.Date;

public class Bill {
	private Integer billId;
	private Integer accNo;
	private Integer consumptionId;
	private java.sql.Date period;
	private Float previousBal;
	private Float calculatedBal;
	private Float totPay;
	private Float totalDue;
	
	public Bill() {}
	
	public Bill(Integer billId, Integer accNo, Integer consumptionId, Date period, Float previousBal, Float calculatedBal, Float totPay,
			Float totalDue) {
		super();
		this.accNo = accNo;
		this.consumptionId = consumptionId;
		this.period = period;
		this.previousBal = previousBal;
		this.calculatedBal = calculatedBal;
		this.totPay = totPay;
		this.totalDue = totalDue;
	}
	
	//method to create records
	
	
	//getters and setters
	public Integer getBillId() {
		return billId;
	}
	public void setBillId(Integer billId) {
		this.billId = billId;
	}
	public Integer getAccNo() {
		return accNo;
	}
	public void setAccNo(Integer accNo) {
		this.accNo = accNo;
	}
	public Integer getConsumptionId() {
		return consumptionId;
	}
	public void setConsumptionId(Integer consumptionId) {
		this.consumptionId = consumptionId;
	}
	public java.sql.Date getPeriod() {
		return period;
	}
	public void setPeriod(java.sql.Date period) {
		this.period = period;
	}
	public Float getPreviousBal() {
		return previousBal;
	}
	public void setPreviousBal(Float previousBal) {
		this.previousBal = previousBal;
	}
	public Float getCalculatedBal() {
		return calculatedBal;
	}
	public void setCalculatedBal(Float calculatedBal) {
		this.calculatedBal = calculatedBal;
	}
	public Float getTotPay() {
		return totPay;
	}
	public void setTotPay(Float totPay) {
		this.totPay = totPay;
	}
	public Float getTotalDue() {
		return totalDue;
	}
	public void setTotalDue(Float totalDue) {
		this.totalDue = totalDue;
	}
	
	
	
}
