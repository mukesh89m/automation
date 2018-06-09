package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT17.R1722;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mukesh on 7/10/14.
 */
public class DefaultViewOnSummaryPage extends Driver {

    @Test(priority=1,enabled = true)
    public void summaryPageDefaultView()
    {
        try {
            //Row no 2 - "1. Login as a CMS user (Super admin)  2. Select a course and go to ""Summary"" page" Expected - #1. Default value in the filter should be "All Authors"
            //Driver.startDriver();
            new DirectLogin().CMSLogin(); //CMS login as author
            new SelectCourse().selectcourse(); //select course
            new Click().clickByid("deliver-course");//click on summary
            new Click().clickbylinkText("All Authors"); //click on All authors drop down
            //Expected - #2. By default "Difficulty level count" should be selected and should show "Pie chart" of all questions count
            new Click().clickbylinkText("Difficulty Level Count");
            String questionCount= new TextFetch().textfetchbyclass("highcharts-title");
            if(questionCount==null || questionCount.equals(""))
                Assert.fail("Question count value in pie chart present on summary page is blank or null");
            //#3. Click on the dropdown arrow Expected - #1. It should show the list of all CMS users (Super admin, product designer and Subject matter expert) with first name followed with last name
            new Click().clickbylinkText("All Authors");
            List<String> authors=new TextFetch().fetchhiddentextbyxpath("//select[@id='course-question-author-drop-down']/following-sibling::div/ul/div[2]/div/li/a");
            authors.remove("All Authors");//remove all authors from list
            authors.remove("Admin, Publisher1");
            authors.remove("Admin, Publisher2");
            authors.remove("Admin, Publisher3");
            Collections.sort(authors);
            Thread.sleep(5000);
            new DBConnect().Connect();
            ResultSet rs = DBConnect.st.executeQuery("select firstname,lastname from t_user where id in (SELECT user_id FROM t_course_user where course_id = 253) and id in(select user_id from t_user_role where type = 'ROLE_SUPER_ADMIN' or type = 'ROLE_SUBJECT_MATTER_EXPERT' or type='ROLE_PRODUCT_DESIGNER') order by lastname;");
            List<String> DBName = new ArrayList<>();
            while (rs.next())
            {
                DBName.add(rs.getString("lastname") + ", " + rs.getString("firstname"));
            }
            //DBName.add(0,"All Authors");//add All
            Collections.sort(DBName);
            System.out.println("DB Name:"+DBName);
            System.out.println("DB Name Size:"+DBName.size());
            System.out.println("UI authors Size:"+authors);
            System.out.println("UI authors Size:"+authors.size());

            if(!(CollectionUtils.isEqualCollection(DBName, authors)))
                Assert.fail("list of all CMS users from UI are not matched with list of all CMS users from DB");

            //#2. Below the "All Authors" it should show the users in alphabetical order based on the "First name

            new Click().clickbylinkText("All Authors");
            List<String> UIOrdersName=new TextFetch().fetchhiddentextbyxpath("//select[@id='course-question-author-drop-down']/following-sibling::div/ul/div[2]/div/li/a");
            UIOrdersName.remove("All Authors");//remove all authors from list
            UIOrdersName.remove("Admin, Publisher1");
            UIOrdersName.remove("Admin, Publisher2");
            UIOrdersName.remove("Admin, Publisher3");
            System.out.println("Authors:"+UIOrdersName);
            Thread.sleep(5000);
            new DBConnect().Connect();

            ResultSet rst = DBConnect.st.executeQuery("select firstname,lastname from t_user where id in (SELECT user_id FROM t_course_user where course_id = 253) and id in(select user_id from t_user_role where type = 'ROLE_SUPER_ADMIN' or type = 'ROLE_SUBJECT_MATTER_EXPERT' or type='ROLE_PRODUCT_DESIGNER') order by firstname");
            List<String> DBOrderName = new ArrayList<>();
            while (rst.next())
            {
                DBOrderName.add(rst.getString("lastname") + ", " + rst.getString("firstname"));
            }
            System.out.println("DBAuthors Name:"+DBOrderName);
            if(!(CollectionUtils.isEqualCollection(UIOrdersName,DBOrderName)))
                Assert.fail("The \"All Authors\" are not in alphabetical order based on the \"First name");

            // #3. Should show users only who is having permisssion to the selected course

            List<String> permittedAuthors=new TextFetch().fetchhiddentextbyxpath("//select[@id='course-question-author-drop-down']/following-sibling::div/ul/div[2]/div/li/a");
            permittedAuthors.remove("All Authors");//remove all authors from list
            System.out.println("Permitted Authors:"+permittedAuthors);
            System.out.println("Permitted Authors Size:"+permittedAuthors.size());

            ResultSet rst1 = DBConnect.st.executeQuery("SELECT firstname,lastname FROM t_user where id in (select user_id from t_course_user where course_id = 253) order by lastname;");
            List<String> DBAuthor = new ArrayList<>();
            while (rst1.next())
            {
                DBAuthor.add(rst1.getString("lastname") + ", " + rst1.getString("firstname"));
            }

            System.out.println("DB Author Name::"+DBAuthor);
            System.out.println("DB Author Name Size::"+DBAuthor.size());
            if(!CollectionUtils.isEqualCollection(permittedAuthors,DBAuthor))
                Assert.fail("showing users  who is not having permisssion to the selected course");

            //#4. Select "Bloom's taxonomy count" from the dropdown
            new Click().clickbyxpath("(//select[@id='course-question-content-metrics-drop-down']/following-sibling::div/a)[1]");
            new Click().clickbylinkText("Bloom's Taxonomy Count");//click on the Bloom's taxonomy count

            //Expected #1. It should show the pie chart of all authors with count
            String taxonomyCount=driver.findElement(By.cssSelector("tspan")).getText();
            System.out.println("taxonomyCount::"+taxonomyCount);
            if(taxonomyCount==null || taxonomyCount.equals(""))
                Assert.fail(" Bloom's taxonomy count value in pie chart present on summaryPageDefaultView page is blank or null"+taxonomyCount);

            //#5. Select "Question type count" from the dropdown
            new Click().clickbyxpath("(//select[@id='course-question-content-metrics-drop-down']/following-sibling::div/a)[1]");
            new Click().clickbylinkText("Question Type Count");//click on the Question Type Count
            Thread.sleep(5000);
            //Expected #1. It should show the question count for each question type of "All Authors"
            //new QuestionTypeAndCount().questionTypeCount("SELECT tq.question_type,tq.display_type, count(distinct tq.question_version_id) FROM t_assessment ta, t_questions tq, t_question_set tqs, t_question_set_item tqsi, t_category_assessment_map tcam,t_course tcu, t_subject ts,t_category tca where ta.id = tqs.assessment_id and tqs.id = tqsi.question_set_id and tq.id = tqsi.question_id and ta.id = tcam.assessment_id and tca.id = tcam.category_id and tcu.id = ts.course_id and ts.id = tca.subject_id and tq.status not in (90,100) and tcu.id = 253 group by tq.question_type order by display_type;");
            new QuestionTypeAndCount().questionTypeCount("select qs.question_type,qs.display_type,count(qs.id) as abc\n" +
                    "from t_questions qs,\n" +
                    "(select max(q.id) qid      from t_category_assessment_map casmap         cross join t_category cat\n" +
                    "cross join t_question_set qset        cross join t_question_set_item qsitem         cross join t_questions q\n" +
                    "where casmap.category_id=cat.id         and cat.course_id =253         and casmap.assessment_id=qset.assessment_id\n" +
                    "and qsitem.question_set_id=qset.id         and qsitem.question_id=q.id\n" +
                    " and q.template_question_id is null\n" +
                    " and (qsitem.is_custom_question_item is null OR qsitem.is_custom_question_item = false)\n" +
                    " group by q.question_version_id) qmax      where qs.id = qmax.qid\n" +
                    " and qs.status <> 90 and qs.status <> 100     group by qs.question_type order by qs.display_type;");


            //new QuestionTypeAndCount().questionTypeCount("select qs.question_type,count(qs.id) as abc from t_questions qs,   (select max(q.id) qid      from t_category_assessment_map casmap         cross join t_category cat        cross join t_question_set qset        cross join t_question_set_item qsitem         cross join t_questions q       where casmap.category_id=cat.id         and cat.course_id =253        and casmap.assessment_id=qset.assessment_id         and qsitem.question_set_id=qset.id         and qsitem.question_id=q.id         and q.template_question_id is null        and (qsitem.is_custom_question_item is null OR qsitem.is_custom_question_item = false)      group by q.question_version_id) qmax      where qs.id = qmax.qid      and qs.created_by =37163    and qs.status <> 90 and qs.status <> 100     group by qs.question_type;");

            //6. Select "Question status count" from the dropdown
            new Click().clickbyxpath("(//select[@id='course-question-content-metrics-drop-down']/following-sibling::div/a)[1]");
            new Click().clickbylinkText("Question Status Count");

            //Expected #1. It should show the pie chart of all authors with count
            String questionStatusCount=driver.findElement(By.cssSelector("tspan")).getText();
            if(questionStatusCount==null || questionStatusCount.equals(""))
                Assert.fail(" Question Status Count value in pie chart present on summaryPageDefaultView page is blank or null");

        }

        catch (Exception e)
        {
            Assert.fail("Exception in testcase summaryPageDefaultView in class DefaultViewOnSummaryPage",e);
        }
    }

