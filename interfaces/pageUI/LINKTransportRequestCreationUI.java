package pageUI;

public class LINKTransportRequestCreationUI {
	public static final String INPUT_VALUE_IN_DROPDOWN = "css=div#select2-drop input";
	public static final String CHILD_LOCATOR_IN_DROPDOWN = "xpath=//ul[@class='select2-results']//div[@class='select2-result-label']";
	public static final String LOADING_PAGE = "css=div.loading-page";
	public static final String GO_UP_BUTTON = "xpath=//div[@class='PDQ']/following-sibling::div[@class='button-search']/input[@id='SubmitButtonUp']";
	public static final String CLEAR_UP_BUTTON = "xpath=//div[@class='PDQ']/following-sibling::div[@class='button-search']/input[@value='CLEAR']";
	public static final String ERROR_MESSAGE_AT_TEXTBOX_BY_NAME = "xpath=//label[text()='%s']/parent::div/following-sibling::div[1]//span[text()='Required']";
	public static final String SEARCH_BUTTON_IN_PO_COLAPPSE = "xpath=//div[@data-row-index='1']//label[text()='LINE NO']/parent::div/following-sibling::div[1]//input[@value='SEARCH']";
	public static final String SEARCH_BUTTON_IN_ATTACH_TR_COLAPPSE = "xpath=//div[@data-row-index='1']//label[text()='SUB TR #']/parent::div/following-sibling::div[1]//input[@value='SEARCH']";
	public static final String PO_INSERT_NAME_IN_PO_LINE_ATTACH_COLAPPSE= "xpath=//a[normalize-space()='PO LINES ATTACHEMENT (EASY MODE)']/following-sibling::div//div[@data-row-index='1']//span[@data-name='COD_CDE']";
	public static final String SEARCH_BUTTON_IN_PO_LINE_ATTACH_COLAPPSE = "xpath=//a[normalize-space()='PO LINES ATTACHEMENT (EASY MODE)']/following-sibling::div//div[@data-row-index='1']//input[@value='SEARCH']";
	public static final String LOADING_POPUP = "css=img#PanoramaAjaxLoading";
	public static final String NEW_AVAILABLE_QUANTITY_PO_COLLAPSE = "xpath=//div[@data-row-index='1']//label[text()='AVAILABLE QUANTITY']/parent::div/following-sibling::div[1]/span";
	public static final String DROPDOWN_BY_NAME_AT_TRANSPORT_REQUEST_COLLAPSE = "xpath=//a[normalize-space()='TRANSPORT REQUEST']/following-sibling::div//label[normalize-space(text())='%s']/parent::div/following-sibling::div[1]/div";
	public static final String TEXTBOX_BY_NAME_AT_TRANSPORT_REQUEST_COLLAPSE = "xpath=//a[normalize-space()='TRANSPORT REQUEST']/following-sibling::div//label[normalize-space(text())='%s']/parent::div/following-sibling::div[1]//input[1]";
	public static final String CODE_DROPDOWN_AT_LINE_NUMBER = "xpath=//a[normalize-space()='RELATED PARTIES']/following-sibling::div//div[@data-row-index='%s']//label[text()='CODE']/parent::div/following-sibling::div[1]/div/input/preceding-sibling::div";
	public static final String REF_VALUE_TEXTBOX = "xpath=//a[normalize-space()='REFERENCES']/following-sibling::div//div[@data-row-index='1']//label[text()='REF VALUE']/parent::div/following-sibling::div[1]//input[contains(@class,'text-box single-line')]";
	public static final String MORE_ICON_OF_CODE_BY_LINE_NUMBER = "xpath=//a[normalize-space()='RELATED PARTIES']/following-sibling::div//div[@data-row-index='%s']//label[text()='CODE']/parent::div/following-sibling::div//div[@class='popUp search-tra']";
	public static final String PRE_DEFINED_QUERY_DROPDOWN = "xpath=//span[normalize-space(text())='Use a Predefined Query']";
	public static final String SAVE_TEMPLATE = "xpath=//input[@value='SAVE TEMPLATE']";
	public static final String DELETE_TEMPLATE = "xpath=//input[@value='DELETE TEMPLATE']";
	public static final String INPUT_QUERY_NAME = "xpath=//tbody//th[text()=\"QUERY'S NAME\"]/following-sibling::td//input";
	public static final String TEMPLATE_BY_NAME = "xpath=//div[@class='select2-result-label' and normalize-space(text())='%s']";
	public static final String CONFIRM = "xpath=//tbody//td//input[@value='CONFIRM']";
	public static final String SHARE_WITH_THIRDPARTY = "xpath=//th[text()='SHARE WITH PARTICIPANT']/following-sibling::td";
	public static final String STATUS_DROPDOWN_LIST = "css=div#s2id_DynModel_STA_DEM";
	public static final String QUANTITY_TEXTBOX = "XPATH=//a[normalize-space()='PURCHASE ORDERS']/following-sibling::div//div[@data-row-index='1']//label[text()='QUANTITY']/parent::div/following-sibling::div[1]//input[1]";
	public static final String QUANTITY_TEXTBOX_MODIFY = "XPATH=//a[normalize-space()='PURCHASE ORDERS']/following-sibling::div//div[@data-row-index='1']//label[text()='QUANTITY']/parent::div/following-sibling::div[1]//input[2]";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
