package org.exoplatform.selenium.platform.wildcard.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.administration.ContentAdministration;
import org.exoplatform.selenium.platform.administration.ContentAdministration.mainEcmFunctions;
import org.exoplatform.selenium.platform.administration.ContentAdministration.specificEcmFunctions;
import org.exoplatform.selenium.platform.ecms.CreateNewDocument;
import org.exoplatform.selenium.platform.ecms.ECMS_Permission;
import org.exoplatform.selenium.platform.ecms.SiteExplorerHome;
import org.exoplatform.selenium.platform.ecms.CreateNewDocument.selectDocumentType;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.social.SpaceManagement;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Wildcard_TestConfig_2 extends PlatformBase {
	
	HomePagePlatform hp;
	ManageLogInOut magAc;
	SpaceManagement spManag;
	NavigationToolbar navTool;

	ContentAdministration caPage;
	SiteExplorerHome SEHome;
	CreateNewDocument CreNewDoc;
	ECMS_Permission EcmsPerm;
	
	TextBoxDatabase txData;
	
	ArrayList<String> arrayUsers;
	UserAddManagement addUserPage;
	UserAndGroupManagement userAndGroup;
	
	String groupA;
	String password;
	
	@BeforeMethod
	public void setUpBeforeMethod() throws Exception{
		info("Start setUpBeforeClass");
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		magAc = new ManageLogInOut(driver);
		SEHome = new SiteExplorerHome(driver);
		CreNewDoc = new CreateNewDocument(driver);
		caPage = new ContentAdministration(driver);
		navTool = new NavigationToolbar(driver);
		EcmsPerm = new ECMS_Permission(driver);
		hp = new HomePagePlatform(driver);
		
		addUserPage = new UserAddManagement(driver);
		userAndGroup = new UserAndGroupManagement(driver);
		
		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		
		magAc.signIn(DATA_USER1, DATA_PASS);
		groupA = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		password="123456";
		info("Create new user");
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		addGroup(groupA);
		addUserToGroup(arrayUsers.get(0),groupA);
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
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		navTool.goToUsersAndGroupsManagement();
		if(arrayUsers.size()>0){
			userAndGroup.deleteAllUsers(arrayUsers);
		}
	}
	/**
	 * Create new file
	 * @param title
	 * @param content
	 */
	public void createNewFile(String title,String content){
		SEHome.goToAddNewContent();
		info("Create new file document");
		Utils.pause(1000);
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(title, content);
		CreNewDoc.saveAndClose();
		Utils.pause(3000);
	}
	/**
	 * delete category
	 */
	public void deleteCategory(String name){
		navTool.goToContentAdministration();
		caPage.goToSpecificMainFunctions(mainEcmFunctions.ADVANCED);
		caPage.goToSpecificFunctions(specificEcmFunctions.CATEGORIES);
		caPage.deleteCategories(name);
	}
}