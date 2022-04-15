package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {

	public DBconnection() {
		// TODO Auto-generated constructor stub
	}
	
	public Connection connect()
	{
		 Connection con = null;
	
		 try
		 {
			 Class.forName("com.mysql.jdbc.Driver");
			 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_testing_db","root", "");
			 //For testing
			 System.out.print("Successfully connected");
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	
		 return con;
	}


}
