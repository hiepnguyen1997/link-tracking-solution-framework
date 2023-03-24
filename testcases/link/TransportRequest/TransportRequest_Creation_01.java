package link.TransportRequest;

import java.lang.reflect.Method;

import org.aeonbits.owner.ConfigFactory;
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
import pageUI.LINKPageUIs;
import pageUI.LINKTransportRequestCreationUI;
import pageUI.navigations.MenuName;
import pageUI.navigations.SubMenuName;
import reportConfig.ExtentTestManager;
import utilities.EnvironmentConfig;

public class TransportRequest_Creation_01 extends BaseTest {
	private WebDriver driver;
	private String POattach001, PO_Attach, subTR, TDEPE, TARRE, formTest, formTestNo3Rd, codeRole, reference_010100Value, GOODS;
	private String availableQuantity, status, pol, pod, polTemplate, podTemplate, consigneeRef, shipperRef, incoterm, date;
	private String existedtemplatePDQ1, existedTemplatePDQ2, existedTemplatePDQ3, TR, nameTemplate1, nameTemplate2;
	private String KEA;
	private int quantity;
	String[] refValue = { "A1", "A2", "A3", "B1" };

	LINKLoginPageObject linkLoginPage;
	LINKHomePageObject linkHomePage;
	LINKMySettingPageObject linkMySettingPage;
	LINKTransportRequestCreationPageObject linkTRCreatePage;
	LINKTRDetailPageObject linkTRDetailPage;
	EnvironmentConfig environmentConfig;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		ConfigFactory.setProperty("evn", environmentName);
		environmentConfig = ConfigFactory.create(EnvironmentConfig.class);
		driver = getBrowserName(browserName, environmentConfig.getURL());
		quantity = generateRandomNumber();
		subTR = "AT_SUB_TR_01";
		POattach001 = "POattach001";
		PO_Attach = "PO_Attach";
		formTest = "Test_AAD / 010100";
		formTestNo3Rd = "test_noparties / 010100";
		TDEPE = "Expected departure";
		TARRE = "Expected arrival";
		codeRole = "8006LK - HAMM CO GMBH";
		reference_010100Value = "010100/001 - Automatic creation by @";
		GOODS = "Test" + generateRandomNumber();
		status = "OUV - Opened";
		pol = "FR - FRANCE";
		pod = "VN - VIET NAM";
		polTemplate = "FRA2H - Avrigney";
		podTemplate = "VNSGN - HO CHI MINH CITY";
		consigneeRef = "Consignee Test";
		shipperRef = "Shipper Test";
		incoterm = "CIP - CARRIAGE & INSURANCE PAID TO ...";
		date = "10 Nov 22 - 6:00 PM";
		KEA = "KEY ACCOUNT";
		existedtemplatePDQ1 = "AT_Example Template";
		existedTemplatePDQ2 = "TKLE/Basiczic";
		existedTemplatePDQ3 = "EKINOQCA/MY_BUCKET";
		TR = "TR" + generateRandomNumber();
		nameTemplate1 = "Auto_Template Local";
		nameTemplate2 = "Auto_Template Shared";

