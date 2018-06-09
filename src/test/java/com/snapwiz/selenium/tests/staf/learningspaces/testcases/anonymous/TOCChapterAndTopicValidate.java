package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCChapterNames;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCVerify;


public class TOCChapterAndTopicValidate extends Driver {
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.TOCChapterAndTopicValidate");
	@Test
	public void tocChapterAndTopicValidate()
	{
		try
		{
			logger.log(Level.INFO,"TestCase tocChapterAndTopicValidate under class TOCChapterAndTopicValidate Execution Starting");
			new LoginUsingLTI().ltiLogin("654");
			Thread.sleep(2000);
			new Navigator().NavigateTo("eTextbook");
			String [] toccard = new TOCVerify().tocverify(1);
		    String [] chapternames = new TOCChapterNames().getTOCChapterNames();
		    String chapter1 = ReadTestData.readDataByTagName("TOCChapterAndTopicValidate", "chapter1", "654");
		    String chapter2 = ReadTestData.readDataByTagName("TOCChapterAndTopicValidate", "chapter2", "654");
		    if(!chapter1.equals(chapternames[0]) || !chapter2.equals(chapternames[1]))
		       	Assert.fail("Chapter 1 and Chapter 2 given in testdata doesn't match with chapters present");
		    
		   
		    String topic1 = ReadTestData.readDataByTagName("tocdata", "card1topic1", "1");
		    String topic2 = ReadTestData.readDataByTagName("tocdata", "card1topic2", "1");
		    String topic3 = ReadTestData.readDataByTagName("tocdata", "card1topic3", "1");
		    if(topic1!= "")
		    {
		    	if(!topic1.equals(toccard[0]))
		    		Assert.fail("First topic under chapter 1 doesn't match with chapter given in topic1 tag of testdata");
		    }
		   
		    if(topic2!= "")
		    {
		    	if(!topic2.equals(toccard[1]))
		    		Assert.fail("Second topic under chapter 1 doesn't match with chapter given in topic2 tag of testdata");
		    }
		    if(topic3!= "")
		    {
		    	if(!topic3.equals(toccard[2]))
		    		Assert.fail("Third topic under chapter 1 doesn't match with chapter given in topic3 tag of testdata");
		    }
		    
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in TC elementStatusDisplay in ElementStatusDisplay class");
		}
	}
	

	
}
