package com.unifier.automation.dmautomation.LoginPage;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.unifier.automation.dmautomation.testBase.TestBase;
import com.unifier.automation.dmautomation.uiActions.DMActions;
import com.unifier.automation.dmautomation.uiActions.LoginPage;
import com.unifier.automation.dmautomation.uiActions.UnifierHome;

public class TC001_VerifyLoginwithInvalidCredentials extends TestBase{
		public static final Logger log = Logger.getLogger(TC001_VerifyLoginwithInvalidCredentials.class.getName());
		//WebDriver driver;
		LoginPage loginPage;
		UnifierHome home;
		DMActions dmActions;
		
		
		@DataProvider(name="loginData")
		public String[][] getTestData() 
		{
			String[][] testRecords = getData("TestData.xlsx","LoginTestData");
			return testRecords;
		}
		
		@BeforeTest
		public void setUp()
		{
			init();
		}
		
		@Test(dataProvider="loginData")
		public void VerifyLoginwithInvalidCredentials (String userName , String Password , String runMode)
		{
				if(runMode.equalsIgnoreCase("n"))
					{
						throw new SkipException("User marked this record as no run");
					}
				log.info("------------Starting Test-------------");
				loginPage = new LoginPage(driver);
				loginPage.VerifyLogin(userName,Password);
				driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
				home = new UnifierHome(driver);
				boolean status=home.isSignoutPresent();
				if(status)
				{
					try 
					{
						//Thread.sleep(10000);
						//home.clickDM();
						//log.info("DM Clicked");
						//home.clickSignOut();
						Thread.sleep(10000);
				
						dmActions = new DMActions(driver);
						dmActions.switchToFrame();
						dmActions.createNewFolderWindow();
						Thread.sleep(5000);
						
						dmActions.switchToCreateFolder();
						dmActions.createFolder();
						dmActions.switchToMainWindow();
						dmActions.switchToFrame();
						
						Assert.assertTrue(dmActions.verifyFolder(), "Folder Not Created");
						log.info("Folder created successfully");
						Thread.sleep(1000);
						dmActions.clickFolderName();
						Thread.sleep(5000);
						dmActions.deleteFolder();
						Assert.assertFalse(dmActions.verifyFolder());
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				log.info("------------Finished Test-------------");
				
		}
		
		@AfterTest
		public void endTest()
		{
			
			closeBrowser();
		}

		
}
