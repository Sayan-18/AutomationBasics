package TestBot.Appium;

import java.time.Duration;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class iOSBasics extends iOSBaseTest  {
	@Test
	public void iOSBasicsTest() throws InterruptedException {
	driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
	driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == \"Text Entry\"`]")).click();
	driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeTextField")).sendKeys("Text send successfully");
	driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"OK\"`]")).click();
	//driver.manage().timeouts().wait(5);
	driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND value=='Confirm / Cancel'")).click();
	//driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c]'Confirm'")).click();
	//driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND value ENDSWITH[c]'Cancel' AND label== 'Confirm / Cancel'")).click();
	String popUp = driver.findElement(AppiumBy.iOSNsPredicateString("name BEGINSWITH[c] 'A message'")).getText();
	System.out.println(popUp);
	driver.findElement(AppiumBy.iOSNsPredicateString("label== 'Confirm'")).click();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
}
