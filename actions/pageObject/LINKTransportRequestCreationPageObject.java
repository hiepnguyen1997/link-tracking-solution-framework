package pageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import pageObject.Menu.ListMenuPageObject;
import pageUI.LINKTransportRequestCreationUI;

public class LINKTransportRequestCreationPageObject extends ListMenuPageObject {
	private WebDriver driver;
	private int availableQuatity;

	public LINKTransportRequestCreationPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickOnClearButton() {
		waitForElementInvisible(driver, LINKTransportRequestCreationUI.LOADING_PAGE);
		waitForElementClickabled(driver, LINKTransportRequestCreationUI.CLEAR_UP_BUTTON);
		clickToElement(driver, LINKTransportRequestCreationUI.CLEAR_UP_BUTTON);
		delay(2);
		waitForElementInvisible(driver, LINKTransportRequestCreationUI.LOADING_PAGE);
		if (isElementDisplayed(driver, LINKTransportRequestCreationUI.LOADING_PAGE)) {
			waitForLoadingPageUnDisplay(driver);
		}
	}

	public boolean isErrorMessageAtTextboxNameDisplayed(String textboxName) {
		waitForElementClickabled(driver, LINKTransportRequestCreationUI.ERROR_MESSAGE_AT_TEXTBOX_BY_NAME, textboxName);
		return isElementDisplayed(driver, LINKTransportRequestCreationUI.ERROR_MESSAGE_AT_TEXTBOX_BY_NAME, textboxName);

	}

	public void clickOnSearchButtonInPOCollapse() {
		waitForElementClickabled(driver, LINKTransportRequestCreationUI.SEARCH_BUTTON_IN_PO_COLAPPSE);
		clickToElement(driver, LINKTransportRequestCreationUI.SEARCH_BUTTON_IN_PO_COLAPPSE);
		waitForElementInvisible(driver, LINKTransportRequestCreationUI.LOADING_POPUP);
		delay(3);
	}
	
	public void clickOnSearchButtonInPOLineAttachCollapse() {
		waitForElementClickabled(driver, LINKTransportRequestCreationUI.SEARCH_BUTTON_IN_PO_LINE_ATTACH_COLAPPSE);
		clickToElement(driver, LINKTransportRequestCreationUI.SEARCH_BUTTON_IN_PO_LINE_ATTACH_COLAPPSE);
		waitForElementInvisible(driver, LINKTransportRequestCreationUI.LOADING_POPUP);
		delay(3);
	}
	
	public void clickOnSearchButtonInAttachedTRCollapse() {
		waitForElementClickabled(driver, LINKTransportRequestCreationUI.SEARCH_BUTTON_IN_ATTACH_TR_COLAPPSE);
		clickToElement(driver, LINKTransportRequestCreationUI.SEARCH_BUTTON_IN_ATTACH_TR_COLAPPSE);
		waitForElementInvisible(driver, LINKTransportRequestCreationUI.LOADING_POPUP);
		delay(3);
	}

	public String getPONameAtPOLineAttachCollapse() {
		waitForElementVisible(driver, LINKTransportRequestCreationUI.PO_INSERT_NAME_IN_PO_LINE_ATTACH_COLAPPSE);
		return getElementText(driver, LINKTransportRequestCreationUI.PO_INSERT_NAME_IN_PO_LINE_ATTACH_COLAPPSE);
	}
	
	public String newAvailabeQuantityOfSelectedPO(String oldAvailableQuatity, int quantity) {
		String[] arrayQuantityStr = oldAvailableQuatity.split("/");
		float convertQuantity = Float.parseFloat(arrayQuantityStr[0]);
		this.availableQuatity = (int) convertQuantity;
		int newAvailableQuantityNum = this.availableQuatity - quantity;
		String newAvailableQuantityStr = String.valueOf(newAvailableQuantityNum);
		String fullNewAvailableQuantityStr = newAvailableQuantityStr + "/" + arrayQuantityStr[1];
		return fullNewAvailableQuantityStr;
	}

	public void clickOnDropdownByNameAtTransportRequestCollapse(String dropdownName) {
		waitForElementVisible(driver, LINKTransportRequestCreationUI.DROPDOWN_BY_NAME_AT_TRANSPORT_REQUEST_COLLAPSE, dropdownName);
		clickToElement(driver, LINKTransportRequestCreationUI.DROPDOWN_BY_NAME_AT_TRANSPORT_REQUEST_COLLAPSE, dropdownName);
	}
	
	public String getTextOnDropdownByNameAtTransportRequestCollapse(String dropdownName) {
		waitForElementVisible(driver, LINKTransportRequestCreationUI.DROPDOWN_BY_NAME_AT_TRANSPORT_REQUEST_COLLAPSE, dropdownName);
		return getElementText(driver, LINKTransportRequestCreationUI.DROPDOWN_BY_NAME_AT_TRANSPORT_REQUEST_COLLAPSE, dropdownName);
	}
	
	public void inputToTextboxByNameAtTransportRequestCollapse(String fieldName, String value) {
		waitForElementVisible(driver, LINKTransportRequestCreationUI.TEXTBOX_BY_NAME_AT_TRANSPORT_REQUEST_COLLAPSE, fieldName);
		sendkeyToElement(driver, LINKTransportRequestCreationUI.TEXTBOX_BY_NAME_AT_TRANSPORT_REQUEST_COLLAPSE, value, fieldName);
	}
	
