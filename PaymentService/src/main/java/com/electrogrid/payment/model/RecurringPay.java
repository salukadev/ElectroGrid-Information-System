package com.electrogrid.payment.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.electrogrid.payment.utils.DBConnectionSingleton;

public class RecurringPay {
	private Integer id;
	private Integer payId;
	private Float amount;
	private Integer user;
	private Integer bill_account;
	private java.sql.Date until;
	
	//get db connection from DBConnection singleton
	static Connection con = DBConnectionSingleton.getConnection();
	
	public RecurringPay() {}

	public RecurringPay(Integer id, Integer payId, Float amount, Integer user, Integer bill_account, Date until) {
		super();
		this.id = id;
		this.payId = payId;
		this.amount = amount;
		this.user = user;
		this.bill_account = bill_account;
		this.until = until;
	}
	
	//save RecurringPay object to the db
	public String save()
	 {
		String output = "";
	 try
	 {
		 if (con == null)
		 {return "Error while connecting to the database."; }
	 
		 // create a prepared statement
		 String query = " insert into recurring_pay values (0,?, ?, ?, ?, ?)";
		 PreparedStatement preparedSt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		 
		 //Prepare sql timestamp
//		 final java.util.Date today = new java.util.Date();
//	   	 final java.sql.Timestamp todaySQL = new java.sql.Timestamp(today.getTime());
//		 this.dTime = todaySQL;
//		 this.status = "Processing";
	   	 
		 // binding values
		 preparedSt.setInt(1, payId); //payment info ref id
		 preparedSt.setFloat(2, this.amount); //amount
		 preparedSt.setInt(3, this.user); //user_id
		 preparedSt.setInt(4, this.bill_account); //billing account
		 preparedSt.setDate(5, this.until); //rec stop date
	 
		 // execute the statement
		 preparedSt.executeUpdate();
		 
		 //get autoincremented id
		 ResultSet rs = preparedSt.getGeneratedKeys();
         if(rs.next())
         {
             this.id = rs.getInt(1);
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
	
	//Get rec pay using the acc number
	public RecurringPay getRecPayByAcc(int id) {
		String output = "";
		try
		 {
			 if (con == null)
			 {
				 System.out.println("DB Error!");
				 return null;
			 }
		 
			 // create a statement and execute query
			 String query = "SELECT * FROM recurring_pay WHERE bill_account=?";
			 PreparedStatement pSt = con.prepareStatement(query);
			 pSt.setInt(1,id);
			 ResultSet rs = pSt.executeQuery();
			 
			 boolean read = false;
			 
			 while (rs.next())
		      {
				read=true;
		        
		        this.id = rs.getInt("id");
				this.payId = rs.getInt("payId");
				this.amount = rs.getFloat("amount");
				this.user = rs.getInt("user");
				this.bill_account = rs.getInt("bill_account");
				this.until = rs.getDate("until");
		      }
			 
			 if(!read) {
				 return null;
			 }
			 output = "Executed successfully";
			 System.out.println(output);
		 }
		 catch (Exception e)
		 {
			 System.err.println(e.getMessage());
			 return null;
		 } 	
		return this;
	}
	
	//Cancel a recurring payment using the id
	public String cancelById(int id) {
		String output = "";
		try
		 {
			 if (con == null)
			 {return "Processing Error!"; }
		 
			 // create a statement and execute query
			 String query = " delete from recurring_pay where id=?";
			 PreparedStatement pSt = con.prepareStatement(query);
			 pSt.setInt(1,id);
			 int state = pSt.executeUpdate();
			 //System.out.println(state);
			 if(state==0) {
				 output = "Entry id "+ id +" already cancelled !";
			 }else {
				 output = "Entry id "+ id +" cancelled successfully !";
			 }
			 
			
		 }
		 catch (Exception e)
		 {
			 System.err.println(e.getMessage());
			 output = "Processing Error!";
		 } 	
		return output;
	}
	
	//update rec pay(only amount & until updates are permited)
	public RecurringPay updateRecPay(Integer id, Float amount, java.sql.Date until)
	{
		try
		 {
			 if (con == null)
			 {return null; }
			 
			 PreparedStatement pSt;
			 
			 // create a statement and execute query
			 if(amount!=null && until!=null) {
				 String query = "UPDATE recurring_pay set amount = ?, until=? WHERE id = ?";
				 pSt = con.prepareStatement(query);
				 pSt.setFloat(1,amount);
				 pSt.setDate(2,until);
				 pSt.setInt(3,id);
			 }else if(amount!=null) {
				 String query = "UPDATE recurring_pay set amount = ? WHERE id = ?";
				 pSt = con.prepareStatement(query);
				 pSt.setFloat(1,amount);
				 pSt.setInt(2,id);
			 }else {
				 String query = "UPDATE recurring_pay set until = ? WHERE id = ?";
				 pSt = con.prepareStatement(query);
				 pSt.setDate(1,until);
				 pSt.setInt(2,id);
			 }
			 
			 pSt.executeUpdate();
			 
			 //get affected row
			 String squery = "SELECT * FROM recurring_pay WHERE id = ?";
			 PreparedStatement spSt = con.prepareStatement(squery);
			 spSt.setInt(1,id);
			 
			 ResultSet rs = spSt.executeQuery();
			 boolean read = false;
			 
				 while (rs.next())
			      {
					read=true;
					this.id = rs.getInt("id");
					this.payId = rs.getInt("payId");
					this.amount = rs.getFloat("amount");
					this.user = rs.getInt("user");
					this.bill_account = rs.getInt("bill_account");
					this.until = rs.getDate("until");
			      }
				 
				 //if there is an affected row return the object
				 if(!read) {
					 return null;
				 }else {
					 return this;
				 }
			 
		 }
		 catch (Exception e)
		 {
			 System.err.println(e.getMessage());
		 } 
		return null;
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPayId() {
		return payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Integer getBill_account() {
		return bill_account;
	}

	public void setBill_account(Integer bill_account) {
		this.bill_account = bill_account;
	}

	public java.sql.Date getUntil() {
		return until;
	}

	public void setUntil(java.sql.Date until) {
		this.until = until;
	}
	
	
	
	
	
	

}
