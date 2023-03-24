package pageUI;

public class LINKTransportRequestSearchUI {
	
	public static final String RESULT_TABLE = "css=div.gridbox-container";
	public static final String INDEX_COLLUMN_BY_NAME = "xpath=//thead//span[text()='%s']/ancestor::th/preceding-sibling::th";
	public static final String CELL_OF_COLLUMN_BY_NAME = "xpath=//tbody/tr/td[%s]";
	public static final String FILTER_BY_COLUMN_NAME = "xpath=//span[text()='%s']/parent::a/preceding-sibling::a/span[@class='k-icon k-i-filter']";
	public static final String VALUE_FILTER_FIRST = "xpath=//div[text()='Show items with value that:']/following-sibling::input[@title='Value']";
	public static final String OPERATOR_FIRST = "xpath=//div[text()='Show items with value that:']/following-sibling::select[@title='Operator']";
	public static final String FILTER_BUTTON = "xpath=//div[text()='Show items with value that:']/following-sibling::div/button[text()='Filter']";
	public static final String LOADING_ICON = "css=div.k-loading-mask";
	public static final String ROLE = "css=div#s2id_DynModel_INTs0__COD_ROL";
	public static final String THIRD_PARTY = "css=div#s2id_DynModel_INTs0__TRA";
	public static final String REFERENCE = "css=div#s2id_DynModel_REFs0__COD_REF";
	public static final String REF_VALUE = "css=input#DynModel_REFs0__VALEUR_REF";
	public static final String ABSENT_CHECKBOX_AT_REFERENCE = "xpath=//label[@for='DynModel_REFs0__REF_EXCLUDE']";
	public static final String EYE_ICON_BY_NAME = "xpath=//table/tbody//span[text()='%s']/parent::td/preceding-sibling::td/a";
	
	public static final String GO_BUTTON_UP = "css=input#SubmitButtonUp";
	public static final String GO_BUTTON_DOWN = "css=input#SubmitButtonDown";
	public static final String INPUT_TO_REFERENCE_DROPDOWN = "css=div#s2id_DynModel_REFs0__COD_REF input";
	
	public static final String CHILD_LOCATOR_IN_DROPDOWN = "css=ul.select2-results div.select2-result-label";
	
	public static final String CALENDAR_CREATION_DATE = "xpath=//label[text()='CREATION DATE']/parent::div/following-sibling::div//div[@class='dateRange-buttons']//span[text()='Calendar']";
	public static final String CALENDAR_EVENTS_COLLAPSE = "xpath=//a[contains(text(),'EVENTS')]/following-sibling::div//div[contains(@class,'extensible-table-form-row')]//span[text()='Calendar']";
	public static final String RANGE_CREATION_DATE = "xpath=//label[text()='CREATION DATE']/parent::div/following-sibling::div//div[@class='dateRange-buttons']//span[text()='Range']";
	public static final String SLIDER_RANGE_CREATION_DATE = "xpath=//label[text()='CREATION DATE']/parent::div/following-sibling::div//div[@class='dateRange-range']/div[@id='slider_DynModel_DAT_CRE_DATERANGE']";
	public static final String FROM_TEXTBOX_EVENTS_COLLAPSE = "xpath=//a[contains(text(),'EVENTS')]/following-sibling::div//div[contains(@class,'extensible-table-form-row')]//input[@name='DynModel.EVTs[0].DAT_EVT_DATERANGECalendarFrom']";
	public static final String FROM_TEXTBOX_CREATION_DATE = "xpath=//label[text()='CREATION DATE']/parent::div/following-sibling::div//input[@name='DynModel.DAT_CRE_DATERANGECalendarFrom']";
	public static final String TO_TEXTBOX_CREATION_DATE = "xpath=//label[text()='CREATION DATE']/parent::div/following-sibling::div//input[@name='DynModel.DAT_CRE_DATERANGECalendarTo']";
	public static final String TO_TEXTBOX_EVENTS_COLLAPSE = "xpath=//a[contains(text(),'EVENTS')]/following-sibling::div//div[contains(@class,'extensible-table-form-row')]//input[@name='DynModel.EVTs[0].DAT_EVT_DATERANGECalendarTo']";
	public static final String CREATION_DATE_WEEK = "xpath=//label[text()='CREATION DATE']/parent::div/following-sibling::div//li[text()='WEEK']/following-sibling::li[text()='W']";
	public static final String EYE_ICON_FIRST_LINE = "xpath=//tbody/tr[1]/td[1]//span";

}
