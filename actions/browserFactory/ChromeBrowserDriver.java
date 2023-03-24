package browserFactory;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeBrowserDriver implements BrowserFactory {
	String projectPath = System.getProperty("user.dir");
	@Override
	public WebDriver getBrowser() {
		System.setProperty("webdriver.chrome.driver", projectPath + File.separator + "browserDrivers" + File.separator + "chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-geolocation");
		return new ChromeDriver(options);
	}

}
