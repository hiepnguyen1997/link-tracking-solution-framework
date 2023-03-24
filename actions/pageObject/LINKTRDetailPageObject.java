package pageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.BasePage;
import common.PageGenerateManagement;
import pageObject.Menu.ListMenuPageObject;
import pageUI.LINKTRDetailPageUI;

public class LINKTRDetailPageObject extends ListMenuPageObject{
	private WebDriver driver;
	
	public LINKTRDetailPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public int getColumnSizeByColumnName(String columnName) {
		int columnIndex = getElementSize(driver, LINKTRDetailPageUI.COLUMN_INDEX_BY_NAME_AT_REFERENCE_COLLAPSE, columnName) + 1;
		return getElementSize(driver, LINKTRDetailPageUI.ALL_VALUE_AT_COLUMN_NAME_REFERENCE_COLLAPSE, String.valueOf(columnIndex));
				
	}

	public boolean isValueAtColumnNameDisplayAtReferenceCollapse(String columnName, String... refValue) {
		int  count = 0;
		int columnIndex = getElementSize(driver, LINKTRDetailPageUI.COLUMN_INDEX_BY_NAME_AT_REFERENCE_COLLAPSE, columnName) + 1;
		ArrayList<String> allValue = new ArrayList<String>();
		List<WebElement> allCell = getListWebElement(driver, LINKTRDetailPageUI.ALL_VALUE_AT_COLUMN_NAME_REFERENCE_COLLAPSE, String.valueOf(columnIndex));
		for (WebElement eachCell : allCell) {
			allValue.add(eachCell.getText().trim());
		}
		for (String ref : refValue) {
			for (String value : allValue) {
				if (value.equals(ref)) {
					count = count + 1 ;
					System.out.println(count);
				}
			}
		}
		return (refValue.length == count);
	}
	
	public String getTextValueByFieldNameAtHeaderForTRDetail(String fieldName) {
		waitForElementVisible(driver, LINKTRDetailPageUI.TEXT_VALUE_AT_HEADER_BY_FIELD_NAME, fieldName);
		return getElementText(driver, LINKTRDetailPageUI.TEXT_VALUE_AT_HEADER_BY_FIELD_NAME, fieldName);
	}
	
	public String getTextValueByFieldNameAtTRCollapseForTRDetail(String fieldName) {
		waitForElementVisible(driver, LINKTRDetailPageUI.TEXT_VALUE_AT_TR_COLLAPSE_BY_FIELD_NAME_FOR_TR, fieldName);
		return getElementText(driver, LINKTRDetailPageUI.TEXT_VALUE_AT_TR_COLLAPSE_BY_FIELD_NAME_FOR_TR, fieldName);
	}
	
	
}
