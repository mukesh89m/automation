package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;


import com.snapwiz.selenium.tests.staf.orion.ReadTestData;


public class TLOExpected {
	
	public List<String> tloExpected(List<Integer> tloquestionattemptedcount,List<String> tlonames,String tcIndex)
	{
		 int userid=0,classid=0;
		 String username = ReadTestData.readDataByTagName("", "user_id", tcIndex);
		 try
		 {
		 ResultSet userids = DBConnect.st.executeQuery("SELECT id as userid from t_user where username like '"+username+"';");
		 while(userids.next())
		 	{
			 userid = userids.getInt("userid");
		 	}
		 
		 ResultSet classsections = DBConnect.st.executeQuery("SELECT id as classid FROM t_class_section  where name like 'studtitle';");
		 while(classsections.next())
		 	{
			 classid = classsections.getInt("classid");
		 	}
		 
		 }
		 catch(Exception e)
		 {
			 Assert.fail("Exception while executing query to fetch the user id from MySQL DB",e);
		 }
		
		List<Double> profs = DBConnect.getProficienciesFromMongo(userid,classid);
		System.out.println(profs);
		BigDecimal rank1 = new BigDecimal(""+ tloquestionattemptedcount.get(0)).multiply(new BigDecimal("" + profs.get(0)));
		BigDecimal rank2 = new BigDecimal(""+ tloquestionattemptedcount.get(1)).multiply(new BigDecimal("" + profs.get(1)));
		BigDecimal rank3 = new BigDecimal(""+ tloquestionattemptedcount.get(2)).multiply(new BigDecimal("" + profs.get(2)));
		BigDecimal rank4 = new BigDecimal(""+ tloquestionattemptedcount.get(3)).multiply(new BigDecimal("" + profs.get(3)));
		BigDecimal rank5 = new BigDecimal(""+ tloquestionattemptedcount.get(4)).multiply(new BigDecimal("" + profs.get(4)));
		BigDecimal rank6 = new BigDecimal(""+ tloquestionattemptedcount.get(5)).multiply(new BigDecimal("" + profs.get(5)));
		Double rank7 = 1000.0;//new BigDecimal(""+ tloquestionattemptedcount.get(6)).multiply(new BigDecimal("" + profs.get(6)));
		
		double [] ranks = {rank1.doubleValue(),rank2.doubleValue(),rank3.doubleValue(),rank4.doubleValue(),rank5.doubleValue(),rank6.doubleValue(),rank7.doubleValue()};
		List<Integer> matches = new ArrayList<Integer>();
		double minvalue = min(rank1.doubleValue(),rank2.doubleValue(),rank3.doubleValue(),rank4.doubleValue(),rank5.doubleValue(),rank6.doubleValue(),rank7.doubleValue());
		int index = 1;
		for(double value : ranks)
		{
			if(value == minvalue)
			{
				matches.add(index);
			}
			index++;
		}
		
		List<String> expectedtlos = new ArrayList<String>();
		for(int va : matches)
		{
			if(va == 1)
				expectedtlos.add(tlonames.get(0));
			if(va == 2)
				expectedtlos.add(tlonames.get(1));
			if(va == 3)
				expectedtlos.add(tlonames.get(2));
			if(va == 4)
				expectedtlos.add(tlonames.get(3));
			if(va == 5)
				expectedtlos.add(tlonames.get(4));
			if(va == 6)
				expectedtlos.add(tlonames.get(5));
			if(va == 7)
				expectedtlos.add(tlonames.get(6));
		}
		return expectedtlos;
		
	}

	
	public static double min(double ... numbers) {
	    double min = numbers[0];
	    for (int i=1 ; i<numbers.length ; i++) {
	        min = (min <= numbers[i]) ? min : numbers[i];
	    }
	    return min;
	}
	
	/*public static void main(String... srtgs){
		 BigDecimal d = new BigDecimal("0.0+" +
		 		"" +
		 		"5");
		 BigDecimal d2 = new BigDecimal("6");
		 BigDecimal res = d.multiply(d2);
		 
		 System.out.println(res.doubleValue());
	}*/
}
