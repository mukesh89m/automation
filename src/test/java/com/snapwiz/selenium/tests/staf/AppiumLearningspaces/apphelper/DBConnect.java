package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.mongodb.*;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Config;

public  class DBConnect{
    public static Statement st;
    public static Connection conn;
    public static void Connect() {
        String url = Config.dbconnecturl;
        String dbName = Config.dbName;
        String driver = Config.dbdriver;
        String userName = Config.dbuserName;
        String password = Config.dbPassword;
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName,userName,password);
            st = conn.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception while connecting to database",e);
        }
    }

    public static void updateUserPassword(String userType , String updatePassword){
        try{
            Connect();
            ResultSet rs=DBConnect.st.executeQuery("select id from t_user where id in (SELECT user_id FROM t_course_user where course_id = 253) and id in(select user_id from t_user_role where type = '"+userType+"') limit 1,1;");
            String id="";
            while (rs.next())
            {
                id=rs.getString("id");
            }
            DBConnect.st.executeUpdate("update t_user set password = '"+updatePassword+"' where id ='"+id+"'");
        }catch(Exception e){
            Assert.fail("Exception in reading ID from database");
        }
    }



    public static String updateUserPasswordAndReturnUserName(String userType , String updatePassword){
        String userName="";
        try{
            Connect();
            ResultSet rs=DBConnect.st.executeQuery("select id, username from t_user where id in (SELECT user_id FROM t_course_user where course_id = 253) and id in(select user_id from t_user_role where type = '"+userType+"') limit 1,1;");
            String id="";
            while (rs.next())
            {
                id=rs.getString("id");
                userName=rs.getString("username");
            }
            DBConnect.st.executeUpdate("update t_user set password = '"+updatePassword+"' where id ='"+id+"'");
        }catch(Exception e){
            Assert.fail("Exception in reading ID from database");
        }
        return userName;
    }


    public static DBCollection mongoConnect(String collectionName)
    {
        DBCollection collection = null;
        try
        {
            MongoClient mongoClient = new MongoClient( "10.0.0.241" , 27017);
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

    public static void setTotalParticipationCountMongo(int csId,int dc)
    {
        try
        {
            DBCollection collection = mongoConnect("ClassSectionCourseSummary");
            BasicDBObject update = new BasicDBObject();
            update.append("$set", new BasicDBObject().append("dc", dc));
            BasicDBObject query = new BasicDBObject().append("csId", csId);
            System.out.println("query = " + query.toString());
            System.out.println("update = " + update.toString());

            collection.update(query, update, true, false);
        }
        catch(Exception e)
        {
            Assert.fail("Exception while getting proficiencies from mongo database",e);
        }
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
