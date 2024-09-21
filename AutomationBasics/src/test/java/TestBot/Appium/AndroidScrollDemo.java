package TestBot.Appium;

import io.appium.java_client.AppiumBy;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;
import java.net.MalformedURLException;
import java.net.URISyntaxException;


public class AndroidScrollDemo extends AndroidBase {

	@Test
	public void ScrollDemoTest() throws MalformedURLException, URISyntaxException {

		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		// When you have prior idea where to scroll then use this. androidUiAutomator
		// will directly communicate with google engine and work.
		// driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new
		// UiSelector()).scrollIntoView(text(\"WebView\"))"));

		// When you do not have prior idea how much you need to scroll use this.
		scrollToEndAction();
		
	}
	
}
