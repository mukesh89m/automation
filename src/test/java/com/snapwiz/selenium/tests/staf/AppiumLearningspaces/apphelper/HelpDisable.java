package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.testng.Assert;

import java.sql.ResultSet;

/**
 * Created by root on 11/3/15.
 */
public class HelpDisable {

    public void helpDisable(String userId) {
        try {
            com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DBConnect.Connect();
            ResultSet rst = com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DBConnect.st.executeQuery("select id from t_user where external_id like '"+userId+"'");
            String id="";
            while (rst.next())
            {
                id=rst.getString("id");
            }

            DBCollection userContextCollection = com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DBConnect.mongoConnect("UserContext");
            DBObject fields = new BasicDBObject();
            fields.put("cn", "HELP_CONTEXT");
            fields.put("uId", Integer.parseInt(id));
            DBObject inner1 = new BasicDBObject();
            inner1.put("PAGE_ID","DASHBOARD");
            inner1.put("NOTIFICATION_ID","1");
            inner1.put("USER_DISPLAY_COUNT","1");
            inner1.put("STOP_DISPLAY_FLAG","Y");

            DBObject inner2 = new BasicDBObject();
            inner2.put("PAGE_ID","helpCardInfo");
            inner2.put("NOTIFICATION_ID","helpCard");
            inner2.put("USER_DISPLAY_COUNT","1");

            DBObject inner3 = new BasicDBObject();
            inner3.put("PAGE_ID","DASHBOARD");
            inner3.put("NOTIFICATION_ID","2");
            inner3.put("USER_DISPLAY_COUNT","1");
            inner3.put("STOP_DISPLAY_FLAG","Y");

            DBObject inner4 = new BasicDBObject();
            inner4.put("PAGE_ID","COURSE_STREAM");
            inner4.put("NOTIFICATION_ID","3");
            inner4.put("USER_DISPLAY_COUNT","1");
            inner4.put("STOP_DISPLAY_FLAG","Y");

            DBObject inner5 = new BasicDBObject();
            inner5.put("PAGE_ID","COURSE_STREAM");
            inner5.put("NOTIFICATION_ID","4");
            inner5.put("USER_DISPLAY_COUNT","1");
            inner5.put("STOP_DISPLAY_FLAG","Y");

            DBObject inner6 = new BasicDBObject();
            inner6.put("PAGE_ID","COURSE_STREAM");
            inner6.put("NOTIFICATION_ID","5");
            inner6.put("USER_DISPLAY_COUNT","1");
            inner6.put("STOP_DISPLAY_FLAG","Y");

            DBObject inner7 = new BasicDBObject();
            inner7.put("PAGE_ID","TOC");
            inner7.put("NOTIFICATION_ID","12");
            inner7.put("USER_DISPLAY_COUNT","1");
            inner7.put("STOP_DISPLAY_FLAG","Y");

            DBObject inner8 = new BasicDBObject();
            inner8.put("PAGE_ID","TOC");
            inner8.put("NOTIFICATION_ID","13");
            inner8.put("USER_DISPLAY_COUNT","1");
            inner8.put("STOP_DISPLAY_FLAG","Y");

            DBObject inner9 = new BasicDBObject();
            inner9.put("PAGE_ID","TOC");
            inner9.put("NOTIFICATION_ID","14");
            inner9.put("USER_DISPLAY_COUNT","1");
            inner9.put("STOP_DISPLAY_FLAG","Y");

            DBObject inner10 = new BasicDBObject();
            inner10.put("PAGE_ID","TEXTBOOK");
            inner10.put("NOTIFICATION_ID","9");
            inner10.put("USER_DISPLAY_COUNT","1");
            inner10.put("STOP_DISPLAY_FLAG","Y");

            DBObject inner11 = new BasicDBObject();
            inner11.put("PAGE_ID","TEXTBOOK");
            inner11.put("NOTIFICATION_ID","10");
            inner11.put("USER_DISPLAY_COUNT","1");
            inner11.put("STOP_DISPLAY_FLAG","Y");

            DBObject inner12 = new BasicDBObject();
            inner12.put("PAGE_ID","TEXTBOOK");
            inner12.put("NOTIFICATION_ID","11");
            inner12.put("USER_DISPLAY_COUNT","1");
            inner12.put("STOP_DISPLAY_FLAG","Y");

            DBObject inner13 = new BasicDBObject();
            inner13.put("PAGE_ID","TEXTBOOK");
            inner13.put("NOTIFICATION_ID","22");
            inner13.put("USER_DISPLAY_COUNT","1");
            inner13.put("STOP_DISPLAY_FLAG","Y");

            DBObject inner14 = new BasicDBObject();
            inner14.put("PAGE_ID","MY_JOURNAL");
            inner14.put("NOTIFICATION_ID","6");
            inner14.put("USER_DISPLAY_COUNT","1");
            inner14.put("STOP_DISPLAY_FLAG","Y");

            DBObject inner15 = new BasicDBObject();
            inner15.put("PAGE_ID","MY_JOURNAL");
            inner15.put("NOTIFICATION_ID","7");
            inner15.put("USER_DISPLAY_COUNT","1");
            inner15.put("STOP_DISPLAY_FLAG","Y");

            DBObject inner16 = new BasicDBObject();
            inner16.put("PAGE_ID","MY_JOURNAL");
            inner16.put("NOTIFICATION_ID","8");
            inner16.put("USER_DISPLAY_COUNT","1");
            inner16.put("STOP_DISPLAY_FLAG","Y");

            fields.put("cv","["+inner1.toString()+","+inner2.toString()+","+inner3.toString()+","+inner4.toString()+","+inner5.toString()+","
                    +inner6.toString()+","+inner7.toString()+","+inner8.toString()+","+inner9.toString()+","+inner10.toString()+","+
                    inner11.toString()+","+inner12.toString()+","+inner13.toString()+","+inner14.toString()+","+inner15.toString()+","+inner16.toString()+"]");

            userContextCollection.save(fields);
            com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DBConnect.conn.close();

        }
        catch (Exception e) {
            Assert.fail("", e);
        }
    }
}
