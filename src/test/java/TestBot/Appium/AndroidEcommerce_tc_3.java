package TestBot.Appium;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class AndroidEcommerce_tc_3 extends AndroidBase {
	@Test
	public void fillForm() throws InterruptedException {
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Sayan");
		driver.hideKeyboard();// used for closing the keyboard;
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Bangladesh\"))"));
		WebElement country = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Bangladesh\")"));
		country.click();
		String countryText = country.getText();
		assertEquals(countryText, "Bangladesh");
		driver.findElement(AppiumBy
				.xpath("//android.widget.Button[@resource-id=\"com.androidsample.generalstore:id/btnLetsShop\"]"))
				.click();
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiSelector().resourceId(\"com.androidsample.generalstore:id/productAddCart\").instance(0)"))
				.click();
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiSelector().resourceId(\"com.androidsample.generalstore:id/productAddCart\").instance(1)"))
				.click();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(
				driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
		int productCount = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice")).size();
		System.out.println("productCount: " + productCount);
		Double TotalPrice = 0d;
		for (int i = 0; i < productCount; i++) {
			String Pricestr = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice")).get(i)
					.getText();
			System.out.println("Pricestr: " + Pricestr);
			Double Price = getFormattedValue(Pricestr);
			System.out.println("Price: " + Price);
			TotalPrice = TotalPrice + Price;
			System.out.println("TotalPrice: " + TotalPrice);
		}

		String CartTotalstr = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl"))
				.getText();
		Double cartTotal = getFormattedValue(CartTotalstr);
		System.out.println("cartTotal: " + cartTotal);
		assertEquals(TotalPrice, cartTotal);
		longPressAction(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/termsButton")));
		String termsText= driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/alertTitle")).getText();
		assertEquals(termsText,"Terms Of Conditions");
		driver.findElement(AppiumBy.id("android:id/button1")).click();
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		Thread.sleep(3000);
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();
		Thread.sleep(5000);
		
	}
}
