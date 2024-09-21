package TestBot.Appium;

import java.util.Map;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class IOSLongPress extends iOSBaseTest {
	@Test
	public void iOSLongPressTest() throws InterruptedException {

		driver.findElement(AppiumBy.accessibilityId("Steppers")).click();
		WebElement ele = driver
				.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label==\"Increment\"`][3]"));
		
		Map<String, Object> params = longPress(ele);;
		driver.executeScript("mobile:touchAndHold", params );
		

	}
}
