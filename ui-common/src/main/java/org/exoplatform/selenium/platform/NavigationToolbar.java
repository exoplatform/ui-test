package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.locator.NavigationToolBarLocator;
import org.exoplatform.selenium.platform.gatein.PageCreationWizard;
import org.exoplatform.selenium.platform.gatein.PortalManagePages;
import org.exoplatform.selenium.platform.gatein.PortalManageSites;
import org.exoplatform.selenium.platform.social.UserProfilePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigationToolbar extends NavigationToolBarLocator {

	PageCreationWizard paWin;
	UserProfilePage myPro;
	PortalManageSites magSites;
	PortalManagePages portMg;
	public NavigationToolbar(WebDriver dr){
		this.driver = dr;
		paWin = new PageCreationWizard(dr);
		myPro = new UserProfilePage(dr);
		magSites = new PortalManageSites(dr);		
		portMg = new PortalManagePages(dr);
	} 

	/**
	 * Go to Edit Layout
	 */
	public void goToEditLayout(){
		info("--Go to Edit Layout--");
		Utils.pause(3000);
		clickByJavascript(ELEMENT_LINK_EDIT);
		mouseOver(ELEMENT_MENU_PAGE_LINK, true);
		click(ELEMENT_MENU_EDIT_LAYOUT,2,true);
	}

	/** 
	 * Go to add page form: Edit-->Page-->Add page
	 */
	public void goToAddPage(){
		info("Go to add page form");
		Utils.pause(3000);
		waitForAndGetElement(ELEMENT_LINK_EDIT);
		click(ELEMENT_LINK_EDIT);
		mouseOver(ELEMENT_MENU_PAGE_LINK, true);
		click(ELEMENT_MENU_ADD_PAGE_LINK,2,true);
		Utils.pause(3000);
		waitForAndGetElement(paWin.ELEMENT_PAGE_CREATION_WIZARD,3000,0);
	}

	/**
	 * Go to Manage Sites page: Administration-->Portal->Sites
	 * By QuynhPT
	 */
	public void goToPotalSites(){
		info("--Go to Portal-->Sites--");
		//waitElementAndTryGetElement(ELEMENT_TOOLBAR_ADMINISTRATION);
		waitForAndGetElement(ELEMENT_TOOLBAR_ADMINISTRATION, DEFAULT_TIMEOUT, 1);
		click(ELEMENT_TOOLBAR_ADMINISTRATION);
		waitForAndGetElement(ELEMENT_ADMINISTRATION_PORTAL, 6000, 0);
		mouseOver(ELEMENT_ADMINISTRATION_PORTAL,true);
		waitForAndGetElement(ELEMENT_ADMINISTRATION_PORTAL_SITES,DEFAULT_TIMEOUT,0);
		click(ELEMENT_ADMINISTRATION_PORTAL_SITES);
		Utils.pause(3000);
		waitForAndGetElement(magSites.ELEMENT_MANAGESITES_TITLE,3000,0);
	}

	/**
	 * Go to Manage Group Sites page: Administration-->Portal->Group Sites
	 */
	public void goToGroupSites(){
		info("--Go to Portal-->Sites--");
		waitElementAndTryGetElement(ELEMENT_TOOLBAR_ADMINISTRATION);
		click(ELEMENT_TOOLBAR_ADMINISTRATION);
		mouseOver(ELEMENT_ADMINISTRATION_PORTAL,true);
		waitForAndGetElement(ELEMENT_ADMINISTRATION_PORTAL_GROUP_SITES,3000,0);
		click(ELEMENT_ADMINISTRATION_PORTAL_GROUP_SITES);
		Utils.pause(3000);
		waitForAndGetElement(magSites.ELEMENT_ADD_NAVIGATION_BUTTON,3000,0);
	}

	/**
	 * Go to Manage Sites page: Administration-->Portal->Pages
	 */
	public void goToPotalPages(){
		info("-- Go to Page Management page --");
		Utils.pause(2000);
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				mouseOverAndClick(ELEMENT_LINK_SETUP);
				break;
			}
			//mouseOver(ELEMENT_LINK_SETUP, true);
			clickByJavascript(ELEMENT_LINK_SETUP, 2);
			if (waitForAndGetElement(ELEMENT_ADMINISTRATION_PORTAL, 5000, 0) != null){
				info("Element " + ELEMENT_ADMINISTRATION_PORTAL + "... is displayed");
				break;
			}
			info("Retry...[" + repeat + "]");
		}
		mouseOverAndClick(ELEMENT_ADMINISTRATION_PORTAL);
		Utils.pause(2000);
		info("Page Managements is shown successfully");
	}


	/**
	 * function: Go to Users and Group management (Administration --> Users --> Groups and Roles)
	 */
	public void goToUsersAndGroupsManagement() {
		info("--Go to Users and groups management--");
		Utils.pause(3000);	
        if(browser.contains("iexplorer")){
        	waitForAndGetElement(ELEMENT_TOOLBAR_ADMINISTRATION, DEFAULT_TIMEOUT, 1, 2);
    		clickByJavascript(ELEMENT_TOOLBAR_ADMINISTRATION, 2);
    		waitForAndGetElement(ELEMENT_ADMINISTRATION_USERS, DEFAULT_TIMEOUT, 1);
        }
        else
		click(ELEMENT_LINK_SETUP, 2);
		mouseOver(ELEMENT_ADMINISTRATION_USERS, true);
		if(waitForAndGetElement(ELEMENT_GROUP_AND_ROLE_LINK,2000,0)!=null)
			click(ELEMENT_GROUP_AND_ROLE_LINK);
		else {
			driver.get(baseUrl+"/g/:platform:administrators/administration/management");
		}
		Utils.pause(3000);
	}
	
	
	/**
	 * function: Go to Users and Group management (Administration --> Community --> Manage Community)
	 */
	public void goToCommunityManagement() {
		info("--Go to Users and groups management--");
		click(ELEMENT_TOOLBAR_ADMINISTRATION);
		mouseOver(ELEMENT_ADMINISTRATION_USERS, true);
		if(waitForAndGetElement(ELEMENT_GROUP_AND_ROLE_LINK,2000,0)!=null)
			click(ELEMENT_GROUP_AND_ROLE_LINK);
		else {
			driver.get(baseUrl+"/g/:platform:administrators/administration/management");
		}
		Utils.pause(2000);
	}	

	/**
	 * List sublink in user menu
	 */
	public enum specifUserToolBar{
		MY_PROFILE,MY_ACTIVITY,MY_CONNECTIONS,MY_WIKI,MY_DASHBOARD,MY_NOTIFICATION,SETTINGS,CHANGE_LANGUAGE;
	}
	/**
	 * Select a link in User menu
	 * @param link
	 */
	public void selectALinkOfUserMenu(specifUserToolBar link){
		openUserMenu();
		switch(link){
		case MY_PROFILE:
			click(ELEMENT_MY_PROFILE_LINK);
			Utils.pause(2000);
			waitForAndGetElement(myPro.ELEMENT_MY_PROFILE_TAB,3000,1);
			break;
		case MY_ACTIVITY:
			info("Go to Activities of User");
			waitForAndGetElement(ELEMENT_ACTIVITIES_LINK);
			click(ELEMENT_ACTIVITIES_LINK);
			waitForAndGetElement(ELEMENT_ACTIVITIES_PORTLET, 2000,1);
			break;
		case MY_CONNECTIONS:
			click(ELEMENT_MY_CONNECTION_LINK);
			break;
		case MY_WIKI:
			click(ELEMENT_MY_WIKI_LINK);
			break;
		case MY_DASHBOARD:
			click(ELEMENT_MY_DASHBOARD_LINK);
			break;
		case MY_NOTIFICATION:
			click(ELEMENT_MY_NOTIFICATIONS_LINK);
			break;
		case SETTINGS:
			click(ELEMENT_MY_SETTINGS_LINK);
			break;
		case CHANGE_LANGUAGE:
			break;
		}
	}
	/**
	 * Open User menu by click on username
	 */
	public void openUserMenu() {
		info("--Open User Menu--");
		waitForAndGetElement(ELEMENT_ACCOUNT_NAME_LINK);
		click(ELEMENT_ACCOUNT_NAME_LINK);
		Utils.pause(1000);
	}
    /**
     * Go to Adinistration->Content->Site Explorer
     */
	public void goToSiteExplorer() {
		info("-- Go to site explorer home page --");
		Utils.pause(500);
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				mouseOverAndClick(ELEMENT_LINK_SETUP);
				break;
			}
           if(browser.contains("iexplorer")){
        	   waitForAndGetElement(ELEMENT_TOOLBAR_ADMINISTRATION, DEFAULT_TIMEOUT, 1, 2);
   			clickByJavascript(ELEMENT_TOOLBAR_ADMINISTRATION, 2);
           }
           else
			click(ELEMENT_LINK_SETUP, 2);
			if (waitForAndGetElement(ELEMENT_MENU_CONTENT_LINK, 5000, 0) != null){
				info("Element " + ELEMENT_MENU_CONTENT_LINK + "... is displayed");
				mouseOver(ELEMENT_MENU_CONTENT_LINK, true);
				if (waitForAndGetElement(ELEMENT_MENU_SITE_EXPLORER, 5000, 0)!= null){
					click(ELEMENT_MENU_SITE_EXPLORER);
					break;
				}
			}
			info("Retry...[" + repeat + "]");
		}
		Utils.pause(2000);
		info("Site Explorer is shown successfully");
	}
	
	
	/**
	 * Go to Edit-->Page-->SEO
	 */
	public void goToSEO(){
		info("Go to SEO page");
		info("Click on Edit button");
		click(ELEMENT_LINK_EDIT);
		info("Hover over on Page link");
		mouseOver(ELEMENT_MENU_PAGE_LINK, true);
		info("Click on Seo Menu");
		WebElement seoMenu = waitForAndGetElement(ELEMENT_MENU_SEO_LINK,10000,1,2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click()",seoMenu);		
		Utils.pause(2000);
	}
	/**
	 * Go to Edit Content
	 */
	public void goToEditContent(){
		info("Go to Edit content");
		waitForAndGetElement(ELEMENT_LINK_EDIT);
		click(ELEMENT_LINK_EDIT);
		if(waitForAndGetElement(ELEMENT_EDIT_CONTENT,5000,0)!=null)
			click(ELEMENT_EDIT_CONTENT);
	}
	/**
	 * Go to Un-edit content
	 * Edit-->Uncheck Content
	 */
	public void goToUnEditContent(){
		info("Go to un Edit content");
		waitForAndGetElement(ELEMENT_LINK_EDIT);
		click(ELEMENT_LINK_EDIT);
		if(waitForAndGetElement(ELEMENT_EDIT_CONTENT_CHECK,5000,0)!=null)
			click(ELEMENT_EDIT_CONTENT_CHECK);
	}
	/**
	 * Open Change language popup
	 * Username-->Change Language
	 */
	public void goToChangeLanguage(){
		info("Open Change Language popup");
		waitForAndGetElement(ELEMENT_TOPBAR_AVATAR);
		click(ELEMENT_TOPBAR_AVATAR);
		click(ELEMENT_AVATAR_CHANGELANGUAGE);
		Utils.pause(2000);
	}

	/**
	 * Go to content administration
	 */
	public void goToContentAdministration(){
		info("Go to content administration");		
		if (baseUrl==null) baseUrl = DEFAULT_BASEURL;
		info("Base url is " + baseUrl);
		String url = baseUrl + "/g/:platform:web-contributors/wcmAdmin";
		info("base url of content admin is " + baseUrl);
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				driver.get(url);
				break;
			}
			if(browser.contains("iexplorer")){
				waitForAndGetElement(ELEMENT_TOOLBAR_ADMINISTRATION, DEFAULT_TIMEOUT, 1, 2);
				clickByJavascript(ELEMENT_TOOLBAR_ADMINISTRATION, 2);
			}
			else
			click(ELEMENT_LINK_SETUP, 2);
			if (waitForAndGetElement(ELEMENT_MENU_CONTENT_LINK, 5000, 0)!= null) {
				mouseOver(ELEMENT_MENU_CONTENT_LINK, true);
				if (waitForAndGetElement(ELEMENT_LINK_CONTENT_ADMIN, 5000, 0)!= null){
					click(ELEMENT_LINK_CONTENT_ADMIN);
					break;
				}
			}
			info("Retry...[" + repeat + "]");
		}
		Utils.pause(1000);
	}
	/**
	 * Go to IDE page
	 */
	public void goToIDE(){
		info("-- Go to IDE home page --");
		Utils.pause(500);
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				mouseOverAndClick(ELEMENT_LINK_SETUP);
				break;
			}
			mouseOver(ELEMENT_LINK_SETUP, true);
			if (waitForAndGetElement(ELEMENT_ADMINISTRATION_PORTAL_IDE, 5000, 0) != null){
				info("Element " +ELEMENT_ADMINISTRATION_PORTAL_IDE + "... is displayed");
				break;
			}
			info("Retry...[" + repeat + "]");
		}
		mouseOverAndClick(ELEMENT_ADMINISTRATION_PORTAL_IDE);
		Utils.pause(2000);
	}
	/**
	 * Go to Application home page
	 */
	public void goToApplication(){
		info("-- Go to Application home page --");
		Utils.pause(500);
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				mouseOverAndClick(ELEMENT_LINK_SETUP);
				break;
			}
			clickByJavascript(ELEMENT_LINK_SETUP, 2);
			if (waitForAndGetElement(ELEMENT_ADMINISTRATION_APPLICATION, 5000, 0) != null){
				info("Element " +ELEMENT_ADMINISTRATION_APPLICATION + "... is displayed");
				break;
			}
			info("Retry...[" + repeat + "]");
		}
		mouseOverAndClick(ELEMENT_ADMINISTRATION_APPLICATION);
		Utils.pause(2000);
	}

	/**
	 * Go to Banding page
	 */
	public void goToBanding() {
		info("-- Go to Banding page --");
		waitElementAndTryGetElement(ELEMENT_TOOLBAR_ADMINISTRATION);
		click(ELEMENT_TOOLBAR_ADMINISTRATION);
		mouseOver(ELEMENT_ADMINISTRATION_PORTAL, true);
		waitForAndGetElement(ELEMENT_ADMINISTRATION_PORTAL_BRANDING,5000,0);
		click(ELEMENT_ADMINISTRATION_PORTAL_BRANDING);
	}


	/**
	 * Go to create wiki page from the toolbars
	 * @param location
	 */
	public void goToCreateWikiPage(String location){
		info("Go to create wiki page");
		click(ELEMENT_ADD_TOOTLBAR);
		click(ELEMENT_ADD_WIKI_TOOLBAR);
		if(!location.isEmpty()){
			click(ELEMENT_ADD_WIKI_SET_LOCATION);
			click(ELEMENT_ADD_WIKI_CHOOSE_LOCATION.replace("{$location}", location));
		}
		click(ELEMENT_NEXT_BUTTON);
	}

	/**
	 * Go to add a pool from the toolbar
	 * @param location
	 * @param forum
	 */
	public void goToAddPoll(String location,String forum){
		info("Go to add poll from tootlbar");
		waitForAndGetElement(ELEMENT_ADD_TOOTLBAR,3000,0).click();
		info("Click on Poll link");
		waitForAndGetElement(ELEMENT_ADD_POOL_TOOLBAR,3000,0).click();
		if (location!=""&&location!=null){
			info("Set location for the poll");
			click(ELEMENT_ADD_POLL_SET_LOCATION);
		}
		info("click on Next button");
		click(ELEMENT_NEXT_BUTTON);
		Utils.pause(2000);
		info("Select a forum for poll");
		waitForAndGetElement(ELEMENT_SELECT_FORUM_COMBOBOX,3000,0).click();
		waitForAndGetElement(ELEMENT_SELECT_FORUM_NAME.replace("${forum}",forum),2000,0).click();
		info("Click on next button");
		click(ELEMENT_NEXT_BUTTON);
		Utils.pause(2000);
	}
	/**
	 * Add a topic from the toolbar
	 * @param location
	 * @param forum
	 */
	public void goToAddTopic(String location,String forum){
		info("Go to add a topic from toolbar");
		click(ELEMENT_ADD_TOOTLBAR);
		click(ELEMENT_ADD_TOPIC_TOOLBAR);
		if (location!=""){
			click(ELEMENT_ADD_POLL_SET_LOCATION);
		}
		click(ELEMENT_NEXT_BUTTON);
		info("click on Next button");
		click(ELEMENT_NEXT_BUTTON);
		info("Select a forum for topic");
		click(ELEMENT_SELECT_FORUM_COMBOBOX);
		click(ELEMENT_SELECT_FORUM_NAME.replace("${forum}",forum));
		info("Click on next button");
		click(ELEMENT_NEXT_BUTTON);
		Utils.pause(2000);
	}

	/**
	 * Add an event or a task from the toolbar
	 * @param eventTask
	 * @param name
	 * @param from
	 * @param to
	 * @param calendar
	 */
	public void goToAddEventTask(String eventTask,String name,String from, String to, String calendar){
		click(ELEMENT_ADD_TOOTLBAR);
		click(ELEMENT_ADD_EVENT_CLASS_TOOLBAR);
		if(eventTask=="event"){
			check(ELEMENT_ADD_EVENT_RADIO_BUTTON,2);
		}
		if(eventTask=="task"){
			check(ELEMENT_ADD_TASK_RADIO_BUTTON,2);
		}
		type(ELEMENT_ADD_TITLE,name,true);
		click(ELEMENT_SAVE_BUTTON);
	}

	/**
	 * Go to upload file fron the toolbar
	 * @param drive
	 * @param pathInDrive
	 * @param linkFile
	 */
	public void goToUploadFile(String drive, String pathInDrive,String linkFile){
		info("-- Upload file --");
		click(ELEMENT_ADD_TOOTLBAR);
		click(ELEMENT_UPLOAD_FILE_TOOLBAR);

		if(drive!=""){

		}

		if(pathInDrive!=""){

		}else{
			click(ELEMENT_UPLOAD_FILE_TOOLBAR_PERSONNAL_DOCUMENTS);
		}

		WebElement frame = waitForAndGetElement(ELEMENT_UPLOAD_FILE_FRAME_XPATH);
		driver.switchTo().frame(frame);
		info(getAbsoluteFilePath(linkFile));
		Utils.pause(2000);
		((JavascriptExecutor)driver).executeScript("document.getElementsByTagName('input')[0].style.display = 'block';");
		Utils.pause(2000);
		driver.findElement(ELEMENT_ACTIVITY_UPLOAD_POPUP_UPLOAD_BUTTON).sendKeys(getAbsoluteFilePath(linkFile));
		Utils.pause(1000);
		switchToParentWindow();
		info("Upload finished");
	}


	/**
	 * Open search administration
	 */
	public void goToAdminSearch() {
		waitElementAndTryGetElement(ELEMENT_TOOLBAR_ADMINISTRATION);
		click(ELEMENT_TOOLBAR_ADMINISTRATION);
		mouseOver( ELEMENT_MENU_CONTENT_LINK , true);
		click(ELEMENT_SEARCH_LINK);
	}
	/**
	 * Open quick search on toolbar
	 */
	public void goToQuickSearch(){
		info("Click on Quick search icon");
		click(ELEMENT_TOOLBAR_QUICKSEARCH);
		Utils.pause(2000);
	}

	/**
	 * Open My profile page
	 */
	public void goToMyProfile(){
		selectALinkOfUserMenu(specifUserToolBar.MY_PROFILE);
		Utils.pause(2000);
	}

	/**
	 * Open My dashboard
	 */
	public void goToMyDashboard(){
		selectALinkOfUserMenu(specifUserToolBar.MY_DASHBOARD);
		Utils.pause(2000);
	}

	/** 
	 * Go to My activities
	 */
	public void goToMyActivities(){
		selectALinkOfUserMenu(specifUserToolBar.MY_ACTIVITY);
		Utils.pause(2000);
	}

	/**
	 * Open My dashboard
	 */
	public void goToMySettings(){
		selectALinkOfUserMenu(specifUserToolBar.SETTINGS);
	}

	/** Open My Connection
	 */
	public void goToMyConnection(){
		selectALinkOfUserMenu(specifUserToolBar.MY_CONNECTIONS);
		Utils.pause(2000);
	}

	/**
	 * Go to My wiki page
	 */
	public void goToMyWiki(){
		selectALinkOfUserMenu(specifUserToolBar.MY_WIKI);
		Utils.pause(2000);
	}

	/**
	 * Go to email notification
	 */
	public void goToAdminNotifications(){
		info("Go to email notifications");
		waitElementAndTryGetElement(ELEMENT_TOOLBAR_ADMINISTRATION);
		click(ELEMENT_TOOLBAR_ADMINISTRATION);
		mouseOver(ELEMENT_ADMINISTRATION_PORTAL,true);
		waitForAndGetElement(ELEMENT_ADMINISTRATION_PORTAL_EMAIL_NOTIFICATIONS,3000,1);
		click(ELEMENT_ADMINISTRATION_PORTAL_EMAIL_NOTIFICATIONS);
		Utils.pause(2000);
	}

	/**
	 * Go to add an user
	 */
	public void goToAddUser(){
		info("Go to add user page");
		int repeat =0;
		while(waitForAndGetElement(ELEMENT_ADMINISTRATION_USERS, 3000,0)==null){
			if(repeat>5)break;
			info("Click on Administration icon");
			click(ELEMENT_TOOLBAR_ADMINISTRATION);
			repeat++;
		}
		info("add user page is shown");
		info("mouse over on community link");
		mouseOver(ELEMENT_ADMINISTRATION_USERS,true);
		if(waitForAndGetElement(ELEMENT_ADMINISTRATION_PORTAL_ADD_USERS,3000,0)!=null){
			info("click on Add user link");
			click(ELEMENT_ADMINISTRATION_PORTAL_ADD_USERS);
		}else{
			info("Cannot click on add user link. Go to this page by link");
			driver.get(baseUrl+"/g/:platform:administrators/administration/newStaff");
		}
	}
	/**
	 * Open Notification list
	 */
	public void goToNotificationList(){
		info("Click on Notification icon");
		click(ELEMENT_TOOLBAR_NOTIFICATION_LIST);
		info("Notification list is shown");
		waitForAndGetElement(ELEMENT_NOTIFICATION_DROPDOWN,3000,1);

	}

	/** Open My Notifications
	 */
	public void goToMyNotifications(){
		selectALinkOfUserMenu(specifUserToolBar.MY_NOTIFICATION);
		Utils.pause(2000);
	}

	/** Open Intranet Notification
	 */
	public void goToIntranetNotification(){
		info("Go to Intranet Notification");
		driver.navigate().refresh();
		Utils.pause(2000);
		click(ELEMENT_INTRANET_NOTIFICATION_BELL);
		waitForAndGetElement(ELEMENT_NOTIFICATION_DROPDOWN);
		info("The elemnt is shown successfully");
	}
	
	/**
	 * Go to Edit/Site/Layout
	 */
	public void goToEditSiteLayout(){
		info("Go to Edit layout form");
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				mouseOverAndClick(ELEMENT_LINK_EDIT);
				break;
			}
			mouseOver(ELEMENT_LINK_EDIT, true);
			if (waitForAndGetElement( ELEMENT_MENU_EDIT_SITES, 5000, 0)!= null) {
				info("-- Click Site menu --");
				mouseOver( ELEMENT_MENU_EDIT_SITES,true);
				if (waitForAndGetElement(ELEMENT_MENU_EDIT_SITE_LAYOUT, 5000, 0)!= null){
					click(ELEMENT_MENU_EDIT_SITE_LAYOUT);
					break;
				}
			}
			else{
				String editPageRequest = "ajaxGet(eXo.env.server.createPortalURL('UIWorkingWorkspace', 'EditInline', true))";
				info("editPageRequest:"+editPageRequest);
				((JavascriptExecutor)driver).executeScript(editPageRequest);
				Utils.pause(1000);
				break;
			}
			info("Retry...[" + repeat + "]");
		}
		Utils.pause(1000);
	}
	/**
	 * go to Edit->Site->Navigation
	 */
	public void goToEditSiteNavigation(){
		info("Go to Edit layout form");
		int repeat=0;
		while(waitForAndGetElement(ELEMENT_NAVIGATION_MANAGE_POPUP, 5000, 0)== null){
			if(repeat>5)break;
			if(waitForAndGetElement(ELEMENT_MENU_EDIT_SITES_NAV, 5000, 0)!= null)break;
			info("Click on Edit link");
			click(ELEMENT_LINK_EDIT);
			info("-- Click Site menu --");
			mouseOver(ELEMENT_MENU_EDIT_SITES,true);
			info("Click on Navigation link");
			click(ELEMENT_MENU_EDIT_SITES_NAV);
			repeat++;
		}
		Utils.pause(1000);
	}
	/**
	 * go to Edit->Site->Add Site
	 */
	public void goToEditSiteAddSite(){
		info("Go to Edit layout form");
		int repeat=0;
		while(waitForAndGetElement(ELEMENT_ADDSITE_MANAGE_POPUP, 5000, 0)== null){
			if(repeat>5)break;
			if(waitForAndGetElement(ELEMENT_ADDSITE_MANAGE_POPUP, 5000, 0)!= null)break;
			info("Click on Edit link");
			click(ELEMENT_LINK_EDIT);
			info("-- Click Site menu --");
			mouseOver( ELEMENT_MENU_EDIT_SITES,true);
			info("Click Add Site link");
			click(ELEMENT_MENU_EDIT_ADDSITE);
			repeat++;
		}
		Utils.pause(1000);
	}
    /**
     * verify edit page
     * @param title
     * @param isEnable
     */
	public void verifyEditPagePerm(String title,boolean isEnable){
		info("verify edit page permission");
		portMg.openPage(baseUrl+"/intranet/home/"+title);
		if(isEnable){
			click(ELEMENT_LINK_EDIT,0,true);
			mouseOver(ELEMENT_MENU_PAGE_LINK, true);
			waitForAndGetElement(ELEMENT_MENU_EDIT_LAYOUT);
		}else{
			waitForElementNotPresent(ELEMENT_LINK_EDIT);
			waitForElementNotPresent(ELEMENT_MENU_EDIT_LAYOUT);
		}
	}
	/**
     * verify edit site
     * @param title
     * @param isEnable
     */
	public void verifyEditSitePerm(String title,boolean isEnable){
		info("verify edit stie permission");
		driver.get(baseUrl + "/" + title);
		if(isEnable){
			click(ELEMENT_LINK_EDIT,0,true);
			mouseOver(ELEMENT_MENU_SITE_LINK, true);
			waitForAndGetElement(ELEMENT_MENU_LAYOUT);
		}else{
			waitForElementNotPresent(ELEMENT_MENU_LAYOUT);
		}
	}
}
