package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Config;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.TextSend;
/*
 * Brajesh
 * uploaded resources by instructor
 */

public class UploadResources {
	public void uploadResources(String dataIndex, boolean reserveforassignment, boolean reserveforgradedpolic, boolean associteresourcesto) {
		try {
			String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);
			String resourcesname = ReadTestData.readDataByTagName("", "resourcesname", dataIndex);
			String description = ReadTestData.readDataByTagName("", "description", dataIndex);
			String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicystatus", dataIndex);
			String topic = ReadTestData.readDataByTagName("", "topic", dataIndex);
            if(resourcesname != "") {
                if (System.getProperty("UCHAR") == null) {
                    resourcesname = resourcesname + com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.LoginUsingLTI.appendChar;
                } else {
                    resourcesname = resourcesname + System.getProperty("UCHAR");
                }
            }
			new Navigator().NavigateTo("Resources");//navigate to resource
			new Click().clickBycssselector("div[data-id='my-resources']");//click on my resources tab
			new Click().clickByid("upload-resourse-button");//click on upload resources button
            ((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("uploadFile")));
            Thread.sleep(5000);
            new KeysSend().sendKeyBoardKeys("$");
			new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
			Thread.sleep(10000);
			Driver.driver.findElement(By.className("ins-uploadResource-input")).clear();
			Driver.driver.findElement(By.className("ins-uploadResource-input")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("ins-uploadResource-input")).sendKeys(resourcesname);//give resources name
			Driver.driver.findElement(By.className("ins-uploadResource-textbox")).sendKeys(description);//give description
			if (reserveforassignment == true)
				new Click().clickByclassname("ls-instructor-only-assignment-check");//checked reserve for assignment
			if (gradingpolicy != null) {
				if (gradingpolicy.equals("true"))
					new Click().clickByclassname("ls-instructor-only-grading-policy-check");//checked for reserve for graded policy
			}
			if (associteresourcesto == true && topic == null)
				new Click().clickbylistcssselector("div[class='course-chapter-label node']", 0);//select 1st chapter to associate
			if (associteresourcesto == true && topic != null) {
				new Click().clickByclassname("expand-chapter-tree");
				new Click().clickbylistcssselector("div[class='course-topic-label node']", Integer.parseInt(topic));//select topic to associate
			}
			Thread.sleep(20000);
			Driver.driver.findElement(By.className("ins-dialogBox-Save")).click();

		} catch (Exception e) {
			Assert.fail("Exception in apphelper  UploadResources", e);
		}
	}

	public void openUploadResource() {
		try {
			new Navigator().NavigateTo("Resources");//navigate to resource
			new Click().clickBycssselector("div[data-id='my-resources']");//click on my resources tab
			new Click().clickByid("upload-resourse-button");//click on upload resources button
		} catch (Exception e) {
			Assert.fail("Exception in apphelper  UploadResources", e);
		}
	}

	public boolean uploadresourcessuccessfully() {
		boolean success = false;
		try {
			WebElement notification = (new WebDriverWait(Driver.driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.className("ls-notification-text")));
			if (notification.getText().contains("The resource is successfully created.")) {
				success = true;
			}
		} catch (Exception e) {
			Assert.fail("Exception in apphelper  uploadresourcessuccessfully", e);
		}
		return success;
	}

	public void VerifyResourceswithGradingPolicyInetextbook(String resourcename) {
		try {
			new Navigator().NavigateTo("e-Textbook");
			//50--search resources name in search field of etextbook
			new Click().clickBycssselector("input[class='toc-sprite search-course-stream ui-autocomplete-input']");
			new TextSend().textsendbycssSelector(resourcename  , "input[class='toc-sprite search-course-stream ui-autocomplete-input']");
			new Click().clickByclassname("ls-search-icon");
			Thread.sleep(2000);
			boolean searchresult = new BooleanValue().booleanbyclass("ls-search-results-not-found");//verify search result not found
			if (searchresult == false)
				Assert.fail("after search resources name is displayed");
		} catch (Exception e) {
			Assert.fail("Exception in apphelper  VerifyResourceswithGradingPolicyInetextbook", e);
		}
	}

	public void uploadResourcesInCourseLevel(String dataIndex, boolean reserveforassignment, boolean reserveforgradedpolic, boolean associteresourcesto) {
		try {
			String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);
			String resourcesname = ReadTestData.readDataByTagName("", "resourcesname", dataIndex);
			String description = ReadTestData.readDataByTagName("", "description", dataIndex);
			String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicystatus", dataIndex);
			String course = ReadTestData.readDataByTagName("", "course", dataIndex);

			new Navigator().NavigateTo("Resources");//navigate to resource
			new Click().clickBycssselector("div[data-id='my-resources']");//click on my resources tab
			new Click().clickByid("upload-resourse-button");//click on upload resources button
			new Click().clickByid("uploadFile");
			new KeysSend().sendKeyBoardKeys("$");
			new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
			Thread.sleep(10000);
			Driver.driver.findElement(By.className("ins-uploadResource-input")).clear();
			Driver.driver.findElement(By.className("ins-uploadResource-input")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("ins-uploadResource-input")).sendKeys(resourcesname);//give resources name
			Driver.driver.findElement(By.className("ins-uploadResource-textbox")).sendKeys(description);//give description
			if (reserveforassignment == true)
				new Click().clickByclassname("ls-instructor-only-assignment-check");//checked reserve for assignment
			if (gradingpolicy != null) {
				if (gradingpolicy.equals("true"))
					new Click().clickByclassname("ls-instructor-only-grading-policy-check");//checked for reserve for graded policy
			}
			if (associteresourcesto == true )
				new Click().clickbylistcssselector("div[class='course-label node']", 0);//select 1st chapter to associate
			Thread.sleep(20000);
			Driver.driver.findElement(By.className("ins-dialogBox-Save")).click();


		} catch (Exception e) {
			Assert.fail("Exception in apphelper  uploadResourcesInCourseLevel", e);
		}
	}

}