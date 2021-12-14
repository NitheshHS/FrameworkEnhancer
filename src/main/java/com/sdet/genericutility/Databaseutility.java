package com.sdet.genericutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class Databaseutility {
	
	private Connection connection;
	public void connectToDB() throws Throwable {
		Driver dbDriver=new Driver();
		DriverManager.registerDriver(dbDriver);
		connection=DriverManager.getConnection("url", "username", "password");
		
	}
	
	public void closeDBconnection() throws Throwable {
		connection.close();
	}
	
	public String executeQuery(String query,String dbColumnName,String expectedData) throws Throwable {
	   Statement stmt = connection.createStatement();
	   ResultSet result = stmt.executeQuery(query);
	   boolean flag=false;
	   String dbData=null;
	   while(result.next()) {
		    dbData=result.getString(dbColumnName);
		   if(dbData.equals(expectedData)) {
			   flag=true;
		   }
	   }
	   if(flag) {
		   return dbData;
	   }
	   else {
		   return "No matching data found in DB: "+expectedData;
	   }
	}

}

