package org.exoplatform.selenium.platform.ecms;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformPermission;
import org.exoplatform.selenium.platform.social.SocialLocator;
import org.exoplatform.selenium.platform.social.SpaceHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.social.SpaceSettingManagement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SiteExplorerHome extends ECMSLocator{
	
	ManageAlert alert;
	Button button;
	CreateNewDocument CreNewDoc;
	Dialog dialog;
	PlatformPermission plfPerm;
	
	public SiteExplorerHome(WebDriver dr){
		this.driver=dr;
		alert = new ManageAlert(dr);
		CreNewDoc = new CreateNewDocument(dr);
		button = new Button(dr);
		dialog= new Dialog(driver);
		plfPerm = new PlatformPermission(driver);
	}
	/**
	 * Go to a folder by a path in SE
	 * Example: go to Site management drive-->a folder
	 * @param path
	 * @param drive
	 */
	public void goToPath(String path, String drive){
		info("Go to selected Drive");
		waitForAndGetElement(ELEMENT_ACTIONBAR_SHOWDRIVES);
		click(ELEMENT_ACTIONBAR_SHOWDRIVES);
		waitForAndGetElement(ELEMENT_ACTIONBAR_SELECTED_DRIVE.replace("${driver}",drive));
		click(ELEMENT_ACTIONBAR_SELECTED_DRIVE.replace("${driver}",drive));
		waitForAndGetElement(ELEMENT_SIDE_BAR_MAINTAB);
		click(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON);
		info("Go to folder");
		if(!path.isEmpty()){
		String[] arrayPath = path.split("/");
		for (String arrayElement : arrayPath){
			selectNode(arrayElement);
		}
		}
	}
	/**
	 * Open Add a new folder popup
	 */
	public void goToAddNewFolder() {
		info("Add a new folder");
		if (waitForAndGetElement(ELEMENT_ACTIONBAR_ADDFOLDER, 7000, 0) == null){
			info("Click on More menu");
			click(ELEMENT_ACTIONBAR_MORE);
			waitForAndGetElement(ELEMENT_ACTIONBAR_ADDFOLDER, DEFAULT_TIMEOUT, 1);
			info("Click on New folder on Action Bar");
			click(ELEMENT_ACTIONBAR_ADDFOLDER);
		}
		else {
			waitForAndGetElement(ELEMENT_ACTIONBAR_ADDFOLDER, DEFAULT_TIMEOUT, 1);
			info("Click on New folder on Action Bar");
			click(ELEMENT_ACTIONBAR_ADDFOLDER);
		}
		info("Verify that Add folder popup is shown");
		waitForAndGetElement(ELEMENT_ADDFOLDERBOX);
		info("The folder is shown successfully");
	}

	/**
	 * Create a new folder. Input the title and folder type
	 * @param title
	 * @param folderType
	 */
	public void createFolder(String title, String folderType) {
		info("Type a title:" + title + " for the folder");
		type(ELEMENT_ADDFOLDER_NAME, title, true);
		if (!folderType.isEmpty()) {
			info("Select folder type:" + folderType);
			select(ELEMENT_ADDFOLDER_FOLDERTYPE, folderType);
		}
		info("Click on Create folder button");
		click(ELEMENT_ADDFOLDER_CREATEFOLDERBUTTON);
		info("Verify that the folder is created");
		waitForAndGetElement(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", title));
		info("The folder is created successfully");
	}


	/**
	 * Go to New content page
	 */
	public void goToAddNewContent() {
		waitForAndGetElement(ELEMENT_ACTIONBAR_ADDDOCUMENT);
		info("Click on New Document on Action Bar");
		click(ELEMENT_ACTIONBAR_ADDDOCUMENT);
		info("Verify that New content page is shown");
		waitForAndGetElement(ELEMENT_ADDDOCUMENT_CHOICETYPE);
		info("New content page is shown successfully");
	}
	/**
	 * Add Symlink for a node
	 * @param node
	 */
	public void addSymlink(String node){
		rightClickOnElement(By.xpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", node)));
		click(ELEMENT_SITEEXPLORER_ACTION_ADDSYMLINK);
		Utils.pause(2000);
	}
	/**
	 * Delete data by title
	 * @param title
	 */
	public void deleteData(String title, boolean...destination) {
		boolean verify = (destination.length > 0 ? destination[0]:false);
		info("Click on File Explorer icon");
		click(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON);
		Utils.pause(2000);
		info("Right click on nodename");
		rightClickOnElement(By.xpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", title)));
		info("Click on Delete link");
		click(ELEMENT_SITEEXPLORER_ACTION_DELETE);
		info("Click on Delete button on Confirm popup");
		//click(ELEMENT_SITEEXPLORER_CONFIRMBOX_DELETE);
		clickByJavascript(ELEMENT_SITEEXPLORER_CONFIRMBOX_DELETE, 2);
		info("Verify that the node is deleted");
		Utils.pause(3000);
		if (verify)
			waitForElementNotPresent(By.xpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", title)));
		info("the node is deleted successfully");
	}
	/**
	 * 
	 * @param title
	 * @param destination
	 */
	public void copyPasteNode(String title,String destination){
		rightClickOnElement(By.xpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", title)));
		click(ELEMENT_SITEEXPLORER_ACTION_COPY);
		rightClickOnElement(By.xpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", destination)));
		click(ELEMENT_SITEEXPLORER_ACTION_PASTE);
		driver.navigate().refresh();
		click(ELEMENT_SIDEBAR_SITES_MANAGEMENT);
		Utils.pause(2000);
	}
	/**
	 * Open list document's templates
	 */
	public void openListDocumentTemplateByRightClick(){
		info("Select Document type");
		int repeat =0;
		while(waitForAndGetElement(ELEMENT_WORKING_AREA_TEMPLATE_DOCUMENTS,2000,0)==null){
			if(repeat>5)break;
			if(waitForAndGetElement(ELEMENT_WORKING_AREA_TEMPLATE_DOCUMENTS,2000,0)!=null)
				break;
			rightClickOnElement(ELEMENT_THUMBNAIL_VIEW_ADMIN_VIEW);
			Utils.pause(2000);
			//Actions action = new Actions(this.driver);
			//action.moveToElement(waitForAndGetElement(ELEMENT_CONTEXT_MENU_ADD_DOCUMENT))
			//.doubleClick().perform();
			click(ELEMENT_CONTEXT_MENU_ADD_DOCUMENT,2);
			repeat++;
		}
		waitForAndGetElement(ELEMENT_WORKING_AREA_TEMPLATE_DOCUMENTS,2000,1);
		info("Document type is created");
	}

	/**
	 * Cut and paste node
	 * @param title
	 * @param destination
	 */
	public void cutPasteNode(String title,String destination){
		rightClickOnElement(By.xpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", title)));
		click(ELEMENT_SITEEXPLORER_ACTION_CUT);
		rightClickOnElement(By.xpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", destination)));
		click(ELEMENT_SITEEXPLORER_ACTION_PASTE);
		driver.navigate().refresh();
		click(ELEMENT_SIDEBAR_SITES_MANAGEMENT);
		Utils.pause(2000);
	}
	/**
	 * Rename a node
	 * @param node
	 * @param newName
	 */
	public void renameNode(String node, String newName){
		rightClickOnElement(By.xpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", node)));
		click(ELEMENT_SITEEXPLORER_ACTION_RENAME);
		type(ELEMENT_SITEEXPLORER_RENAME_FIELD,newName,true);
		click(ELEMENT_SITEEXPLORER_RENAME_SAVE);
		Utils.pause(2000);
		click(ELEMENT_SIDEBAR_SITES_MANAGEMENT);

	}

	/**
	 * Upload a file
	 * @param link
	 * @param params
	 */
	public void uploadFileWithDymanicPath(String link, Object... params) {
		Boolean verify = (Boolean) (params.length > 0 ? params[0] : true);
		Boolean prev = (Boolean) (params.length > 1 ? params[1] : false);
		if (waitForAndGetElement(ELEMENT_UPLOAD_BUTTON, 10000, 0) == null) {
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
		}
		click(ELEMENT_UPLOAD_LINK);
		if (prev){
			uploadFileUsingRobotDocumentPreview(link);
			info("Upload file " + getAbsoluteFilePathFromFile(link));
		}
		else{
			uploadFileUsingRobot(link);
			info("Upload file " + getAbsoluteFilePath(link));
		}
		
		waitForElementNotPresent(ELEMENT_UPLOAD_PROGRESS_BAR,120000,0);

		info("verify:"+verify);
		if (verify) {
			String links[] = link.split("/");
			int length = links.length;
			Utils.pause(2000);
			waitForAndGetElement(By.xpath("//*[contains(text(),'"
					+ links[length - 1] + "')]"));
		}

		info("Upload file successfully");
		Utils.pause(2000);
	}

	/**
	 * Upload a file
	 * @param link
	 * @param params
	 */
	public void uploadFile(String link, Object... params) {
		Boolean verify = (Boolean) (params.length > 0 ? params[0] : true);
		if (waitForAndGetElement(ELEMENT_UPLOAD_BUTTON, DEFAULT_TIMEOUT, 0) == null) {
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
		}
		Utils.pause(2000);
		click(ELEMENT_UPLOAD_LINK);
		uploadFileUsingRobot(link);
		//waitForElementNotPresent(ELEMENT_UPLOAD_PROGRESS_BAR);

		info("verify:"+verify);
		if (verify) {
			String links[] = link.split("/");
			int length = links.length;
			Utils.pause(2000);
			waitForAndGetElement(By.xpath("//*[contains(text(),'"+ links[length - 1] + "')]"),3000,1);
		}

		info("Upload file successfully");
		Utils.pause(2000);
	}

	/**
	 * Go to Edit page of a document
	 */

	public void goToEditDocument() {
		click(ELEMENT_ACTIONBAR_EDIT);
		Utils.pause(3000);
	}
	/**
	 * Add tag to a Content
	 * @param tag
	 */
	public void addTag(String tag){
		waitForAndGetElement(ELEMENT_ACTIONBAR_MORE);
		info("Click on More menu");
		click(ELEMENT_ACTIONBAR_MORE);
		info("Click on Tag link");
		click(ELEMENT_ACTIONBAR_TAG);
		info("Input name of tag");
		type(ELEMENT_TAG_FORM,tag,true);
		info("Click on Add button");
		click(ELEMENT_ADD_TAG_FORM);
		info("The tag is created successfully");
		info("Close the popup");
		click(ELEMENT_TAG_POPUP_CLOSE);
	}
	/**
	 * Edit a Tag
	 * @param oldName
	 * @param newName
	 */
	public void editTag(String oldName,String newName){
		info("Click on Tag Cloud tab of SE");
		click(ELEMENT_SITEEXPLORER_TAG_CLOUD_TAB);
		waitForAndGetElement(ELEMENT_SIDEBAR_TAGCLOUD_NAME.replace("${name}",oldName));
		info("Click on Edit button of Tag Cloud");
		click(ELEMENT_SIDEBAR_TAGCLOUD_EDIT);
		waitForAndGetElement(ELEMENT_SIDEBAR_TAGCLOUD_POPUP_TITLE);
		info("Click on Edit button of the old tag");
		click(ELEMENT_SIDEBAR_TAGCLOUD_POPUP_EDIT.replace("${name}",oldName));
		waitForAndGetElement(ELEMENT_TAG_POPUP_NAME_FIELD);
		info("Input new name of tag");
		((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('value', '" + newName +"')", waitForAndGetElement(ELEMENT_TAG_POPUP_NAME_FIELD));
		info("Save all changes");
		clickByJavascript(ELEMENT_TAG_POPUP_SAVE);
		info("Verify that the new name of tag is changed");
		waitForAndGetElement(ELEMENT_SIDEBAR_TAGCLOUD_NAME.replace("${name}",newName));
		info("the new name of tag is changed successfully");
		click(ELEMENT_TAGE_POPUP_CLOSE);
		info("The edit tag popup is closed");
	}
	/**
	 * Delete a tag
	 * @param tag
	 */
	public void deleteTag(String tag){
		info("Click on Tag Cloud tab of SE");
		click(ELEMENT_SITEEXPLORER_TAG_CLOUD_TAB);
		waitForAndGetElement(ELEMENT_SIDEBAR_TAGCLOUD_NAME.replace("${name}",tag));
		info("Click on Edit button of Tag Cloud");
		click(ELEMENT_SIDEBAR_TAGCLOUD_EDIT);
		waitForAndGetElement(ELEMENT_SIDEBAR_TAGCLOUD_POPUP_TITLE);
		info("Click on Delete button of the old tag");
		click(ELEMENT_SIDEBAR_TAGCLOUD_POPUP_DELETE.replace("${name}",tag));
		alert.acceptAlert();
		info("Verify that tag is delete");
		waitForElementNotPresent(ELEMENT_SIDEBAR_TAGCLOUD_NAME.replace("${name}",tag));
		info("The tag is deleted successfully");
		info("Close the popup");
		click(ELEMENT_TAGE_POPUP_CLOSE);

	}
	/**
	 * Edit a Document
	 * @param content
	 */
	public void editDocument(String newTitle,String content) {
		if(waitForAndGetElement(ELEMENT_ACTIONBAR_MORE,5000,0)!=null){
			info("Click on More menu");
			click(ELEMENT_ACTIONBAR_MORE);
		}
		info("Click on Edit link");
		click(ELEMENT_ACTIONBAR_EDIT);
		driver.navigate().refresh();
		Utils.pause(2000);
		if(!newTitle.isEmpty())
			waitForAndGetElement(ELEMENT_FILE_FORM_TITLE, DEFAULT_TIMEOUT, 1);
			type(ELEMENT_FILE_FORM_TITLE,newTitle, true );
		if(!content.isEmpty()){	
			waitForAndGetElement(CreNewDoc.ELEMENT_FILEFORM_BLANK_CONTENT, DEFAULT_TIMEOUT, 1);
			inputFrame(CreNewDoc.ELEMENT_FILEFORM_BLANK_CONTENT, content);
			switchToParentWindow();
		}
	}
	/**
	 * Go to Intranet page
	 */

	public void goToIntranet() {
		click(ELEMENT_SITEEXPLORER_LEFTBOX_INTRANET);
	}

	/**
	 * Go to document
	 */
	public void goToDocument() {
		click(ELEMENT_SITEEXPLORER_LEFTBOX_DOCUMENT);
	}

	/**
	 * Go to space
	 */
	public void goToSpace(String spaceName) {
		click(ELEMENT_ACTIONBAR_SHOWDRIVES);
		click(By.xpath((ELEMENT_SELECTDRIVE_SPECIFICDRIVE).replace("${spaceName}",spaceName)));
	}

	/**
	 * Select Drive by option
	 *
	 */
	public enum selectDriverOption{
		ALPHABETICAL, TYPE, CREATEDDATE, MODIFIEDDATE
	}

	/**
	 * Select Drive by order
	 *
	 */

	public enum selectDriverOrder{
		ASCENDING, DESCENDING
	}

	/**
	 * Open Setting drive page
	 * @param type
	 * @param order
	 */
	public void openSettingsDriver(selectDriverOption type, selectDriverOrder order) {
		click(ELEMENT_ACTIONBAR_SETTINGS);
		info("Go to type "+ type);
		switch(type){
		case ALPHABETICAL:
			select(ELEMENT_DRIVERSETTINGS_SORTBY, "Alphabetical");
			break;

		case TYPE:
			select(ELEMENT_DRIVERSETTINGS_SORTBY, "Type");
			break;

		case CREATEDDATE:
			select(ELEMENT_DRIVERSETTINGS_SORTBY, "Created Date");
			break;

		case MODIFIEDDATE:
			select(ELEMENT_DRIVERSETTINGS_SORTBY, "Modified Date");
			break;
		}

		info("Go to type "+ order);
		switch(order){
		case ASCENDING:
			select(ELEMENT_DRIVERSETTINGS_ORDER, "Ascending");
			break;

		case DESCENDING:
			select(ELEMENT_DRIVERSETTINGS_ORDER, "Descending");
			break;
		}

		click(ELEMENT_DRIVERSETTINGS_SAVE);
	}

	/**
	 * Go to Permission popup
	 */
	public void goToPermission() {
		info("Click on More link on Action bar");
		click(ELEMENT_ACTIONBAR_MORE);
		info("Click on Permission on Action bar");
		click(ELEMENT_ACTIONBAR_PERMISSION);
		info("Finished opening permission popup");
	}
	/**
	 * Select a node by name
	 * @param nodeName
	 */
	public void selectNode(String nodeName) {
		info("Verify that nodeName:"+nodeName+" is shown");
		waitForAndGetElement(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", nodeName));
		info("Click on the nodeName:"+nodeName);
		click(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", nodeName));
		Utils.pause(2000);
		info("Finished selecting nodeName:"+nodeName);
	}
	/**
	 * Go to Advanced Search page
	 */
	public void goToAdvancedSearch() {
		click(ELEMENT_SITEEXPLORER_LEFTBOX_SAVEDSEARCH);
		Utils.pause(2000);
		click(ELEMENT_SITEEXPLORER_LEFTBOX_ADVANCEDSEARCH);
	}
	/**
	 * Lock a Node
	 * @param name
	 */
	public void lockNode(String name){
		info("lock node:"+name);
		rightClickOnElement(By.xpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}",name)));
		click(ELEMENT_SITEEXPLORER_LIST_LOCK_NODE);
		waitForAndGetElement(ELEMENT_SITEEXPLORER_LOCK_ICON.replace("$node", name));
	}

	/**
	 * Unlock a Node
	 * @param name
	 */
	public void unlockNode(String name){
		info("unlock node:"+name);
		rightClickOnElement(By.xpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}",name)));
		click(ELEMENT_SITEEXPLORER_LIST_UNLOCK_NODE);
		waitForElementNotPresent(ELEMENT_SITEEXPLORER_LOCK_ICON.replace("$node", name));
	}


	/**
	 * By QuynhPT
	 * Open Add Relation popup
	 */
	public void goToManageRelation(){
		click(ELEMENT_ACTIONBAR_RELATION);
		Utils.pause(2000);
	}

	/**
	 * Add a Relation for many files
	 * @param nameContent
	 * @param path
	 */
	public void addRelation(String[] nameContent,String path) {
		for (String arrayElement:nameContent){
			goToPathHasFiles(path);
			click(ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB_SELECT_CONTENT_RIGHT_TREE
					.replace("${nameContent}",arrayElement));
			Utils.pause(2000);
		}
	}
	/**
	 * Open Add Category popup
	 * By QuynhPT
	 * date 16/01/2015
	 */
	public void goToAddCategory(){
		click(ELEMENT_ACTIONBAR_CATEGORY);
		Utils.pause(2000);
	}
	/**
	 * Add category for a file in SE
	 * @param categoryTreeName
	 * @param arrayCatePath
	 * @param nameSelectedCategory
	 */
	public void addCategory(String categoryTreeName,String[] arrayCatePath,String nameSelectedCategory){
		info("select category");
		click(ELEMENT_ADD_CATEGORY_POPUP_SELECT_CATEGORY_TAB);
		Utils.pause(2000);
		select(ELEMENT_ADD_CATEGORY_POPUP_MENU, categoryTreeName);
		Utils.pause(2000);
		for (String cateName : arrayCatePath) {
			//click(ELEMENT_ADD_CATEGORY_POPUP_CATEGORY_NAME_LEFT_SIDE.replace("${nameTitle}", cateName));
			clickByJavascript(ELEMENT_ADD_CATEGORY_POPUP_CATEGORY_NAME_LEFT_SIDE.replace("${nameTitle}", cateName), 2);
			Utils.pause(2000);
		}
		click(ELEMENT_ADD_CATEGORY_POPUP_SELECT_CATEGORY_RIGHT_SIDE.replace("${nameCategory}",nameSelectedCategory));
		Utils.pause(3000);
	}

	/**
	 * Close Add Category popup
	 * By QuynhPT
	 * date 16/01/2015
	 */
	public void closeAddCategoryPopup(){
		click(ELEMENT_ADD_CATEGORY_POPUP_CLOSED_BUTTON);
		Utils.pause(2000);
	}
	/**
	 * Go to the path that include content files to create relation
	 * @param path
	 */
	public void goToPathHasFiles(String path) {
		// Open "Select Relation" tab
		click(ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB);
		Utils.pause(500);
		String[] arrayPath = path.split("/");
		for (String arrayElement:  arrayPath) {
			click(ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB_NODE_LEFT_TREE
					.replace("${nameNode}", arrayElement));
		}
	}
	/**
	 * By QuynhPT
	 * Close "Add Relation" popup
	 */
	public void closeAddRelationPopup(){
		click(ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB_CLOSE_BUTTON);
		Utils.pause(2000);
	}

	/**
	 * By QuynhPT
	 * Open Relation tab on SideBar
	 */
	public void goToRelationSideBar(){
		click(ELEMENT_SIDE_BAR_RELATION_ICON);
		Utils.pause(2000);
	}

	/**
	 * Delete Relation
	 * By QuynhPT
	 * @param nameContent
	 */
	public void deleteRelation(String nameContent){
		click(By.xpath(ELEMENT_RELATION_POPUP_RELATION_LIST_DELETE_BUTTON.replace("${nameContent}",nameContent)));
		alert.waitForConfirmation(MESSAGE_DELETE_RELATION);
		alert.acceptAlert();
	}
	/**
	 * Delete a category that is added to the file in SE
	 * By QuynhPT
	 * date 16/01/2015
	 * @param nameCategory
	 */
	public void deleteCategory(String nameCategory){
		click(By.xpath(ELEMENT_ADD_CATEGORY_POPUP_DELETE_CATEGORY.replace("${nameCategory}",nameCategory)));
		alert.acceptAlert();
	}

	/**
	 * Go to Import Node popup
	 * By QuynhPT
	 * date 16/01/2015
	 */
	public void goToImportNode(){
		click(ELEMENT_ACTIONBAR_IMPORT_BUTTON);
		Utils.pause(2000);
	}

	/**
	 * Open Export Node popup
	 * By QuynhPT
	 * date 16/01/2015
	 */
	public void goToExportNode(){
		click( ELEMENT_ACTIONBAR_EXPORT_BUTTON);
		Utils.pause(2000);
	}

	/**
	 * Select a value for behavior
	 * by quynhpt
	 * date 16/01/2015
	 */
	public enum defineValueBehavior{
		CREATE_NEW,REMOVE_EXISTING,REPLACE_EXISTING,THROW_EXEPTION;
	}
	/**
	 * Select a value for behavior
	 * @param value
	 */
	public void selectBehavior(defineValueBehavior value){
		switch(value){
		case CREATE_NEW:
			click(ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_CREATE_NEW);
			break;
		case REMOVE_EXISTING:
			click(ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_REMOVE_EXISTING);
			break;
		case REPLACE_EXISTING:
			click(ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_REPLACE_EXISTING);
			break;
		case THROW_EXEPTION:
			click(ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_THROW_EXCEPTION);
			break;
		}
	}
	/**
	 * Import a Node 
	 * By QuynhPt
	 * date 16/01/2015
	 * @param linkFile
	 * @param behavior
	 * @param version
	 * @param linkVersion
	 */
	public void importNode(String linkFile, String behavior, boolean version,String linkVersion){
		//Verify that the popup is shown
		waitForAndGetElement(ELEMENT_IMPORT_NODE_POPUP_TITLE);

		//upload the file
		WebElement upload = waitForAndGetElement(ELEMENT_IMPORT_NODE_POPUP_UPLOAD_BUTTON, DEFAULT_TIMEOUT,1,2);

		((JavascriptExecutor)driver).executeScript("arguments[0].style.display = 'block';", upload);
		upload.sendKeys(getAbsoluteFilePath(linkFile));
		String[] nameFile = linkFile.split("/");
		waitForAndGetElement(ELEMENT_IMPORT_NODE_POPUP_UPLOAD_FILE_LABEL.replace("${fileName}", nameFile[1]));

		//select a value for behavior
		select(ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR, behavior);

		if(version){
			WebElement uploadVersion = waitForAndGetElement(ELEMENT_IMPORT_NODE_POPUP_VERSION_HISTORY_BUTTON, DEFAULT_TIMEOUT,1,2);
			((JavascriptExecutor)driver).executeScript("arguments[0].style.display = 'block';", uploadVersion);
			uploadVersion.sendKeys(getAbsoluteFilePath(linkVersion));
			String[] namefile = linkVersion.split("/");
			waitForAndGetElement(ELEMENT_IMPORT_NODE_POPUP_UPLOAD_FILE_LABEL.replace("${fileName}", namefile[1]));
		}
		switchToParentWindow();
		click(ELEMENT_IMPORT_MODE_POPUP_IMPORT_BUTTON);

		waitForMessage("Imported successfully.");
		click(button.ELEMENT_OK_BUTTON);
		Utils.pause(2000);

	}

	/**
	 * Export node
	 * By QuynhPT
	 * date 16/01/2015
	 * @param systemView
	 * @param zip
	 */
	public void exportNode(boolean systemView, boolean zip) {
		//Verify that the popup is shown
		waitForAndGetElement(ELEMENT_EXPORT_NODE_POPUP_TITLE);
		//select doc view type
		if (!systemView)
			click(ELEMENT_EXPORT_NODE_POPUP_DOC_VIEW, 2);
		//select zip type
		if (zip)
			click(ELEMENT_EXPORT_NODE_POPUP_ZIP, 2);

		click(ELEMENT_EXPORT_NODE_POPUP_EXPORT_BUTTON);
		waitForElementNotPresent(ELEMENT_EXPORT_NODE_POPUP_EXPORT_BUTTON);
	}
	/**
	 * Open Properties popup
	 * By QuynhPT
	 * date 19/01/2015
	 */
	public void goToProperties(){
		waitForAndGetElement(ELEMENT_ACTIONBAR_PROPERTIES);
		click(ELEMENT_ACTIONBAR_PROPERTIES);
	}

	/**
	 * Go to Properties popup
	 * By QuynhPT
	 * @param property
	 * @param value
	 */
	public void addProperty(String property, String value){
		waitForAndGetElement(ELEMENT_VIEWPROPERTIES_PROPERTIES_TAB);
		click(ELEMENT_VIEWPROPERTIES_ADD_PROPERTY_TAB);
		Utils.pause(1000);
		select(ELEMENT_VIEWPROPERTIES_ADD_PROPERTY_INPUT, property);
		type(ELEMENT_VIEWPROPERTIES_VALUE_INPUT,value,true);
		button.save();

		//Check if a property is added successfully.
		waitForAndGetElement(ELEMENT_VIEWPROPERTIES_PROPERTY.replace("{$property}", property).replace("{$value}", value));
		button.close();
	}
	/**
	 * Open Manage Pulishtation popup
	 * By QuynhPT
	 */
	public void goToManagePublishtation(){
		waitForAndGetElement(ELEMENT_ACTIONBAR_MANAGER_PUBLISHTATION);
		click(ELEMENT_ACTIONBAR_MANAGER_PUBLISHTATION);
		Utils.pause(2000);
	}

	/**
	 * Manage Publication popup
	 * By thunt
	 * Update QuynhPT
	 * @param state
	 * @param date
	 */
	public void managePublication(String state, String...date){
		By bState = By.xpath(ELEMENT_MANAGEPUBLICATION_STATE.replace("{$state}", state));
		String date1 = (String) (date.length > 0 ? date[0]:"");
		String date2 = (String) (date.length > 1 ? date[1]:"");
		//select State
		click(bState);
		waitForAndGetElement(ELEMENT_MANAGEPUBLICATION_CURRENT_SPECIFIC_STATUS.replace("${status}", state));
		if (state.equals("Staged") & (date.length > 0)){
			click(ELEMENT_MANAGEPUBLICATION_SCHEDULE_TAB);
			if((date1!=""&& date1!= null))
				type(ELEMENT_MANAGEPUBLICATION_PUB_FROM_INPUT,date1,true);
			if((date2!="" && date2!= null))
				type(ELEMENT_MANAGEPUBLICATION_PUB_TO_INPUT,date2,true);
			button.save();
			if(date.length > 2){
				waitForMessage(MSG_INVALID_DATE_TIME);
				button.ok();
				button.close();
				return;
			}
			waitForMessage("Your new publication schedule was saved successfully.");
			button.ok();
			click(ELEMENT_MANAGEPUBLICATION_REVISION_TAB);
		}
		waitForAndGetElement(ELEMENT_MANAGEPUBLICATION_HISTORY_TAB);
		waitForAndGetElement(ELEMENT_MANAGEPUBLICATION_REVISION_TAB);

		click(ELEMENT_MANAGEPUBLICATION_HISTORY_TAB);
		waitForAndGetElement(ELEMENT_MANAGEPUBLICATION_HISTORY_ITEM.replace("${state}", state));

		button.close();

	}

	/**
	 * Add a Translation
	 */
	public void addTranslation() {
		info("Click on Add Tranlation button");
		if(waitForAndGetElement(ELEMENT_ACTIONBAR_ADDTRANSLATION, 5000, 0)==null)
			click(ELEMENT_ACTIONBAR_MORE);
		click(ELEMENT_ACTIONBAR_ADDTRANSLATION);
	}

	/**
	 * Vote document
	 */
	public void voteDocument() {
		info("Click to vote document");
		if(waitForAndGetElement(ELEMENT_ACTIONBAR_VOTE, 5000, 0)==null)
			click(ELEMENT_ACTIONBAR_MORE);
		click(ELEMENT_ACTIONBAR_VOTE);
	}

	/**
	 * Add a document translation
	 * @param path
	 * @param content
	 */
	public void addDocumentTranslation(String path, String content) {
		addTranslation();
		waitForAndGetElement(ELEMENT_ADDTRANSLATION_SELECTDOC);
		click(ELEMENT_ADDTRANSLATION_SELECTDOC);
		Utils.pause(2000);
		String[] arrayPath = path.split("/");
		for (String arrayElement : arrayPath) {
			click(ELEMENT_SELECT_DOCUMENT_NODE_FOLDER.replace("${node}", arrayElement));
		}

		if (!content.isEmpty()) {
			waitForAndGetElement(ELEMENT_SELECT_DOCUMENT_NODE_FILE.replace("${content}", content));
			click(ELEMENT_SELECT_DOCUMENT_NODE_FILE.replace("${content}",content));
		}
		click(ELEMENT_SAVE_BTN);
		Utils.pause(2000);
	}


	/**
	 * Go to publication
	 */
	public void goToPublication() {
		click(ELEMENT_ACTIONBAR_MORE);
		click(ELEMENT_ACTIONBAR_PUBLICATION);
	}

	/**
	 * Add a category for a node
	 * @param node
	 * @param category
	 */
	public void addCategoryForNode(String node, String category){
		info("Click on More menu");
		click(ELEMENT_ACTIONBAR_MORE);
		Utils.pause(2000);
		click(ELEMENT_ACTIONBAR_CATEGORY);
		Utils.pause(2000);
		click(ELEMENT_CATEGORY_CHANGE_FORM_SELECT_CATEGORY);
		Utils.pause(2000);
		select(ELEMENT_CATEGORY_SELECT_CATEGORY_TREE,category);
		Utils.pause(2000);
		click(ELEMENT_CATEGORY_ADD_ROOT_NODE);
		Utils.pause(2000);
	}
	/**
	 * Go to the publication status' form
	 */
	public void changeStatusPulication(String status) {
		waitForAndGetElement(ELEMENT_PUBLICATION_STATUS.replace("${status}", status));
		click((ELEMENT_PUBLICATION_STATUS).replace("${status}", status));
		Utils.pause(2000);
	}

	/**
	 * Go to root drive as Site Management, Collaboration tabs... of sidebar
	 */
	public void goToRootDrive(){
		waitForAndGetElement(ELEMENT_SIDE_BAR_MAINTAB);
		click(ELEMENT_SIDE_BAR_MAINTAB);
		Utils.pause(2000);
	}
	/**
	 * Open View Metadata popup
	 */
	public void viewMetadata(){
		info("View Metadata");
		info("Click on More link");
		click(ELEMENT_ACTIONBAR_MORE);
		info("Click on Metadata link");
		click(ELEMENT_ACTIONBAR_METADATA); 
		info("Verify that View metadata popup is shown");
		waitForAndGetElement(ELEMENT_METADATA_POPUP);
		info("Close the popup");
		click(ELEMENT_METADATA_POPUP_CANCEL);
		info("Metadata popup is shown successfully");
	}
	/**
	 * Add/Edit a comment
	 * @param content
	 */
	public void addEditComment(String content, boolean isAdd){
		info("Add/Edit a comment");
		if(isAdd==true){
			info("Click on Add comment on action bar");
			click(ELEMENT_ACTIONBAR_ADDCOMMENT);
		}else {
			info("Click on Edit comment button on action bar");
			click(ELEMENT_SITEEXPLORER_COMMENT_EDIT);
		}
		info("Refresh the page");
		this.driver.navigate().refresh();
		info("Input a content to the frame");
		inputDataToCKEditor(ELEMENT_FILEFORM_BLANK_CONTENT,content);
		info("Switch to parent window");
		switchToParentWindow();
		info("Click on Save button");
		click(ELEMENT_SITEEXPLORER_COMMENT_SAVE);
		info("Finish adding/Editing the Comment");
	}

	/**
	 * Delete a file or node in SE by clicking a checkbox of that file
	 * @param file
	 */
	public void selectAndDeleteByCheckBox(String file){
		waitForAndGetElement(ELEMENT_PERSONAL_DOCUMENT_FILE_CHECKBOX.replace("${file}",file));
		click(ELEMENT_PERSONAL_DOCUMENT_FILE_CHECKBOX.replace("${file}",file));
		click(ELEMENT_ACTIONBAR_DELETE);
		click(ELEMENT_SITEEXPLORER_CONFIRMBOX_DELETE);
		waitForElementNotPresent(ELEMENT_PERSONAL_DOCUMENT_FILE_CHECKBOX.replace("${file}",file));
	}

	/**
	 * Open drive area
	 */
	public void openDrives() {
		Utils.pause(500);
		if (waitForAndGetElement(ELEMENT_SHOW_DRIVES, 3000, 0) != null)
			click(ELEMENT_SHOW_DRIVES);
		else
			click(By.xpath("//*[@title = 'Show Drives']"));
		Utils.pause(1000);
	}

	/**
	 * Go to a drive
	 * @param nameDrive
	 */
	public void selectADrive(String nameDrive){
		info("Go to a folder of a drive");
		waitForAndGetElement(ELEMENT_SELECTED_DRIVE.replace("${nameDrive}", nameDrive));
		click(ELEMENT_SELECTED_DRIVE.replace("${nameDrive}", nameDrive));
		Utils.pause(2000);
	}

	/**
	 * Go to a folder
	 * @param path
	 */
	public void goToAFolder(String path){
		info("Go to a folder of a drive");
		Utils.pause(1000);
		WebElement pathInput = waitForAndGetElement(ELEMENT_SITE_PATH,2000,1,2);
		pathInput.clear();
		pathInput.sendKeys(path);

		Actions action = new Actions(this.driver);
		action.moveToElement(pathInput).sendKeys(Keys.ENTER).build().perform();
		action.moveToElement(pathInput).release();
		Utils.pause(2000);
	}

	/**
	 * Go to a folder in Admin view
	 * @param name
	 */
	public void openAFolder(String name){
		info("Click on the folder");
		click(By.xpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME)
				.replace("${title}",name)));
		Utils.pause(2000);
	}
	
	/**
	 * Open Web view type
	 */
	public void clickWebView() {
		info("Select a view type");
		Utils.pause(3000);
		waitForAndGetElement(ELEMENT_WEB_VIEW);
		//click(ELEMENT_WEB_VIEW);
		clickByJavascript(ELEMENT_WEB_VIEW, 2);
		Utils.pause(3000);
	}

	/**
	 * Open Admin view type
	 */
	public void clickAdminView() {
		info("Select a view type");
		waitForAndGetElement(ELEMENT_ADMIN_VIEW_ICON);
		click(ELEMENT_ADMIN_VIEW_ICON);
		Utils.pause(2000);
	}
	/**
	 * Open List view type
	 */
	public void clickListView() {
		info("Select a view type");
		waitForAndGetElement(ELEMENT_LIST_VIEW_ICON);
		click(ELEMENT_LIST_VIEW_ICON);
		Utils.pause(2000);
	}

	/**
	 * Select File Explorer tree on left panel
	 */
	public void selectFileExplorer(){
		info("Select File Explorer");
		WebElement el = (new WebDriverWait(driver, 30))
				.until(ExpectedConditions.presenceOfElementLocated(ELEMENT_FILE_EXPLORER_ICON));
		el.click();
		Utils.pause(3000);
	}

	/**
	 * Click on Delete button
	 */
	public void clickDeleteButton(){
		info("click on Delete button");
		waitElementAndTryGetElement(ELEMENT_DELETE_ALL_BUTTON);
		WebElement el = waitForAndGetElement(ELEMENT_DELETE_ALL_BUTTON);
		/*WebElement el = (new WebDriverWait(driver, 30))
				  .until(ExpectedConditions.presenceOfElementLocated(ELEMENT_DELETE_ALL_BUTTON));*/
		el.click();
		dialog.deleteInDialog();
		Utils.pause(3000);
	}

	/**
	 * Open a file from right panel
	 * @param filename
	 */
	public void selectAFile(String filename){
		info("Waiting the file:"+filename+" is shown");
		waitForAndGetElement(ELEMENT_FILE_TITLE_RIGHT_PANEL.replace("${fileName}", filename),5000,1);
		info("Select the file");
		click(ELEMENT_FILE_TITLE_RIGHT_PANEL.replace("${fileName}", filename));
		Utils.pause(3000);
		info("The document is opened");
	}

	/**
	 * Select all files in folder under admin view
	 */
	public void selectAllFiles() {
		info("Select all file");
		WebElement el = (new WebDriverWait(driver, 30))
				.until(ExpectedConditions.presenceOfElementLocated(ELEMENT_SITE_EXPLORER_ALL_CHECKBOX));
		if (waitForAndGetElement(ELEMENT_DOCUMENT_LIST_ROW_CONTENT, 5000, 0) != null) {
			info("check on the checkbox");
			//el.click();
			clickByJavascript(el, 2);
			Utils.pause(3000);
			info("Click on Delete button");
			clickDeleteButton();
		}

	}

	/**
	 * Select a new content in list
	 * @param nameContent
	 */
	public void selectAContentType(String nameContent){
		info("Select a content");
		WebElement el = (new WebDriverWait(driver, 30))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ELEMENT_SITE_EXPLORER_CONTENT_NAME.replace("${nameContent}", nameContent))));
		el.click();
		Utils.pause(3000);
	}
	/**
	 * Delete all files in a folder under Admin view
	 */
	public void deleteAllFiles(){
		info("Select Admin view type");
		clickAdminView();
		info("Select All checkbox");
		selectAllFiles();
	}
	/**
<<<<<<< HEAD
	 * Check display of drive
	 * @param drive
	 * @param isDisplay
	 */
	public void checkDisplayOfDrive(String drive,boolean isDisplay){
		info("check display of drive:"+drive);
		click(ELEMENT_ACTIONBAR_SHOWDRIVES,0,true);
		if(isDisplay)
			waitForAndGetElement(ELEMENT_ACTIONBAR_SELECTED_DRIVE.replace("${drive}",drive));
		else
			waitForElementNotPresent(ELEMENT_ACTIONBAR_SELECTED_DRIVE.replace("${drive}",drive));
	}
	/**
	 * Check action in view
	 * @param view
	 * @param action
	 */
	public void checkActionInView(String view,String[] actions){
		info("check action:"+action+" in view"+view);
		waitForAndGetElement(ELEMENT_ITEM_VIEW.replace("$view", view));
		click(ELEMENT_ITEM_VIEW.replace("$view", view),0,true);
		for (String action : actions) {
			waitForAndGetElement(ELEMENT_ACTIONBAR_ACTION.replace("$action", action));
		}
		
	}
	/**
	 * Check query in saved query
	 * @param queries
	 * 				list of queries
	 */
	public void checkQueryInSavedQuery(String...queries){
		info("check display of query");
		goToAdvancedSearch();
		click(ELEMENT_SITEXPLORER_ADVANCEDSEARCH_SAVEDQUERYTAB,0,true);
		for (String query : queries) {
			waitForAndGetElement(ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_EXECUTEQUERY.replace("${name}",query));
		}
	}
	/**
	 * Check user selector of Documents/Permission
	 * @param user
	 * @param isPresent
	 */
	public void checkUserSelectorECM(String user,boolean isPresent){
		if (waitForAndGetElement(ELEMENT_ACTIONBAR_PERMISSION, DEFAULT_TIMEOUT, 0) == null) {
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
		}
		click(ELEMENT_ACTIONBAR_PERMISSION,0,true);
		Utils.pause(1000);
		info("check user selector");
		click(plfPerm.ELEMENT_SELECT_USER_ICON1,0,true);
		plfPerm.checkUserSelector(user, isPresent);
	}
	/**
	 * Check personal file
	 * @param file
	 * @param isPresent
	 */
	public void checkFileInPersonal(String file,boolean isPresent){
		info("check file in personal document");
		waitForAndGetElement(ELEMENT_FOLDERSELECTOR_PATH.replace("${path}", file),3000,1);
	}
	/**
	 * Check SE file
	 * @param file
	 * @param isPresent
	 */
	public void checkFileInSE(String file,boolean isPresent){
		info("check file in SE");
		waitForAndGetElement((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", file),3000,1);
	}
	/**
	 * Watch a document
	 */
	public void watchDocument(){
		info("watch a document");
		click(ELEMENT_ACTIONBAR_WATCH,0,true);
		Utils.pause(500);
		click(ELEMENT_ACTIONBAR_WATCH_RADIO,0,true);
		click(ELEMENT_ACTIONBAR_WATCH_BUTTON,0,true);
		waitForAndGetElement(ELEMENT_ACTIONBAR_WATCH_NOTICE);
		Utils.pause(2000);
	}
	
	/**
	 * Share document
	 */
	public void shareDocument() {
		info("Click to share document");
		if(waitForAndGetElement(ELEMENT_ACTIONBAR_SHARE, 5000, 0)==null)
			click(ELEMENT_ACTIONBAR_MORE);
		click(ELEMENT_ACTIONBAR_SHARE);
	}
	
	/**
	 * Upload file/files to a specific folder
	 * @param folderName
	 * @param uploadFiles
	 * @throws Exception
	 */
	public void uploadFileToFolder(String folderName, ArrayList<String> uploadFiles) throws Exception{
		info("Upload file to folder");
		click(ELEMENT_DOCUMENT_SELECTED_FOLDER.replace("${folderName}",folderName));
		
		for(String fileName:uploadFiles){			
			uploadFile("TestData/"+fileName);
		}		
	}
	
	/**
	 * Share one document to many spaces
	 * @param fileName
	 * @param spaceList
	 * @param comment
	 */
	public void shareDocumentToManySpaces(String fileName, ArrayList<String> spaceList, String comment){
		info("Share document to space");
		for(String spaceElement:spaceList){
			//Click space list
			click(ELEMENT_SPACE_LIST);
			//Select a space
			click(ELEMENT_SELECTED_SPACE.replace("${spaceName}", spaceElement));	
		}
		//Comment
		if(comment!= null && !comment.isEmpty()){
			type(ELEMENT_SHARE_DOCUMENT_COMMENT, comment, false);
		}
		
		//Click Share button
		click(ELEMENT_SHARE_DOCUMENT_ACTION_BUTTON.replace("${name}", "Share"));
	}	
	
	/**
	 * Activity about shared document is displayed in Intranet Activity Stream & Space Activity Stream
	 * @param shareOwner
	 * @param fileName
	 * @param spaceName
	 */
	public void checkDisplayOfSharedDocument(String shareOwner, String fileName, String spaceName){
		SocialLocator socLocator  = new SocialLocator();
		//Check Shared content in Intranet AS
		waitForAndGetElement(ELEMENT_SHARE_DOCUMENT_CONTENT
							.replace("${author}", shareOwner)
							.replace("${spaceName}", spaceName));
		
		//Open space displayed in shared document
		click(ELEMENT_SHARE_DOCUMENT_CONTENT.replace("${author}", shareOwner).replace("${spaceName}", spaceName));
		waitForAndGetElement(socLocator.ELEMENT_SPACE_NAME.replace("${name}", spaceName));
		//Check Shared content in Space AS
		waitForAndGetElement(ELEMENT_SHARE_DOCUMENT_CONTENT_IN_SPACE.replace("${author}", shareOwner));		
	} 	
	
	/**
	 * Symlink of shared file is displayed in shared folder
	 * @param fileName
	 * @param spaceName
	 */
	public void checkSharedFileSymlink( String fileName, String spaceName){
		SpaceManagement spaceManage = new SpaceManagement(driver);
		//Go to Documents of Space
		spaceManage.goToDocumentTab();
		waitForAndGetElement(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
		//Open shared folder
		doubleClickOnElement(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
		//Find the shared file
		waitForAndGetElement(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK
												.replace("${spaceName}", spaceName)
												.replace("${fileName}", fileName));				
	}	
	
	/**
	 * Create a space with 2 users
	 * @param spaceName
	 * @param user1
	 * @param user2
	 * @param user2FullName
	 * @param password
	 */
	public void initSpaceWithUsers(String spaceName, String user1, String user2,String user2FullName, String password){
		ManageLogInOut manageLoginOut = new ManageLogInOut(driver);
		HomePagePlatform homepage = new HomePagePlatform(driver);
		SpaceManagement spaceManage = new SpaceManagement(driver);
		SpaceHomePage spaceHome = new SpaceHomePage(driver);
		SpaceSettingManagement spaceSetting = new SpaceSettingManagement(driver);
		
		info("User A login");
		manageLoginOut.signIn(user1, password);
		Utils.pause(3000);
		
		info("User A creates a space");
		homepage.goToAllSpace();
		Utils.pause(3000);
		spaceManage.goToCreateSpace();
		spaceManage.addNewSpaceSimple(spaceName, "");
		
		info("User A invites UserB to the space");
		homepage.goToSpecificSpace(spaceName);
		spaceHome.goToSpaceSettingTab();
		spaceSetting.goToMemberTab();
		spaceSetting.inviteUser(user2,true,user2FullName);
		
		info("User B login");
		manageLoginOut.signOut();
		manageLoginOut.signIn(user2, password);
		Utils.pause(3000);
		
		info("User B accepted to join the space");
		homepage.goToAllSpace();
		Utils.pause(3000);
		spaceManage.acceptAInvitation(spaceName);
		
		manageLoginOut.signOut();
	}
	
	/**
	 * Upload and Share a document to a space
	 * @param fileName
	 * @param spaceName
	 * @param comment
	 */
	public void uploadAndShareDocumentToSpace(String fileName, String spaceName, String comment){
		NavigationToolbar navTool = new NavigationToolbar(driver);
		
		info("Share document to space");
		navTool.goToSiteExplorer();
		Utils.pause(3000);
		
		uploadFile("TestData/"+fileName);
		Utils.pause(3000);
		waitForAndGetElement(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", fileName));		
		//Share file to space
		click(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", fileName));		
		shareDocument();
		//Click space list
		click(ELEMENT_SPACE_LIST);
		//Select a space
		click(ELEMENT_SELECTED_SPACE.replace("${spaceName}", spaceName));	
		
		//Comment
		if(comment!= null && !comment.isEmpty()){
			type(ELEMENT_SHARE_DOCUMENT_COMMENT, comment, false);
		}
		
		//Click Share button
		click(ELEMENT_SHARE_DOCUMENT_ACTION_BUTTON.replace("${name}", "Share"));
	}
	
	/**
	 * 
	 * Date: Oct 7, 2015
	 * Delete symlink of shared document in space documents
	 */
	public void deleteSymlink(String spaceName, String fileName){
		HomePagePlatform homepage = new HomePagePlatform(driver);
		SpaceManagement spaceManage = new SpaceManagement(driver);
		
		homepage.goToSpecificSpace(spaceName);
		
		spaceManage.goToDocumentTab();
		waitForAndGetElement(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
		//Open shared folder
		doubleClickOnElement(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
		//Find the shared file
		waitForAndGetElement(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK
																.replace("${spaceName}", spaceName)
																.replace("${fileName}", fileName));
		//Delete symlink
		rightClickOnElement(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK
																.replace("${spaceName}", spaceName)
																.replace("${fileName}", fileName));
		waitForAndGetElement(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE);
		click(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE);
		//confirm
		waitForAndGetElement(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE_OPTION.replace("${action}", "Delete"));
		click(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE_OPTION.replace("${action}", "Delete"));		
		waitForElementNotPresent(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK
																.replace("${spaceName}", spaceName)
																.replace("${fileName}", fileName));				
	}
	
	
	public void checkShareActivityAfterDeleted(String spaceName, boolean verifyInSpace){	
		HomePagePlatform homepage = new HomePagePlatform(driver);
		SpaceManagement spaceManage = new SpaceManagement(driver);
		
		waitForElementNotPresent(ELEMENT_SHARE_DOCUMENT_CONTENT
														.replace("${author}", DATA_NAME_USER1)
														.replace("${spaceName}", spaceName));
		if(verifyInSpace){
			homepage.goToSpecificSpace(spaceName);
			spaceManage.goToActivityStreamTab();
			waitForElementNotPresent(ELEMENT_SHARE_DOCUMENT_CONTENT_IN_SPACE.replace("${author}", DATA_NAME_USER1));
		}	
	}	
	
	/**
	 * 
	 * Date: Oct 8, 2015
	 * Documents -> Icons button
	 */
	public void clickIconView() {
		info("Select a view type");
		waitForAndGetElement(ELEMENT_ADDRESS_BAR_ICON_VIEW);
		click(ELEMENT_ADDRESS_BAR_ICON_VIEW);
		Utils.pause(2000);
	}
	
	
	/**
	 * The symlink does not exist in Shared folder
	 * Date: Oct 21, 2015
	 */
	public void checkSharedFileSymlinkAfterDeleted( String fileName, String spaceName){
		SpaceManagement spaceManage = new SpaceManagement(driver);
		//Go to Documents of Space
		spaceManage.goToDocumentTab();
		waitForAndGetElement(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
		//Open shared folder
		doubleClickOnElement(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
		//Find the shared file
		waitForElementNotPresent(ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK
												.replace("${spaceName}", spaceName)
												.replace("${fileName}", fileName));				
	}	


	/**
	 * Share document with access rights: Can view/Can edit
	 * Date: Oct 22, 2015
	 */
	public void shareDocumentToSpaceWithAccessRight(String spaceName, String accessRight, String comment){
		shareDocument();
		click(ELEMENT_DOCUMENT_SHARE_ACCESS_OPTION.replace("${option}", accessRight));
		//Click space list
		click(ELEMENT_SPACE_LIST);
		//Select a space
		click(ELEMENT_SELECTED_SPACE.replace("${spaceName}", spaceName));	
		
		//Comment
		if(comment!= null && !comment.isEmpty()){
			type(ELEMENT_SHARE_DOCUMENT_COMMENT, comment, false);
		}		
		//Click Share button
		click(ELEMENT_SHARE_DOCUMENT_ACTION_BUTTON.replace("${name}", "Share"));		
	}

	/**
	 * Go to drive -> folder
	 * Date: Oct 27, 2015
	 */
	public void goToPathOfDrive(String path, String drive){
		info("Go to selected Drive");
		waitForAndGetElement(ELEMENT_ACTIONBAR_SHOWDRIVES);
		click(ELEMENT_ACTIONBAR_SHOWDRIVES);
		waitForAndGetElement(ELEMENT_ACTIONBAR_SELECTED_DRIVE.replace("${drive}",drive));
		click(ELEMENT_ACTIONBAR_SELECTED_DRIVE.replace("${drive}",drive));
		info("Go to folder");
		String[] arrayPath = path.split("/");
		for (String arrayElement : arrayPath){
			selectNode(arrayElement);
		}
	}	

	 /** 
	  * Delete all files in a folder under Admin view
	 * @param title
	 */
	public void verifyContentCreatedSuccessfully(String title){
		info ("Verify Content was created successfully");
		Utils.pause(2000);
		waitForAndGetElement(By.xpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", title)), DEFAULT_TIMEOUT, 1);
		info("Content was created successfully");
	}
}
