package com.electrogrid.consumption.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.electrogrid.consumption.model.Consumption;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.electrogrid.consumption.utils.DBConnectionSingleton;

public class Consumption {
	private int consumptionId;
	private int accNo;
	private int year;
	private int month;
	private int units;
	private float calculatedBal;
	
	
	static Connection con = DBConnectionSingleton.getConnection();
	
	public Consumption(int consumptionId, int accNo, int year, int month, int units, float calculatedBal) {
		super();
		this.consumptionId = consumptionId;
		this.accNo = accNo;
		this.year = year;
		this.month = month;
		this.units = units;
		this.calculatedBal = calculatedBal;
	}

	public Consumption() {}

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
	
	public float getCalculatedBal() {
		return calculatedBal;
	}
	
	public void setCalculatedBal(float calculatedBal) {
		this.calculatedBal = calculatedBal;
	}
	
	
	
	public ArrayList<Consumption>  consumptionHistoryAll() {
		ArrayList<Consumption> consumptionHistory = new ArrayList<Consumption>();
		try
		 {
			 if (con == null)
			 {return consumptionHistory; }
		 
			 // create a statement and execute query
			 String query = " SELECT * FROM consumption;";
			 Statement st = con.createStatement(); 
			 ResultSet rs = st.executeQuery(query);
			 
			 while (rs.next())
		      {
		        this.consumptionId = rs.getInt("consumptionId");
		        this.accNo  = rs.getInt("accNo");
		        this.year = rs.getInt("year");
		        this.month = rs.getInt("month");
		        this.units = rs.getInt("units");
		        this.calculatedBal = rs.getFloat("calculatedBal");
		       
		        
		        //create Consumption object from db entries
		        Consumption trans = new Consumption(consumptionId, accNo, year, month, units, calculatedBal);
		        consumptionHistory.add(trans);
		        
		      }
		 }
		 catch (Exception e)
		 {
			 System.err.println(e.getMessage());
		 }
		return consumptionHistory; 
	}
	
		
	public String consumptionHistoryAllbyAccno(int accno) {
		String output = "";
		try
		 {
			 if (con == null)
			 {return "Processing Error!"; }
		 
			 // create a statement and execute query
			 String query = " SELECT * FROM consumption WHERE accNo = ?;";
			 PreparedStatement prepSt = con.prepareStatement(query);
			 prepSt.setInt(1,accno);
			 ResultSet rs = prepSt.executeQuery();
			 
			 boolean read = false;
			 
			 while (rs.next())
		      {
				read=true;
				this.consumptionId = rs.getInt("consumptionId");
		        this.accNo  = rs.getInt("accNo");
		        this.year = rs.getInt("year");
		        this.month = rs.getInt("month");
		        this.units = rs.getInt("units");
		        this.calculatedBal = rs.getFloat("calculatedBal"); 
		      }
			 
			 if(!read) {
				 return null;
			 }
			 output = "Executed successfully";
		 }
		 catch (Exception e)
		 {
			 System.err.println(e.getMessage());
			 output = "Processing Error!";
		 } 	
		return output;
	}
}
