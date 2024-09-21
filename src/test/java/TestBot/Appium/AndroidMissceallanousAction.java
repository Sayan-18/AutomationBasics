package TestBot.Appium;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class AndroidMissceallanousAction extends AndroidBase {

	@Test
	/*Packages and activities. Here package means the app and activity means every page or
	 * functionality within the app.**adb shell dumpsys window | find "mCurrentFocus"**- to find the
	 * current app package.
	 * "io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"- Here
	 * the content before the slash is package info and after that is the activity/page info.
	 * we can use this info to start the test directly from any specific page.
	 * */
	public void missceallanousTest() throws MalformedURLException, URISyntaxException {
		((JavascriptExecutor)driver).executeScript("mobile:startActivity", ImmutableMap.of("intent","io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"));
		
		driver.findElement(AppiumBy.id("android:id/checkbox")).click();

		// This will rotate the device in landscape mode.
		DeviceRotation landscape = new DeviceRotation(0, 0, 90);
		driver.rotate(landscape);

		driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();
		String popUpText = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
		assertEquals(popUpText, "WiFi settings");

		// This will work like copying the content in clipboard.
		driver.setClipboardText("Sayan");
		// Here content will be paste from the clipboard.
		driver.findElement(AppiumBy.id("android:id/edit")).sendKeys(driver.getClipboardText());
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();

		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		System.out.println("back key pressed");
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
		System.out.println("Home key pressed");
	}
}