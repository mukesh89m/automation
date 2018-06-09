package com.snapwiz.selenium.tests.staf.learnon.apphelper;

import com.mongodb.*;
import com.snapwiz.selenium.tests.staf.learnon.Config;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
            MongoClient mongoClient = new MongoClient( "localhost" , 27017);
            DB db = mongoClient.getDB("mongrel");
            collection = db.getCollection(collectionName);
        }
        catch(Exception e)
        {
            Assert.fail("Exception while connecting to mongo database",e);
        }
        return collection;
    }

    public static Double getProficienciesFromMongo(int userid,int classid)
    {
        List<Double> doubles = new ArrayList<Double>();
        Double profsum=0.0;
        try
        {
            DBCollection collection = mongoConnect("lsDashBoard");
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
                Map<String, Object> tempMap = (Map<String, Object>) map.get("tlos");
                Collection<Object> tlosList = tempMap.values();
                for(Object tlo : tlosList){
                    Double profValue = (Double) ((Map<String,Object>)tlo).get("prof");
                    Double ecfValue = (Double) ((Map<String,Object>)tlo).get("ecf");
                    if(profValue != null && ecfValue != null)
                        doubles.add(profValue * ecfValue);
                }
                for(Double value : doubles)
                profsum = profsum+value;

            }

        }
        catch(Exception e)
        {
            Assert.fail("Exception while getting proficiencies from mongo database",e);
        }
        return profsum;

    }

    public static Double getTotalECFfromMongo(int userid,int classid)
    {
        List<Double> doubles = new ArrayList<Double>();
        Double totalECF = 0.0;
        try
        {
            DBCollection collection = mongoConnect("lsDashBoard");
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
                Map<String, Object> tempMap = (Map<String, Object>) map.get("tlos");
                Collection<Object> tlosList = tempMap.values();
                for(Object tlo : tlosList){
                    Double ecfValue = (Double) ((Map<String,Object>)tlo).get("ecf");
                    if(ecfValue != null)
                        doubles.add(ecfValue);
                }
                for(Double value : doubles)
                totalECF = totalECF + value;
            }

        }
        catch(Exception e)
        {
            Assert.fail("Exception while getting proficiencies from mongo database",e);
        }
        return totalECF;

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
