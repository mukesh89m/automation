package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.sql.Connection;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mukesh on 19/11/15.
 */
public class DbConnector {
    static Connection con = null;
    String dbvalue;
    private static Statement stmt;
    public static String DB_URL = "jdbc:mysql://localhost:3306/mongrel";
    public static String DB_USER = "root";
    public static String DB_PASSWORD = "snapwiz";
    @BeforeMethod
    public void setUp() throws Exception{
        try
        {
            String dbClass = "com.mysql.jdbc.Driver";
            Class.forName(dbClass).newInstance();
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = con.createStatement();
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
    }
    public String databaseConnect(String query)
    {
        try
        {
            setUp();
            ResultSet res = stmt.executeQuery(query);
            while(res.next())
            {
                dbvalue =res.getString(1);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return dbvalue;
    }

    public String databaseConnectWithParam(String queryparam)
    {

        try
        {
            setUp();
            ResultSet res = stmt.executeQuery(queryparam);
            while(res.next())
            {
                dbvalue =res.getString(1);

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return dbvalue;
    }
    public List<String> databaseConnectReturnMultipleValue(String queryParam)
    {
        List<String> allOption=new ArrayList<String>();

        try
        {
            setUp();
            // String queryparam = "select count(*) from t_assessment;";
            //System.out.println("query: "+query);
            ResultSet res = stmt.executeQuery(queryParam);
            while(res.next())
            {
                allOption.add(res.getString(1));
                //System.out.println("allOption"+allOption);

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return allOption;
    }

    @AfterMethod
    public void tearDown() throws Exception
    {
        if(con!=null)
        {
            con.close();
        }
    }
}
