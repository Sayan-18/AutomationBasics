package TestBot.Appium;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import junit.framework.Assert;

public class AndroidEcommerce_tc_1 extends AndroidBase{
	@Test
	public void fillForm() {
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Sayan");
		//driver.hideKeyboard();// used for closing the keyboard;
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"India\"))")).click();
	}
}
