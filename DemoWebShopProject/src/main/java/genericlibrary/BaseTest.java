package genericlibrary;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.xml.XmlTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import pomrepository.HomePage;
import pomrepository.LoginPage;

public class BaseTest {

	public WebDriver driver;
	public ExtentSparkReporter sparkReporter;
	public ExtentReports reports;
	public ExtentTest test;
	public UtilityMethods um = new UtilityMethods();
	public LoginPage lp;
	public HomePage hp;

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		sparkReporter = new ExtentSparkReporter("./Reports/"+um.getCurrentTime()+".html");
		reports = new ExtentReports();
		reports.attachReporter(sparkReporter);
	}

	@BeforeTest(alwaysRun = true)
	public void beforeTest() {
//		test= reports.createTest(ITestResult.class.getTypeName());
//		test.log(Status.INFO, "Execution Started");
	}

	@BeforeClass(alwaysRun = true)
	public void beforeClass(@Optional("Chrome")String browser) throws IOException {

		if (browser.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			//test.log(Status.INFO, "Chrome Browser Launched");
		} else if (browser.equals("Edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			//test.log(Status.INFO, "Edge Browser Launched");
		} else {
			System.out.println("Please enter a valid browser name");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(um.getDataFromProperties("url"));
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method) throws IOException {
		test= reports.createTest(method.getName());
		test.log(Status.INFO, "Execution Started");
		hp=new HomePage(driver);
		hp.getLoginLink().click();
		lp = new LoginPage(driver);
		test.log(Status.INFO, "Performing Login");
		lp.getEmailTextField().sendKeys(um.getDataFromProperties("un"));
		lp.getPasswordTextField().sendKeys(um.getDataFromProperties("pwd"));
		lp.getLoginButton().click();

	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		hp.getLogoutLink().click();
		test.log(Status.INFO, "Performing Logout");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.close();
	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {
		test.log(Status.INFO, "Execution Completed");
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		reports.flush();
	}
}