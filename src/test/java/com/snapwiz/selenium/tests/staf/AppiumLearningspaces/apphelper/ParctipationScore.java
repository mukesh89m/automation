package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.math.BigDecimal;
import java.util.Map;

import org.testng.Assert;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.ReadTestData;
	/*
	*calculate the ParctipationScore for student from Mongo 
	*Author Sumit
	*/
public class ParctipationScore {
	
	public Double parctipationScore(String dataIndex)
	{
        Double truncatedDouble = 0.0;
		Double value = 0.0;
		try
		{
			String user_id = ReadTestData.readDataByTagName("", "user_id", dataIndex);
			String context_id = ReadTestData.readDataByTagName("", "context_id", dataIndex);
			int csId = new DataFetchFromDataBase().userverification(1, "SELECT id FROM t_class_section where external_id = '"+context_id+"';");
			int uId = new DataFetchFromDataBase().userverification(1, "SELECT * FROM t_user where username = '"+user_id+"';");
			DBCollection dbCollection = com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DBConnect.mongoConnect("StudentCourseSummary");
			DBObject query = new BasicDBObject();
			query.put("csId", csId);
			query.put("uId", uId);
			
			DBObject fields = new BasicDBObject();
			fields.put("ps", 1);
			
			DBCursor dbObject = dbCollection.find(query, fields, 0, 1);
			System.out.println(dbObject);
			while(dbObject.hasNext()){			
				Map<String,Object> map = com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DBConnect.convertJSONStringTOMap(dbObject.next().toString());
				Object obj  = map.get("ps");
				value = ((Double)obj);
				break;
				}

            truncatedDouble=new BigDecimal(value ).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out.println("value: "+truncatedDouble);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper ParctipationScore",e);
		}
		return truncatedDouble;
	}

}
