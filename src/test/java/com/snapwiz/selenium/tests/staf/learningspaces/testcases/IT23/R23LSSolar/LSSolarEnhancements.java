package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R23LSSolar;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyNotes.MyNote;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
//import com.snapwiz.selenium.tests.staf.orion.apphelper.CreateChapterTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

/*
 * Created by Snapwiz on 15/09/15.
 */
public class LSSolarEnhancements extends Driver {
    @Test(priority = 1, enabled = true)
    public void verifyInstructorSideImpacts(){
        try{
            String dataIndex = "15";
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            new Assignment().create(Integer.parseInt(dataIndex));
            dataIndex = "15_1";
            String classSection1 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);
            dataIndex = "15_2";
            String classSection2 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);
            dataIndex = "15_3";
            String classSection3 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);
            String contextTite = ReadTestData.readDataByTagName("", "context_title", dataIndex);

            new UIElement().waitAndFindElement(By.linkText(contextTite));
            try{
                driver.findElement(By.linkText(contextTite));
            }catch(Exception e){
                Assert.fail("1.It should land on dash board.");
            }

            new Navigator().NavigateTo("eTextbook");
            driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).click();
            driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).sendKeys("it");
            new UIElement().waitAndFindElement(By.linkText(assessmentName));

            try {
                driver.findElement(By.linkText(assessmentName)).click();
            }catch(Exception e){
                Assert.fail("It should display all the results starting with 'cell' as auto suggest below the search field.");
            }

            new UIElement().waitAndFindElement(By.cssSelector("a[title = '"+assessmentName+"']"));

            try {
                driver.findElement(By.cssSelector("a[title = '"+assessmentName+"']"));
            }catch(Exception e){
                Assert.fail("#.It should display the results regarding to options selected.");
            }


            //Assign a lesson
            new Navigator().NavigateTo("eTextbook");
            new Click().clickByclassname("ls-inner-arw");
            new UIElement().waitAndFindElement(By.cssSelector("div[class='toc-assign-this open-assign-window']"));
            new Click().clickBycssselector("div[class='toc-assign-this open-assign-window']");

            List<WebElement> itemList = driver.findElements(By.className("item-text"));
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(3).getText().equals(classSection2)&&itemList.get(4).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }


            for(int a =0; a<5;a++){
                itemList = driver.findElements(By.className("closebutton"));
                System.out.println("itemList : " + itemList.size());
                driver.findElement(By.className("closebutton")).click();
                Thread.sleep(1000);
            }

            new UIElement().waitAndFindElement(By.className("maininput"));
            driver.findElement(By.className("maininput")).sendKeys("stu");
            new UIElement().waitAndFindElement(By.className("auto-suggest-element"));
            itemList = driver.findElements(By.xpath("//li[contains(@class,'auto-suggest-element')]"));

            for(int a =0; a<itemList.size();a++){
                System.out.println("2: " + itemList.get(a).getText());
            }
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(1).getText().equals(classSection2)&&itemList.get(2).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }


            itemList.get(2).click();
            new Click().clickByclassname("assign-different-dates");
            new UIElement().waitAndFindElement(By.className("add-row-button"));
            new Click().clickByclassname("add-row-button");
            new Click().clickByclassname("closebutton");
            new UIElement().waitAndFindElement(By.className("holder"));
            new TextSend().textsendbyclass("stu", "maininput");
            itemList = driver.findElements(By.xpath("//li[contains(@class,'auto-suggest-element')]"));

            for(int a =0; a<itemList.size();a++){
                System.out.println("2: " + itemList.get(a).getText());
            }
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(1).getText().equals(classSection2)&&itemList.get(2).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }
            itemList.get(2).click();

        }catch(Exception e){
            Assert.fail("Exception in testscript 'verifyInstructorSideImpacts' in class 'EnhancementToLessonWidth'", e);
        }
    }


    @Test(priority = 2, enabled = true)
    public void assignDiscussionWidget(){
        try{
            String dataIndex = "22";
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            new Assignment().create(Integer.parseInt(dataIndex));
            dataIndex = "22_1";
            String classSection1 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);
            dataIndex = "22_2";
            String classSection2 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);
            dataIndex = "22_3";
            String classSection3 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);
            String contextTite = ReadTestData.readDataByTagName("", "context_title", dataIndex);

            new Navigator().NavigateTo("eTextBook");

            new TopicOpen().lessonOpen(11, 3);
            Thread.sleep(3000);
            new UIElement().waitAndFindElement(By.cssSelector("span.assign-this-text"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.assign-this-text")));

            //Assign a lesson

            List<WebElement> itemList = driver.findElements(By.className("item-text"));
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(3).getText().equals(classSection2)&&itemList.get(4).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }


            for(int a =0; a<5;a++){
                itemList = driver.findElements(By.className("closebutton"));
                System.out.println("itemList : " + itemList.size());
                driver.findElement(By.className("closebutton")).click();
                Thread.sleep(1000);
            }

            new UIElement().waitAndFindElement(By.className("maininput"));
            driver.findElement(By.className("maininput")).sendKeys("stu");
            new UIElement().waitAndFindElement(By.className("auto-suggest-element"));
            itemList = driver.findElements(By.xpath("//li[contains(@class,'auto-suggest-element')]"));

            for(int a =0; a<itemList.size();a++){
                System.out.println("2: " + itemList.get(a).getText());
            }
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(1).getText().equals(classSection2)&&itemList.get(2).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }


            itemList.get(2).click();
            new Click().clickByclassname("assign-different-dates");
            new UIElement().waitAndFindElement(By.className("add-row-button"));
            new Click().clickByclassname("add-row-button");
            new Click().clickByclassname("closebutton");
            new UIElement().waitAndFindElement(By.className("holder"));
            new TextSend().textsendbyclass("stu", "maininput");
            itemList = driver.findElements(By.xpath("//li[contains(@class,'auto-suggest-element')]"));

            for(int a =0; a<itemList.size();a++){
                System.out.println("2: " + itemList.get(a).getText());
            }
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(1).getText().equals(classSection2)&&itemList.get(2).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }
            itemList.get(2).click();

        }catch(Exception e){
            Assert.fail("Exception in testscript 'verifyInstructorSideImpacts' in class 'EnhancementToLessonWidth'", e);
        }
    }




    @Test(priority = 3, enabled = true)
    public void assignThroughBulkAssignment(){
        try{
            String dataIndex = "26_1";
            String classSection1 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);
            dataIndex = "26_2";
            String classSection2 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);
            dataIndex = "26_3";
            String classSection3 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);
            String contextTite = ReadTestData.readDataByTagName("", "context_title", dataIndex);

            new Navigator().NavigateTo("eTextBook");
            new Click().clickBycssselector("div[title = 'Assign all lessons in this chapter']");
            new UIElement().waitAndFindElement(By.cssSelector("span[class = 'btn sty-green submit-assign']"));
            new Click().clickBycssselector("span[class = 'btn sty-green submit-assign']");

            Thread.sleep(3000);
            new UIElement().waitAndFindElement(By.className("item-text"));
            List<WebElement> itemList = driver.findElements(By.className("item-text"));
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(3).getText().equals(classSection2)&&itemList.get(4).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }


            for(int a =0; a<5;a++){
                itemList = driver.findElements(By.className("closebutton"));
                System.out.println("itemList : " + itemList.size());
                driver.findElement(By.className("closebutton")).click();
                Thread.sleep(1000);
            }

            new UIElement().waitAndFindElement(By.className("maininput"));
            driver.findElement(By.className("maininput")).sendKeys("stu");
            new UIElement().waitAndFindElement(By.className("auto-suggest-element"));
            itemList = driver.findElements(By.xpath("//li[contains(@class,'auto-suggest-element')]"));

            for(int a =0; a<itemList.size();a++){
                System.out.println("2: " + itemList.get(a).getText());
            }
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(1).getText().equals(classSection2)&&itemList.get(2).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }


            itemList.get(2).click();
            new Click().clickByclassname("assign-different-dates");
            new UIElement().waitAndFindElement(By.className("add-row-button"));
            new Click().clickByclassname("add-row-button");
            new Click().clickByclassname("closebutton");
            new UIElement().waitAndFindElement(By.className("holder"));
            new TextSend().textsendbyclass("stu", "maininput");
            itemList = driver.findElements(By.xpath("//li[contains(@class,'auto-suggest-element')]"));

            for(int a =0; a<itemList.size();a++){
                System.out.println("2: " + itemList.get(a).getText());
            }
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(1).getText().equals(classSection2)&&itemList.get(2).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }
            itemList.get(2).click();

        }catch(Exception e){
            Assert.fail("Exception in testscript 'assignThroughBulkAssignment' in class 'EnhancementToLessonWidth'", e);
        }
    }


    @Test(priority = 4, enabled = true)
    public void assignThroughAssignmentCart(){
        try{
            String dataIndex = "30_1";
            String classSection1 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);
            dataIndex = "30_2";
            String classSection2 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);
            dataIndex = "30_3";
            String classSection3 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);

            new Navigator().NavigateTo("eTextBook");
            new TopicOpen().lessonOpen(2, 1);
            new UIElement().waitAndFindElement(By.cssSelector("span.assign-options"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.assign-options")));
            new UIElement().waitAndFindElement(By.cssSelector("span[title = 'Add to Activity']"));
            new Click().clickBycssselector("span[title = 'Add to Activity']");
            new UIElement().waitAndFindElement(By.cssSelector("span[title = 'Resources']"));
            new Click().clickBycssselector("span[title = 'Resources']");
            new UIElement().waitAndFindElement(By.className("ls-resource-show-assign-this-block"));
            new Click().clickByclassname("ls-resource-show-assign-this-block");

            new UIElement().waitAndFindElement(By.cssSelector("span[class = 'ls-right-tab-hover-sprite add-to-cart-resource-bg']"));
            new Click().clickBycssselector("span[class = 'ls-right-tab-hover-sprite add-to-cart-resource-bg']");

            new UIElement().waitAndFindElement(By.className("assignment-cart-notifications"));
            new Click().clickByclassname("assignment-cart-notifications");
            new UIElement().waitAndFindElement(By.cssSelector("span[class='btn sty-green submit-assign']"));
            new Click().clickBycssselector("span[class='btn sty-green submit-assign']");



            Thread.sleep(3000);
            new UIElement().waitAndFindElement(By.className("item-text"));
            List<WebElement> itemList = driver.findElements(By.className("item-text"));
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(3).getText().equals(classSection2)&&itemList.get(4).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }


            for(int a =0; a<5;a++){
                itemList = driver.findElements(By.className("closebutton"));
                System.out.println("itemList : " + itemList.size());
                driver.findElement(By.className("closebutton")).click();
                Thread.sleep(1000);
            }

            new UIElement().waitAndFindElement(By.className("maininput"));
            driver.findElement(By.className("maininput")).sendKeys("stu");
            new UIElement().waitAndFindElement(By.className("auto-suggest-element"));
            itemList = driver.findElements(By.xpath("//li[contains(@class,'auto-suggest-element')]"));

            for(int a =0; a<itemList.size();a++){
                System.out.println("2: " + itemList.get(a).getText());
            }
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(1).getText().equals(classSection2)&&itemList.get(2).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }


            itemList.get(2).click();
            new Click().clickByclassname("assign-different-dates");
            new UIElement().waitAndFindElement(By.className("add-row-button"));
            new Click().clickByclassname("add-row-button");
            new Click().clickByclassname("closebutton");
            new UIElement().waitAndFindElement(By.className("holder"));
            new TextSend().textsendbyclass("stu", "maininput");
            itemList = driver.findElements(By.xpath("//li[contains(@class,'auto-suggest-element')]"));

            for(int a =0; a<itemList.size();a++){
                System.out.println("2: " + itemList.get(a).getText());
            }
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(1).getText().equals(classSection2)&&itemList.get(2).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }
            itemList.get(2).click();

        }catch(Exception e){
            Assert.fail("Exception in testscript 'assignThroughAssignmentCart' in class 'EnhancementToLessonWidth'", e);
        }
    }






    @Test(priority = 6, enabled = true)
    public void assignAssignrmntFromAssignmentTab(){
        try{
            String dataIndex = "34_1";
            String classSection1 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);
            dataIndex = "34_2";
            String classSection2 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);
            dataIndex = "34_3";
            String classSection3 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);

            new Navigator().NavigateTo("eTextBook");
            new TopicOpen().lessonOpen(2, 1);
            new UIElement().waitAndFindElement(By.cssSelector("span[title = 'Assignments']"));
            new Click().clickBycssselector("span[title = 'Assignments']");

            List<WebElement> assignList = driver.findElements(By.className("assignment-content-posts-list"));
            for(int a=0;a<assignList.size();a++){
                if(assignList.get(a).getText().contains("Pre-lecture - The Study of Life")){
                    assignList.get(a).findElement(By.tagName("a")).click();
                }
            }


            /*new UIElement().waitAndFindElement(By.className("ls-resource-show-assign-this-block"));
            new Click().clickByclassname("ls-resource-show-assign-this-block");*/

            new UIElement().waitAndFindElement(By.cssSelector("span[class = 'ls-assign-this-sprite-right-tab assign-resource-bg']"));
            new Click().clickBycssselector("span[class = 'ls-assign-this-sprite-right-tab assign-resource-bg']");

            Thread.sleep(3000);
            new UIElement().waitAndFindElement(By.className("item-text"));
            List<WebElement> itemList = driver.findElements(By.className("item-text"));
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(3).getText().equals(classSection2)&&itemList.get(4).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }


            for(int a =0; a<5;a++){
                itemList = driver.findElements(By.className("closebutton"));
                System.out.println("itemList : " + itemList.size());
                driver.findElement(By.className("closebutton")).click();
                Thread.sleep(1000);
            }

            new UIElement().waitAndFindElement(By.className("maininput"));
            driver.findElement(By.className("maininput")).sendKeys("stu");
            new UIElement().waitAndFindElement(By.className("auto-suggest-element"));
            itemList = driver.findElements(By.xpath("//li[contains(@class,'auto-suggest-element')]"));

            for(int a =0; a<itemList.size();a++){
                System.out.println("2: " + itemList.get(a).getText());
            }
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(1).getText().equals(classSection2)&&itemList.get(2).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }


            itemList.get(2).click();
            new Click().clickByclassname("assign-different-dates");
            new UIElement().waitAndFindElement(By.className("add-row-button"));
            new Click().clickByclassname("add-row-button");
            new Click().clickByclassname("closebutton");
            new UIElement().waitAndFindElement(By.className("holder"));
            new TextSend().textsendbyclass("stu", "maininput");
            itemList = driver.findElements(By.xpath("//li[contains(@class,'auto-suggest-element')]"));

            for(int a =0; a<itemList.size();a++){
                System.out.println("2: " + itemList.get(a).getText());
            }
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(1).getText().equals(classSection2)&&itemList.get(2).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }
            itemList.get(2).click();

        }catch(Exception e){
            Assert.fail("Exception in testscript 'assignResourceFromResourceTab' in class 'EnhancementToLessonWidth'", e);
        }
    }







    @Test(priority = 5, enabled = true)
    public void assignResourceFromResourceTab(){
        try{
            String dataIndex = "38_1";
            String classSection1 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);
            dataIndex = "38_2";
            String classSection2 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);
            dataIndex = "38_3";
            String classSection3 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);

            new Navigator().NavigateTo("eTextBook");
            new TopicOpen().lessonOpen(2, 1);
            new UIElement().waitAndFindElement(By.cssSelector("span[title = 'Resources']"));
            new Click().clickBycssselector("span[title = 'Resources']");

            List<WebElement> assignList = driver.findElements(By.className("resource-content-posts-list"));
            for(int a=0;a<assignList.size();a++){
                if(assignList.get(a).getText().contains("Chapter 01: Art PowerPoint")){
                    assignList.get(a).findElement(By.tagName("a")).click();
                }
            }


            /*new UIElement().waitAndFindElement(By.className("ls-resource-show-assign-this-block"));
            new Click().clickByclassname("ls-resource-show-assign-this-block");*/

            new UIElement().waitAndFindElement(By.cssSelector("span[class = 'ls-assign-this-sprite-right-tab assign-resource-bg']"));
            new Click().clickBycssselector("span[class = 'ls-assign-this-sprite-right-tab assign-resource-bg']");

            Thread.sleep(3000);
            new UIElement().waitAndFindElement(By.className("item-text"));
            List<WebElement> itemList = driver.findElements(By.className("item-text"));
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(3).getText().equals(classSection2)&&itemList.get(4).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }


            for(int a =0; a<5;a++){
                itemList = driver.findElements(By.className("closebutton"));
                System.out.println("itemList : " + itemList.size());
                driver.findElement(By.className("closebutton")).click();
                Thread.sleep(1000);
            }

            new UIElement().waitAndFindElement(By.className("maininput"));
            driver.findElement(By.className("maininput")).sendKeys("stu");
            new UIElement().waitAndFindElement(By.className("auto-suggest-element"));
            itemList = driver.findElements(By.xpath("//li[contains(@class,'auto-suggest-element')]"));

            for(int a =0; a<itemList.size();a++){
                System.out.println("2: " + itemList.get(a).getText());
            }
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(1).getText().equals(classSection2)&&itemList.get(2).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }


            itemList.get(2).click();
            new Click().clickByclassname("assign-different-dates");
            new UIElement().waitAndFindElement(By.className("add-row-button"));
            new Click().clickByclassname("add-row-button");
            new Click().clickByclassname("closebutton");
            new UIElement().waitAndFindElement(By.className("holder"));
            new TextSend().textsendbyclass("stu", "maininput");
            itemList = driver.findElements(By.xpath("//li[contains(@class,'auto-suggest-element')]"));

            for(int a =0; a<itemList.size();a++){
                System.out.println("2: " + itemList.get(a).getText());
            }
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(1).getText().equals(classSection2)&&itemList.get(2).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }
            itemList.get(2).click();

        }catch(Exception e){
            Assert.fail("Exception in testscript 'assignResourceFromResourceTab' in class 'EnhancementToLessonWidth'", e);
        }
    }



    @Test(priority = 6, enabled = true)
    public void assignStaticAssessment(){
        try{
            String dataIndex = "42";
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            new Assignment().create(Integer.parseInt(dataIndex));
            dataIndex = "42_1";
            String classSection1 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);
            dataIndex = "42_2";
            String classSection2 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);
            dataIndex = "42_3";
            String classSection3 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);
            String contextTite = ReadTestData.readDataByTagName("", "context_title", dataIndex);

            new Navigator().NavigateTo("Question Banks");
            new Assignment().searchAssessmentInQuestionBanks(42);
            new Click().clickBycssselector("span[data-atype='static_assessment']");

            /*if(driver.findElement(By.className("span[data-atype='static_assessment']")).isDisplayed()){
                new Click().clickBycssselector("span[data-atype='static_assessment']");
                System.out.println("Yes");
            }*/



            Thread.sleep(3000);
            new UIElement().waitAndFindElement(By.className("item-text"));
            List<WebElement> itemList = driver.findElements(By.className("item-text"));
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(3).getText().equals(classSection2)&&itemList.get(4).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }


            for(int a =0; a<5;a++){
                itemList = driver.findElements(By.className("closebutton"));
                System.out.println("itemList : " + itemList.size());
                driver.findElement(By.className("closebutton")).click();
                Thread.sleep(1000);
            }

            new UIElement().waitAndFindElement(By.className("maininput"));
            driver.findElement(By.className("maininput")).sendKeys("stu");
            new UIElement().waitAndFindElement(By.className("auto-suggest-element"));
            itemList = driver.findElements(By.xpath("//li[contains(@class,'auto-suggest-element')]"));

            for(int a =0; a<itemList.size();a++){
                System.out.println("2: " + itemList.get(a).getText());
            }
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(1).getText().equals(classSection2)&&itemList.get(2).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }


            itemList.get(2).click();
            new Click().clickByclassname("assign-different-dates");
            new UIElement().waitAndFindElement(By.className("add-row-button"));
            new Click().clickByclassname("add-row-button");
            new Click().clickByclassname("closebutton");
            new UIElement().waitAndFindElement(By.className("holder"));
            new TextSend().textsendbyclass("stu", "maininput");
            itemList = driver.findElements(By.xpath("//li[contains(@class,'auto-suggest-element')]"));

            for(int a =0; a<itemList.size();a++){
                System.out.println("2: " + itemList.get(a).getText());
            }
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(1).getText().equals(classSection2)&&itemList.get(2).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }
            itemList.get(2).click();

        }catch(Exception e){
            Assert.fail("Exception in testscript 'verifyInstructorSideImpacts' in class 'EnhancementToLessonWidth'", e);
        }
    }


















    @Test(priority = 7, enabled = true)
    public void assignAdaptivePractice(){
        try{
            String dataIndex = "46";
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            dataIndex = "46_1";
            String classSection1 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);
            dataIndex = "46_2";
            String classSection2 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);
            dataIndex = "46_3";
            String classSection3 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);
            String contextTite = ReadTestData.readDataByTagName("", "context_title", dataIndex);


            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);


            new Navigator().NavigateTo("eTextbook");
            new Click().clickBycssselector("span[title='ORION Adaptive Practice']");
            new UIElement().waitAndFindElement(By.className("ls-inner-arw"));

            List<WebElement> elementsList  = driver.findElements(By.className("ls-inner-arw"));
            for(int a=0;a<elementsList.size();a++){
                if(elementsList.get(a).isDisplayed()){
                    elementsList.get(a+1).click();
                    Thread.sleep(3000);
                    break;
                }
            }

            elementsList  = driver.findElements(By.cssSelector("div[class='toc-assign-this open-assign-window']"));
            for(int a=0;a<elementsList.size();a++){
                if(elementsList.get(a).isDisplayed()){
                    elementsList.get(a).click();
                    Thread.sleep(3000);
                    break;
                }
            }



            //new Click().clickbylist("ls-inner-arw", 2);
            /*new UIElement().waitAndFindElement(By.cssSelector("div[class='toc-assign-this open-assign-window']"));
            new Click().clickBycssselector("div[class='toc-assign-this open-assign-window']");
*/
            Thread.sleep(3000);
            new UIElement().waitAndFindElement(By.className("item-text"));
            List<WebElement> itemList = driver.findElements(By.className("item-text"));
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(3).getText().equals(classSection2)&&itemList.get(4).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }


            for(int a =0; a<5;a++){
                itemList = driver.findElements(By.className("closebutton"));
                System.out.println("itemList : " + itemList.size());
                driver.findElement(By.className("closebutton")).click();
                Thread.sleep(1000);
            }

            new UIElement().waitAndFindElement(By.className("maininput"));
            driver.findElement(By.className("maininput")).sendKeys("stu");
            new UIElement().waitAndFindElement(By.className("auto-suggest-element"));
            itemList = driver.findElements(By.xpath("//li[contains(@class,'auto-suggest-element')]"));

            for(int a =0; a<itemList.size();a++){
                System.out.println("2: " + itemList.get(a).getText());
            }
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(1).getText().equals(classSection2)&&itemList.get(2).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }


            itemList.get(2).click();
            new Click().clickByclassname("assign-different-dates");
            new UIElement().waitAndFindElement(By.className("add-row-button"));
            new Click().clickByclassname("add-row-button");
            new Click().clickByclassname("closebutton");
            new UIElement().waitAndFindElement(By.className("holder"));
            new TextSend().textsendbyclass("stu", "maininput");
            itemList = driver.findElements(By.xpath("//li[contains(@class,'auto-suggest-element')]"));

            for(int a =0; a<itemList.size();a++){
                System.out.println("2: " + itemList.get(a).getText());
            }
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(1).getText().equals(classSection2)&&itemList.get(2).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }
            itemList.get(2).click();





            //Row No - 52 :
            new Navigator().NavigateTo("Question Banks");
            elementsList = driver.findElements(By.cssSelector("span[title = 'Add to My Question Bank']"));
            for(int a=0;a<5;a++){
                elementsList = driver.findElements(By.cssSelector("span[title = 'Add to My Question Bank']"));
                elementsList.get(a).click();
            }


            String stringToSearch = driver.findElement(By.className("resource-title")).getAttribute("title");
            System.out.println("stringToSearch : " + stringToSearch);

            new Navigator().NavigateTo("My Question Bank");
            new Click().clickByid("my-resource-search-textarea");
            new TextSend().textsendbyid(stringToSearch, "my-resource-search-textarea");
            new Click().clickBycssselector("span[class='ins-resource-search-sprite instructor-resource-search-img']");
            new UIElement().waitAndFindElement(By.className("resource-title"));
            String actual = driver.findElement(By.className("resource-title")).getAttribute("title");
            Assert.assertEquals(actual, stringToSearch, "1. It should display all assessments related to search key word.");
            System.out.println("stringToSearch 2 : " + stringToSearch);


            //Row No  53 : Question bank page results and Assign through question bank page.

            new Navigator().NavigateTo("Question Banks");
            new Click().clickByclassname("assign-this");
            checkAssignmentForm(classSection1, classSection2, classSection3);














            //Row No - 60 - Search Assessments/Assignments using My Question bank page.
            new Navigator().NavigateTo("My Question Bank");
            new Click().clickByid("my-resource-search-textarea");
            new TextSend().textsendbyid(stringToSearch, "my-resource-search-textarea");
            new Click().clickBycssselector("span[class='ins-resource-search-sprite instructor-resource-search-img']");
            new UIElement().waitAndFindElement(By.className("resource-title"));
            actual = driver.findElement(By.className("resource-title")).getAttribute("title");
            Assert.assertEquals(actual, stringToSearch, "1. It should display all assessments related to search key word.");
            System.out.println("stringToSearch 2 : " + stringToSearch);

            new Click().clickByclassname("assign-this");
            checkAssignmentForm(classSection1, classSection2, classSection3);

            new Navigator().NavigateTo("My Question Bank");
            myQuestionBank.getCustomAssignmentButton().click();
            newAssignment.tab_newAssignment.click();
            Assert.assertEquals(newAssignment.tab_newAssignment.getText(), "New Assignment", "1.It should land on new assignment tab with search and browse options. ");
            myQuestionBank.button_search.click();
            myQuestionBank.textField_SearchQuestionStatement.sendKeys("Study");
            myQuestionBank.searchButton1.click();

            List<WebElement> questionTextElementsList = driver.findElements(By.className("ls-search-question-text"));
            for(int a=0;a<questionTextElementsList.size();a++){
                if(!(questionTextElementsList.get(a).getText().contains("study")||questionTextElementsList.get(a).getText().contains("Study"))){
                    Assert.fail("1.It should display all the questionsrelated to that search string.(Ex: It should display all the question which contain 'Study' word in question part.')");
                }
            }

            //Row No - 67 : Browse Results
            myQuestionBank.button_browse.click();
            myQuestionBank.getGo_Button().click();
            questionTextElementsList = driver.findElements(By.className("ls-search-question-text"));
            if(!(questionTextElementsList.size()>0)){
                Assert.fail("1.It should display all the question related to browse results.");

            }


            myQuestionBank.checkbox.click();
            myQuestionBank.getAssignmentNameField().click();
            myQuestionBank.clickToEnterAssignmentName.sendKeys("Custom_" + assessmentName);
            myQuestionBank.button_saveForLater.click();
            new Navigator().NavigateTo("My Question Bank");
            myQuestionBank.getAssignThis().click();
            checkAssignmentForm(classSection1, classSection2, classSection3);



            //Row No - 74 : Search Reources in All Resources.
            new Navigator().NavigateTo("All Resources");
            stringToSearch = driver.findElement(By.className("resource-title")).getAttribute("title");
            System.out.println("stringToSearch : " + stringToSearch);

            new Click().clickByid("all-resource-search-textarea");
            new TextSend().textsendbyid("\"" + stringToSearch + "\"", "all-resource-search-textarea");

            new Click().clickBycssselector("span[class='ins-resource-search-sprite instructor-resource-search-img']");
            new UIElement().waitAndFindElement(By.className("resource-title"));
            actual = driver.findElement(By.className("resource-title")).getAttribute("title");
            Assert.assertEquals(actual, stringToSearch, "1. It should display all assessments related to search key word.");


            //Row No -75 :  All Resources tab.
            myQuestionBank.getAssignThis().click();
            checkAssignmentForm(classSection1, classSection2, classSection3);
            myQuestionBank.cancelPopUp.click();



            new Navigator().NavigateTo("All Resources");

            stringToSearch = driver.findElement(By.className("resource-title")).getAttribute("title");
            System.out.println("stringToSearch : " + stringToSearch);


            //Row No - 80  : Search Reources in My Resources.
            for(int a=0;a<5;a++){
                elementsList = driver.findElements(By.cssSelector("span[title = 'Add to My Resources']"));
                elementsList.get(a).click();
            }

            new Navigator().NavigateTo("My Resources");
            new UIElement().waitAndFindElement(By.id("my-resource-search-textarea"));
            new Click().clickByid("my-resource-search-textarea");
            new TextSend().textsendbyid("\"" + stringToSearch + "\"", "my-resource-search-textarea");//Type as assessment name which we would like to search
            new Click().clickBycssselector("span[class='ins-resource-search-sprite instructor-resource-search-img']");
            new UIElement().waitAndFindElement(By.className("resource-title"));
            actual = driver.findElement(By.className("resource-title")).getAttribute("title");
            Assert.assertEquals(actual, stringToSearch, "1. It should display all assessments related to search key word.");
            myQuestionBank.getAssignThis().click();
            checkAssignmentForm(classSection1, classSection2, classSection3);
            myQuestionBank.cancelPopUp.click();


            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.tab_post.click();
            new UIElement().waitAndFindElement(courseStreamPage.sharedName);
            Assert.assertEquals(courseStreamPage.sharedName.getText(),classSection3,"1. In share With the present class-section name is shown by default.");



            //Row No - 86 : 17a Try to search some student of that clas section.
            //Expeceted - #.In Auto suggest it should display all the students name of that class-section.
            //Not Working

        }catch(Exception e){
            Assert.fail("Exception in testscript 'verifyInstructorSideImpacts' in class 'EnhancementToLessonWidth'", e);
        }
    }





    @Test(priority = 8, enabled = true)
    public void validateFromStudentSide(){
        try{
            String dataIndex = "89_1";
            String classSection1 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);

            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            MyNote myNote = PageFactory.initElements(driver, MyNote.class);

            new Navigator().NavigateTo("eTextbook");
            new UIElement().waitAndFindElement(lessonPage.searchETextBook);
            lessonPage.searchETextBook.click();
            Thread.sleep(2000);
            lessonPage.searchETextBook.sendKeys("cell");
            List<WebElement> chapterList = driver.findElements(By.tagName("a"));
            System.out.println("chapterList.size() : " + chapterList.size());
            Thread.sleep(3000);
            for(int a=0;a<chapterList.size();a++){
                if(chapterList.get(a).getAttribute("class").equals("ui-corner-all")){
                    System.out.println("chapterList.get(a): " + chapterList.get(a).getText().trim());
                    if(!chapterList.get(a).getText().startsWith("cell")){
                        Assert.fail("1.It should display all the results starting with 'cell' as auto suggest below the search field.");
                    }

                }
            }



            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.tab_post.click();
            new UIElement().waitAndFindElement(courseStreamPage.sharedName);
            Assert.assertEquals(courseStreamPage.sharedName.getText(), classSection1, "1. In share With the present class-section name is shown by default.");


            new Navigator().NavigateTo("My Notes");
            Assert.assertEquals(myNote.label_myNotes.getText().trim(), "My Notes", "1.It should display my notes page.");

            new AddNote().addNote(dataIndex, true, false, 0);
            String tag = "ThisIsSpecialTag";
            new AddNote().addTag(tag, 0);
            new AddNote().searchtag(tag.substring(0, 3));
            Assert.assertEquals(myNote.autoSuggest.getText(),tag,"#.In Auto suggest it should display all the tags related to that search.");


        }catch(Exception e){
            Assert.fail("Exception in testscript 'verifyInstructorSideImpacts' in class 'EnhancementToLessonWidth'", e);
        }
    }




    @Test(priority = 9, enabled = true)
    public void validateEdgeCases(){
        try{
            String dataIndex = "97_1";
            String classSection1 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);

          /*  dataIndex = "97_3";
            String classSection3 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);

*/

            dataIndex = "97_2";
            String classSection2 = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            String familyName = ReadTestData.readDataByTagName("", "familyname", dataIndex);
            String givenName = ReadTestData.readDataByTagName("", "givenname", dataIndex);

            new LoginUsingLTI().ltiLogin(dataIndex);


            dataIndex = "97_4";
            new LoginUsingLTI().ltiLogin(dataIndex);
            new Navigator().NavigateTo("All Resources");

            String stringToSearch = driver.findElement(By.className("resource-title")).getAttribute("title");
            System.out.println("stringToSearch : " + stringToSearch);


            //Row No - 80  : Search Reources in My Resources.
            ;
            for(int a=0;a<5;a++){
                List<WebElement> elementsList = driver.findElements(By.cssSelector("span[title = 'Add to My Resources']"));
                elementsList.get(a).click();
            }

            new Navigator().NavigateTo("My Resources");
            new UIElement().waitAndFindElement(By.id("my-resource-search-textarea"));
            new Click().clickByid("my-resource-search-textarea");
            new TextSend().textsendbyid("\"" + stringToSearch + "\"", "my-resource-search-textarea");//Type as assessment name which we would like to search
            new Click().clickBycssselector("span[class='ins-resource-search-sprite instructor-resource-search-img']");
            new UIElement().waitAndFindElement(By.className("resource-title"));
            String actual = driver.findElement(By.className("resource-title")).getAttribute("title");
            Assert.assertEquals(actual, stringToSearch, "1. It should display all assessments related to search key word.");
            myQuestionBank.getAssignThis().click();




            for(int a =0; a<3;a++){
                List<WebElement> itemList = driver.findElements(By.className("closebutton"));
                System.out.println("itemList : " + itemList.size());
                driver.findElement(By.className("closebutton")).click();
                Thread.sleep(1000);
            }

            new UIElement().waitAndFindElement(By.className("maininput"));
            driver.findElement(By.className("maininput")).sendKeys("fam");
            new UIElement().waitAndFindElement(By.className("auto-suggest-element"));
            List<WebElement> itemList = driver.findElements(By.xpath("//li[contains(@class,'auto-suggest-element')]"));

            for(int a =0; a<itemList.size();a++){
                System.out.println("2: " + itemList.get(a).getText());
            }
            String a = familyName+", "+givenName+" - "+classSection2;
            System.out.println("a : " + a);
            if(!itemList.get(0).getText().equals(familyName+", "+givenName+" - "+classSection2)){
                Assert.fail("1.It should Auto suggest with new name.");
            }
            itemList.get(0).click();

            new UIElement().waitAndFindElement(By.className("maininput"));
            driver.findElement(By.className("maininput")).sendKeys("fam");
            new UIElement().waitAndFindElement(By.className("auto-suggest-element"));
            itemList = driver.findElements(By.xpath("//li[contains(@class,'auto-suggest-element')]"));

            if(itemList.size()>0){
                Assert.fail("1.It shouls not display student old namw in auto suggest.");
            }




            dataIndex = "97_5";
            new LoginUsingLTI().ltiLogin(dataIndex);


            dataIndex = "97_4";
            new LoginUsingLTI().ltiLogin(dataIndex);
            new Navigator().NavigateTo("All Resources");

            stringToSearch = driver.findElement(By.className("resource-title")).getAttribute("title");
            System.out.println("stringToSearch : " + stringToSearch);


            //Row No - 80  : Search Reources in My Resources.
            ;
            for(int b=0;b<5;b++){
                List<WebElement> elementsList = driver.findElements(By.cssSelector("span[title = 'Add to My Resources']"));
                elementsList.get(b).click();
            }

            new Navigator().NavigateTo("My Resources");
            new UIElement().waitAndFindElement(By.id("my-resource-search-textarea"));
            new Click().clickByid("my-resource-search-textarea");
            new TextSend().textsendbyid("\"" + stringToSearch + "\"", "my-resource-search-textarea");//Type as assessment name which we would like to search
            new Click().clickBycssselector("span[class='ins-resource-search-sprite instructor-resource-search-img']");
            new UIElement().waitAndFindElement(By.className("resource-title"));
            actual = driver.findElement(By.className("resource-title")).getAttribute("title");
            Assert.assertEquals(actual, stringToSearch, "1. It should display all assessments related to search key word.");
            myQuestionBank.getAssignThis().click();








            //checkAssignmentForm(classSection1, classSection2, classSection3);


            /*dataIndex = "97_5";
            new LoginUsingLTI().ltiLogin(dataIndex);


            dataIndex = "97_4";
            new LoginUsingLTI().ltiLogin(dataIndex);

            new Navigator().NavigateTo("All Resources");

            stringToSearch = driver.findElement(By.className("resource-title")).getAttribute("title");
            System.out.println("stringToSearch : " + stringToSearch);


            //Row No - 80  : Search Reources in My Resources.
            for(int a=0;a<5;a++){
                List<WebElement> elementsList  = driver.findElements(By.cssSelector("span[title = 'Add to My Resources']"));
                elementsList.get(a).click();
            }

            new Navigator().NavigateTo("My Resources");
            new UIElement().waitAndFindElement(By.id("my-resource-search-textarea"));
            new Click().clickByid("my-resource-search-textarea");
            new TextSend().textsendbyid("\"" + stringToSearch + "\"", "my-resource-search-textarea");//Type as assessment name which we would like to search
            new Click().clickBycssselector("span[class='ins-resource-search-sprite instructor-resource-search-img']");
            new UIElement().waitAndFindElement(By.className("resource-title"));
            actual = driver.findElement(By.className("resource-title")).getAttribute("title");
            Assert.assertEquals(actual, stringToSearch, "1. It should display all assessments related to search key word.");
            myQuestionBank.getAssignThis().click();
            checkAssignmentForm(classSection1, classSection2, classSection3);
*/

        }catch(Exception e){
            Assert.fail("Exception in testscript 'validateEdgeCases' in class 'EnhancementToLessonWidth'", e);
        }
    }






    public void checkAssignmentForm(String classSection1,String classSection2,String classSection3){
        try{
            Thread.sleep(3000);
            new UIElement().waitAndFindElement(By.className("item-text"));
            List<WebElement> itemList = driver.findElements(By.className("item-text"));
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(3).getText().equals(classSection2)&&itemList.get(4).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }


            for(int a =0; a<5;a++){
                itemList = driver.findElements(By.className("closebutton"));
                System.out.println("itemList : " + itemList.size());
                driver.findElement(By.className("closebutton")).click();
                Thread.sleep(1000);
            }

            new UIElement().waitAndFindElement(By.className("maininput"));
            driver.findElement(By.className("maininput")).sendKeys("stu");
            new UIElement().waitAndFindElement(By.className("auto-suggest-element"));
            itemList = driver.findElements(By.xpath("//li[contains(@class,'auto-suggest-element')]"));

            for(int a =0; a<itemList.size();a++){
                System.out.println("2: " + itemList.get(a).getText());
            }
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(1).getText().equals(classSection2)&&itemList.get(2).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }


            itemList.get(2).click();
            new Click().clickByclassname("assign-different-dates");
            new UIElement().waitAndFindElement(By.className("add-row-button"));
            new Click().clickByclassname("add-row-button");
            new Click().clickByclassname("closebutton");
            new UIElement().waitAndFindElement(By.className("holder"));
            new TextSend().textsendbyclass("stu", "maininput");
            itemList = driver.findElements(By.xpath("//li[contains(@class,'auto-suggest-element')]"));

            for(int a =0; a<itemList.size();a++){
                System.out.println("2: " + itemList.get(a).getText());
            }
            if(!itemList.get(0).getText().equals(classSection1)&&itemList.get(1).getText().equals(classSection2)&&itemList.get(2).getText().equals(classSection3)){
                Assert.fail("1.In 'Assign To' filed it should display all the class-sections by default.(EX:Sec-A, Sec-B, Sec-c)");
            }
            itemList.get(2).click();
        }catch(Exception e){

        }
    }











}
