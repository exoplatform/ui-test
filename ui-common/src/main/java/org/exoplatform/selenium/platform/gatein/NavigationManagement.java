package org.exoplatform.selenium.platform.gatein;

import static org.exoplatform.selenium.TestLogger.info;


import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/**
 * Path: Navigation Management popup
 */
public class NavigationManagement extends PlatformBase {
	
	public final By ELEMENT_MANAGESITES_TITLE=By.xpath(".//*[@id='UIPortalNavigationPortlet']//h5[text()='Manage Sites']");
	public final String ELEMENT_MANAGESITES_EDIT_NAVIGATION_ICON=".//*[@class='managementBlock']//div[text()='${site}']/../..//*[@class='uiIconNavigation uiIconLightGray']";

	public final String ELEMENT_MANAGESITES_EDIT_LAYOUT_ICON=".//*[@class='managementBlock']//div[text()='${site}']/../..//*[contains(@class,'uiIconEditLayout')]";
	public final String ELEMENT_MANAGESITES_EDIT_CONFIG_ICON=".//*[@class='managementBlock']//div[text()='${site}']/../..//*[contains(@class,'uiIconEditPortalConfig')]";
	
	public final By ELEMENT_MANAGESITES_ADD_NEW_BTN=By.cssSelector("#UISiteManagement .btn");
	public final By ELEMENT_MANAGESITES_EDIT_LAYOUT_SITE_CONFIG_BTN = By.cssSelector(".PageProfileIcon");
	public final String ELEMENT_MANAGESITES_PORTAL_LABEL =".//*[contains(@class,'siteName')][contains(text(),'${portal}')]/..//*[contains(@class,'siteLabel')][contains(text(),'${label}')]";
	public final String ELEMENT_MANAGESITES_PORTAL_DESC =".//*[contains(@class,'siteName')][contains(text(),'${portal}')]/..//*[contains(@class,'siteDescription')][contains(text(),'${desc}')]";

	public final String ELEMENT_ADD_NAVIGATION_BUTTON = "//*[contains(text(),'Add Navigation')]";

	//Navigation Management popup
	public final By ELEMENT_NAVIGATION_MANAGEMENT_POPUP_TITLE=By.xpath(".//*[@class='PopupTitle popupTitle'][text()='Navigation Management']");
	public final String ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME=".//*[@title='${name}']";
	public final By ELEMENT_NAVIGATION_MANAGEMENT_SAVE = By.xpath(".//*[text()='Save']");
	public final String ELEMENT_NAVIGATION_SPECIFIC_NODE ="//*[@id='UINavigationNodeSelector']//*[@class='uiIconFileMini uiIconLightGray' ]/../..//*[contains(text(),'${name}')]";
	public final String ELEMENT_NAVIGATION_SUB_NODE_CHECK = "//*[@id='UINavigationNodeSelector']//*[@class='uiIconNode collapseIcon' and contains(text(),'{$node}')]";
	public final String ELEMENT_NAVIGATION_PARENT_CHILD_NODE =".//*[contains(@class,'uiIconNode')][@title='${parent}']/..//*[contains(@class,'childrenContainer')]//*[contains(@title,'${child}')]";
	public final String ELEMENT_NAVIGATION_NUMBER_CHILD_NODES=".//*[contains(@class,'uiIconNode')][@title='${parent}']/..//*[contains(@class,'childrenContainer')][count(*)=${numberChild}]";
	public final String ELEMENT_NAVIGATION_PARENT_NODE =".//*[contains(@class,'treeContainer')]//*[contains(@class,'nodeGroup')]//*[contains(@title,'${parent}')]";
	public final String ELEMENT_NAVIGATION_PREVIOUS_NODE=".//*[contains(@class,'node')]/*[contains(@title,'${currentNode}')]/..//preceding-sibling::*[contains(@class,'node')]/*[contains(@title,'${previousNode}')]";
	public final String ELEMENT_NAVIGATION_NEXT_NODE=".//*[contains(@class,'node')]/*[contains(@title,'${currentNode}')]/..//following-sibling::*[contains(@class,'node')]/*[contains(@title,'${nextNode}')]";
	//Add new portal popup
	public final By ELEMENT_ADD_NEW_PORTAL_POPUP_NAME=By.cssSelector("#name");
	public final By ELEMENT_ADD_NEW_PORTAL_POPUP_LABEL=By.cssSelector("#label");
	public final By ELEMENT_ADD_NEW_PORTAL_POPUP_DESC=By.cssSelector("#description");
	public final By ELEMENT_ADD_NEW_PORTAL_POPUP_LOCALE=By.cssSelector("#PortalSetting-tab .selectbox[name~='locale']");
	public final By ELEMENT_ADD_NEW_PORTAL_POPUP_SITE=By.cssSelector("#PortalSetting-tab .selectbox[name~='skin']");
	public final By ELEMENT_ADD_NEW_PORTAL_POPUP_SAVE_BTN=By.xpath(".//*[@id='UIPortalForm']//button[text()='Save']");
	public final By ELEMENT_ADD_NEW_PORTAL_POPUP_PUBLIC_PERMISSION=By.cssSelector("#publicMode");
	public final By ELEMENT_ADD_NEW_PORTAL_POPUP_PERMISSION_TAB = By.xpath(".//*[contains(@data-target,'#PermissionSetting-tab')]");
	public final By ELEMENT_ADD_NEW_PORTAL_POPUP_EDIT_PERMISSITION_SETTINGS= By.xpath(".//*[contains(text(),'Edit Permission Settings')]");
	public final By ELEMENT_ADD_NEW_PORTAL_POPUP_SELECT_PERMISSION_BTN= By.xpath(".//*[contains(text(),'Select Permission')]");
	
