package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;

/*
 * Test case 600-603
 */
public class CardStructureVerification extends Driver
{
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.CardStructureVerification");
	
	@Test
	 public void cardStructureVerification()
	 {
	  try
	  {
		  new LoginUsingLTI().ltiLogin("602");
		  new Navigator().NavigateTo("eTextbook");
		  new TOCVerify().tocChapterVerify(1);
		  new ExpandCollapseChapter().expandChapter(1);
		  new TOCVerify().tocTopicValidate(1, 1); 
	      new TOCVerify().tocTopicValidate(1, 2);
		 
		  WebElement WE1 = driver.findElement(By.cssSelector("ul[class='chapter-card']"));
		  
		  String card = WE1.getText();
		  String card1topic1 = ReadTestData.readDataByTagName("tocdata", "card1topic1","1");
		  String card1topic2 = ReadTestData.readDataByTagName("tocdata", "card1topic2","1");
		  String card1topic3 = ReadTestData.readDataByTagName("tocdata", "card1topic3", "1");
		  int diagnosticAssessmentIndex = card.indexOf(card1topic1) ;		 
		  int adaptiveAssessmentIndex = card.indexOf(card1topic2);
		  int staticAssessmentIndex = card.indexOf(card1topic3);		  
		  if(staticAssessmentIndex>adaptiveAssessmentIndex && adaptiveAssessmentIndex>diagnosticAssessmentIndex)
		  {
			  logger.log(Level.INFO,"diagnostic assessment comes first,  Adaptive assessment comes second, Static assessment comes 3rd"); 
		  }
		  else
		  {
			  logger.log(Level.INFO,"Sequence for diagnostic assessment , Adaptive assessment, Static assessment is not Correct");
			  Assert.fail("Sequence for diagnostic assessment ,  Adaptive assessment, Static assessment is not Correct");	
		  }
		  
	  }
	  catch(Exception e)
	   {
		  Assert.fail("Exception TestCase cardStructureVerification in class CardStructureVerification",e);
	   }
	 }

}
