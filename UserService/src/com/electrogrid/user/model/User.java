package com.electrogrid.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.electrogrid.user.utils.DBConnectionSingleton;

public class User {
	
	private String username;
	private String email;
	private String password;
	private int phone_no;
	private int accNo;
	private String nic;
	
	//connection to the database
	static Connection con = DBConnectionSingleton.getConnection();
	
	//overloaded constructor
	public User(String username, String email, String password, int phone_no, int accNo, String nic) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone_no = phone_no;
		this.accNo = accNo;
		this.nic = nic;
	}
	
	//default constructor
	public User() {
		super();
	}
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(int phone_no) {
		this.phone_no = phone_no;
	}

	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	
	// Registering a new user 
	public String registerUser() {
		String output = "";
		
		try {
			if (con == null){
				return "Error while connecting to the database."; 
			}
			
			String query = "insert into users values (?,?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = con.prepareStatement(query);
		   	
		   	// binding values
			preparedStatement.setInt(1, 0);
		   	preparedStatement.setString(2, this.username); //username
		   	preparedStatement.setString(3, this.email); //email
		   	preparedStatement.setString(4, this.password); //password
		   	preparedStatement.setInt(5, this.phone_no); //area
		   	preparedStatement.setInt(6, this.accNo); //accNo
		   	preparedStatement.setString(7, this.nic); //nic number
		   	
			// execute the statement
		   	preparedStatement.execute();
		   	output = "User Registered successfully"; 
			
		}catch(Exception e) {
			output = "User registration unsuccessful";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	// updating user details
	public String editUser() {
		return "";
	}
	
	// deleting a user
	public String deleteUser() {
		return "";
	}
	
	// user login
	public String userlogin() {
		return "";
	}
	
	// change user Password
	public String changePassword() {
		return "";
	}

}