	//Permission selector
	public final String ELEMENT_PERMISSION_SELECTOR_POPUP_GROUP = ".//*[contains(@class,'uiIconNode')][contains(@title,'${group}')]";
	public final String ELEMENT_PERMISSION_SELECTOR_POPUP_MEMEBRSHIP = ".//*[@id='PermissionSelector']//*[contains(@title,'${member}')]";

	//Contextmenu
	public final By ELEMENT_MANAGESITES_CONTEXTMENU_DELETE_ICON= By.xpath(".//*[@id='NavigationNodePopupMenu']//*[@class='uiIconDeleteNode']");
	public final By ELEMENT_MANAGESITES_CONTEXTMENU_EDIT_ICON= By.cssSelector(".ContainerConfigOptions .uiIconEditSelectedNode");
	public final By ELEMENT_MANAGESITES_CONTEXTMENU_EDIT_NODE_PAGE_ICON= By.cssSelector(".ContainerConfigOptions .uiIconEditPageNode");
	public final By ELEMENT_MANAGESITES_CONTEXTMENU_COPY_ICON= By.cssSelector(".ContainerConfigOptions .uiIconCopyNode");
	public final By ELEMENT_MANAGESITES_CONTEXTMENU_PASTE_ICON= By.cssSelector(".ContainerConfigOptions .uiIconPasteNode");
	public final By ELEMENT_MANAGESITES_CONTEXTMENU_CUT_ICON= By.cssSelector(".ContainerConfigOptions .uiIconCutNode");
	public final By ELEMENT_MANAGESITES_CONTEXTMENU_CLONE_ICON= By.cssSelector(".ContainerConfigOptions .uiIconCloneNode");
	public final By ELEMENT_MANAGESITES_CONTEXTMENU_MOVE_UP_ICON= By.cssSelector(".ContainerConfigOptions .uiIconMoveUp");
	public final By ELEMENT_MANAGESITES_CONTEXTMENU_MOVE_DOWN_ICON= By.cssSelector(".ContainerConfigOptions .uiIconMoveDown");
	
