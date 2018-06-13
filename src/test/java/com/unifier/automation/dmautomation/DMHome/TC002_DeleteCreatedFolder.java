package com.unifier.automation.dmautomation.DMHome;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.unifier.automation.dmautomation.LoginPage.TC001_VerifyLoginwithInvalidCredentials;
import com.unifier.automation.dmautomation.testBase.TestBase;
import com.unifier.automation.dmautomation.uiActions.DMActions;

public class TC002_DeleteCreatedFolder extends TestBase {
	public static final Logger log = Logger.getLogger(TC001_VerifyLoginwithInvalidCredentials.class.getName());
	//WebDriver driver;
	DMActions dmActions;
	
	
	@Test
	public void DeleteFolder ()
	{
			log.info("Starting Test ");
	
				try 
				{
					 	
					dmActions = new DMActions(driver);
					dmActions.switchToFrame();
					//dmActions.clickFolderName();
					Thread.sleep(5000);
					
					dmActions.switchToCreateFolder();
					dmActions.createFolder();
					dmActions.switchToMainWindow();
					dmActions.switchToFrame();
					
					Assert.assertTrue(dmActions.verifyFolder(), "Folder is created");
					log.info("Folder created successfully");
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			
				log.info("------------Finished Test-------------");
			
	}
	
}