	public String getAttributeValueOfTextboxByNameAtTransportRequestCollapse(String fieldName, String attributeName) {
		waitForElementVisible(driver, LINKTransportRequestCreationUI.TEXTBOX_BY_NAME_AT_TRANSPORT_REQUEST_COLLAPSE, fieldName);
		return getElementAttribute(driver, LINKTransportRequestCreationUI.TEXTBOX_BY_NAME_AT_TRANSPORT_REQUEST_COLLAPSE, attributeName, fieldName);
	}
	
	public void clickOnMoreIconOfCodeAtLineNumber(String lineNumber) {
		waitForElementClickabled(driver, LINKTransportRequestCreationUI.MORE_ICON_OF_CODE_BY_LINE_NUMBER, lineNumber);
		clickToElement(driver, LINKTransportRequestCreationUI.MORE_ICON_OF_CODE_BY_LINE_NUMBER, lineNumber);
	}
	
	public void clickOnCodeDropdownAtLineNumber(String lineNumber) {
		waitForElementClickabled(driver, LINKTransportRequestCreationUI.CODE_DROPDOWN_AT_LINE_NUMBER, lineNumber);
		clickToElement(driver, LINKTransportRequestCreationUI.CODE_DROPDOWN_AT_LINE_NUMBER, lineNumber);
	}
	
	public String getTextOnCodeDropdownAtLineNumber(String lineNumber) {
		waitForElementVisible(driver, LINKTransportRequestCreationUI.CODE_DROPDOWN_AT_LINE_NUMBER, lineNumber);
		return getElementText(driver, LINKTransportRequestCreationUI.CODE_DROPDOWN_AT_LINE_NUMBER, lineNumber);
	}
	
	public void inputToReferenceCollapse(WebDriver driver, String...refValue) {
		String value = "";
		for (String ref : refValue) {
			value = value + ref + ";";
		}
		value = value.substring(0, value.length() - 1);
		waitForElementVisible(driver, LINKTransportRequestCreationUI.REF_VALUE_TEXTBOX);
		sendkeyToElement(driver, LINKTransportRequestCreationUI.REF_VALUE_TEXTBOX, value);
	}
	
	public void sendkeyToElementByKey(String collapseName, String lineNumber, String fieldName , Keys key) {
		waitForElementVisible(driver, LINKTransportRequestCreationUI.REF_VALUE_TEXTBOX);
		sendkeyBoardToElement(driver, LINKTransportRequestCreationUI.REF_VALUE_TEXTBOX, key);
	}
	
	public void clickOnUseAPreDefinedQuery() {
		waitForElementVisible(driver, LINKTransportRequestCreationUI.PRE_DEFINED_QUERY_DROPDOWN);
		waitForElementClickabled(driver, LINKTransportRequestCreationUI.PRE_DEFINED_QUERY_DROPDOWN);
		clickToElement(driver, LINKTransportRequestCreationUI.PRE_DEFINED_QUERY_DROPDOWN);
		
	}

	public void clickOnSaveTemplate() {
		waitForElementVisible(driver, LINKTransportRequestCreationUI.SAVE_TEMPLATE);
		waitForElementClickabled(driver, LINKTransportRequestCreationUI.SAVE_TEMPLATE);
		clickToElement(driver, LINKTransportRequestCreationUI.SAVE_TEMPLATE);
	}

	public void inputToQueryName(String nameTempalte1) {
		waitForElementVisible(driver, LINKTransportRequestCreationUI.INPUT_QUERY_NAME);
		sendkeyToElement(driver, LINKTransportRequestCreationUI.INPUT_QUERY_NAME, nameTempalte1);
	}

	public void clickOnConfirmButton() {
		waitForElementVisible(driver, LINKTransportRequestCreationUI.CONFIRM);
		waitForElementClickabled(driver, LINKTransportRequestCreationUI.CONFIRM);
		clickToElement(driver, LINKTransportRequestCreationUI.CONFIRM);
	}

	public void clickOnShareWithParticipant() {
		waitForElementClickabled(driver, LINKTransportRequestCreationUI.SHARE_WITH_THIRDPARTY);
		clickToElement(driver, LINKTransportRequestCreationUI.SHARE_WITH_THIRDPARTY);
	}

	public void clickOnDeleteTemplateButton() {
		waitForElementClickabled(driver, LINKTransportRequestCreationUI.DELETE_TEMPLATE);
		clickToElement(driver, LINKTransportRequestCreationUI.DELETE_TEMPLATE);
		delay(2);
		waitForElementInvisible(driver, LINKTransportRequestCreationUI.LOADING_PAGE);
		if (isElementDisplayed(driver, LINKTransportRequestCreationUI.LOADING_PAGE)) {
			waitForLoadingPageUnDisplay(driver);
		}
	}

	public boolean isTemplateUnDisplayByName(String nameTemplate) {
		return isElementUnDisplay(driver, LINKTransportRequestCreationUI.TEMPLATE_BY_NAME, nameTemplate);
	}
	
	public String getAttributeValueOfStatusDropdown(String attributeName) {
		waitForElementVisible(driver, LINKTransportRequestCreationUI.STATUS_DROPDOWN_LIST);
		return getElementAttribute(driver, LINKTransportRequestCreationUI.STATUS_DROPDOWN_LIST, attributeName);
	}
	
	
	public void clearQuantityNumberAtPOCollapse() {
		waitForElementVisible(driver, LINKTransportRequestCreationUI.QUANTITY_TEXTBOX);
		clickToElement(driver, LINKTransportRequestCreationUI.QUANTITY_TEXTBOX);
		sendkeyBoardToElement(driver, LINKTransportRequestCreationUI.QUANTITY_TEXTBOX_MODIFY, Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

}