	// new node
	public final By ELEMENT_UP_LEVEL_PATH_NODE = By.xpath("//*[@id='UINavigationNodeSelector']//*[@class='uiIconUpLevel uiIconLightGray']");
	public final By ELEMENT_ADD_NODE = By.xpath("//*[@id='UINavigationManagement']/..//*[contains(text(),'Add Node')]");
	public final By ELEMENT_SAVE_NODE = By.xpath("//*[@id='UINavigationManagement']/..//*[contains(text(),'Save')]");
	public final By ELEMENT_NODE_NAME = By.id("name");
	public final By ELEMENT_NODE_PAGE_SELECTOR_TAB=By.cssSelector("a[data-target='#UIPageSelector-tab']");
	public final By ELEMENT_NODE_PAGE_SELECTOR_SELECT_PAGES_BTN = By.cssSelector("#SelectPage .uiIconSelectPage");
	
	//new node-->Page Node Setting
	public final By ELEMENT_NODE_SETTING_SWITCH_MODE=By.cssSelector("#switchmode");
	public final By ELEMENT_NODE_SETTING_LANGUAGE_BOX=By.cssSelector(".selectbox");
	public final By ELEMENT_NODE_SETTING_LABEL_FIELD_1=By.cssSelector("#i18nizedLabel");
	public final By ELEMENT_NODE_SETTING_LABEL_FIELD_2=By.cssSelector("#label");
	public final By ELEMENT_NODE_SETTING_VISIBLE=By.cssSelector("#visible");
	public final By ELEMENT_NODE_SETTING_PUBLISH_DATE_TIME=By.cssSelector("#showPublicationDate");
	public final By ELEMENT_NODE_SETTING_VISIBLE_CHECKED=By.cssSelector("#visible[checked='']");
	public final By ELEMENT_NODE_SETTING_SWITCH_MODE_CHECKED=By.cssSelector("#switchmode[checked='']");
	
	//new node-->Page selector
	public final By ELEMENT_NODE_PAGE_SELECTOR_PAGE_NAME=By.cssSelector("#pageName");
	public final By ELEMENT_NODE_PAGE_SELECTOR_PAGE_TITLE=By.cssSelector("#pageTitle");
	public final By ELEMENT_NODE_PAGE_SELECTOR_CREATE_PAGE_BTN=By.xpath(".//*[@id='UIPageSelector']//button[1]");
	public final By ELEMENT_NODE_PAGE_SELECTOR_SELECT_PAGE_BTN=By.xpath(".//*[@id='UIPageSelector']//button[2]");
	public final By ELEMENT_NODE_PAGE_SELECTOR_CLEAR_PAGE_BTN=By.xpath(".//*[@id='UIPageSelector']//button[3]");
	
	public final By ELEMENT_NODE_PAGE_SELECTOR_TITLE_FIELD=By.cssSelector("#UIPageSearchForm #pageTitle");
	public final By ELEMENT_NODE_PAGE_SELECTOR_SITES_NAME_FIELD=By.cssSelector("#UIPageSearchForm #siteName");
	public final By ELEMENT_NODE_PAGE_SELECTOR_TYPE_DROPBOX=By.cssSelector("#UIPageSearchForm .selectbox");
	public final String ELEMENT_NODE_PAGE_SELECTOR_SELECT_TYPE=".//*[@name='searchOption']//*[contains(@value,'${type}')]";
	public final By ELEMENT_NODE_PAGE_SELECTOR_SEARCH_BUTTON = By.cssSelector("#UIPageSearchForm .uiIconSearch");
	
	
	ManageAlert alert;
	public NavigationManagement(WebDriver dr){
		driver = dr;
		alert = new ManageAlert(dr);
	} 
    
	/**
	 * Open Navigation Management popup
	 * @param site as acme or intranet or a group
	 */
	public void goToEditNavigation(String site) {
		waitForAndGetElement(ELEMENT_MANAGESITES_EDIT_NAVIGATION_ICON.replace(
				"${site}", site), 3000, 0);
		click(ELEMENT_MANAGESITES_EDIT_NAVIGATION_ICON.replace("${site}", site));
		waitForAndGetElement(ELEMENT_NAVIGATION_MANAGEMENT_POPUP_TITLE, 3000, 0);
	}
	/**
	 * Edit layout of a portal
	 * @param site
	 */
	public void goToEditLayout(String site){
		info("Click on Edit layout button");
		click(ELEMENT_MANAGESITES_EDIT_LAYOUT_ICON.replace("${site}",site));
		Utils.pause(3000);
	}

