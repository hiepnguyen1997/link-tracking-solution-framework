package link.TransportRequest;

import java.lang.reflect.Method;

import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import common.BaseTest;
import common.PageGenerateManagement;
import pageObject.LINKHomePageObject;
import pageObject.LINKLoginPageObject;
import pageObject.LINKMySettingPageObject;
import pageObject.LINKTRDetailPageObject;
import pageObject.LINKTransportRequestCreationPageObject;
import pageObject.LINKTransportRequestSearchPageObject;
import pageUI.LINKPageUIs;
import pageUI.LINKTransportRequestSearchUI;
import pageUI.navigations.MenuName;
import pageUI.navigations.SubMenuName;
import reportConfig.ExtentTestManager;
import utilities.EnvironmentConfig;

public class TransportRequest_Creation_02 extends BaseTest {

	private WebDriver driver;
	private String subTRID, subTR, masTR, formTest, formTest_Edit, statusSubTR, statusMasTR, goodSubTR, goodMasTR;
	private String masPOL, masPOD, masEvent, masEvtLocation, masEvtDate, masRole1, masCode1, masRole2, masCode2;
	private String purchaseOrderValue, pkgGoodsDesc, pkgType, poQuantity;
	private String fileName, docType;
	private int weightSubTR, volumeSubTR, quantitySubTR, flightNumber, SQNumber;

	LINKLoginPageObject linkLoginPage;
	LINKTransportRequestSearchPageObject linkTRSearchPage;
	LINKHomePageObject linkHomePage;
	LINKMySettingPageObject linkMySettingPage;
	LINKTRDetailPageObject linkTRDetailPage;
	LINKTransportRequestCreationPageObject linkTRCreatePage;
	EnvironmentConfig environmentConfig;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		ConfigFactory.setProperty("evn", environmentName);
		environmentConfig = ConfigFactory.create(EnvironmentConfig.class);
		driver = getBrowserName(browserName, environmentConfig.getURL());
		formTest = "Test_AAD / 010100";
		formTest_Edit = "Test_AAD";
		subTRID = "SUB_TR_TEST_01";
		subTR = "SUB-TR-" + generateRandomNumber();
		masTR = "MAS-TR-" + generateRandomNumber();
		statusSubTR = "COM - Completed";
		statusMasTR = "OUV - Opened";
		masPOL = "CA - CANADA";
		masPOD = "HK - HONG KONG";
		masEvent = "Expected departure";
		masEvtLocation = "CA - CANADA";
		masEvtDate = "10 Jan 23 - 12:00 AM";
		masRole1 = "CARRIER AIRLINE";
		masRole2 = "SHIPPING LINE";
		masCode1 = "8006LF - DETECTINON";
		masCode2 = "8006QQ - Aromate Industries Co.,Ltd";
		purchaseOrderValue = "TEST PO_TRACK";
		pkgGoodsDesc = "PKG Test";
		pkgType = "BOTTLE";
		poQuantity = "1";
		weightSubTR = generateRandomNumber();
		volumeSubTR = generateRandomNumber();
		quantitySubTR = generateRandomNumber();
		flightNumber = generateRandomNumber();
		SQNumber = generateRandomNumber();
		goodSubTR = "N/A";
		goodMasTR = "None";
		fileName = "Pupbie.png";
		docType = "AIR WAY BILL SHIPPER";
		
