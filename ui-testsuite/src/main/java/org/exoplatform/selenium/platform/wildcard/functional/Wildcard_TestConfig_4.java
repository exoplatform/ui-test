package org.exoplatform.selenium.platform.wildcard.functional;

import static org.exoplatform.selenium.TestLogger.info;
import java.util.ArrayList;
import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.GateinPortalMemberShipsPermissionDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.SpaceGroupsDatabase;
import org.exoplatform.selenium.platform.social.SpaceHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.wiki.RichTextEditor;
import org.exoplatform.selenium.platform.wiki.WikiHomePage;
import org.exoplatform.selenium.platform.wiki.WikiLocators;
import org.exoplatform.selenium.platform.wiki.WikiManagement;
import org.exoplatform.selenium.platform.wiki.WikiPermission;
import org.exoplatform.selenium.platform.wiki.WikiValidattions;
import org.exoplatform.selenium.platform.wiki.WikiPermission.permissionType;
import org.exoplatform.selenium.platform.wiki.WikiPermission.userGroupTypes;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Wildcard_TestConfig_4 extends PlatformBase {
	
	HomePagePlatform hp;
	ManageLogInOut magAc;
	NavigationToolbar navTool;

	TextBoxDatabase txData;
	WikiHomePage wHome;
	WikiManagement wikiMg;
	WikiPermission wPerm;
	WikiValidattions wValidate;
	WikiPermission wPermission;
	WikiLocators wLocator;
	RichTextEditor richEditor;
	
	SpaceHomePage spaHome;
	SpaceManagement spaMg;
	GateinPortalMemberShipsPermissionDatabase membershipData;
	SpaceGroupsDatabase spGroupsData;
	
	ArrayList<String> arrayUsers;
	UserAddManagement addUserPage;
	UserAndGroupManagement userAndGroup;
	
	String groupA;
	String password;
	Button button;
	ArrayList<String> arrayPage;
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
		
		wHome = new WikiHomePage(driver);
		wikiMg = new WikiManagement(driver);
		wPerm = new WikiPermission(driver);
		richEditor = new RichTextEditor(driver);
		wValidate = new WikiValidattions(driver);
		wPermission = new WikiPermission(driver);
		wLocator = new WikiLocators();
		
		spaHome = new SpaceHomePage(driver);
		spaMg = new SpaceManagement(driver);
		
		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		
		spGroupsData = new SpaceGroupsDatabase();
		spGroupsData.setData(spaceGroupsFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		membershipData = new GateinPortalMemberShipsPermissionDatabase();
		membershipData.setData(portalPermisMemFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		magAc.signIn(DATA_USER1, DATA_PASS);
		groupA = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		password="123456";
		info("Create new user");
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		addGroup(groupA);
		addUserToGroup(arrayUsers.get(0),groupA);
		
		arrayPage  = new ArrayList<String>();
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
 	 	userAndGroup.addUsersToGroup(user,"*", false, true);
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
		if(arrayUsers.size()>0){
			magAc.signOut();
			magAc.signIn(DATA_USER1,DATA_PASS);
			navTool.goToUsersAndGroupsManagement();
			userAndGroup.deleteAllUsers(arrayUsers);
		}
	}
	/**
	 * Delete all wiki pages that are created in testing proccess
	 */
	public void deleteAllWikiPages(){
		if(arrayPage.size()>0){
			for(String title:arrayPage){
				info("Delete the page:"+title);
				driver.get(baseUrl);
				hp.goToWiki();
				wHome.deleteWiki(title);
			}
		}
	}
	/**
	 * Add wiki page
	 * @param title
	 * @param content
	 */
	public void addWikiPage(String title,String content){
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title, content);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title);
		arrayPage.add(title);
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
	 * Add wiki page in space
	 * @param content
	 * @param title
	 */
	public void addWikiPageInSpace(String title,String content){
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title, content);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		waitForAndGetElement(wHome.ELEMENT_INFOR_BAR_VERSION.replace("$version","V1"));
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
	 * Add permission
	 */
	public void addPermWikiSet(permissionType type,String space){
		wHome.goToWikiSettingPage();
		wHome.openPermTab();
		wPerm.addPermissionBySelect(groupA,"*",userGroupTypes.Membership);
		wPerm.selectPermission(groupA,type);
		if(space.length()>0)
			wPerm.unSelectPermission("* in "+space,type);
		wPerm.savePermWikiSetting();
	}
	/**
	 * Remove permission
	 */
	public void removePermWikiSet(permissionType type,String space){
		wHome.goToWikiSettingPage();
		wHome.openPermTab();
		wPerm.unSelectPermission(groupA,type);
		wPerm.savePermWikiSetting();
	}
	/**
	 * Add permission in wiki page
	 */
	public void addPermToWikiPage(String title,permissionType type,String space){
		wHome.selectAPage(title);
		click(wLocator.ELEMENT_MORE_LINK,0,true);
		click(wLocator.ELEMENT_PERMISSION_LINK,0,true);
		wPerm.addPermissionBySelect(groupA,"*",userGroupTypes.Membership);
		wPerm.selectPermission(groupA,type);
		if(space.length()>0)
			wPerm.unSelectPermission("* in "+space,type);
		wPerm.savePermisison();
	}
	/**
	 * Remove permission in wiki page
	 */
	public void removePermToWikiPage(String title,permissionType type){
		wHome.selectAPage(title);
		click(wLocator.ELEMENT_MORE_LINK,0,true);
		click(wLocator.ELEMENT_PERMISSION_LINK,0,true);
		wPerm.unSelectPermission(groupA,type);
		wPerm.savePermisison();
	}
	/**
	 * Delete permission in wiki page
	 */
	public void deletePermToWikiPage(String title,String groupUsers,String space){
		wHome.selectAPage(title);
		click(wLocator.ELEMENT_MORE_LINK,0,true);
		click(wLocator.ELEMENT_PERMISSION_LINK,0,true);
		wPerm.deletePermission(groupUsers);
		wPerm.deletePermission("any");
		if(space.length()>0)
			wPerm.deletePermission("* in "+space);
		wPerm.savePermisison();
	}
	/**
	 * Delete permission 
	 */
	public void deletePermWikiSet(String groupUsers,String space){
		wHome.goToWikiSettingPage();
		wHome.openPermTab();
		wPerm.deletePermission(groupUsers);
		wPerm.deletePermission("any");
		if(space.length()>0)
			wPerm.deletePermission("* in "+space);
		wPerm.savePermWikiSetting();
	}
}