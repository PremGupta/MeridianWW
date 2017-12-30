package MeridianPackages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import MeridianPackages.Announcements;
import MeridianPackages.AnnouncementsFullCode;

public class Screenshot {
	public static String Randomscreenshotname;

	public static void getscreenshot(WebDriver driver, int randomNumber) throws IOException {
		File snapshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String screenshotname = "Test123";
		String filetype = ".png";
		String Randomscreenshotname = screenshotname + randomNumber + filetype;

		System.out.println("Randome image name");
		System.out.println(Randomscreenshotname);

		FileUtils.copyFile(snapshot, new File("C:\\Users\\Prem\\Meridian\\Screenshot\\" + Randomscreenshotname));
		System.out.println("Verified Created Announcement");
	}

	public static void failedStepsScreenshot(WebDriver driver, int randomNumber) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String screenshotname = "Test123";
		String filetype = ".png";
		String Randomscreenshotname = screenshotname + randomNumber + filetype;
		System.out.println("Failed script screenshot taken");
		System.out.println(Randomscreenshotname);

		FileUtils.copyFile(src,
				new File("C:\\Users\\Prem\\Meridian\\FailedStepsScreenshots\\" + "Failed" + Randomscreenshotname));

	}
	
}
