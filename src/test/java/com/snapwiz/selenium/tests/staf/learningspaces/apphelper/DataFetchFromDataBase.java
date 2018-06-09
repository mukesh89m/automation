package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.sql.*;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DBConnect;


public class DataFetchFromDataBase
{
	public  int userverification(int index,String sqlquery)
	{
		int userid=0;
		try
		{
            DBConnect.Connect();
			ResultSet rs=DBConnect.st.executeQuery(sqlquery);
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
            DBConnect.Connect();
			ResultSet rs=DBConnect.st.executeQuery(sqlquery);
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
