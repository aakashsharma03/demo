package com.unifier.automation.dmautomation.testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;

import com.unifier.automation.dmautomation.customListener.WebEventListener;
import com.unifier.automation.dmautomation.excelReader.Excel_Reader;

public class TestBase {
	
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	public WebDriver dr;
	Excel_Reader excel;
	String url;
	String browser;
	public EventFiringWebDriver driver;
	public WebEventListener eventListener;
	Properties OR;
	public File file;
	FileReader filereader;
	public EventFiringWebDriver getDriver()
	{
		return driver;
	}
	
	public void setDriver(EventFiringWebDriver driver)
	{
		this.driver=driver;
	}
	
	public void init()
	{
		loadData();
		selectBrowser(browser);
		getURL(url);
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
	}
	
	public void loadData()
	{
		OR = new Properties();
		file=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\unifier\\automation\\dmautomation\\config\\config.properties");
		try {
				filereader= new FileReader(file);
				OR.load(filereader);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			url=OR.getProperty("url");
			browser=OR.getProperty("browser");
		
	}
	public void selectBrowser(String browser)
	{
		if (browser.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
			log.info("Creating object of "+browser);
			dr = new ChromeDriver();
			driver = new EventFiringWebDriver(dr);
			eventListener = new WebEventListener();
			driver.register(eventListener);
		}
		else if (browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
			log.info("Creating object of "+browser);
			dr = new FirefoxDriver();
			driver = new EventFiringWebDriver(dr);
			eventListener = new WebEventListener();
			driver.register(eventListener);
		}
	}
	
	public void getURL(String url)
	{
		log.info("Navigating to "+url);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
	}
	
	public String [][] getData(String excelName , String sheetName)
	{
		excel = new Excel_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\unifier\\automation\\dmautomation\\data\\"+excelName);
		String data [][]=excel.getDataFromSheet(sheetName, excelName);
		return data;
	}
	
	public void getScreenShot(String name)
	{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				String reportDirectory = new File (System.getProperty("user.dir")).getAbsolutePath()+"\\src\\main\\java\\com\\unifier\\automation\\dmautomation\\screenshot\\";
				File destFile = new File((String)reportDirectory+name+"_"+formater.format(calendar.getTime())+".png");
				FileHandler.copy(srcFile, destFile);
				Reporter.log("<a href='"+destFile.getAbsolutePath()+"'> <img src='"+destFile.getAbsolutePath()+"' height='100' width='100'/> </a>");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public void closeBrowser()
	{
		driver.close();
		driver.quit();
		log.info("Browser Closed");
	}
	
	public Iterator<String> getAllWindow(EventFiringWebDriver driver)
	{
		log.info("inside getAllWindow");
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		log.info("return itr");
		return itr;
	}
	
	public void switchToWindow(String win,EventFiringWebDriver driver)
	{
		driver.switchTo().window(win);
	}
	
}
