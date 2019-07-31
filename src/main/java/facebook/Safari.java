package facebook;

import java.util.Properties;
import java.util.logging.*;
import java.io.FileInputStream;

import org.openqa.selenium.*;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Safari {
	
	static String url = "http://facebook.com/";
	static String email = System.getenv("fb_email");
	static String password = System.getenv("fb_password");
	static Properties p = new Properties();
	
	static WebDriver driver; 
	
	public static void main(String[] args) throws Exception {
		Logger.getLogger("").setLevel(Level.OFF);
		p.load(new FileInputStream("./input.properties"));
		
		System.out.println("Browser: Safari");
		
		driver = new SafariDriver();
		
		// driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		//creating wait to assign explicit wait times
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		driver.manage().window().maximize();
		driver.get(url);
		// driver.findElement(By.id("email")).sendKeys(email);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("id_email")))).sendKeys(email);
		
		//driver.findElement(By.id("pass")).sendKeys(password);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("id_password")))).sendKeys(password);
		
		//driver.findElement(By.xpath("//*[@value='Log In']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(p.getProperty("xpath_login")))).click();
		
		//Click on Timeline
		//driver.findElement(By.xpath("//*[@title='Profile']/span/span")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(p.getProperty("xpath_timeline")))).click();
		
		//Output how many friends user has
		//String friends = driver.findElement(By.xpath("//*[@id='fbTimelineHeadline']/div[2]/ul/li[3]/a/span[1]")).getText();
		String friends = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(p.getProperty("xpath_friends")))).getText();
		System.out.println("You have: " + friends + " friends");
		
		//Click on Account Settings
		//driver.findElement(By.id("userNavigationLabel")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id(p.getProperty("id_settings")))).click();
		
		//Click on Log out button
		//driver.findElement(By.xpath("//*[contains(text(),'Log Out')]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(p.getProperty("xpath_logout")))).click();
		
		driver.quit();	
	}
}
