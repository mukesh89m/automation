package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Questions extends Driver {

    public void deActivateQuestion(int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String chapterName = ReadTestData.readDataByTagName("","chapterName" ,Integer.toString(dataIndex));
            String assignmentName = ReadTestData.readDataByTagName("","assignmentName" , Integer.toString(dataIndex));
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();//select course
            new SelectCourse().selectchapter(chapterName);//select chapter
            new SelectCourse().selectassignment(assignmentName);//select assignment
            driver.findElement(By.cssSelector("span[class='question-link-labels  ']")).click();//click on question
            Thread.sleep(2000);
            driver.findElement(By.id("questionOptions")).click();//click on question option
            Thread.sleep(2000);
            driver.findElement(By.id("questionRevisions")).click();//click on revision link
            Thread.sleep(3000);
            driver.findElement(By.id("cms-question-revision-deactivate-button")).click();
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in App helper deActivateQuestion in class Questions",e);
        }
    }

    public void duplicateQuestion(int dataIndex)
    {
        try
        {
            String chapterName = ReadTestData.readDataByTagName("","chapterName" ,Integer.toString(dataIndex));
            String assignmentName = ReadTestData.readDataByTagName("","assignmentName" , Integer.toString(dataIndex));
            new SelectCourse().selectcourse();//select course
            new SelectCourse().selectchapter(chapterName);//select chapter
            new SelectCourse().selectassignment(assignmentName);//select assignment
            new UIElement().waitAndFindElement(By.xpath("(//span[text() = 'New'])[2]"));
            new Click().clickbyxpath("(//span[text() = 'New'])[2]");
            //new Click().clickbyxpath(".//*[@id='question-label-wrapper']/span[2]");
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in Method duplicateQuestion in class Questions",e);
        }
    }


    public void navigateToQuestionPage(int dataIndex){
        try {
            String questiontext = ReadTestData.readDataByTagName("","questiontext" ,Integer.toString(dataIndex));
            System.out.println("questiontext : " + questiontext);
            new UIElement().waitAndFindElement(By.id("content-search-icon"));
            new Click().clickByid("content-search-icon");//Click on 'Search' Icons
            new TextSend().textsendbyid((questiontext), "content-search-field");// Type text 'test' in search field
            new Click().clickByid("search-question-contents-icon");// Cick on 'Go' Button
            Thread.sleep(2000);
        }catch(Exception e){
            Assert.fail("Exception in Method 'navigateToQuestionPage' in class 'Questions'",e);
        }
    }

    public void copySecondQuestionWithOutReferenceToOtherCourse(int dataIndex){
        try {
            WebDriver driver=Driver.getWebDriver();
            new UIElement().waitAndFindElement(By.xpath("//div[@id = 'question-check-box-div']//label"));
            new Click().clickbylistxpath("//div[@id = 'question-check-box-div']//label", 0);//Select a second question
            new Click().clickbyxpath("//div[@id = 'content-search-copy-btn']//img[@id='content-search-icon']");//Click on Copy Button
            new Click().clickbylistxpath(".//*[@id='contentSearchSelectChapterSlider']//a[contains(@id,'sbSelector_')]",0);//Click on a 'Select Course'
            String course = Config.lscourse;
            WebElement element=driver.findElement(By.xpath("//a[text()='"+course+"']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(5000);
            new Click().clickbylinkText(course);//Select a  Course
            new Click().clickbylistxpath(".//*[@id='contentSearchSelectChapterSlider']//a[contains(@id,'sbSelector_')]",1);//Click on a 'Select Chapter'
            new Click().clickbypartiallinkText("Ch 2");//Select a 2nd Chapter
            new Click().clickbyxpath("//div[@id='copy-ref-check-box-div']/label");//Uncheck Check box 'copy as reference only
            new Click().clickbylistxpath("//div[@class='content-left-assessment-section']//label",0);//Click on 1st assessment
            driver.findElement(By.id("add-assesments-in-contentSearch")).click();
            Thread.sleep(2000);
        }catch(Exception e){
            Assert.fail("Exception in Method 'copySecondQuestionWithOutReferenceToOtherCourse' in class Questions",e);
        }
    }

    public void copySecondQuestionWithReferenceToOtherCourse(int dataIndex){
        try {
            WebDriver driver=Driver.getWebDriver();
            new Click().clickbylistxpath("//div[@id = 'question-check-box-div']//label",0);//Select a second question
            new Click().clickbyxpath("//div[@id = 'content-search-copy-btn']//img[@id='content-search-icon']");//Click on Copy Button
            new Click().clickbylistxpath(".//*[@id='contentSearchSelectChapterSlider']//a[contains(@id,'sbSelector_')]", 0);//Click on a 'Select Course'
            String course = Config.lscourse;
            WebElement element=driver.findElement(By.xpath("//a[text()='"+course+"']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(5000);
            new Click().clickbylinkText(course);//Select a  Course
            new Click().clickbylistxpath(".//*[@id='contentSearchSelectChapterSlider']//a[contains(@id,'sbSelector_')]",1);//Click on a 'Select Course'
            new Click().clickbypartiallinkText("Ch 2");//Select a 2nd Chapter
            new Click().clickbylistxpath("//div[@class='content-left-assessment-section']//label",0);//Click on 1st assessment
            new Click().clickByid("add-assesments-in-contentSearch"); //Click on 'Done Button'
            Thread.sleep(2000);
        }catch(Exception e){
            Assert.fail("Exception in Method 'copySecondQuestionWithReferenceToOtherCourse' in class Questions",e);
        }
    }




    public void copySecondQuestionWithOutReferenceToOtherChapterOfSameCourse(String dataIndex){
        String  role  =  ReadTestData.readDataByTagName("", "role", dataIndex);

        try {
            WebDriver driver=Driver.getWebDriver();
            new Click().clickbylistxpath("//div[@id = 'question-check-box-div']//label",0);//Select a second question
            new Click().clickbyxpath("//div[@id = 'content-search-copy-btn']//img[@id='content-search-icon']");//Click on Copy Button
            Thread.sleep(3000);
            new Click().clickbylistxpath(".//*[@id='contentSearchSelectChapterSlider']//a[contains(@id,'sbSelector_')]", 0);//Click on a 'Select Course'
            int pos = 0;
            List<WebElement> optionsElementsList = null;
            if(role==null){
                optionsElementsList = driver.findElements(By.xpath("(//ul[contains(@id,'sbOptions_')])[21]//a"));
                for(int a=1;a<optionsElementsList.size();a++){
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", optionsElementsList.get(a));
                    if(optionsElementsList.get(a).getText().equals("Biology")){
                        pos++;
                        if(pos==1){
                            pos=a;
                            break;
                        }
                    }

                }
            }else{
                if(role.equalsIgnoreCase("ROLE_SUBJECT_MATTER_EXPERT")||role.equalsIgnoreCase("ROLE_PRODUCT_DESIGNER")){
                    optionsElementsList = driver.findElements(By.xpath("(//ul[contains(@id,'sbOptions_')])[21]//a"));
                    for(int a=1;a<optionsElementsList.size();a++){
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", optionsElementsList.get(a));
                        if(optionsElementsList.get(a).getText().equals("Biology")){
                            pos++;
                            if(pos==2){
                                pos=a;
                                break;
                            }
                        }

                    }
                }
            }
            new Click().clickByElement(optionsElementsList.get(pos));
            new Click().clickbylistxpath(".//*[@id='contentSearchSelectChapterSlider']//a[contains(@id,'sbSelector_')]",1);//Click on a 'Select Course'
            new UIElement().waitAndFindElement(By.partialLinkText("Ch 2"));
            Thread.sleep(2000);
            new Click().clickbypartiallinkText("Ch 2");//Select a 2nd Chapter
            if(driver.findElements(By.xpath("//div[@class='content-left-assessment-section']//label")).size()!=0){
                new Click().clickbylistxpath("//div[@class='content-left-assessment-section']//label",0);//Click on 1st assessment
            }
            new Click().clickByid("add-assesments-in-contentSearch"); //Click on 'Done Button'
            Thread.sleep(2000);
        }catch(Exception e){
            Assert.fail("Exception in Method 'copySecondQuestionWithOutReferenceToOtherChapterOfSameCourse' in class Questions",e);
        }
    }

    public void copyQuestionWithinCourse(String dataIndex,String ChapterName){

        try {
            WebDriver driver=Driver.getWebDriver();
            new Click().clickbylistxpath("//div[@id = 'question-check-box-div']//label",0);//Select a second question
            new Click().clickbyxpath("//div[@id = 'content-search-copy-btn']//img[@id='content-search-icon']");//Click on Copy Button
            Thread.sleep(3000);
            new Click().clickbylistxpath(".//*[@id='contentSearchSelectChapterSlider']//a[contains(@id,'sbSelector_')]", 0);//Click on a 'Select Course'
            int pos = 0;
            List<WebElement> optionsElementsList = null;
                optionsElementsList = driver.findElements(By.xpath("(//ul[contains(@id,'sbOptions_')])[21]//a"));
                for(int a=1;a<optionsElementsList.size();a++){
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", optionsElementsList.get(a));
                    if(optionsElementsList.get(a).getText().equals("Biology")){
                        pos++;
                        if(pos==1){
                            pos=a;
                            break;
                        }
                    }

                }
            new Click().clickByElement(optionsElementsList.get(pos));
            new Click().clickbylistxpath(".//*[@id='contentSearchSelectChapterSlider']//a[contains(@id,'sbSelector_')]", 1);//Click on a 'Select Course'
            new UIElement().waitAndFindElement(By.partialLinkText(ChapterName));
            Thread.sleep(2000);
            new Click().clickbypartiallinkText(ChapterName);
            List<WebElement> allList=driver.findElements(By.xpath("//div[@class='content-left-assessment-section']//label"));
            new ScrollElement().scrollToViewOfElement(allList.get(allList.size()-1));
            allList.get(allList.size()-1).click();
            new Click().clickByid("add-assesments-in-contentSearch"); //Click on 'Done Button'
            Thread.sleep(3000);
            new Click().clickByid("refreshSearchResult");

        }catch(Exception e){
            Assert.fail("Exception in Method 'copyQuestionWithinCourse' in class Questions",e);
        }
    }
    public void moveQuestionWithinCourse(String dataIndex,String ChapterName){

        try {
            WebDriver driver=Driver.getWebDriver();
            new Click().clickbylistxpath("//div[@id = 'question-check-box-div']//label",0);//Select a second question
            new Click().clickbyxpath(".//*[@id='content-search-move-btn']//img");//Click on Move Button
            Thread.sleep(3000);
            new Click().clickbylistxpath(".//*[@id='contentSearchSelectChapterSlider']//a[contains(@id,'sbSelector_')]", 0);//Click on a 'Select Course'
            int pos = 0;
            List<WebElement> optionsElementsList = null;
            optionsElementsList = driver.findElements(By.xpath("(//ul[contains(@id,'sbOptions_')])[21]//a"));
            for(int a=1;a<optionsElementsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", optionsElementsList.get(a));
                if(optionsElementsList.get(a).getText().equals("Biology")){
                    pos++;
                    if(pos==1){
                        pos=a;
                        break;
                    }
                }

            }
            new Click().clickByElement(optionsElementsList.get(pos));
            new Click().clickbylistxpath(".//*[@id='contentSearchSelectChapterSlider']//a[contains(@id,'sbSelector_')]", 1);//Click on a 'Select Course'
            new UIElement().waitAndFindElement(By.partialLinkText(ChapterName));
            Thread.sleep(2000);
            new Click().clickbypartiallinkText(ChapterName);
            List<WebElement> allList=driver.findElements(By.xpath("//div[@class='content-left-assessment-section']//label"));
            new ScrollElement().scrollToViewOfElement(allList.get(allList.size()-1));
            allList.get(allList.size()-1).click();
            new Click().clickByid("add-assesments-in-contentSearch"); //Click on 'Done Button'
            Thread.sleep(3000);
            new Click().clickByid("refreshSearchResult");

        }catch(Exception e){
            Assert.fail("Exception in Method 'copyQuestionWithinCourse' in class Questions",e);
        }
    }
    public void copyQuestionBetweenCourses(int dataIndex,String chapterName){
        try {
            WebDriver driver=Driver.getWebDriver();
            new UIElement().waitAndFindElement(By.xpath("//div[@id = 'question-check-box-div']//label"));
            new Click().clickbylistxpath("//div[@id = 'question-check-box-div']//label", 0);//Select a second question
            new Click().clickbyxpath(".//*[@id='content-search-move-btn']//img");//Click on Move Button
            Thread.sleep(3000);
            new Click().clickbylistxpath(".//*[@id='contentSearchSelectChapterSlider']//a[contains(@id,'sbSelector_')]",0);//Click on a 'Select Course'
            String course = Config.lscourse;
            WebElement element=driver.findElement(By.xpath("//a[text()='"+course+"']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(5000);
            new Click().clickbylinkText(course);//Select a  Course
            new Click().clickbylistxpath(".//*[@id='contentSearchSelectChapterSlider']//a[contains(@id,'sbSelector_')]",1);//Click on a 'Select Chapter'
            new Click().clickbypartiallinkText(chapterName);//Select a 2nd Chapter
            List<WebElement> allList=driver.findElements(By.xpath("//div[@class='content-left-assessment-section']//label"));
            new ScrollElement().scrollToViewOfElement(allList.get(allList.size() - 1));
            allList.get(allList.size()-1).click();
            new Click().clickByid("add-assesments-in-contentSearch"); //Click on 'Done Button'
            Thread.sleep(3000);
            new Click().clickByid("refreshSearchResult");
            Thread.sleep(2000);
        }catch(Exception e){
            Assert.fail("Exception in Method 'copyQuestionBetweenCourses' in class Questions",e);
        }
    }
    public void moveQuestionBetweenCourses(int dataIndex,String chapterName){
        try {
            WebDriver driver=Driver.getWebDriver();
            new UIElement().waitAndFindElement(By.xpath("//div[@id = 'question-check-box-div']//label"));
            new Click().clickbylistxpath("//div[@id = 'question-check-box-div']//label", 1);//Select a second question
            new Click().clickbyxpath(".//*[@id='content-search-move-btn']//img");//Click on Move Button
            new Click().clickbylistxpath(".//*[@id='contentSearchSelectChapterSlider']//a[contains(@id,'sbSelector_')]",0);//Click on a 'Select Course'
            String course = Config.lscourse;
            WebElement element=driver.findElement(By.xpath("//a[text()='"+course+"']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(5000);
            new Click().clickbylinkText(course);//Select a  Course
            new Click().clickbylistxpath(".//*[@id='contentSearchSelectChapterSlider']//a[contains(@id,'sbSelector_')]",1);//Click on a 'Select Chapter'
            new Click().clickbypartiallinkText(chapterName);//Select a 2nd Chapter
            List<WebElement> allList=driver.findElements(By.xpath("//div[@class='content-left-assessment-section']//label"));
            new ScrollElement().scrollToViewOfElement(allList.get(allList.size() - 1));
            allList.get(allList.size()-1).click();
            new Click().clickByid("add-assesments-in-contentSearch"); //Click on 'Done Button'
            Thread.sleep(3000);
            new Click().clickByid("refreshSearchResult");
            Thread.sleep(2000);
        }catch(Exception e){
            Assert.fail("Exception in Method 'moveQuestionBetweenCourses' in class Questions",e);
        }
    }

    public void checkForLaunchReview(int dataIndex){
        try {
            new UIElement().waitAndFindElement(By.xpath("//div[@id = 'question-check-box-div']//label"));
            new Click().clickbylistxpath("//div[@id = 'question-check-box-div']//label", 0);//Select a second question
            new Click().clickbyxpath(".//*[@id='content-search-review-btn']//img");//Click on LaunchReview
        }catch(Exception e){
            Assert.fail("Exception in Method 'checkForLaunchReview' in class Questions",e);
        }
    }

    public void moveSecondQuestionToOtherCourse(int dataIndex){
        try {
            WebDriver driver=Driver.getWebDriver();
            new Click().clickbylistxpath("//div[@id = 'question-check-box-div']//label",0);//Select a second question
            new Click().clickbyxpath(".//*[@id='content-search-move-btn']//img");//Click on Move Button
            new Click().clickbylistxpath(".//*[@id='contentSearchSelectChapterSlider']//a[contains(@id,'sbSelector_')]",0);//Click on a 'Select Course'
            String course = Config.lscourse;
            WebElement element=driver.findElement(By.xpath("//a[text()='"+course+"']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(5000);
            new Click().clickbylinkText(course);//Select a  Course
            new Click().clickbylistxpath(".//*[@id='contentSearchSelectChapterSlider']//a[contains(@id,'sbSelector_')]",1);//Click on a 'Select Course'
            new Click().clickbypartiallinkText("Ch 2");//Select a 2nd Chapter
            new Click().clickbylistxpath("//div[@class='content-left-assessment-section']//label",0);//Click on 1st assessment
            new Click().clickByid("add-assesments-in-contentSearch"); //Click on 'Done Button'
            Thread.sleep(2000);
        }catch(Exception e){
            Assert.fail("Exception in Method 'moveSecondQuestionToOtherCourse' in class Questions",e);
        }
    }


    public void moveSecondQuestionToOtherChapterOfSameCourse(String dataIndex){
        String  role  =  ReadTestData.readDataByTagName("", "role", dataIndex);
        try {
            WebDriver driver=Driver.getWebDriver();
            new Click().clickbylistxpath("//div[@id = 'question-check-box-div']//label",0);//Select a second question
            List<WebElement> QuestionsElementsList = driver.findElements(By.xpath(".//*[@id='search-chapter-blk']/div"));
            new Click().clickbyxpath(".//*[@id='content-search-move-btn']//img");//Click on Move Button
            new Click().clickbylistxpath(".//*[@id='contentSearchSelectChapterSlider']//a[contains(@id,'sbSelector_')]",0);//Click on a 'Select Course'
            int pos = 0;
            List<WebElement> optionsElementsList = null;
            if(role==null){
                optionsElementsList = driver.findElements(By.xpath("(//ul[contains(@id,'sbOptions_')])[21]//a"));
                for(int a=1;a<optionsElementsList.size();a++){
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", optionsElementsList.get(a));
                    if(optionsElementsList.get(a).getText().equals("Biology")){
                        pos++;
                        if(pos==1){
                            pos=a;
                            break;
                        }
                    }

                }
            }else{
                if(role.equalsIgnoreCase("ROLE_SUBJECT_MATTER_EXPERT")||role.equalsIgnoreCase("ROLE_PRODUCT_DESIGNER")){
                    optionsElementsList = driver.findElements(By.xpath("(//ul[contains(@id,'sbOptions_')])[21]//a"));
                    for(int a=1;a<optionsElementsList.size();a++){
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", optionsElementsList.get(a));
                        if(optionsElementsList.get(a).getText().equals("Biology")){
                            pos++;
                            if(pos==2){
                                pos=a;
                                break;
                            }
                        }

                    }
                }
            }
            new Click().clickByElement(optionsElementsList.get(pos));
            new Click().clickbylistxpath(".//*[@id='contentSearchSelectChapterSlider']//a[contains(@id,'sbSelector_')]",1);//Click on a 'Select Chapter'
            new Click().clickbypartiallinkText("Ch 2");//Select a 2nd Chapter
            new Click().clickbylistxpath("//div[@class='content-left-assessment-section']//label",0);//Click on 1st assessment
            new Click().clickByid("add-assesments-in-contentSearch"); //Click on 'Done Button'
            Thread.sleep(2000);
        }catch(Exception e){
            Assert.fail("Exception in Method 'moveSecondQuestionToOtherChapterOfSameCourse' in class Questions",e);
        }
    }

   /* public void clickingEditButtoninQuestionTofetchCreationDateAndAuthorName(){
        try {
            Thread.sleep(2000);
            new MouseHover().mouserHoverByXpathList("//div[@id = 'question-check-box-div']//label", 0);// Mouse Over on Edit icon on first question
            Thread.sleep(3000);
            new Click().clickbylistxpath("//div[@class='edit-question-content']", 0);// Click on 'Edit' Button on 2nd Question
            Thread.sleep(2000);
        }catch(Exception e){
            Assert.fail("Exception in Method 'clickingEditButtoninQuestionTofetchCreationDateAndAuthorName' in class Questions",e);
        }
    }*/

    public void clickingEditButtoninQuestionTofetchCreationDateAndAuthorName(int index){
        try{
            WebDriver driver=Driver.getWebDriver();
            driver.findElement(By.xpath("//body")).sendKeys(Keys.F5);
            navigateToQuestionPage(index);
            WebDriverWait webDriverWait = new WebDriverWait(driver,60);
            webDriverWait.until((ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id = 'content-search-results-block']//div")))));
            new MouseHover().mouserHoverByXpathList("//div[@id = 'content-search-results-block']//div", 0);// Mouse Over on Edit icon on first question
            new UIElement().waitAndFindElement(By.xpath("//div[@class='edit-question-content']"));
            new Click().clickbylistxpath("//div[@class='edit-question-content']", 0);// Click on 'Edit' Button on 2nd Question
        }catch(Exception e){
            Assert.fail("Exception in Method 'clickingEditButtoninQuestionTofetchCreationDateAndAuthorName' in class Questions",e);
        }
    }




    public void clickingEditButtoninQuestionTofetchCreationDateAndAuthorName(){
        try {
            WebDriver driver=Driver.getWebDriver();
            Thread.sleep(2000);
            WebDriverWait webDriverWait = new WebDriverWait(driver,60);
            webDriverWait.until((ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id = 'content-search-results-block']//div")))));
            new MouseHover().mouserHoverByXpathList("//div[@id = 'content-search-results-block']//div", 0);// Mouse Over on Edit icon on first question
            new UIElement().waitAndFindElement(By.xpath("//div[@class='edit-question-content']"));
            new Click().clickbylistxpath("//div[@class='edit-question-content']", 0);// Click on 'Edit' Button on 2nd Question
        }catch(Exception e){
            Assert.fail("Exception in Method 'clickingEditButtoninQuestionTofetchCreationDateAndAuthorName' in class Questions",e);
        }
    }


    public String getTodaysDateAsperFormat() {
        String todaysDate = null;
        try {
            String month =  new SimpleDateFormat("MMMM").format(new Date());
            String currentDate =  new SimpleDateFormat("dd").format(new Date());
            String currentYear=  new SimpleDateFormat("yyyy").format(new Date());
            todaysDate = month+" "+currentDate+", "+currentYear;
        } catch (Exception e) {
            Assert.fail("Exception in Method getTodaysDateAsperFormat in class Questions.",e);
        }
        return (todaysDate);
    }
    public void clickOnLatQuestionInQuestionPage(){//Clicks the last Question in a Question Page
        try {
            WebDriver driver=Driver.getWebDriver();
            driver.findElement(By.linkText("Jump To Q#")).click();
            Thread.sleep(3000);
            WebDriverWait wait = new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//div[@class='overview'])[2]//li//a")));
            List<WebElement> QuestionsList = driver.findElements(By.xpath("(//div[@class='overview'])[2]//li//a"));
            WebElement element = QuestionsList.get(QuestionsList.size()-1);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
            Thread.sleep(2000);
            QuestionsList.get(QuestionsList.size()-1).click();
            Thread.sleep(5000);
        }catch(Exception e){
            Assert.fail("Exception in Method 'clickOnLatQuestionInQuestionPage' in class Questions.",e);
        }
    }


    public String getQuestionsAuthorName(){
        String authorName = null;
        try {
            authorName = new TextFetch().textfetchbyid("content-authorName");
        }catch(Exception e){
            Assert.fail("Exception in Method getQustionsCreationDate in class Questions.",e);
        }
        return authorName;
    }

    public String getQuestionsCreationDate(){
        String creationDate = null;
        try {
            creationDate = new TextFetch().textfetchbyid("content-creationDate");
            String creationDateTokens[] = creationDate.split("[|]");
            creationDate = creationDateTokens[0].trim();
        }catch(Exception e){
            Assert.fail("Exception in Method getQuestionsCreationDate in class Questions.",e);
        }
        return creationDate;
    }

}
