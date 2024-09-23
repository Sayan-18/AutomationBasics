package TestBot.Appium;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AndroidEcommerce_tc_4_Hybrid extends AndroidBase {
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
		String termsText = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/alertTitle")).getText();
		assertEquals(termsText, "Terms Of Conditions");
		driver.findElement(AppiumBy.id("android:id/button1")).click();
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		Thread.sleep(3000);
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();
		Thread.sleep(5000);

		// From here app is redirected to we view where Android driver will not work as
		// it was native driver. In Android to interact with the browser we need to
		// inform the driver about this change. So, After landing into webview we need
		// to check the context of the driver then need to change the specified view
		// name which is given by the developer. Bellow two are the context's for this
		// app.
		// NATIVE_APP
		// WEBVIEW_com.androidsample.generalstore

		Set<String> contexts = driver.getContextHandles();
		for (String ContextName : contexts) {
			System.out.println(ContextName);
		}
		driver.context("WEBVIEW_com.androidsample.generalstore");
		driver.findElement(By.name("q")).sendKeys("https://www.w3schools.com/");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

		/*
		 * This two lines will be executed without changing the context and without
		 * chrome driver setup
		 * driver.findElement(By.xpath("//android.widget.EditText")).sendKeys("Google");
		 * driver.findElement(By.xpath("//android.widget.EditText")).sendKeys(Keys.ENTER
		 * );
		 */

		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		//Again returned back to app view so need to change the context
		driver.context("NATIVE_APP");
		String nativeBackText = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title"))
				.getText();
		assertEquals(nativeBackText, "General Store");
		Thread.sleep(5000);
	}
}
