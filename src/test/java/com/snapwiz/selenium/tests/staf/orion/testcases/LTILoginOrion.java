package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.CheckWileyPluslink;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Openstudylink;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;


public class LTILoginOrion 
{
	@Test(priority = 1, enabled = true)
	public void ltilogin1()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1153");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin1 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 2, enabled=true)
	public void ltilogin2()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1154");
			String text=Driver.driver.findElement(By.className("al-content-body-wrapper-disabled")).getAttribute("style");
			System.out.println(text);
			if(!text.contains("1px solid rgb(68, 155, 216)"))
			{
				Assert.fail("Selected first Chapter border not highlight");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin2 in class LoginCheck",e);
		}
	}

	@Test(priority = 3, enabled=true)
	public void ltilogin3()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1155");
			Thread.sleep(2000);
			String borderhighlight="";
			List<WebElement> borders=Driver.driver.findElements(By.className("al-content-body-wrapper-disabled"));
			for(WebElement border : borders)
			{
				if(border.getAttribute("style").contains("border:"))
				{
					borderhighlight = border.getAttribute("style");
					break;
				}
			}
			System.out.println(borderhighlight);
			if(!borderhighlight.contains("border:"))
			{
				Assert.fail("Selected Last Chapter border not highlight");
				
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin3 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 4, enabled =true)
	public void ltilogin4()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1156");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin4 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 5, enabled = true)
	public void ltilogin5()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1157");
			new CheckWileyPluslink().CheckWileyPluslinkdashboardanddiagqspage();
			Thread.sleep(6000);
			int homelink = Driver.driver.findElements(By.cssSelector("img[title='ORION Dashboard']")).size();
			if(homelink==1)
			{
				Driver.driver.findElement(By.cssSelector("img[title='ORION Dashboard']")).click();
			}
			Thread.sleep(3000);
			new Openstudylink().Openstudylinkindashboard(3); 
			boolean checkwileypage = Driver.driver.findElement(By.tagName("body")).getText().contains("If you are looking for a specific product, you can enter information in the search box at the top right-hand corner of this page.");
			if(checkwileypage==false)
			{
				Assert.fail("text not match to actual text");
			}			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin5 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 6, enabled = true)
	public void ltilogin6()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1158");
			new CheckWileyPluslink().CheckWileyPluslinkdashboardanddiagqspage();			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin6 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 7, enabled = true)
	public void ltiLogin7()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1159");
			new CheckWileyPluslink().CheckWileyPluslinkdashboardanddiagqspage();
			int homelink = Driver.driver.findElements(By.cssSelector("img[title='ORION Dashboard']")).size();
			if(homelink==1)
			{
				Driver.driver.findElement(By.cssSelector("img[title='ORION Dashboard']")).click();
			}
			Thread.sleep(3000);
			new Openstudylink().Openpracticelinkindashboard(0);
			int timer= Driver.driver.findElements(By.cssSelector("span[id='cntdwn']")).size();
			for(int i=0;i<10;i++)
			{
				boolean returnwileypluspractice = Driver.driver.findElement(By.id("al-header-return-url")).isDisplayed();
				if(returnwileypluspractice==false)
				{
					Assert.fail("On practice quetsion page WileyPlus button is not present.");
				}
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-practice-test-submit-button")));
				int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
				   if(noticesize==1)
				   
				   {
				    String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
				    if(notice.length()>0)
				    {
				     ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-practice-test-submit-button")));
				    }
				    
				    
				   }
				
				boolean returnwileypluspracticenext = Driver.driver.findElement(By.id("al-header-return-url")).isDisplayed();
				if(returnwileypluspracticenext==false)
				{
					Assert.fail("On practice quetsion page WileyPlus button is not present.");
				}
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
				Thread.sleep(2000);
				/*Thread.sleep(2000);
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-next-practice-question-button")));*/
				timer =	Driver.driver.findElements(By.cssSelector("span[id='cntdwn']")).size();
				System.out.println(timer);			
				
			}
			Driver.driver.findElement(By.className("al-quit-diag-test-icon")).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.className("al-view-practice-test-report")).click();
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin7 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 8, enabled = true)
	public void ltilogin8()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1160");
			new CheckWileyPluslink().CheckWileyPluslinkdashboardanddiagqspage();
			Thread.sleep(3000);
			boolean returnwileyplusmyactivity = Driver.driver.findElement(By.id("al-header-return-url")).isDisplayed();
			if(returnwileyplusmyactivity==false)
			{
				Assert.fail("On My Activity page WileyPlus button is not present.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin8 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 9, enabled = true)
	public void ltilogin9()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1161");
			new CheckWileyPluslink().CheckWileyPluslinkdashboardanddiagqspage();
			Thread.sleep(3000);
			boolean returnwileyplusmyactivity = Driver.driver.findElement(By.id("al-header-return-url")).isDisplayed();
			if(returnwileyplusmyactivity==true)
			{
				Thread.sleep(2000);
				Driver.driver.findElement(By.className("al-user-profile-name")).click();
				Thread.sleep(2000);
				Driver.driver.findElement(By.id("myReport")).click();
				boolean returnwileyplusmyMCApage = Driver.driver.findElement(By.id("al-header-return-url")).isDisplayed();
				if(returnwileyplusmyMCApage==true)
				{
					Thread.sleep(2000);
					Driver.driver.findElement(By.id("al-metacognitive-report")).click();
					boolean returnwileyplusmetaCongpage = Driver.driver.findElement(By.id("al-header-return-url")).isDisplayed();
					if(returnwileyplusmetaCongpage==true)
					{
						Thread.sleep(2000);
						Driver.driver.findElement(By.id("al-productivity-report")).click();
						boolean returnwileyplusmyprodpage = Driver.driver.findElement(By.id("al-header-return-url")).isDisplayed();
						if(returnwileyplusmyprodpage==true)
						{
							Thread.sleep(2000);
							Driver.driver.findElement(By.id("al-performance-report")).click();
							boolean returnwileyplusmyperfpage = Driver.driver.findElement(By.id("al-header-return-url")).isDisplayed();
							if(returnwileyplusmyperfpage==false)
							{
								Assert.fail("On Student My Reports page WileyPlus button is not present.");
							}
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin9 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 10, enabled = true)
	public void ltilogin10()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1163");
			Thread.sleep(1000);
			Driver.driver.findElement(By.id("close-al-notification-dialog")).click();
			Thread.sleep(2000);
			boolean returnwileyplusinsdashboard = Driver.driver.findElement(By.id("idb-header-return-url")).isDisplayed();
			if(returnwileyplusinsdashboard==true)
			{
				Driver.driver.findElement(By.className("idb-user-profile-name")).click();
				Thread.sleep(2000);
				Driver.driver.findElement(By.id("instructorReports")).click();
				Thread.sleep(2000);
				boolean returnwileyplusMCApage = Driver.driver.findElement(By.id("idb-header-return-url")).isDisplayed();
				if(returnwileyplusMCApage==true)
				{
					Driver.driver.findElement(By.id("al-metacognitive-report")).click();
					Thread.sleep(2000);	
					boolean returnwileyplusMetaCpage = Driver.driver.findElement(By.id("idb-header-return-url")).isDisplayed();
					if(returnwileyplusMetaCpage==true)
					{
						Driver.driver.findElement(By.id("al-productivity-report")).click();
						Thread.sleep(2000);	
						boolean returnwileyplusProductivitypage = Driver.driver.findElement(By.id("idb-header-return-url")).isDisplayed();
						if(returnwileyplusProductivitypage==true)
						{
							Driver.driver.findElement(By.id("al-performance-report")).click();
							Thread.sleep(2000);	
							boolean returnwileyplusPerfpage = Driver.driver.findElement(By.id("idb-header-return-url")).isDisplayed();
							if(returnwileyplusPerfpage==false)
							{
									Assert.fail("On Instrcutor My Reports page WileyPlus button is not present.");							
							}
						}
					}
				}
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin10 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 11, enabled = true)
	public void ltilogin11()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1164");
			Thread.sleep(1000);
			int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
			   if(noticesize==1)
			   
			   {
			    String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
			    if(notice.length()>0)
			    {
			     ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[title='Close']")));
			    }
			    
			    
			   }
			//Driver.driver.findElement(By.id("close-al-notification-dialog")).click();
			Thread.sleep(2000);
			boolean returnwileyplusinsdashboard7days = Driver.driver.findElement(By.id("idb-header-return-url")).isDisplayed();
			if(returnwileyplusinsdashboard7days==true)
			{
				Driver.driver.findElement(By.cssSelector("span[buckettype='month']")).click();
				Thread.sleep(2000);
				boolean returnwileyplusinsdashboard5weeks = Driver.driver.findElement(By.id("idb-header-return-url")).isDisplayed();
				if(returnwileyplusinsdashboard5weeks==true)
				{
					Driver.driver.findElement(By.cssSelector("span[buckettype='all']")).click();
					Thread.sleep(2000);
					boolean returnwileyplusinsdashboardall = Driver.driver.findElement(By.id("idb-header-return-url")).isDisplayed();
					if(returnwileyplusinsdashboardall==false)
					{
						Assert.fail("On Instrcutor All Activity WileyPlus button is not present.");
					}
				}
			}				
		}	
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin11 in class LoginCheck",e);
		}
			
	}
	
	@Test(priority = 12, enabled = true)
	public void ltiLogin12()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1167");
			Driver.driver.findElement(By.id("al-header-return-url")).click();
			Thread.sleep(3000);
			String url = Driver.driver.getCurrentUrl();
			System.out.println("url: "+url);
			if(!url.contains("edugen.wiley.com/edugen/rs-callback"))
			{
				Assert.fail("Return WileyPlus link is not navigated to correct url");
			}			
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin12 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 13, enabled = true)
	public void ltiLogin13()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1157");
			new CheckWileyPluslink().CheckWileyPluslinkdashboardanddiagqspage();
			Thread.sleep(6000);
			int homelink = Driver.driver.findElements(By.cssSelector("img[title='ORION Dashboard']")).size();
			if(homelink==1)
			{
				Driver.driver.findElement(By.cssSelector("img[title='ORION Dashboard']")).click();
			}
			Thread.sleep(3000);
			new Openstudylink().Openstudylinkindashboard(3);
			Thread.sleep(3000);
			String url = Driver.driver.getCurrentUrl();
			System.out.println("url: "+url);
			if(!url.contains("edugen.wiley.com/edugen/rs-callback"))
			{
				Assert.fail("Return WileyPlus link is not navigated to correct url");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin13 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 14, enabled = true)
	public void ltiLogin14()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1170");
			Driver.driver.quit();
			Driver.startDriver();
			Thread.sleep(3000);
			new LoginUsingLTI().ltiLogin("1170_1");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin14 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 15, enabled = true)
	public void ltiLogin15()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1170_1");
			Driver.driver.quit();
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1172");			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin15 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 16, enabled = true)
	public void ltiLogin16()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1172");
			Driver.driver.quit();
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1171");			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin16 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 17, enabled = true)
	public void ltiLogin17()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1172");
			Driver.driver.quit();
			Driver.startDriver();
			Thread.sleep(3000);
			new LoginUsingLTI().ltiLogin("1173");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin17 in class LoginCheck",e);
		}
	}	
	
	@Test(priority = 18, enabled = true)
	public void ltiLogin18()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1172");
			Driver.driver.quit();
			Driver.startDriver();
			Thread.sleep(3000);
			new LoginUsingLTI().ltiLogin("1174");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin18 in class LoginCheck",e);
		}
	}	
	
	@Test(priority = 19, enabled = true)
	public void ltiLogin19()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1175");
			Driver.driver.quit();
			Driver.startDriver();
			Thread.sleep(3000);
			new LoginUsingLTI().ltiLogin("1175_1");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin19 in class LoginCheck",e);
		}
	}		
	
	
	@Test(priority = 20, enabled = true)
	public void ltiLogin20()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1176");
			Driver.driver.quit();
			Driver.startDriver();
			Thread.sleep(3000);
			new LoginUsingLTI().ltiLogin("1176_1");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin20 in class LoginCheck",e);
		}
	}	
	
