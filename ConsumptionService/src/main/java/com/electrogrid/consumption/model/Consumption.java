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
	
	public float calculateDomesticConsumption(int units) {
		
		float output = 0;
		
		float c0_30 = (float) 5.21;
		float c31_60 = (float) 14.52;
		float c61_90 = (float) 21.33;
		float c91_120 = (float) 34.12;
		float c121 = (float) 42.22;
		
		if(units<30) {
			output = (float) (units * c0_30);
		}else {
			output = (float) (30 * c0_30);
			
			if (units<60) {
				output += (float) ((units-30) * c31_60);
			}else {
				output += (float) (30 * c31_60);
				
				if(units < 90) {
					output += (float) ((units-60) * c61_90);
				}else {
					output += (float) (30 * c61_90);
					
					if(units < 120) {
						output += (float) ((units-90) * c91_120);
					}else {
						
						output += (float) (30 * c91_120);
						output += (float) ((units-120) * c121);
					}
				}
			}
		}
		return output;
	}
	
	public String insertConsumption(int consumptionId, int accNo, int year, int month, int units) {
		String output = "";
		try {
			//checking db connection
			if (con == null)
			 {
				return "DB error!"; 
			}
			
			//sql query to insert data
			String query = " insert into consumption values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedSt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			
			float calbal = calculateDomesticConsumption(units);
			//bind values
			preparedSt.setInt(1, consumptionId);
			preparedSt.setInt(2, accNo);
			preparedSt.setFloat(3, year);
			preparedSt.setFloat(4, month);
			preparedSt.setFloat(5, units);
			preparedSt.setFloat(6, calbal);
			
			//execute sql query to store values in db
			preparedSt.execute();
			output = "New rates inserted successfully";
			
		}catch(Exception e) {
			//output when error occured
			System.out.println(consumptionId);
			output = "Insertion failed";
		}
		return output;
	}
	
	public String updateUnits(int accNo,int year, int month, int units) {
		String output = "";
		
		try {
			//checking db connection
			if (con == null)
			 {
				return "DB error!"; 
			 }
			
			
			String query = "UPDATE consumption SET units = ?, calculatedBal = ? WHERE accNo = ? AND year = ? AND month = ?";
			PreparedStatement preparedSt = con.prepareStatement(query);
			
			// calculate balance for new no units entry
			float bal = calculateDomesticConsumption(units);
			
			preparedSt.setInt(1,units);
			preparedSt.setFloat(2,bal);
			preparedSt.setInt(3,accNo);
			preparedSt.setInt(4,year);
			preparedSt.setInt(5,month);
			
			preparedSt.executeUpdate();
			
		}catch(Exception e) {
			//output when error occured
			output = "Update failed";
		}
		
		return output;
	}
}