	/**
	 * change config of a portal
	 * @param site
	 */
	public void changeConfig(String site){
		goToEditLayout(site);
		info("Click on site's config button");
		click(ELEMENT_MANAGESITES_EDIT_LAYOUT_SITE_CONFIG_BTN);
		Utils.pause(3000);
	}
	/**
	 * Go to Edit site configuration 
	 * @param site
	 */
	public void goToEditSiteConfig(String site){
		info("Click on Edit Site Configuration button");
		click(ELEMENT_MANAGESITES_EDIT_CONFIG_ICON.replace("${site}",site));
		Utils.pause(2000);
	}
	/**
	 * list all sublinks in Contextmenu
	 *
	 */
	public enum specifiContextMenu{
		ADD_NEW_NODE,EDIT_NODE_PAGE,EDIT_THIS_NODE,COPY_NODE,CLONE_NODE,CUT_NODE,PASTE_NODE,DELETE_NODE,
		MOVE_UP,MOVE_DOWN;
	}
	
	/**
	 * Select a item in ContextMenu
	 * @param link
	 */
	public void selectItem(specifiContextMenu link){
		switch(link){
		case ADD_NEW_NODE:
			break;
		case EDIT_NODE_PAGE:
			info("Click on Edit node page");
			click( ELEMENT_MANAGESITES_CONTEXTMENU_EDIT_NODE_PAGE_ICON);
			Utils.pause(2000);
			break;
		case EDIT_THIS_NODE:
			info("Click on Edit icon");
			click(ELEMENT_MANAGESITES_CONTEXTMENU_EDIT_ICON);
			Utils.pause(2000);
			break;
		case COPY_NODE:
			info("Click on Copy node");
			click(ELEMENT_MANAGESITES_CONTEXTMENU_COPY_ICON);
			Utils.pause(2000);
			break;
		case CLONE_NODE:
			info("Click on Clone node");
			click(ELEMENT_MANAGESITES_CONTEXTMENU_CLONE_ICON);
			Utils.pause(2000);
			break;
		case CUT_NODE:
			info("Click on Cut node");
			click(ELEMENT_MANAGESITES_CONTEXTMENU_CUT_ICON);
			Utils.pause(2000);
			break;
		case PASTE_NODE:
			info("Click on Paste node");
			click(ELEMENT_MANAGESITES_CONTEXTMENU_PASTE_ICON);
			Utils.pause(2000);
			break;
		case DELETE_NODE:
			info("Click on Delete node");
			click(ELEMENT_MANAGESITES_CONTEXTMENU_DELETE_ICON);
			alert.acceptAlert();
			break;
		case MOVE_UP:
			info("Click on Paste node");
			click(ELEMENT_MANAGESITES_CONTEXTMENU_MOVE_UP_ICON);
			Utils.pause(2000);
			break;
		case MOVE_DOWN:
			info("Click on Paste node");
			click(ELEMENT_MANAGESITES_CONTEXTMENU_MOVE_DOWN_ICON);
			Utils.pause(2000);
			break;
		}
	}
	/**
	 *  Add a node
	 * @param title
	 * @param subTitle
	 */
	public void addNode(String title, String subTitle) {
		if (subTitle.isEmpty()) {
			info("Add parent node");
			click(ELEMENT_UP_LEVEL_PATH_NODE);
			click(ELEMENT_ADD_NODE);
			type(ELEMENT_NODE_NAME, title, true);
		} else {
			info("Add sub node");
			waitForAndGetElement(ELEMENT_NAVIGATION_SPECIFIC_NODE.replace(
					"${name}", title));
			click(ELEMENT_NAVIGATION_SPECIFIC_NODE.replace("${name}", title));
			click(ELEMENT_ADD_NODE);
			type(ELEMENT_NODE_NAME, subTitle, true);
		}
	}
	
