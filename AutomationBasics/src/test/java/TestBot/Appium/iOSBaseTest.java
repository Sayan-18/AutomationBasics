package TestBot.Appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class iOSBaseTest {

	AppiumDriverLocalService server;
	public IOSDriver driver;

	@BeforeClass
	public void configureAppium() throws MalformedURLException, URISyntaxException {

		HashMap<String, String> environment = new HashMap<String, String>();
		environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));

		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withAppiumJS(new File("/usr/local/bin/appium")).usingDriverExecutable(new File("/usr/local/bin/node"))
				.usingPort(4724).withEnvironment(environment).withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
				.withLogFile(new File("AppiumLog.txt"));

		server = AppiumDriverLocalService.buildService(builder);

		System.out.println("Server started at :" + server.getUrl());
		System.out.println("Server is Started.");
		server.start();
		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName("iPhone 12");
		//options.setApp(
			//	"/Users/datsayan/Documents/My Folder/Sayan/Appium/src/test/java/resources/UIKitCatalog.app");
		//options.setApp(
			//"/Users/datsayan/Documents/My Folder/Sayan/Appium/src/test/java/resources/TestApp 3.app");
		//options.setPlatformName("iOS"); 
		options.setPlatformVersion("17.4");
		options.setWdaLaunchTimeout(Duration.ofSeconds(10)); 
		driver = new IOSDriver(new URI("http://127.0.0.1:4724").toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public Map<String, Object> longPress(WebElement ele) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("element",((RemoteWebElement)ele).getId());
		params.put("duration",5);
		
		return params;
	}

	@AfterClass
	public void tearDown() {
		
		server.stop();
		driver.quit();
		System.out.println("Service stopped.");
	}
}
