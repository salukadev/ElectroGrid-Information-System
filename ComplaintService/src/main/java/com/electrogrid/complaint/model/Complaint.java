package com.electrogrid.complaint.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import com.electrogrid.complaint.utils.DBConnectionSingleton;


public class Complaint {

	private Integer compId;
	private Integer user;
	private java.sql.Timestamp dTime;
	private String title;
	private String description;

	public Complaint() {}

	public Complaint(Integer compId, Integer user, Timestamp dTime, String title, String description) {
		super();
		this.compId = compId;
		this.user = user;
		this.dTime = dTime;
		this.title = title;
		this.description = description;
	}

	static Connection con = DBConnectionSingleton.getConnection();
	
	//insert method
	public String insertItem(int user, String title, String des) {
		this.user = user;
		this.title = title;
		this.description = des;
		
		String output = this.save();
		return output;
		
	}
	
	public String save()
	 {
		String output = "";
	 try
	 {
		 if (con == null)
		 {return "Error while connecting to the database."; }
	 
		 // create a prepared statement
		 String query = " insert into complaints values (0,?, ?, ?, ?)";
		 PreparedStatement preparedSt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		 
		 //Prepare sql timestamp
		 final java.util.Date today = new java.util.Date();
	   	 final java.sql.Timestamp todaySQL = new java.sql.Timestamp(today.getTime());
		 this.dTime = todaySQL;
	   	 
		 // binding values
		 preparedSt.setInt(1, this.user);
		 preparedSt.setTimestamp(2, todaySQL); //dtime
		 preparedSt.setString(3, this.title); 
		 preparedSt.setString(4, this.description);
	 
		 // execute the statement
		 preparedSt.executeUpdate();
		 
		 //get auto incremented id
		 ResultSet rs = preparedSt.getGeneratedKeys();
        if(rs.next())
        {
            this.compId = rs.getInt(1);
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
	
	//get recent complaints
		public ArrayList<Complaint> fetchRecentComplaints() {
			ArrayList<Complaint> compList = new ArrayList<Complaint>();
			try
			 {
				 if (con == null)
				 {return compList; }
			 
				 // create a statement and execute query
				 String query = "SELECT * FROM complaints;";
				 PreparedStatement spSt = con.prepareStatement(query);
				 //spSt.setInt(1,uid);
				 
				 ResultSet rs = spSt.executeQuery();
				 
				 while (rs.next())
			      {
			        Integer compId = rs.getInt("compId");
			        Integer user = rs.getInt("user");
			        java.sql.Timestamp dTime  = rs.getTimestamp("date");
			        String title = rs.getString("title");
			        String description = rs.getString("description");
			        
			        //create a list of objects from db rows
			        Complaint comp = new Complaint(compId, user,dTime,title, description);
			        compList.add(comp);
			        
			      }
			 }
			 catch (Exception e)
			 {
				 System.err.println(e.getMessage());
			 }
			return compList; 	
		}
		
		//Get specific complaint using the id
		public String fetchById(int id) {
			String output = "";
			try
			 {
				 if (con == null)
				 {return "Processing Error!"; }
			 
				 // create a statement and execute query
				 String query = "SELECT * FROM complaints WHERE compId=?";
				 PreparedStatement pSt = con.prepareStatement(query);
				 pSt.setInt(1,id);
				 ResultSet rs = pSt.executeQuery();
				 
				 boolean read = false;
				 
				 while (rs.next())
			      {
					read=true;
					this.compId = rs.getInt("compId");
					this.user = rs.getInt("user");
					this.dTime  = rs.getTimestamp("date");
					this.title = rs.getString("title");
					this.description = rs.getString("description");
			         
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
		
		//Cancel a complaint using the id
		public String cancelById(int id) {
			String output = "";
			try
			 {
				 if (con == null)
				 {return "Processing Error!"; }
			 
				 // create a statement and execute query
				 String query = " delete from complaints where compId=?";
				 PreparedStatement pSt = con.prepareStatement(query);
				 pSt.setInt(1,id);
				 int state = pSt.executeUpdate();
				 //System.out.println(state);
				 if(state==0) {
					 output = "Complaint id "+ id +" already cancelled !";
				 }else {
					 output = "Complaint id "+ id +" terminated successfully !";
				 }
				 
				
			 }
			 catch (Exception e)
			 {
				 System.err.println(e.getMessage());
				 output = "Processing Error-2!";
			 } 	
			return output;
		}
		
		//update a complaint
		public Complaint updateItem(int id,String newDesc)
		{
			try
			 {
				 if (con == null)
				 {return null; }
			 
				 // create a statement and execute query
				 String query = "UPDATE complaints set description = ? WHERE compId= ?";
				 PreparedStatement pSt = con.prepareStatement(query);
				 pSt.setString(1,newDesc);
				 pSt.setInt(2,id);
				 pSt.executeUpdate();
				 
				 //get affected row
				 String squery = "SELECT * FROM complaints WHERE compId= ?";
				 PreparedStatement spSt = con.prepareStatement(squery);
				 spSt.setInt(1, id);
				 
				 ResultSet rs = spSt.executeQuery();
				 boolean read = false;
				 
					 while (rs.next())
				      {
						read=true;
				        this.compId = rs.getInt("compId");
				        this.dTime  = rs.getTimestamp("date");
				        this.user = rs.getInt("user");
				        this.title = rs.getString("title");
				        this.description = rs.getString("description"); 
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

		//getters and setters
		public Integer getCompId() {
			return compId;
		}

		public void setCompId(Integer compId) {
			this.compId = compId;
		}

		public Integer getUser() {
			return user;
		}

		public void setUser(Integer user) {
			this.user = user;
		}

		public java.sql.Timestamp getdTime() {
			return dTime;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

}
