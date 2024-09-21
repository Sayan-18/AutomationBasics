package TestBot.Appium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.Duration;
import java.util.HashMap;

public class AndroidBase {

	AppiumDriverLocalService server;
	public AndroidDriver driver;

	// Method to create log file with a unique timestamp
	private File createLogFile() throws IOException {
		// Create timestamp
		String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		// Define log file path with timestamp
		String logFilePath = "C:\\Users\\datsayan\\Documents\\Learning\\Sayan\\Appium\\logs\\AppiumLog_" + timestamp
				+ ".txt";
		File logFile = new File(logFilePath);

		// Ensure the log directory exists
		logFile.getParentFile().mkdirs();

		// Create new log file
		logFile.createNewFile();
		return logFile;
	}

	@BeforeClass
	public void configureAppium() throws MalformedURLException, URISyntaxException, IOException {
		// Create a log file for each test run with a timestamp
		// File logFile = createLogFile();

		// Redirect System.out and System.err to the log file
		// PrintStream logStream = new PrintStream(new FileOutputStream(logFile));
		// System.setOut(logStream); // Redirect System.out to log file
		// System.setErr(logStream); // Redirect System.err to log file

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
		options.setApp(
				"C:\\Users\\datsayan\\Documents\\Learning\\Sayan\\Appium\\src\\test\\resources\\ApiDemos-debug.apk");

		driver = new AndroidDriver(new URI("http://127.0.0.1:4725").toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		System.out.println("Android Driver setup complete.");
	}

	public void longPressAction(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 2000));
		// Map will take key value pair. to tell the driver how long should we press the
		// element we need to mention the duration.
	}

	public void scrollToEndAction() {
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 500, "top", 500, "width", 600, "height", 600, "direction", "down", "percent", 25.0));
		} while (canScrollMore);
	}
	
	public void SwipeAction(WebElement ele, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"width",100,
			    "elementId",((RemoteWebElement)ele).getId(),
			    "direction", direction,
			    "percent", 0.1
			));
	}
	
	public void dragAndDropActionBypositon(int startX, int startY,int endX, int endY, int speed) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "startX",startX,
			    "startY",startY,
			    "endX", endX,
			    "endY", endY,
			    "speed", 75
			));
	}
	
	public void dragAndDropActionByid(WebElement ele,int endX, int endY, int speed) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "element",((RemoteWebElement)ele).getId(),
			    "endX", endX,
			    "endY", endY,
			    "speed", 75
			));
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