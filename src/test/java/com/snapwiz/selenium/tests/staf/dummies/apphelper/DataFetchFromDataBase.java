package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import java.sql.*;

import com.snapwiz.selenium.tests.staf.dummies.apphelper.DBConnect;


public class DataFetchFromDataBase
{
	public  int userverification(int index,String sqlquery)
	{
		int userid=0;
		try
		{
			String url1 ="jdbc:mysql://localhost:3306/mongrel";           
			String dbClass = "com.mysql.jdbc.Driver";
			Class.forName(dbClass).newInstance();
			Connection con = DriverManager.getConnection(url1, "dbuser", "dbpassword");
			Statement st = (Statement) con.createStatement();			
			ResultSet rs=st.executeQuery(sqlquery);
			while(rs.next())
			{
				userid=rs.getInt(index);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return userid;
	}
	
	public String databaseverificationstring(int index,String sqlquery)
	{
		String userid=null;
		try
		{
			String url1 ="jdbc:mysql://localhost:3306/mongrel";           
			String dbClass = "com.mysql.jdbc.Driver";
			Class.forName(dbClass).newInstance();
			Connection con = DriverManager.getConnection(url1, "dbuser", "dbpassword");
			Statement st = (Statement) con.createStatement();			
			ResultSet rs=st.executeQuery(sqlquery);
			while(rs.next())
			{
				userid=rs.getString(index);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return userid;
	}
}