	/**
	 * Delete a node
	 * @param title
	 */
	public void deleteNode(String title) {
		info("Delete a node");
		info("Right click on the node");
		rightClickOnElement(ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME.replace(
				"${name}", title));
		info("Select Delete link");
		selectItem(specifiContextMenu.DELETE_NODE);
		info("Verify that the node is deleted");
		waitForElementNotPresent(ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME
				.replace("${name}", title));
		waitForAndGetElement(ELEMENT_NAVIGATION_MANAGEMENT_SAVE);
		info("Click on Save button");
		click(ELEMENT_NAVIGATION_MANAGEMENT_SAVE);
		Utils.pause(2000);
	}
	/**
	 * Edit Node page
	 * @param name
	 */
	public void editNodePage(String name){
		info("Edit a node page");
		info("Right click on the node");
		rightClickOnElement(ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME.replace(
				"${name}", name));
		info("Select Edit link");
		selectItem(specifiContextMenu.EDIT_NODE_PAGE);
	}
	/**
	 * Edit a Node
	 * @param oldName
	 * @param newName
	 */
	public void editThisNode(String name){
		info("Edit a node");
		info("Right click on the node");
		rightClickOnElement(ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME.replace(
				"${name}", name));
		info("Select Edit link");
		selectItem(specifiContextMenu.EDIT_THIS_NODE);
	}
	/**
	 * Copy a node
	 * @param name
	 */
	public void copyNode(String name){
		info("Copy a node");
		info("Right click on the node");
		rightClickOnElement(ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME.replace(
				"${name}", name));
		info("Select Edit link");
		selectItem(specifiContextMenu.COPY_NODE);
	}
	
	/**
	 * Clone a node
	 * @param name
	 */
	public void cloneNode(String name){
		info("Clone a node");
		info("Right click on the node");
		rightClickOnElement(ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME.replace(
				"${name}", name));
		info("Select Edit link");
		selectItem(specifiContextMenu.CLONE_NODE);
	}
	/**
	 * Cut a node
	 * @param name
	 */
	public void cutNode(String name){
		info("Cut a node");
		info("Right click on the node");
		rightClickOnElement(ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME.replace(
				"${name}", name));
		info("Select Edit link");
		selectItem(specifiContextMenu.CUT_NODE);
	}
	
	/**
	 * Paste a node
	 * @param name
	 */
	public void pasteNode(String name){
		info("Paste a node");
		info("Right click on the node");
		rightClickOnElement(ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME.replace(
				"${name}", name));
		info("Select Edit link");
		selectItem(specifiContextMenu.PASTE_NODE);
	}
	
	/**
	 * Move up a node
	 * @param name
	 */
	public void moveUpNode(String name){
		info("Move up a node");
		info("Right click on the node");
		rightClickOnElement(ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME.replace(
				"${name}", name));
		info("Select Edit link");
		selectItem(specifiContextMenu.MOVE_UP);
	}
	
	/**
	 * Move down a node
	 * @param name
	 */
	public void moveDownNode(String name){
		info("Move down a node");
		info("Right click on the node");
		rightClickOnElement(ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME.replace(
				"${name}", name));
		info("Select Edit link");
		selectItem(specifiContextMenu.MOVE_DOWN);
	}
	
