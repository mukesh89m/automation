package com.snapwiz.selenium.tests.staf.datacreation.apphelper;

import com.mongodb.*;
import com.snapwiz.selenium.tests.staf.datacreation.Config;
import com.snapwiz.selenium.tests.staf.datacreation.Driver;
import com.snapwiz.selenium.tests.staf.datacreation.ReadTestData;
import org.bson.types.ObjectId;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public  class DBConnect extends Driver {
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
	 
	 public static DBCollection mongoConnect(String collectionName)
	 {
		 DBCollection collection = null;
		 try
		 {
		 MongoClient mongoClient = new MongoClient( Config.mongoHost , Integer.parseInt(Config.mongoPort));
			DB db = mongoClient.getDB(Config.dbName);
			collection = db.getCollection(collectionName);
		 }
		 catch(Exception e)
		 {
			 Assert.fail("Exception while connecting to mongo database",e);
		 }
		 return collection;
	 }

	 
	 public static List<Map> getDataFromMongo(DBCollection collection)
	 {
		 List<Map> data = new ArrayList<Map>();
		 try
		 {
		DBObject query = new BasicDBObject();
             query.put("partitionIndex",1);
            /* query.put( "_id" , new ObjectId("5451fe6d8a462891476230f9"));
            query.put("AssessmentType" , 1);
             query.put("ChapterId" , 2445L); //206788
             query.put("QuestionId" , 206788L);*/

        DBObject sort = new BasicDBObject();
             sort.put("user_external_id",1);
             sort.put("CourseId",1);
             sort.put("AssessmentType",1);
             sort.put("uaId",1);
             //sort.put("dl",1);
             sort.put("dlIndex",1);

		DBCursor dbObject = collection.find(query).sort(sort);

		while(dbObject.hasNext()){
			Map<String,Object> map = convertJSONStringTOMap(dbObject.next().toString());
            data.add(map);
		}
		 }
		 catch(Exception e)
		 {
             System.out.println("Error while reading data from mongo");
         }
		return data;
		
	 }


	 public static Map<String,Object> convertJSONStringTOMap(String jsonString){
		  if(jsonString == null){
		   return null;
		  }
		  ObjectMapper mapper = new ObjectMapper();
		  try
		  {
		   Map<String,Object> map = (Map<String,Object>)mapper.readValue(jsonString, Map.class);
		   return map;
		  } catch (JsonGenerationException e) {
		   e.printStackTrace();
		  } catch (JsonMappingException e) {
		   e.printStackTrace();
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		  return null;
		 }
	
	
}
