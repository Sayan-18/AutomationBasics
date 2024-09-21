package TestBot.Appium;

import io.appium.java_client.AppiumBy;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.Duration;

public class AndroidLongPress extends AndroidBase {

	@Test
	public void LongPressTest() throws MalformedURLException, URISyntaxException {
		// configureAppium(); as we have mentioned before class in base class so, testNG
		// will always search for before class and executes it if it's found. so no need
		// to call that function. It will be automatically executed by the testNG
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
		WebElement element = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"People Names\"]"));
		longPressAction(element);
		WebElement popUp = driver.findElement(AppiumBy.id("android:id/title"));
		String text=popUp.getText();
		boolean display=popUp.isDisplayed();
		Assert.assertEquals(text,"Sample menu");
		Assert.assertTrue(display);
	}
}