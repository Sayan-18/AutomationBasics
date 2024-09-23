package TestBot.Appium;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import junit.framework.Assert;

public class AndroidEcommerce_tc_2 extends AndroidBase{
	@Test
	public void fillForm() throws InterruptedException {
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Sayan");
		driver.hideKeyboard();// used for closing the keyboard;
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Bangladesh\"))"));
		WebElement country= driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Bangladesh\")"));
		country.click();
		String countryText=country.getText();
		Assert.assertEquals(countryText, "Bangladesh");
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"com.androidsample.generalstore:id/btnLetsShop\"]")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"))"));
		
		int productCount= driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).size();
		for(int i=0;i<productCount;i++) {
			String ProductName= driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			System.out.println(ProductName);
			if(ProductName.equalsIgnoreCase("Jordan 6 Rings")) {
				driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
			}
		}
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")),"text", "Cart"));
		String CartProductText=driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/productName")).getText();
		assertEquals(CartProductText, "Jordan 6 Rings");
		Thread.sleep(5000);
	}
}
