package com.snapwiz.selenium.tests.staf.datacreation.testcases;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.snapwiz.selenium.tests.staf.datacreation.Config;
import com.snapwiz.selenium.tests.staf.datacreation.Driver;
import com.snapwiz.selenium.tests.staf.datacreation.Redis;
import com.snapwiz.selenium.tests.staf.datacreation.apphelper.*;
import com.snapwiz.selenium.tests.staf.datacreation.uihelper.RandomNumber;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by akansh on 17/10/14.
 */
public class DataCreate extends Driver {

    String getStringOrEmpty(Object o)
    {
        if (o!=null){
            if(o instanceof Double)
                return (Math.round((Double)o)) + "";
            else
                return o.toString();
        }
        return "";
    }

    @Test(priority = 1)
    public void dataCreateOrion() {
        try {
            if(!Config.course.equalsIgnoreCase("orion")) {
                Assert.fail("Course is not set as orion");
            }
            /*DBConnect.st.executeUpdate("insert into t_course_context (name,value,course_id) values ('SHOW_DEBUG_MESSAGE','true','" + Config.csorionid + "')");
            new Redis().deleteKeys();*/
            /*for(int ins = 293;ins<=293;ins++) {
                new LoginUsingLTI().ltiLogin(ins, 0, "0");
            }*/

            for (int userNo = 1; userNo <= 1; userNo++) {

                        new LoginUsingLTI().ltiLogin(userNo, 0, "0");

                        for(int chapterNo = 0; chapterNo < 2 ; chapterNo++) {
                            new DiagnosticTest().startTest(0, new RandomNumber().generateRandomNumber(1,5));
                            if(userNo <= 60 || (userNo > 65 && userNo <=70)) {
                                new DiagnosticTest().attemptProfile(1, "Diagnostic", "orion", userNo);
                            }
                            else /*if((userNo > 60 && userNo <= 65 || (userNo > 70)))*/ {
                                new DiagnosticTest().attemptProfile(new RandomNumber().generateRandomNumber(2, 6), "Diagnostic", "orion", userNo);
                            }
                            new Navigator().orionDashboard();
                            new PracticeTest().startTest(chapterNo);
                            if(userNo <= 60 || (userNo > 65 && userNo <=70)) {
                                new DiagnosticTest().attemptProfile(new RandomNumber().generateRandomNumber(1, 6), "Practice", "orion", userNo);
                            }
                            else /*if(userNo > 60 && userNo <= 65 || (userNo > 70))*/ {
                                new DiagnosticTest().attemptProfile(new RandomNumber().generateRandomNumber(2, 6), "Practice", "orion", userNo);
                            }
                            new Navigator().orionDashboard();


                    }

                System.out.println("Completed "+userNo);
                if((userNo % 10) == 0)
                    System.out.println("Completed "+userNo+ " of 76");
                //cnt = 1;
            }
         /*   DBConnect.st.executeUpdate("delete from t_course_context where name = 'SHOW_DEBUG_MESSAGE' and course_id = "+Config.csorionid);
            new Redis().deleteKeys();*/
            new RunScheduledJobs().runScheduledJobs();

        } catch (Exception e) {
            Assert.fail("Exception while creating user data of Orion Course", e);
        }
    }

    @Test(priority = 2)
    public void dataCreationLSAdp() {
        try {
            if(!Config.course.equalsIgnoreCase("lsadp")) {
                Assert.fail("Course is not set as ls+adp");
            }

            for (int i = 1; i <= 2; i++) {  // i is Number of Class Sections. cnt is number of students

                        new LoginUsingLTI().ltiLogin(i, 0, "0");

                        new TOCShow().chaptertree();
                        new ExpandCollapseChapter().expandChapter(3);
                        new DiagnosticTest().startTestLS(new RandomNumber().generateRandomNumber(1,5),1);
                        new DiagnosticTest().attemptProfile(1, "Diagnostic","lsadp",i);
                        new TOCShow().chaptertree();

                        new PracticeTest().startTestLS(1);
                        new DiagnosticTest().attemptProfile(new RandomNumber().generateRandomNumber(1, 6), "Practice","lsadp",i); // new PracticeTest().attemptCorrectAnswerLS(15);


                        /*    new PracticeTest().quitTestAndGoToDashboard();
                            Thread.sleep(3000);
                            new TOCShow().chaptertree();
                            new ExpandCollapseChapter().expandChapter(2);
                            new DiagnosticTest().startTestLS(new RandomNumber().generateRandomNumber(1, 5), 2);
                            new DiagnosticTest().attemptProfile(1, "Diagnostic", "lsadp", i);
                            new TOCShow().chaptertree();
                            new ExpandCollapseChapter().expandChapter(2);
                            new PracticeTest().startTestLS(2);
                            new DiagnosticTest().attemptProfile(new RandomNumber().generateRandomNumber(1, 6), "Practice", "lsadp", i);
*/
                System.out.println("Completed "+i);
                    }

          //  new RunScheduledJobs().runScheduledJobsForLSDashboard();

            }
          catch (Exception e) {
            Assert.fail("Exception while creating user data of LS and Adaptive Course", e);
        }
    }

    @Test(priority = 3)
    public void dataCreationLS()
    {
        try {

            if(!Config.course.equalsIgnoreCase("ls")) {
                Assert.fail("Course is not set as ls");
            }
            int cnt = 1;
            for (int i = 4; i <= 4; i++) {  // i is Number of Class Sections. cnt is number of students

                if (cnt == 1) {
                    new LoginUsingLTI().ltiLogin(i, cnt, "0");

                    while (cnt < 2) {
                        new LoginUsingLTI().ltiLogin(i, cnt, "0");

                        new TOCShow().chaptertree();
                        new PracticeTest().startStaticPracticeTestLS();

                        new PracticeTest().attemptStaticPractice(15);


                        cnt++;
                    }

                }
                cnt = 1;

            }
            new RunScheduledJobs().runScheduledJobsForLSDashboard();

        }
        catch (Exception e) {

        }
    }

    public List<Map> getDataFromMongo(DBCollection collection)
    {
        List<Map> data = new ArrayList<Map>();
        try
        {
            DBObject query = new BasicDBObject();

            DBObject sort = new BasicDBObject();
            sort.put("_id",-1);

            DBCursor dbObject = collection.find(query).sort(sort).limit(1);

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

/*select per.user_id, per.class_section_id from t_class_section_permission per, t_user usr where usr.id = per.user_id and usr.external_id in ('autoinstructororion_141','autostudentorion_141','autostudentorion_142','autostudentorion_143','autostudentorion_144','autostudentorion_145');*/
/*update t_user set password = '6ab14d3be1858b6e8f9f223b6980c7a6dc087a1d4871255f7fc366535762f2a90213d242f2eaa0ab' where  external_id in ('autoinstructororion_141','autostudentorion_141','autostudentorion_142','autostudentorion_143','autostudentorion_144','autostudentorion_145');*/
/*insert into t_course_context (name,value,course_id) values ('SHOW_DEBUG_MESSAGE','true',255);*/
