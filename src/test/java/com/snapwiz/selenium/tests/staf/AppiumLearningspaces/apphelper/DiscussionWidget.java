package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.util.Arrays;
import java.util.List;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.ShareWith;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.ReadTestData;

public class DiscussionWidget {

	public void navigateToTab(int zerobasedindex)
	{
		try
		{
			List<WebElement> allTabs = Driver.driver.findElements(By.cssSelector("span[class='publisher-count ls-publisherIcons-bg ls-publisher-count-bg-holder']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allTabs.get(zerobasedindex));
			Thread.sleep(3000);
			
		}
		catch(Exception e)
		{
			new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in navigateToTab in apphelper DiscussionWidget.",e);
		}
	}
	public void enableOrDisableDWQuestion(int zerobasedindex)
	{
		try
		{
			List<WebElement> allEnableDisableIcon = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'select-question ls-publisherIcons-bg')]"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allEnableDisableIcon.get(zerobasedindex));
			Thread.sleep(3000);

		}
		catch(Exception e)
		{
			new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in navigateToTab in apphelper DiscussionWidget.",e);
		}
	}
	public void addTabInDW(String questionText)
	{
		try
		{
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("span.ls-discussion-widget-publisherIcons-bg.ls-discussion-widget-publisher-addCount-bg")));	//click on + icon to add tabs
			Thread.sleep(2000);
			List<WebElement> widgetdefaulttext = Driver.driver.findElements(By.cssSelector("div[class='widget-content']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", widgetdefaulttext.get(widgetdefaulttext.size()-1));//click on default content
			Thread.sleep(3000);
			//String str = new RandomString().randomstring(25);
			WebElement t=Driver.driver.findElement(By.className("text-iframe")); 
			Driver.driver.switchTo().frame(t);
			Actions ac = new Actions(Driver.driver);
			for(int i=0;i<10;i++)
				ac.sendKeys(Keys.DELETE);
			Driver.driver.findElement(By.xpath("/html/body")).sendKeys(questionText);
			Driver.driver.switchTo().defaultContent();
			Thread.sleep(2000);
			Driver.driver.findElement(By.xpath("/html")).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in addTabInDW in apphelper DiscussionWidget.",e);
		}
	}
	public void assignDiscussionWidgetWithDefaultClassSection(String dataIndex,String... testData)
	{
		try
		{
			String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
			String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
			String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);
			String gradable = ReadTestData.readDataByTagName("", "gradable", dataIndex);
			String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", dataIndex);
			String totalpoints = ReadTestData.readDataByTagName("", "totalpoints", dataIndex);
			/*WebElement we = Driver.driver.findElement(By.cssSelector("span[class='widget discussion-widget ls-publisher-tabs-ins-border']"));
			new MouseHover();
			MouseHover.mouserhoverbywebelement(we);*/
			if(duedate==null){
				duedate = "28";
			}

			if(additionalnote==null){
				additionalnote = "This is an additional note";
			}








            new UIElement().waitAndFindElement(By.cssSelector("span.assign-this-text"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("span.assign-this-text")));
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label

            if(gradable !=null) {
				System.out.println("Its not null");
				if (gradable.equals("true")) {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ir-ls-assign-dialog-dw-gradable-label-check ")));    //check gradable chekbox
                    Thread.sleep(1000);
                    Driver.driver.findElement(By.id("total-points")).sendKeys(totalpoints);  //enter Total Points
                }
            }else{
					if(Arrays.asList(testData).contains("gradable")){
						((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ir-ls-assign-dialog-dw-gradable-label-check ")));    //check gradable chekbox
						Thread.sleep(1000);
						if(totalpoints==null){
							totalpoints = "1";
						}
						Driver.driver.findElement(By.id("total-points")).sendKeys(totalpoints);  //enter Total Points
					}

			}
			Driver.driver.findElement(By.id("due-time")).click();//click on due time
			Driver.driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
			/*List<WebElement> elements = Driver.driver.findElements(By.xpath("//li"));//fetch all time section
			for(WebElement time : elements)
			{
				if(time.getText().equals(duetime))
				{
					time.click();
					break;
				}
			}*/
			Driver.driver.findElement(By.id("due-date")).click();//click on due date
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("a[title='Next']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(duedate)).click();//click on specified date
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("additional-notes")).click();
		    Driver.driver.switchTo().activeElement().sendKeys(additionalnote);//add additional note
			Thread.sleep(15000);
		    Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
		    Thread.sleep(1000);
            new UIElement().waitAndFindElement(By.className("assessmentStatus"));
        }
		catch(Exception e)
		{
			Assert.fail("Exceptiion in apphelper method assignlessonwithdefaultclassection in class  DiscussionWidget",e);
		}
	}
    public void assignDiscussionWidget(String dataIndex)
    {
        try
        {
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
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("span.assign-this-text")));
            Thread.sleep(3000);
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,true);
            Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            Thread.sleep(2000);
            if(gradable !=null) {
                if (gradable.equals("true")) {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ir-ls-assign-dialog-dw-gradable-label-check ")));    //check gradable chekbox
                    Thread.sleep(3000);
                    Driver.driver.findElement(By.id("total-points")).sendKeys(totalpoints);  //enter Total Points
                }
            }

            Driver.driver.findElement(By.id("due-time")).click();//click on due time
			Driver.driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();

            Driver.driver.findElement(By.id("due-date")).click();//click on due date
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("a[title='Next']")).click();
            Thread.sleep(2000);
            Driver.driver.findElement(By.linkText(duedate)).click();//click on specified date
            Thread.sleep(2000);
            Driver.driver.findElement(By.id("additional-notes")).click();
            Driver.driver.switchTo().activeElement().sendKeys(additionalnote);//add additional note
            Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
            Thread.sleep(5000);

        }
        catch(Exception e)
        {
            Assert.fail("Exceptiion in apphelper method assignDiscussionWidget in class  DiscussionWidget",e);
        }
    }

	public void addPerspectiveForDWAssignment(String perspective)
	{
		try
		{
			new WebDriverWait(Driver.driver,180).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
			new Click().clickBycssselector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']"); //click on the perspective
			new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
			WebElement allPerspectives = Driver.driver.findElement(By.xpath("//div[starts-with(@id,'ls-add-perspective-expand')]"));
			allPerspectives.sendKeys(perspective + Keys.RETURN);
			List<WebElement> post=Driver.driver.findElements(By.className("post-perspective"));
			for(WebElement posts:post){
				if(posts.isDisplayed()){
					posts.click();
				}
			}
			Thread.sleep(2000);
			//Validating the added perspective
			boolean perspectivefound = false;
			List<WebElement> perspectivetexts = Driver.driver.findElements(By.className("ls-comment-entry"));
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


    public void openAddedPerspectiveForDWAssignment(int dataIndex)
    {
        try
        {
            CurrentAssignments currentAssignments = PageFactory.initElements(Driver.driver, CurrentAssignments.class);
            com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.eTextbook.Discussion discussion= PageFactory.initElements(Driver.driver, com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.eTextbook.Discussion.class);
            currentAssignments.getLessonAssignment().click();
            new UIElement().waitAndFindElement(By.partialLinkText("Perspectives"));
            discussion.getPerspectives().click();
            discussion.getLink_enterSubmission().click();
            if (!discussion.getTab_discussion().isDisplayed()) {
                Assert.fail("The Assignment is not opened in a new tab");
            }

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
			
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			List<WebElement> allComments = Driver.driver.findElements(By.cssSelector("a[class='ls-content-post__footer-comment-link js-toggle-comments']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allComments.get(perspectiveIndex));	//click on Comment
			Thread.sleep(3000);
			Driver.driver.switchTo().activeElement().sendKeys(commentText+Keys.RETURN);
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
			
			List<WebElement> allComments = Driver.driver.findElements(By.cssSelector("a[class='ls-stream-post__footer-comment-link js-toggle-comments']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allComments.get(1));	//click on Comment
			Thread.sleep(3000);
			Driver.driver.switchTo().activeElement().sendKeys(commentText+Keys.RETURN);
			List<WebElement> allComments1 = Driver.driver.findElements(By.cssSelector("div[class='ls-stream-post__comment-text']"));
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
			Actions action = new Actions(Driver.driver);
			List<WebElement> we = Driver.driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
			action.moveToElement(we.get(dataIndex)).build().perform();  
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("idb-grade-now-link")));	//click on Enter Grade
			Thread.sleep(3000);
			Driver.driver.findElement(By.cssSelector("input[class='idb-grade-points']")).clear();
			Thread.sleep(2000);
			Driver.driver.switchTo().activeElement().sendKeys(grade);	//enter grade
			Driver.driver.findElement(By.cssSelector("body")).click();//click outside
			//Driver.driver.findElement(By.className("gradeBook-question-content-header")).click();	//click outside
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
            new Click().clickBycssselector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']"); //click on the perspective
            new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
			List<WebElement> allComments = Driver.driver.findElements(By.cssSelector("a[class='ls-content-post__footer-comment-link js-toggle-comments']"));
			for(WebElement l: allComments)
			{
				System.out.println("-->"+l.getText());
			}
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allComments.get(perspectiveIndex));	//click on Comment
			Thread.sleep(3000);
			Driver.driver.findElement(By.name("perspective-comment")).sendKeys(commentText+Keys.RETURN);
			//Driver.driver.switchTo().activeElement().sendKeys(commentText+Keys.RETURN);
			List<WebElement> allCommentText = Driver.driver.findElements(By.className("ls-perspctive-comments-posted"));
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
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[title='Perspectives']")));
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("textarea[name='perspective']")));
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("textarea[name='perspective']")).sendKeys(perspective+Keys.RETURN);
			//Validating the added perspective
			boolean perspectivefound = false;
			List<WebElement> perspectivetexts = Driver.driver.findElements(By.className("ls-comment-entry"));
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
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[title='Perspectives']")));
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("textarea[name='perspective']")));
			Thread.sleep(2000);
			for(int i = 0; i< howManyPerspective; i++)
			{
				String perspective = new RandomString().randomstring(15);
				Driver.driver.findElement(By.cssSelector("textarea[name='perspective']")).sendKeys(perspective+Keys.RETURN);
				//Validating the added perspective
				boolean perspectivefound = false;
				List<WebElement> perspectivetexts = Driver.driver.findElements(By.className("ls-comment-entry"));
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
            List<WebElement> allHover = Driver.driver.findElements(By.cssSelector("div[class='ls-dropdown__toggle']"));

            MouseHover.mouserhoverbywebelement(allHover.get(hoverIndex));
            List<WebElement> allArrows2 = Driver.driver.findElements(By.cssSelector("div[class='ls-dropdown__toggle']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allArrows2.get(arrowIndex)); //click on arrow icon for perspective
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
            List<WebElement> allHover = Driver.driver.findElements(By.cssSelector("li[class='ls-stream-post-comment']"));
            MouseHover.mouserhoverbywebelement(allHover.get(hoverIndex));
            List<WebElement> allArrows2 = Driver.driver.findElements(By.cssSelector("div[class='ls-dropdown__toggle']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allArrows2.get(arrowIndex)); //click on arrow icon for perspective
            Thread.sleep(2000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-perspective-hide")));//hide the Perspective
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
            List<WebElement> allHover = Driver.driver.findElements(By.cssSelector("li[class='ls-stream-post-comment']"));
            MouseHover.mouserhoverbywebelement(allHover.get(hoverIndex));
            List<WebElement> allArrows2 = Driver.driver.findElements(By.cssSelector("div[class='ls-dropdown__toggle']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allArrows2.get(arrowIndex)); //click on arrow icon for perspective
            Thread.sleep(2000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-perspective-report-abuse")));//click on Report Abuse
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
            String removePer = (String)((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",Driver.driver.findElement(By.className("ls-perspective-hide")));
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
            String removeComment = (String)((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",Driver.driver.findElement(By.className("ls-hide-comment")));
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
            List<WebElement> allHover = Driver.driver.findElements(By.cssSelector("div[class='ls-perspctive-comments-posted']"));
            MouseHover.mouserhoverbywebelement(allHover.get(hoverIndex));
            String removeComment = Driver.driver.findElement(By.className("ls-hide-comment")).getText();
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
            List<WebElement> allHover = Driver.driver.findElements(By.cssSelector("li[class='ls-stream-post-comment']"));
            MouseHover.mouserhoverbywebelement(allHover.get(hoverIndex));
            String removePer = Driver.driver.findElement(By.className("ls-perspective-hide")).getText();
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
            String reportAbuse = (String)((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",Driver.driver.findElement(By.className("ls-perspective-report-abuse")));
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
            String reportAbuse = (String)((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",Driver.driver.findElement(By.className("ls-perspective-report-abuse")));
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
            String reportAbuse = (String)((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",Driver.driver.findElement(By.className("ls-hide-comment")));
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
            String reportAbuse = (String)((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",Driver.driver.findElement(By.className("perspective-abuse-reported")));
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
            List<WebElement> allHover = Driver.driver.findElements(By.cssSelector("li[class='ls-stream-post-comment']"));
            MouseHover.mouserhoverbywebelement(allHover.get(hoverIndex));
            List<WebElement> allArrows2 = Driver.driver.findElements(By.cssSelector("div[class='ls-dropdown__toggle']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allArrows2.get(arrowIndex)); //click on arrow icon for comment
            Thread.sleep(2000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-hide-comment")));//hide the Perspective
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
            Actions action = new Actions(Driver.driver);
            List<WebElement> we = Driver.driver.findElements(By.cssSelector("span[class='idb-gradebook-question-content']"));
            action.moveToElement(we.get(dataIndex)).build().perform();
			//Thread.sleep(5000);
			new WebDriverWait(Driver.driver, 300).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-view-response-link")));	//click on View Response
            Thread.sleep(5000);
            Driver.driver.findElement(By.id("view-user-question-performance-feedback-box")).sendKeys(feedbackText);
            Driver.driver.findElement(By.className("view-user-discussion-performance-save-btn")).click();	//click Save
            Thread.sleep(3000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper provideFeedbackToStudent in class DiscussionWidget",e);
        }
    }
}
