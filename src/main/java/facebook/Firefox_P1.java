package facebook;

import java.util.logging.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Firefox_P1 {
	static String url = "http://facebook.com/";
	static String email = "a.lukashevich2019@gmail.com";
	static String password = "***********";
	
	static WebDriver driver; 
	
	public static void main(String[] args) {
		Logger.getLogger("").setLevel(Level.OFF);
		
		System.out.println("Browser: Firefox");
		
		String driverPath = "";
		if (System.getProperty("os.name").toUpperCase().contains("MAC"))
			driverPath = "./resources/webdrivers/mac/geckodriver.sh";
		else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
			driverPath = "./resources/webdrivers/pc/geckodriver.exe";
		else throw new IllegalArgumentException("Unknown OS");
		
		System.setProperty("webdriver.gecko.driver", driverPath);
		
		//disable browser-level notifications
		FirefoxOptions options = new FirefoxOptions();
		options.addPreference("dom.webnotifications.enabled", false);
		
		driver = new FirefoxDriver(options);
		
		// driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		//creating wait to assign explicit wait times
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		driver.manage().window().maximize();
		driver.get(url);
		// driver.findElement(By.id("email")).sendKeys(email);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys(email);
		
		//driver.findElement(By.id("pass")).sendKeys(password);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pass"))).sendKeys(password);
		
		//driver.findElement(By.xpath("//*[@value='Log In']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@value='Log In']"))).click();
		
		//Click on Timeline
		//driver.findElement(By.xpath("//*[@title='Profile']/span/span")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='Profile']/span/span"))).click();
		
		//Output how many friends user has
		//String friends = driver.findElement(By.xpath("//*[@id='fbTimelineHeadline']/div[2]/ul/li[3]/a/span[1]")).getText();
		String friends = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='fbTimelineHeadline']/div[2]/ul/li[3]/a/span[1]"))).getText();
		System.out.println("You have: " + friends + " friends");
		
		//Click on Account Settings
		//driver.findElement(By.id("userNavigationLabel")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("userNavigationLabel"))).click();
		
		//Click on Log out button
		//driver.findElement(By.xpath("//*[contains(text(),'Log Out')]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Log Out')]"))).click();
		
		driver.quit();
		
	}

}
