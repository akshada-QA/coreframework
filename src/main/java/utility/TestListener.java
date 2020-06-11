package utility;

import java.util.List;

import org.testng.IAnnotationTransformer;
import org.testng.IConfigurable;
import org.testng.IConfigurationListener;
import org.testng.IConfigureCallBack;
import org.testng.IExecutionListener;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.IInvokedMethodListener;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.IReporter;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.core.coreframework.BaseTest;

public class TestListener extends BaseTest implements ITestListener,ISuiteListener,IReporter,IMethodInterceptor ,IInvokedMethodListener ,IHookable ,IExecutionListener,IConfigurationListener ,IConfigurable ,IAnnotationTransformer {
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
	
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent
			try {																		// report
			String screenshotPath = Screenshot.getScreenshot(result.getName());
			test.addScreenCaptureFromPath(screenshotPath);// adding screen shot
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			
			
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(IConfigureCallBack callBack, ITestResult testResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(IHookCallBack callBack, ITestResult testResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
