package com.electrogrid.supplyconnection.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.electrogrid.supplyconnection.utils.DBConnectionSingleton;


public class SupplyConnection {
	
	//class attributes
	private int accNo;
	private String name;
	private String address;
	private String area;
	private String type;
	private String connection_status;
	private java.sql.Timestamp timestamp;
	
	//default constructor
	public SupplyConnection() {
		super();
	}

	//overloaded constructor
	public SupplyConnection(int accNo, String name, String address, String area, String type, String connection_status,
			Timestamp timestamp) {
		super();
		this.accNo = accNo;
		this.name = name;
		this.address = address;
		this.area = area;
		this.type = type;
		this.connection_status = connection_status;
		this.timestamp = timestamp;
	}
	
	
	//generating getters and setters
	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getConnection_status() {
		return connection_status;
	}

	public void setConnection_status(String connection_status) {
		this.connection_status = connection_status;
	}

	public java.sql.Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(java.sql.Timestamp timestamp) {
		this.timestamp = timestamp;
	}



	//connection to the database
	static Connection con = DBConnectionSingleton.getConnection();
	
	//creating a supply connection
	public String createConnection() {
		String output = "";
		
		try {
			if (con == null){
				return "Error while connecting to the database."; 
			}
			
			String query = " insert into supply_connection values (?,?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			
			//Prepare sql timestamp
			final java.util.Date today = new java.util.Date();
		   	final java.sql.Timestamp todaySQL = new java.sql.Timestamp(today.getTime());
		   	
		   	// binding values
		   	preparedStatement.setInt(1, this.accNo); //accNo
		   	preparedStatement.setString(2, this.name); //name
		   	preparedStatement.setString(3, this.address); //address
		   	preparedStatement.setString(4, this.area); //area
		   	preparedStatement.setString(5, this.type); //type
		   	preparedStatement.setString(6, this.connection_status); //type
		   	preparedStatement.setTimestamp(7, todaySQL); //timestamp
		   	
			 // execute the statement
		   	preparedStatement.execute();
		   	output = "Inserted successfully"; 
			
		}catch(Exception e) {
			output = "Data insertion unsuccessful";
			System.err.println(e.getMessage());
		}
		return output;
	}
		

	//update supply connection
	public String updateConnection(int accNo, String name, String address, 
			String area, String type, String connection_status) {
		String output = ""; 
		try
		{ 
			if (con == null) 
			{
				return "Error while connecting to the database for updating."; 
			} 
			// create a prepared statement
			String query = "UPDATE supply_connection SET name=?, address=?, area=?, type=?,connection_status=?, timestamp=? WHERE accNo=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			
			//Prepare sql timestamp
			final java.util.Date today = new java.util.Date();
		   	final java.sql.Timestamp todaySQL = new java.sql.Timestamp(today.getTime());
			
			
			preparedStmt.setString(1, name); 
			preparedStmt.setString(2, address); 
			preparedStmt.setString(3, area);
			preparedStmt.setString(4, type);
			preparedStmt.setString(5, connection_status);
			preparedStmt.setTimestamp(6, todaySQL);
			preparedStmt.setInt(7, accNo);
			// execute the statement
			preparedStmt.execute(); 
			output = "Connection Updated successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while updating the connection."; 
			System.err.println(e.getMessage()); 
		} 
		return output;
	}

	//delete supply connection
	public String deleteConnection(Integer accNo) {
		String output = "";
		try{
			 if (con == null)
			 {return "Processing Error!"; }
		 
			 // create a statement and execute query
			 String query = "delete from supply_connection where accNo=?";
			 PreparedStatement preparedStatement = con.prepareStatement(query);
			 preparedStatement.setInt(1,accNo);
			 int state = preparedStatement.executeUpdate();
			 //System.out.println(state);
			 if(state==0) {
				 output = "Connection with Account No :"+ accNo +" does not exist!";
			 }else {
				 output = "Connection with the Account No :"+ accNo +" is Deleted successfully !";
			 }
			 
			
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
			output = "Processing Error!";
		} 	
		return output;
	}
			
		
	//view all the connections
	public ArrayList<SupplyConnection> viewAllConnections() {
		ArrayList<SupplyConnection> SupplyConnectionList = new ArrayList<SupplyConnection>();
		try{
			if (con == null){
				 return SupplyConnectionList; 
			}
		 
			 // create a statement and execute query
			String query = "SELECT * FROM supply_connection";
			Statement statement = con.createStatement(); 
			ResultSet rs = statement.executeQuery(query);
			 
			while (rs.next()){
		        SupplyConnectionList.add(new SupplyConnection(
		        		rs.getInt("accNo"),
				        rs.getString("name"),
				        rs.getString("address"),
				        rs.getString("area"),
				        rs.getString("type"),
				        rs.getString("connection_status"),
				        rs.getTimestamp("timestamp") 
		        ));
		    }
		 }
		 catch (Exception e)
		 {
			 System.err.println(e.getMessage());
			 
		 } 	
		return SupplyConnectionList;
	}
		

}
