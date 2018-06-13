package com.unifier.automation.dmautomation.customListener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.unifier.automation.dmautomation.testBase.TestBase;

public class Listener extends TestBase implements ITestListener {
	WebDriver driver;
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) {
		Object curentClass = result.getInstance();
		this.driver = ((TestBase)curentClass).getDriver();
				
				
				
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String methodName = result.getName();
		if(!result.isSuccess())	
		{File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				String reportDirectory = new File (System.getProperty("user.dir")).getAbsolutePath()+"\\src\\main\\java\\com\\unifier\\automation\\dmautomation";
				File destFile = new File((String)reportDirectory+"\\failure_screenshots\\"+methodName+"_"+formater.format(calendar.getTime())+".png");
				FileHandler.copy(srcFile, destFile);
				Reporter.log("<a href='"+destFile.getAbsolutePath()+"'> <img src='"+destFile.getAbsolutePath()+"' height='100' width='100'/> </a>");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

}
