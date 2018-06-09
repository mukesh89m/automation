package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import java.sql.*;

import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Config;

public  class DBConnect {
	public static Statement st;
	 public static void Connect() {
         String url = Config.dbconnecturl;
         String dbName = Config.dbName;
         String driver = Config.dbdriver;
         String userName = Config.dbuserName;
         String password = Config.dbPassword;
         try {
         Class.forName(driver).newInstance();
         Connection conn = DriverManager.getConnection(url+dbName,userName,password);
          st = conn.createStatement();
               
         } catch (Exception e) {
         e.printStackTrace();
         Assert.fail("Exception while connecting to database",e);
         }
         }



}
