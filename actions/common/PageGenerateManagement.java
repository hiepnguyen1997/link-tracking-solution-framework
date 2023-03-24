package common;

import org.openqa.selenium.WebDriver;

import pageObject.LINKHomePageObject;
import pageObject.LINKLoginPageObject;
import pageObject.LINKMySettingPageObject;
import pageObject.LINKShipmentCreationPageObject;
import pageObject.LINKShipmentSearchPageObject;
import pageObject.LINKTRDetailPageObject;
import pageObject.LINKTransportRequestCreationPageObject;
import pageObject.LINKTransportRequestSearchPageObject;

public class PageGenerateManagement {
	public static LINKLoginPageObject getLINKLoginPage(WebDriver driver) {
		return new LINKLoginPageObject(driver);
	}
	
	public static LINKHomePageObject getLINKHomePage(WebDriver driver) {
		return new LINKHomePageObject(driver);
	}
	
	public static LINKShipmentCreationPageObject getSHPCreationPage(WebDriver driver) {
		return new LINKShipmentCreationPageObject(driver);
	}
	
	public static LINKShipmentSearchPageObject getSHPSearchPage(WebDriver driver) {
		return new LINKShipmentSearchPageObject(driver);
	}
	
	public static LINKTransportRequestCreationPageObject getTRCreationPage(WebDriver driver) {
		return new LINKTransportRequestCreationPageObject(driver);
	}
	
	public static LINKTransportRequestSearchPageObject getTRSearchPage(WebDriver driver) {
		return new LINKTransportRequestSearchPageObject(driver);
	}
	
	public static LINKMySettingPageObject getMySettingPage(WebDriver driver) {
		return new LINKMySettingPageObject(driver);
	}
	
	public static  LINKTRDetailPageObject getTRDetailPage(WebDriver driver) {
		return new LINKTRDetailPageObject(driver);
	}
}
