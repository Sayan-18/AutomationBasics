package TestBot.Appium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AndroidMobileBrowserTest extends AndroidBrowserBase {

	@Test
	public void mobileBrowserTest() throws InterruptedException {
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//button[@aria-label='Toggle navigation']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Products ')]")).click();
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,1000)", "");
		String product = driver.findElement(By.xpath("//a[contains(text(),'Devops')]")).getText();
		System.out.println(product);
		Assert.assertEquals(product, "Devops");
		Thread.sleep(5000);
	}
}
