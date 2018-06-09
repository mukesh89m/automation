package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;

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
	 
	 public static void dropMongo()
	 {
		 try
		 {
			 MongoClient mongoClient = new MongoClient( Config.mongoHost , Integer.parseInt(Config.mongoPort) );
			 DB db = mongoClient.getDB(Config.dbName);
			 db.dropDatabase();
		 }
		 catch(Exception e)
		 {
			 Assert.fail("Exception while dropping mongo database",e);
		 }
	 }
	 
	 public static List<Double> getProficienciesFromMongo(int userid,int classid)
	 {
		 List<Double> doubles = new ArrayList<Double>();		
		 try
		 {
		DBCollection collection = mongoConnect("dashBoard");
		DBObject query = new BasicDBObject();
		query.put("csId", classid);
		query.put("uId", userid);
		DBObject fields = new BasicDBObject();
		fields.put("tlos", 1);
		//fields.put("tlos.rProf", 1);
		DBCursor dbObject = collection.find(query, fields, 0, 1);
		
		while(dbObject.hasNext()){
			//qr.add(dbObject.next().toString());
			
			Map<String,Object> map = convertJSONStringTOMap(dbObject.next().toString());
			Object obj  = map.get("tlos");
			List<Object> tlosList = (List<Object>)obj;
			
			for(Object tlo : tlosList){
				Double value = (Double) ((Map<String,Object>)tlo).get("prof");
				if(value != null)
				doubles.add(value);
			}
			
		}
		
		 }
		 catch(Exception e)
		 {
			 Assert.fail("Exception while getting proficiencies from mongo database",e);
		 }
		return doubles;
		
	 }
	 
	 public static int getAttemptedQuestions(int qid,DBCollection collection,int userid,int classid)
	 {
	   int value=0;
		 try
		 {
		
		DBObject query = new BasicDBObject();
		query.put("csId", classid);
		query.put("uId", userid);
		query.put("qId", qid);
		DBObject fields = new BasicDBObject();
		fields.put("qId", 1);
		DBCursor dbObject = collection.find(query, fields, 0, 1);
		
		while(dbObject.hasNext()){			
			Map<String,Object> map = convertJSONStringTOMap(dbObject.next().toString());
			Object obj  = map.get("qId");
			value = ((Integer)obj);			
			}
		
		}
		 catch(Exception e)
		 {
			 Assert.fail("Exception while getting the attempted questions from mongo database",e);
		 }
		return value;
		
	 }
	 
	 public static int getQuestions(int qid,DBCollection collection,int userid,int classid,String questionFilterCondition)
	 {
	   int value=0;
	   Boolean skipped=null,corrected=null;
		 try
		 {
		
		DBObject query = new BasicDBObject();
		query.put("csId", classid);
		query.put("uId", userid);
		query.put("qId", qid);
		/*if(questionFilterCondition.equalsIgnoreCase("skipped"))
			query.put("skip",true);
		else if(questionFilterCondition.equalsIgnoreCase("incorrect"))
			query.put("correct",false);
		else
			query.put("correct",true);*/
		DBObject fields = new BasicDBObject();
		fields.put("qId", 1);
		fields.put("skip", 1);
		fields.put("correct", 1);
		//fields.put("adate", -1);
		//fields.put("time", 1);
		DBCursor dbObject = collection.find(query, fields, 0, 1);
		dbObject.sort(new BasicDBObject( "adate" , -1 ));
		while(dbObject.hasNext()){			
			Map<String,Object> map = convertJSONStringTOMap(dbObject.next().toString());
			Object obj  = map.get("qId");
			value = ((Integer)obj);
			Object skip = map.get("skip");
			skipped = ((Boolean)skip);
			Object correct = map.get("correct");
			corrected = ((Boolean)correct);			
			break;
			}
		//System.out.println("Mongo value "+value+" "+skipped+" "+corrected);
		if(questionFilterCondition.equals("skipped"))
			{
				if(skipped == null)			
					value = 0;
			}
		if(questionFilterCondition.equals("correct"))
		{
			if(corrected == null)			
				value = 0;
			if(value !=0)
			{
				if(corrected == false)
					value = 0;
			}
			
		}
		if(questionFilterCondition.equals("incorrect"))
		{
			if(corrected == null)			
				value = 0;
			if(value !=0)
			{
				if(corrected == true)
					value = 0;
			}
			
		}	
		}
		 catch(Exception e)
		 {
			 Assert.fail("Exception while getting the attempted questions from mongo database",e);
		 }
		return value;
		
	 }
	 
	/* public static void main(String [] args)
	 {
		 DBCollection collection = DBConnect.mongoConnect("UserQuestionHistory");
		 int val = DBConnect.getQuestions(338971,collection,23192,1651,"correct");
		 System.out.println(val);
	 }*/
	 
	 public void generateComputedDifficulty(int dataIndex,int ...tloId)
	 {
		 try
		 {
			 String practiceassessmentname= ReadTestData.readDataByTagName("", "practiceassessmentname",Integer.toString(dataIndex));
			 int assessmentid = 0;
			 List<Integer> qidstlo1 = new ArrayList<Integer>();
			ResultSet rstassmtid = DBConnect.st.executeQuery("select id as assessment_id from t_assessment where name like '"+practiceassessmentname+"' and assessment_type like 'Adaptive Component Practice';");
			while(rstassmtid.next())
			{
				assessmentid = rstassmtid.getInt("assessment_id");
			}
			int [] tloIds = tloId;
			for(int i=0;i<tloIds.length;i++)
			{
			ResultSet getqids = DBConnect.st.executeQuery("select distinct tq.id as questionid from t_questions tq, t_assessment ta, t_question_set tqs, t_question_set_item tqsi,t_master_skill tms, t_question_skill_map tqsm where ta.id = tqs.assessment_id and" +
					" tq.id = tqsi.question_id and tqs.id = tqsi.question_set_id and tms.id = tqsm.master_skill_id and tq.id = tqsm.question_id and" +
					" ta.id = "+assessmentid+" and tms.id = "+tloIds[i]+" and tq.status = 80;");
			while(getqids.next())
			{
				qidstlo1.add(getqids.getInt("questionid"));
			}
			int lower = 10;
			int upper = 100;
			for(Integer qid : qidstlo1)
				{				
				int R = (int)(Math.random() * (upper - lower)) + lower;
				DBConnect.st.executeUpdate("UPDATE t_questions set computed_difficulty= "+R+" where id= "+qid+";");
				}
			}
		 }
		 catch(Exception e)
		 {
			 Assert.fail("Exception while generating computed difficulty", e);
		 }
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