    @Test(priority = 2,enabled = true, groups = {"bugsLogged"})
    public void showCountOfCurrentLoggedUser()
    {

        try {
            //Driver.startDriver();
            new DirectLogin().CMSLogin(); //CMS login as author
            //Row no 10 #1Login as a CMS user (Product designer)2. Select a course and go to "Summary" page
            new Assignment().create(1);//create questions
            new Assignment().addQuestions(1,"truefalse","IT17_R1722_Custom Assignment_1");
            new Assignment().addQuestions(1, "multiplechoice", "IT17_R1722_Custom Assignment_1");
            new Assignment().addQuestions(1,"textentry","IT17_R1722_Custom Assignment_1");
            new Assignment().addQuestions(1,"textdropdown","IT17_R1722_Custom Assignment_1");
            //new SelectCourse().selectcourse(); //select course
            new Click().clickByid("deliver-course");//click on summary

            // Select current logged user from the dropdown filter
            new ComboBox().selectValueByScrollToView("All Authors","Spaces, learning");

            //Expected 1. It should show "difficultLevelCount" "Pie chart" of all questions count created by the current user
            String difficultLevelCount= new TextFetch().textfetchbyid("cms-course-question-metrics-chart");
            if(difficultLevelCount==null || difficultLevelCount.equals(""))
                Assert.fail("difficultLevelCount value in pie chart present on showCountOfCurrentLoggedUser page is blank or null");

            //#4. Select "Bloom's taxonomy count" from the dropdown #1. It should show the pie chart with count of current logged in user
            new Click().clickByid("deliver-course");
            new ComboBox().selectValueByScrollToView("All Authors","Spaces, learning");
            new Click().clickbylinkText("Difficulty Level Count");
            new Click().clickbylinkText("Bloom's Taxonomy Count");//click on the Bloom's taxonomy count
            String taxonomyCount= new TextFetch().textfetchbyid("cms-course-question-metrics-chart");
            if(taxonomyCount==null || taxonomyCount.equals(""))
                Assert.fail(" Bloom's taxonomy count value in pie chart present on showCountOfCurrentLoggedUser page is blank or null");

            //#5. Select "Question type count" from the dropdown
            new Click().clickbyxpath("(//select[@id='course-question-content-metrics-drop-down']/following-sibling::div/a)[1]");
            new Click().clickbylinkText("Question Type Count");

            //Expected #1. It should show the question count for each question type of current logged in user
            String Query="SELECT tq.question_type,tq.display_type,count(distinct tq.question_version_id) as abc\n" +
                    "     FROM t_assessment ta, t_questions tq, t_question_set tqs, t_question_set_item tqsi, t_category_assessment_map tcam,\n" +
                    "      t_course tcu, t_subject ts,t_category tca\n" +
                    "        where ta.id = tqs.assessment_id and tqs.id = tqsi.question_set_id and tq.id = tqsi.question_id and\n" +
                    "         ta.id = tcam.assessment_id and tca.id = tcam.category_id and tcu.id = ts.course_id and ts.id = tca.subject_id\n" +
                    "         and tq.created_by = 37164 and tcu.id in (253) group by tq.question_type;";
            new QuestionTypeAndCount().questionTypeCount(Query);

            //#6. Select "Question status count" from the dropdown #1. It should show the pie chart of all authors with count
            new Click().clickbyxpath("(//select[@id='course-question-content-metrics-drop-down']/following-sibling::div/a)[1]");
            new Click().clickbylinkText("Question Status Count");
            String qestionStatusCount= new TextFetch().textfetchbyid("cms-course-question-metrics-chart");
            if(qestionStatusCount==null || qestionStatusCount.equals(""))
                Assert.fail(" Question Status Count value in pie chart present on showCountOfCurrentLoggedUser page is blank or null");

        } catch (Exception e) {
            Assert.fail("Exception in testcase showCountOfCurrentLoggedUser in class DefaultViewOnSummaryPage",e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void showCountOfProductDesigner()
    {
        try {
            //Driver.startDriver();
            new DBConnect().Connect();
            //Row no 14 Show count based on the current logged in user (Product designer)#"1. Login as a CMS user (Product designer
            ResultSet rs=DBConnect.st.executeQuery("select id,username,firstname,lastname  from t_user where id in (SELECT user_id FROM t_course_user where course_id = 253) and id in(select user_id from t_user_role where type = 'ROLE_PRODUCT_DESIGNER') limit 1,1;");
            String id="";
            String username="";
            String firstname="";
            String lastname="";
            String fullname="";
            while (rs.next())
            {
                id=rs.getString("id");
                username=rs.getString("username");
                firstname=rs.getString("firstname");
                lastname=rs.getString("lastname");

            }

            DBConnect.st.executeUpdate("update t_user set password = '6ab14d3be1858b6e8f9f223b6980c7a6dc087a1d4871255f7fc366535762f2a90213d242f2eaa0ab' where id ='"+id+"'");
            new Assignment().create(14);
            new Assignment().addQuestions(14,"multiplechoice","");
            new Assignment().addQuestions(14,"textentry","");
            new Assignment().addQuestions(14,"textdropdown","");

            //#2. Select a course and go to ""Summary"" page
            new Click().clickByid("deliver-course");
            //#3Select current logged user from the dropdown filter"
            fullname=lastname+", "+firstname;
            System.out.println("Full Name:"+fullname);
            new ComboBox().selectValueByScrollToView("All Authors",fullname);
            // new ComboBox().selectValueByScrollToView("All Authors","Averback, Howard");

            driver.navigate().refresh();//refresh the url
            //Expected #1. It should show "Difficulty level count" "Pie chart" of all questions count created by the current user
            String difficultLevelCount= new TextFetch().textfetchbyid("cms-course-question-metrics-chart");
            if(difficultLevelCount==null || difficultLevelCount.equals(""))
                Assert.fail("difficultLevelCount value in pie chart present on showCountOfProductDesigner page is blank or null"+difficultLevelCount);

            //#4. Select "Bloom's taxonomy count" from the dropdown #1. It should show the pie chart with count of current logged in user
            new ComboBox().selectValueByScrollToView("All Authors","Spaces, learning");
            new Click().clickbylinkText("Difficulty Level Count");
            new Click().clickbylinkText("Bloom's Taxonomy Count");//click on the Bloom's taxonomy count
            String taxonomyCount= new TextFetch().textfetchbyid("cms-course-question-metrics-chart");
            if(taxonomyCount==null || taxonomyCount.equals(""))
                Assert.fail(" Bloom's taxonomy count value in pie chart present on showCountOfProductDesigner page is blank or null"+taxonomyCount);

            //#5. Select "Question type count" from the dropdown

            new Click().clickbyxpath("(//select[@id='course-question-content-metrics-drop-down']/following-sibling::div/a)[1]");
            new Click().clickbylinkText("Question Type Count");//click on the Question Type Count
            new ComboBox().selectValueByScrollToViewByXpath("(//select[@id='course-question-author-drop-down']/following-sibling::div/a)[1]",fullname);

            //Expected #1. It should show the question count for each question type of current logged in user
            String query="";
            new QuestionTypeAndCount().questionTypeCount("select qs.question_type,count(qs.id) as abc from t_questions qs,   (select max(q.id) qid      from t_category_assessment_map casmap         cross join t_category cat        cross join t_question_set qset        cross join t_question_set_item qsitem         cross join t_questions q       where casmap.category_id=cat.id         and cat.course_id =253         and casmap.assessment_id=qset.assessment_id         and qsitem.question_set_id=qset.id         and qsitem.question_id=q.id         and q.template_question_id is null        and (qsitem.is_custom_question_item is null OR qsitem.is_custom_question_item = false)      group by q.question_version_id) qmax      where qs.id = qmax.qid      and qs.created_by =(select id from t_user where username ='iawillia@wiley.com')    and qs.status <> 90 and qs.status <> 100     group by qs.question_type;");

            //#6. Select "Question status count" from the dropdown #1. It should show the pie chart of all authors with count
            new Click().clickbyxpath("(//select[@id='course-question-content-metrics-drop-down']/following-sibling::div/a)[1]");
            new Click().clickbylinkText("Question Status Count");
            String qestionStatusCount= new TextFetch().textfetchbyid("cms-course-question-metrics-chart");
            if(qestionStatusCount==null || qestionStatusCount.equals(""))
                Assert.fail(" Question Status Count value in pie chart present on showCountOfProductDesigner page is blank or null");

        } catch (Exception e) {
            e.printStackTrace();

            Assert.fail("Exception in testcases showCountOfProductDesigner for theDefaultViewOnSummaryPage"+e);
        }
    }
    @Test(priority = 4,enabled = true)
    public void showCountOfCurrentLoggedCMEUser()
    {
        try {
            //Driver.startDriver();
            //Row n0:18 #1. Login as a CMS user (Admin / PD)
            new DBConnect().Connect();
            ResultSet rs=DBConnect.st.executeQuery("select id,username,password,firstname,lastname from t_user where id in (SELECT user_id FROM t_course_user where course_id = 253) and id in(select user_id from t_user_role where type = 'ROLE_SUBJECT_MATTER_EXPERT') limit 1,1;");
            String id="";
            String username="";
            String firstname="";
            String lastname="";
            String fullname="";
            while (rs.next())
            {
                id=rs.getString("id");
                username=rs.getString("username");
                firstname=rs.getString("firstname");
                lastname=rs.getString("lastname");
            }
            System.out.println("UserName : " + username);
            DBConnect.st.executeUpdate("update t_user set password = '6ab14d3be1858b6e8f9f223b6980c7a6dc087a1d4871255f7fc366535762f2a90213d242f2eaa0ab' where id ='"+id+"'");
            new Assignment().create(18);
            new Assignment().addQuestions(18,"multiplechoice","");
            new Assignment().addQuestions(18,"textentry","");
            new Assignment().addQuestions(18,"textdropdown","");
            new DirectLogin().CMSLogin();//login as a CMS USer
            new Click().clickbyxpath("(//select[@name='product-drop-down']/following-sibling::div/a)[1]");
            new Click().clickbylinkText("Learning Space + Adaptive Component");
            //2. Select a course and go to ""Summary"" page
            new SelectCourse().selectcourse();
            new Click().clickByid("deliver-course");
            //#3. Select ""SME"" user from the dropdown filter"

            fullname=lastname+", "+firstname;
            new ComboBox().selectValueByScrollToView("All Authors",fullname);
            //Expected #1. It should show "Difficulty level count" "Pie chart" of all questions count created by the current user
            String difficultLevelCount= new TextFetch().textfetchbyid("cms-course-question-metrics-chart");
            if(difficultLevelCount==null || difficultLevelCount.equals(""))
                Assert.fail("difficultLevelCount value in pie chart present on showCountofCureentLoggedCMEUser page is blank or null"+difficultLevelCount);

            //4. Click on the "Pie chart"

            driver.findElement(By.cssSelector("tspan")).click();

            //Expected #1. It should take to question count per chapter page
            String questionCountPerChap= new TextFetch().textfetchbyid("cms-course-content-metrics-header-title-wrapper");
            Assert.assertEquals(true,questionCountPerChap.contains("Question Count Per Chapter"),"current logged user are not in question count per chapter page");

            Thread.sleep(2000);
            //5. Drill down to TLO level count and click on "Show questions" link
            WebElement element=driver.findElement(By.cssSelector("rect[width='30']"));
            new MouseHover().mouserhoverbywebelement(element);
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span.cms-ccs-show-questions")).click();

            //Expected #1. It should take to question review page with all the questions
            WebDriverWait wait = new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));

            String reviewPage=new TextFetch().textfetchbyclass("cms-content-question-review-list-label");
            Assert.assertEquals(true,reviewPage.equals("Review Content"),"question review page with all the questions is not displayed");

            //6. Select "Bloom's taxonomy count" from the dropdown and select any author
            new Click().clickByid("deliver-course");
            new Click().clickbyxpath("(//select[@id='course-question-content-metrics-drop-down']/following-sibling::div/a)[1]");
            new Click().clickbylinkText("Bloom's Taxonomy Count");

            new  ComboBox().selectValueByScrollToView("All Authors","Pandey, Avinash");
            //Expected #1. It should show the pie chart with count of selected user
            String taxonomyCount= new TextFetch().textfetchbyid("cms-course-question-metrics-chart");
            System.out.println("taxonomyCount"+taxonomyCount);
            if(taxonomyCount==null || taxonomyCount.equals(""))
                Assert.fail(" Bloom's taxonomy count value in pie chart present on showCountofCureentLoggedCMEUser page is blank or null"+taxonomyCount);

            //7. Click on the "Pie chart"
            driver.findElement(By.cssSelector("tspan")).click();
            //Expected #1. It should take to question count per chapter page
            String questionCountPerChap1= new TextFetch().textfetchbyid("cms-course-content-metrics-header-title-wrapper");
            Assert.assertEquals(true,questionCountPerChap1.contains("Question Count Per Chapter"),"current logged user are not in question count per chapter page");
            Thread.sleep(2000);
            //8. Drill down to TLO level count and click on "Show questions" link
            WebElement element1=driver.findElement(By.cssSelector("rect[width='30']"));
            new MouseHover().mouserhoverbywebelement(element1);
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span.cms-ccs-show-questions")).click();//click on the show question
            //Expected #1. It should take to question review page with all the questions
            wait = new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));
            String reviewPage1=new TextFetch().textfetchbyclass("cms-content-question-review-list-label");
            Assert.assertEquals(true,reviewPage1.equals("Review Content"),"question review page1 with all the questions is not displayed");

            //9. Select "Question type count" from the dropdown  and select any author
            new Click().clickByid("deliver-course");
            new Click().clickbyxpath("(//select[@id='course-question-content-metrics-drop-down']/following-sibling::div/a)[1]");
            new Click().clickbylinkText("Question Type Count");
            new ComboBox().selectValueByScrollToViewByXpath("(//select[@id='course-question-author-drop-down']/following-sibling::div/a)[1]","Pandey, Avinash");
            //Expected #1. It should show the question count for each question type of selected user

            /*String query="SELECT tq.question_type,tq.display_type,count(distinct tq.question_version_id)\n" +
                    "     FROM t_assessment ta, t_questions tq, t_question_set tqs, t_question_set_item tqsi, t_category_assessment_map tcam,\n" +
                    "      t_course tcu, t_subject ts,t_category tca\n" +
                    "        where ta.id = tqs.assessment_id and tqs.id = tqsi.question_set_id and tq.id = tqsi.question_id and\n" +
                    "         ta.id = tcam.assessment_id and tca.id = tcam.category_id and tcu.id = ts.course_id and ts.id = tca.subject_id\n" +
                    "         and tq.created_by = 19633 and tcu.id in (253) group by tq.question_type;\n";*/

            String query="select qs.question_type,count(qs.id) as abc from t_questions qs,   (select max(q.id) qid      from t_category_assessment_map casmap         cross join t_category cat        cross join t_question_set qset        cross join t_question_set_item qsitem         cross join t_questions q       where casmap.category_id=cat.id         and cat.course_id =253         and casmap.assessment_id=qset.assessment_id         and qsitem.question_set_id=qset.id         and qsitem.question_id=q.id         and q.template_question_id is null        and (qsitem.is_custom_question_item is null OR qsitem.is_custom_question_item = false)      group by q.question_version_id) qmax      where qs.id = qmax.qid      and qs.created_by =(select id from t_user where username ='avinash.pandey@magicsw.com')    and qs.status <> 90 and qs.status <> 100     group by qs.question_type;";
            new QuestionTypeAndCount().questionTypeCount(query);
            // 10. Click on the "Show questions" link
            new Click().clickbyxpath("//span[text()='Show questions']");

            //Expected #1. It should take to question review page with all the questions
            String reviewPage2=new TextFetch().textfetchbyclass("cms-content-question-review-list-label");
            Assert.assertEquals(true,reviewPage2.equals("Review Content"),"question review page2 with all the questions is not displayed");

            //#11. Select "Question status count" from the dropdown
            new Click().clickByid("deliver-course");
            new Click().clickbyxpath("(//select[@id='course-question-content-metrics-drop-down']/following-sibling::div/a)[1]");
            new Click().clickbylinkText("Question Status Count");

            //Expected #1. It should show the pie chart of all authors with count
            String qestionStatusCount= new TextFetch().textfetchbyid("cms-course-question-metrics-chart");
            if(qestionStatusCount==null || qestionStatusCount.equals(""))
                Assert.fail(" Question Status Count value in pie chart present on showCountofCureentLoggedCMEUser page is blank or null");

            //12. Click on the "Pie chart"
            driver.findElement(By.cssSelector("tspan")).click();

            //Expected #1. It should take to question count per chapter page
            String questionCountPerChap2= new TextFetch().textfetchbyid("cms-course-content-metrics-header-title-wrapper");
            Assert.assertEquals(true,questionCountPerChap2.contains("Question Count Per Chapter"),"current logged user are not in question count per chapter page");
            Thread.sleep(2000);
            //13. Drill down to TLO level count and click on "Show questions" link
            //WebElement element3=driver.findElement(By.cssSelector("rect[height='195']"));
            List<WebElement> element3=driver.findElements(By.cssSelector("rect[fill='rgb(192,192,192)']"));
            new MouseHover().mouserhoverbywebelement(element3.get(element3.size()-1));
            Thread.sleep(9000);
            wait = new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='cms-ccs-show-questions']")));
            new Click().clickBycssselector("span[class='cms-ccs-show-questions']");//click on the show question

