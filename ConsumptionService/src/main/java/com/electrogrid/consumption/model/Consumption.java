package com.electrogrid.consumption.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.electrogrid.consumption.utils.DBConnectionSingleton;


public class Consumption {
	private int consumptionId;
	private int accNo;
	private int year;
	private int month;
	private int units;
	
	
	static Connection con = DBConnectionSingleton.getConnection();
	
	public int getConsumptionId() {
		return consumptionId;
	}
	
	public void setConsumptionId(int consumptionId) {
		this.consumptionId = consumptionId;
	}
	
	public int getAccNo() {
		return accNo;
	}
	
	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public int getUnits() {
		return units;
	}
	
	public void setUnits(int units) {
		this.units = units;
	}
	
	
	
}
