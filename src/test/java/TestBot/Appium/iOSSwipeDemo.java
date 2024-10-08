package TestBot.Appium;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class iOSSwipeDemo extends iOSBaseTest {
	@Test
	public void iOSSwipeTest() throws InterruptedException {

		Map<String, String> params = new HashMap<String, String>();
		params.put("bundleId", "com.apple.mobileslideshow");
		driver.executeScript("mobile:launchApp", params);
		driver.findElement(AppiumBy.accessibilityId("photo.fill.on.rectangle.fill")).click();
		List<WebElement> photos = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeCell"));
		System.out.println(photos.size());

		driver.findElement(AppiumBy.xpath("//XCUIElementTypeCell [1]")).click();

		for (int i = 1; i < photos.size(); i++) {
			Map<String, String> params1 = new HashMap<String, String>();
			params1.put("direction", "left");
			driver.executeScript("mobile:swipe", params1);
		}
		
		for (int i = 1; i < photos.size(); i++) {
			Map<String, String> params1 = new HashMap<String, String>();
			params1.put("direction", "right");
			driver.executeScript("mobile:swipe", params1);
		}
		
		driver.navigate().back();
		driver.findElement(AppiumBy.accessibilityId("Albums")).click();

	}
}
