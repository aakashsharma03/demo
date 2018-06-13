package com.unifier.automation.dmautomation.uiActions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.unifier.automation.dmautomation.testBase.TestBase;

public class DMActions extends TestBase{
	String name;
	String win1;
	String win2;
	EventFiringWebDriver   driver;
	WebElement fold;
	
	public static final Logger log = Logger.getLogger(DMActions.class.getName());
	
	@FindBy(id="newButton")
	WebElement createButton;
	
	@FindBy(xpath=".//li[@id='id-menu-newfolder']/a[contains(text(),'Folder')]")
	WebElement newFolder;
	
	@FindBy(xpath="//iframe[contains(@src,'/bp/dm/project_documents')]")
	WebElement iframe;
	
	@FindBy(id="node_name")
	WebElement folderName;
	
	@FindBy(id="btnOk")
	WebElement okBtn;
	
	@FindBy(xpath=".//input[@value='Cancel']")
	WebElement cancelBtn;
	
	@FindBy(id="actionButton")
	WebElement actionBtn;
	
	@FindBy(xpath=".//ul[@id='actionMenu']/li[@id='id-menu-delete'][1]/a[contains(text(),'Delete')]")
	WebElement deleteBtn;
	
	
	@FindBy(id="YesButton")
	WebElement acceptBtn;
	
	public DMActions(EventFiringWebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void switchToFrame()
	{
			driver.switchTo().frame(iframe);
			log.info("Switched to Frame");
	}
	public void createNewFolderWindow() throws InterruptedException
	{
		
		Thread.sleep(2000);
		log.info("clicking createButton");
		createButton.click();
		newFolder.click();
		log.info("Create new Folder clicked");
	}
	
	
	public void switchToCreateFolder()
	{
		Iterator<String> itr = getAllWindow(driver);
		log.info("backtoSwitchToFolder");
		win1= itr.next();
		win2=itr.next();
		switchToWindow(win2,driver);
		log.info("Switched to create window");
	}
	
	public void switchToMainWindow()
	{
		
		switchToWindow(win1,driver);
		log.info("Switched back to main window");
	}
	
	public void createFolder()
	{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String time = formater.format(calendar.getTime());
		name="Test"+time;
		folderName.clear();
		folderName.sendKeys(name);
		//cancelBtn.click();
		okBtn.click();
		log.info("Folder created with name "+name);
		
	}
	
	public boolean verifyFolder()
	{
		try
		{
			fold = driver.findElement(By.xpath(".//a[@role='menuitem'][contains(text(),'"+name+"')]"));
			fold.isDisplayed();
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	public void clickFolderName()
	{
		driver.findElement(By.xpath(".//a[@data-trigger='open-document'][contains(text(),'"+name+"')]/ancestor::tr")).click();
	}
	
	public void deleteFolder() throws InterruptedException
	{
		actionBtn.click();
		deleteBtn.click();
		Thread.sleep(2000);
		Iterator<String> itr = getAllWindow(driver);
		win1= itr.next();
		switchToWindow(win1,driver);
		acceptBtn.click();
		log.info("Folder deleted with name "+name);
	}
	
	
	
}
