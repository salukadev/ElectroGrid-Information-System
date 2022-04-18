package com.electrogrid.payment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.electrogrid.payment.utils.DBConnectionSingleton;


public class Payment {
	
	private Integer transId;
	private java.sql.Timestamp dTime;
	private Integer bill;
	private Integer user;
	private String pay_type;
	private Float amount;
	private String status;
	
	static Connection con = DBConnectionSingleton.getConnection();
	
	//insert item method
	public String insertItem(int bill, int user, String type, float amount)
	 {
		String output = "";
	 try
	 {
		 if (con == null)
		 {return "Error while connecting to the database."; }
	 
		 // create a prepared statement
		 //String query = " insert into items(`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)"+ " values (?, ?, ?, ?, ?)";
		 String query = " insert into payments values (0,?, ?, ?, ?, ?, ?)";
		 PreparedStatement preparedSt = con.prepareStatement(query);
		 
		 //Prepare sql timestamp
		 final java.util.Date today = new java.util.Date();
	   	 final java.sql.Timestamp todaySQL = new java.sql.Timestamp(today.getTime());
		 
		 // binding values
		 preparedSt.setTimestamp(1, todaySQL); //dtime
		 preparedSt.setInt(2, bill); //bill_id
		 preparedSt.setInt(3, user); //user_id
		 //preparedSt.setDouble(5, Double.parseDouble(price));
		 preparedSt.setString(4, type); //pay_type
		 preparedSt.setFloat(5, amount); //amount
		 preparedSt.setString(6, "Processing"); //status
	 
		 // execute the statement
		 preparedSt.execute();
		 //con.close();
		 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
		 output = "Error while inserting the item.";
		 System.err.println(e.getMessage());
	 }
	 	return output;
	 }
	
	//save object to the db
	public String save()
	 {
		String output = "";
	 try
	 {
		 if (con == null)
		 {return "Error while connecting to the database."; }
	 
		 // create a prepared statement
		 //String query = " insert into items(`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)"+ " values (?, ?, ?, ?, ?)";
		 String query = " insert into payments values (0,?, ?, ?, ?, ?, ?)";
		 PreparedStatement preparedSt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		 
		 //Prepare sql timestamp
		 final java.util.Date today = new java.util.Date();
	   	 final java.sql.Timestamp todaySQL = new java.sql.Timestamp(today.getTime());
		 this.dTime = todaySQL;
		 this.status = "Processing";
	   	 
		 // binding values
		 preparedSt.setTimestamp(1, todaySQL); //dtime
		 preparedSt.setInt(2, this.bill); //bill_id
		 preparedSt.setInt(3, this.user); //user_id
		 //preparedSt.setDouble(5, Double.parseDouble(price));
		 preparedSt.setString(4, this.pay_type); //pay_type
		 preparedSt.setFloat(5, this.amount); //amount
		 preparedSt.setString(6, this.status); //status
	 
		 // execute the statement
		 preparedSt.executeUpdate();
		 
		 //get autoincremented id
		 ResultSet rs = preparedSt.getGeneratedKeys();
         if(rs.next())
         {
             this.transId = rs.getInt(1);
         }
         
		 //con.close();
		 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
		 output = "Error while inserting the item.";
		 System.err.println(e.getMessage());
	 }
	 	return output;
	 }

	//get last transaction
	public void getLastTransaction() {
		try
		 {
			 if (con == null)
			 {return; }
		 
			 // create a statement and execute query
			 String query = " SELECT * FROM payments ORDER BY id DESC LIMIT 1;";
			 Statement st = con.createStatement(); 
			 ResultSet rs = st.executeQuery(query);
			 
			 while (rs.next())
		      {
		        this.transId = rs.getInt("id");
		        this.dTime  = rs.getTimestamp("dtime");
		        this.bill = rs.getInt("bill_id");
		        this.user = rs.getInt("user");
		        this.pay_type = rs.getString("pay_type");
		        this.amount = rs.getFloat("amount");
		        this.status = rs.getString("status");
		        
		        // print the results
		        //System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName, lastName, dateCreated, isAdmin, numPoints);
		      }
			
		 }
		 catch (Exception e)
		 {
			 System.err.println(e.getMessage());
		 } 	
	}
	
	
	public Integer getBill() {
		return bill;
	}


	public void setBill(Integer bill) {
		this.bill = bill;
	}


	public Integer getUser() {
		return user;
	}


	public void setUser(Integer user) {
		this.user = user;
	}


	public String getPay_type() {
		return pay_type;
	}


	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}


	public Float getAmount() {
		return amount;
	}


	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getdTime() {
		return dTime.toLocaleString();
	}
	
	public String getStatus() {
		return status;
	}

	public Integer getTransId() {
		return transId;
	}
	
	
	
}
