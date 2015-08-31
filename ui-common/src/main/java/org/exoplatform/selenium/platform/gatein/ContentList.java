package org.exoplatform.selenium.platform.gatein;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContentList extends PlatformBase {
	
	public final By ELEMENT_EDIT_CLV = By.xpath("//*[@class='clv uiBox']");
	public final By ELEMENT_EDIT_PREFERENCE = By.xpath("//*[@class='UIPageBody']//*[@data-original-title='Preferences']");
	
	
	//Edit popup
	final public By ELEMENT_CONTENT_LIST_EDIT_BTN = By.xpath("//*[@class='uiIconEdit uiIconWhite']");
	final public By ELEMENT_CONTENT_LIST_SAVE_BTN = By.xpath("//*[@class='btn' and text()='Save']");
	final public By ELEMENT_CONTENT_LIST_CLOSE_BTN = By.id("Close");
	final public By ELEMENT_CONTENT_LIST_ADDPATH_BTN = By.xpath("//*[@class='uiIconAddPath uiIconLightGray']");
	final public By ELEMENT_CONTENT_LIST_BY_CONTENT_MODE = By.xpath("//*[@class='radio' and @value='ManualViewerMode']");
	final public By ELEMENT_CONTENT_LIST_BY_FOLDER_MODE = By.xpath("//*[@class='radio' and @value='AutoViewerMode']");
	
	//Conntent list Preference popup
	final public By ELEMENT_CONTENT_LIST_PREFERENCE_SAVE_BTN = By.xpath(".//*[@id='UICLVConfig']//a[text()='Save']");
	final public String ELEMENT_CONTENT_LIST_CONTENT_TITLE= "//*[@title='${title}']";
	
	
	//Multiple content selector popup
	final public String ELEMENT_MULTIPLE_CONTENT_POPUP_NODE_FOLDER= ".//span[contains(text(),'${node}')]";
	final public String ELEMENT_MULTIPLE_CONTENT_POPUP_FILE= ".//*[@id='ListRecords']//*[contains(text(),'${content}')]";
	final public By ELEMENT_MULTIPLE_CONTENT_POPUP_SAVE_BTN=By.xpath(".//*[@id='tab-UIContentBrowsePanelMulti']//button[text()='Save']");
	
	//Content Detail Preference
	final public By ELEMENT_CONTENT_LIST_DISPLAY_SETTING_TAB = By.xpath("//*[@data-target='#clvDisplayTab-tab']");
	
	//Content Detail Preference-->Display Setting tab
	final public By ELEMENT_DISPLAY_SETTING_TAB_HEADER_FIELD = By.xpath("//*[@id='UICLVConfigHeaderFormStringInput']");

    //View Content
	final public String ELEMENT_CONTENT_LIST_CONTENT_BOX_CONTENT_TITLE = "//*[@class='title']//*[text()='${title}']";
	final public By ELEMENT_CONTENT_LIST_CONTENT_BOX = By.xpath("//*[@class='uiContentBox']");
	final public By ELEMENT_CONTENT_LIST_CONTENT_BOX_PREFERENCES_BTN = By.xpath("//*[@class='uiContentBox']/../../../..//*[@data-original-title='Preferences']");
	
	public ContentList(WebDriver driver) {
		this.driver=driver;
	}	

	/**
	 * Select a folder or a content or both in Multiple Content Selector Pane popup
	 * @param arrayPath
	 * @param content
	 */
	public void selectFolderContent(String path, String content) {
		waitForAndGetElement(ELEMENT_CONTENT_LIST_ADDPATH_BTN);
		click(ELEMENT_CONTENT_LIST_ADDPATH_BTN);
		Utils.pause(2000);
		String[] arrayPath = path.split("/");
		for (String arrayElement : arrayPath) {
			click(ELEMENT_MULTIPLE_CONTENT_POPUP_NODE_FOLDER.replace("${node}", arrayElement));
		}
		
		if (content != "" || content != null) {
			waitForAndGetElement(ELEMENT_MULTIPLE_CONTENT_POPUP_FILE.replace("${content}", content));
			//click(ELEMENT_MULTIPLE_CONTENT_POPUP_FILE.replace("${content}",content));
			clickByJavascript(ELEMENT_MULTIPLE_CONTENT_POPUP_FILE.replace("${content}",content), 2);
		}
		Utils.pause(2000);
	}
	
	
}
