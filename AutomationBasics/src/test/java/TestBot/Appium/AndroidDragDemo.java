package TestBot.Appium;

import io.appium.java_client.AppiumBy;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class AndroidDragDemo extends AndroidBase {

	@Test
	public void DragDemoTest() throws MalformedURLException, URISyntaxException {

		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
	WebElement dragElemet=driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
		//dragAndDropActionBypositon(290, 755, 830, 760, 75);
		String dragged= driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Dropped!']")).getText();
		Assert.assertEquals("Dropped!", dragged);
		
		
	}

}
