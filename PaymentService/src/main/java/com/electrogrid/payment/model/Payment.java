package com.electrogrid.payment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Date;

import com.electrogrid.payment.utils.DBConnectionSingleton;

public class Payment {
	
	static Connection con = DBConnectionSingleton.getConnection();
	
	
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
}
