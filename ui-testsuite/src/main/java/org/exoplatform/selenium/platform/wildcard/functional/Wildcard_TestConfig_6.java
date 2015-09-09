package org.exoplatform.selenium.platform.wildcard.functional;

import static org.exoplatform.selenium.TestLogger.info;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.gatein.ApplicationRegistry;
import org.exoplatform.selenium.platform.gatein.PageCreationWizard;
import org.exoplatform.selenium.platform.gatein.PageEditor;
import org.exoplatform.selenium.platform.gatein.PortalGroupNavigation;
import org.exoplatform.selenium.platform.gatein.PortalManagePages;
import org.exoplatform.selenium.platform.gatein.PortalManageSites;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.objectdatabase.common.LanguageDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.AppAddGateinDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.AppListGateinDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.ApplicationLayoutDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.ContainersDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.GateinPortalMemberShipsPermissionDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.SpaceGroupsDatabase;
import org.exoplatform.selenium.platform.social.SpaceHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Wildcard_TestConfig_6 extends PlatformBase {
	
	HomePagePlatform hp;
	ManageLogInOut magAc;
	NavigationToolbar navTool;

	TextBoxDatabase txData;
	
	SpaceHomePage spaHome;
	SpaceManagement spaMg;
	GateinPortalMemberShipsPermissionDatabase membershipData;
	SpaceGroupsDatabase spGroupsData;
	
	PortalManagePages portMg;
	PageCreationWizard pagCW;
	ApplicationLayoutDatabase appLayData;
	PageEditor pagEditor;
	PortalGroupNavigation portGrpNav;
	LanguageDatabase langData;
	PortalManageSites portMgSite;
	
	ArrayList<String> arrayUsers;
	UserAddManagement addUserPage;
	UserAndGroupManagement userAndGroup;
	
	ApplicationRegistry appRegistry;
	AppListGateinDatabase appGateInData;
	AppAddGateinDatabase appAddGateinData;
	ContainersDatabase contaiData;
	
	String groupA;
	String password;
	Button button;
	ArrayList<String> arrayPage;
	String portletId;
	String portletName;
	@BeforeMethod
	public void setUpBeforeMethod() throws Exception{
		info("Start setUpBeforeClass");
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		magAc = new ManageLogInOut(driver);
		navTool = new NavigationToolbar(driver);
		hp = new HomePagePlatform(driver);
		button = new Button(driver);
		addUserPage = new UserAddManagement(driver);
		userAndGroup = new UserAndGroupManagement(driver);
		
		spaHome = new SpaceHomePage(driver);
		spaMg = new SpaceManagement(driver);
		
		portMg = new PortalManagePages(driver);
		pagCW = new PageCreationWizard(driver);
		pagEditor = new PageEditor(driver);
		portGrpNav = new PortalGroupNavigation(driver);
		portMgSite = new PortalManageSites(driver);
		
		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		
		spGroupsData = new SpaceGroupsDatabase();
		spGroupsData.setData(spaceGroupsFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		membershipData = new GateinPortalMemberShipsPermissionDatabase();
		membershipData.setData(portalPermisMemFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		appLayData = new ApplicationLayoutDatabase();
		appLayData.setApplicationLayoutData(appLayoutFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		langData = new LanguageDatabase();
		langData.setLanguageData(languageFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		
		appRegistry = new ApplicationRegistry(driver);
		appGateInData = new AppListGateinDatabase();
		appGateInData.setApplicationGateinData(appListGateinFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
	
		appAddGateinData = new AppAddGateinDatabase();
		appAddGateinData.setAppAddGateinData(appAddGateinFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		contaiData = new ContainersDatabase();
		contaiData.setContainersData(containerFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		magAc.signIn(DATA_USER1, DATA_PASS);
		arrayPage  = new ArrayList<String>();
		portletId = appLayData.id.get(2);
		portletName = appLayData.title.get(2);
		info("End setUpBeforeClass");
	}

	@AfterMethod
	public void afterMethod(){
		info("Start afterClass");
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End afterClass");
	}
	
	public void addGroup(String name){
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.goToGroupTab();
		userAndGroup.addGroup(name, name,name, true);
	}
	public void addUserToGroup(String user,String group){
 	 	click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}",group));
 	 	userAndGroup.addUsersToGroup(user,"*", false, false);
	}
	public void deleteGroup(String name){
		navTool.goToUsersAndGroupsManagement();
 	 	userAndGroup.goToGroupTab();
 	 	click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}",name));
		userAndGroup.deleteGroup(name, true);
	}
	/**
	 * Delete all users that are created in testing process
	 */
	public void deleteAllUsers(){
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		navTool.goToUsersAndGroupsManagement();
		if(arrayUsers.size()>0){
			userAndGroup.deleteAllUsers(arrayUsers);
		}
	}

	/**
	 * Create space
	 * @param space
	 */
	public void createSpace(String space){
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
	}

	/**
	 * Add user as member of space
	 * @param space
	 */
	public void addUserSpace(String space,String user){
		navTool.goToUsersAndGroupsManagement();
        userAndGroup.goToGroupTab();
        ArrayList<String> groups=spGroupsData.getArrayGroupByType(4);
		String[] arrayGroupPath ={groups.get(0),space};
		String membership = membershipData.getContentByIndex(4);
		userAndGroup.selectGroup(arrayGroupPath);
		userAndGroup.addUsersToGroup(user,membership,false,true);
	}
    /**
     * Add page with Group Navigation Portlet
     * @param title
     */
	public void addPage(String title,String group,String mem){
		hp.goToHomePage();
		navTool.goToAddPage();
		pagCW.inputPageInfoStep1(title, true, "English",title, null,null);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		pagCW.addApp("",portletName,pagCW.ELEMENT_APPLICATION_APPLICATION.replace("${name}",portletId),pagCW.ELEMENT_PAGEEDITOR_VIEWPAGE);
		info("edit permission");
		navTool.goToEditLayout();
		pagEditor.goToEditPortlet(portletName);
		click(pagCW.ELEMENT_PAGEEDITOR_ACCESS_PERM_BTN,0,true);
        pagCW.selectAccessPerm(group,mem);
        pagCW.saveChangesApplication();
        pagCW.saveChangesPageEditor();
	}
	/**
	 * Delete page
	 */
	public void deletePage(String title){
		navTool.goToPotalPages();
		portMg.searchPage(title, "","",true);
		portMg.deletePage(title,"");
	}
	/**
	 * Add portal
	 */
	public void addPortal(String portal,boolean publicMode,String group,String mem){
		Map<String, String> permissions = new HashMap<String,String>();
        permissions.put(groupA, "*");
		String language = langData.getLanguageByIndex(0);
		navTool.goToPotalSites();
		portMgSite.addNewPortal(portal, null, null, language, null, "Always", publicMode, permissions, group,mem);
		waitForAndGetElement(portMgSite.ELEMENT_NEW_PORTAL_ADD.replace("${portalName}", portal));
	}
	/**
	 * Delete portal
	 */
	public void deletePortal(String portal){
		navTool.goToPotalSites();
		portMgSite.deletePortal(portal);
	}
}