package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{
	
	private int retryCount = 0;
	private static final int maxRetryCount = 3;
	
	@Override
	public boolean retry(ITestResult result) {
		if(retryCount < maxRetryCount) {
			retryCount++;
			System.out.println("Retrying test " + result.getName() + " for the " + retryCount + " time.");
		}
		return false; // Return false to stop retrying after max attempts
}

}
