package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R1920;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DataFetchFromDataBase;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by sumit on 9/1/15.
 */
public class BlackBoardIntegrationForLSAdaptive extends Driver{

    @Test(priority = 1, enabled = true)
    public void blackBoardIntegrationForLSAdaptive()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("11");		//login as student
            validate("11");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class BlackBoardIntegrationForLSAdaptive in method blackBoardIntegrationForLSAdaptive.", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void instructorBlackBoardIntegrationForLSAdaptive()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("23");		//login as instructor
            validate("23");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class BlackBoardIntegrationForLSAdaptive in method instructorBlackBoardIntegrationForLSAdaptive.", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void mentorBlackBoardIntegrationForLSAdaptive()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("34");		//login as mentor
            validate("34");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class BlackBoardIntegrationForLSAdaptive in method mentorBlackBoardIntegrationForLSAdaptive.", e);
        }
    }
    @Test(priority = 4, enabled = true)
    public void studentBlackBoardIntegrationForLS()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("46");		//login as student
            validate("46");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class BlackBoardIntegrationForLSAdaptive in method studentBlackBoardIntegrationForLS.", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void instructorBlackBoardIntegrationForLS()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("57");		//login as instructor
            validate("57");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class BlackBoardIntegrationForLSAdaptive in method instructorBlackBoardIntegrationForLS.", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void mentorBlackBoardIntegrationForLS()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("68");		//login as mentor
            validate("68");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class BlackBoardIntegrationForLSAdaptive in method mentorBlackBoardIntegrationForLS.", e);
        }
    }

    public void validate(String dataIndex)
    {
        try
        {
            String Role =  ReadTestData.readDataByTagName("", "Role", dataIndex);
            String course_type =  ReadTestData.readDataByTagName("", "course_type", dataIndex);
            String user_id =  ReadTestData.readDataByTagName("", "user_id", dataIndex);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);

            String course = null;
            if(course_type == null){
                course = "LS+Adaptive";
            }
            else if(course_type.equalsIgnoreCase("ls")){
                course = "LS";
            }
            else if(course_type.equalsIgnoreCase("adaptive")){
                course = "Adaptive";
            }
            dashboard.getUserName().click();	//click on profile dropdown
            Thread.sleep(2000);
            String blackBoard = dashboard.getMyBlackBoard().getText();
            Assert.assertEquals(blackBoard, "My Blackboard", "BlackBoard option is not appearing for " + Role + " under profile dropdown in " + course + " course");

            String tooltip = dashboard.getMyBlackBoard().getAttribute("title");
            Assert.assertEquals(tooltip, "My Blackboard", "Tooltip in BlackBoard option is not appearing for " + Role + " under profile dropdown in " + course + " course");

            String blackBoardIcon = dashboard.getMyBlackBoardIcon().getCssValue("background-image");
            if(!blackBoardIcon.contains("blackboard.png")){
                Assert.fail("Blackboard icon is not present near the Blackboard option for "+Role+" under profile dropdown in "+course+" course");
            }
            dashboard.getMainNavigator().click();//click on Navigator icon
            String navigator = dashboard.getGoToMyBlackBoard().getText();
            Assert.assertEquals(navigator, "Go back to My Blackboard", "For a "+Role +", In the main navigator, \"Go back to My Blackboard\" option is absent in "+course+" course.");

            String blackBoardIcon1 = dashboard.getMyBlackBoardIcon().getCssValue("background-image");
            if(!blackBoardIcon1.contains("blackboard.png")){
                Assert.fail("Blackboard icon is not present near the \"Go back to My Blackboard\" option for "+Role+" under main navigator in "+course+" course");
            }
            String appendChar=null;
            if(System.getProperty("UCHAR")==null) {
                appendChar= LoginUsingLTI.appendChar;
            }
            else {
                appendChar = System.getProperty("UCHAR");
            }
            System.out.println("username:"+user_id +appendChar);
            String dbData = new DataFetchFromDataBase().databaseverificationstring(1, "SELECT tool_instance_guid FROM mongrel.t_user where username = '" + user_id +appendChar+ "';");
            System.out.println("dbData:"+dbData);
            Assert.assertEquals(dbData, "blackboard", "\"tool_consumer_instance_guid\" - column in database is changed to \"blackboard\" value. for "+Role+" under main navigator in "+course+" course.");

        }
        catch (Exception e){
            Assert.fail("Exception in class BlackBoardIntegrationForLSAdaptive in method validate.", e);
        }
    }

}
