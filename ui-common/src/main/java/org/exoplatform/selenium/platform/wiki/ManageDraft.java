package org.exoplatform.selenium.platform.wiki;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;

/**
 * Provides all methods of managing a draft of wiki's page. 
 * 
 * 
 */
public class ManageDraft extends RichTextMode {
	ManageAlert magAl;
	
	public By ELEMENT_DRAFT_NOTIFY = By.xpath("//*[contains(@class, 'uiWikiPageEditForm') and contains(text(), 'Draft saved at')]");
			//By.xpath("//*[@class='uiWikiPageEditForm_MessageArea pull-left' and contains(text(), 'Draft saved at')]");
	
	//Manage Draft screen
	public String ELEMENT_DRAFT_OF_NEW_PAGE = "//*[@id='UIWikiDraftGrid']//*[text()='${title} (New Page)']";
	public String ELEMENT_DELETE_DRAFT = "//*[contains(text(), '${title}')]/../../..//*[contains(@class, 'uiIconDeleteDraft')]";
	public String ELEMENT_DRAFT_OF_EDIT_PAGE = "//*[@id='UIWikiDraftGrid']//*[text()='${title}']";
	
	/**
	 * Goes to the draft management of wiki's page.
	 * 
	 */
	public void goToManageDraft(){
		mouseOverAndClick(ELEMENT_BROWSE_LINK);
		mouseOverAndClick(ELEMENT_MY_DRAFT);
		Utils.pause(1000);
	}
	
	/**
	 * Deletes a draft.
	 * 
	 * 
	 * @param title The title of a wiki's page to be deleted.
	 */
	public void deleteDraft(String title){
		magAl = new ManageAlert(driver);
		click(ELEMENT_DELETE_DRAFT.replace("${title}", title));
		magAl.acceptAlert();
		waitForElementNotPresent(ELEMENT_DELETE_DRAFT.replace("${title}", title));
	}
}
