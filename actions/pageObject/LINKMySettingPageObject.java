package pageObject;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageObject.Menu.ListMenuPageObject;
import pageUI.LINKMySettingPageUI;

public class LINKMySettingPageObject extends ListMenuPageObject {
	private WebDriver driver;

	public LINKMySettingPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickOnThirdPartyCheckboxByName(String thirdPartyName) {
		if (!isElementSelected(driver, LINKMySettingPageUI.ACTIVATE)) {
			checkToCustomCheckboxRadio(driver, LINKMySettingPageUI.ACTIVATE);
			checkToCustomCheckboxRadio(driver, LINKMySettingPageUI.ACTIVATE);
			checkToCustomCheckboxRadio(driver, LINKMySettingPageUI.THIRD_PARTY_CHECKBOX_BY_NAME, thirdPartyName);
			isElementSelected(driver, LINKMySettingPageUI.THIRD_PARTY_CHECKBOX_BY_NAME, thirdPartyName);
		} else {
			checkToCustomCheckboxRadio(driver, LINKMySettingPageUI.ACTIVATE);
			checkToCustomCheckboxRadio(driver, LINKMySettingPageUI.THIRD_PARTY_CHECKBOX_BY_NAME, thirdPartyName);
			isElementSelected(driver, LINKMySettingPageUI.THIRD_PARTY_CHECKBOX_BY_NAME, thirdPartyName);
		}
		clickOnSaveButton();
		delay(3);
	}
	
	public void clickOnActiveCheckbox() {
		if(!isElementSelected(driver, LINKMySettingPageUI.ACTIVATE)) {
			checkToCustomCheckboxRadio(driver, LINKMySettingPageUI.ACTIVATE);
			waitForElementClickabled(driver, LINKMySettingPageUI.SAVE_BUTTON);
			clickOnSaveButton();
			delay(3);
		}
	}
	
	public void clickOnSaveButton() {
		waitForElementClickabled(driver, LINKMySettingPageUI.SAVE_BUTTON);
		clickToElement(driver, LINKMySettingPageUI.SAVE_BUTTON);
	}
	

}
