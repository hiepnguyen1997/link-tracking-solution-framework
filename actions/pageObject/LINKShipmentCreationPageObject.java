package pageObject;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageObject.Menu.ListMenuPageObject;

public class LINKShipmentCreationPageObject extends ListMenuPageObject{
	private WebDriver driver;
	
	public LINKShipmentCreationPageObject (WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
