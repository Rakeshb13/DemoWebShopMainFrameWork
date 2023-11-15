package genericlibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Properties;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import net.bytebuddy.asm.Advice.Local;
import pomrepository.HomePage;
import pomrepository.LoginPage;

public class UtilityMethods {
	
	
	
	public String getDataFromProperties(String key) throws IOException
	{
		FileInputStream fis = new FileInputStream("./src/test/resources/TestData/PropertiesFile.properties");
		Properties pobj=new Properties();
		pobj.load(fis);
		return pobj.getProperty(key);
	}
	
	public String getDataFromExcel(String sheetName, int rowNum,int cellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream("./src/test/resources/TestData/ExcelFile.xlxs");
		Workbook book = WorkbookFactory.create(fis);
		Sheet sh=book.getSheet(sheetName);
		return sh.getRow(rowNum).getCell(cellNum).toString();
	}
	
	public static String getCurrentTime()
	{
		LocalDateTime l= LocalDateTime.now();
		String name=l.toString().replace(":", "-");
		return name;
	}
	
	public void selectDropDownByValue(WebElement dropdown,String value)
	{
		Select s= new Select(dropdown);
		s.selectByValue(value);
	}
	
	public void selectDropDownByVisibleText(WebElement dropdown,String visibleText)
	{
		Select s= new Select(dropdown);
		s.selectByValue(visibleText);
	}
	
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	public void dismissAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	public String getAlertText(WebDriver driver)
	{
		return driver.switchTo().alert().getText();
	}
	
	public void passDataToAlert(WebDriver driver,String value)
	{
		driver.switchTo().alert().sendKeys(value);
	}
	
	public void waitUntilVisibilityOfElement(WebDriver driver,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitUntilInVisibilityOfElement(WebDriver driver,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void waitUntilPresenceOfElement(WebDriver driver, By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void switchToWindow(WebDriver driver, String sessionId)
	{
		driver.switchTo().window(sessionId);
	}
	
	public void takeScreenshot(WebDriver driver)
	{
		String path="./Screenshot/"+UtilityMethods.getCurrentTime()+".png";
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg=new File(path);
		try {
			FileHandler.copy(src, trg);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}