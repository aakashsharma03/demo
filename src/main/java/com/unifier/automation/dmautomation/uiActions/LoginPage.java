package com.unifier.automation.dmautomation.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

		WebDriver driver;
		
		public static final Logger log = Logger.getLogger(LoginPage.class.getName());
		
		@FindBy(id="username")
		WebElement userName;
		
		@FindBy(id="password")
		WebElement password;
		
		@FindBy(id="lsubmit")
		WebElement submitButton;
		
		public LoginPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
		
		
		public void VerifyLogin(String username , String pwd)
		{
			userName.clear();
			userName.sendKeys(username);
			log.info("Entered Username "+username );
			password.clear();
			password.sendKeys(pwd);
			log.info("Entered Password "+pwd);
			submitButton.click();
			log.info("Clicked Submit");
		}
		
		
		
}
