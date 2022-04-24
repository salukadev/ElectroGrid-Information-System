package com.electrogrid.consumption.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.electrogrid.consumption.utils.DBConnectionSingleton;

public class DomesticRates {
	
	private int rateId;
	private int year;
	private int month;
	private float c0_30;
	private float c31_60;
	private float c61_90;
	private float c91_120;
	private float c121;
	
	static Connection con = DBConnectionSingleton.getConnection();
	
	public DomesticRates(int rateId, int year, int month, float c0_30, float c31_60, float c61_90, float c91_120,
			float c121) {
		super();
		this.rateId = rateId;
		this.year = year;
		this.month = month;
		this.c0_30 = c0_30;
		this.c31_60 = c31_60;
		this.c61_90 = c61_90;
		this.c91_120 = c91_120;
		this.c121 = c121;
	}

	public DomesticRates() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public int getRateId() {
		return rateId;
	}

	public void setRateId(int rateId) {
		this.rateId = rateId;
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

	public float getC0_30() {
		return c0_30;
	}

	public void setC0_30(float c0_30) {
		this.c0_30 = c0_30;
	}

	public float getC31_60() {
		return c31_60;
	}

	public void setC31_60(float c31_60) {
		this.c31_60 = c31_60;
	}

	public float getC61_90() {
		return c61_90;
	}

	public void setC61_90(float c61_90) {
		this.c61_90 = c61_90;
	}

	public float getC91_120() {
		return c91_120;
	}

	public void setC91_120(float c91_120) {
		this.c91_120 = c91_120;
	}

	public float getC121() {
		return c121;
	}

	public void setC121(float c121) {
		this.c121 = c121;
	}

	public String currentRate()
	{
		String output = "connection failed";
		
		try
		 {
			 if (con == null)
			 {return output; }
		 
			 // create a statement and execute query
			 String query = " SELECT * FROM domesticrates ORDER BY rateId DESC LIMIT 1;";
			 Statement st = con.createStatement(); 
			 ResultSet rs = st.executeQuery(query);
			 
			 boolean available = false;
			 while (rs.next())
		      {
				 	available = true;
					this.rateId = rs.getInt("rateId");
					this.year = rs.getInt("year");
					this.month = rs.getInt("month");
					this.c0_30 = rs.getFloat("c0_30");
					this.c31_60 = rs.getFloat("c31_60");
					this.c61_90 = rs.getFloat("c61_90");
					this.c91_120 = rs.getFloat("c91_120");
					this.c121 = rs.getFloat("c121");
		        
		      }
			 if(!available) {
				 return null;
			 }
			 output = "Successful";
		 }
		 catch (Exception e)
		 {
			 System.err.println(e.getMessage());
			 output = "No data found";
		 } 	
		return output; 
	}
	
	public String insertRate() {
		
		String output = "";
		
		try {
			//checking db connection
			if (con == null)
			 {
				return "DB error!"; 
			}
			
			//sql query to insert data
			String query = " insert into domesticrates values (0,?, ?, ?, ?, ?, ?,?)";
			PreparedStatement preparedSt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			
			//bind values
			preparedSt.setInt(1, this.year);
			preparedSt.setInt(2, this.month);
			preparedSt.setFloat(3, this.c0_30);
			preparedSt.setFloat(4, this.c31_60);
			preparedSt.setFloat(5, this.c61_90);
			preparedSt.setFloat(6, this.c91_120);
			preparedSt.setFloat(7, this.c121);
			
			preparedSt.execute();
			
			output = "New rates inserted successfully";
			
		}catch(Exception e) {
			//output when error occured
			output = "Insertion failed";
		}
				
		return output;
	}
	
	public String Delete(int rateId) {
		String output = "";
		
		try {
			//checking db connection
			if (con == null)
			 {
				return "DB error!"; 
			}
			
			//sql query to insert data
			String query = " DELETE FROM domesticRates WHERE rateId=?";
			PreparedStatement preparedSt = con.prepareStatement(query);
			
			//bind values
			preparedSt.setInt(1, rateId);
			
			preparedSt.executeUpdate();
			
			output = "Deleted rate successfully";
			
		}catch(Exception e) {
			//output when error occured
			output = "Delete failed";
		}
		
		return output;
	}
}

	
