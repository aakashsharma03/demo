package com.unifier.automation.dmautomation.uiActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UnifierHome {

	WebDriver driver;
	
	@FindBy(id="homeTab")
	WebElement homeTab;
	
	@FindBy(linkText="Sign Out")
	WebElement signOut;
	
	public UnifierHome(WebDriver driver) 
	{
		PageFactory.initElements(driver,this);
	}
	
	public boolean isSignoutPresent()
	{	
		try
		{
			signOut.isDisplayed();
			return true;
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			return false;
		}
	}
	public void clickSignOut()
	{
		signOut.click();
	}
}
