package pageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.PageGenerateManagement;
import pageObject.Menu.ListMenuPageObject;
import pageUI.LINKTransportRequestSearchUI;

public class LINKTransportRequestSearchPageObject extends ListMenuPageObject {
	private WebDriver driver;

	public LINKTransportRequestSearchPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickOnGoButtonToSearchRecord() {
		waitForElementVisible(driver, LINKTransportRequestSearchUI.GO_BUTTON_DOWN);
		scrollToElement(driver, LINKTransportRequestSearchUI.GO_BUTTON_DOWN);
		waitForElementClickabled(driver, LINKTransportRequestSearchUI.GO_BUTTON_DOWN);
		clickToElement(driver, LINKTransportRequestSearchUI.GO_BUTTON_DOWN);
	}

	public boolean isValueDisplayByCollumnName(String collumnName, String expectedTRName) {
		boolean mapping = false;
		int indexCollumn = getElementSize(driver, LINKTransportRequestSearchUI.INDEX_COLLUMN_BY_NAME, collumnName) + 1;
		List<String> allValueOfCollumn = new ArrayList<String>();
		waitForAllElementPresence(driver, LINKTransportRequestSearchUI.CELL_OF_COLLUMN_BY_NAME, String.valueOf(indexCollumn));
		List<WebElement> allCellOfCollumn = getListWebElement(driver, LINKTransportRequestSearchUI.CELL_OF_COLLUMN_BY_NAME, String.valueOf(indexCollumn));
		for (WebElement eachCell : allCellOfCollumn) {
			allValueOfCollumn.add(eachCell.getText().trim());
		}
		for (String value : allValueOfCollumn) {
			if (value.equals(expectedTRName)) {
				mapping = !mapping;
				break;
			}
		}
		if (mapping == false) {
			allValueOfCollumn.clear();
			waitForElementVisible(driver, LINKTransportRequestSearchUI.FILTER_BY_COLUMN_NAME, collumnName);
			clickToElement(driver, LINKTransportRequestSearchUI.FILTER_BY_COLUMN_NAME, collumnName);
			waitForElementVisible(driver, LINKTransportRequestSearchUI.OPERATOR_FIRST);
			selectItemInDefaultDropdown(driver, LINKTransportRequestSearchUI.OPERATOR_FIRST, "Contains");
			sendkeyToElement(driver, LINKTransportRequestSearchUI.VALUE_FILTER_FIRST, "iboucif");
			waitForElementClickabled(driver, LINKTransportRequestSearchUI.FILTER_BUTTON);
			clickToElement(driver, LINKTransportRequestSearchUI.FILTER_BUTTON);
			waitForElementInvisible(driver, LINKTransportRequestSearchUI.LOADING_ICON);
			waitForAllElementPresence(driver, LINKTransportRequestSearchUI.CELL_OF_COLLUMN_BY_NAME, String.valueOf(indexCollumn));
			allCellOfCollumn = getListWebElement(driver, LINKTransportRequestSearchUI.CELL_OF_COLLUMN_BY_NAME, String.valueOf(indexCollumn));
			for (WebElement eachCell : allCellOfCollumn) {
				allValueOfCollumn.add(eachCell.getText().trim());
			}
			for (String value : allValueOfCollumn) {
				if (value.equals(expectedTRName)) {
					mapping = !mapping;
					break;
				}
			}
		}
		return mapping;
	}

	public void clickOnCalendarAtCreationDate() {
		waitForElementClickabled(driver, LINKTransportRequestSearchUI.CALENDAR_CREATION_DATE);
		clickToElement(driver, LINKTransportRequestSearchUI.CALENDAR_CREATION_DATE);
		delay(1);
	}

	public void clickRangeAtCreationDate() {
		waitForElementClickabled(driver, LINKTransportRequestSearchUI.RANGE_CREATION_DATE);
		clickToElement(driver, LINKTransportRequestSearchUI.RANGE_CREATION_DATE);
		delay(1);
	}

	public void inputToFromTextboxAtCalendarCreationDate(String value) {
		waitForElementVisible(driver, LINKTransportRequestSearchUI.FROM_TEXTBOX_CREATION_DATE);
		sendkeyToElement(driver, LINKTransportRequestSearchUI.FROM_TEXTBOX_CREATION_DATE, value);
		delay(1);
	}

	public void inputToToTextboxAtCalendarCreationDate(String value) {
		waitForElementVisible(driver, LINKTransportRequestSearchUI.TO_TEXTBOX_CREATION_DATE);
		sendkeyToElement(driver, LINKTransportRequestSearchUI.TO_TEXTBOX_CREATION_DATE, value);
	}