		linkLoginPage = PageGenerateManagement.getLINKLoginPage(driver);
		linkHomePage = linkLoginPage.loginAsLINKUser(environmentConfig.getUserName(), environmentConfig.getPassword());
		linkMySettingPage = (LINKMySettingPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.SETTINGS, SubMenuName.SETTINGS_MY_SETTING);
		linkMySettingPage.clickOnActiveCheckbox();
	}

	@Test
	public void TC_02_Verify_warning_error_when_missing_at_least_one_mandatory_field(Method method) {

		ExtentTestManager.startTest(method.getName(), "TC 02: Verify warning error when missing at least one mandatory field");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transport Requet/Create Page");
		log.info("Step 01: Navigate to Transport Requet/Create Page");
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPORT_REQUEST_CREATE);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Select the form: " + formTest);
		log.info("Step 02: Select the form: " + formTest);
		linkTRCreatePage.selectFormInDropdownList(driver, formTest);

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Click on Clear button");
		log.info("Step 03: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Click on Go button");
		log.info("Step 04: Click on Go button");
		linkTRCreatePage.clickOnGoButtonToCreateTR(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Verify error message display at required field");
		log.info("Step 05: Verify error message display at required field");
		linkTRCreatePage.isErrorMessageAtTextboxNameDisplayed("STATUS");
		linkTRCreatePage.isErrorMessageAtTextboxNameDisplayed("GOODS");
	}

	@Test
	public void TC_04_Verify_inserting_values_in_the_purchase_order_section(Method method) {

		ExtentTestManager.startTest(method.getName(), "Verify inserting values in the purchase order section");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transport Requet/Create Page");
		log.info("Step 01: Navigate to Transport Requet/Create Page");
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPORT_REQUEST_CREATE);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Select the form: " + formTest);
		log.info("Step 02: Select the form: " + formTest);
		linkTRCreatePage.selectFormInDropdownList(driver, formTest);

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Click on Clear button");
		log.info("Step 03: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "Open PO Collapse");
		log.info("Step 04: Open PO Collapse");
		linkTRCreatePage.openCollapseByName(driver, "PURCHASE ORDERS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on Search PO button");
		log.info("Step 05: Click on Search PO button");
		linkTRCreatePage.clickOnSearchButtonInPOCollapse();

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Input PO value to PO field");
		log.info("Step 06: Input PO value to PO field");
		linkTRCreatePage.inputToTextboxByNameForSearch(driver, "PURCHASE ORDER", POattach001);

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Click GO button to search in PO popup");
		log.info("Step 07: Click GO button to search in PO popup");
		linkTRCreatePage.clickOnGoButtonInSeachPopup(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Click on PO checkbox");
		log.info("Step 08: Click on PO checkbox");
		linkTRCreatePage.checkToCheckboxByNameInSearchPopup(driver, POattach001);

		ExtentTestManager.getTest().log(Status.INFO, "Step 09: Click on Attach Selected PO Lines button");
		log.info("Step 09: Click on Attach Selected PO Lines button");
		linkTRCreatePage.clickOnAttachedSelectedButton(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Verify the PO value insert correctly");
		log.info("Step 10: Verify the PO value insert correctly");
		Assert.assertEquals(linkTRCreatePage.getInsertTextAtLineNumberByFieldName(driver, "1", "PURCHASE ORDER"), POattach001);

	}

	@Test
	public void TC_05_Verify_quantity_and_available_quantity_of_an_inserted_PO_in_PO_section(Method method) {

		ExtentTestManager.startTest(method.getName(), "TC 05: Verify inserting values in the purchase order section");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transport Requet/Create Page");
		log.info("Step 01: Navigate to Transport Requet/Create Page");
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPORT_REQUEST_CREATE);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Select the form: " + formTest);
		log.info("Step 02: Select the form: " + formTest);
		linkTRCreatePage.selectFormInDropdownList(driver, formTest);

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Click on Clear button");
		log.info("Step 03: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Open PO Collapse");
		log.info("Step 04: Open PO Collapse");
		linkTRCreatePage.openCollapseByName(driver, "PURCHASE ORDERS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on Search PO button");
		log.info("Step 05: Click on Search PO button");
		linkTRCreatePage.clickOnSearchButtonInPOCollapse();

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Input PO value to PO field");
		log.info("Step 06: Input PO value to PO field");
		linkTRCreatePage.inputToTextboxByNameForSearch(driver, "PURCHASE ORDER", POattach001);

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Click GO button to search in PO popup");
		log.info("Step 07: Click GO button to search in PO popup");
		linkTRCreatePage.clickOnGoButtonInSeachPopup(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Click on PO checkbox");
		log.info("Step 08: Click on PO checkbox");
		linkTRCreatePage.checkToCheckboxByNameInSearchPopup(driver, POattach001);

		ExtentTestManager.getTest().log(Status.INFO, "Step 09: Click on Attach Selected PO Lines button");
		log.info("Step 09: Click on Attach Selected PO Lines button");
		linkTRCreatePage.clickOnAttachedSelectedButton(driver);
		linkTRCreatePage.clearQuantityNumberAtPOCollapse();
		linkTRCreatePage.clickToTextboxByNameAtCollapseNameForCreate(driver, "PURCHASE ORDERS", "1", "CONSIGNEE REF.");

		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Verify the PO value insert correctly");
		log.info("Step 10: Verify the PO value insert correctly");
		Assert.assertEquals(linkTRCreatePage.getInsertTextAtLineNumberByFieldName(driver, "1", "PURCHASE ORDER"), POattach001);

		ExtentTestManager.getTest().log(Status.INFO, "Step 11: Get Available Quantity");
		log.info("Step 11: Get Available Quantity");
		availableQuantity = linkTRCreatePage.getInsertTextAtLineNumberByFieldName(driver, "1", "AVAILABLE QUANTITY");

		ExtentTestManager.getTest().log(Status.INFO, "Step 12: Input value to QUANTITY field");
		log.info("Step 12: Input value to QUANTITY field");
		linkTRCreatePage.inputToTextboxByNameAtCollapseNameForCreate(driver, "PURCHASE ORDERS", "1", "QUANTITY", String.valueOf(quantity));
		linkTRCreatePage.clickToTextboxByNameAtCollapseNameForCreate(driver, "PURCHASE ORDERS", "1", "CONSIGNEE REF.");

		ExtentTestManager.getTest().log(Status.INFO, "Step 13: Verify new Availabe Quantity after entering the quantity:" + quantity);
		log.info("Step 13: Verify new Availabe Quantity after entering the quantity: " + quantity);
		String newQuantity = linkTRCreatePage.newAvailabeQuantityOfSelectedPO(availableQuantity, quantity);
		Assert.assertEquals(linkTRCreatePage.getInsertTextAtLineNumberByFieldName(driver, "1", "AVAILABLE QUANTITY"), newQuantity);

	}

	@Test
	public void TC_06_Verify_only_data_of_the_first_selected_PO_will_be_added_to_TR_request(Method method) {

		ExtentTestManager.startTest(method.getName(), "TC 06: Verify only data of the first selected PO will be added to TR request (PO section)");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transport Requet/Create Page");
		log.info("Step 01: Navigate to Transport Requet/Create Page");
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPORT_REQUEST_CREATE);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Select the form: " + formTest);
		log.info("Step 02: Select the form: " + formTest);
		linkTRCreatePage.selectFormInDropdownList(driver, formTest);

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Click on Clear button");
		log.info("Step 03: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Open Event Collapse");
		log.info("Step 04: Open Event Collapse");
		linkTRCreatePage.openCollapseByName(driver, "EVENTS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on Add button");
		log.info("Step 05: Click on Add button");
		linkTRCreatePage.clickOnAddButtonAtCollapseByName(driver, "EVENTS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Click on Event field 1");
		log.info("Step 06: Click on Event field 1");
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "EVENT");

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Select Expected Departure event for evt1");
		log.info("Step 07: Select Expected Departure event for evt1");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "TDEPE", TDEPE);

		ExtentTestManager.getTest().log(Status.INFO, "Click on Event field 2");
		log.info("Step 08: Click on Event field 2");
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "2", "EVENT");

		ExtentTestManager.getTest().log(Status.INFO, "Step 09: Select Expected Arrival event for evt2");
		log.info("Step 09: Select Expected Arrival event for evt2");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "TARRE", TARRE);

		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Open PO Collapse");
		log.info("Step 10: Open PO Collapse");
		linkTRCreatePage.openCollapseByName(driver, "PURCHASE ORDERS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 11: Click on Search PO button");
		log.info("Step 11: Click on Search PO button");
		linkTRCreatePage.clickOnSearchButtonInPOCollapse();

		ExtentTestManager.getTest().log(Status.INFO, "Step 12: Input PO value to PO field");
		log.info("Step 12: Input PO value to PO field");
		linkTRCreatePage.inputToTextboxByNameForSearch(driver, "PURCHASE ORDER", POattach001);

		ExtentTestManager.getTest().log(Status.INFO, "Step 13: Click GO button to search in PO popup");
		log.info("Step 13: Click GO button to search in PO popup");
		linkTRCreatePage.clickOnGoButtonInSeachPopup(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 14: Click on PO checkbox");
		log.info("Step 14: Click on PO checkbox");
		linkTRCreatePage.checkToCheckboxByNameInSearchPopup(driver, POattach001);

		ExtentTestManager.getTest().log(Status.INFO, "Step 15: Click on Attach Selected PO Lines button");
		log.info("Step 15: Click on Attach Selected PO Lines button");
		linkTRCreatePage.clickOnAttachedSelectedButton(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 16: Verify the PO value insert correctly");
		log.info("Step 16: Verify the PO value insert correctly");
		Assert.assertEquals(linkTRCreatePage.getInsertTextAtLineNumberByFieldName(driver, "1", "PURCHASE ORDER"), POattach001);

		ExtentTestManager.getTest().log(Status.INFO, "Step 17: Verify the PO data insert to Transport Type, POL and POD at Header");
		log.info("Step 17: Verify the PO data insert to Transport Type, POL and POD at Header");
		linkTRCreatePage.scrollToElement(driver, LINKTransportRequestCreationUI.GO_UP_BUTTON);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "TRANSPORT TYPE"), "S - SEA");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POL"), "FR - FRANCE");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POD"), "VN - VIET NAM");

		ExtentTestManager.getTest().log(Status.INFO, "Step 18: Verify the PO data insert to Incoterm at TR collapse");
		log.info("Step 18: Verify the PO data insert to Incoterm at TR collapse");
		linkTRCreatePage.openCollapseByName(driver, "TRANSPORT REQUEST");
		linkTRCreatePage.getTextOnDropdownByNameAtTransportRequestCollapse("INCOTERM");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POD"), "VN - VIET NAM");

		ExtentTestManager.getTest().log(Status.INFO, "Step 19: Verify the PO data insert to Event Location");
		log.info("Step 19: Verify the PO data insert to Event Location");
		linkTRCreatePage.scrollToCollapseByName(driver, "EVENTS");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "LOCATION"), "FR - FRANCE");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "2", "LOCATION"), "VN - VIET NAM");

		ExtentTestManager.getTest().log(Status.INFO, "Step 20: Verify the PO data insert to Related Parties collapse with: ROUTED BY, FREIGHT FORWD, DEPARTURE AGENT, DEST AGENT, VENDOR SUPPLIER, KEY ACCOUNT");
		log.info("Step 20: Verify the PO data insert to Related Parties collapse with: ROUTED BY, FREIGHT FORWD, DEPARTURE AGENT, DEST AGENT, VENDOR SUPPLIER, KEY ACCOUNT");
		linkTRCreatePage.openCollapseByName(driver, "RELATED PARTIES");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "1", "ROLE"), "ROUTED BY");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "2", "ROLE"), "FREIGHT FORWD");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "3", "ROLE"), "DEPARTURE AGENT");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "4", "ROLE"), "DEST AGENT");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "5", "ROLE"), "VENDOR SUPPLIER");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "6", "ROLE"), "SHIPPER");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "7", "ROLE"), "KEY ACCOUNT");
	}

	@Test
	public void TC_07_Verify_only_data_of_the_first_selected_Sub_TR_will_be_added_to_TR_Request(Method method) {

		ExtentTestManager.startTest(method.getName(), "TC 07: Verify only data of the first selected TR will be added to TR request (Sub TR)");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transport Requet/Create Page");
		log.info("Step 01: Navigate to Transport Requet/Create Page");
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPORT_REQUEST_CREATE);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Select the form: " + formTest);
		log.info("Step 02: Select the form: " + formTest);
		linkTRCreatePage.selectFormInDropdownList(driver, formTest);

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Click on Clear button");
		log.info("Step 03: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Click on Clear button");
		log.info("Step 04: Click on Clear button");
		linkTRCreatePage.openCollapseByName(driver, "ATTACHED TRANSPORT REQUESTS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on Search button");
		log.info("Step 05: Click on Search button");
		linkTRCreatePage.clickOnSearchButtonInAttachedTRCollapse();

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Input to T/R# by value: " + subTR);
		log.info("Step 06: Input to T/R# by value: " + subTR);
		linkTRCreatePage.inputToTextboxByNameForSearch(driver, "T/R #", subTR);

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Click on Go button");
		log.info("Step 07: Click on Go button");
		linkTRCreatePage.clickOnGoButtonInSeachPopup(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Click checkbox of TR: " + subTR);
		log.info("Step 08: Click checkbox of TR: " + subTR);
		linkTRCreatePage.checkToCheckboxByNameInSearchPopup(driver, subTR);

		ExtentTestManager.getTest().log(Status.INFO, "Step 09: Click on Attach selected value button");
		log.info("Step 09: Click on Attach selected value button");
		linkTRCreatePage.clickOnAttachedSelectedButton(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Verify the TR attached successfull with: " + subTR);
		log.info("Step 10: Verify the TR attached successfull with: " + subTR);
		Assert.assertEquals(linkTRCreatePage.getInsertTextAtLineNumberByFieldName(driver, "1", "T/R #"), subTR);
		Assert.assertEquals(linkTRCreatePage.getInsertTextAtLineNumberByFieldName(driver, "1", "DEPARTURE"), "CANADA");
		Assert.assertEquals(linkTRCreatePage.getInsertTextAtLineNumberByFieldName(driver, "1", "ARRIVAL"), "INDIA");

		ExtentTestManager.getTest().log(Status.INFO, "Step 11: Verify the POL and POD are inserted value from Sub Transport Request");
		log.info("Step 11: Verify the POL and POD are inserted value from Sub Transport Request");
		linkTRCreatePage.scrollToElement(driver, LINKPageUIs.DROPDOWN_LIST_BY_NAME_IN_HEADER_CREATE, "POL");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POL"), "CAXXX - CANADA");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POD"), "INXXX - INDIA");

	}

	@Test
	public void TC_08_Verify_only_data_of_the_first_selected_PO_at_PO_line_attachment_will_be_added_to_TR_Request(Method method) {

		ExtentTestManager.startTest(method.getName(), " TC 08: Verify only data of the first attached PO will be added to TR request (PO line attachment)");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transport Requet/Create Page");
		log.info("Step 01: Navigate to Transport Requet/Create Page");
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPORT_REQUEST_CREATE);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Select the form: " + formTest);
		log.info("Step 02: Select the form: " + formTest);
		linkTRCreatePage.selectFormInDropdownList(driver, formTest);

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Click on Clear button");
		log.info("Step 03: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Open Event Collapse");
		log.info("Step 04: Open Event Collapse");
		linkTRCreatePage.openCollapseByName(driver, "EVENTS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on Add button");
		log.info("Step 05: Click on Add button");
		linkTRCreatePage.clickOnAddButtonAtCollapseByName(driver, "EVENTS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Click on Event field 1");
		log.info("Step 06: Click on Event field 1");
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "EVENT");

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Select Expected Departure event for evt1");
		log.info("Step 07: Select Expected Departure event for evt1");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "TDEPE", "Expected departure");

		ExtentTestManager.getTest().log(Status.INFO, "Click on Event field 2");
		log.info("Step 08: Click on Event field 2");
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "2", "EVENT");

		ExtentTestManager.getTest().log(Status.INFO, "Step 09: Select Expected Arrival event for evt2");
		log.info("Step 09: Select Expected Arrival event for evt2");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "TARRE", "Expected arrival");

		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Open PO Line Attachment collapse");
		log.info("Step 10: Open PO Line Attachment collapse");
		linkTRCreatePage.openCollapseByName(driver, "PO LINES ATTACHEMENT (EASY MODE)");

		ExtentTestManager.getTest().log(Status.INFO, "Step 11: Open PO Line Attachment collapse");
		log.info("Step 11: Click on Search button");
		linkTRCreatePage.clickOnSearchButtonInPOLineAttachCollapse();

		ExtentTestManager.getTest().log(Status.INFO, "Step 12: Input PO value to PO field");
		log.info("Step 12: Input PO value to PO field");
		linkTRCreatePage.inputToTextboxByNameForSearch(driver, "PURCHASE ORDER", POattach001);

		ExtentTestManager.getTest().log(Status.INFO, "Step 13: Click GO button to search in PO popup");
		log.info("Step 13: Click GO button to search in PO popup");
		linkTRCreatePage.clickOnGoButtonInSeachPopup(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 14: Click on PO checkbox");
		log.info("Step 14: Click on PO checkbox");
		linkTRCreatePage.checkToCheckboxByNameInSearchPopup(driver, POattach001);

		ExtentTestManager.getTest().log(Status.INFO, "Step 15: Click on Attach Selected PO Lines button");
		log.info("Step 15: Click on Attach Selected PO Lines button");
		linkTRCreatePage.clickOnAttachedSelectedButton(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 16: Verify the PO value insert correctly");
		log.info("Step 16: Verify the PO Name display with: " + POattach001);
		Assert.assertEquals(linkTRCreatePage.getPONameAtPOLineAttachCollapse(), POattach001);

		ExtentTestManager.getTest().log(Status.INFO, "Step 17: Verify the PO data insert to Transport Type, POL and POD at Header");
		log.info("Step 17: Verify the PO data insert to Transport Type, POL and POD at Header");
		linkTRCreatePage.scrollToElement(driver, LINKTransportRequestCreationUI.GO_UP_BUTTON);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "TRANSPORT TYPE"), "S - SEA");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POL"), "FR - FRANCE");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POD"), "VN - VIET NAM");

		ExtentTestManager.getTest().log(Status.INFO, "Step 18: Verify the PO data insert to Incoterm at TR collapse");
		log.info("Step 18: Verify the PO data insert to Incoterm at TR collapse");
		linkTRCreatePage.openCollapseByName(driver, "TRANSPORT REQUEST");
		linkTRCreatePage.getTextOnDropdownByNameAtTransportRequestCollapse("INCOTERM");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POD"), "VN - VIET NAM");

		ExtentTestManager.getTest().log(Status.INFO, "Step 19: Verify the PO data insert to Event Location");
		log.info("Step 19: Verify the PO data insert to Event Location");
		linkTRCreatePage.scrollToCollapseByName(driver, "EVENTS");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "LOCATION"), "FR - FRANCE");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "2", "LOCATION"), "VN - VIET NAM");

		ExtentTestManager.getTest().log(Status.INFO, "Step 20: Verify the PO data insert to Related Parties collapse with: ROUTED BY, FREIGHT FORWD, DEPARTURE AGENT, DEST AGENT, VENDOR SUPPLIER, KEY ACCOUNT");
		log.info("Step 20: Verify the PO data insert to Related Parties collapse with: ROUTED BY, FREIGHT FORWD, DEPARTURE AGENT, DEST AGENT, VENDOR SUPPLIER, KEY ACCOUNT");
		linkTRCreatePage.openCollapseByName(driver, "RELATED PARTIES");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "1", "ROLE"), "ROUTED BY");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "2", "ROLE"), "FREIGHT FORWD");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "3", "ROLE"), "DEPARTURE AGENT");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "4", "ROLE"), "DEST AGENT");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "5", "ROLE"), "VENDOR SUPPLIER");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "6", "ROLE"), "SHIPPER");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "7", "ROLE"), "KEY ACCOUNT");
	}

	@Test
	public void TC_09_Verify_Participants_search_popup_in_Related_parties_collapse_with_more_button(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 09: Verify Participants search popup in Related parties collapse with '... ' button");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Step 01: Navigate to Transport Requet/Create Page");
		log.info("Step 01: Navigate to Transport Requet/Create Page");
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName("Transport Request", "Create");

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Selecte the form: " + formTest);
		log.info("Step 02: Selecte the form " + formTest);
		linkTRCreatePage.selectFormInDropdownList(driver, formTest);

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Click on Clear button");
		log.info("Step 03: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Open Related Party collapse");
		log.info("Step 04: Open Related Party collapse");
		linkTRCreatePage.openCollapseByName(driver, "RELATED PARTIES");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on Role");
		log.info("Step 05: Click on Role");
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "1", "ROLE");

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Select the value in Role dropdown list");
		log.info("Step 06: Select the value in Role dropdown list");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "exp", "SHIPPER");

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Click on ... icon in Code");
		log.info("Step 07: Click on ... icon in Code");
		linkTRCreatePage.clickOnMoreIconOfCodeAtLineNumber("1");

		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Input to Name or Code field with value: 8006LK");
		log.info("Step 08: Input to Name or Code field with value: 8006LK");
		linkTRCreatePage.inputToTextboxByNameForSearch(driver, "NAME OR CODE", "8006LK");

		ExtentTestManager.getTest().log(Status.INFO, "Step 09: Click on Go button");
		log.info("Step 09: Click on Go button");
		linkTRCreatePage.clickOnGoButtonInSeachPopup(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Click on radio button to select the 3RD result");
		log.info("Step 10: Click on radio button to select the 3RD result");
		linkTRCreatePage.checkToRadioButtonByNameInSearchPopup(driver, "8006LK");

		ExtentTestManager.getTest().log(Status.INFO, "Step 11: Click on Use Selected Third Party");
		log.info("Step 11: Click on Use Selected Third Party");
		linkTRCreatePage.clickOnAttachedSelectedButton(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 12: Verify the data of select 3RD insert to the fileds correctly");
		log.info("Step 12: Verify the data of select 3RD insert to the fileds correctly");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "1", "CODE"), codeRole);

	}

	@Test
	public void TC_10_Verify_input_participant_in_related_parties_is_not_replaced_by_inserted_PO(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 10: Verify input participant in related parties is not replaced by inserted PO");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Step 01: Navigate to Transport Requet/Create Page");
		log.info("Step 01: Navigate to Transport Requet/Create Page");
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName("Transport Request", "Create");

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Selecte the form " + formTest);
		log.info("Step 02: Selecte the form " + formTest);
		linkTRCreatePage.selectFormInDropdownList(driver, formTest);

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Click on Clear button");
		log.info("Step 03: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Open Related Party collapse");
		log.info("Step 04: Open Related Party collapse");
		linkTRCreatePage.openCollapseByName(driver, "RELATED PARTIES");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on + button");
		log.info("Step 05: Click on + button");
		linkTRCreatePage.clickOnAddButtonAtCollapseByName(driver, "RELATED PARTIES");
		linkTRCreatePage.clickOnAddButtonAtCollapseByName(driver, "RELATED PARTIES");
		linkTRCreatePage.clickOnAddButtonAtCollapseByName(driver, "RELATED PARTIES");
		linkTRCreatePage.clickOnAddButtonAtCollapseByName(driver, "RELATED PARTIES");
		linkTRCreatePage.clickOnAddButtonAtCollapseByName(driver, "RELATED PARTIES");

		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Step 01: Navigate to Transport Requet/Create Page");
		log.info("Step 06: Input and Select DOR role and it code");
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "1", "ROLE");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "DOR", "ROUTED BY");
		linkTRCreatePage.clickOnCodeDropdownAtLineNumber("1");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "8006LK", codeRole);

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Input and Select TRA role and it code");
		log.info("Step 07: Input and Select TRA role and it code");
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "2", "ROLE");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "TRA", "FREIGHT FORWD");
		linkTRCreatePage.clickOnCodeDropdownAtLineNumber("2");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "8006LK", codeRole);

		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Input and Select ADE role and it code");
		log.info("Step 08: Input and Select ADE role and it code");
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "3", "ROLE");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "ADE", "DEPARTURE AGENT");
		linkTRCreatePage.clickOnCodeDropdownAtLineNumber("3");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "8006LK", codeRole);

		ExtentTestManager.getTest().log(Status.INFO, "Step 09: Input and Select ADS role and it code");
		log.info("Step 09: Input and Select ADS role and it code");
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "4", "ROLE");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "ADS", "DEST AGENT");
		linkTRCreatePage.clickOnCodeDropdownAtLineNumber("4");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "8006LK", codeRole);

		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Input and Select FRS role and it code");
		log.info("Step 10: Input and Select FRS role and it code");
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "5", "ROLE");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "FRS", "VENDOR SUPPLIER");
		linkTRCreatePage.clickOnCodeDropdownAtLineNumber("5");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "8006LK", codeRole);

		ExtentTestManager.getTest().log(Status.INFO, "Step 11: Input and Select KEA role and it code");
		log.info("Step 11: Input and Select KEA role and it code");
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "6", "ROLE");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "KEA", "KEY ACCOUNT");
		linkTRCreatePage.clickOnCodeDropdownAtLineNumber("6");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "8006LK", codeRole);

		ExtentTestManager.getTest().log(Status.INFO, "Step 12: Open PO Collapse");
		log.info("Step 12: Open PO Collapse");
		linkTRCreatePage.openCollapseByName(driver, "PURCHASE ORDERS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 13: Click on Search PO button");
		log.info("Step 13: Click on Search PO button");
		linkTRCreatePage.clickOnSearchButtonInPOCollapse();

		ExtentTestManager.getTest().log(Status.INFO, "Step 14: Input PO value to PO field");
		log.info("Step 14: Input PO value to PO field");
		linkTRCreatePage.inputToTextboxByNameForSearch(driver, "PURCHASE ORDER", POattach001);

		ExtentTestManager.getTest().log(Status.INFO, "Step 15: Click GO button to search in PO popup");
		log.info("Step 15: Click GO button to search in PO popup");
		linkTRCreatePage.clickOnGoButtonInSeachPopup(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 16: Click on PO checkbox");
		log.info("Step 16: Click on PO checkbox");
		linkTRCreatePage.checkToCheckboxByNameInSearchPopup(driver, POattach001);

		ExtentTestManager.getTest().log(Status.INFO, "Step 17: Click on Attach Selected PO Lines button");
		log.info("Step 17: Click on Attach Selected PO Lines button");
		linkTRCreatePage.clickOnAttachedSelectedButton(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 18: Verify the value at Related Parties uncnhange when user attach the PO value");
		log.info("Step 18: Verify the value at Related Parties uncnhange when user attach the PO value");
		linkTRCreatePage.scrollToCollapseByName(driver, "RELATED PARTIES");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "1", "ROLE"), "ROUTED BY");
		Assert.assertEquals(linkTRCreatePage.getTextOnCodeDropdownAtLineNumber("1"), codeRole);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "2", "ROLE"), "FREIGHT FORWD");
		Assert.assertEquals(linkTRCreatePage.getTextOnCodeDropdownAtLineNumber("2"), codeRole);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "3", "ROLE"), "DEPARTURE AGENT");
		Assert.assertEquals(linkTRCreatePage.getTextOnCodeDropdownAtLineNumber("3"), codeRole);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "4", "ROLE"), "DEST AGENT");
		Assert.assertEquals(linkTRCreatePage.getTextOnCodeDropdownAtLineNumber("4"), codeRole);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "5", "ROLE"), "VENDOR SUPPLIER");
		Assert.assertEquals(linkTRCreatePage.getTextOnCodeDropdownAtLineNumber("5"), codeRole);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "6", "ROLE"), "KEY ACCOUNT");
		Assert.assertEquals(linkTRCreatePage.getTextOnCodeDropdownAtLineNumber("6"), codeRole);

	}

	@Test
	public void TC_11_Verify_creating_multiple_Reference_records_by_inputting_to_Reference_Value(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 11: Verify creating multiple Reference records by inputting ';' to Reference Value");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Step 01: Navigate to Transport Requet/Create Page");
		log.info("Step 01: Navigate to Transport Requet/Create Page");
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName("Transport Request", "Create");

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Selecte the form " + formTest);
		log.info("Step 02: Selecte the form " + formTest);
		linkTRCreatePage.selectFormInDropdownList(driver, formTest);

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Click on Clear button");
		log.info("Step 03: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Go to and open Reference Collapse");
		log.info("Step 04: Go to and open Reference Collapse");
		linkTRCreatePage.openCollapseByName(driver, "REFERENCES");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on REFERENCE dropdown");
		log.info("Step 05: Click on REFERENCE dropdown");
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "REFERENCES", "1", "REFERENCE");

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Select the reference by 010100: " + reference_010100Value);
		log.info("Step 06: Select the reference by 010100: " + reference_010100Value);
		linkTRCreatePage.selectValueInEditableDropdown(driver, "010100", reference_010100Value);

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Input to REF VALUE textbox");
		log.info("Step 07: Input to REF VALUE textbox");
		linkTRCreatePage.inputToReferenceCollapse(driver, refValue);

		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Click and select Status");
		log.info("Step 08: Click and select Status");
		linkTRCreatePage.clickOnDropdownByNameAtHeaderForCreate(driver, "STATUS");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "open", status);

		ExtentTestManager.getTest().log(Status.INFO, "Step 09: Open Transport Request");
		log.info("Step 09: Open Transport Request");
		linkTRCreatePage.openCollapseByName(driver, "TRANSPORT REQUEST");

		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Input to Goods field");
		log.info("Step 10: Input to Goods field");
		linkTRCreatePage.inputToTextboxByNameAtTransportRequestCollapse("GOODS", GOODS);

		ExtentTestManager.getTest().log(Status.INFO, "Step 11: Click on Go button button");
		log.info("Step 11: Click on Go button button");
		linkTRDetailPage = linkTRCreatePage.clickOnGoButtonToCreateTR(driver);
		linkTRDetailPage.waitForLoadingPageUnDisplay(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 12: Open Reference collapse");
		log.info("Step 12: Open Reference collapse");
		linkTRDetailPage.openCollapseByName(driver, "REFERENCES");

		ExtentTestManager.getTest().log(Status.INFO, "Step 13: Verify the reference value display " + refValue.length + " value");
		log.info("Step 13: Verify the reference value display " + refValue.length + " value");
		Assert.assertEquals(linkTRDetailPage.getColumnSizeByColumnName("Reference"), refValue.length);
		Assert.assertEquals(linkTRDetailPage.getColumnSizeByColumnName("Ref value"), refValue.length);
		Assert.assertTrue(linkTRDetailPage.isValueAtColumnNameDisplayAtReferenceCollapse("Ref value", refValue));
	}

	@Test
	public void TC_14_Verify_inserting_values_in_the_PO_Easy_mode_section(Method method) {

		ExtentTestManager.startTest(method.getName(), " TC 14: Verify inserting values in the PO Easy mode section");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transport Requet/Create Page");
		log.info("Step 01: Navigate to Transport Requet/Create Page");
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPORT_REQUEST_CREATE);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Select the form: " + formTest);
		log.info("Step 02: Select the form: " + formTest);
		linkTRCreatePage.selectFormInDropdownList(driver, formTest);

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Click on Clear button");
		log.info("Step 03: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Open Event Collapse");
		log.info("Step 04: Open Event Collapse");
		linkTRCreatePage.openCollapseByName(driver, "EVENTS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on Add button");
		log.info("Step 05: Click on Add button");
		linkTRCreatePage.clickOnAddButtonAtCollapseByName(driver, "EVENTS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Click on Event field 1");
		log.info("Step 06: Click on Event field 1");
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "EVENT");

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Select Expected Departure event for evt1");
		log.info("Step 07: Select Expected Departure event for evt1");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "TDEPE", "Expected departure");

		ExtentTestManager.getTest().log(Status.INFO, "Click on Event field 2");
		log.info("Step 08: Click on Event field 2");
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "2", "EVENT");

		ExtentTestManager.getTest().log(Status.INFO, "Step 09: Select Expected Arrival event for evt2");
		log.info("Step 09: Select Expected Arrival event for evt2");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "TARRE", "Expected arrival");

		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Open PO Line Attachment collapse");
		log.info("Step 10: Open PO Line Attachment collapse");
		linkTRCreatePage.openCollapseByName(driver, "PO LINES ATTACHEMENT (EASY MODE)");

		ExtentTestManager.getTest().log(Status.INFO, "Step 11: Open PO Line Attachment collapse");
		log.info("Step 11: Click on Search button");
		linkTRCreatePage.clickOnSearchButtonInPOLineAttachCollapse();

		ExtentTestManager.getTest().log(Status.INFO, "Step 12: Input PO value to PO field");
		log.info("Step 12: Input PO value to PO field");
		linkTRCreatePage.inputToTextboxByNameForSearch(driver, "PURCHASE ORDER", POattach001);

		ExtentTestManager.getTest().log(Status.INFO, "Step 13: Click GO button to search in PO popup");
		log.info("Step 13: Click GO button to search in PO popup");
		linkTRCreatePage.clickOnGoButtonInSeachPopup(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 14: Click on PO checkbox");
		log.info("Step 14: Click on PO checkbox");
		linkTRCreatePage.checkToCheckboxByNameInSearchPopup(driver, POattach001);

		ExtentTestManager.getTest().log(Status.INFO, "Step 15: Click on Attach Selected PO Lines button");
		log.info("Step 15: Click on Attach Selected PO Lines button");
		linkTRCreatePage.clickOnAttachedSelectedButton(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 16: Verify the PO value insert correctly");
		log.info("Step 16: Verify the PO Name display with: " + POattach001);
		Assert.assertEquals(linkTRCreatePage.getPONameAtPOLineAttachCollapse(), POattach001);

		ExtentTestManager.getTest().log(Status.INFO, "Step 17: Verify the PO data insert to Transport Type, POL and POD at Header");
		log.info("Step 17: Verify the PO data insert to Transport Type, POL and POD at Header");
		linkTRCreatePage.scrollToElement(driver, LINKTransportRequestCreationUI.GO_UP_BUTTON);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "TRANSPORT TYPE"), "S - SEA");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POL"), "FR - FRANCE");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POD"), "VN - VIET NAM");

		ExtentTestManager.getTest().log(Status.INFO, "Step 18: Verify the PO data insert to Incoterm at TR collapse");
		log.info("Step 18: Verify the PO data insert to Incoterm at TR collapse");
		linkTRCreatePage.openCollapseByName(driver, "TRANSPORT REQUEST");
		linkTRCreatePage.getTextOnDropdownByNameAtTransportRequestCollapse("INCOTERM");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POD"), "VN - VIET NAM");

		ExtentTestManager.getTest().log(Status.INFO, "Step 19: Verify the PO data insert to Event Location");
		log.info("Step 19: Verify the PO data insert to Event Location");
		linkTRCreatePage.scrollToCollapseByName(driver, "EVENTS");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "LOCATION"), "FR - FRANCE");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "2", "LOCATION"), "VN - VIET NAM");

		ExtentTestManager.getTest().log(Status.INFO, "Step 20: Verify the PO data insert to Related Parties collapse with: ROUTED BY, FREIGHT FORWD, DEPARTURE AGENT, DEST AGENT, VENDOR SUPPLIER, KEY ACCOUNT");
		log.info("Step 20: Verify the PO data insert to Related Parties collapse with: ROUTED BY, FREIGHT FORWD, DEPARTURE AGENT, DEST AGENT, VENDOR SUPPLIER, KEY ACCOUNT");
		linkTRCreatePage.openCollapseByName(driver, "RELATED PARTIES");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "1", "ROLE"), "ROUTED BY");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "2", "ROLE"), "FREIGHT FORWD");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "3", "ROLE"), "DEPARTURE AGENT");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "4", "ROLE"), "DEST AGENT");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "5", "ROLE"), "VENDOR SUPPLIER");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "6", "ROLE"), "SHIPPER");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "7", "ROLE"), "KEY ACCOUNT");
	}

	@Test
	public void TC_15_Verify_deleting_all_added_POs_are_not_cleared_the_automatically_added_data_by_inserting_PO(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 15: Verify deleting all added POs are not cleared the automatically added data by inserting PO ");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transport Requet/Create Page");
		log.info("Step 01: Navigate to Transport Requet/Create Page");
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPORT_REQUEST_CREATE);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Select the form: " + formTest);
		log.info("Step 02: Select the form: " + formTest);
		linkTRCreatePage.selectFormInDropdownList(driver, formTest);

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Click on Clear button");
		log.info("Step 03: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Open Event Collapse");
		log.info("Step 04: Open Event Collapse");
		linkTRCreatePage.openCollapseByName(driver, "EVENTS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on Add button");
		log.info("Step 05: Click on Add button");
		linkTRCreatePage.clickOnAddButtonAtCollapseByName(driver, "EVENTS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Click on Event field 1");
		log.info("Step 06: Click on Event field 1");
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "EVENT");

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Select Expected Departure event for evt1");
		log.info("Step 07: Select Expected Departure event for evt1");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "TDEPE", TDEPE);

		ExtentTestManager.getTest().log(Status.INFO, "Click on Event field 2");
		log.info("Step 08: Click on Event field 2");
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "2", "EVENT");

		ExtentTestManager.getTest().log(Status.INFO, "Step 09: Select Expected Arrival event for evt2");
		log.info("Step 09: Select Expected Arrival event for evt2");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "TARRE", TARRE);

		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Open PO Collapse");
		log.info("Step 10: Open PO Collapse");
		linkTRCreatePage.openCollapseByName(driver, "PURCHASE ORDERS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 11: Click on Search PO button");
		log.info("Step 11: Click on Search PO button");
		linkTRCreatePage.clickOnSearchButtonInPOCollapse();

		ExtentTestManager.getTest().log(Status.INFO, "Step 12: Input PO value to PO field");
		log.info("Step 12: Input PO value to PO field");
		linkTRCreatePage.inputToTextboxByNameForSearch(driver, "PURCHASE ORDER", POattach001);

		ExtentTestManager.getTest().log(Status.INFO, "Step 13: Click GO button to search in PO popup");
		log.info("Step 13: Click GO button to search in PO popup");
		linkTRCreatePage.clickOnGoButtonInSeachPopup(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 14: Click on PO checkbox");
		log.info("Step 14: Click on PO checkbox");
		linkTRCreatePage.checkToCheckboxByNameInSearchPopup(driver, POattach001);

		ExtentTestManager.getTest().log(Status.INFO, "Step 15: Click on Attach Selected PO Lines button");
		log.info("Step 15: Click on Attach Selected PO Lines button");
		linkTRCreatePage.clickOnAttachedSelectedButton(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Step 16: Verify the PO value insert correctly");
		log.info("Step 16: Verify the PO value insert correctly");
		Assert.assertEquals(linkTRCreatePage.getInsertTextAtLineNumberByFieldName(driver, "1", "PURCHASE ORDER"), POattach001);

		ExtentTestManager.getTest().log(Status.INFO, "Step 17: Verify the PO data insert to Transport Type, POL and POD at Header");
		log.info("Step 17: Verify the PO data insert to Transport Type, POL and POD at Header");
		linkTRCreatePage.scrollToElement(driver, LINKTransportRequestCreationUI.GO_UP_BUTTON);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "TRANSPORT TYPE"), "S - SEA");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POL"), "FR - FRANCE");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POD"), "VN - VIET NAM");

		ExtentTestManager.getTest().log(Status.INFO, "Step 18: Verify the PO data insert to Incoterm at TR collapse");
		log.info("Step 18: Verify the PO data insert to Incoterm at TR collapse");
		linkTRCreatePage.openCollapseByName(driver, "TRANSPORT REQUEST");
		linkTRCreatePage.getTextOnDropdownByNameAtTransportRequestCollapse("INCOTERM");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POD"), "VN - VIET NAM");

		ExtentTestManager.getTest().log(Status.INFO, "Step 19: Verify the PO data insert to Event Location");
		log.info("Step 19: Verify the PO data insert to Event Location");
		linkTRCreatePage.scrollToCollapseByName(driver, "EVENTS");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "LOCATION"), "FR - FRANCE");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "2", "LOCATION"), "VN - VIET NAM");

		ExtentTestManager.getTest().log(Status.INFO, "Step 20: Verify the PO data insert to Related Parties collapse with: ROUTED BY, FREIGHT FORWD, DEPARTURE AGENT, DEST AGENT, VENDOR SUPPLIER, KEY ACCOUNT");
		log.info("Step 20: Verify the PO data insert to Related Parties collapse with: ROUTED BY, FREIGHT FORWD, DEPARTURE AGENT, DEST AGENT, VENDOR SUPPLIER, KEY ACCOUNT");
		linkTRCreatePage.openCollapseByName(driver, "RELATED PARTIES");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "1", "ROLE"), "ROUTED BY");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "2", "ROLE"), "FREIGHT FORWD");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "3", "ROLE"), "DEPARTURE AGENT");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "4", "ROLE"), "DEST AGENT");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "5", "ROLE"), "VENDOR SUPPLIER");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "6", "ROLE"), "SHIPPER");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "7", "ROLE"), "KEY ACCOUNT");

		ExtentTestManager.getTest().log(Status.INFO, "Step 21: Go to and click on - icon at PO Collapse");
		log.info("Step 21: Go to and click on - icon at PO Collapse");
		linkTRCreatePage.clickOnRemoveButtonAtLineNumberInCollapseByName(driver, "PURCHASE ORDERS", "1");

		ExtentTestManager.getTest().log(Status.INFO, "Step 22: Verify the PO data in other fileds will not be removed");
		log.info("Step 22: Verify the PO data in other fileds will not be removed");
		linkTRCreatePage.scrollToElement(driver, LINKTransportRequestCreationUI.GO_UP_BUTTON);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "TRANSPORT TYPE"), "S - SEA");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POL"), "FR - FRANCE");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POD"), "VN - VIET NAM");
		linkTRCreatePage.openCollapseByName(driver, "TRANSPORT REQUEST");
		linkTRCreatePage.getTextOnDropdownByNameAtTransportRequestCollapse("INCOTERM");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POD"), "VN - VIET NAM");
		linkTRCreatePage.scrollToCollapseByName(driver, "EVENTS");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "LOCATION"), "FR - FRANCE");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "2", "LOCATION"), "VN - VIET NAM");
		linkTRCreatePage.scrollToCollapseByName(driver, "RELATED PARTIES");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "1", "ROLE"), "ROUTED BY");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "2", "ROLE"), "FREIGHT FORWD");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "3", "ROLE"), "DEPARTURE AGENT");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "4", "ROLE"), "DEST AGENT");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "5", "ROLE"), "VENDOR SUPPLIER");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "6", "ROLE"), "SHIPPER");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "7", "ROLE"), "KEY ACCOUNT");
		Assert.assertTrue(linkTRCreatePage.isInsertFieldAtLineNumberByNameUnDisplay(driver, "1", "PURCHASE ORDER"));

	}

	@Test
	public void TC_16_Verify_if_the_collapse_is_not_visible_in_TR_form_the_data_by_inserted_PO_are_not_save_in_new_TR(Method method) {
		ExtentTestManager.startTest(method.getName(), "Verify  if  the collapse is not visible in TR form the data by inserted PO are not save in new TR");
		ExtentTestManager.getTest().log(Status.INFO, "TC 16 - Step 01: Navigate to Transport Requet/Create Page");
		log.info("TC 16 - Step 01: Navigate to Transport Requet/Create Page");
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPORT_REQUEST_CREATE);

		ExtentTestManager.getTest().log(Status.INFO, "TC 16 - Step 02: Select the form: " + formTestNo3Rd);
		log.info("TC 16 - Step 02: Select the form: " + formTestNo3Rd);
		linkTRCreatePage.selectFormInDropdownList(driver, formTestNo3Rd);

		ExtentTestManager.getTest().log(Status.INFO, "TC 16 - Step 03: Click on Clear button");
		log.info("TC 16 - Step 03: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "TC 16 - Step 04: Go to and open PO Collapase");
		log.info("TC 16 - Step 04: Go to and open PO Collapase");
		linkTRCreatePage.openCollapseByName(driver, "PURCHASE ORDERS");

		ExtentTestManager.getTest().log(Status.INFO, "TC 16 - Step 05: Click on Search button");
		log.info("TC 16 - Step 05: Click on Search button");
		linkTRCreatePage.clickOnSearchButtonInPOCollapse();

		ExtentTestManager.getTest().log(Status.INFO, "TC 16 - Step 06: Input to Purchase Oder field with value: " + PO_Attach);
		log.info("TC 16 - Step 06: Input to Purchase Oder field with value: " + PO_Attach);
		linkTRCreatePage.inputToTextboxByNameForSearch(driver, "PURCHASE ORDER", PO_Attach);

		ExtentTestManager.getTest().log(Status.INFO, "TC 16 - Step 07: Click on Go button to Search Po value");
		log.info("TC 16 - Step 07: Click on Go button to Search Po value");
		linkTRCreatePage.clickOnGoButtonInSeachPopup(driver);

		ExtentTestManager.getTest().log(Status.INFO, "TC 16 - Step 08: Click on check box of PO: " + PO_Attach);
		log.info("TC 16 - Step 08: Click on check box of PO: " + PO_Attach);
		linkTRCreatePage.checkToCheckboxByNameInSearchPopup(driver, PO_Attach);

		ExtentTestManager.getTest().log(Status.INFO, "TC 16 - Step 09: Click on Attach Selected value");
		log.info("TC 16 - Step 09: Click on Attach Selected value");
		linkTRCreatePage.clickOnAttachedSelectedButton(driver);
		linkTRCreatePage.clearQuantityNumberAtPOCollapse();
		linkTRCreatePage.clickToTextboxByNameAtCollapseNameForCreate(driver, "PURCHASE ORDERS", "1", "CONSIGNEE REF.");

		ExtentTestManager.getTest().log(Status.INFO, "TC 16 - Step 10: Verify the attach selected PO display correctly");
		log.info("TC 16 - Step 10: Verify the attach selected PO display correctly");
		Assert.assertEquals(linkTRCreatePage.getInsertTextAtLineNumberByFieldName(driver, "1", "PURCHASE ORDER"), PO_Attach);

		ExtentTestManager.getTest().log(Status.INFO, "TC 16 - Step 11: Input to Quantity");
		log.info("TC 16 - Step 11: Input to Quantity");
		linkTRCreatePage.inputToTextboxByNameAtCollapseNameForCreate(driver, "PURCHASE ORDERS", "1", "QUANTITY", String.valueOf(quantity));

		ExtentTestManager.getTest().log(Status.INFO, "TC 16 - Step 12: Select the Status value");
		log.info("TC 16 - Step 12: Select the Status value");
		linkTRCreatePage.scrollToElement(driver, LINKTransportRequestCreationUI.GO_UP_BUTTON);
		linkTRCreatePage.clickOnDropdownByNameAtHeaderForCreate(driver, "STATUS");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "open", status);

		ExtentTestManager.getTest().log(Status.INFO, "TC 16 - Step 13: Open Transport Request Collapse");
		log.info("TC 16 - Step 13: Open Transport Request Collapse");
		linkTRCreatePage.openCollapseByName(driver, "TRANSPORT REQUEST");

		ExtentTestManager.getTest().log(Status.INFO, "TC 16 - Step 14: Input to Good textbox with: " + GOODS);
		log.info("TC 16 - Step 14: Input to Good textbox with: " + GOODS);
		linkTRCreatePage.inputToTextboxByNameAtTransportRequestCollapse("GOODS", GOODS);

		ExtentTestManager.getTest().log(Status.INFO, "TC 16 - Step 15: Click on Go button to Create the TR record");
		log.info("TC 16 - Step 15: Click on Go button to Create the TR record");
		linkTRDetailPage = linkTRCreatePage.clickOnGoButtonToCreateTR(driver);
		linkTRDetailPage.waitForLoadingPageUnDisplay(driver);

		ExtentTestManager.getTest().log(Status.INFO, "TC 16 - Step 16: Open Related Parties Collapse at TR detail page");
		log.info("TC 16 - Step 16: Open Related Parties Collapse at TR detail page");
		linkTRDetailPage.openCollapseByName(driver, "RELATED PARTIES");

		ExtentTestManager.getTest().log(Status.INFO, "TC 16 - Step 17: Verify the value of PO not insert to the Related Parties");
		log.info("TC 16 - Step 17: Verify the value of PO not insert to the Related Parties");
		Assert.assertEquals(linkTRDetailPage.getNumberLineAtCollapseByCollapseNameForDetailPage(driver, "RELATED PARTIES"), 1);
		Assert.assertEquals(linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "RELATED PARTIES", "1", "Desc."), "ROUTED BY");
		Assert.assertEquals(linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "RELATED PARTIES", "1", "Name"), "NEW BOLLO");
	}

	@Test
	public void TC_17_Verify_all_inputted_values_will_be_cleared_by_Clear_button(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 17: Verify all inputted values will be cleared by Clear button");
		ExtentTestManager.getTest().log(Status.INFO, "TC 17 - Step 01: Navigate to Transport Requet/Create Page");
		log.info("TC 17 - Step 01: Navigate to Transport Requet/Create Page");
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPORT_REQUEST_CREATE);

		ExtentTestManager.getTest().log(Status.INFO, "TC 17 - Step 02: Select the form: " + formTest);
		log.info("TC 17 - Step 02: Select the form: " + formTest);
		linkTRCreatePage.selectFormInDropdownList(driver, formTest);

		ExtentTestManager.getTest().log(Status.INFO, "TC 17 - Step 03: Click on Clear button");
		log.info("TC 17 - Step 03: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "TC 17 - Step 04: Click and select value in POL dropdown with value: " + pol);
		log.info("TC 17 - Step 04: Click and select value in POL dropdown with value: " + pol);
		linkTRCreatePage.clickOnDropdownByNameAtHeaderForCreate(driver, "POL");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "FR", pol);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POL"), pol);

		ExtentTestManager.getTest().log(Status.INFO, "TC 17 - Step 05: Click and select value in POD dropdown with value: " + pod);
		log.info("TC 17 - Step 05: Click and select value in POD dropdown with value: " + pod);
		linkTRCreatePage.clickOnDropdownByNameAtHeaderForCreate(driver, "POD");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "VN", pod);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POD"), pod);

		ExtentTestManager.getTest().log(Status.INFO, "TC 17 - Step 06: Input to Consignee Ref by value: " + consigneeRef);
		log.info("TC 17 - Step 06: Input to Consignee Ref by value: " + consigneeRef);
		linkTRCreatePage.inputToTextboxByNameAtHeaderForCreate(driver, "CONSIGNEE REF.", consigneeRef);
		Assert.assertEquals(linkTRCreatePage.getAttributeValueOfTextboxByNameAtHeaderForCreate(driver, "CONSIGNEE REF.", "value"), consigneeRef);

		ExtentTestManager.getTest().log(Status.INFO, "TC 17 - Step 07: Input to Shipper Ref by value: " + shipperRef);
		log.info("TC 17 - Step 07: Input to Shipper Ref by value: " + shipperRef);
		linkTRCreatePage.inputToTextboxByNameAtHeaderForCreate(driver, "SHIPPER REF.", shipperRef);
		Assert.assertEquals(linkTRCreatePage.getAttributeValueOfTextboxByNameAtHeaderForCreate(driver, "SHIPPER REF.", "value"), shipperRef);

		ExtentTestManager.getTest().log(Status.INFO, "TC 17 - Step 08: Go to and open Transport Request collapse");
		log.info("TC 17 - Step 08: Go to and open Transport Request collapse");
		linkTRCreatePage.openCollapseByName(driver, "TRANSPORT REQUEST");

		ExtentTestManager.getTest().log(Status.INFO, "TC 17 - Step 09: Click and select value in Incoterm dropdown list with value: " + incoterm);
		log.info("TC 17 - Step 09: Click and select value in Incoterm dropdown list with value: " + incoterm);
		linkTRCreatePage.clickOnDropdownByNameAtTransportRequestCollapse("INCOTERM");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "", incoterm);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtTransportRequestCollapse("INCOTERM"), incoterm);

		ExtentTestManager.getTest().log(Status.INFO, "TC 17 - Step 10: Click on Clear button");
		log.info("TC 17 - Step 10: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "TC 17 - Step 11: Verify all data input previous will be clear");
		log.info("TC 17 - Step 11: Verify all data input previous will be clear");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POL"), "Search");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POD"), "Search");
		Assert.assertEquals(linkTRCreatePage.getAttributeValueOfTextboxByNameAtHeaderForCreate(driver, "CONSIGNEE REF.", "value"), "");
		Assert.assertEquals(linkTRCreatePage.getAttributeValueOfTextboxByNameAtHeaderForCreate(driver, "SHIPPER REF.", "value"), "");
		linkTRCreatePage.openCollapseByName(driver, "TRANSPORT REQUEST");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtTransportRequestCollapse("INCOTERM"), "Search");
	}

	@Test
	public void TC_18_Verify_creating_successfully_a_TR_with_all_the_inputted_fields(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 18: Verify creating successfully a TR with all the inputted fields");
		ExtentTestManager.getTest().log(Status.INFO, "TC 18 - Step 01: Navigate to Transport Requet/Create Page");
		log.info("TC 18 - Step 01: Navigate to Transport Requet/Create Page");
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPORT_REQUEST_CREATE);

		ExtentTestManager.getTest().log(Status.INFO, "TC 18 - Step 02: Select the form: " + formTest);
		log.info("TC 18 - Step 02: Select the form: " + formTest);
		linkTRCreatePage.selectFormInDropdownList(driver, formTest);

		ExtentTestManager.getTest().log(Status.INFO, "TC 18 - Step 03: Click on Clear button");
		log.info("TC 18 - Step 03: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "TC 18 - Step 04: Click and select status in Staus dropdown with value: " + status);
		log.info("TC 18 - Step 04: Click and select status in Staus dropdown with value: " + status);
		linkTRCreatePage.clickOnDropdownByNameAtHeaderForCreate(driver, "STATUS");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "", status);

		ExtentTestManager.getTest().log(Status.INFO, "TC 18 - Step 05: Click and select value in POL dropdown with value: " + pol);
		log.info("TC 18 - Step 05: Click and select value in POL dropdown with value: " + pol);
		linkTRCreatePage.clickOnDropdownByNameAtHeaderForCreate(driver, "POL");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "FR", pol);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POL"), pol);

		ExtentTestManager.getTest().log(Status.INFO, "TC 18 - Step 06: Click and select value in POD dropdown with value: " + pod);
		log.info("TC 18 - Step 06: Click and select value in POD dropdown with value: " + pod);
		linkTRCreatePage.clickOnDropdownByNameAtHeaderForCreate(driver, "POD");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "VN", pod);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POD"), pod);

		ExtentTestManager.getTest().log(Status.INFO, "TC 18 - Step 07: Go to and open Transport Request collapse");
		log.info("TC 18 - Step 07: Go to and open Transport Request collapse");
		linkTRCreatePage.openCollapseByName(driver, "TRANSPORT REQUEST");

		ExtentTestManager.getTest().log(Status.INFO, "TC 18 - Step 07: Click and select value in Incoterm dropdown with value: " + incoterm);
		log.info("TC 18 - Step 07: Click and select value in Incoterm dropdown with value: " + incoterm);
		linkTRCreatePage.clickOnDropdownByNameAtTransportRequestCollapse("INCOTERM");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "", incoterm);

		ExtentTestManager.getTest().log(Status.INFO, "TC 18 - Step 08: Input to Goods: " + GOODS);
		log.info("TC 18 - Step 08: Input to Goods: " + GOODS);
		linkTRCreatePage.inputToTextboxByNameAtTransportRequestCollapse("GOODS", GOODS);

		ExtentTestManager.getTest().log(Status.INFO, "TC 18 - Step 09: Go to and open event collapse");
		log.info("TC 18 - Step 09: Go to and open event collapse");
		linkTRCreatePage.openCollapseByName(driver, "EVENTS");

		ExtentTestManager.getTest().log(Status.INFO, "TC 18 - Step 10: Click and select value in Event dropdown with value: " + TARRE);
		log.info("TC 18 - Step 10: Click and select value in Event dropdown with value: " + TARRE);
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "EVENT");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "tarr", TARRE);

		ExtentTestManager.getTest().log(Status.INFO, "TC 18 - Step 11: Click and select value in Location dropdown with value: " + pod);
		log.info("TC 18 - Step 11: Click and select value in Location dropdown with value: " + pod);
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "LOCATION");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "vn", pod);

		ExtentTestManager.getTest().log(Status.INFO, "TC 18 - Step 12: Input the Date: " + date);
		log.info("TC 18 - Step 12: Input the Date: " + date);
		linkTRCreatePage.inputToTextboxByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "DATE", date);

		ExtentTestManager.getTest().log(Status.INFO, "TC 18 - Step 13: Click on Go to create TR");
		log.info("TC 18 - Step 13: TC 18 - Step 13: Click on Go to create TR");
		linkTRDetailPage = linkTRCreatePage.clickOnGoButtonToCreateTR(driver);
		linkTRDetailPage.waitForLoadingPageUnDisplay(driver);

		ExtentTestManager.getTest().log(Status.INFO, "TC 18 - Step 14: Verify the TR is created successfull with all inputed value");
		log.info("TC 18 - Step 14: Verify the TR is created successfull with all inputed value");
		Assert.assertEquals(linkTRDetailPage.getTextValueByFieldNameAtHeaderForTRDetail("Status"), "OPENED");
		Assert.assertEquals(linkTRDetailPage.getTextValueByFieldNameAtHeaderForTRDetail("Incoterm"), "CIP");
		Assert.assertEquals(linkTRDetailPage.getTextValueByFieldNameAtHeaderForTRDetail("POL"), pol);
		Assert.assertEquals(linkTRDetailPage.getTextValueByFieldNameAtHeaderForTRDetail("POD"), pod);
		linkTRDetailPage.openCollapseByName(driver, "TRANSPORT REQUEST");
		Assert.assertEquals(linkTRDetailPage.getTextValueByFieldNameAtTRCollapseForTRDetail("Goods"), GOODS.toUpperCase());
		linkTRDetailPage.openCollapseByName(driver, "EVENTS");
		Assert.assertEquals(linkTRDetailPage.getNumberLineAtCollapseByCollapseNameForDetailPage(driver, "EVENTS"), 1);
		Assert.assertEquals(linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "EVENTS", "1", "Event"), "Arrival");
		Assert.assertEquals(linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "EVENTS", "1", "Nature"), "Expected");
		Assert.assertEquals(linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "EVENTS", "1", "Event date"), date.toUpperCase());
		Assert.assertEquals(linkTRDetailPage.getTextValueInCollapseNameAtRowByColumnNameForDetailPage(driver, "EVENTS", "1", "Location"), pod);
	}

	@Test
	public void TC_19_Verify_proposed_PDQ_option_list_for_template(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 19: Verify proposed PDQ option list for template");
		ExtentTestManager.getTest().log(Status.INFO, "TC 19 - Step 01: Navigate to Transport Requet/Create Page");
		log.info("TC 19 - Step 01: Navigate to Transport Requet/Create Page");
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPORT_REQUEST_CREATE);

		ExtentTestManager.getTest().log(Status.INFO, "TC 19 - Step 02: Select the form: " + formTest);
		log.info("TC 19 - Step 02: Select the form: " + formTest);
		linkTRCreatePage.selectFormInDropdownList(driver, formTest);

		ExtentTestManager.getTest().log(Status.INFO, "TC 19 - Step 03: Click on Clear button");
		log.info("TC 19 - Step 03: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "TC 19 - Step 04: Click on Use a Predifined Query dropdown and verify the template is proposed");
		log.info("TC 19 - Step 04: Click on Use a Predifined Query dropdown and verify the template is supposed");
		linkTRCreatePage.clickOnUseAPreDefinedQuery();
		Assert.assertTrue(linkTRCreatePage.isTemplateDisplayedByName(driver, "", existedtemplatePDQ1));
		Assert.assertTrue(linkTRCreatePage.isTemplateDisplayedByName(driver, "", existedTemplatePDQ2));
		Assert.assertTrue(linkTRCreatePage.isTemplateDisplayedByName(driver, "", existedTemplatePDQ3));
		linkTRCreatePage.selectValueInEditableDropdown(driver, "", existedTemplatePDQ3);
		delay(5);
	}

	@Test
	public void TC_20_Verify_data_are_loaded_into_TR_form_by_applying_a_template(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 20: Verify data are loaded into TR form by applying a template");
		ExtentTestManager.getTest().log(Status.INFO, "TC 20 - Step 01: Navigate to Transport Requet/Create Page");
		log.info("TC 20 - Step 01: Navigate to Transport Requet/Create Page");
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPORT_REQUEST_CREATE);

		ExtentTestManager.getTest().log(Status.INFO, "TC 20 - Step 02: Select the form: " + formTest);
		log.info("TC 20 - Step 02: Select the form: " + formTest);
		linkTRCreatePage.selectFormInDropdownList(driver, formTest);

		ExtentTestManager.getTest().log(Status.INFO, "TC 20 - Step 03: Click on Clear button");
		log.info("TC 20 - Step 03: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "TC 20 - Step 04: Click on PDQ dropdown and select the template: " + existedtemplatePDQ1);
		log.info("TC 20 - Step 04: Click on PDQ dropdown and select the template: " + existedtemplatePDQ1);
		linkTRCreatePage.clickOnUseAPreDefinedQuery();
		linkTRCreatePage.selectValueInEditableDropdown(driver, "AT", existedtemplatePDQ1);
		linkTRCreatePage.delay(6);

		ExtentTestManager.getTest().log(Status.INFO, "TC 20 - Step 05: Verify the data of template loading in from correctly");
		log.info("TC 20 - Step 05: Verify the data of template loading in from correctly");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "STATUS"), "VAL - Validated");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "TRANSPORT TYPE"), "S - SEA");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POL"), "FRXXX - FRANCE");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POD"), "INXXX - INDIA");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtTransportRequestCollapse("INCOTERM"), "CPT - CARRIAGE PAID TO (...NAMED PLACE)");
		Assert.assertEquals(linkTRCreatePage.getAttributeValueOfTextboxByNameAtTransportRequestCollapse("GOODS", "value"), "N/A");
		linkTRCreatePage.scrollToCollapseByName(driver, "EVENTS");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "EVENT"), TDEPE);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "LOCATION"), "VNXXX - VIET NAM");
		Assert.assertEquals(linkTRCreatePage.getAttributeValueOfTextboxByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "DATE", "value"), "27 May 21 - 12:00 AM");
		linkTRCreatePage.scrollToCollapseByName(driver, "RELATED PARTIES");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "1", "ROLE"), "SHIPPING LINE");
		Assert.assertEquals(linkTRCreatePage.getTextOnCodeDropdownAtLineNumber("1"), "8006LF - DETECTINON");
		linkTRCreatePage.scrollToCollapseByName(driver, "RELATED PARTIES");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "REFERENCES", "1", "REFERENCE"), "010100/AAA - AAA");

	}

	@Test
	public void TC_21_Verify_creating_new_template_for_a_TR_form(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 21: Verify data are loaded into TR form by applying a template");
		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 01: Navigate to Transport Requet/Create Page");
		log.info("TC 21 - Step 01: Navigate to Transport Requet/Create Page");
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPORT_REQUEST_CREATE);

		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 02: Select the form: " + formTest);
		log.info("TC 21 - Step 02: Select the form: " + formTest);
		linkTRCreatePage.selectFormInDropdownList(driver, formTest);

		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 03: Click on Clear button");
		log.info("TC 21 - Step 03: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 04: Set text to T/R# field: " + TR);
		log.info("TC 21 - Step 03: Click on Clear button");
		linkTRCreatePage.inputToTextboxByNameAtHeaderForCreate(driver, "T/R #", TR);

		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 05: Click on POL drropdown and select: " + polTemplate);
		log.info("TC 21 - Step 05: Click on POL drropdown and select: " + polTemplate);
		linkTRCreatePage.clickOnDropdownByNameAtHeaderForCreate(driver, "POL");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "fra", polTemplate);

		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 06: Click on POD drropdown and select: " + podTemplate);
		log.info("TC 21 - Step 06: Click on POD drropdown and select: " + podTemplate);
		linkTRCreatePage.clickOnDropdownByNameAtHeaderForCreate(driver, "POD");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "vns", podTemplate);

		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 07: Set text to Shipper Ref: " + shipperRef);
		log.info("TC 21 - Step 07: Set text to Shipper Ref: " + shipperRef);
		linkTRCreatePage.inputToTextboxByNameAtHeaderForCreate(driver, "SHIPPER REF.", shipperRef);

		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 08: Set text to Consignee Ref: " + consigneeRef);
		log.info("TC 21 - Step 08: Set text to Consignee Ref: " + consigneeRef);
		linkTRCreatePage.inputToTextboxByNameAtHeaderForCreate(driver, "CONSIGNEE REF.", consigneeRef);

		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 09: Go to and open Event collapse");
		log.info("TC 21 - Step 09: Go to and open Event collapse");
		linkTRCreatePage.openCollapseByName(driver, "EVENTS");

		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 10: Click and select value in Event dropdown: " + TARRE);
		log.info("TC 21 - Step 10: Click and select value in Event dropdown: " + TARRE);
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "EVENT");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "tarre", TARRE);

		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 11: Click and select value in Location dropdwown: " + pod);
		log.info("TC 21 - Step 11: Click and select value in Location dropdwown: " + podTemplate);
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "LOCATION");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "vns", podTemplate);

		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 12: Go to and open Related Parties collapse");
		log.info("TC 21 - Step 12: Go to and open Related Parties collapse");
		linkTRCreatePage.openCollapseByName(driver, "RELATED PARTIES");

		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 13: Click and select value in Role dropdown");
		log.info("TC 21 - Step 13: Click and select value in Role dropdown");
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "1", "ROLE");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "KEA", KEA);

		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 14: Click and select value in Code dropdown");
		log.info("TC 21 - Step 14: Click and select value in Code dropdown");
		linkTRCreatePage.clickOnCodeDropdownAtLineNumber("1");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "8006LK", codeRole);

		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 15: Click on Save tempale");
		log.info("TC 21 - Step 15: Click on Save tempale");
		linkTRCreatePage.clickOnSaveTemplate();

		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 16: Input to QUERY'S NAME");
		log.info("TC 21 - Step 16: Input to QUERY'S NAME");
		linkTRCreatePage.inputToQueryName(nameTemplate1);

		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 17: Click on Confirm button");
		log.info("TC 21 - Step 17: Click on Confirm button");
		linkTRCreatePage.clickOnConfirmButton();
		linkTRCreatePage.waitForLoadingPageUnDisplay(driver);

		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 18: Verify the Template Save Message display");
		log.info("TC 21 - Step 18: Verify the Template Save Message display");
		Assert.assertEquals(linkTRCreatePage.getNoitificationMessage(driver), "Template saved");
		linkTRCreatePage.waitForElementInvisible(driver, LINKPageUIs.NOITIFICATION_MESSAGE);

		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 19: Click on Clear button");
		log.info("TC 21 - Step 19: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 20: Click on Use a PDQ");
		log.info("TC 21 - Step 20: Click on Use a PDQ");
		linkTRCreatePage.clickOnUseAPreDefinedQuery();

		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 21: Verify the created template display");
		log.info("TC 21 - Step 21: Verify the created template display");
		Assert.assertTrue(linkTRCreatePage.isTemplateDisplayedByName(driver, nameTemplate1.substring(0, 1), nameTemplate1));

		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 22: Click on template: " + nameTemplate1);
		log.info("TC 21 - Step 22: Click on template: " + nameTemplate1);
		linkTRCreatePage.selectValueInEditableDropdown(driver, "", nameTemplate1);
		linkTRCreatePage.delay(5);

		ExtentTestManager.getTest().log(Status.INFO, "TC 21 - Step 23: Verify the data of template is load to the TR form");
		log.info("TC 21 - Step 23: Verify the data of template is load to the TR form");
		Assert.assertEquals(linkTRCreatePage.getAttributeValueOfTextboxByNameAtHeaderForCreate(driver, "T/R #", "value"), TR);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POL"), polTemplate);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POD"), podTemplate);
		Assert.assertEquals(linkTRCreatePage.getAttributeValueOfTextboxByNameAtHeaderForCreate(driver, "CONSIGNEE REF.", "value"), consigneeRef);
		Assert.assertEquals(linkTRCreatePage.getAttributeValueOfTextboxByNameAtHeaderForCreate(driver, "SHIPPER REF.", "value"), shipperRef);
		linkTRCreatePage.scrollToCollapseByName(driver, "EVENTS");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "EVENT"), TARRE);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "LOCATION"), podTemplate);
		linkTRCreatePage.scrollToCollapseByName(driver, "RELATED PARTIES");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "1", "ROLE"), KEA);
		Assert.assertEquals(linkTRCreatePage.getTextOnCodeDropdownAtLineNumber("1"), codeRole);

	}

	@Test
	public void TC_22_Verify_sharing_a_template(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 22: Verify sharing template");
		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 01: Navigate to Transport Requet/Create Page");
		log.info("TC 22 - Step 01: Navigate to Transport Requet/Create Page");
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPORT_REQUEST_CREATE);

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 02: Select the form: " + formTest);
		log.info("TC 22 - Step 02: Select the form: " + formTest);
		linkTRCreatePage.selectFormInDropdownList(driver, formTest);

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 03: Click on Clear button");
		log.info("TC 22 - Step 03: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 04: Set text to T/R# field: " + TR);
		log.info("TC 22 - Step 03: Click on Clear button");
		linkTRCreatePage.inputToTextboxByNameAtHeaderForCreate(driver, "T/R #", TR);

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 05: Click on POL drropdown and select: " + polTemplate);
		log.info("TC 22 - Step 05: Click on POL drropdown and select: " + polTemplate);
		linkTRCreatePage.clickOnDropdownByNameAtHeaderForCreate(driver, "POL");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "fra", polTemplate);

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 06: Click on POD drropdown and select: " + podTemplate);
		log.info("TC 22 - Step 06: Click on POD drropdown and select: " + podTemplate);
		linkTRCreatePage.clickOnDropdownByNameAtHeaderForCreate(driver, "POD");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "vns", podTemplate);

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 07: Set text to Shipper Ref: " + shipperRef);
		log.info("TC 22 - Step 07: Set text to Shipper Ref: " + shipperRef);
		linkTRCreatePage.inputToTextboxByNameAtHeaderForCreate(driver, "SHIPPER REF.", shipperRef);

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 08: Set text to Consignee Ref: " + consigneeRef);
		log.info("TC 22 - Step 08: Set text to Consignee Ref: " + consigneeRef);
		linkTRCreatePage.inputToTextboxByNameAtHeaderForCreate(driver, "CONSIGNEE REF.", consigneeRef);

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 09: Go to and open Event collapse");
		log.info("TC 22 - Step 09: Go to and open Event collapse");
		linkTRCreatePage.openCollapseByName(driver, "EVENTS");

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 10: Click and select value in Event dropdown: " + TARRE);
		log.info("TC 22 - Step 10: Click and select value in Event dropdown: " + TARRE);
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "EVENT");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "tarre", TARRE);

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 11: Click and select value in Location dropdwown: " + pod);
		log.info("TC 22 - Step 11: Click and select value in Location dropdwown: " + podTemplate);
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "LOCATION");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "vns", podTemplate);

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 12: Go to and open Related Parties collapse");
		log.info("TC 22 - Step 12: Go to and open Related Parties collapse");
		linkTRCreatePage.openCollapseByName(driver, "RELATED PARTIES");

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 13: Click and select value in Role dropdown");
		log.info("TC 22 - Step 13: Click and select value in Role dropdown");
		linkTRCreatePage.clickOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "1", "ROLE");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "KEA", KEA);

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 14: Click and select value in Code dropdown");
		log.info("TC 22 - Step 14: Click and select value in Code dropdown");
		linkTRCreatePage.clickOnCodeDropdownAtLineNumber("1");
		linkTRCreatePage.selectValueInEditableDropdown(driver, "8006LK", codeRole);

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 15: Click on Save tempale");
		log.info("TC 22 - Step 15: Click on Save tempale");
		linkTRCreatePage.clickOnSaveTemplate();

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 16: Input to QUERY'S NAME");
		log.info("TC 21 - Step 16: Input to QUERY'S NAME");
		linkTRCreatePage.inputToQueryName(nameTemplate2);

		ExtentTestManager.getTest().log(Status.INFO, "TC 22: Step 17: Click and select the shared 3RD Party");
		log.info("TC 22: Step 18: Click and select the shared 3RD Party");
		linkTRCreatePage.clickOnShareWithParticipant();
		linkTRCreatePage.selectValueInEditableDropdown(driver, "", "NEW BOLLO");

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 18: Click on Confirm button");
		log.info("TC 22 - Step 17: Click on Confirm button");
		linkTRCreatePage.clickOnConfirmButton();
		linkTRCreatePage.waitForLoadingPageUnDisplay(driver);

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 18: Verify the Template Save Message display");
		log.info("TC 22 - Step 18: Verify the Template Save Message display");
		Assert.assertEquals(linkTRCreatePage.getNoitificationMessage(driver), "Template saved");
		linkTRCreatePage.waitForElementInvisible(driver, LINKPageUIs.NOITIFICATION_MESSAGE);

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 19: Click on Clear button");
		log.info("TC 22 - Step 19: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 20: Click on Use a PDQ");
		log.info("TC 22 - Step 20: Click on Use a PDQ");
		linkTRCreatePage.clickOnUseAPreDefinedQuery();

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 21: Verify the created template display");
		log.info("TC 22 - Step 21: Verify the created template display");
		Assert.assertTrue(linkTRCreatePage.isTemplateDisplayedByName(driver, nameTemplate2.substring(0, 2), nameTemplate2));

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 22: Click on template: " + nameTemplate2);
		log.info("TC 22 - Step 22: Click on template: " + nameTemplate2);
		linkTRCreatePage.selectValueInEditableDropdown(driver, nameTemplate2.substring(0, 2), nameTemplate2);

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 23: Click on Log Out");
		log.info("TC 22 - Step 23: click on Log Out");
		linkLoginPage = linkTRCreatePage.clickOnLogOutButton(driver);

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 24: Login with other account: " + environmentConfig.getUserNameOther());
		log.info("TC 22 - Step 24: click on Log Out");
		linkHomePage = linkLoginPage.loginAsLINKUser(environmentConfig.getUserNameOther(), environmentConfig.getPasswordOther());
		linkMySettingPage = (LINKMySettingPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.SETTINGS, SubMenuName.SETTINGS_MY_SETTING);
		linkMySettingPage.clickOnActiveCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 25: Navigate to Transport Requet/Create Page");
		log.info("TC 22 - Step 25: Navigate to Transport Requet/Create Page");
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPORT_REQUEST_CREATE);

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 26: Select the form: " + formTest);
		log.info("TC 22 - Step 26: Select the form: " + formTest);
		linkTRCreatePage.selectFormInDropdownList(driver, formTest);

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 27: Click on Clear button");
		log.info("TC 22 - Step 27: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 28: Click on Use a PDQ");
		log.info("TC 22 - Step 28: Click on Use a PDQ");
		linkTRCreatePage.clickOnUseAPreDefinedQuery();

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 29: Verify the created template display");
		log.info("TC 22 - Step 29: Verify the created template display");
		Assert.assertTrue(linkTRCreatePage.isTemplateDisplayedByName(driver, nameTemplate2.substring(0, 2), environmentConfig.getUserName() + "/" + nameTemplate2));

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 30: Click on template: " + nameTemplate2);
		log.info("TC 22 - Step 30: Click on template: " + nameTemplate2);
		linkTRCreatePage.selectValueInEditableDropdown(driver, nameTemplate2.substring(0, 2), environmentConfig.getUserName() + "/" + nameTemplate2);
		linkTRCreatePage.delay(5);

		ExtentTestManager.getTest().log(Status.INFO, "TC 22 - Step 31: Verify the data of template is load to the TR form");
		log.info("TC 22 - Step 23: Verify the data of template is load to the TR form");
		Assert.assertEquals(linkTRCreatePage.getAttributeValueOfTextboxByNameAtHeaderForCreate(driver, "T/R #", "value"), TR);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POL"), polTemplate);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtHeaderForCreate(driver, "POD"), podTemplate);
		Assert.assertEquals(linkTRCreatePage.getAttributeValueOfTextboxByNameAtHeaderForCreate(driver, "CONSIGNEE REF.", "value"), consigneeRef);
		Assert.assertEquals(linkTRCreatePage.getAttributeValueOfTextboxByNameAtHeaderForCreate(driver, "SHIPPER REF.", "value"), shipperRef);
		linkTRCreatePage.scrollToCollapseByName(driver, "EVENTS");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "EVENT"), TARRE);
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "EVENTS", "1", "LOCATION"), podTemplate);
		linkTRCreatePage.scrollToCollapseByName(driver, "RELATED PARTIES");
		Assert.assertEquals(linkTRCreatePage.getTextOnDropdownByNameAtCollapseNameForCreate(driver, "RELATED PARTIES", "1", "ROLE"), KEA);
		Assert.assertEquals(linkTRCreatePage.getTextOnCodeDropdownAtLineNumber("1"), codeRole);

	}

	@Test
	public void TC_23_Delete_template(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 23: Verify sharing template");
		ExtentTestManager.getTest().log(Status.INFO, "TC 23 - Step 01: Navigate to Transport Requet/Create Page");
		log.info("TC 23 - Step 01: Navigate to Transport Requet/Create Page");
		linkLoginPage = linkTRCreatePage.clickOnLogOutButton(driver);
		linkHomePage = linkLoginPage.loginAsLINKUser(environmentConfig.getUserName(), environmentConfig.getPassword());
		linkTRCreatePage = (LINKTransportRequestCreationPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPORT_REQUEST_CREATE);

		ExtentTestManager.getTest().log(Status.INFO, "TC 23 - Step 02: Select the form: " + formTest);
		log.info("TC 23 - Step 02: Select the form: " + formTest);
		linkTRCreatePage.selectFormInDropdownList(driver, formTest);

		ExtentTestManager.getTest().log(Status.INFO, "TC 23 - Step 03: Click on Clear button");
		log.info("TC 23 - Step 03: Click on Clear button");
		linkTRCreatePage.clickOnClearButton();

		ExtentTestManager.getTest().log(Status.INFO, "TC 23 - Step 04: Click on Use a Predefined Query");
		log.info("TC 23 - Step 04: Click on Use a Predefined Query");
		linkTRCreatePage.clickOnUseAPreDefinedQuery();

		ExtentTestManager.getTest().log(Status.INFO, "TC 23 - Step 05: Click on template: " + nameTemplate1);
		log.info("TC 23 - Step 05: Click on template: " + nameTemplate1);
		linkTRCreatePage.selectValueInEditableDropdown(driver, "au", nameTemplate1);

		ExtentTestManager.getTest().log(Status.INFO, "TC 23 - Step 06: Click on Delete template button");
		log.info("TC 23 - Step 06: Click on Delete template button");
		linkTRCreatePage.clickOnDeleteTemplateButton();
		linkTRCreatePage.waitForLoadingPageUnDisplay(driver);
		Assert.assertEquals(linkTRCreatePage.getNoitificationMessage(driver), "TEMPLATE DELETED");

		ExtentTestManager.getTest().log(Status.INFO, "TC 23 - Step 07: Click on Use a Predefined Query again");
		log.info("TC 23 - Step 07: Click on Use a Predefined Query again");
		linkTRCreatePage.clickOnUseAPreDefinedQuery();

		ExtentTestManager.getTest().log(Status.INFO, "TC 23 - Step 08: Verify the template " + nameTemplate1 + " does not exist");
		log.info("TC 23 - Step 08: Verify the template " + nameTemplate1 + " does not exist");
		Assert.assertTrue(linkTRCreatePage.isTemplateUnDisplayByName(nameTemplate1));

		ExtentTestManager.getTest().log(Status.INFO, "TC 23 - Step 09: Click on template: " + nameTemplate2);
		log.info("TC 23 - Step 09: Click on template: " + nameTemplate2);
		linkTRCreatePage.selectValueInEditableDropdown(driver, "au", nameTemplate2);

		ExtentTestManager.getTest().log(Status.INFO, "TC 23 - Step 10: Click on Delete template button");
		log.info("TC 23 - Step 10: Click on Delete template button");
		linkTRCreatePage.clickOnDeleteTemplateButton();
		linkTRCreatePage.waitForLoadingPageUnDisplay(driver);
		Assert.assertEquals(linkTRCreatePage.getNoitificationMessage(driver), "TEMPLATE DELETED");

		ExtentTestManager.getTest().log(Status.INFO, "TC 23 - Step 11: Click on Use a Predefined Query again");
		log.info("TC 23 - Step 11: Click on Use a Predefined Query again");
		linkTRCreatePage.clickOnUseAPreDefinedQuery();

		ExtentTestManager.getTest().log(Status.INFO, "TC 23 - Step 12: Verify the template " + nameTemplate2 + " does not exist");
		log.info("TC 23 - Step 12: Verify the template " + nameTemplate2 + " does not exist");
		Assert.assertTrue(linkTRCreatePage.isTemplateUnDisplayByName(nameTemplate2));
	}

	@AfterClass(alwaysRun = true)

	public void afterClass() {
		closeBrowserAndDriver();
	}

}