            //Expected #1. It should take to question review page with all the questions
            String reviewPage3=new TextFetch().textfetchbyclass("cms-content-question-review-list-label");
            Assert.assertEquals(true,reviewPage3.equals("Review Content"),"question review page3 with all the questions is not displayed");


        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception in testcases showCountofCureentLoggedCMEUser for the DefaultViewOnSummaryPage"+e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void filterOptionDisableForSMEUser()
    {
        try {
            //Driver.startDriver();
            //Row n0:29 "1. Login as a CMS user (SME)
            new DBConnect().Connect();
            ResultSet rs=DBConnect.st.executeQuery("select id,username from t_user where id in (SELECT user_id FROM t_course_user where course_id = 253) and id in(select user_id from t_user_role where type = 'ROLE_SUBJECT_MATTER_EXPERT') limit 1,1;");
            String id="";
            String username="";
            while (rs.next())
            {
                id=rs.getString("id");
                username=rs.getString("username");

            }
            DBConnect.st.executeUpdate("update t_user set password = '6ab14d3be1858b6e8f9f223b6980c7a6dc087a1d4871255f7fc366535762f2a90213d242f2eaa0ab' where id ='"+id+"'");
            new DirectLogin().directLoginWithCreditial(username,"snapwiz",29);
            new Click().clickbyxpath("(//select[@name='product-drop-down']/following-sibling::div/a)[1]");
            new Click().clickbylinkText("Learning Space + Adaptive Component");
            //2. Select a course and go to ""Summary"" page
            new SelectCourse().selectcourse();
            new Click().clickByid("deliver-course");
            //Expected #1. It should not show author filter dropdown
            if(driver.getPageSource().contains("All Authors"))
                Assert.fail("author filter dropdown in  shown in the page");
            //3. Select "Bloom's taxonomy count" from the dropdown
            new UIElement().waitAndFindElement(By.xpath("(//select[@id='course-question-content-metrics-drop-down']/following-sibling::div/a)[1]"));
            new Click().clickbyxpath("(//select[@id='course-question-content-metrics-drop-down']/following-sibling::div/a)[1]");
            new Click().clickbylinkText("Bloom's Taxonomy Count");

            //Expected  #1. It should show the pie chart with count of all users without author filter dropdown
            String countOfUser=new TextFetch().textfetchbycssselector("tspan");
            System.out.println("countOfUser:"+countOfUser);
            if(countOfUser==null || countOfUser.equals(""))
                Assert.fail(" count of all user  in pie chart present on filterOptionDisableForSMEUser page is blank or null");

            //4. Select "Question type count" from the dropdown
            new Click().clickbyxpath("(//select[@id='course-question-content-metrics-drop-down']/following-sibling::div/a)[1]");
            Thread.sleep(2000);
            new Click().clickbylinkText("Question Type Count");
            Thread.sleep(3000);
            //Expected #1. It should show the question count for each question type of all users without author filter dropdown
            new QuestionTypeAndCount().questionTypeCount("select qs.question_type,count(qs.id) as abc from t_questions qs,   (select max(q.id) qid      from t_category_assessment_map casmap         cross join t_category cat        cross join t_question_set qset        cross join t_question_set_item qsitem         cross join t_questions q       where casmap.category_id=cat.id         and cat.course_id =253       and casmap.assessment_id=qset.assessment_id         and qsitem.question_set_id=qset.id         and qsitem.question_id=q.id         and q.template_question_id is null        and (qsitem.is_custom_question_item is null OR qsitem.is_custom_question_item = false)      group by q.question_version_id) qmax      where qs.id = qmax.qid      and qs.status <> 90 and qs.status <> 100     group by qs.question_type ");
            //5. Select "Question status count" from the dropdown
            new UIElement().waitAndFindElement(By.xpath("(//select[@id='course-question-content-metrics-drop-down']/following-sibling::div/a)[1]"));
            new Click().clickbyxpath("(//select[@id='course-question-content-metrics-drop-down']/following-sibling::div/a)[1]");
            new Click().clickbylinkText("Question Status Count");

            //Expected #1. It should show the pie chart with count of all users without author filter dropdown
            String qestionStatusCount=new TextFetch().textfetchbycssselector("tspan");
            System.out.println("qestionStatusCount"+qestionStatusCount);
            if(qestionStatusCount==null || qestionStatusCount.equals(" "))
                Assert.fail(" Question Status Count value in pie chart present on filterOptionDisableForSMEUser page is blank or null"+qestionStatusCount);

        }

        catch (Exception e)
        {
            Assert.fail("Exception in testcase filterOptionDisableForSMEUser of class DefaultViewOnSummaryPage",e);
        }
    }
    @Test(priority = 6,enabled = true)
    public void showNewQuestionForCMSUSer()
    {
        try {
            //Driver.startDriver();
            //Row no 33 1. Login as a CMS user (super admin)
            new DirectLogin().CMSLogin();
            //Create a new question and go to summary page
            new Assignment().create(33);
            new Assignment().addQuestions(33,"multiplechoice","");
            new Assignment().addQuestions(33,"textentry","");
            new Assignment().addQuestions(33,"textdropdown","");
            //new SelectCourse().selectcourse(); //select course
            new Click().clickByid("deliver-course");//click on summary
            //In the author filter select the current logged in author"
            new ComboBox().selectValueByScrollToView("All Authors","Spaces, learning");

        } catch (Exception e) {
            Assert.fail("Exception in testcases showNewQuestionForCMSUSer of class DefaultViewOnSummaryPage",e);
        }
    }