		linkLoginPage = PageGenerateManagement.getLINKLoginPage(driver);
		linkHomePage = linkLoginPage.loginAsLINKUser(environmentConfig.getUserName(), environmentConfig.getPassword());
		linkMySettingPage = (LINKMySettingPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.SETTINGS, SubMenuName.SETTINGS_MY_SETTING);
		linkMySettingPage.clickOnActiveCheckbox();
	}

	@Test
	public void TC_12_Verify_adding_a_Sub_TR_to_a_Master_TR(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 12: Verify adding a Sub TR to a Master TR");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transport Request/Create");
		log.info("Step 01: Navigate to Transport Request/Create");
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkMySettingPage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPORT_REQUEST_CREATE);

		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Select form: ");
		log.info("Step 02: Select form: ");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "", formTest);

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Click on Clear button");
		log.info("Step 03: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Go to and open ATTACHED TRANSPORT REQUESTS collapse");
		log.info("Step 04: Go to and open ATTACHED TRANSPORT REQUESTS collapse");
		linkTRCreatePage.openCollapseByName(driver, "ATTACHED TRANSPORT REQUESTS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on Search button");
		log.info("Step 05: Click on Search button");
		linkTRCreatePage.clickOnSearchButtonInAttachedTRCollapse();

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Input value into T/R# field: " + subTRID);
		log.info("Step 06: Input value into T/R# field: " + subTRID);
		linkTRCreatePage.inputToTextboxByNameForSearch(driver, "T/R #", subTRID);

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Click on Go to search sub TR");
		log.info("Step 07: Click on Go to search sub TR");
		linkTRCreatePage.clickOnGoButtonInSeachPopup(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Click on checkbox to select sub TR: " + subTRID);
		log.info("Step 08: Click on checkbox to select sub TR: " + subTRID);
		linkTRCreatePage.checkToCheckboxByNameInSearchPopup(driver, subTRID);

		ExtentTestManager.getTest().log(Status.INFO, "Step 09: click on Attached selected value");
		log.info("Step 09: click on Attached selected value");
		linkTRCreatePage.clickOnAttachedSelectedButton(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Verify the Weight, Volume and Quantity of master TR are the sum of all inserted from Sub TR");
		log.info("Step 10: Verify the Weight, Volume and Quantity of master TR are the sum of all inserted from Sub TR");
		String weight = linkTRCreatePage.getInsertTextAtLineNumberByFieldName(driver, "1", "WEIGHT");
		String requestQuantity = linkTRCreatePage.getInsertTextAtLineNumberByFieldName(driver, "1", "REQUESTED QUANTITY");
		String volume = linkTRCreatePage.getInsertTextAtLineNumberByFieldName(driver, "1", "VOLUME");
		linkTRCreatePage.openCollapseByName(driver, "TRANSPORT REQUEST");
		Assert.assertEquals(linkTRCreatePage.getAttributeValueOfTextboxByNameAtTransportRequestCollapse("WEIGHT", "value"), weight.replace(",", "."));
		Assert.assertEquals(linkTRCreatePage.getAttributeValueOfTextboxByNameAtTransportRequestCollapse("REQUESTED QUANTITY", "value"), requestQuantity.replace(",", "."));
		Assert.assertEquals(linkTRCreatePage.getAttributeValueOfTextboxByNameAtTransportRequestCollapse("VOLUME", "value"), volume.substring(0, volume.length() - 1).replace(",", "."));
	}

	@Test
	public void TC_13_Verify_the_Sub_TR_information_is_replaced_by_master_TR(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 13: Verify the Sub TR information is replaced by master TR");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transport Request/Create");
		log.info("Step 01: Navigate to Transport Request/Create");
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPORT_REQUEST_CREATE);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Select the form: " + formTest);
		log.info("Step 02: Select the form: " + formTest);
		linkTRCreatePage.selectValueInEditableDropdown(driver, "", formTest);

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Clicn on Clear button");
		log.info("Step 03: Clicn on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Input to T/R # field: " + subTR);
		log.info("Step 04: Input to T/R # field: " + subTR);
		linkTRCreatePage.inputToTextboxByNameAtHeaderForCreate(driver, "T/R #", subTR);

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on Status dropdown");
		log.info("Step 05: Navigate to Transport Request/Create");
		linkTRCreatePage.clickOnDropdownByNameAtHeaderForCreate(driver, "STATUS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Select value in Status: " + statusSubTR);
		log.info("Step 06: Select value in Status: " + statusSubTR);
		linkTRCreatePage.selectValueInEditableDropdown(driver, "", statusSubTR);

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Go to and open Transport Request collapse");
		log.info("Step 07: Go to and open Transport Request collapse");
		linkTRCreatePage.openCollapseByName(driver, "TRANSPORT REQUEST");

		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Input to Good field: " + goodSubTR);
		log.info("Step 08: Input to Good field: " + goodSubTR);
		linkTRCreatePage.inputToTextboxByNameAtTransportRequestCollapse("GOODS", goodSubTR);

		ExtentTestManager.getTest().log(Status.INFO, "Step 09: Input to Weight field: " + weightSubTR);
		log.info("Step 09: Input to Weight field: " + weightSubTR);
		linkTRCreatePage.inputToTextboxByNameAtTransportRequestCollapse("WEIGHT", String.valueOf(weightSubTR));

		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Input to Volume field: " + volumeSubTR);
		log.info("Step 10: Input to Volume field: " + volumeSubTR);
		linkTRCreatePage.inputToTextboxByNameAtTransportRequestCollapse("VOLUME", String.valueOf(volumeSubTR));

		ExtentTestManager.getTest().log(Status.INFO, "Step 11: Input to Request Quantity field: " + quantitySubTR);
		log.info("Step 11: Input to Request Quantity field: " + quantitySubTR);
		linkTRCreatePage.inputToTextboxByNameAtTransportRequestCollapse("REQUESTED QUANTITY", String.valueOf(quantitySubTR));

		ExtentTestManager.getTest().log(Status.INFO, "Step 12: Click on Go button");
		log.info("Step 12: Click on Go button");
		linkTRDetailPage = linkTRCreatePage.clickOnGoButtonToCreateTR(driver);
		linkTRDetailPage.waitForLoadingPageUnDisplay(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 13: Navigate to Transport Request/Create again");
		log.info("Step 13: Navigate to Transport Request/Create again");
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkTRDetailPage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPORT_REQUEST_CREATE);

		ExtentTestManager.getTest().log(Status.INFO, "Step 14: Select form: ");
		log.info("Step 14: Select form: ");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "", formTest);

		ExtentTestManager.getTest().log(Status.INFO, "Step 15: Click on Clear button");
		log.info("Step 15: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "Step 16: Go to and open ATTACHED TRANSPORT REQUESTS collapse");
		log.info("Step 16: Go to and open ATTACHED TRANSPORT REQUESTS collapse");
		linkTRCreatePage.openCollapseByName(driver, "ATTACHED TRANSPORT REQUESTS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 17: Click on Search button");
		log.info("Step 17: Click on Search button");
		linkTRCreatePage.clickOnSearchButtonInAttachedTRCollapse();

		ExtentTestManager.getTest().log(Status.INFO, "Step 18: Input value into T/R# field: " + subTR);
		log.info("Step 18: Input value into T/R# field: " + subTR);
		linkTRCreatePage.inputToTextboxByNameForSearch(driver, "T/R #", subTR);

		ExtentTestManager.getTest().log(Status.INFO, "Step 19: Click on Go to search sub TR");
		log.info("Step 19: Click on Go to search sub TR");
		linkTRCreatePage.clickOnGoButtonInSeachPopup(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 20: Click on checkbox to select sub TR: " + subTR);
		log.info("Step 20: Click on checkbox to select sub TR: " + subTR);
		linkTRCreatePage.checkToCheckboxByNameInSearchPopup(driver, subTR);

		ExtentTestManager.getTest().log(Status.INFO, "Step 21: click on Attached selected value");
		log.info("Step 21: click on Attached selected value");
		linkTRCreatePage.clickOnAttachedSelectedButton(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 22: Verify the Weight, Volume and Quantity of master TR are the sum of all inserted from Sub TR");
		log.info("Step 22: Verify the Weight, Volume and Quantity of master TR are the sum of all inserted from Sub TR");
		String weight = linkTRCreatePage.getInsertTextAtLineNumberByFieldName(driver, "1", "WEIGHT");
		String requestQuantity = linkTRCreatePage.getInsertTextAtLineNumberByFieldName(driver, "1", "REQUESTED QUANTITY");
		String volume = linkTRCreatePage.getInsertTextAtLineNumberByFieldName(driver, "1", "VOLUME");
		linkTRCreatePage.openCollapseByName(driver, "TRANSPORT REQUEST");
		Assert.assertEquals(linkTRCreatePage.getAttributeValueOfTextboxByNameAtTransportRequestCollapse("WEIGHT", "value").replace(",", ""), weight.replace(",", "."));
		Assert.assertEquals(linkTRCreatePage.getAttributeValueOfTextboxByNameAtTransportRequestCollapse("REQUESTED QUANTITY", "value").replace(",", ""), requestQuantity.replace(",", "."));
		Assert.assertEquals(linkTRCreatePage.getAttributeValueOfTextboxByNameAtTransportRequestCollapse("VOLUME", "value").replace(",", ""), volume.substring(0, volume.length() - 1).replace(",", "."));

		ExtentTestManager.getTest().log(Status.INFO, "Step 23: Input to T/R# field: " + masTR);
		log.info("Step 23: Input to T/R# field: " + masTR);
		linkTRCreatePage.scrollToElement(driver, LINKPageUIs.TEXTBOX_BY_NAME_IN_HEADER_CREATE, "T/R #");
		linkTRCreatePage.inputToTextboxByNameAtHeaderForCreate(driver, "T/R #", masTR);

		ExtentTestManager.getTest().log(Status.INFO, "Step 24: Click on Status dropdown list");
		log.info("Step 24: Click on Status dropdown list");
		linkTRCreatePage.clickOnDropdownByNameAtHeaderForCreate(driver, "STATUS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 25: Select the status for master TR: " + statusMasTR);
		log.info("Step 25: Select the status for master TR: " + statusMasTR);
		linkTRCreatePage.selectValueInEditableDropdown(driver, "", statusMasTR);

		ExtentTestManager.getTest().log(Status.INFO, "Step 26: Select POL: ");
		log.info("Step 26: Select POL: ");
		linkTRCreatePage.clickOnDropdownByNameAtHeaderForCreate(driver, "POL");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "ca", masPOL);

		ExtentTestManager.getTest().log(Status.INFO, "Step 27: Select POD: ");
		log.info("Step 27: Select POD: ");
		linkTRCreatePage.clickOnDropdownByNameAtHeaderForCreate(driver, "POD");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "hk", masPOD);

		ExtentTestManager.getTest().log(Status.INFO, "Step 28: Input to Good field: " + goodMasTR);
		log.info("Step 28: Input to Good field: " + goodMasTR);
		linkTRCreatePage.inputToTextboxByNameAtTransportRequestCollapse("GOODS", goodMasTR);

		ExtentTestManager.getTest().log(Status.INFO, "Step 29: Input to SQ#: " + SQNumber);
		log.info("Step 29: Input to SQ#: " + SQNumber);
		linkTRCreatePage.inputToTextboxByNameAtTransportRequestCollapse("SQ#", String.valueOf(SQNumber));

		ExtentTestManager.getTest().log(Status.INFO, "Step 30: Input to FLIGHT/VESSEL NUMBER: " + flightNumber);
		log.info("Step 30: Input to FLIGHT/VESSEL NUMBER: " + flightNumber);
		linkTRCreatePage.inputToTextboxByNameAtTransportRequestCollapse("FLIGHT/VESSEL NUMBER", String.valueOf(flightNumber));

		ExtentTestManager.getTest().log(Status.INFO, "Step 31: Go to and open Event collapse");
		log.info("Step 31: Go to and open Event collapse");
		linkTRCreatePage.openCollapseByName(driver, "EVENTS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 32: Select the event for master TR: " + masEvent);
		log.info("Step 32: Select the event for master TR: " + masEvent);
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "EVENT");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "expected", masEvent);

		ExtentTestManager.getTest().log(Status.INFO, "Step 33: Select the location for event");
		log.info("Step 33: Select the location for event");
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "LOCATION");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "ca", masEvtLocation);

		ExtentTestManager.getTest().log(Status.INFO, "Step 34: Input the day for event");
		log.info("Step 34: Input the day for event");
		linkTRCreatePage.inputToTextboxByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "DATE", masEvtDate);

		ExtentTestManager.getTest().log(Status.INFO, "Step 35: Go to and open Related Parties collapse");
		log.info("Step 35: Go to and open Related Parties collapse");
		linkTRCreatePage.openCollapseByName(driver, "RELATED PARTIES");

		ExtentTestManager.getTest().log(Status.INFO, "Step 36: Click on Add button");
		log.info("Step 36: Click on Add button");
		linkTRCreatePage.clickOnAddButtonAtCollapseByName(driver, "RELATED PARTIES");

		ExtentTestManager.getTest().log(Status.INFO, "Step 37: Select the value for Role 1: " + masRole1);
		log.info("Step 37: Select the value for Role 1: " + masRole1);
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "1", "ROLE");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "ae", masRole1);

		ExtentTestManager.getTest().log(Status.INFO, "Step 38: Select the value for Code 1: " + masCode1);
		log.info("Step 38: Select the value for Code 1: " + masCode1);
		linkTRCreatePage.clickOnCodeDropdownAtLineNumber("1");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "8006l", masCode1);

		ExtentTestManager.getTest().log(Status.INFO, "Step 39: Select the value for Role 2: " + masRole2);
		log.info("Step 39: Select the value for Role 2: " + masRole2);
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "2", "ROLE");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "ma", masRole2);

		ExtentTestManager.getTest().log(Status.INFO, "Step 40: Select the value for Code 2: " + masCode2);
		log.info("Step 40: Select the value for Code 2: " + masCode2);
		linkTRCreatePage.clickOnCodeDropdownAtLineNumber("2");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "8006q", masCode2);

		ExtentTestManager.getTest().log(Status.INFO, "Step 41: Click on Go to create the TR");
		log.info("Step 41: Click on Go to create the TR");
		linkTRDetailPage = linkTRCreatePage.clickOnGoButtonToCreateTR(driver);
		linkTRDetailPage.waitForLoadingPageUnDisplay(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 42 : Navigate to Transport Request/Search");
		log.info("Step 42 : Navigate to Transport Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkTRDetailPage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 43 : Input to T/R#: " + subTR);
		log.info("Step 43 : Input to T/R#: " + subTR);
		linkTRSearchPage.inputToTextboxByNameForSearch(driver, "T/R #", subTR);

		ExtentTestManager.getTest().log(Status.INFO, "Step 44 : Click on Go to Search TR record");
		log.info("Step 44 : Click on Go to Search TR record");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();
		linkTRSearchPage.waitForElementInvisible(driver, LINKTransportRequestSearchUI.LOADING_ICON);

		ExtentTestManager.getTest().log(Status.INFO, "Step 45 : Click on eye icon of TR: " + subTR);
		log.info("Step 45 : Click on eye icon of TR: " + subTR);
		linkTRSearchPage.clickOnEyeIconByLabelName(subTR);

		ExtentTestManager.getTest().log(Status.INFO, "Step 46 : Switch to windown of TR " + subTR + " and verify the inserted data from master TR");
		log.info("Step 46 : Switch to windown of TR " + subTR + " and verify the inserted data from master TR");
		linkTRDetailPage.switchToWindowByTitle(driver, "LINK | " + subTR + " | 010100 | Transport Request");
		Assert.assertTrue(StringUtils.containsIgnoreCase(statusMasTR, linkTRDetailPage.getTextValueByFieldNameAtHeaderForTRDetail("Status")));
		Assert.assertEquals(linkTRDetailPage.getTextValueByFieldNameAtHeaderForTRDetail("POL"), masPOL);
		Assert.assertEquals(linkTRDetailPage.getTextValueByFieldNameAtHeaderForTRDetail("POD"), masPOD);
		linkTRDetailPage.openCollapseByName(driver, "TRANSPORT REQUEST");
		Assert.assertEquals(linkTRDetailPage.getTextValueByFieldNameAtTRCollapseForTRDetail("Flight/Vessel number"), String.valueOf(flightNumber));
		linkTRDetailPage.openCollapseByName(driver, "RELATED PARTIES");
		Assert.assertTrue(StringUtils.containsIgnoreCase(masRole1, linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "RELATED PARTIES", "2", "Desc.")));
		Assert.assertTrue(StringUtils.containsIgnoreCase(masCode1, linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "RELATED PARTIES", "2", "Name")));
		Assert.assertTrue(StringUtils.containsIgnoreCase(masRole2, linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "RELATED PARTIES", "3", "Desc.")));
		Assert.assertTrue(StringUtils.containsIgnoreCase(masCode2, linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "RELATED PARTIES", "3", "Name")));
		linkTRDetailPage.openCollapseByName(driver, "EVENTS");
		Assert.assertTrue(StringUtils.containsIgnoreCase(masEvent, linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "EVENTS", "1", "Event")));
		Assert.assertTrue(StringUtils.containsIgnoreCase(masEvent, linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "EVENTS", "1", "Nature")));
		Assert.assertTrue(StringUtils.containsIgnoreCase(masEvtDate, linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "EVENTS", "1", "Event date")));
		Assert.assertTrue(StringUtils.containsIgnoreCase(masEvtLocation, linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "EVENTS", "1", "Location")));
	}

	@Test
	public void TC_24_Verify_Edit_Button(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 24: Verify the display of edit button");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transport Request/Search");
		log.info("Step 01: Navigate to Transport Request/Search");
		linkTRDetailPage.closeWindowWithoutCurrent(driver);
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on Creation Date: M");
		log.info("Step 02: Click on Creation Date: M");
		linkTRSearchPage.clickOnCreationDateOptionMonth();

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Click on Go to search TR record");
		log.info("Step 03: Click on Go to search TR record");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Click on Eye icon of first value");
		log.info("Step 04: Click on Eye icon of first value");
		linkTRDetailPage = linkTRSearchPage.clickOnEyeIconOfFirstLine();

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Verify edit button is displayed");
		log.info("Step 05: Verify edit button is displayed");
		linkTRDetailPage.switchToWindowByIndex(driver, 1);
		Assert.assertTrue(linkTRDetailPage.isEditButtonDisplayed(driver));
	}

	@Test
	public void TC_25_Verify_Edit_Form(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 25: Verify edit the TR from");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Open Transport Request/Create");
		log.info("Step 01: Open Transport Request/Create");
		linkTRDetailPage.closeWindowWithoutCurrent(driver);
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPORT_REQUEST_CREATE);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Select the form");
		log.info("Step 02: Select the form");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "", formTest);

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Click on Clear button");
		log.info("Step 03: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Click and select value in Status");
		log.info("Step 04: Click and select value in Status");
		linkTRCreatePage.clickOnDropdownByNameAtHeaderForCreate(driver, "STATUS");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "", statusMasTR);

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Open Transport Request collapse");
		log.info("Step 05: Open Transport Request collapse");
		linkTRCreatePage.openCollapseByName(driver, "TRANSPORT REQUEST");

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Input value to Good field");
		log.info("Step 06: Input value to Good field");
		linkTRCreatePage.inputToTextboxByNameAtTransportRequestCollapse("GOODS", goodMasTR);

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Click on Go button to create TR");
		log.info("Step 07: Click on Go button to create TR");
		linkTRDetailPage = linkTRCreatePage.clickOnGoButtonToCreateTR(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Get TR ID");
		log.info("Step 08: Get TR ID");
		String idTransportRequest = linkTRDetailPage.getTextValueByFieldNameAtHeaderForTRDetail("Transport request");

		ExtentTestManager.getTest().log(Status.INFO, "Step 09: Navigate to Transport Request/Search");
		log.info("Step 09: Navigate to Transport Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkTRDetailPage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Input to search TR: " + idTransportRequest);
		log.info("Step 10: Input to search TR: " + idTransportRequest);
		linkTRSearchPage.inputToTextboxByNameForSearch(driver, "T/R #", idTransportRequest);

		ExtentTestManager.getTest().log(Status.INFO, "Step 11: Click on Go to Search TR");
		log.info("Step 11: Click on Go to Search TR");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 12: Click on Eye icon of TR: " + idTransportRequest);
		log.info("Step 12: Click on Eye icon of TR: " + idTransportRequest);
		linkTRDetailPage = linkTRSearchPage.clickOnEyeIconByLabelName(idTransportRequest);

		ExtentTestManager.getTest().log(Status.INFO, "Step 13: Click on Eidt button");
		log.info("Step 13: Click on Eidt button");
		linkTRDetailPage.switchToWindowByTitle(driver, "LINK | " + idTransportRequest + " | 010100 | Transport Request");
		linkTRCreatePage = linkTRDetailPage.clickOnEditButtonAtTRDetailPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 14: Select the form");
		log.info("Step 14: Select the form");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "", formTest_Edit);
		linkTRCreatePage.waitForLoadingPageUnDisplay(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 15: Open Event collapse and input the value to mandatory fields");
		log.info("Step 15: Open Event collapse and input the value to mandatory fields");
		linkTRCreatePage.openCollapseByName(driver, "EVENTS");
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "EVENT");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "expected", masEvent);
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "LOCATION");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "ca", masPOL);
		linkTRCreatePage.inputToTextboxByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "DATE", masEvtDate);

		ExtentTestManager.getTest().log(Status.INFO, "Step 16: Open Shipped Packages collapse and input the value to mandatory fields");
		log.info("Step 16: Open Shipped Packages collapse and input the value to mandatory fields");
		linkTRCreatePage.openCollapseByName(driver, "SHIPPED PACKAGES");
		linkTRCreatePage.inputToTextboxByNameAtCollapseNameForCreate(driver, "SHIPPED PACKAGES", "1", "GOODS DESC.", pkgGoodsDesc);
		linkTRCreatePage.inputToTextboxByNameAtCollapseNameForCreate(driver, "SHIPPED PACKAGES", "1", "NB OF PARCELS", String.valueOf(quantitySubTR));
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "SHIPPED PACKAGES", "1", "PACKAGE TYPE");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "", pkgType);

		ExtentTestManager.getTest().log(Status.INFO, "Step 17: Open Purchase Oreder collapse and input the value to mandatory fields");
		log.info("Step 17: Open Purchase Oreder collapse and input the value to mandatory fields");
		linkTRCreatePage.openCollapseByName(driver, "PURCHASE ORDERS");
		linkTRCreatePage.clickOnSearchButtonInPOCollapse();
		linkTRCreatePage.inputToTextboxByNameForSearch(driver, "PURCHASE ORDER", purchaseOrderValue);
		linkTRCreatePage.clickOnGoButtonInSeachPopup(driver);
		linkTRCreatePage.checkToCheckboxByNameInSearchPopup(driver, purchaseOrderValue);
		linkTRCreatePage.clickOnAttachedSelectedButton(driver);
		String poItemDesc = linkTRCreatePage.getInsertTextAtLineNumberByFieldName(driver, "1", "ITEM DESC.");
		String poItemCode = linkTRCreatePage.getInsertTextAtLineNumberByFieldName(driver, "1", "ITEM CODE");
		linkTRCreatePage.clearQuantityNumberAtPOCollapse();
		String linkPackage = "1 - " + pkgGoodsDesc;
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "PURCHASE ORDERS", "1", "PACKAGE");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "", linkPackage);
		linkTRCreatePage.inputToTextboxByNameAtCollapseNameForCreate(driver, "PURCHASE ORDERS", "1", "QUANTITY", poQuantity);

		ExtentTestManager.getTest().log(Status.INFO, "Step 18: Get inserted value at Transport Request collapse");
		log.info("Step 18: Get inserted value at Transport Request collapse");
		String transportType = linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "TRANSPORT TYPE");
		linkTRCreatePage.openCollapseByName(driver, "TRANSPORT REQUEST");
		String incoterm = linkTRCreatePage.getTextOnDropdownByNameAtTransportRequestCollapse("INCOTERM");

		ExtentTestManager.getTest().log(Status.INFO, "Step 19: Click on Go button to modify TR");
		log.info("Step 19: Click on Go button to modify TR");
		linkTRCreatePage.scrollToPosition(driver, 0, -3000);
		linkTRDetailPage = linkTRCreatePage.clickOnGoButtonToCreateTR(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 20: Verify the modified data at header");
		log.info("Step 20: Verify the modified data at header");
		Assert.assertTrue(StringUtils.containsIgnoreCase(statusMasTR, linkTRDetailPage.getTextValueByFieldNameAtHeaderForTRDetail("Status")));
		Assert.assertTrue(StringUtils.containsIgnoreCase(transportType, linkTRDetailPage.getTextValueByFieldNameAtHeaderForTRDetail("Mode of Transport")));
		Assert.assertTrue(incoterm.contains(linkTRDetailPage.getTextValueByFieldNameAtHeaderForTRDetail("Incoterm")));

		ExtentTestManager.getTest().log(Status.INFO, "Step 21: Verify data at Event collapse");
		log.info("Step 21: Verify data at Event collapse");
		linkTRDetailPage.openCollapseByName(driver, "EVENTS");
		String eventInforCode[] = masEvent.split(" ");
		String eventInforEvtDate[] = masEvtDate.split("-");
		Assert.assertTrue(linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "EVENTS", "1", "Event").equalsIgnoreCase(eventInforCode[1]));
		Assert.assertEquals(linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "EVENTS", "1", "Nature"), eventInforCode[0]);
		Assert.assertEquals(linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "EVENTS", "1", "Event date"), eventInforEvtDate[0].trim().toUpperCase());
		Assert.assertEquals(linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "EVENTS", "1", "Location"), masEvtLocation);

		ExtentTestManager.getTest().log(Status.INFO, "Step 22: Verify data at Shipped Packages collapse");
		log.info("Step 22: Verify data at Shipped Packages collapse");
		linkTRDetailPage.openCollapseByName(driver, "SHIPPED PACKAGES");
		Assert.assertEquals(linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "SHIPPED PACKAGES", "1", "Nb of parcels"), String.valueOf(quantitySubTR));
		Assert.assertEquals(linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "SHIPPED PACKAGES", "1", "Package type"), pkgType);
		Assert.assertEquals(linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "SHIPPED PACKAGES", "1", "Goods"), pkgGoodsDesc);
		linkTRDetailPage.clickOnExpandIconAtLineNumberOfCollapseName(driver, "SHIPPED PACKAGES", "1");
		Assert.assertTrue(linkTRDetailPage.getValueOfColumnNameInTabNameAtLineNumberOfCollapseName(driver, "SHIPPED PACKAGES", "1", "Input Purchase Orders", "Purchase Order").contains(purchaseOrderValue));
		Assert.assertEquals(linkTRDetailPage.getValueOfColumnNameInTabNameAtLineNumberOfCollapseName(driver, "SHIPPED PACKAGES", "1", "Input Purchase Orders", "Item Qty"), "1");

		ExtentTestManager.getTest().log(Status.INFO, "Step 23: Verify data at Purchase Order collapse");
		log.info("Step 23: Verify data at Purchase Order collapse");
		linkTRDetailPage.openCollapseByName(driver, "PURCHASE ORDERS");
		String purchaseOrder = linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "PURCHASE ORDERS", "1", "Purchase Order");
		String[] purchaseOrderID = purchaseOrder.split("/");
		Assert.assertEquals(purchaseOrderID[1], purchaseOrderValue);
		Assert.assertEquals(linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "PURCHASE ORDERS", "1", "Item Qty"), "1");
		linkTRDetailPage.clickOnExpandIconAtLineNumberOfCollapseName(driver, "PURCHASE ORDERS", "1");
		Assert.assertEquals(linkTRDetailPage.getValueOfColumnNameInSubTableAtLineNumberOfCollapseName(driver, "PURCHASE ORDERS", "1", "Item code"), poItemCode);
		Assert.assertEquals(linkTRDetailPage.getValueOfColumnNameInSubTableAtLineNumberOfCollapseName(driver, "PURCHASE ORDERS", "1", "Desc."), poItemDesc);
		linkTRDetailPage.clickOnExpandIconOfSubTableAtLineNumberOfCollapseName(driver, "PURCHASE ORDERS", "1");
		Assert.assertEquals(linkTRDetailPage.getValueOfColumnNameInSubTabNameAtLineNumberOfCollapseName(driver, "PURCHASE ORDERS", "1", "Shipped Packages", "Goods"), pkgGoodsDesc);
		Assert.assertEquals(linkTRDetailPage.getValueOfColumnNameInSubTabNameAtLineNumberOfCollapseName(driver, "PURCHASE ORDERS", "1", "Shipped Packages", "Package type"), pkgType);
	}
	
	@Test
	public void TC_26_Verify_Attached_Document_In_Create_TR(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 26: Verify attached a document in Created TR");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transport Request/Search");
		log.info("Step 01: Navigate to Transport Request/Search");
		linkTRDetailPage.closeWindowWithoutCurrent(driver);
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on Creation Date: M");
		log.info("Step 02: Click on Creation Date: M");
		linkTRSearchPage.clickOnCreationDateOptionMonth();

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Click on Go to search TR record");
		log.info("Step 03: Click on Go to search TR record");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Click on Eye icon of first value");
		log.info("Step 04: Click on Eye icon of first value");
		linkTRDetailPage = linkTRSearchPage.clickOnEyeIconOfFirstLine();

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on Attchment button");
		log.info("Step 05: Click on Attchment button");
		linkTRDetailPage.switchToWindowByIndex(driver, 1);
		linkTRDetailPage.clickOnAttachmentButtonAtDetailPage(driver);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Click on Upload button");
		log.info("Step 06: Click on Upload button");
		linkTRDetailPage.clickOnUploadButtonAtDetailPage(driver);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Upload file and document Type");
		log.info("Step 07: Upload file and document Type");
		linkTRDetailPage.uploadDocumentAtDetailPage(driver, docType, fileName);
		Assert.assertEquals(linkTRDetailPage.getNoitificationMessage(driver), "Attachments") ;
		
		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Click on Attachment button again");
		log.info("Step 08: Click on Attachment button again");
		linkTRDetailPage.clickOnAttachmentButtonAtDetailPage(driver);
		
		ExtentTestManager.getTest().log(Status.INFO, "Step 09: Verify the file is attached successfully");
		log.info("Step 09: Verify the file is attached successfully");
		Assert.assertEquals(linkTRDetailPage.getTextValueByColumnNameInAttchmentPopup(driver, "Name"), fileName.split("\\.")[0]);
		Assert.assertEquals(linkTRDetailPage.getTextValueByColumnNameInAttchmentPopup(driver, "Type"), docType);
		linkTRDetailPage.closeAttachmentPopup(driver);
		
	}
	
	@Test
	public void TC_27_Verify_TR_Status_cannot_be_updated_in_TR_sub(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 27: Verify TR Status cannot be updated in TR sub");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Search and click on edit button of sub TR: " + subTR);
		log.info("Step 01: Search and click on edit button of sub TR: " + subTR);
		linkTRDetailPage.closeWindowWithoutCurrent(driver);
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkTRDetailPage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);
		linkTRSearchPage.inputToTextboxByNameForSearch(driver, "T/R #", subTR);
		linkTRSearchPage.clickOnGoButtonToSearchRecord();
		linkTRDetailPage = linkTRSearchPage.clickOnEyeIconByLabelName(subTR);
		linkTRDetailPage.switchToWindowByTitle(driver, "LINK | " + subTR +" | 010100 | Transport Request");
		linkTRCreatePage = linkTRDetailPage.clickOnEditButtonAtTRDetailPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Selec the form: " + formTest_Edit);
		log.info("Step 02: Selec the form: " + formTest_Edit);
		linkTRCreatePage.selectValueInEditableDropdown(driver, "", formTest_Edit);
		linkTRCreatePage.waitForLoadingPageUnDisplay(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Verify th status dropdown is disabled");
		log.info("Step 03: Verify th status dropdown is disabled");
		System.out.println(linkTRCreatePage.getAttributeValueOfStatusDropdown("class"));
		Assert.assertTrue(linkTRCreatePage.getAttributeValueOfStatusDropdown("class").contains("disabled"));

	}

	@Test
	public void TC_28_Verify_data_of_TR_subs_after_it_is_removed_to_Master_TR(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 28: Verify data of TR subs after it is removed to Master TR");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Switch to window and search the mas TR: " + masTR);
		log.info("Step 01: Switch to window and search the mas TR: " + masTR);
		linkTRDetailPage.closeWindowWithoutCurrent(driver);
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkTRDetailPage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);
		linkTRSearchPage.inputToTextboxByNameForSearch(driver, "T/R #", masTR);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on Go to search");
		log.info("Step 02: Click on Go to search");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();
		linkTRSearchPage.waitForElementInvisible(driver, LINKTransportRequestSearchUI.LOADING_ICON);

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Click on eye icon of TR: " + masTR);
		log.info("Step 03: Click on eye icon of TR: " + masTR);
		linkTRDetailPage = linkTRSearchPage.clickOnEyeIconByLabelName(masTR);

		ExtentTestManager.getTest().log(Status.INFO, "Step : Click on edit button of TR: " + masTR);
		log.info("Step : Click on edit button of TR: " + masTR);
		linkTRDetailPage.switchToWindowByTitle(driver, "LINK | " + masTR + " | 010100 | Transport Request");
		linkTRCreatePage = linkTRDetailPage.clickOnEditButtonAtTRDetailPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Select the form: " + formTest_Edit);
		log.info("Step 05: Select the form: " + formTest_Edit);
		linkTRCreatePage.selectValueInEditableDropdown(driver, "", formTest_Edit);
		linkTRCreatePage.waitForLoadingPageUnDisplay(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Go to and open ATTACHED TRANSPORT REQUESTS collapse");
		log.info("Step 06: Go to and open ATTACHED TRANSPORT REQUESTS collapse");
		linkTRCreatePage.openCollapseByName(driver, "ATTACHED TRANSPORT REQUESTS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Click on remove button");
		log.info("Step 07: Click on remove button");
		linkTRCreatePage.clickOnRemoveButtonAtLineNumberInCollapseByName(driver, "ATTACHED TRANSPORT REQUESTS", "1");

		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Go to and open TRANSPORT REQUESTcollapse");
		log.info("Step 08: Go to and open TRANSPORT REQUESTcollapse");
		linkTRCreatePage.scrollToCollapseByName(driver, "TRANSPORT REQUEST");

		ExtentTestManager.getTest().log(Status.INFO, "Step 09: Verify the value inserted from sub TR " + subTR + " is removed");
		log.info("Step 09: Verify the value inserted from sub TR " + subTR + " is removed");
		Assert.assertEquals(linkTRCreatePage.getAttributeValueOfTextboxByNameAtTransportRequestCollapse("WEIGHT", "value"), "0.000");
		Assert.assertEquals(linkTRCreatePage.getAttributeValueOfTextboxByNameAtTransportRequestCollapse("REQUESTED QUANTITY", "value"), "0.000");
		Assert.assertEquals(linkTRCreatePage.getAttributeValueOfTextboxByNameAtTransportRequestCollapse("VOLUME", "value"), "0.0000");

		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Click on Go button");
		log.info("Step 10: Click on Go button");
		linkTRCreatePage.scrollToPosition(driver, 0, -1000);
		linkTRDetailPage = linkTRCreatePage.clickOnGoButtonToCreateTR(driver);
		linkTRDetailPage.waitForLoadingPageUnDisplay(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 11: Search the sub TR: " + subTR);
		log.info("Step 11: Search the sub TR: " + subTR);
		linkTRDetailPage.closeWindowWithoutCurrent(driver);
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkTRDetailPage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);
		linkTRSearchPage.inputToTextboxByNameForSearch(driver, "T/R #", subTR);

		ExtentTestManager.getTest().log(Status.INFO, "Step 12: Click on Go button");
		log.info("Step 12: Click on Go button");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();
		linkTRSearchPage.waitForElementInvisible(driver, LINKTransportRequestSearchUI.LOADING_ICON);

		ExtentTestManager.getTest().log(Status.INFO, "Step 13: Click on Eye icon of TR: " + subTR);
		log.info("Step 13: Click on Eye icon of TR: " + subTR);
		linkTRDetailPage = linkTRSearchPage.clickOnEyeIconByLabelName(subTR);

		ExtentTestManager.getTest().log(Status.INFO, "Step 14: Click on Edit button");
		log.info("Step 14: Click on Edit button");
		linkTRDetailPage.switchToWindowByTitle(driver, "LINK | " + subTR + " | 010100 | Transport Request");
		linkTRCreatePage = linkTRDetailPage.clickOnEditButtonAtTRDetailPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 15: Select the form: " + formTest_Edit);
		log.info("Step 15: Select the form: " + formTest_Edit);
		linkTRCreatePage.selectValueInEditableDropdown(driver, "", formTest_Edit);
		linkTRCreatePage.waitForLoadingPageUnDisplay(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 16: Verify the status dropdown can be editable");
		log.info("Step 16: Verify the status dropdown can be editable");
		linkTRCreatePage.clickOnDropdownByNameAtHeaderForCreate(driver, "STATUS");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "", statusSubTR);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "STATUS"), statusSubTR);

		ExtentTestManager.getTest().log(Status.INFO, "Step 17: Go to and open RELATED PARTIES collapse");
		log.info("Step 17: Go to and open RELATED PARTIES collapse");
		linkTRCreatePage.openCollapseByName(driver, "RELATED PARTIES");

		ExtentTestManager.getTest().log(Status.INFO, "Step 18: Veify the 3rd Parties value from mas TR " + masTR + " is removed");
		log.info("Step 18: Veify the 3rd Parties value from mas TR " + masTR + " is removed");
		Assert.assertEquals(linkTRCreatePage.getAllLineNumberAtCollapseByNameForCreatePage(driver, "RELATED PARTIES"), 1);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "1", "ROLE"), "ROUTED BY");
		Assert.assertEquals(linkTRCreatePage.getTextOnCodeDropdownAtLineNumber("1"), "010100 - NEW BOLLO");

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}
