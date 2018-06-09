package com.snapwiz.selenium.tests.staf.edulastic.apphelpers;


import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.mongodb.*;

import com.mongodb.util.JSON;
import com.snapwiz.selenium.tests.staf.edulastic.Config;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.testng.Assert;

public  class DBConnect {
    public static Statement st;
    public static Connection conn;

    public static void Connect() {

        Config.readconfiguration();

        String url = Config.dbconnecturl;
        String dbName = Config.dbName;
        String driver = Config.dbdriver;
        String userName = Config.dbuserName;
        String password = Config.dbPassword;
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
            st = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception while connecting to database", e);
        }
    }

    public static DBCollection mongoConnect(String collectionName) {
        DBCollection collection = null;
        try {
            MongoClient mongoClient = new MongoClient("10.0.0.70", 27017);
            DB db = mongoClient.getDB("mongrel");
            collection = db.getCollection(collectionName);
        } catch (Exception e) {
            Assert.fail("Exception while connecting to mongo database", e);
        }
        return collection;
    }



    public static Long collectionValue(DBCollection collection,String id,String value) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", new ObjectId(id));
        DBCursor cursor = collection.find(whereQuery);
        Long eid = null;
        while(cursor.hasNext()) {
            DBObject dbObject= cursor.next();
            System.out.println("cursor value:"+dbObject);
            eid = (Long)dbObject.get(value); //csId
            System.out.println("value === "+eid);
        }
        return eid;
    }

}

