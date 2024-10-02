package TestBot.Appium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.Duration;
import java.util.HashMap;

public class AndroidBrowserBase {

	AppiumDriverLocalService server;
	public AndroidDriver driver;

	@BeforeClass
	public void configureAppium() throws MalformedURLException, URISyntaxException, IOException {
		// Start writing log output
		System.out.println("Starting Appium Server...");

		// Set environment variables
		HashMap<String, String> environment = new HashMap<String, String>();
		environment.put("PATH", System.getenv("PATH"));

		// Create the Appium service builder with the correct paths for Windows
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withAppiumJS(
				new File("C:\\Users\\datsayan\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe")).usingPort(4725)
				.withEnvironment(environment).withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
				.withTimeout(Duration.ofSeconds(60));
		// .withLogFile(logFile); // Appium logs also go to the same file

		// Build and start the Appium server
		server = AppiumDriverLocalService.buildService(builder);
		server.start();

		System.out.println("Appium Server started at: " + server.getUrl());

		// Set up the driver with UiAutomator2Options
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("2a65445a3f047ece");
		options.setPlatformName("Android");
		options.setChromedriverExecutable(
				"C:\\Users\\datsayan\\Documents\\Learning\\MobileAutomation\\AppiumBasics\\src\\test\\resources\\chromedriver.exe");
		options.setCapability("browserName", "Chrome");
		driver = new AndroidDriver(new URI("http://127.0.0.1:4725").toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		System.out.println("Android Driver setup complete.");
	}


	public double getFormattedValue(String S) {
		Double D = Double.parseDouble(S.replace("$", ""));
		return D;
	}

	@AfterClass
	public void tearDown() throws IOException {
		// Safely stop the driver
		if (driver != null) {
			driver.quit();
			System.out.println("Driver quit successfully.");
		} else {
			System.out.println("Driver was null, skipping driver quit.");
		}

		// Safely stop the Appium server
		if (server != null && server.isRunning()) {
			server.stop();
			System.out.println("Appium server stopped successfully.");
		} else {
			System.out.println("Appium server was not running or was null.");
		}
	}
}