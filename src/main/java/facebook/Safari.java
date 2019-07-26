package facebook;

import java.util.logging.*;
import org.openqa.selenium.*;
import org.openqa.selenium.safari.*;

public class Safari {
	public static void main(String[] args) throws InterruptedException {
		Logger.getLogger("").setLevel(Level.OFF);
		
		WebDriver driver;
		driver = new SafariDriver();
		driver.manage().window().maximize();
		
		System.out.println("Browser is: Safari");
		
		driver.get("https://www.facebook.com/");
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
