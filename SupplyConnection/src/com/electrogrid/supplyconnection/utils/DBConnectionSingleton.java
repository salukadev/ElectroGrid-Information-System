package com.electrogrid.supplyconnection.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnectionSingleton {
	
	private static Connection con = null;
	
	static {
		
		Properties prop = new Properties();
		try (InputStream input = DBConnectionSingleton.class.getResourceAsStream("/config.properties")){
			
			if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            }else {
            	System.out.println("File config.properties read successfully!");
            }
			
			//load a properties file from class path, inside static method
            prop.load(input);
			
            //checking whether the values have be taken correctly
            System.out.println(prop.getProperty("db.url"));
            System.out.println(prop.getProperty("db.user"));
            System.out.println(prop.getProperty("db.password"));
            
		}catch (IOException ex) {
			ex.printStackTrace();
		}
		
		String url = "jdbc:mysql://"+ prop.getProperty("db.url")+":"+prop.getProperty("db.port")+"/"+prop.getProperty("db.name");
        String user = prop.getProperty("db.user");
        String pass = prop.getProperty("db.password");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        }
        catch (Exception e) {
        	System.out.println(prop.getProperty("DB Error !!!"));
            e.printStackTrace();
        }
		
	}
	
	//accessing the connection in public
	public static Connection getConnection(){
		return con;
	}

}
