package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ActivityStream;
import org.exoplatform.selenium.platform.ConnectionsManagement;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.calendar.EventManagement;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.LinksDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.MailSuffixDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.UserInfoDatabase;
import org.exoplatform.selenium.platform.objectdatabase.ecms.SiteExplorerDriveDatabase;
import org.exoplatform.selenium.platform.objectdatabase.ecms.SiteExplorerPathDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.NotificationDescriptionDatabase;
import org.exoplatform.selenium.platform.social.AllNotificationPage;
import org.exoplatform.selenium.platform.social.IntranetNotification;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting;
import org.exoplatform.selenium.platform.social.NotificationsAdminSeting;
import org.exoplatform.selenium.platform.social.SpaceHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.selenium.platform.social.UserProfilePage;
import org.exoplatform.selenium.platform.wiki.WikiHomePage;
import org.exoplatform.selenium.platform.wiki.WikiManagement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SOC_TestConfig2 extends PlatformBase {
	
	ManageLogInOut magAc;
	Button button;
	
	NavigationToolbar navTool;
	NotificationsAdminSeting notiAdmin;
	MyNotificationsSetting myNoti;
	AllNotificationPage allNotiPag;
	HomePagePlatform hp;
	ActivityStream hpAct;
	IntranetNotification intraNot;
	
	WikiHomePage wHome;
	WikiManagement wikiMg;
	
	SpaceManagement spaMg;
	SpaceHomePage spaHome;
	SpaceSettingManagement setSpaceMg;
	EventManagement evMg;
	
	TextBoxDatabase txData;
	UserInfoDatabase userInfoData;
	UserAddManagement addUserPage;
	UserAndGroupManagement userAndGroup;
	UserProfilePage userProPage;
	ConnectionsManagement connMag;
	NotificationDescriptionDatabase notiDesData;
	LinksDatabase linkData;
	AttachmentFileDatabase attFileData;
	SiteExplorerDriveDatabase siteExDrive;
	SiteExplorerPathDatabase siteExPath;
	MailSuffixDatabase mailSuffixData;
	
	
	String password;
	ArrayList<String> arrayUser;
	ArrayList<String> comments;
	
	
	@BeforeMethod
	public void setUpBeforeMethod() throws Exception{
		info("Start setUpBeforeMethod");
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		magAc = new ManageLogInOut(driver);
		button = new Button(driver);
		magAc.signIn(DATA_USER1, DATA_PASS);
		
		navTool = new NavigationToolbar(driver);
		notiAdmin = new NotificationsAdminSeting(driver);
		myNoti = new MyNotificationsSetting(driver);
		addUserPage = new UserAddManagement(driver);
		userAndGroup = new UserAndGroupManagement(driver);
		userProPage = new UserProfilePage(driver);
		
		
		hp = new HomePagePlatform(driver);
		connMag = new ConnectionsManagement(driver);
		hpAct = new ActivityStream(driver);
		intraNot = new IntranetNotification(driver);
		wHome=new WikiHomePage(driver);
		wikiMg = new WikiManagement(driver);
		
		spaHome = new SpaceHomePage(driver);
		spaMg = new SpaceManagement(driver);
		setSpaceMg = new SpaceSettingManagement(driver);
		evMg = new EventManagement(driver);
		
		userInfoData = new UserInfoDatabase();
		userInfoData.setUserInfoData(userInfoFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		notiDesData = new NotificationDescriptionDatabase();
		notiDesData.setData(notiDesFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		linkData = new LinksDatabase();
		linkData.setLinkData(linkPath, defaultSheet, isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		attFileData = new AttachmentFileDatabase();
		attFileData.setAttachFileData(attachmentFilePath, defaultSheet, isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		siteExDrive = new SiteExplorerDriveDatabase();
		siteExDrive.setSiteExpDriveData(siteExpDrivePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		siteExPath = new SiteExplorerPathDatabase();
		siteExPath.setSiteExpPathData(siteExpPathPath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

		mailSuffixData = new MailSuffixDatabase();
		mailSuffixData.setMailSuffixData(mailSuffixFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		comments = new ArrayList<String>();
		
		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		info("End setUpBeforeMethod");
	}

	@AfterMethod
	public void afterMethod(){
		info("Start afterMethod");
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End afterMethod");
	}
	
	/**
	 * Create many users
	 * @param number
	 *               is the number of users that want to create
	 */
	public void createNewUser(int number){
		arrayUser  = new ArrayList<String>();
		navTool.goToAddUser();
		for(int i=0;i<number;i++){
			info("Add new a user");
			String user=txData.getContentByArrayTypeRandom(7)+getRandomString();
			password ="123456" ;
			String email=user+"@gmail.com";
			addUserPage.addUser(user,password, email,user,user);
			info("Add users to user's array");
			arrayUser.add(user);
		}
	}
	
	/**
	 * Delete many users
	 * @param users
	 *             is array of users
	 */
	public void deleteUsers(ArrayList<String> users){
		navTool.goToUsersAndGroupsManagement();
		for(int i=0;i<users.size();i++){
			info("Delete user:"+users.get(i));
			userAndGroup.deleteUser(users.get(i));
			info("Delete user:"+users.get(i)+" successfully");
		}
	}

}
