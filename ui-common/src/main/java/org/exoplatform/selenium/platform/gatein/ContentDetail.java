package org.exoplatform.selenium.platform.gatein;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.WebDriver;

public class ContentDetail extends GateinLocator {
	
	
	public ContentDetail(WebDriver driver) {
		this.driver=driver;
	}
	
	/**
	 * Select a folder or a content or both in Multiple Content Selector Pane popup
	 * @param arrayPath
	 * @param content
	 */
	public void selectFolderContent(String path, String content) {
		waitForAndGetElement(ELEMENT_CONTENT_DETAIL_ADDPATH_BTN);
		click(ELEMENT_CONTENT_DETAIL_ADDPATH_BTN);
		Utils.pause(2000);
		String[] arrayPath = path.split("/");
		for (String arrayElement : arrayPath) {
			click(ELEMENT_SELECT_CONTENT_POPUP_NODE_FOLDER.replace("${node}", arrayElement));
		}
		if (content != "" || content != null) {
			waitForAndGetElement(ELEMENT_SELECT_CONTENT_POPUP_FILE.replace("${content}", content));
			click(ELEMENT_SELECT_CONTENT_POPUP_FILE.replace("${content}", content));
		}
		Utils.pause(2000);
	}
		
}
