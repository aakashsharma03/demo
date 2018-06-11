package com.unifier.automation.dmautomation.customListener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	private int retryCount = 0;
	private int maxRetryCount = 2;
	public boolean retry(ITestResult result) {
		if(retryCount<maxRetryCount)
		{
			System.out.println("Retrying test "+result.getName()+" with Status "+getResultStatusName(result.getStatus())+ " for the "+ (retryCount+1)+" time(s).");
			retryCount++;
			
			return true;
		}
		return false;
	}
	public String getResultStatusName(int status)
	{
		String restultName = null;
		if(status==1)
			restultName="SUCCESS";
		if(status==2)
			restultName="FAILURE";
		if(status==3)
			restultName="SKIP";
		return restultName;
		
	}

}
