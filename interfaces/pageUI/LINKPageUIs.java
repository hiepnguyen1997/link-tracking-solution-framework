package pageUI;

public class LINKPageUIs {
	
	/*
	 * Locator For Menu
	 */
	public static final String MENU_BY_NAME = "xpath=//span[text()='%s']/parent::a//parent::li";
	public static final String SUB_MENU_BY_NAME = "xpath=//span[text()='%s']/parent::a/following-sibling::ul//span[text()='%s']/parent::a";
	public static final String COLLAPSE_BY_NAME = "xpath=//a[normalize-space()='%s']/parent::div";
	public static final String NOITIFICATION_MESSAGE = "css=div.toast.toast-info>div.toast-message";
	public static final String LOGOUT_BUTTON = "css=li.logout";

	/*
	 * Locator At Search Page
	 */
	public static final String TEXTBOX_BY_NAME_IN_HEADER_SEARCH = "xpath=//div[@class='first-block summary-table']//label[text()='%s']/parent::div/following-sibling::div[1]//input[1]";
	public static final String TEXTBOX_BY_NAME_IN_COLLAPSE_NAME_SEARCH = "xpath=//a[normalize-space()='%s']/following-sibling::div//label[text()='%s']/parent::div/following-sibling::div[1]//input";
	public static final String DROPDOWN_LIST_BY_NAME_SEARCH = "xpath=//label[text()='%s']/parent::div/following-sibling::div[1]/div";

	/*
	 * Locator Of Dropdown List For Both Create And Search Page
	 */
	public static final String INPUT_TO_SINGLE_DROP = "css=div#select2-drop input";
	public static final String INPUT_TO_MULTIPLE_DROPDPOWN_BY_NAME = "xpath=//label[text()='%s']/parent::div/following-sibling::div[1]/div//input";
	public static final String CHILD_LOCATOR_IN_DROPDOWN = "css=ul.select2-results div.select2-result-label";
	public static final String TEMPLATE_BY_NAME = "xpath=//div[@class='select2-result-label' and normalize-space(text())='%s']";

	/*
	 * Locator At Creation Page
	 */
	public static final String TEXTBOX_BY_NAME_IN_HEADER_CREATE = "xpath=//div[contains(@class,'first-block summary-table')]//label[text()='%s']/parent::div/following-sibling::div[1]//input[1]";
	public static final String DROPDOWN_LIST_BY_NAME_IN_HEADER_CREATE = "xpath=//div[contains(@class,'first-block')]//label[text()='%s']/parent::div/following-sibling::div[1]/div";
	public static final String TEXTBOX_BY_NAME_AT_LINE_NUMBER_IN_COLLAPSE_NAME_CREATE = "xpath=//a[normalize-space()='%s']/following-sibling::div//div[@data-row-index='%s']//label[text()='%s']/parent::div/following-sibling::div[1]//input[1]";
	public static final String DROPDOWN_LIST_BY_NAME_AT_LINE_NUMBER_IN_COLLAPSE_NAME_CREATE = "xpath=//a[normalize-space()='%s']/following-sibling::div//div[@data-row-index='%s']//label[text()='%s']/parent::div/following-sibling::div[1]/div";
	public static final String FIELD_INSERT_BY_NAME_FOR_CREATE = "xpath=//div[@data-row-index='%s']//label[text()='%s']";
	public static final String ADD_BUTTON_AT_COLLAPSE_NAME = "xpath=//a[normalize-space()='%s']/following-sibling::div//button[@class='add-row float-left']";
	public static final String REMOVE_BUTTON_IN_LINE_NUMBER_AT_COLLAPSE_NAME = "xpath=//a[normalize-space()='%s']/following-sibling::div//div[@data-row-index='%s']//button[@class='remove-row float-left']";
	public static final String SCROLL_TO_COLLAPSE_BY_NAME = "xpath=//a[normalize-space()='%s']/following-sibling::div";
	public static final String SELECT_A_FORM_DROPDOWN_LIST = "xpath=//span[text()='Select a form']";
	public static final String LOADING_PAGE_CREATE = "css=div.loading-page";
	public static final String TEXT_INSERT_BY_FIELD_NAME_FOR_CREATE = "xpath=//div[@data-row-index='%s']//label[text()='%s']/parent::div/following-sibling::div[1]/span";
	public static final String GET_LINE_NUMBER_OF_COLLAPSE_BY_COLLAPSE_NAME_AT_CREATE_PAGE = "xpath=//a[normalize-space()='%s']/following-sibling::div//div[@data-row-index]";
	
	/*
	 * Locator For Popup 
	 */	
	public static final String LOADING_SEARCH_POPUP_RESULT = "css=div.k-loading-mask";
	public static final String GO_BUTTON_IN_POPUP = "css=div.popUp-window input#SubmitButtonDown";
	public static final String ATTACHED_SELECTED_BUTTON = "css=div.popUp-window div.specifics-grid-buttons>button";
	public static final String COLUMN_HEADER_BY_NAME = "xpath=//table/thead//span[text()='%s']/ancestor::th/preceding-sibling::th";
	public static final String CHECKBOX_BY_NAME_AT_TABLE_RESULT = "xpath=//table/tbody//span[starts-with(translate(text(), ' ', ' '), '%s')]/parent::td/preceding-sibling::td//label/preceding-sibling::input";
	public static final String RADIO_BUTTON_BY_NAME_AT_TABLE_RESULT = "xpath=//table/tbody//span[text()='%s']/parent::td/preceding-sibling::td//input";
	
