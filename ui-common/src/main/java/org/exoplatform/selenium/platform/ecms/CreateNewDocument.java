package org.exoplatform.selenium.platform.ecms;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformPermission;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateNewDocument extends ECMSLocator{

	PlatformPermission per;
	ManageAlert alert;

	SiteExplorerHome SEHome;
	
	public CreateNewDocument(WebDriver driver) {
		this.driver= driver;
		alert = new ManageAlert(driver);
	}

	/**
	 * Select a document by type
	 */
	public enum selectDocumentType{
		FILE, WEBCONTENT, ACCESSIBLEMEDIA, ANNOUNCEMENT, CSSFILE, CONTACTUS, HTMLFILE, ILLUSTRATEDWEBCONTENT,
		JAVASCRIPTFILE, PRODUCT, WEBLINK
	}

	/**
	 * Create a new document
	 * @param type
	 */
	public void createNewDoc(selectDocumentType type) {
		Utils.pause(2000);
		info("Go to type "+ type);
		switch(type){
		case FILE:
			info("Select File type");
			click(ELEMENT_ADDDOCUMENT_FILE);
			break;
		case WEBCONTENT:
			info("Select WebContent type");
			if((waitForAndGetElement(ELEMENT_ADDDOCUMENT_WEBCONTENT, 3000, 0) == null) && (waitForAndGetElement(ELEMENT_ADDDOCUMENT_NEXT_PAGE, 3000, 0) != null)) {
				clickByJavascript(ELEMENT_ADDDOCUMENT_NEXT_PAGE, 2);
				waitForAndGetElement(ELEMENT_ADDDOCUMENT_WEBCONTENT, DEFAULT_TIMEOUT, 1);
				clickByJavascript(ELEMENT_ADDDOCUMENT_WEBCONTENT, 2);
			}
			else
				clickByJavascript(ELEMENT_ADDDOCUMENT_WEBCONTENT, 2);
			break;
		case ACCESSIBLEMEDIA:
			info("Select Accessiblemedia type");
			click(ELEMENT_ADDDOCUMENT_ACCESSIBLE_MEDIA);
			break;
		case ANNOUNCEMENT:
			info("Select Announcement type");
			click(ELEMENT_ADDDOCUMENT_ANNOUNCEMENT);
			break;
		case CSSFILE:
			info("Select Css file type");
			click(ELEMENT_ADDDOCUMENT_CSS_FILE);
			break;
		case CONTACTUS:
			info("Select Contact us type");
			click(ELEMENT_ADDDOCUMENT_CONTACT_US);
			break;
		case HTMLFILE:
			info("Select HTML file type");
			click(ELEMENT_ADDDOCUMENT_HTML_FILE);
			break;
		case ILLUSTRATEDWEBCONTENT:
			info("Select Illustrated webcontent type");
			click(ELEMENT_ADDDOCUMENT_ILLUSTRATED_WEB_CONTENT);
			break;
		case WEBLINK:
			info("Select Weblink type");
			if((waitForAndGetElement(ELEMENT_ADDDOCUMENT_WEBLINK, 3000, 0) == null) && (waitForAndGetElement(ELEMENT_ADDDOCUMENT_NEXT_PAGE, 3000, 0) != null)) {
				clickByJavascript(ELEMENT_ADDDOCUMENT_NEXT_PAGE, 2);
				waitForAndGetElement(ELEMENT_ADDDOCUMENT_WEBLINK, DEFAULT_TIMEOUT, 1);
				clickByJavascript(ELEMENT_ADDDOCUMENT_WEBLINK, 2);
			}
			else
				click(ELEMENT_ADDDOCUMENT_WEBLINK);
			break;
		case PRODUCT:
			info("Select Product type");
			click(ELEMENT_ADDDOCUMENT_PRODUCT);
			break;
		case JAVASCRIPTFILE:
			info("Select Javascript file type");
			click(ELEMENT_ADDDOCUMENT_JAVASCRIPT_FILE);
			break;
		}
	}
	
	/**
	 * Define Folder types
	 * By  quynhpt
	 * Date 16/01/2015
	 */
	public enum folderType {
		None, Content, Document, Css;
	}
	
	/**
	 * Add and select the type of new Content Folder
	 * By QuynhPT
	 * Date 16/01/2015
	 * @param title
	 * @param type
	 */
	public void createNewFolder(String title, folderType type) {
		info("-- Creating a new folder --");
		info("Verify that the popup is shown");
		waitForAndGetElement(ELEMENT_ADD_NEW_FOLDER_POPUP_TITLE,2000,0);
		info("Verify that has check box element is shown on the popup");
		WebElement checkBox= waitForAndGetElement(ELEMENT_USE_CUSTOM_TYPE_FOLDER,5000, 0);
		info("if check box is avaiabled and unchecked, so, check it.");
		if (checkBox != null && !checkBox.isSelected()) {
			info("Check on check box");
			click(ELEMENT_USE_CUSTOM_TYPE_FOLDER, 2);
		}
		info("Select a type of new folder");
		switch (type) {
		case Content:
			info("Type a text to title field of Content type");
			type(ELEMENT_FOLDER_TITLE_TEXTBOX, title, true);
			break;
		case Document:
			info("Type a text to title field of Document type");
			type(ELEMENT_FOLDER_TITLE_TEXTBOX, title, true);
			info("Select Document type");
			selectOption(ELEMENT_FOLDER_TYPE_OPTION,
					ELEMENT_DOCUMENT_FOLDER_TYPE);
			break;
		default:
			break;
		}
		info("Save the changes");
		click(ELEMENT_CREATE_FOLDER_BUTTON);
		Utils.pause(2000);
	}

	/**
	 * Add a new file only with title and content string
	 * update QuynhPT
	 * date 13/01/2015
	 * @param title
	 * @param content
	 */
	public void addNewFile(String title, String content) {
		this.driver.navigate().refresh();
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_FILEFORM_BLANK_NAME, DEFAULT_TIMEOUT, 1);
		type(ELEMENT_FILEFORM_BLANK_NAME, title, true);
		inputFrame(ELEMENT_FILEFORM_BLANK_CONTENT,content);
	}

	/**
	 * Add a new Webcontent
	 * @param title
	 * @param content
	 */
	public void addNewWebContent(String title, String content) {
		driver.navigate().refresh();
		Utils.pause(1000);
		type(ELEMENT_FILEFORM_BLANK_NAME, title, true);
		inputDataToCKEditor(ELEMENT_FILEFORM_BLANK_CONTENT, content);
	}

	/**
	 * Add a new product
	 * @param title
	 * @param summary
	 */
	public void addNewProduct(String title, String summary){
		driver.navigate().refresh();
		type(ELEMENT_FILEFORM_BLANK_NAME,title,true);
		inputFrame(ELEMENT_FILEFORM_BLANK_CONTENT,summary);
		switchToParentWindow();
	}
	
	/**
	 * Save and close a file form
	 */

	public void saveAndClose() {
		clickByJavascript(ELEMENT_FILEFORM_BUTTON_SAVEANDCLOSE);
		Utils.pause(2000);
		waitForElementNotPresent(ELEMENT_FILEFORM_BUTTON_SAVEANDCLOSE);
		
	}

	/**
	 * Add a link into webcontent
	 * @param url
	 */
	public void addLinkInWebContent(String url) {
		click(ELEMENT_WEBCONTENTFORM_BUTTON_LINK);
		type(ELEMENT_WEBCONTENTFORM_LINK_ADRESS, url, true);
		click(ELEMENT_WEBCONTENTFORM_LINK_OK);
	}

	/**
	 * Create a Advanced Document
	 * @param name
	 * @param content
	 * @param title
	 * @param desc
	 * @param creator
	 * @param source
	 */
	public void createAdvancedDocument(String name, String content, String title, String desc, String creator, String source) {

		type(ELEMENT_FILEFORM_BLANK_NAME, title, true);
		inputFrame(ELEMENT_FILEFORM_BLANK_CONTENT , content);
		switchToParentWindow();
		if(title != "" && title != null){
			type(ELEMENT_DOCFORM_BLANK_TITLE,title, true);
		}
		if(desc != "" && desc != null){
			type(ELEMENT_DOCFORM_BLANK_DESC,desc, true);
		}
		if(creator != "" && creator != null){
			type(ELEMENT_DOCFORM_BLANK_CREATOR,creator, true);
		}
		if(source != "" && source != null){
			type(ELEMENT_DOCFORM_BLANK_SOURCE,source, true);
		}
	}
}