    @Test(priority = 7,enabled = true)
    public void navigateToOtherPage()
    {

        try {
            //Row No:49 "1. Login as a CMS user (super admin / PD)
            //Driver.startDriver();
            new DirectLogin().CMSLogin(); //CMS login as author
            // 2. Select a course and go to ""Summary"" page
            new SelectCourse().selectcourse(); //select course
            Thread.sleep(3000);
            new Click().clickByid("deliver-course");//click on the summary link
            //3. Select any author from the dropdown filter"
            new  ComboBox().selectValueByScrollToView("All Authors","Pandey, Avinash");
            //Expected #1. It should show "Difficulty level count" "Pie chart" of all questions count created by selected user
            String difficultLevelCount= new TextFetch().textfetchbyid("cms-course-question-metrics-chart");
            if(difficultLevelCount==null || difficultLevelCount.equals(""))
                Assert.fail("difficultLevelCount value in pie chart present on navigateTootherPage page is blank or null"+difficultLevelCount);

            // 4. Navigate to any other page and come back to "Summary" page
            new Click().clickbyxpath("//li[text()='Manage Content']");
            new Click().clickByid("deliver-course");
            //Expected #1. By default "All authors" and "Difficulty level count" should be selected
            Thread.sleep(3000);
            new UIElement().waitAndFindElement(By.linkText("All Authors"));
            String allAuthorLinkText= new TextFetch().textfetchbylinktext("All Authors");
            Assert.assertEquals("All Authors",allAuthorLinkText,"By Default  All Authors are not selected");
            String diffLevelCountText=new TextFetch().textfetchbyxpath("(//a[text()='Difficulty Level Count'])[1]");
            Thread.sleep(5000);
            System.out.println("diffLevelCountText"+diffLevelCountText);
            Assert.assertEquals("Difficulty Level Count",diffLevelCountText,"By Default Difficulty Level Count is not selected ");

        } catch (Exception e) {
            Assert.fail("Exception in testcases navigateTootherPage for the class DefaultViewOnSummaryPage ",e);
        }
    }

}