	/*
	 * Locator At Detail Page 
	 */
	public static final String GET_ROW_NUMBER_OF_COLLAPSE_BY_COLLAPSE_NAME_AT_DETAIL_PAGE = "xpath=(//a[normalize-space(text())='%s']/following-sibling::div//table//thead)[1]/following-sibling::tbody/tr[@data-key]";
	public static final String COLUMN_INDEX_BY_NAME_AT_COLLAPSE_NAME = "xpath=(//a[normalize-space(text())='%s']/following-sibling::div//table//thead)[1]//a[text()='%s']/parent::th/preceding-sibling::th";
	public static final String VALUE_IN_COLLAPSE_NAME_AT_ROW_NUMBER_BY_COLUMN_NAME = "xpath=(//a[normalize-space(text())='%s']/following-sibling::div//table//thead)[1]/following-sibling::tbody/tr[@data-key][%s]/td[%s]";
	public static final String EXPAND_BUTTON_BY_COLLAPSE_NAME_AT_ROW_NUMBER = "xpath=(//a[normalize-space(text())='%s']/following-sibling::div//table//thead)[1]/following-sibling::tbody/tr[%s]/td/a[@class='expand-button']";
	public static final String SUB_TABLE_BY_COLLAPSE_NAME_AT_ROW_NUMBER = "xpath=(//a[normalize-space(text())='%s']/following-sibling::div//table//thead)[1]/following-sibling::tbody/tr[%s]/following-sibling::tr[@class='sub-table-row'][1]";
	public static final String TAB_INDEX_BY_COLLAPSE_NAME_AT_ROW_NUMBER = "XPATH=(//a[normalize-space(text())='%s']/following-sibling::div//table//thead)[1]/following-sibling::tbody/tr[%s]/following-sibling::tr[@class='sub-table-row'][1]//a[text()='%s']/parent::li/preceding-sibling::li";
	public static final String COLUMN_NAME_INDEX_IN_SUB_TABLE_BY_TAB_INDEX = "xpath=(//a[normalize-space(text())='%s']/following-sibling::div//table//thead)[1]/following-sibling::tbody/tr[%s]/following-sibling::tr[@class='sub-table-row'][1]//div[@class='content odd'][%s]//thead//a[text()='%s']/parent::th/preceding-sibling::th";
	public static final String VALUE_OF_COLUMN_NAME_IN_SUB_TABLE_BY_TAB_INDEX = "xpath=(//a[normalize-space(text())='%s']/following-sibling::div//table//thead)[1]/following-sibling::tbody/tr[%s]/following-sibling::tr[@class='sub-table-row'][1]//div[@class='content odd'][%s]//tbody//td[%s]";
	public static final String COLUMN_NAME_INDEX_IN_SUB_TABLE = "xpath=(//a[normalize-space(text())='%s']/following-sibling::div//table//thead)[1]/following-sibling::tbody/tr[%s]/following-sibling::tr[@class='sub-table-row'][1]//thead//a[text()='%s']/parent::th/preceding-sibling::th";
	public static final String VALUE_OF_COLUMN_NAME_IN_SUB_TABLE = "xpath=(//a[normalize-space(text())='%s']/following-sibling::div//table//thead)[1]/following-sibling::tbody/tr[%s]/following-sibling::tr[@class='sub-table-row'][1]//tbody//td[%s]";
	public static final String EXPAND_BUTTON_IN_SUB_TABLE_BY_COLLAPSE_NAME_AT_ROW_NUMBER = "xpath=(//a[normalize-space(text())='%s']/following-sibling::div//table//thead)[1]/following-sibling::tbody/tr[%s]/following-sibling::tr[@class='sub-table-row'][1]//tbody//td/a[@class='expand-button']";
	public static  final String COLUMN_NAME_INDEX_IN_SUB_CHILD_TABLE_BY_SUB_TAB_INDEX = "xpath=(//a[normalize-space(text())='%s']/following-sibling::div//table//thead)[1]/following-sibling::tbody/tr[%s]/following-sibling::tr[@class='sub-table-row'][1]//div[@class='content even'][%s]//thead//a[text()='%s']/parent::th/preceding-sibling::th";
	public static final String VALUE_OF_COLUMN_NAME_IN_SUB_CHILD_TABLE_BY_SUB_TAB_INDEX = "xpath=(//a[normalize-space(text())='%s']/following-sibling::div//table//thead)[1]/following-sibling::tbody/tr[%s]/following-sibling::tr[@class='sub-table-row'][1]//div[@class='content even'][%s]//tbody//td[%s]";
	public static final String EDIT_BUTTON_FOR_TR_DETAIL_PAGE = "xpath=//div[@id='footer']//span[@title='EDIT']";
	public static final String ATTACHMENT_FOR_TR_DETAIL_PAGE = "xpath=//div[@id='footer']//span[@id='footer-attachments']";
	public static final String UPLOAD_FILE = "xpath=//div[@data-row-index='1']//input[@type='file']";
	public static final String DOCUMENT_TYPE = "xpath=//div[@data-row-index='1']//span[text()='Search']";
	public static final String UPLOAD_BUTTON = "xpath=//input[@value='UPLOAD']";
	public static final String SUBMIT_BUTTON_TO_UPLOAD = "CSS=input.attSubmitUpload";
	public static final String CLOSE_ATTACHMENT_POPUP = "xpath=//span[text()='Attachments']/following-sibling::div//span[@class='k-icon k-i-close']";
	public static final String COLUMN_INDEX_BY_NAME_AT_ATTACHMENT_POPUP = "xpath=//div[@class='attachmentsList CheckboxTable']/table//th[text()='%s']/preceding-sibling::th";
	public static final String TEXT_VALUE_BY_COLUMN_INDEX_AT_ATTACHMENT_POPUP = "xpath=//div[@class='attachmentsList CheckboxTable']/table//tbody//td[%s]";
	
}
