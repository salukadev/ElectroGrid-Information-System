package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.DBconnection;



public class User {

	public User() {
		// TODO Auto-generated constructor stub
	}
	//insert method
	 public String insertUser(String name) {
		 String output = ""; 
		 try
		 { 
			 DBconnection db = new DBconnection();
			 
			 Connection con =  db.connect();
			 if (con == null) {
				 System.out.print("Error in the connection");
			 } 
			 // create a prepared statement
			 String query = " insert into users(`name`)" + " values (?)"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setString(1, name); 
			 
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Inserted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while inserting the item."; 
			 System.err.println(e.getMessage()); 
		 } 
		 	return output; 
		
	}
	 //update method
	 public String updateUser(String id, String name) {
		 String output = ""; 
		 try
		 { 
			 DBconnection db = new DBconnection();
			 
			 Connection con =  db.connect();
			 if (con == null) 
			 {return "Error while connecting to the database for updating."; } 
			 // create a prepared statement
			 String query = "UPDATE users SET name=? WHERE id=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setString(1, name); 
			 preparedStmt.setString(2, id); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while updating the product."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output;

	}
	//read method
	 public String readAllUsers() {
		 DBconnection db = new DBconnection();
		 
		 Connection con =  db.connect();
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; }
		 
		 String output = "<table border='1'><tr><th>ID</th><th>Name</th>";
		 
		 
		try {
			String query = "select * from users"; 
			Statement stmt = con.createStatement();
		
			ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 
			while (rs.next()) 
			{ 
				String id = Integer.toString(rs.getInt("id")); 
				String name = rs.getString("name"); 
				
				// Add into the html table 
				output += "<tr><td>" + id + "</td>";
				output += "<td>" + name + "</td></tr>"; 
				
				 
				// buttons
//				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+ "<td><form method='post' action='Product.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
//				+ "<input name='pId' type='hidden' value='" +pId 
//				+ "'>" + "</form></td></tr>"; 
			} 
			output += "</table>";
			con.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print(e);
			e.printStackTrace();
			
		} 
		 
		 return output;
	 }
	 
	 //delete user
	 public String deleteUser(String id) 
	 { 
		 String output = ""; 
		 try
		 { 
			 DBconnection db = new DBconnection();
			 
			 Connection con =  db.connect(); 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for deleting."; 
			 } 
			 // create a prepared statement
			 String query = "delete from users where id=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(id)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while deleting the product."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 }

}