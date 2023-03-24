package pageUI;

public class LINKTRDetailPageUI {
	public static final String TEXT_VALUE_AT_HEADER_BY_FIELD_NAME = "XPATH=//div[@class='summary DEM']//span[normalize-space(text())='%s :']/following-sibling::span";
	public static final String TEXT_VALUE_AT_TR_COLLAPSE_BY_FIELD_NAME_FOR_TR = "xpath=//a[normalize-space(text())='TRANSPORT REQUEST']/parent::div//span[normalize-space(text())='%s :']/following-sibling::span";
	public static final String COLUMN_INDEX_BY_NAME_AT_REFERENCE_COLLAPSE = "xpath=(//a[normalize-space(text())='REFERENCES']/following-sibling::div//table//thead)[1]//a[text()='%s']/parent::th/preceding-sibling::th";
	public static final String ALL_VALUE_AT_COLUMN_NAME_REFERENCE_COLLAPSE = "xpath=//a[normalize-space(text())='REFERENCES']/following-sibling::div//table//tbody/tr/td[%s]";
}
