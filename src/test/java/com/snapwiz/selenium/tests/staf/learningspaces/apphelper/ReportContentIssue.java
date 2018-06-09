package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

/*
 * Created by Sumit on 8/2/2014.
 */
public class ReportContentIssue extends Driver {

    //report a content issue on a question in CMS
    public void reportContentIssue(String issueText) {
        try {
            WebDriver driver=Driver.getWebDriver();
            (new WebDriverWait(driver, 300))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title='Report Content Errors']")));
            driver.findElement(By.cssSelector("div[title='Report Content Errors']")).click();//click on report content issue
            Thread.sleep(2000);
            String url = driver.getCurrentUrl();
            if (url.contains("lsStudentDashBoard")) {
                /*driver.findElement(By.cssSelector("div[class='add-content-error show-content-issues-dialog']")).click();//click on report content issue(again clicking because of those help pop-up)
                Thread.sleep(2000);*/
                Thread.sleep(10000);//wait for help pop-up to disappear
                driver.findElement(By.id("text-area-content-issue")).click();//click on report content issue text area
                driver.findElement(By.id("text-area-content-issue")).sendKeys(issueText);//report text
                driver.findElement(By.id("send-content-issue-btn")).click();//click on report content issue send button
                Thread.sleep(3000);
                driver.findElement(By.id("send-content-issue")).click();//click on Yes link on pop-up
                Thread.sleep(3000);
            } else {
                driver.findElement(By.id("text-area-content-issue")).click();//click on report content issue text area
                driver.findElement(By.id("text-area-content-issue")).sendKeys(issueText);//report text
                driver.findElement(By.id("send-content-issue-btn")).click();//click on report content issue send button
                Thread.sleep(3000);
            }
        } catch (Exception e) {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in method reportIssueOnQuestion in class ReportContentIssue", e);
        }
    }

    public void clickOnResolveButton(int index) {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            List<WebElement> allResolveButton = driver.findElements(By.className("content-error-resolved-it-btn"));//click on Resolve button
            allResolveButton.get(index).click();
            Thread.sleep(2000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in method clickOnResolveButton in class ReportContentIssue", e);
        }
    }
    public void clickOnReportContentIssueIcon() {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            driver.findElement(By.cssSelector("div[title='Report Content Errors']")).click();//click on report content issue icon
            Thread.sleep(2000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in method clickOnReportContentIssueIcon in class ReportContentIssue", e);
        }
    }
    public void goToContentErrorReportPage() {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            driver.findElement(By.id("deliver-course")).click();//click on Summary in CMS
            Thread.sleep(2000);
            driver.findElement(By.className("review-details")).click();//click on right arrow icon next to "Content issue reported"
            Thread.sleep(2000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in method goToContentErrorReportPage in class ReportContentIssue", e);
        }
    }
    public boolean isIssuePresentInContentErrorReportPage(String issueText) {
        boolean issuePresent = false;
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String errorText = driver.findElement(By.className("reported-content-issue-cmnts")).getText();
            if(errorText.contains(issueText))
                issuePresent = true;
        }
        catch (Exception e)
        {
            Assert.fail("Exception in method goToContentErrorReportPage in class ReportContentIssue", e);
        }
        return issuePresent;
    }

    public void reportContentIssueForPassageBaseQuestion(String issueText) {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            (new WebDriverWait(driver, 30))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title='Report Content Errors']")));
            List<WebElement> allIcon = driver.findElements(By.cssSelector("div[title='Report Content Errors']"));//click on report content issue
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allIcon.get(1));//click on icon
            Thread.sleep(10000);//wait for help pop-up to disappear
            List<WebElement> textarea = driver.findElements(By.id("text-area-content-issue"));
            textarea.get(1).click();
            textarea.get(1).sendKeys(issueText);//report text
            List<WebElement> sendButtons = driver.findElements(By.id("send-content-issue-btn"));
            sendButtons.get(1).click();//click on report content issue send button
            List<WebElement> yesLinks = driver.findElements(By.id("send-content-issue"));
            yesLinks.get(0).click();//click on Yes link on pop-up
            Thread.sleep(3000);
        } catch (Exception e) {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in method reportContentIssueForPassageBaseQuestion in class ReportContentIssue", e);
        }
    }
}