	public boolean isFromInCalendarDisplayed() {
		waitForElementVisible(driver, LINKTransportRequestSearchUI.FROM_TEXTBOX_CREATION_DATE);
		return isElementDisplayed(driver, LINKTransportRequestSearchUI.FROM_TEXTBOX_CREATION_DATE);
	}

	public boolean isToInCalendarDisplayed() {
		waitForElementVisible(driver, LINKTransportRequestSearchUI.TO_TEXTBOX_CREATION_DATE);
		return isElementDisplayed(driver, LINKTransportRequestSearchUI.TO_TEXTBOX_CREATION_DATE);
	}

	public boolean isSliderInRangeDisplayed() {
		waitForElementVisible(driver, LINKTransportRequestSearchUI.SLIDER_RANGE_CREATION_DATE);
		return isElementDisplayed(driver, LINKTransportRequestSearchUI.SLIDER_RANGE_CREATION_DATE);
	}

	public void clickOnCalendarInEventCollapse() {
		waitForElementClickabled(driver, LINKTransportRequestSearchUI.CALENDAR_EVENTS_COLLAPSE);
		clickToElement(driver, LINKTransportRequestSearchUI.CALENDAR_EVENTS_COLLAPSE);
		delay(1);
	}

	public void inputToFromTextboxAtEventCollapse(String fromDate) {
		waitForElementVisible(driver, LINKTransportRequestSearchUI.FROM_TEXTBOX_EVENTS_COLLAPSE);
		sendkeyToElement(driver, LINKTransportRequestSearchUI.FROM_TEXTBOX_EVENTS_COLLAPSE, fromDate);
		delay(1);
	}

	public void inputToToTextboxAtEventCollapse(String toDate) {
		waitForElementVisible(driver, LINKTransportRequestSearchUI.TO_TEXTBOX_EVENTS_COLLAPSE);
		sendkeyToElement(driver, LINKTransportRequestSearchUI.TO_TEXTBOX_EVENTS_COLLAPSE, toDate);
		delay(1);
	}

	public void clickOnRoleDropdownList() {
		waitForElementVisible(driver, LINKTransportRequestSearchUI.ROLE);
		clickToElement(driver, LINKTransportRequestSearchUI.ROLE);
	}

	public void clickOnThirdDropdownList() {
		waitForElementVisible(driver, LINKTransportRequestSearchUI.THIRD_PARTY);
		clickToElement(driver, LINKTransportRequestSearchUI.THIRD_PARTY);
	}

	public void clickOnRerferenceDropdownList() {
		waitForElementVisible(driver, LINKTransportRequestSearchUI.REFERENCE);
		clickToElement(driver, LINKTransportRequestSearchUI.REFERENCE);
	}
	
	public void selectValueInReferenceDropdown(String fisrtCharacterOfValue, String expectedValue) {
		
		selectItemInEditableDropdown(driver, LINKTransportRequestSearchUI.INPUT_TO_REFERENCE_DROPDOWN, fisrtCharacterOfValue, LINKTransportRequestSearchUI.CHILD_LOCATOR_IN_DROPDOWN, expectedValue);
	}

	public void inputToRefValueTextbox(String refValue) {
		waitForElementVisible(driver, LINKTransportRequestSearchUI.REF_VALUE);
		sendkeyToElement(driver, LINKTransportRequestSearchUI.REF_VALUE, refValue);
	}

	public void clickOnAbsentCheckbox() {
		waitForElementClickabled(driver, LINKTransportRequestSearchUI.ABSENT_CHECKBOX_AT_REFERENCE);
		checkToCustomCheckboxRadio(driver, LINKTransportRequestSearchUI.ABSENT_CHECKBOX_AT_REFERENCE);
	}
	
	public LINKTRDetailPageObject clickOnEyeIconByLabelName(String labelName) {
		waitForElementVisible(driver, LINKTransportRequestSearchUI.EYE_ICON_BY_NAME, labelName);
		clickToElement(driver, LINKTransportRequestSearchUI.EYE_ICON_BY_NAME, labelName);
		return PageGenerateManagement.getTRDetailPage(driver);
	}

	public void clickOnCreationDateOptionMonth() {
		waitForElementVisible(driver, LINKTransportRequestSearchUI.CREATION_DATE_WEEK);
		clickToElement(driver, LINKTransportRequestSearchUI.CREATION_DATE_WEEK);
		
	}

	public LINKTRDetailPageObject clickOnEyeIconOfFirstLine() {
		waitForElementInvisible(driver, LINKTransportRequestSearchUI.LOADING_ICON);
		waitForElementVisible(driver, LINKTransportRequestSearchUI.EYE_ICON_FIRST_LINE);
		clickToElement(driver, LINKTransportRequestSearchUI.EYE_ICON_FIRST_LINE);
		return PageGenerateManagement.getTRDetailPage(driver);
	}
	
}
