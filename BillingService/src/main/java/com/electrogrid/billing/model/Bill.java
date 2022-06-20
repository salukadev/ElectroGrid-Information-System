package com.electrogrid.billing.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.electrogrid.billing.utils.DBConnectionSingleton;


public class Bill {
	private Integer billId;
	private Integer accNo;
	private Integer totUnits;
	private java.sql.Date period;
	private Float previousBal;
	private Float calculatedBal;
	private Float totPay;
	private Float totalDue;
	
	static Connection con = DBConnectionSingleton.getConnection();
	
	public Bill() {}
	
	public Bill(Integer billId, Integer accNo, Integer consumptionId, Date period, Float previousBal, Float calculatedBal, Float totPay,
			Float totalDue) {
		super();
		this.billId = billId;
		this.accNo = accNo;
		this.totUnits = consumptionId;
		this.period = period;
		this.previousBal = previousBal;
		this.calculatedBal = calculatedBal;
		this.totPay = totPay;
		this.totalDue = totalDue;
	}
	
	//method to create records
	public String insertRecord(int accNo, int units, float amount)
	{
		String output = "";
		try
		 {
			 if (con == null)
			 {return "DB Error!"; }
		 
			 // Check for the previous bills of the account
			 String query = "SELECT * FROM bills WHERE accNo=? ORDER BY billId DESC LIMIT 1";
			 PreparedStatement pSt = con.prepareStatement(query);
			 pSt.setInt(1,accNo);
			 ResultSet rs = pSt.executeQuery();
			 
			 boolean read = false;
			 
			 while (rs.next())
		      {
				read=true;
				this.billId = rs.getInt("billId") ;
		        this.accNo = rs.getInt("accNo") ;
		        this.totUnits = rs.getInt("totUnits");
		        this.period = rs.getDate("period");
		        this.previousBal = rs.getFloat("previousBal");
		        this.calculatedBal = rs.getFloat("calculatedBal");
		        this.totPay = rs.getFloat("totPay");
		        this.totalDue = rs.getFloat("totalDue");
		      }
			 
			//prepare today's date in sql date format
			 final java.util.Date today = new java.util.Date();
			 java.sql.Date sqlToday = new java.sql.Date(today.getTime());
			 
		      //If no prev bills found for the accNo
			 if(!read) {
				 System.out.println("No previous bills for this connection ! Creating the first bill");
				 
				 String queryIn = " insert into bills values (0,?, ?, ?, ?, ?, ?, ?)"; 
				 PreparedStatement preparedSt = con.prepareStatement(queryIn);
				 
				 
				// binding values
				 preparedSt.setInt(1, accNo); //accNo
				 preparedSt.setInt(2, units);  //totUnits
				 preparedSt.setDate(3, sqlToday);  //period
				 preparedSt.setFloat(4, 0f); //previousBal
				 preparedSt.setFloat(5, amount); //calculatedBal
				 preparedSt.setFloat(6, 0f); //totPay
				 preparedSt.setFloat(7, amount); //totalDue
				 
				// execute the statement
				 preparedSt.executeUpdate();
	
			 }else {
				 //when previous month's data is available
				 String queryIn = " insert into bills values (0,?, ?, ?, ?, ?, ?, ?)"; 
				 PreparedStatement preparedSt = con.prepareStatement(queryIn);
				 
				// binding values
				 preparedSt.setInt(1, accNo); //accNo
				 preparedSt.setInt(2, units);  //totUnits
				 preparedSt.setDate(3, sqlToday);  //period
				 preparedSt.setFloat(4, this.totalDue); //set prev month's due to previousBal
				 preparedSt.setFloat(5, amount); //calculatedBal for this month
				 preparedSt.setFloat(6, 0f); //totPay
				 preparedSt.setFloat(7, this.totalDue+amount); //totalDue {prevBal + this month bill}
				 
				// execute the statement
				 preparedSt.executeUpdate();
				 System.out.println("Created the bill for this month");
				 
				 
			 }
			 output = "Bill created";
		 }
		 catch (Exception e)
		 {
			 System.err.println(e.getMessage());
			 output = "Processing Error!";
		 } 	
		return output;
	}
	
