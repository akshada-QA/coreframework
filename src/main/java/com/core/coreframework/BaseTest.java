package com.core.coreframework;

import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.core.coreframework.customExcptions.PropertyNotFoundExption;

public class BaseTest {

	protected static Logger logger = Logger.getLogger(BaseTest.class);

	public static BaseTest objectTB = null;
	public static WebDriver driver ;
	private Properties props;

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public static ExtentTest test;

//	Singleton design 
	protected BaseTest() {

		PropertyConfigurator.configure("log4j.properties");
		props = new Properties();
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/config.properties");
			props.load(fis);
		} catch (Exception e) {
			// catch Configuration Exception right here
		}
	}

	public synchronized static BaseTest getInstance() {
		if (objectTB == null)
			objectTB = new BaseTest();
		return objectTB;
	}

	// get property value by name
	public String getProperty(String key) {

		String value = null;
		if (props.containsKey(key)) {

			value = (String) props.get(key);

		} else {
			throw new PropertyNotFoundExption(key);
		}
		return value;

	}
	// String propValue = TestBase.getInstance().getProperty("browser");

	// browser open for every test case
	@BeforeMethod 
	public void SetupEnv() {

		String browserName = BaseTest.getInstance().getProperty("browser");

		if ("chrome".equals(browserName)) {
			logger.info("browser is open");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
			driver = new ChromeDriver();
		}

		else if ("firefox".equals(browserName)) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/geckodriver.exe");
			driver = new FirefoxDriver();

		} else if ("IE".equals(browserName)) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/IEDriverServer.exe");
			driver = new InternetExplorerDriver();

		}
		//System.out.println(Integer.parseInt( BaseTest.getInstance().getProperty("timeout")));
		driver.manage().timeouts().implicitlyWait(Integer.parseInt( BaseTest.getInstance().getProperty("timeout")), TimeUnit.SECONDS);
		driver.get(BaseTest.getInstance().getProperty("url"));
		driver.manage().deleteAllCookies();

	}

	// extent report
	@BeforeTest
	public void setExtent() {
		// specify location of the report
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/myReport.html");
		// test.log = extent.createTest(testName, desc); // enter testName, desc....

		htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report
		htmlReporter.config().setReportName("Hopscotch Testing"); // Name of the report
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		// Passing General information
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "Akshada");
	}

	@AfterTest
	public void endReport() {
		extent.flush();
	}

}
