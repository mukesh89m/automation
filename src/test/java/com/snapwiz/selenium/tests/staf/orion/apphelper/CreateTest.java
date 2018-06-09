package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.List;
import org.testng.Assert;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
/*
 * Brajesh 
 * create practice and adaptive test
 */
public class CreateTest 
{
	public void createtest(int diagonestic,int practice)
	{
		try
		{
			
		String diagnostictest=ReadTestData.readDataByTagName("CreateTest", "diagassessmentname", "0");
		String adaptivename=ReadTestData.readDataByTagName("CreateTest", "practiceassessmentname", "0");		
		List<String> tloids = new PracticeTest().tloIds();
		new Assignment().createChapter(0, Integer.parseInt(tloids.get(0)),Integer.parseInt(tloids.get(1)),Integer.parseInt(tloids.get(2)),Integer.parseInt(tloids.get(3)),Integer.parseInt(tloids.get(4)),Integer.parseInt(tloids.get(5)));
		new Assignment().createPracticeAtChapterLevel(0, "Adaptive Component Diagnostic", false, true, true,Integer.parseInt(tloids.get(0)));
		new Assignment().addMultipleQuestions(0, "qtn-multiple-selection-img", diagnostictest, diagonestic, false, true, true, Integer.parseInt(tloids.get(0)));
		new Assignment().createPracticeAtChapterLevel(0, "Adaptive Component Practice", false, true, true,Integer.parseInt(tloids.get(0)));
		new Assignment().addMultipleQuestions(0, "qtn-multiple-selection-img", adaptivename, practice, false, true, true, Integer.parseInt(tloids.get(0)));
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper CreateTest  ",e);
		}
		
	}

}