	//get past bills using accNo
	public ArrayList<Bill> getHistory(int acNo, int length)
	{
		ArrayList<Bill> billList = new ArrayList<Bill>();
		try
		 {
			 if (con == null)
			 {
				 System.out.println("DB Error!");
				 return null; 
			 }
		 
			 // Check for the previous bills of the account
			 String query = "SELECT * FROM bills WHERE accNo=? ORDER BY period DESC LIMIT ?";
			 PreparedStatement pSt = con.prepareStatement(query);
			 pSt.setInt(1,acNo);
			 pSt.setInt(2,length);
			 ResultSet rs = pSt.executeQuery();
			 
			 boolean read = false;
			 
			 while (rs.next())
		      {
				read=true;
				this.billId = rs.getInt("billId") ;
		        this.accNo = rs.getInt("accNo") ;
		        this.totUnits = rs.getInt("totUnits");
		        this.period = rs.getDate("period");
		        this.previousBal = rs.getFloat("previousBal");
		        this.calculatedBal = rs.getFloat("calculatedBal");
		        this.totPay = rs.getFloat("totPay");
		        this.totalDue = rs.getFloat("totalDue");
		        
		        
		      //create a list of objects from db rows
		        Bill bill = new Bill(billId,accNo,totUnits,period,previousBal,calculatedBal,totPay,totalDue);
		        billList.add(bill);
		      }
			 
			 if(read) {
				 System.out.println("Reading past bills");
				 return billList;
			 }
			 
		 }catch (Exception e)
		 {
			 System.err.println(e.getMessage());
			 System.out.println("Error Reading past bills");
		 }
		System.out.println("No account");
		return null; 	
	}
	
	public Bill getBillById(int bid)
	{
		try
		 {
			 if (con == null)
			 {
				 System.out.println("DB Error!");
				 return null; 
			 }
		 
			 // Check for the previous bills of the account
			 String query = "SELECT * FROM bills WHERE billId=?";
			 PreparedStatement pSt = con.prepareStatement(query);
			 pSt.setInt(1,bid);
			 ResultSet rs = pSt.executeQuery();
			 
			 boolean read = false;
			 
			 while (rs.next())
		      {
				read=true;
				this.billId = rs.getInt("billId") ;
		        this.accNo = rs.getInt("accNo") ;
		        this.totUnits = rs.getInt("totUnits");
		        this.period = rs.getDate("period");
		        this.previousBal = rs.getFloat("previousBal");
		        this.calculatedBal = rs.getFloat("calculatedBal");
		        this.totPay = rs.getFloat("totPay");
		        this.totalDue = rs.getFloat("totalDue");
		      }
			 
			 if(read) {
				 System.out.println("Reading bill id: "+bid);
				 return this;
			 }
			 
		 }catch (Exception e)
		 {
			 System.err.println(e.getMessage());
		 }
		return null; 	
	}
	
	//method used to update payment info after a transaction
	public void updateBillPayment(int billid, float amount) 
	{
		try
		 {
			 if (con == null)
			 {
				 System.out.println("DB Error!");
				 return; 
			 }
		 
			 // Get last bill
			 String query = "SELECT * FROM bills WHERE billId=? ORDER BY period DESC LIMIT 1";
			 PreparedStatement pSt = con.prepareStatement(query);
			 pSt.setInt(1,billid);
			 
			 ResultSet rs = pSt.executeQuery();
			 
			 boolean read = false;
			 
			 while (rs.next())
		      {
				read=true;
				this.billId = rs.getInt("billId") ;
		        this.accNo = rs.getInt("accNo") ;
		        this.totUnits = rs.getInt("totUnits");
		        this.period = rs.getDate("period");
		        this.previousBal = rs.getFloat("previousBal");
		        this.calculatedBal = rs.getFloat("calculatedBal");
		        this.totPay = rs.getFloat("totPay");
		        this.totalDue = rs.getFloat("totalDue");
		      }
			 
			 if(read) {
				 System.out.println("Bill found. Updating payment info...");
				 
				 try {
					 
					 String queryIn = "UPDATE bills set totPay = ? , totalDue= ? WHERE billId = ?"; 
					 PreparedStatement preparedSt = con.prepareStatement(queryIn);
					 
					// binding values
					 preparedSt.setFloat(1, this.totPay+ amount); //update totPay
					 preparedSt.setFloat(2, this.totalDue-amount); //update totalDue
					 preparedSt.setInt(3, this.billId); //billId
					 
					// execute the statement
					 preparedSt.executeUpdate();
					 System.out.println("Updated payment info for the last month");
					 
				 }catch(Exception e) {
					 System.out.println(e);
				 }
			 }
		 
		 }catch(Exception e) {
			 
		 }
		return;
	}
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
	public Integer getTotUnits() {
		return totUnits;
	}
	public void setTotUnits(Integer totUnits) {
		this.totUnits = totUnits;
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
