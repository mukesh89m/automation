package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ShareWith;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

public class DiscussionWidget extends Driver {

    public void navigateToTab(int zerobasedindex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            List<WebElement> allTabs = driver.findElements(By.cssSelector("span[class='publisher-count ls-publisherIcons-bg ls-publisher-count-bg-holder']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allTabs.get(zerobasedindex));
            Thread.sleep(3000);

        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in navigateToTab in apphelper DiscussionWidget.",e);
        }
    }
    public void enableOrDisableDWQuestion(int zerobasedindex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            List<WebElement> allEnableDisableIcon = driver.findElements(By.xpath("//*[starts-with(@class, 'select-question ls-publisherIcons-bg')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allEnableDisableIcon.get(zerobasedindex));
            Thread.sleep(3000);

        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in navigateToTab in apphelper DiscussionWidget.",e);
        }
    }
    public void addTabInDW(String questionText)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.ls-discussion-widget-publisherIcons-bg.ls-discussion-widget-publisher-addCount-bg")));	//click on + icon to add tabs
            Thread.sleep(2000);
            List<WebElement> widgetdefaulttext = driver.findElements(By.cssSelector("div[class='widget-content']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", widgetdefaulttext.get(widgetdefaulttext.size()-1));//click on default content
            Thread.sleep(3000);
            //String str = new RandomString().randomstring(25);
            WebElement t=driver.findElement(By.className("text-iframe"));
            driver.switchTo().frame(t);
            Actions ac = new Actions(driver);
            for(int i=0;i<10;i++)
                ac.sendKeys(Keys.DELETE);
            driver.findElement(By.xpath("/html/body")).sendKeys(questionText);
            driver.switchTo().defaultContent();
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html")).click();
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in addTabInDW in apphelper DiscussionWidget.",e);
        }
    }

    public void assignDiscussionWidgetWithDefaultClassSection(int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            String gradable = ReadTestData.readDataByTagName("", "gradable", Integer.toString(dataIndex));
            String defaultDuetime = ReadTestData.readDataByTagName("", "defaultDuetime", Integer.toString(dataIndex));
            String totalpoints = ReadTestData.readDataByTagName("", "totalpoints", Integer.toString(dataIndex));

            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("span.assign-this-text")),50);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.assign-this-text")));
            Thread.sleep(2000);
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")),50);