	/**
	 * Save all changes of a node
	 */
	public void saveNode(){
		info("Save all changes of a node");
		waitForAndGetElement(ELEMENT_NAVIGATION_MANAGEMENT_SAVE);
		info("Click on Save button");
		click(ELEMENT_NAVIGATION_MANAGEMENT_SAVE);
		Utils.pause(1000);
		closeNavigationManagementPopup();
	}
	/**
	 * Save and close navigation mangement popup
	 */
	public void closeNavigationManagementPopup(){
		click(ELEMENT_SAVE_NODE);
		Utils.pause(2000);
	}
	/**
	 * Input information for Page Node Setting tab
	 * @param language
	 * @param isLabelMode
	 * @param label
	 * @param isVisibale
	 * @param isPublishDateTime
	 */
	public void inputInfoNodeSetting(boolean isNotLabelMode, String language,
			String label, boolean isVisibale, boolean isPublishDateTime) {
		if (isNotLabelMode == true) {
			info("Uncheck label mode");
			uncheck(ELEMENT_NODE_SETTING_SWITCH_MODE, 2);
			if (!label.isEmpty()) {
				info("Input a label");
				type(ELEMENT_NODE_SETTING_LABEL_FIELD_2, label, true);
			}
		} else {
			if (!language.isEmpty()) {
				info("Click on Language box");
				select(ELEMENT_NODE_SETTING_LANGUAGE_BOX, language);
			}
			if (!label.isEmpty()) {
				info("Input a label");
				type(ELEMENT_NODE_SETTING_LABEL_FIELD_1, label, true);
			}
		}

		if (isVisibale == true) {
			info("Uncheck visible box");
			uncheck(ELEMENT_NODE_SETTING_VISIBLE, 2);
		} else {
			if (isPublishDateTime == true) {
				info("Check publish date and time box");
				check(ELEMENT_NODE_SETTING_PUBLISH_DATE_TIME, 2);
			}
		}
	}
	/**
	 * Input information for Page Selector tab
	 * @param namePage
	 * @param titlePage
	 * @param isCreatePage
	 * @param isSelectPage
	 * @param isCleanPage
	 */
	public void inputInfoPageSelector(String namePage,String titlePage,boolean isCreatePage,
			boolean isSelectPage,boolean isCleanPage){
		info("Click on Page Selector tab");
		click(ELEMENT_NODE_PAGE_SELECTOR_TAB);
		if(!namePage.isEmpty()){
			info("Input a new Page's name");
			type(ELEMENT_NODE_PAGE_SELECTOR_PAGE_NAME,namePage,true);
		}
		if(!titlePage.isEmpty()){
			info("Input a new Page's title");
			type(ELEMENT_NODE_PAGE_SELECTOR_PAGE_TITLE,titlePage,true);
			
		}
		if(isCreatePage==true){
			info("click on Create new page button");
			click(ELEMENT_NODE_PAGE_SELECTOR_CREATE_PAGE_BTN);
		}
		
		if(isSelectPage==true){
			info("click on Select a page button");
			click(ELEMENT_NODE_PAGE_SELECTOR_SELECT_PAGE_BTN);
		}
		if(isCleanPage==true){
			info("click on Clear button");
			click(ELEMENT_NODE_PAGE_SELECTOR_CLEAR_PAGE_BTN);
		}
		
	}
	/**
	 * Input information for Icon tab
	 * @param isSizeIcon
	 * @param typeIcon
	 * @param nameIcon
	 * @param isGetDefault
	 */
	public void inputInfoIcon(boolean isSizeIcon,String typeIcon,String nameIcon,boolean isGetDefault){
		
	}
	
	/**
	 * Search a page
	 * @param titlePage
	 * @param siteName
	 * @param type
	 */
	public void searchPage(String title,String siteName, String type){
		info("waiting the page is loaded full");
		if (!title.isEmpty()) {
			info("Input a new title");
			type(ELEMENT_NODE_PAGE_SELECTOR_TITLE_FIELD, title, true);
		}
		if (!siteName.isEmpty()){
			info("Input a new site Name");
			type(ELEMENT_NODE_PAGE_SELECTOR_SITES_NAME_FIELD,siteName, true);
		}
		if(!type.isEmpty()){
			info("Select a type");
			select(ELEMENT_NODE_PAGE_SELECTOR_TYPE_DROPBOX, type,2);
		}
		info("Click on Search button");
		click(ELEMENT_NODE_PAGE_SELECTOR_SEARCH_BUTTON);
	}

    /**
     * Select a page in pages list after searching the page
     * @param title
     */
	public void selectPage(String title){
		info("Searching the page");
		searchPage(title,"","");
		info("Select a page");
		click(ELEMENT_NODE_PAGE_SELECTOR_SELECT_PAGES_BTN);
		Utils.pause(2000);
	}
	/**
	 * verify Add Navigation permission
	 * @param isEnable
	 */
	public void verifyAddNavigationPerm(String title,boolean isEnable){
		info("verify Add Navigation permission");
		
		if(isEnable){
			
		}
	}
}
