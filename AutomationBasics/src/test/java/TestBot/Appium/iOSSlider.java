package TestBot.Appium;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import junit.framework.Assert;

public class iOSSlider extends iOSBaseTest {
	@Test
	public void iOSSliderTest() throws InterruptedException {

		WebElement val = driver.findElement(AppiumBy.className("XCUIElementTypeSlider"));
		val.sendKeys("1");
		Assert.assertEquals("100%",val.getAttribute("value"));
		Thread.sleep(3000);
		val=driver.findElement(AppiumBy.className("XCUIElementTypeSlider"));
		val.sendKeys("0");
		Assert.assertEquals("0%",val.getAttribute("value"));
		Thread.sleep(3000);
		val=driver.findElement(AppiumBy.className("XCUIElementTypeSlider"));
		val.sendKeys(".5");
		Assert.assertEquals("57%",val.getAttribute("value"));
		Thread.sleep(3000);

	}
}
