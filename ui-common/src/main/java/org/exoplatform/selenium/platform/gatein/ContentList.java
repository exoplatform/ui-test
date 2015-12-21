package org.exoplatform.selenium.platform.gatein;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.WebDriver;

public class ContentList extends GateinLocator {
	
	
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
