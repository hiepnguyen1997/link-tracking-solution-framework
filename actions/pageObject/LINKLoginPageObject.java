package pageObject;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import common.PageGenerateManagement;
import pageUI.LINKLoginPageUI;

public class LINKLoginPageObject extends BasePage{
	private WebDriver driver;

	public LINKLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputToUserNameAccount(String userNameAccount) {
		waitForElementVisible(driver, LINKLoginPageUI.USER_NAME);
		sendkeyToElement(driver, LINKLoginPageUI.USER_NAME, userNameAccount);
	}
	
	public void inputToPassword(String password) {
		waitForElementVisible(driver, LINKLoginPageUI.PASSWORD);
		sendkeyToElement(driver, LINKLoginPageUI.PASSWORD, password);
	}
	
	public LINKHomePageObject clickToGOButton() {
		waitForElementVisible(driver, LINKLoginPageUI.GO_BUTTON);
		clickToElement(driver, LINKLoginPageUI.GO_BUTTON);
		return PageGenerateManagement.getLINKHomePage(driver);
	}
	
	public LINKHomePageObject loginAsLINKUser(String userName, String password) {
		inputToUserNameAccount(userName);
		inputToPassword(password);
		return clickToGOButton();
	}
}
