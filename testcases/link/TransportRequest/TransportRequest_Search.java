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
import pageObject.LINKShipmentCreationPageObject;
import pageObject.LINKTransportRequestSearchPageObject;
import pageUI.ListThirdPartyName;
import pageUI.navigations.MenuName;
import pageUI.navigations.SubMenuName;
import reportConfig.ExtentTestManager;
import testData.LINK_TR_Search_Data;
import utilities.EnvironmentConfig;

public class TransportRequest_Search extends BaseTest {
	WebDriver driver;
	LINKLoginPageObject linkLoginPage;
	LINKHomePageObject linkHomePage;
	LINKMySettingPageObject linkMySettingPage;
	LINKShipmentCreationPageObject linkSHPCreationPage;
	LINKTransportRequestSearchPageObject linkTRSearchPage;
	EnvironmentConfig environmnentConig;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		ConfigFactory.setProperty("evn", environmentName);
		environmnentConig = ConfigFactory.create(EnvironmentConfig.class);
		driver = getBrowserName(browserName, environmnentConig.getURL());
		linkLoginPage = PageGenerateManagement.getLINKLoginPage(driver);
		linkHomePage = linkLoginPage.loginAsLINKUser(environmnentConig.getUserName(), environmnentConig.getPassword());
		linkMySettingPage = (LINKMySettingPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.SETTINGS, SubMenuName.SETTINGS_MY_SETTING);
		linkMySettingPage.clickOnThirdPartyCheckboxByName(ListThirdPartyName.NEW_BOLLO);
	}

	@Test
	public void TC_02_Search_By_TR_Code(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 02: Search by TR code");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transpor Request/Search");
		log.info("Step 01: Navigate to Transpor Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkMySettingPage.openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Input to T/R# field with value iboucif01");
		log.info("Step 02: Input to T/R# field with value iboucif01");
		linkTRSearchPage.inputToTextboxByNameForSearch(driver, "T/R #", "iboucif01");

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Click on Go to search");
		log.info("Step 03: Click on Go to search");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Verify the result with iboucif01 display");
		log.info("Step 04: Verify the result with iboucif01 display");
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif01"));
	}

	@Test
	public void TC_03_Search_By_User(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 03: Search by User");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transpor Request/Search");
		log.info("Step 01: Navigate to Transpor Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on USER dropdown");
		log.info("Step 02: Click on USER dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "USER");

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Select the user IBOUCIF");
		log.info("Step 03: Select the user IBOUCIF");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "iboucif", LINK_TR_Search_Data.IBOUCIF_USER);

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Click on Go to search");
		log.info("Step 04: Click on Go to search");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Verify the result with iboucif01, iboucif02, iboucif03 display");
		log.info("Step 05: Verify the result with iboucif01, iboucif02, iboucif03 dispaly");
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif01"));
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif02"));
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif03"));
	}

	@Test
	public void TC_04_Search_By_User(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 04: Search by User 2");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transpor Request/Search");
		log.info("Step 01: Navigate to Transpor Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on USER dropdown");
		log.info("Step 02: Click on USER dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "USER");

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Select the user IBOUCIF");
		log.info("Step 03: Select the user IBOUCIF");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "iboucif", LINK_TR_Search_Data.IBOUCIF_USER);

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Click on VALIDATE BY dropdown");
		log.info("Step 04: Click on VALIDATE BY dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "VALIDATED BY");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Select the value VNET - Vincent NET");
		log.info("Step 05: Select the value VNET - Vincent NET");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "vnet", "VNET - Vincent NET");

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Click on Go to search");
		log.info("Step 06: Click on Go to search");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Verify the result with iboucif01 display");
		log.info("Step 07: Verify the result with iboucif01 display");
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif01"));
	}

	@Test
	public void TC_05_Search_By_User(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 05: Search by User 3");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transpor Request/Search");
		log.info("Step 01: Navigate to Transpor Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on USER dropdown");
		log.info("Step 02: Click on USER dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "USER");

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Select the user IBOUCIF");
		log.info("Step 03: Select the user IBOUCIF");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "iboucif", LINK_TR_Search_Data.IBOUCIF_USER);

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Click on ASSIGN TO dropdown");
		log.info("Step 04: Click on ASSIGN TO dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "ASSIGN TO");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Select the value SWANG - Sijun WANG");
		log.info("Step 05: Select the value SWANG - Sijun WANG");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "swang", "SWANG - Sijun WANG");

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Click on Go to search");
		log.info("Step 06: Click on Go to search");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Verify the result with iboucif01 display");
		log.info("Step 07: Verify the result with iboucif01 display");
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif01"));
	}

	@Test
	public void TC_06_Search_By_Status(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 06: Search by Status");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transpor Request/Search");
		log.info("Step 01: Navigate to Transpor Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on STATUS dropdown");
		log.info("Step 02: Click on STATUS dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "STATUS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Select th value END - END");
		log.info("Step 03: Select th value END - END");
		linkTRSearchPage.selectValueInDropdownMultiByNameForSearch(driver, "STATUS", "end", "END - END");

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Click on USER dropdown");
		log.info("Step 04: Click on USER dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "USER");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Select the user IBOUCIF");
		log.info("Step 05: Select the user IBOUCIF");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "iboucif", LINK_TR_Search_Data.IBOUCIF_USER);

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Click on Go to search");
		log.info("Step 06: Click on Go to search");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Verify the result with iboucif02 display");
		log.info("Step 07: Verify the result with iboucif02 display");
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif02"));
	}

	@Test
	public void TC_07_Search_By_Status(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 07: Search by Status 2");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transpor Request/Search");
		log.info("Step 01: Navigate to Transpor Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on STATUS dropdown and select value VAL - Validated");
		log.info("Step 02: Click on STATUS dropdown and select value VAL - Validated");
		linkTRSearchPage.selectValueInDropdownMultiByNameForSearch(driver, "STATUS", "val", "VAL - Validated");

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Click on USER dropdown");
		log.info("Step 03: Click on USER dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "USER");

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Select the user IBOUCIF");
		log.info("Step 04: Select the user IBOUCIF");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "iboucif", LINK_TR_Search_Data.IBOUCIF_USER);

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on Go to search");
		log.info("Step 05: Click on Go to search");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Verify the result with iboucif03 display");
		log.info("Step 06: Verify the result with iboucif03 display");
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif03"));
	}

	@Test
	public void TC_08_Search_By_Creation_Date(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 08: Search by Creation date");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transpor Request/Search");
		log.info("Step 01: Navigate to Transpor Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on USER dropdown");
		log.info("Step 02: Click on USER dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "USER");

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Select the user IBOUCIF");
		log.info("Step 03: Select the user IBOUCIF");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "iboucif", LINK_TR_Search_Data.IBOUCIF_USER);

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Click on Calendar icon at CREATION DATE");
		log.info("Step 04: Click on Calendar icon at CREATION DATE");
		linkTRSearchPage.clickOnCalendarAtCreationDate();

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Input to FROM with value 01 Mar 19");
		log.info("Step 05: Input to FROM with value 01 Mar 19");
		linkTRSearchPage.inputToFromTextboxAtCalendarCreationDate("01 Mar 19");

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Input to FROM with value 01 May 19");
		log.info("Step 06: Input to FROM with value 01 May 19");
		linkTRSearchPage.inputToToTextboxAtCalendarCreationDate("01 May 19");

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Click on Go to search");
		log.info("Step 07: Click on Go to search");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Verify the result with iboucif01, iboucif03 display");
		log.info("Step 08: Verify the result with iboucif01, iboucif03 display");
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif01"));
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif03"));

	}

	@Test
	public void TC_09_Search_By_Creation_Date(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 09: Search by Creation date 2");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transpor Request/Search");
		log.info("Step 01: Navigate to Transpor Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on USER dropdown");
		log.info("Step 02: Click on USER dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "USER");

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Select the user IBOUCIF");
		log.info("Step 03: Select the user IBOUCIF");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "iboucif", LINK_TR_Search_Data.IBOUCIF_USER);

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Click on Calendar icon at CREATION DATE");
		log.info("Step 04: Click on Calendar icon at CREATION DATE");
		linkTRSearchPage.clickOnCalendarAtCreationDate();

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Verify the FROM and TO field are displayed");
		log.info("Step 05: Verify the FROM and TO field are displayed");
		Assert.assertTrue(linkTRSearchPage.isFromInCalendarDisplayed());
		Assert.assertTrue(linkTRSearchPage.isToInCalendarDisplayed());
	}

	@Test
	public void TC_10_Search_By_Creation_Date(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 10: Search by Creation date 2");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transpor Request/Search");
		log.info("Step 01: Navigate to Transpor Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on USER dropdown");
		log.info("Step 02: Click on USER dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "USER");

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Select the user IBOUCIF");
		log.info("Step 03: Select the user IBOUCIF");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "iboucif", LINK_TR_Search_Data.IBOUCIF_USER);

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Click ion RANGE icon at CREATION DATE");
		log.info("Step 04: Click ion RANGE icon at CREATION DATE");
		linkTRSearchPage.clickRangeAtCreationDate();

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Verify the slider reange is display");
		log.info("Step 05: Verify the slider reange is display");
		Assert.assertTrue(linkTRSearchPage.isSliderInRangeDisplayed());
	}

	@Test
	public void TC_11_Search_By_POL(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 11: Search by POL");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transpor Request/Search");
		log.info("Step 01: Navigate to Transpor Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on USER dropdown");
		log.info("Step 02: Click on USER dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "USER");

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Select the user IBOUCIF");
		log.info("Step 03: Select the user IBOUCIF");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "iboucif", LINK_TR_Search_Data.IBOUCIF_USER);

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Open FLOW collapse");
		log.info("Step 04: Open FLOW collapse");
		linkTRSearchPage.openCollapseByName(driver, "FLOW");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on POL dropdown");
		log.info("Step 05: Click on POL dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "POL");

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Select the value SAJED - JEDDAH");
		log.info("Step 06: Select the value SAJED - JEDDAH");
		linkTRSearchPage.selectValueInDropdownMultiByNameForSearch(driver, "POL", "SAJE", "SAJED - JEDDAH");

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Click on Go to search");
		log.info("Step 07: Click on Go to search");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Verify the result with iboucif03 display");
		log.info("Step 08: Verify the result with iboucif03 display");
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif03"));
	}

	@Test
	public void TC_12_Search_By_POL(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 12: Search by POL 2");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transpor Request/Search");
		log.info("Step 01: Navigate to Transpor Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on USER dropdown");
		log.info("Step 02: Click on USER dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "USER");

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Select the user IBOUCIF");
		log.info("Step 03: Select the user IBOUCIF");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "iboucif", LINK_TR_Search_Data.IBOUCIF_USER);

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Open FLOW collapse");
		log.info("Step 04: Open FLOW collapse");
		linkTRSearchPage.openCollapseByName(driver, "FLOW");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on POL dropdown");
		log.info("Step 05: Click on POL dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "POL");

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Select the value CUHAV - La Habana");
		log.info("Step 06: Select the value CUHAV - La Habana");
		linkTRSearchPage.selectValueInDropdownMultiByNameForSearch(driver, "POL", "CUH", "CUHAV - La Habana");

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Click on Go to search");
		log.info("Step 07: Click on Go to search");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Verify the result with iboucif02 display");
		log.info("Step 08: Verify the result with iboucif02 display");
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif02"));
	}

	@Test
	public void TC_13_Search_By_POD(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 13: Search by POD");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transpor Request/Search");
		log.info("Step 01: Navigate to Transpor Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on USER dropdown");
		log.info("Step 02: Click on USER dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "USER");

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Select the user IBOUCIF");
		log.info("Step 03: Select the user IBOUCIF");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "iboucif", LINK_TR_Search_Data.IBOUCIF_USER);

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Open FLOW collapse");
		log.info("Step 04: Open FLOW collapse");
		linkTRSearchPage.openCollapseByName(driver, "FLOW");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on POD dropdown");
		log.info("Step 05: Click on POD dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "POD");

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Select the value DZTLM - TLEMCEN");
		log.info("Step 06: Select the value DZTLM - TLEMCEN");
		linkTRSearchPage.selectValueInDropdownMultiByNameForSearch(driver, "POD", "DZT", "DZTLM - TLEMCEN");

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Click on Go to search");
		log.info("Step 07: Click on Go to search");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Verify the result with iboucif03 display");
		log.info("Step 08: Verify the result with iboucif03 display");
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif03"));
	}

	@Test
	public void TC_14_Search_By_POD(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 14: Search by POD 2");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transpor Request/Search");
		log.info("Step 01: Navigate to Transpor Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on USER dropdown");
		log.info("Step 02: Click on USER dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "USER");

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Select the user IBOUCIF");
		log.info("Step 03: Select the user IBOUCIF");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "iboucif", LINK_TR_Search_Data.IBOUCIF_USER);

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Open FLOW collapse");
		log.info("Step 04: Open FLOW collapse");
		linkTRSearchPage.openCollapseByName(driver, "FLOW");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on POD dropdown");
		log.info("Step 05: Click on POD dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "POD");

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Select the value ALTIA - TIRANA");
		log.info("Step 06: Select the value ALTIA - TIRANA");
		linkTRSearchPage.selectValueInDropdownMultiByNameForSearch(driver, "POD", "alti", "ALTIA - TIRANA");

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Click on Go to search");
		log.info("Step 07: Click on Go to search");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Verify the result with iboucif02 display");
		log.info("Step 08: Verify the result with iboucif02 display");
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif02"));
	}

	@Test
	public void TC_15_Search_By_SHIPPER_REF(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 15: Search by SHIPPER REF");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transpor Request/Search");
		log.info("Step 01: Navigate to Transpor Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on USER dropdown");
		log.info("Step 02: Click on USER dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "USER");

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Select the user IBOUCIF");
		log.info("Step 03: Select the user IBOUCIF");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "iboucif", LINK_TR_Search_Data.IBOUCIF_USER);

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Open FLOW collapse");
		log.info("Step 04: Open FLOW collapse");
		linkTRSearchPage.openCollapseByName(driver, "FLOW");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Input to SHIPPER REF. with value 0123");
		log.info("Step 05: Input to SHIPPER REF. with value 0123");
		linkTRSearchPage.inputToTexboxNameAtCollapseNameForSearch(driver, "FLOW", "SHIPPER REF.", "0123");

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Click on Go to search");
		log.info("Step 06: Click on Go to search");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Verify the result with iboucif01 display");
		log.info("Step 07: Verify the result with iboucif01 display");
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif01"));
	}

	@Test
	public void TC_16_Search_By_CONSINGEE_REF(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 16: Search by CONSINGEE REF");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transpor Request/Search");
		log.info("Step 01: Navigate to Transpor Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on USER dropdown");
		log.info("Step 02: Click on USER dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "USER");

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Select the user IBOUCIF");
		log.info("Step 03: Select the user IBOUCIF");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "iboucif", LINK_TR_Search_Data.IBOUCIF_USER);

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Open FLOW collapse");
		log.info("Step 04: Open FLOW collapse");
		linkTRSearchPage.openCollapseByName(driver, "FLOW");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Input to CONSIGNEE REF. with value 212");
		log.info("Step 05: Input to CONSIGNEE REF. with value 212");
		linkTRSearchPage.inputToTexboxNameAtCollapseNameForSearch(driver, "FLOW", "CONSIGNEE REF.", "212");

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Click on Go to search");
		log.info("Step 06: Click on Go to search");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Verify the result with iboucif03 display");
		log.info("Step 07: Verify the result with iboucif03 display");
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif03"));
	}

	@Test
	public void TC_17_Search_By_Incoterm(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 17: Search by Incoterm");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transpor Request/Search");
		log.info("Step 01: Navigate to Transpor Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on USER dropdown");
		log.info("Step 02: Click on USER dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "USER");

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Select the user IBOUCIF");
		log.info("Step 03: Select the user IBOUCIF");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "iboucif", LINK_TR_Search_Data.IBOUCIF_USER);

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Open TRANSPORT REQUEST collapse");
		log.info("Step 04: Open TRANSPORT REQUEST collapse");
		linkTRSearchPage.openCollapseByName(driver, "TRANSPORT REQUEST");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on INCOTERN dropdown");
		log.info("Step 05: Click on INCOTERN dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "INCOTERM");

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Select the value FOB - Free on board");
		log.info("Step 06: Select the value FOB - Free on board");
		linkTRSearchPage.selectValueInDropdownMultiByNameForSearch(driver, "INCOTERM", "FO", "FOB - Free on board");

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Click on Go to search");
		log.info("Step 07: Click on Go to search");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Verify the result with iboucif02 display");
		log.info("Step 08: Verify the result with iboucif02 display");
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif02"));
	}

	@Test
	public void TC_18_Search_By_Departure_Date(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 18: Search by Departure Date");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transpor Request/Search");
		log.info("Step 01: Navigate to Transpor Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on USER dropdown");
		log.info("Step 02: Click on USER dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "USER");

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Select the user IBOUCIF");
		log.info("Step 03: Select the user IBOUCIF");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "iboucif", LINK_TR_Search_Data.IBOUCIF_USER);

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Open EVENTS collapse");
		log.info("Step 04: Open EVENTS collapse");
		linkTRSearchPage.openCollapseByName(driver, "EVENTS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on EVENT dropdown");
		log.info("Step 05: Click on EVENT dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "EVENT");

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Input and select the value Expected departure");
		log.info("Step 06: Input and select the value Expected departure");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "expected", "Expected departure");

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Click on Calendar icon in EVENTS collapse");
		log.info("Step 07: Click on Calendar icon in EVENTS collapse");
		linkTRSearchPage.clickOnCalendarInEventCollapse();

		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Input to FROM with value 01 May 19");
		log.info("Step 08: Input to FROM with value 01 May 19");
		linkTRSearchPage.inputToFromTextboxAtEventCollapse("01 May 19");

		ExtentTestManager.getTest().log(Status.INFO, "Step 09: Input to TO with value 31 May 19");
		log.info("Step 09: Input to TO with value 31 May 19");
		linkTRSearchPage.inputToToTextboxAtEventCollapse("31 May 19");

		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Click on Go to search");
		log.info("Step 10: Click on Go to search");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 11: Verify the result with iboucif03 display");
		log.info("Step 11: Verify the result with iboucif03 display");
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif03"));
	}

	@Test
	public void TC_19_Search_By_Transport_Type(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 19: Search by Transport Type");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transpor Request/Search");
		log.info("Step 01: Navigate to Transpor Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on USER dropdown");
		log.info("Step 02: Click on USER dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "USER");

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Select the user IBOUCIF");
		log.info("Step 03: Select the user IBOUCIF");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "iboucif", LINK_TR_Search_Data.IBOUCIF_USER);

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Open TRANSPORT REQUEST collapse");
		log.info("Step 04: Open TRANSPORT REQUEST collapse");
		linkTRSearchPage.openCollapseByName(driver, "TRANSPORT REQUEST");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on TRANSPORT TYPE dropdown");
		log.info("Step 05: Click on TRANSPORT TYPE dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "TRANSPORT TYPE");

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Select the value A - AIR");
		log.info("Step 06: Select the value A - AIR");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "A", "A - AIR");

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Click on Go to search");
		log.info("Step 07: Click on Go to search");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Verify the result with iboucif01, iboucif02, iboucif03 display");
		log.info("Step 08: Verify the result with iboucif01, iboucif02, iboucif03 display");
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif01"));
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif02"));
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif03"));
	}

	@Test
	public void TC_23_Search_By_3RD_Parties(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 23: Search by 3Rd Paries");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transpor Request/Search");
		log.info("Step 01: Navigate to Transpor Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on USER dropdown");
		log.info("Step 02: Click on USER dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "USER");

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Select the user IBOUCIF");
		log.info("Step 03: Select the user IBOUCIF");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "iboucif", LINK_TR_Search_Data.IBOUCIF_USER);

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Open 3RD PARTIES collapse");
		log.info("Step 04: Open 3RD PARTIES collapse");
		linkTRSearchPage.openCollapseByName(driver, "3RD PARTIES");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on Role dropdown");
		log.info("Step 05: Click on Role dropdown");
		linkTRSearchPage.clickOnRoleDropdownList();

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Select value ROUTED BY");
		log.info("Step 06: Select value ROUTED BY");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "dor", "ROUTED BY");

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Click on 3Rd Parties dropdown");
		log.info("Step 07: Click on 3Rd Parties dropdown");
		linkTRSearchPage.clickOnThirdDropdownList();

		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Select the value 010100 - NEW BOLLO");
		log.info("Step 08: Select the value 010100 - NEW BOLLO");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "010100", "010100 - NEW BOLLO");

		ExtentTestManager.getTest().log(Status.INFO, "Step 09: Click on Go to search");
		log.info("Step 09: Click on Go to search");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Verify the result with iboucif01, iboucif02, iboucif03 display");
		log.info("Step 10: Verify the result with iboucif01, iboucif02, iboucif03 display");
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif01"));
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif02"));
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif03"));
	}

	@Test
	public void TC_24_Search_BY_Reference_1(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 24: Search by Reference");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transpor Request/Search");
		log.info("Step 01: Navigate to Transpor Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on USER dropdown");
		log.info("Step 02: Click on USER dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "USER");

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Select the user IBOUCIF");
		log.info("Step 03: Select the user IBOUCIF");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "iboucif", LINK_TR_Search_Data.IBOUCIF_USER);

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Open REFERENCES collapse");
		log.info("Step 04: Open REFERENCES collapse");
		linkTRSearchPage.openCollapseByName(driver, "REFERENCES");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on Reference dropdown");
		log.info("Step 05: Click on Reference dropdown");
		linkTRSearchPage.clickOnRerferenceDropdownList();

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Select the value 0DS - Sous-traitance DST/010100");
		log.info("Step 06: Select the value 0DS - Sous-traitance DST/010100");
		linkTRSearchPage.selectValueInReferenceDropdown("0DS", "0DS - Sous-traitance DST/010100");

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Input to Value field with value 45");
		log.info("Step 07: Input to Value field with value 45");
		linkTRSearchPage.inputToRefValueTextbox("45");

		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Click on Go to search");
		log.info("Step 08: Click on Go to search");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 09: Verify the result with iboucif03 display");
		log.info("Step 09: Verify the result with iboucif03 display");
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif03"));
	}

	@Test
	public void TC_25_Search_BY_Reference_2(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 25: Search by Reference 2");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transpor Request/Search");
		log.info("Step 01: Navigate to Transpor Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on USER dropdown");
		log.info("Step 02: Click on USER dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "USER");

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Select the user IBOUCIF");
		log.info("Step 03: Select the user IBOUCIF");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "iboucif", LINK_TR_Search_Data.IBOUCIF_USER);

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Open REFERENCES collapse");
		log.info("Step 04: Open REFERENCES collapse");
		linkTRSearchPage.openCollapseByName(driver, "REFERENCES");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on Reference dropdown");
		log.info("Step 05: Click on Reference dropdown");
		linkTRSearchPage.clickOnRerferenceDropdownList();

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Select the value 0DS - Sous-traitance DST/010100");
		log.info("Step 06: Select the value 0DS - Sous-traitance DST/010100");
		linkTRSearchPage.selectValueInReferenceDropdown("0DS", "0DS - Sous-traitance DST/010100");

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Input to Value field with value 45");
		log.info("Step 07: Input to Value field with value 45");
		linkTRSearchPage.inputToRefValueTextbox("45");

		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Check to Absent checkbox");
		log.info("Step 08: Check to Absent checkbox");
		linkTRSearchPage.clickOnAbsentCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Step 09: Click on Go to search");
		log.info("Step 09: Click on Go to search");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 10: Verify the result with iboucif01, iboucif02 display");
		log.info("Step 10: Verify the result with iboucif01, iboucif02 display");
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif01"));
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif02"));
	}

	@Test
	public void TC_26_Search_By_Document(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC 26: Search by Document");
		ExtentTestManager.getTest().log(Status.INFO, "Step 01: Navigate to Transpor Request/Search");
		log.info("Step 01: Navigate to Transpor Request/Search");
		linkTRSearchPage = (LINKTransportRequestSearchPageObject) linkHomePage.getListMenu(driver).openPageOnMenuByPageName(MenuName.TRANSPORT_REQUEST, SubMenuName.TRANSPOT_REQUEST_SEARCH);

		ExtentTestManager.getTest().log(Status.INFO, "Step 02: Click on USER dropdown");
		log.info("Step 02: Click on USER dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "USER");

		ExtentTestManager.getTest().log(Status.INFO, "Step 03: Select the user IBOUCIF");
		log.info("Step 03: Select the user IBOUCIF");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "iboucif", LINK_TR_Search_Data.IBOUCIF_USER);

		ExtentTestManager.getTest().log(Status.INFO, "Step 04: Open DOCUMENTS collapse");
		log.info("Step 04: Open DOCUMENTS collapse");
		linkTRSearchPage.openCollapseByName(driver, "DOCUMENTS");

		ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on TYPE dropdown");
		log.info("Step 05: Click on TYPE dropdown");
		linkTRSearchPage.clickOnDropdownByNameForSearch(driver, "TYPE");

		ExtentTestManager.getTest().log(Status.INFO, "Step 06: Select the value BILL OF LADING");
		log.info("Step 06: Select the value BILL OF LADING");
		linkTRSearchPage.selectValueInEditableDropdown(driver, "bill", "BILL OF LADING");

		ExtentTestManager.getTest().log(Status.INFO, "Step 07: Click on Go to search");
		log.info("Step 07: Click on Go to search");
		linkTRSearchPage.clickOnGoButtonToSearchRecord();

		ExtentTestManager.getTest().log(Status.INFO, "Step 08: Verify the result with iboucif02 display");
		log.info("Step 08: Verify the result with iboucif02 display");
		Assert.assertTrue(linkTRSearchPage.isValueDisplayByCollumnName("T/R #", "iboucif02"));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
		driver.quit();
	}
}
