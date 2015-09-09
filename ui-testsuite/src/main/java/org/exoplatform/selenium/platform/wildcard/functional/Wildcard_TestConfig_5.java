package org.exoplatform.selenium.platform.wildcard.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.exoplatform.selenium.Button;
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
import org.exoplatform.selenium.platform.social.SpaceSettingManagement;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Wildcard_TestConfig_5 extends PlatformBase {
	
	HomePagePlatform hp;
	ManageLogInOut magAc;
	NavigationToolbar navTool;

	TextBoxDatabase txData;
	
	SpaceHomePage spaHome;
	SpaceManagement spaMg;
	SpaceSettingManagement spaSetMg;
	GateinPortalMemberShipsPermissionDatabase membershipData;
	SpaceGroupsDatabase spGroupsData;
	
	ArrayList<String> arrayUsers;
	UserAddManagement addUserPage;
	UserAndGroupManagement userAndGroup;
	
	String spaceA;
	String password;
	Button button;
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
		spaSetMg = new SpaceSettingManagement(driver);
		
		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		
		spGroupsData = new SpaceGroupsDatabase();
		spGroupsData.setData(spaceGroupsFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		membershipData = new GateinPortalMemberShipsPermissionDatabase();
		membershipData.setData(portalPermisMemFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		magAc.signIn(DATA_USER1, DATA_PASS);
		spaceA = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		password="123456";
		info("Create new user");
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		createSpace(spaceA);
		addUserSpace(spaceA,arrayUsers.get(0));
		info("End setUpBeforeClass");
	}

	@AfterMethod
	public void afterMethod(){
		info("Start afterClass");
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End afterClass");
	}
	/**
	 * Add user as member of space
	 */
	public void addUserSpace(){
		navTool.goToUsersAndGroupsManagement();
        userAndGroup.goToGroupTab();
        ArrayList<String> groups=spGroupsData.getArrayGroupByType(4);
		String[] arrayGroupPath ={groups.get(0),spaceA};
		String membership = membershipData.getContentByIndex(4);
		userAndGroup.selectGroup(arrayGroupPath);
		userAndGroup.addUsersToGroup(user,membership,false,true);
	}
	/**
	 * Remove user from space
	 */
	public void removeUserSpace(){
		navTool.goToUsersAndGroupsManagement();
        userAndGroup.goToGroupTab();
        ArrayList<String> groups=spGroupsData.getArrayGroupByType(4);
		String[] arrayGroupPath ={groups.get(0),spaceA};
		userAndGroup.selectGroup(arrayGroupPath);
		userAndGroup.removeUser(arrayUsers.get(0));
	}
	/**
	 * Delete all users that are created in testing process
	 */
	public void deleteAllUsers(){
		if(arrayUsers.size()>0){
			navTool.goToUsersAndGroupsManagement();
			userAndGroup.goToUsersTab();
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
		String membership = membershipData.getContentByIndex(0);
		userAndGroup.selectGroup(arrayGroupPath);
		userAndGroup.addUsersToGroup(user,membership,false,true);
	}
}