            if (defaultDuetime != null) {
                if (defaultDuetime.equals("true")) {
                    new UIElement().waitAndFindElement(By.id("due-time"));
                    DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
                    Calendar now = Calendar.getInstance();
                    //add minutes to current date using Calendar.add method
                    now.add(Calendar.MINUTE, Integer.parseInt(duetime));
                    String calenderFormat = dateFormat.format(now.getTime());
                    System.out.println("calenderFormat:" + calenderFormat);

                    JavascriptExecutor jse = (JavascriptExecutor)driver;
                    jse.executeScript("window.scrollBy(0,250)", "");
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("due-time"))); //click on due time
                    WebElement ele=driver.findElement(By.id("due-time"));//click on dur time
                    ele.sendKeys(calenderFormat);
                    Thread.sleep(2000);
                    new KeysSend().sendKeyBoardKeys("^");
                    Thread.sleep(2000);
                    driver.findElement(By.id("due-date")).click();
                    Thread.sleep(5000);
                    List<WebElement> defaultsDate = driver.findElements(By.cssSelector("a[class='ui-state-default ui-state-highlight ui-state-hover']"));
                    for (WebElement defaultDate : defaultsDate) {
                        if (defaultDate.isDisplayed()) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", defaultDate);
                            break;
                        }
                    }
                }
            }

            else {
                new UIElement().waitAndFindElement(By.id("due-time"));
                JavascriptExecutor jse = (JavascriptExecutor)driver;
                jse.executeScript("window.scrollBy(0,250)", "");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("due-time"))); //click on due time
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[text()='07:30 PM']")));

                WebDriverUtil.waitForAjax(driver,60);
                try {
                    WebDriverUtil.executeJavascript("$('#due-date').focus()");
                }
                catch (Exception e){
                    WebDriverUtil.executeJavascript("$('#due-date').focus()");
                }
                Thread.sleep(4000);
                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("a[title='Next']")));
                Thread.sleep(2000);
                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.linkText(duedate)));


            }



            if(gradable !=null) {
                if (gradable.equals("true")) {
                    Thread.sleep(1000);
                    driver.findElement(By.id("total-points")).sendKeys(totalpoints);  //enter Total Points
                }else{
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-dw-gradable-label-check selected']")));    //check gradable checkbox
                }
            }


            driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional no
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")),50);
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")));
            Thread.sleep(5000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper method assignlessonwithdefaultclassection in class  DiscussionWidget",e);
        }
    }

    public void assignDiscussionWidget(String dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
            String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);
            String gradable = ReadTestData.readDataByTagName("", "gradable", dataIndex);
            String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", dataIndex);
            String totalpoints = ReadTestData.readDataByTagName("", "totalpoints", dataIndex);
            String  shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
            String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            Thread.sleep(5000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.assign-this-text")));
            Thread.sleep(3000);
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,true);
            Thread.sleep(2000);
            if(gradable !=null) {
                if (gradable.equals("true")) {
/*
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ir-ls-assign-dialog-dw-gradable-label-check ")));    //check gradable chekbox
*/
                    Thread.sleep(3000);
                    driver.findElement(By.id("total-points")).sendKeys(totalpoints);  //enter Total Points
                }
            }

            driver.findElement(By.id("due-time")).click();//click on due time
            driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();

            driver.findElement(By.id("due-date")).click();//click on due date
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("a[title='Next']")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText(duedate)).click();//click on specified date
            Thread.sleep(2000);
            driver.findElement(By.id("additional-notes")).click();
            driver.switchTo().activeElement().sendKeys(additionalnote);//add additional note
            driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
            Thread.sleep(5000);

        }
        catch(Exception e)
        {
            Assert.fail("Exceptiion in apphelper method assignDiscussionWidget in class  DiscussionWidget",e);
        }
    }
    public void assignDiscussionWidgetWithGroup(int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            String gradable = ReadTestData.readDataByTagName("", "gradable", Integer.toString(dataIndex));
            String studentName = ReadTestData.readDataByTagName("", "studentName", Integer.toString(dataIndex));
            String totalpoints = ReadTestData.readDataByTagName("", "totalpoints", Integer.toString(dataIndex));
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
            String defaultDuetime = ReadTestData.readDataByTagName("", "defaultDuetime", Integer.toString(dataIndex));
            System.out.println(totalpoints);
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("span.assign-this-text")),50);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.assign-this-text")));
            Thread.sleep(2000);
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")),50);
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,4400);"); //click on due time

            List<WebElement> allClassSection = driver.findElements(By.cssSelector("li[class='bit-box']"));
            for (WebElement classSection : allClassSection) {
                WebDriverUtil.clickOnElementUsingJavascript(driver.findElements(By.className("closebutton")).get(driver.findElements(By.className("closebutton")).size() - 1));//click on close symbol
                String assignToField = new TextFetch().textfetchbyclass("holder");
                if (assignToField.length() == 0) {
                    break;
                }
            }

            driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
            Thread.sleep(2000);
            new KeysSend().sendKeyBoardKeys("^");
            Thread.sleep(2000);
            new KeysSend().sendKeyBoardKeys("^");
            if (studentName != null) {
                driver.findElement(By.className("maininput")).sendKeys(studentName);
                Thread.sleep(2000);
                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.xpath("//ul[@id ='share-with_feed']//li")));
            }

            if (defaultDuetime != null) {
                if (defaultDuetime.equals("true")) {
                    new UIElement().waitAndFindElement(By.id("due-time"));
                    DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
                    Calendar now = Calendar.getInstance();
                    //add minutes to current date using Calendar.add method
                    now.add(Calendar.MINUTE, Integer.parseInt(duetime));
                    String calenderFormat = dateFormat.format(now.getTime());
                    System.out.println("calenderFormat:" + calenderFormat);

                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("due-time"))); //click on due time
                    WebElement ele=driver.findElement(By.id("due-time"));//click on dur time
                    ele.sendKeys(calenderFormat);
                    Thread.sleep(2000);
                    new KeysSend().sendKeyBoardKeys("^");
                    Thread.sleep(2000);
                    driver.findElement(By.id("due-date")).click();//click on due date
                    Thread.sleep(5000);
                    List<WebElement> defaultsDate = driver.findElements(By.cssSelector("a[class='ui-state-default ui-state-highlight ui-state-hover']"));
                    for (WebElement defaultDate : defaultsDate) {
                        if (defaultDate.isDisplayed()) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", defaultDate);
                            break;
                        }
                    }
                }
            }

            else {
                new UIElement().waitAndFindElement(By.id("due-time"));
                driver.findElement(By.id("due-time")).click();
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[text()='07:30 PM']")));

                driver.findElement(By.id("due-date")).click();
                new UIElement().waitAndFindElement(By.linkText(duedate));
                driver.findElement(By.cssSelector("a[title='Next']")).click();
                Thread.sleep(2000);
                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.linkText(duedate)));


            }
            if(gradable !=null) {
                if (gradable.equals("true")) {
                    Thread.sleep(3000);
                    System.out.println("inside ttp");
                    driver.findElement(By.id("total-points")).sendKeys(totalpoints);  //enter Total Points
                }
            }

            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("additional-notes")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].value='"+additionalnote+"';", driver.findElement(By.id("additional-notes"))); //click on due time

            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")),50);
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")));
            Thread.sleep(5000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper method assignlessonwithdefaultclassection in class  DiscussionWidget",e);
        }
    }
    public void addPerspectiveForDWAssignment(String perspective)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
            new Click().clickBycssselector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']"); //click on the perspective
            Thread.sleep(4000);
            new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
            WebElement allPerspectives = driver.findElement(By.xpath("//div[starts-with(@id,'ls-add-perspective-expand')]"));
            allPerspectives.sendKeys(perspective+Keys.RETURN);
            List<WebElement> post=driver.findElements(By.className("post-perspective"));
            for(WebElement posts:post){
                if(posts.isDisplayed()){
                    WebDriverUtil.clickOnElementUsingJavascript(posts);
                }
            }
            Thread.sleep(2000);
            //Validating the added perspective
            boolean perspectivefound = false;
            List<WebElement> perspectivetexts = driver.findElements(By.className("ls-comment-entry"));
            for(WebElement perspectivetext : perspectivetexts)
            {
                if(perspectivetext.getText().equals(perspective))
                {
                    perspectivefound = true;
                    break;
                }
            }
            if(perspectivefound == false)
                Assert.fail("Perspective not added successfully");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper addPerspectiveForDWAssignment in class DiscussionWidget",e);
        }
    }
    public void addPerspectiveOfDiscussionAssignment(String perspective)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            //new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
            //((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Perspectives']")));
            WebElement allPerspectives = driver.findElement(By.xpath("//div[starts-with(@id,'ls-add-perspective-expand')]"));
            allPerspectives.sendKeys(perspective+Keys.RETURN);
            Thread.sleep(3000);
            List<WebElement> post=driver.findElements(By.className("post-perspective"));
            for(WebElement posts:post){
                if(posts.isDisplayed()){
                    WebDriverUtil.clickOnElementUsingJavascript(posts);
                }
                //Validating the added perspective
            }
            Thread.sleep(2000);
            boolean perspectiveFound = false;
            List<WebElement> perspectiveTexts = driver.findElements(By.className("ls-comment-entry"));
            for(WebElement perspectiveText : perspectiveTexts)
            {
                if(perspectiveText.getText().equals(perspective))
                {
                    perspectiveFound = true;
                    break;
                }
            }
            if(perspectiveFound == false)
                Assert.fail("Perspective not added successfully");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper addPerspectiveOfDiscussionAssignment in class DiscussionWidget",e);
        }
    }

    public void openAddedPerspectiveForDWAssignment(int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion= PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
            currentAssignments.getLessonAssignment().click();
            new UIElement().waitAndFindElement(By.partialLinkText("Perspectives"));
            discussion.getPerspectives().click();
            discussion.getLink_enterSubmission().click();
            Thread.sleep(3000);
            if (!discussion.getTab_discussion().isDisplayed()) {
                Assert.fail("The Assignment is not opened in a new tab");
            }

        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper openAddedPerspectiveForDWAssignment in class DiscussionWidget",e);
        }
    }
    public void openPerspectiveForDWAssignment(int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
            new Click().clickBycssselector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']"); //click on the perspective
            new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission

        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper openAddedPerspectiveForDWAssignment in class DiscussionWidget",e);
        }
    }


    public void commentOnPerspective(String commentText, int perspectiveIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
            Thread.sleep(3000);
            List<WebElement> allComments = driver.findElements(By.cssSelector("a[class='ls-content-post__footer-comment-link js-toggle-comments']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allComments.get(perspectiveIndex));	//click on Comment
            Thread.sleep(3000);
            driver.switchTo().activeElement().sendKeys(commentText+Keys.RETURN);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper commentOnPerspective in class DiscussionWidget",e);
        }
    }

    public void commentOnPerspectives(String commentText, int perspectiveIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            Thread.sleep(3000);
            List<WebElement> allComments = driver.findElements(By.cssSelector("a[class='ls-content-post__footer-comment-link js-toggle-comments']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allComments.get(perspectiveIndex));	//click on Comment
            Thread.sleep(3000);
            driver.switchTo().activeElement().sendKeys(commentText+Keys.RETURN);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper commentOnPerspective in class DiscussionWidget",e);
        }
    }
    public void commentOnDWFromGradingPage(String commentText)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            List<WebElement> allComments = driver.findElements(By.cssSelector("a[class='ls-stream-post__footer-comment-link js-toggle-comments']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allComments.get(1));	//click on Comment
            Thread.sleep(3000);
            driver.switchTo().activeElement().sendKeys(commentText+Keys.RETURN);
            List<WebElement> allComments1 = driver.findElements(By.cssSelector("div[class='ls-stream-post__comment-text']"));
            if(!allComments1.get(allComments1.size()-1).getText().contains(commentText))
            {
                Assert.fail("Comment is not posted successfully for DW assignment from grading page.");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper commentOnDWFromGradingPage in class DiscussionWidget",e);
        }
    }

    public void provideGradeToStudent(int dataIndex, String grade)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            Actions action = new Actions(driver);
            List<WebElement> we = driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
            action.moveToElement(we.get(dataIndex)).build().perform();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("idb-grade-now-link")));	//click on Enter Grade
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("input[class='idb-grade-points']")).clear();
            Thread.sleep(2000);
            driver.switchTo().activeElement().sendKeys(grade);	//enter grade
            driver.findElement(By.cssSelector("body")).click();//click outside
            //driver.findElement(By.className("gradeBook-question-content-header")).click();	//click outside
            Thread.sleep(3000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper provideGradeToStudent in class DiscussionWidget",e);
        }
    }

    //student add comment from DW assignment page
    public void studentCommentOnPerspective(String commentText, int perspectiveIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            new Click().clickBycssselector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']"); //click on the perspective
            new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
            List<WebElement> allComments = driver.findElements(By.cssSelector("a[class='ls-content-post__footer-comment-link js-toggle-comments']"));
            for(WebElement l: allComments)
            {
                System.out.println("-->"+l.getText());
            }
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allComments.get(perspectiveIndex));	//click on Comment
            Thread.sleep(3000);
            driver.findElement(By.name("perspective-comment")).sendKeys(commentText+Keys.RETURN);
            //driver.switchTo().activeElement().sendKeys(commentText+Keys.RETURN);
            List<WebElement> allCommentText = driver.findElements(By.className("ls-perspctive-comments-posted"));
            boolean found = false;
            for(int i = 0; i<allCommentText.size(); i++)
            {
                if(!allCommentText.get(i).getText().contains(commentText))
                {
                    found = false;
                }
                else
                {
                    found = true;
                    break;
                }

            }
            if(found == false)
                Assert.fail("Comment is not added successfully to the perspective by the student.");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper studentCommentOnPerspective in class DiscussionWidget",e);
        }
    }
    public void addPerspectiveForDWIneTextBook(String perspective)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Perspectives']")));
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//textarea[starts-with(@name,'ls-add-perspective')]")));
            Thread.sleep(2000);
            List<WebElement> post= driver.findElements(By.xpath("//div[starts-with(@id,'ls-add-perspective')]"));
            for(WebElement posts : post){

                if(posts.isDisplayed())
                    driver.findElement(By.xpath("//div[starts-with(@id,'ls-add-perspective')]")).sendKeys(perspective+Keys.RETURN);

            }
            //Validating the added perspective
            boolean perspectivefound = false;
            List<WebElement> perspectivetexts = driver.findElements(By.className("ls-comment-entry"));
            for(WebElement perspectivetext : perspectivetexts)
            {
                if(perspectivetext.getText().equals(perspective))
                {
                    perspectivefound = true;
                    break;
                }
            }
            if(perspectivefound == false)
                Assert.fail("Perspective not added successfully from eTextBook.");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper addPerspectiveForDWAssignment in class DiscussionWidget",e);
        }
    }
    public void addMultiplePerspectiveForDWIneTextBook(int howManyPerspective)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Perspectives']")));
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("textarea[name='perspective']")));
            Thread.sleep(2000);
            for(int i = 0; i< howManyPerspective; i++)
            {
                String perspective = new RandomString().randomstring(15);
                driver.findElement(By.cssSelector("textarea[name='perspective']")).sendKeys(perspective+Keys.RETURN);
                //Validating the added perspective
                boolean perspectivefound = false;
                List<WebElement> perspectivetexts = driver.findElements(By.className("ls-comment-entry"));
                for(WebElement perspectivetext : perspectivetexts)
                {
                    if(perspectivetext.getText().equals(perspective))
                    {
                        perspectivefound = true;
                        break;
                    }
                }
                if(perspectivefound == false)
                    Assert.fail("Perspective not added successfully from eTextBook.");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper addMultiplePerspectiveForDWIneTextBook in class DiscussionWidget",e);
        }
    }

    //click on Arrow icon, for Perspective and Comment (both)
    public void clickOnArrowIconForPerspective(int arrowIndex, int hoverIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            List<WebElement> allHover = driver.findElements(By.cssSelector("div[class='ls-dropdown__toggle']"));

            new MouseHover().mouserhoverbywebelement(allHover.get(hoverIndex));
            List<WebElement> allArrows2 = driver.findElements(By.cssSelector("div[class='ls-dropdown__toggle']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allArrows2.get(arrowIndex)); //click on arrow icon for perspective
            Thread.sleep(2000);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper clickOnArrowIconForPerspective in class DiscussionWidget",e);
        }
    }
    public void removePerspective(int arrowIndex, int hoverIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            List<WebElement> allHover = driver.findElements(By.cssSelector("li[class='ls-stream-post-comment']"));
            new MouseHover().mouserhoverbywebelement(allHover.get(hoverIndex));
            List<WebElement> allArrows2 = driver.findElements(By.cssSelector("div[class='ls-dropdown__toggle']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allArrows2.get(arrowIndex)); //click on arrow icon for perspective
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-perspective-hide")));//hide the Perspective
            Thread.sleep(3000);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper removePerspective in class DiscussionWidget",e);
        }
    }
    public void reportAbuseForPerspective(int arrowIndex, int hoverIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            List<WebElement> allHover = driver.findElements(By.cssSelector("li[class='ls-stream-post-comment']"));
            new MouseHover().mouserhoverbywebelement(allHover.get(hoverIndex));
            List<WebElement> allArrows2 = driver.findElements(By.cssSelector("div[class='ls-dropdown__toggle']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allArrows2.get(arrowIndex)); //click on arrow icon for perspective
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-perspective-report-abuse")));//click on Report Abuse
            Thread.sleep(3000);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper reportAbuseForPerspective in class DiscussionWidget",e);
        }
    }
    public boolean removePerspectiveTextVerify()
    {
        boolean isRemovePerspectiveTextPresent = false;
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String removePer = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",driver.findElement(By.className("ls-perspective-hide")));
            System.out.println("removePer: "+removePer);
            if(removePer.contains("Remove Perspective"))
            {
                isRemovePerspectiveTextPresent = true;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper removePerspectiveTextVerify in class DiscussionWidget",e);
        }
        return isRemovePerspectiveTextPresent;
    }
    public boolean removeCommentTextVerify()
    {
        boolean isRemoveCommentTextPresent = false;
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String removeComment = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",driver.findElement(By.className("ls-hide-comment")));
            System.out.println("removeComment: "+removeComment);
            if(removeComment.contains("Remove Comment"))
            {
                isRemoveCommentTextPresent = true;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper removeCommentTextVerify in class DiscussionWidget",e);
        }
        return isRemoveCommentTextPresent;
    }
    public boolean removeCommentTextNotPresentVerify(int hoverIndex)
    {
        boolean isRemoveCommentTextPresent = false;
        try
        {
            WebDriver driver=Driver.getWebDriver();
            List<WebElement> allHover = driver.findElements(By.cssSelector("div[class='ls-perspctive-comments-posted']"));
            new MouseHover().mouserhoverbywebelement(allHover.get(hoverIndex));
            String removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
            System.out.println("removeComment: "+removeComment);
            if(removeComment.contains("Remove Comment"))
            {
                isRemoveCommentTextPresent = true;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper removeCommentTextVerify in class DiscussionWidget",e);
        }
        return isRemoveCommentTextPresent;
    }
    public boolean removePerspectiveTextNotPresentVerify(int hoverIndex)
    {
        boolean isRemovePerspectiveTextPresent = false;
        try
        {
            WebDriver driver=Driver.getWebDriver();
            List<WebElement> allHover = driver.findElements(By.cssSelector("li[class='ls-stream-post-comment']"));
            new MouseHover().mouserhoverbywebelement(allHover.get(hoverIndex));
            String removePer = driver.findElement(By.className("ls-perspective-hide")).getText();
            System.out.println("removePer: "+removePer);
            if(removePer.contains("Remove Perspective"))
            {
                isRemovePerspectiveTextPresent = true;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper removePerspectiveTextNotPresentVerify in class DiscussionWidget",e);
        }
        return isRemovePerspectiveTextPresent;
    }
    public boolean reportAbuseTextVerifyForPerspective()
    {
        boolean isReportAbuseTextPresent = false;
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String reportAbuse = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",driver.findElement(By.className("ls-perspective-report-abuse")));
            System.out.println("reportAbuse: "+reportAbuse);
            if(reportAbuse.contains("Report Abuse"))
            {
                isReportAbuseTextPresent = true;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper reportAbuseTextVerifyForPerspective in class DiscussionWidget",e);
        }
        return isReportAbuseTextPresent;
    }
    public boolean reportAbuseTextVerifyForComment()
    {
        boolean isReportAbuseTextPresent = false;
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String reportAbuse = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",driver.findElement(By.className("ls-perspective-report-abuse")));
            System.out.println("reportAbuse: "+reportAbuse);
            if(reportAbuse.contains("Report Abuse"))
            {
                isReportAbuseTextPresent = true;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper reportAbuseTextVerifyForPerspective in class DiscussionWidget",e);
        }
        return isReportAbuseTextPresent;
    }
    public boolean abuseReportedTextVerifyForComment()
    {
        boolean isAbuseReportedTextPresent = false;
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String reportAbuse = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",driver.findElement(By.className("ls-hide-comment")));
            System.out.println("reportAbuseComment: "+reportAbuse);
            if(reportAbuse.contains("Abuse Reported"))
            {
                isAbuseReportedTextPresent = true;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper abuseReportedTextVerifyForComment in class DiscussionWidget",e);
        }
        return isAbuseReportedTextPresent;
    }
    public boolean abuseReportedTextVerifyForPerspective()
    {
        boolean isAbuseReportedTextPresent = false;
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String reportAbuse = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",driver.findElement(By.className("perspective-abuse-reported")));
            System.out.println("reportAbuse: "+reportAbuse);
            if(reportAbuse.contains("Abuse Reported"))
            {
                isAbuseReportedTextPresent = true;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper abuseReportedTextVerifyForComment in class DiscussionWidget",e);
        }
        return isAbuseReportedTextPresent;
    }
    public void removeComment(int arrowIndex, int hoverIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            List<WebElement> allHover = driver.findElements(By.cssSelector("li[class='ls-stream-post-comment']"));
            new MouseHover().mouserhoverbywebelement(allHover.get(hoverIndex));
            List<WebElement> allArrows2 = driver.findElements(By.cssSelector("div[class='ls-dropdown__toggle']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allArrows2.get(arrowIndex)); //click on arrow icon for comment
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-hide-comment")));//hide the Perspective
            Thread.sleep(3000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper removeComment in class DiscussionWidget",e);
        }
    }

    public void provideFeedbackToStudent(int dataIndex, String feedbackText)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            Actions action = new Actions(driver);
            List<WebElement> we = driver.findElements(By.cssSelector("span[class='idb-gradebook-question-content']"));
            action.moveToElement(we.get(dataIndex)).build().perform();
            new WebDriverWait(driver, 300).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link")));	//click on View Response
            Thread.sleep(5000);
            driver.findElement(By.id("view-user-question-performance-feedback-box")).sendKeys(feedbackText);
            driver.findElement(By.className("view-user-discussion-performance-save-btn")).click();	//click Save
            Thread.sleep(3000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper provideFeedbackToStudent in class DiscussionWidget",e);
        }
    }

    public void provideFeedbackAndScoreToStudent(String dataIndex)
    {
        try
        {
            String feedbackText = ReadTestData.readDataByTagName("", "feedbackText", dataIndex);
            String score = ReadTestData.readDataByTagName("", "score", dataIndex);
            WebDriver driver=Driver.getWebDriver();
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.className("idb-question-manually-graded")),15);
            WebDriverUtil.mouseHover(By.className("idb-question-manually-graded"));
            Thread.sleep(5000);
            new WebDriverUtil().clickOnElementUsingJavascript(currentAssignments.getViewResponseLink());
            currentAssignments.getPerformanceScoreBox().sendKeys(score);
            currentAssignments.getFeedBack_textBox().click();
            driver.switchTo().activeElement().sendKeys(feedbackText);
            currentAssignments.getDwSave_button().click();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper provideFeedbackAndScoreToStudent in class DiscussionWidget",e);
        }
    }



}