/*	@Test(priority = 21, enabled = false)
	public void ltiLogin21()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1177");			   
			//Driver.driver.findElement(By.tagName("Body")).sendKeys(keys.CONTROL + "t");//opens new tab  					
			Thread.sleep(3000);
			new LoginUsingLTI().ltiLogin("1177_1");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin21 in class LoginCheck",e);
		}
	}	
	*/
	
	@Test(priority = 22, enabled = true)
	public void ltiLogin22()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1178");			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin22 in class LoginCheck",e);
		}
	}	
		
	@Test(priority = 23, enabled = true)
	public void ltiLogin23()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1179");			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin23 in class LoginCheck",e);
		}
	}		
	
	@Test(priority = 24, enabled = true)
	public void ltiLogin24()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1180");			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin24 in class LoginCheck",e);
		}
	}		
	
	@Test(priority = 25, enabled = true)
	public void ltiLogin25()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1181");			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin25 in class LoginCheck",e);
		}
	}	
	
	@Test(priority = 26, enabled = true)
	public void ltiLogin26()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1181");
			Driver.driver.quit();
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1182");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin26 in class LoginCheck",e);
		}
	}		
	
	@Test(priority = 27, enabled = true)
	public void ltiLogin27()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1181");
			Driver.driver.quit();
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1183");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin27 in class LoginCheck",e);
		}
	}	
	
	@Test(priority = 28, enabled = true)
	public void ltiLogin28()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1184");			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin28 in class LoginCheck",e);
		}
	}	

	@Test(priority = 29, enabled = true)
	public void ltiLogin29()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1184");
			Driver.driver.quit();
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1185");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin29 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 30, enabled = true)
	public void ltiLogin30()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1184");
			Driver.driver.quit();
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1186");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin30 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 31, enabled = true)
	public void ltiLogin31()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1187");						
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin31 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 32, enabled = true)
	public void ltiLogin32()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1187");
			Driver.driver.quit();
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1188");				
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin32 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 33, enabled = true)
	public void ltiLogin33()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1187");
			Driver.driver.quit();
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1189");				
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin33 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 34, enabled = true)
	public void ltiLogin34()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1190");			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin34 in class LoginCheck",e);
		}
	}	

	@Test(priority = 35, enabled = true)
	public void ltiLogin35()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1190");
			Driver.driver.quit();
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1191");				
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin35 in class LoginCheck",e);
		}
	}	
	
	@Test(priority = 36, enabled = true)
	public void ltiLogin36()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1190");
			Driver.driver.quit();
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1192");				
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin36 in class LoginCheck",e);
		}
	}	
	
	@Test(priority = 37, enabled = true)
	public void ltiLogin37()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1193");				
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin37 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 38, enabled = true)
	public void ltiLogin38()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1193");
			Driver.driver.quit();
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1194");				
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin38 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 39, enabled = true)
	public void ltiLogin39()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1193");
			Driver.driver.quit();
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1195");				
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin39 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 40, enabled = true)
	public void ltiLogin40()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1196");			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin40 in class LoginCheck",e);
		}
	}	

	@Test(priority = 41, enabled = true)
	public void ltiLogin41()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1196");
			Driver.driver.quit();
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1197");				
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin41 in class LoginCheck",e);
		}
	}
	
	@Test(priority = 42, enabled = true)
	public void ltiLogin42()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1196");
			Driver.driver.quit();
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1198");				
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ltiLogin42 in class LoginCheck",e);
		}
	}
		
	@AfterMethod
	 public void TearDown()throws Exception
	 {
	  new Screenshot().captureScreenshotFromTestCase();
	  Driver.driver.quit();
	 }
	
}
