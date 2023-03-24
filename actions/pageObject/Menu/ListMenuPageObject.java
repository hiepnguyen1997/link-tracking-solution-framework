package pageObject.Menu;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import common.PageGenerateManagement;
import pageUI.LINKPageUIs;

public class ListMenuPageObject extends BasePage {

	private WebDriver driver;

	public ListMenuPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void openMenuByName(String menuName) {
		waitForElementClickabled(driver, LINKPageUIs.MENU_BY_NAME, menuName);
		clickToElement(driver, LINKPageUIs.MENU_BY_NAME, menuName);
	}

	public ListMenuPageObject openPageOnMenuByPageName(String menuName, String subMenuName) {
		openMenuByName(menuName);
		waitForElementClickabled(driver, LINKPageUIs.SUB_MENU_BY_NAME,menuName, subMenuName);
		hoverMouseToElement(driver, LINKPageUIs.SUB_MENU_BY_NAME,menuName, subMenuName);
		clickToElement(driver, LINKPageUIs.SUB_MENU_BY_NAME,menuName, subMenuName);
		if (menuName.equals("Settings")) {
			switch (subMenuName) {
			case "My settings":
				return PageGenerateManagement.getMySettingPage(driver);
			default:
				throw new RuntimeException("Invalid Sub Menu Name.");
			}
		} else if (menuName.equals("Transport Request")) {
			switch (subMenuName) {
			case "Search":
				return PageGenerateManagement.getTRSearchPage(driver);
			case "Create":
				return PageGenerateManagement.getTRCreationPage(driver);
			default:
				throw new RuntimeException("Invalid Sub Menu Name.");
			}
		} else {
			throw new RuntimeException("Invalid Menu Name");
		}
	}
	
}
