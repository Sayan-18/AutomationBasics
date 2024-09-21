package TestBot.Appium;

import io.appium.java_client.AppiumBy;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.Duration;

public class AppiumBasics extends AndroidBase {

	@Test
	public void WifiSettingsName() throws MalformedURLException, URISyntaxException {
		//configureAppium(); as we have mentioned before class in base class so, testNG will always search for before class and executes it if it's found. so no need to call that function. It will be automatically executed by the testNG
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		driver.findElement(AppiumBy.id("android:id/checkbox")).click();
		driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();
		String popUpText = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
		assertEquals(popUpText, "WiFi settings");
		System.out.println(popUpText);
		driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("Sayan");
		driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click(); 
		System.out.println(popUpText);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
}