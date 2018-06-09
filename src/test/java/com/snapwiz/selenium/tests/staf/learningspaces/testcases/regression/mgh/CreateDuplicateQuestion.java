package com.snapwiz.selenium.tests.staf.learningspaces.testcases.regression.mgh;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mukesh on 15/6/16.
 */
public class CreateDuplicateQuestion extends Driver{
    String baseUrl = "http://10.0.5.130:8080/";
    ArrayList<String> questionIds = new ArrayList<>();

    @Test(priority = 1,enabled = true)
    public void createDuplicateQuestion() throws Exception{
        WebDriver driver = Driver.getWebDriver();
        new DBConnect().Connect();
        String query="SELECT tq.id,tq.status\n" +
                "FROM t_assessment ta,t_question_set tqs,t_category_assessment_map tcam,t_question_set_item tqsi,t_questions tq,t_category tca,t_course tc\n" +
                "where ta.id=tqs.assessment_id\n" +
                "and tqs.id=tqsi.question_set_id\n" +
                "and tqsi.question_id=tq.id\n" +
                "and ta.id=tcam.assessment_id\n" +
                "and tca.id=tcam.category_id\n" +
                "and tca.course_id =tc.id and ta.id=205988\n" +
                "and tc.id = 246 and tq.status=80;";

        ResultSet rs=DBConnect.st.executeQuery(query);
        String id="";
        while (rs.next())
        {
            id=rs.getString("tq.id");
            System.out.println(id);
            questionIds.add(id);

        }


      /*  questionIds.add("223837");
        questionIds.add("223838");
        questionIds.add("223839");
        questionIds.add("223840");
        questionIds.add("223842");
        questionIds.add("223844");
        questionIds.add("223847");
        questionIds.add("223848");
        questionIds.add("223849");
        questionIds.add("223850");*/


        System.out.println("Total number of question:"+questionIds.size());
        for (String str:questionIds){
            System.out.println("DB Question ID:"+str);
        }
        int a=0;

        try {
            CMSLogin();
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.xpath("//img[@title='NAPLEXEasy']")));//click on the course name
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("div[class='course-label node']")));//click on the course name
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.xpath("//div[@class='collection-assessment-name']")));//click on the lesson name
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("manage-content-tab")));//click on the manageContentTab
            Thread.sleep(9000);
            String originalQID="";
            String duplicateQID="";
            List<String> ids=new ArrayList<>();
            for(;a<=20;a++){

                try{
                    driver.get(baseUrl + "secure/manageContent?courseId=246#/quesEditView?qId=" + questionIds.get(a) + "&qSetId=12968");
                    new WebDriverUtil().waitUntilTextToBePresentInElement(driver.findElement(By.id("question-id-label")),questionIds.get(a),60);

                    //originalQID = driver.findElement(By.id("question-id-label")).getText().trim();
                    new Assignment().duplicatQuestion();
                    duplicateQID = driver.findElement(By.id("question-id-label")).getText().trim();

                    ids.add(questionIds.get(a)+","+duplicateQID);
                    System.out.println(questionIds.get(a)+","+duplicateQID);
                    Thread.sleep(9000);
                    String updateQuery="update t_question_set_item set question_set_id =13869 where question_id="+duplicateQID+"";
                    DBConnect.st.execute(updateQuery);

                    String updateSkillMap="update t_question_skill_map set course_id=285 where question_id ="+duplicateQID+";";
                    DBConnect.st.execute(updateSkillMap);
                    System.out.println("t_question_skill_map updated of question id:"+duplicateQID);

                    String updateQuestionSetItemPosition="update t_question_set_item SET position="+a+" where question_id="+duplicateQID+";";
                    DBConnect.st.execute(updateQuestionSetItemPosition);
                    System.out.println("Question set item position updated:" + duplicateQID);
                    System.out.println("No of question has duplicated:"+a);


                }catch(Exception e){
                    System.out.println("Failed in the question id : " + questionIds.get(a));
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.out.println("Failed in the question id : " + questionIds.get(a));
        }
    }

    @Test(priority = 2,enabled = true)
    public void createDuplicateQuestionForInstructor() throws Exception{

        WebDriver driver = Driver.getWebDriver();
        ArrayList<String> instructorQID = new ArrayList<>();
        new DBConnect().Connect();
        String query="SELECT tq.id,tq.status\n" +
                "FROM t_assessment ta,t_question_set tqs,t_category_assessment_map tcam,t_question_set_item tqsi,t_questions tq,t_category tca,t_course tc\n" +
                "where ta.id=tqs.assessment_id\n" +
                "and tqs.id=tqsi.question_set_id\n" +
                "and tqsi.question_id=tq.id\n" +
                "and ta.id=tcam.assessment_id\n" +
                "and tca.id=tcam.category_id\n" +
                "and tca.course_id =tc.id and ta.id=206001\n" +
                "and tc.id = 246 and tq.status=80;";

        ResultSet rs=DBConnect.st.executeQuery(query);
        String id="";
        while (rs.next())
        {
            id=rs.getString("tq.id");
            instructorQID.add(id);

        }

       /* instructorQID.add("241523");
        instructorQID.add("241524");
        instructorQID.add("241525");
        instructorQID.add("241527");
        instructorQID.add("241528");*/



        System.out.println("Total number of question:"+instructorQID.size());
        for (String str:instructorQID){
            System.out.println("DB Question ID:"+str);
        }
        int a=0;

        try {
            CMSLogin();
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.xpath("//img[@title='NAPLEXEasy']")));//click on the course name
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("div[class='course-chapter-label node']")));//click on the course name
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.xpath("//div[@class='collection-assessment-name']")));//click on the lesson name
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("manage-content-tab")));//click on the manageContentTab
            Thread.sleep(9000);
            String originalQID="";
            String duplicateQID="";
            List<String> ids=new ArrayList<>();

            for(a=0;a<=16;a++){

                try{
                    driver.get(baseUrl + "secure/manageContent?courseId=246#/quesEditView?qId=" + instructorQID.get(a) + "&qSetId=12994");
                    new WebDriverUtil().waitUntilTextToBePresentInElement(driver.findElement(By.id("question-id-label")),instructorQID.get(a),60);

                    //originalQID = driver.findElement(By.id("question-id-label")).getText().trim();
                    new Assignment().duplicatQuestion();
                    duplicateQID = driver.findElement(By.id("question-id-label")).getText().trim();

                    ids.add(instructorQID.get(a) + "," + duplicateQID);
                    System.out.println(instructorQID.get(a) + "," + duplicateQID);

                    //question set id need to be added

                    String updateQuery="update t_question_set_item set question_set_id =13870 where question_id="+duplicateQID+"";
                    DBConnect.st.execute(updateQuery);

                    String updateSkillMap="update t_question_skill_map set course_id=285 where question_id ="+duplicateQID+";";
                    DBConnect.st.execute(updateSkillMap);
                    System.out.println("t_question_skill_map updated of question id:"+duplicateQID);

                    String updateQuestionSetItemPosition="update t_question_set_item SET position="+a+" where question_id="+duplicateQID+";";
                    DBConnect.st.execute(updateQuestionSetItemPosition);
                    System.out.println("Question set item position updated:" + duplicateQID);
                    System.out.println("No of question has duplicated:"+a);

                }catch(Exception e){
                    System.out.println("Failed in the question id : " + instructorQID.get(a));
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.out.println("Failed in the question id : " + instructorQID.get(a));
        }
    }



    public  void CMSLogin() {
        try {
            WebDriver driver = Driver.getWebDriver();
            driver.get(baseUrl+"ls/login");
            new UIElement().waitAndFindElement(By.id("username"));
            driver.findElement(By.id("username")).sendKeys("naplexeasy1");
            driver.findElement(By.id("password")).sendKeys("snapwiz");
            driver.findElement(By.id("loginSubmitBtn")).click();
        } catch (Exception e) {
            Assert.fail("Exception in CMSLogin in class DirectLogin", e);
        }
    }
}
