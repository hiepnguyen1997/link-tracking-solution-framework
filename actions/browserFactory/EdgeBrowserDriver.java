package browserFactory;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;
import static org.apache.commons.lang3.SystemUtils.IS_OS_MAC;

public class EdgeBrowserDriver implements BrowserFactory{
	String projectPath = System.getProperty("user.dir");
	@Override
	public WebDriver getBrowser() {
		if (!IS_OS_WINDOWS && !IS_OS_MAC) {
			throw new BrowserNotSupportedException("Edge not supported on " + System.getProperty("os.name"));
		}
		System.setProperty("webdriver.edge.driver", projectPath + File.separator + "browserDrivers" + File.separator + "msedgedriver.exe");
		return new EdgeDriver();
	}

}
