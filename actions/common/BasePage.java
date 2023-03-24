package common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObject.LINKLoginPageObject;
import pageObject.LINKTRDetailPageObject;
import pageObject.LINKTransportRequestCreationPageObject;
import pageObject.Menu.ListMenuPageObject;
import pageUI.LINKPageUIs;
import pageUI.LINKTransportRequestCreationUI;


public class BasePage {

	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	protected String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	protected String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	protected void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	protected Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	protected void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	protected void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	protected String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	protected void inputToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	public void switchToWindowByID(WebDriver driver) {
		String currentWindow = driver.getWindowHandle();
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String tempID : allWindowIDs) {
			if (!tempID.equals(currentWindow)) {
				driver.switchTo().window(tempID);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String pageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String tempID : allWindowIDs) {
			driver.switchTo().window(tempID);
			String currentPageTile = getPageTitle(driver);
			if (currentPageTile.equals(pageTitle)) {
				break;
			}
		}
	}

	public void switchToWindowByIndex(WebDriver driver, int index) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		List<String> listWindowIDs = new ArrayList<>(allWindowIDs);
		String expectedWindow = listWindowIDs.get(index);
		driver.switchTo().window(expectedWindow);
	}

	public void closeWindowByIndex(WebDriver driver, int index) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		List<String> listWindowIDs = new ArrayList<>(allWindowIDs);
		String expectedWindow = listWindowIDs.get(index);
		driver.switchTo().window(expectedWindow);
		driver.close();
	}

	public void closeWindowWithoutCurrent(WebDriver driver) {
		String currentPage = driver.getWindowHandle();
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String tempID : allWindowIDs) {
			if (!tempID.equals(currentPage)) {
				driver.switchTo().window(tempID);
				driver.close();
			}
			driver.switchTo().window(currentPage);
		}
	}

	protected By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}

	private By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("id=") || locatorType.startsWith("Id=") || locatorType.startsWith("ID=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=") || locatorType.startsWith("Class=") || locatorType.startsWith("CLASS=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("name=") || locatorType.startsWith("Name=") || locatorType.startsWith("NAME=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=") || locatorType.startsWith("XPATH=")) {
			by = By.xpath(locatorType.substring(6));
		} else {
			throw new RuntimeException("The system NOT support this Locator type");
		}
		return by;
	}

	private String getDynamicXpath(String locatorType, String... params) {
		if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=") || locatorType.startsWith("XPATH=")) {
			locatorType = String.format(locatorType, (Object[]) params);
		} else if (locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
			locatorType = String.format(locatorType, (Object[]) params);
		} else if (locatorType.startsWith("id=") || locatorType.startsWith("Id=") || locatorType.startsWith("ID=")) {
			locatorType = String.format(locatorType, (Object[]) params);
		}
		return locatorType;
	}

	protected WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	protected WebElement getWebElement(WebDriver driver, String locatorType, String... params) {
		return driver.findElement(getByLocator(getDynamicXpath(locatorType, params)));
	}

	protected List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	protected List<WebElement> getListWebElement(WebDriver driver, String locatorType, String... params) {
		return driver.findElements(getByLocator(getDynamicXpath(locatorType, params)));
	}

	public void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}

	public void clickToElement(WebDriver driver, String locatorType, String... params) {
		getWebElement(driver, getDynamicXpath(locatorType, params)).click();
	}

	protected void sendkeyToElement(WebDriver driver, String locatorType, String value) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(value);
	}

	protected void sendkeyToElement(WebDriver driver, String locatorType, String value, String... params) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, params));
		element.clear();
		element.sendKeys(value);
	}

	protected void sendkeyBoardToElement(WebDriver driver, String locatorType, CharSequence keysToSend) {
		getWebElement(driver, locatorType).sendKeys(keysToSend);
	}

	protected void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String value) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(value);
	}

	protected void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String value, String... params) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, params)));
		select.selectByVisibleText(value);
	}

	protected void getSelectedItemDefaultDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.getFirstSelectedOption();
	}

	protected void getSelectedItemDefaultDropdown(WebDriver driver, String locatorType, String... params) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, params)));
		select.getFirstSelectedOption();
	}

	protected void selectItemInCustomDropdown(WebDriver driver, String childLocator, String expectedValue) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
		for (WebElement tempItem : allItems) {
			String actualValue = tempItem.getText();
			if (actualValue.equals(expectedValue)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView('true');", tempItem);
				tempItem.click();
				break;
			}
		}
	}

	protected void selectItemInEditableDropdown(WebDriver driver, String inputLocator, String firstCharacter, String childLocator, String expectedValue) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		sendkeyToElement(driver, inputLocator, firstCharacter);
		delay(1);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
		for (WebElement tempItem : allItems) {
			String actualValue = tempItem.getText().trim();
			if (actualValue.equals(expectedValue)) {
				delay(1);
				tempItem.click();
				break;
			}
		}
	}

	protected void selectItemInEditableDropdown(WebDriver driver, String inputLocator, String firstCharacter, String childLocator, String expectedValue, String... params) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		sendkeyToElement(driver, getDynamicXpath(inputLocator, params), firstCharacter);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
		for (WebElement tempItem : allItems) {
			String actualValue = tempItem.getText().trim();
			if (actualValue.equals(expectedValue)) {
				tempItem.click();
				break;
			}
		}
	}

	protected String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}

	protected String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... params) {
		return getWebElement(driver, getDynamicXpath(locatorType, params)).getAttribute(attributeName);
	}

	public boolean isElementHasAttributeName(WebDriver driver, String locatorType, String attributeName) {
		boolean result = false;
		try {
			String value = getElementAttribute(driver, locatorType, attributeName);
			if (value != null) {
				return result = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	protected String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText().trim();
	}

	protected String getElementText(WebDriver driver, String locatorType, String... params) {
		return getWebElement(driver, getDynamicXpath(locatorType, params)).getText().trim();
	}

	protected String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}

	protected String getHexaColorFormRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	protected int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}

	protected int getElementSize(WebDriver driver, String locatorType, String... params) {
		return getListWebElement(driver, getDynamicXpath(locatorType, params)).size();
	}

	protected void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType, String... params) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, params));
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void checkToCustomCheckboxRadio(WebDriver driver, String locatorType) {
		clickToElementByJS(driver, locatorType);
	}

	protected void checkToCustomCheckboxRadio(WebDriver driver, String locatorType, String... params) {
		clickToElementByJS(driver, getDynamicXpath(locatorType, params));
	}

	protected void unCheckToCustomCheckboxRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		if (element.isSelected()) {
			jsExecutor.executeScript("arguments[0].click();", element);
		}
	}

	protected void uncheckToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}

	protected boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}

	protected boolean isElementSelected(WebDriver driver, String locatorType, String... params) {
		return getWebElement(driver, getDynamicXpath(locatorType, params)).isSelected();
	}

	protected boolean isElementEnabled(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}

	protected boolean isElementDisplayed(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isDisplayed();
	}

	protected boolean isElementDisplayed(WebDriver driver, String locatorType, String... params) {
		return getWebElement(driver, getDynamicXpath(locatorType, params)).isDisplayed();
	}

	protected boolean isElementUnDisplay(WebDriver driver, String locatorType) {
		overridenImplicitWait(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, locatorType);
		overridenImplicitWait(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean isElementUnDisplay(WebDriver driver, String locatorType, String... params) {
		overridenImplicitWait(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, getDynamicXpath(locatorType, params));
		overridenImplicitWait(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	protected void overridenImplicitWait(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	protected void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}

	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	protected void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}

	protected void hoverMouseToElement(WebDriver driver, String locatorType, String... params) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicXpath(locatorType, params))).perform();
	}

	protected void sendKeyBoradByAction(WebDriver driver, String locatorType, CharSequence... keys) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), keys);
	}

	protected void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void highlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}

	protected void clickToElementByJS(WebDriver driver, String locatorType, String... params) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, params));
		if (!element.isSelected()) {
			jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(locatorType, params)));
		}
	}

	public void scrollToElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
		delay(2);
	}

	public void scrollToElement(WebDriver driver, String locatorType, String... params) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, getDynamicXpath(locatorType, params)));
	}

	public void scrollToPosition(WebDriver driver, int xPixels, int ypixels) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String script = "window.scrollBy(" + xPixels + "," + ypixels + ")";
		jsExecutor.executeScript(script);
		delay(2);
	}

	protected void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null)&&(jQuery.active===0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	protected String getElementValidationMessage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
	}
	

	protected boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	protected void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	protected void waitForElementVisible(WebDriver driver, String locatorType, String... params) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, params))));
	}

	protected void waitForAllElementsVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}

	protected void waitForAllElementsVisible(WebDriver driver, String locatorType, String... params) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, params))));
	}

	public void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		overridenImplicitWait(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overridenImplicitWait(driver, longTimeout);
	}

	protected void waitForAllElementsInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
	}

	protected void waitForElementPresence(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByLocator(locatorType)));
	}

	protected void waitForElementPresence(WebDriver driver, String locatorType, String... params) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByLocator(getDynamicXpath(locatorType, params))));
	}

	protected void waitForAllElementPresence(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locatorType)));
	}

	protected void waitForAllElementPresence(WebDriver driver, String locatorType, String... params) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, params))));
	}

	protected void waitForElementClickabled(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getWebElement(driver, locatorType)));
	}

	protected void waitForElementClickabled(WebDriver driver, String locatorType, String... params) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getWebElement(driver, getDynamicXpath(locatorType, params))));
	}

	public ListMenuPageObject getListMenu(WebDriver driver) {
		return new ListMenuPageObject(driver);
	}

	public void openCollapseByName(WebDriver driver, String collapseName) {
		waitForElementVisible(driver, LINKPageUIs.COLLAPSE_BY_NAME, collapseName);
		scrollToElement(driver, LINKPageUIs.COLLAPSE_BY_NAME, collapseName);
		waitForElementClickabled(driver, LINKPageUIs.COLLAPSE_BY_NAME, collapseName);
		clickToElement(driver, LINKPageUIs.COLLAPSE_BY_NAME, collapseName);
	}

	public void selectValueInEditableDropdown(WebDriver driver, String firstValue, String expectedValue) {
		selectItemInEditableDropdown(driver, LINKPageUIs.INPUT_TO_SINGLE_DROP, firstValue, LINKPageUIs.CHILD_LOCATOR_IN_DROPDOWN, expectedValue);

	}

	public void selectValueInDropdownMultiByNameForSearch(WebDriver driver, String dropdownName, String firstLasterOfValue, String value) {
		selectItemInEditableDropdown(driver, LINKPageUIs.INPUT_TO_MULTIPLE_DROPDPOWN_BY_NAME, firstLasterOfValue, LINKPageUIs.CHILD_LOCATOR_IN_DROPDOWN, value, dropdownName);
	}

	public void clickOnDropdownByNameForSearch(WebDriver driver, String dropdownName) {
		waitForElementClickabled(driver, LINKPageUIs.DROPDOWN_LIST_BY_NAME_SEARCH, dropdownName);
		clickToElement(driver, LINKPageUIs.DROPDOWN_LIST_BY_NAME_SEARCH, dropdownName);
	}

	public void inputToTextboxByNameForSearch(WebDriver driver, String textboxName, String value) {
		waitForElementVisible(driver, LINKPageUIs.TEXTBOX_BY_NAME_IN_HEADER_SEARCH, textboxName);
		sendkeyToElement(driver, LINKPageUIs.TEXTBOX_BY_NAME_IN_HEADER_SEARCH, value, textboxName);
	}

	public void inputToTexboxNameAtCollapseNameForSearch(WebDriver driver, String collapseName, String textboxName, String value) {
		waitForElementVisible(driver, LINKPageUIs.TEXTBOX_BY_NAME_IN_COLLAPSE_NAME_SEARCH, collapseName, textboxName);
		sendkeyToElement(driver, LINKPageUIs.TEXTBOX_BY_NAME_IN_COLLAPSE_NAME_SEARCH, value, collapseName, textboxName);
	}

	public void selectFormInDropdownList(WebDriver driver, String expectedValue) {
		selectItemInCustomDropdown(driver, LINKTransportRequestCreationUI.CHILD_LOCATOR_IN_DROPDOWN, expectedValue);
	}

	public void clickOnDropdownByNameAtHeaderForCreate(WebDriver driver, String dropdownName) {
		waitForElementClickabled(driver, LINKPageUIs.DROPDOWN_LIST_BY_NAME_IN_HEADER_CREATE, dropdownName);
		clickToElement(driver, LINKPageUIs.DROPDOWN_LIST_BY_NAME_IN_HEADER_CREATE, dropdownName);
	}

	public void clickOnDropdownByNameAtCollapseNameForCreate(WebDriver driver, String collapseName, String lineNumber, String dropdownName) {
		waitForElementClickabled(driver, LINKPageUIs.DROPDOWN_LIST_BY_NAME_AT_LINE_NUMBER_IN_COLLAPSE_NAME_CREATE, collapseName, lineNumber, dropdownName);
		clickToElement(driver, LINKPageUIs.DROPDOWN_LIST_BY_NAME_AT_LINE_NUMBER_IN_COLLAPSE_NAME_CREATE, collapseName, lineNumber, dropdownName);
	}

	public void inputToTextboxByNameAtHeaderForCreate(WebDriver driver, String textboxName, String value) {
		waitForElementClickabled(driver, LINKPageUIs.TEXTBOX_BY_NAME_IN_HEADER_CREATE, textboxName);
		sendkeyToElement(driver, LINKPageUIs.TEXTBOX_BY_NAME_IN_HEADER_CREATE, value, textboxName);
	}

	public String getAttributeValueOfTextboxByNameAtHeaderForCreate(WebDriver driver, String textboxName, String attributeName) {
		waitForElementVisible(driver, LINKPageUIs.TEXTBOX_BY_NAME_IN_HEADER_CREATE, textboxName);
		return getElementAttribute(driver, LINKPageUIs.TEXTBOX_BY_NAME_IN_HEADER_CREATE, attributeName, textboxName);
	}

	public void inputToTextboxByNameAtCollapseNameForCreate(WebDriver driver, String collapseName, String lineNumber, String textboxName, String value) {
		waitForElementVisible(driver, LINKPageUIs.TEXTBOX_BY_NAME_AT_LINE_NUMBER_IN_COLLAPSE_NAME_CREATE, collapseName, lineNumber, textboxName);
		sendkeyToElement(driver, LINKPageUIs.TEXTBOX_BY_NAME_AT_LINE_NUMBER_IN_COLLAPSE_NAME_CREATE, value, collapseName, lineNumber, textboxName);
	}

	public String getAttributeValueOfTextboxByNameAtCollapseNameForCreate(WebDriver driver, String collapseName, String lineNumber, String textboxName, String attributeName) {
		waitForElementVisible(driver, LINKPageUIs.TEXTBOX_BY_NAME_AT_LINE_NUMBER_IN_COLLAPSE_NAME_CREATE, collapseName, lineNumber, textboxName);
		return getElementAttribute(driver, LINKPageUIs.TEXTBOX_BY_NAME_AT_LINE_NUMBER_IN_COLLAPSE_NAME_CREATE, attributeName, collapseName, lineNumber, textboxName);
	}

	public void clickOnGoButtonInSeachPopup(WebDriver driver) {
		waitForElementClickabled(driver, LINKPageUIs.GO_BUTTON_IN_POPUP);
		scrollToElement(driver, LINKPageUIs.GO_BUTTON_IN_POPUP);
		clickToElement(driver, LINKPageUIs.GO_BUTTON_IN_POPUP);
	}

	public void checkToCheckboxByNameInSearchPopup(WebDriver driver, String checkboxLabel) {
		clickToElementByJS(driver, LINKPageUIs.CHECKBOX_BY_NAME_AT_TABLE_RESULT, checkboxLabel);
	}

	public void checkToRadioButtonByNameInSearchPopup(WebDriver driver, String radiobuttonLabel) {
		checkToDefaultCheckboxRadio(driver, LINKPageUIs.RADIO_BUTTON_BY_NAME_AT_TABLE_RESULT, radiobuttonLabel);
	}

	public void clickOnAttachedSelectedButton(WebDriver driver) {
		waitForElementClickabled(driver, LINKPageUIs.ATTACHED_SELECTED_BUTTON);
		clickToElement(driver, LINKPageUIs.ATTACHED_SELECTED_BUTTON);
		waitForElementInvisible(driver, LINKPageUIs.LOADING_PAGE_CREATE);
		delay(6);
	}

	public void clickToTextboxByNameAtCollapseNameForCreate(WebDriver driver, String collapseName, String lineNumber, String textboxName) {
		waitForElementVisible(driver, LINKPageUIs.TEXTBOX_BY_NAME_AT_LINE_NUMBER_IN_COLLAPSE_NAME_CREATE, collapseName, lineNumber, textboxName);
		clickToElement(driver, LINKPageUIs.TEXTBOX_BY_NAME_AT_LINE_NUMBER_IN_COLLAPSE_NAME_CREATE, collapseName, lineNumber, textboxName);
	}

	public void clickToTextboxByNameAtHeaderForCreate(WebDriver driver, String textboxName) {
		waitForElementVisible(driver, LINKPageUIs.TEXTBOX_BY_NAME_IN_HEADER_CREATE, textboxName);
		clickToElement(driver, LINKPageUIs.TEXTBOX_BY_NAME_AT_LINE_NUMBER_IN_COLLAPSE_NAME_CREATE, textboxName);
	}

	public void clickOnAddButtonAtCollapseByName(WebDriver driver, String collapseName) {
		waitForElementVisible(driver, LINKPageUIs.ADD_BUTTON_AT_COLLAPSE_NAME, collapseName);
		waitForElementClickabled(driver, LINKPageUIs.ADD_BUTTON_AT_COLLAPSE_NAME, collapseName);
		clickToElement(driver, LINKPageUIs.ADD_BUTTON_AT_COLLAPSE_NAME, collapseName);
	}

	public void clickOnRemoveButtonAtLineNumberInCollapseByName(WebDriver driver, String collapseName, String lineNumber) {
		waitForElementVisible(driver, LINKPageUIs.REMOVE_BUTTON_IN_LINE_NUMBER_AT_COLLAPSE_NAME, collapseName, lineNumber);
		waitForElementClickabled(driver, LINKPageUIs.REMOVE_BUTTON_IN_LINE_NUMBER_AT_COLLAPSE_NAME, collapseName, lineNumber);
		clickToElement(driver, LINKPageUIs.REMOVE_BUTTON_IN_LINE_NUMBER_AT_COLLAPSE_NAME, collapseName, lineNumber);
		delay(2);
	}

	public void scrollToCollapseByName(WebDriver driver, String collapseName) {
		waitForElementVisible(driver, LINKPageUIs.COLLAPSE_BY_NAME, collapseName);
		scrollToElement(driver, LINKPageUIs.COLLAPSE_BY_NAME, collapseName);
	}

	public String getInsertTextAtLineNumberByFieldName(WebDriver driver, String lineNumber, String fieldName) {
		waitForElementVisible(driver, LINKPageUIs.TEXT_INSERT_BY_FIELD_NAME_FOR_CREATE, lineNumber, fieldName);
		return getElementText(driver, LINKPageUIs.TEXT_INSERT_BY_FIELD_NAME_FOR_CREATE, lineNumber, fieldName);
	}

	public String getTextOnDropdownByNameAtCollapseNameForCreate(WebDriver driver, String collapseName, String lineNumber, String dropdownName) {
		waitForElementVisible(driver, LINKPageUIs.DROPDOWN_LIST_BY_NAME_AT_LINE_NUMBER_IN_COLLAPSE_NAME_CREATE, collapseName, lineNumber, dropdownName);
		scrollToElement(driver, LINKPageUIs.DROPDOWN_LIST_BY_NAME_AT_LINE_NUMBER_IN_COLLAPSE_NAME_CREATE, collapseName, lineNumber, dropdownName);
		return getElementText(driver, LINKPageUIs.DROPDOWN_LIST_BY_NAME_AT_LINE_NUMBER_IN_COLLAPSE_NAME_CREATE, collapseName, lineNumber, dropdownName);
	}

	public String getTextOnDropdownByNameAtHeaderForCreate(WebDriver driver, String dropdownName) {
		waitForElementVisible(driver, LINKPageUIs.DROPDOWN_LIST_BY_NAME_IN_HEADER_CREATE, dropdownName);
		scrollToElement(driver, LINKPageUIs.DROPDOWN_LIST_BY_NAME_IN_HEADER_CREATE, dropdownName);
		return getElementText(driver, LINKPageUIs.DROPDOWN_LIST_BY_NAME_IN_HEADER_CREATE, dropdownName);
	}

	public boolean isInsertFieldAtLineNumberByNameUnDisplay(WebDriver driver, String lineNumber, String insertFieldName) {
		return isElementUnDisplay(driver, LINKPageUIs.FIELD_INSERT_BY_NAME_FOR_CREATE, lineNumber, insertFieldName);
	}

	public void waitForLoadingPageUnDisplay(WebDriver driver) {
		waitForElementInvisible(driver, LINKPageUIs.LOADING_PAGE_CREATE);
	}

	public LINKTRDetailPageObject clickOnGoButtonToCreateTR(WebDriver driver) {
		waitForElementClickabled(driver, LINKTransportRequestCreationUI.GO_UP_BUTTON);
		clickToElement(driver, LINKTransportRequestCreationUI.GO_UP_BUTTON);
		waitForLoadingPageUnDisplay(driver);
		return PageGenerateManagement.getTRDetailPage(driver);
	}

	public int getNumberLineAtCollapseByCollapseNameForDetailPage(WebDriver driver, String collapseName) {
		waitForAllElementsVisible(driver, LINKPageUIs.GET_ROW_NUMBER_OF_COLLAPSE_BY_COLLAPSE_NAME_AT_DETAIL_PAGE, collapseName);
		return getElementSize(driver, LINKPageUIs.GET_ROW_NUMBER_OF_COLLAPSE_BY_COLLAPSE_NAME_AT_DETAIL_PAGE, collapseName);
	}

	public String getTextValueInCollapseNameAtRowByColumnNameForDetailPage(WebDriver driver, String collapseName, String rowNumber, String columnName) {
		int columnIndex = getElementSize(driver, LINKPageUIs.COLUMN_INDEX_BY_NAME_AT_COLLAPSE_NAME, collapseName, columnName) + 1;
		return getElementText(driver, LINKPageUIs.VALUE_IN_COLLAPSE_NAME_AT_ROW_NUMBER_BY_COLUMN_NAME, collapseName, rowNumber, String.valueOf(columnIndex));
	}

	public boolean isTemplateDisplayedByName(WebDriver driver, String firstCharOfName, String templateName) {
		waitForElementVisible(driver, LINKPageUIs.INPUT_TO_SINGLE_DROP);
		sendkeyToElement(driver, LINKPageUIs.INPUT_TO_SINGLE_DROP, firstCharOfName);
		waitForElementVisible(driver, LINKPageUIs.TEMPLATE_BY_NAME, templateName);
		return isElementDisplayed(driver, LINKPageUIs.TEMPLATE_BY_NAME, templateName);
	}

	public String getNoitificationMessage(WebDriver driver) {
		waitForElementVisible(driver, LINKPageUIs.NOITIFICATION_MESSAGE);
		return getElementText(driver, LINKPageUIs.NOITIFICATION_MESSAGE);
	}

	public LINKLoginPageObject clickOnLogOutButton(WebDriver driver) {
		waitForElementClickabled(driver, LINKPageUIs.LOGOUT_BUTTON);
		clickToElement(driver, LINKPageUIs.LOGOUT_BUTTON);
		return PageGenerateManagement.getLINKLoginPage(driver);
	}

	public int getAllLineNumberAtCollapseByNameForCreatePage(WebDriver driver, String collapseName) {
		waitForAllElementsVisible(driver, LINKPageUIs.GET_LINE_NUMBER_OF_COLLAPSE_BY_COLLAPSE_NAME_AT_CREATE_PAGE, collapseName);
		return getElementSize(driver, LINKPageUIs.GET_LINE_NUMBER_OF_COLLAPSE_BY_COLLAPSE_NAME_AT_CREATE_PAGE, collapseName);
	}

	protected void clearTextInElementByKey(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

	public void clickOnExpandIconAtLineNumberOfCollapseName(WebDriver driver, String collapseName, String lineNumber) {
		waitForElementVisible(driver, LINKPageUIs.EXPAND_BUTTON_BY_COLLAPSE_NAME_AT_ROW_NUMBER, collapseName, lineNumber);
		waitForElementClickabled(driver, LINKPageUIs.EXPAND_BUTTON_BY_COLLAPSE_NAME_AT_ROW_NUMBER, collapseName, lineNumber);
		clickToElement(driver, LINKPageUIs.EXPAND_BUTTON_BY_COLLAPSE_NAME_AT_ROW_NUMBER, collapseName, lineNumber);
	}

	public void clickOnExpandIconOfSubTableAtLineNumberOfCollapseName(WebDriver driver, String collapseName, String lineNumber) {
		waitForElementVisible(driver, LINKPageUIs.EXPAND_BUTTON_IN_SUB_TABLE_BY_COLLAPSE_NAME_AT_ROW_NUMBER, collapseName, lineNumber);
		waitForElementClickabled(driver, LINKPageUIs.EXPAND_BUTTON_IN_SUB_TABLE_BY_COLLAPSE_NAME_AT_ROW_NUMBER, collapseName, lineNumber);
		clickToElement(driver, LINKPageUIs.EXPAND_BUTTON_IN_SUB_TABLE_BY_COLLAPSE_NAME_AT_ROW_NUMBER, collapseName, lineNumber);
	}

	public boolean isSubTableAtLineNumberOfCollapseNameDisplayed(WebDriver driver, String collapseName, String lineNumber) {
		waitForElementVisible(driver, LINKPageUIs.SUB_TABLE_BY_COLLAPSE_NAME_AT_ROW_NUMBER, collapseName, lineNumber);
		return isElementDisplayed(driver, LINKPageUIs.SUB_TABLE_BY_COLLAPSE_NAME_AT_ROW_NUMBER, collapseName, lineNumber);
	}

	public String getValueOfColumnNameInSubTableAtLineNumberOfCollapseName(WebDriver driver, String collapseName, String lineNumber, String columnName) {
		int columnIndex = getElementSize(driver, LINKPageUIs.COLUMN_NAME_INDEX_IN_SUB_TABLE, collapseName, lineNumber, columnName) + 1;
		waitForElementVisible(driver, LINKPageUIs.VALUE_OF_COLUMN_NAME_IN_SUB_TABLE, collapseName, lineNumber, String.valueOf(columnIndex));
		return getElementText(driver, LINKPageUIs.VALUE_OF_COLUMN_NAME_IN_SUB_TABLE, collapseName, lineNumber, String.valueOf(columnIndex));
	}

	public String getValueOfColumnNameInSubTabNameAtLineNumberOfCollapseName(WebDriver driver, String collapseName, String lineNumber, String subTableName, String columnNameInSubTable) {
		int subTabIndex = 0;
		if (isElementUnDisplay(driver, LINKPageUIs.TAB_INDEX_BY_COLLAPSE_NAME_AT_ROW_NUMBER, collapseName, lineNumber, subTableName)) {
			subTabIndex = 1;
		} else {
			subTabIndex = getElementSize(driver, LINKPageUIs.TAB_INDEX_BY_COLLAPSE_NAME_AT_ROW_NUMBER, collapseName, lineNumber, subTableName) + 1;
		}
		int columnIndex = getElementSize(driver, LINKPageUIs.COLUMN_NAME_INDEX_IN_SUB_CHILD_TABLE_BY_SUB_TAB_INDEX, collapseName, lineNumber, String.valueOf(subTabIndex), columnNameInSubTable) + 1;
		waitForElementVisible(driver, LINKPageUIs.VALUE_OF_COLUMN_NAME_IN_SUB_CHILD_TABLE_BY_SUB_TAB_INDEX, collapseName, lineNumber, String.valueOf(subTabIndex), String.valueOf(columnIndex));
		return getElementText(driver, LINKPageUIs.VALUE_OF_COLUMN_NAME_IN_SUB_CHILD_TABLE_BY_SUB_TAB_INDEX, collapseName, lineNumber, String.valueOf(subTabIndex), String.valueOf(columnIndex));
	}

	public String getValueOfColumnNameInTabNameAtLineNumberOfCollapseName(WebDriver driver, String collapseName, String lineNumber, String subTableName, String columnNameInSubTable) {
		int tabIndex = 0;
		if (isElementUnDisplay(driver, LINKPageUIs.TAB_INDEX_BY_COLLAPSE_NAME_AT_ROW_NUMBER, collapseName, lineNumber, subTableName)) {
			tabIndex = 1;
		} else {
			tabIndex = getElementSize(driver, LINKPageUIs.TAB_INDEX_BY_COLLAPSE_NAME_AT_ROW_NUMBER, collapseName, lineNumber, subTableName) + 1;
		}
		int columnIndex = getElementSize(driver, LINKPageUIs.COLUMN_NAME_INDEX_IN_SUB_TABLE_BY_TAB_INDEX, collapseName, lineNumber, String.valueOf(tabIndex), columnNameInSubTable) + 1;
		waitForElementVisible(driver, LINKPageUIs.VALUE_OF_COLUMN_NAME_IN_SUB_TABLE_BY_TAB_INDEX, collapseName, lineNumber, String.valueOf(tabIndex), String.valueOf(columnIndex));
		return getElementText(driver, LINKPageUIs.VALUE_OF_COLUMN_NAME_IN_SUB_TABLE_BY_TAB_INDEX, collapseName, lineNumber, String.valueOf(tabIndex), String.valueOf(columnIndex));
	}
	
	public LINKTransportRequestCreationPageObject clickOnEditButtonAtTRDetailPage(WebDriver driver) {
		waitForElementVisible(driver, LINKPageUIs.EDIT_BUTTON_FOR_TR_DETAIL_PAGE);
		clickToElement(driver, LINKPageUIs.EDIT_BUTTON_FOR_TR_DETAIL_PAGE);
		return PageGenerateManagement.getTRCreationPage(driver);
	}

	public boolean isEditButtonDisplayed(WebDriver driver) {
		waitForElementVisible(driver, LINKPageUIs.EDIT_BUTTON_FOR_TR_DETAIL_PAGE);
		return isElementDisplayed(driver, LINKPageUIs.EDIT_BUTTON_FOR_TR_DETAIL_PAGE);
	}
	
	public void clickOnAttachmentButtonAtDetailPage(WebDriver driver) {
		waitForElementVisible(driver, LINKPageUIs.ATTACHMENT_FOR_TR_DETAIL_PAGE);
		waitForElementClickabled(driver, LINKPageUIs.ATTACHMENT_FOR_TR_DETAIL_PAGE);
		clickToElement(driver, LINKPageUIs.ATTACHMENT_FOR_TR_DETAIL_PAGE);
		delay(3);
	}
	
	public void clickOnUploadButtonAtDetailPage(WebDriver driver) {
		waitForElementVisible(driver, LINKPageUIs.UPLOAD_BUTTON);
		waitForElementClickabled(driver, LINKPageUIs.UPLOAD_BUTTON);
		clickToElement(driver, LINKPageUIs.UPLOAD_BUTTON);
		delay(3);
	}
	
	public void uploadDocumentAtDetailPage(WebDriver driver, String typeName, String nameFile) {
		waitForElementVisible(driver, LINKPageUIs.SUBMIT_BUTTON_TO_UPLOAD);
		String uploadFilePath = GlobalConstant.PROJECT_PATH + File.separator + "uploadFile" + File.separator + nameFile ;
		clickToElement(driver, LINKPageUIs.DOCUMENT_TYPE);
		selectItemInCustomDropdown(driver, LINKPageUIs.CHILD_LOCATOR_IN_DROPDOWN, typeName);
		waitForElementPresence(driver, LINKPageUIs.UPLOAD_FILE);
		sendkeyToElement(driver, LINKPageUIs.UPLOAD_FILE, uploadFilePath);
		waitForElementClickabled(driver, LINKPageUIs.SUBMIT_BUTTON_TO_UPLOAD);
		clickToElement(driver, LINKPageUIs.SUBMIT_BUTTON_TO_UPLOAD);
	}
	
	public String getAttachedDocumentMessageAtDetailPage(WebDriver driver) {
		waitForElementVisible(driver, LINKPageUIs.NOITIFICATION_MESSAGE);
		return getElementText(driver, LINKPageUIs.NOITIFICATION_MESSAGE);
	}
	
	public String getTextValueByColumnNameInAttchmentPopup(WebDriver driver, String columnName) {
		int columnIndex = getElementSize(driver, LINKPageUIs.COLUMN_INDEX_BY_NAME_AT_ATTACHMENT_POPUP, columnName) + 1 ;
		return getElementText(driver, LINKPageUIs.TEXT_VALUE_BY_COLUMN_INDEX_AT_ATTACHMENT_POPUP, String.valueOf(columnIndex));
	}
	
	public void closeAttachmentPopup(WebDriver driver) {
		waitForElementVisible(driver, LINKPageUIs.CLOSE_ATTACHMENT_POPUP);
		waitForElementClickabled(driver, LINKPageUIs.CLOSE_ATTACHMENT_POPUP);
		clickToElement(driver, LINKPageUIs.CLOSE_ATTACHMENT_POPUP); 
	}
	

	private long longTimeout = 25;
	private long shortTimeout = 5;

	public void delay(long unit) {
		try {
			Thread.sleep(unit * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
