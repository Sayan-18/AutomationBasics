package TestBot.Appium;

import io.appium.java_client.AppiumBy;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class AndroidSwipeDemo extends AndroidBase {

	@Test
	public void SwipeDemoTest() throws MalformedURLException, URISyntaxException {

		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
		WebElement firstImage = driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]"));
		Assert.assertEquals(firstImage.getAttribute("focusable"), "true");
		SwipeAction(firstImage, "left");
		Assert.assertEquals(
				driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"),
				"false");

	}

}
