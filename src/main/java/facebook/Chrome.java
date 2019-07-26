package facebook;

import java.util.logging.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Chrome {
	public static void main(String[] args) throws InterruptedException {
		Logger.getLogger("").setLevel(Level.OFF);
		
		WebDriver driver;
		
		String driverPath = "";
		if (System.getProperty("os.name").toUpperCase().contains("MAC"))
			driverPath = "./resources/webdrivers/mac/chromedriver";
		else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
			driverPath = "./resources/webdrivers/pc/chromedriver.exe";
		else if (System.getProperty("os.name").toUpperCase().contains("LINUX"))
			driverPath = "./resources/webdrivers/linux/chromedriver";
		else throw new IllegalArgumentException("Unknown OS");
		
		System.setProperty("webdriver.chrome.driver", driverPath);
		System.setProperty("webdriver.chrome.silentOutput", "true");
		
		//Disable browser level notification
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-notifications");

		//Now Pass ChromeOptions instance to ChromeDriver Constructor to initialize chrome driver which will disable browser notification on the chrome browser
		driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		
		System.out.println("Browser is: Chrome");
		
		driver.get("https://facebook.com/");
		Thread.sleep(1000);
		
		System.out.println("Title: " + driver.getTitle());
	
		//Login into Facebook
		driver.findElement(By.id("email")).sendKeys("a.lukashevich2019@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("************");
		driver.findElement(By.xpath("//*[@value='Log In']")).click();
		
		Thread.sleep(3000);
		
		//Click on Timeline
		driver.findElement(By.xpath("//*[@title='Profile']/span/span")).click();
		
		Thread.sleep(3000);
		
		//Output how namy friends user has
		String friends = driver.findElement(By.xpath("//*[@id='fbTimelineHeadline']/div[2]/ul/li[3]/a/span[1]")).getText();
		System.out.println("You have: " + friends + " friends");
		
		Thread.sleep(5000);
		
		//Click on Account Settings
		driver.findElement(By.id("userNavigationLabel")).click();
		
		Thread.sleep(2000);
		
		//Click on Log out button
		driver.findElement(By.xpath("//*[contains(text(),'Log Out')]")).click();
		
		driver.quit();
		
	}

}
