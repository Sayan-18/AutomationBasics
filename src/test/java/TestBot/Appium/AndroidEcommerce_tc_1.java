package TestBot.Appium;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import junit.framework.Assert;

public class AndroidEcommerce_tc_1 extends AndroidBase{
	@Test
	public void fillForm() throws InterruptedException {
		//driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Sayan");
		//driver.hideKeyboard();// used for closing the keyboard;
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Bangladesh\"))"));
		WebElement country= driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Bangladesh\")"));
		country.click();
		String countryText=country.getText();
		Assert.assertEquals(countryText, "Bangladesh");
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"com.androidsample.generalstore:id/btnLetsShop\"]")).click();
		
		//Use this when toast elements are accessible.need to comment the name input to test this error message.
		//String toastText=driver.findElement(AppiumBy.xpath("//android.widget.Toast[@text=\"Please enter your name\"]")).getText();
		
		//When we can't reach the element via appium inspector then we can use this technique that to make a toast message developer will definitely use "android.widget.Toast". So by using this and indexing with it when there are multiple toast we can reach the toast element. Also with this if we use .getAttribute("name"). we will get the content too.
		String toastText=driver.findElement(AppiumBy.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		Assert.assertEquals(toastText, "Please enter your name");
		Thread.sleep(2000);
	}
}
