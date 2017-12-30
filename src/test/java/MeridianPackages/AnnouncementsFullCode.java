package MeridianPackages;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ObjectRepository_Announcements.AnnouncementsPage;
import ObjectRepository_Announcements.HomePage;
import ObjectRepository_Announcements.loginPage;

import resources.base;

public class AnnouncementsFullCode {

	public ExtentReports report;
	public ExtentTest logger;

	public static WebDriver driver;
	public static int randomnumber;

	public static Logger log = LogManager.getLogger(base.class.getName());

	@Test()

	public void AnnouncementCreation_Fullcode() throws IOException, InterruptedException {

		report=new ExtentReports("./Reports/ExecutionReport_AnnouncementCreation_Fullcode.html");
		logger=report.startTest("Start Testing: AnnouncementCreation_Fullcode");
		
		try {

			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Prem\\Meridian\\DriversExecutable\\chromedriver.exe");
			driver = new ChromeDriver();

			/*
			 * System.setProperty("webdriver.gecko.driver",
			 * "C:\\Users\\Prem\\Meridian\\DriversExecutable\\geckodriver.exe"); driver
			 * =new FirefoxDriver();
			 */

			/*
			 * System.setProperty("webdriver.ie.driver",
			 * "C:\\Users\\Prem\\Meridian\\DriversExecutable\\IEDriverServer.exe"); driver=
			 * new InternetExplorerDriver();
			 */
			driver.get("https://demosite.meridinet.com/template/login.html");

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			driver.findElement(By.id("username")).sendKeys("Meridian");
			driver.findElement(By.id("password")).sendKeys("Demo@09123");
			driver.findElement(By.xpath("//input[@value='Login']")).click();

			Thread.sleep(10000);
			WebElement admin = driver
					.findElement(By.xpath("//a[@class='dropdown-toggle has-submenu'] [text()='Admin']"));
			Actions act1 = new Actions(driver);
			act1.moveToElement(admin).build().perform();
			driver.findElement(By.xpath("//a[@class='dropdown-toggle has-submenu'][text()='Admin']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//a[text()='Announcements'])[2]")).click();

			driver.findElement(By.xpath("//input[@value='Create New Announcement']")).click();
			randomnumber = (int) (10 + Math.random() * ((10000 - 1) + 1));
			driver.findElement(By.xpath("//*[@id='sTitle']")).sendKeys("Test123" + randomnumber);
			driver.findElement(By.id("dFromDate")).click();
			driver.findElement(By
					.xpath("//*[starts-with(@class,'datepicker')]/div[1][@class='datepicker-days']/table/tbody/tr[5]/td[6]"))
					.click();
			driver.findElement(By.id("dThruDate")).click();
			driver.findElement(By
					.xpath("//*[starts-with(@class,'datepicker')]/div[1][@class='datepicker-days']/table/tbody/tr[5]/td[7]"))
					.click();
			driver.findElement(By.xpath("//input[@id='myCheckBox']")).click();

			Thread.sleep(10000);
			act1.moveToElement(driver.findElement(By.xpath(".//div[@id='cke_1_contents']/iframe"))).build().perform();
			WebElement iframeElement = driver.findElement(By.xpath(".//div[@id='cke_1_contents']/iframe"));

			driver.switchTo().frame(iframeElement);
			driver.findElement(By.xpath("html/body/p")).click();
			act1.moveToElement(driver.findElement(By.xpath("html/body/p"))).build().perform();
			act1.doubleClick(driver.findElement(By.xpath("html/body/p"))).sendKeys("shhdshaas").build().perform();
			driver.switchTo().defaultContent();

			driver.findElement(By.xpath("//button[@id='submit']")).click();

			Thread.sleep(5000);
			assertEquals(
					driver.findElement(By.xpath("//*[@id=\'announcement\']/div/div/div[1]/ul/li[1]/span")).getText(),
					"Test123" + randomnumber);
			System.out.println("Test123" + randomnumber);
			log.info("This test is passed as : " + "Test123" + randomnumber + " is created");
			Screenshot.getscreenshot(driver, randomnumber);

			Thread.sleep(5000);
			WebElement Approved = driver.findElement(By.xpath("//input[@value='Approved']"));
			act1.moveToElement(Approved).build().perform();
			act1.moveToElement(Approved).click();
			log.info("This test is passed as : " + "Test123" + randomnumber + " is Approved");
			logger.log(LogStatus.PASS, "Test Verified_AnnouncementCreation_Fullcode: PASSED");
			report.endTest(logger);
			report.flush();
		}

		catch (Exception e) {
			Screenshot.failedStepsScreenshot(driver, randomnumber);
			log.info("This test is Failed as : " + "Test123" + randomnumber + " is not created as Failed");
			e.printStackTrace();
			logger.log(LogStatus.FAIL, "Test Verified_AnnouncementCreation_Fullcode: FAILED");
			report.endTest(logger);
			report.flush();

		}
	}
		
	
	@AfterTest
	public void teardown() {

		driver.close();
		driver = null;

	}

}
