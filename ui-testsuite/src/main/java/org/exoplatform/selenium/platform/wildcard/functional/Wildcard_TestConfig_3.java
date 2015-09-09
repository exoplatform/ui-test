package org.exoplatform.selenium.platform.wildcard.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;
import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.forum.ForumCategoryManagement;
import org.exoplatform.selenium.platform.forum.ForumForumManagement;
import org.exoplatform.selenium.platform.forum.ForumHomePage;
import org.exoplatform.selenium.platform.forum.ForumTopicManagement;
import org.exoplatform.selenium.platform.forum.PrivateMessageManagement;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Wildcard_TestConfig_3 extends PlatformBase {
	
	HomePagePlatform hp;
	ManageLogInOut magAc;
	NavigationToolbar navTool;

	ForumCategoryManagement forumCatMag;
	ForumHomePage forumHP;
	ForumForumManagement forumMag;
	PrivateMessageManagement msgManage;
	ForumTopicManagement foTopic;
	TextBoxDatabase txData;
	
	ArrayList<String> arrayUsers;
	UserAddManagement addUserPage;
	UserAndGroupManagement userAndGroup;
	
	String groupA;
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
		
		msgManage = new PrivateMessageManagement(driver);
		forumCatMag = new ForumCategoryManagement(driver);
		forumMag = new ForumForumManagement(driver);
		forumHP = new ForumHomePage(driver);
		foTopic = new ForumTopicManagement(driver);
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
	 * Add category forum,topic
	 */
	public void addCatForumTopic(String cat,String forum,String topic){
		info("Add a category");
		forumCatMag.addCategorySimple(cat,"",cat);
		forumMag.addForumSimple(forum,"",forum);
		forumMag.goToStartTopic();
		foTopic.startTopic(topic, topic,"","");
	}
	/**
	 * Delete category
	 */
	public void deleteCategory(String cat){
		info("Delete category");
		forumHP.goToHomeCategory();
		forumCatMag.deleteCategory(cat);
	}
}