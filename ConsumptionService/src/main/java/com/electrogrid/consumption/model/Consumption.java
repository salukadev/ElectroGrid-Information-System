package com.electrogrid.consumption.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.electrogrid.consumption.utils.DBConnectionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
	
	
	public String consumptionHistoryAll() {
		if (con == null) 
		 {return "Error while connecting to the database for updating."; }
		 
		String output = "<table border='1'><tr><th>ConsumptionID</th><th>AccountNo</th>";
		try {
			//output += "inside try";
			String query = "select * from consumption"; 
			Statement stmt = con.createStatement();
			
			ResultSet res = stmt.executeQuery(query); 
			
			while (res.next()) {
				
				String consumptionID = Integer.toString(res.getInt("consumptionId")); 
				String accNO = Integer.toString(res.getInt("accNo"));
				
				output += "<tr><td>" + consumptionID + "</td>";
				output += "<td>" + accNO + "</td></tr>";
				
			}
			output += "</table>";
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print(e);
			e.printStackTrace();
			
		} 
		return output;
	}
}
