package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.ConnectionsManagement;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ActivityStream;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.objectdatabase.common.MailSuffixDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.UserInfoDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.NotificationDatabase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.exoplatform.selenium.platform.social.IntranetNotification;
import org.exoplatform.selenium.platform.social.NotificationActivity;
import org.exoplatform.selenium.platform.social.NotificationsAdminSeting;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting;
import org.exoplatform.selenium.platform.social.EmailNotifications;
import org.exoplatform.selenium.platform.social.SpaceHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.selenium.platform.social.UserPageBase;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.social.UserProfilePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SOC_TestConfig_3 extends PlatformBase {
	HomePagePlatform hp;
	MailSuffixDatabase mailSuffixData;
	ActivityStream hpAct;
	ManageLogInOut magAc;
	UserProfilePage myProfile;
	IntranetNotification intraNot;
	UserPageBase userPage;

	NavigationToolbar navTool;

	SpaceManagement spaMg;
	SpaceHomePage spaHome;
	TextBoxDatabase txData;
	UserDatabase userData;
	UserInfoDatabase userInfoData;

	NotificationDatabase notiIntranetData;

	ConnectionsManagement connMag;
	SpaceSettingManagement setSpaceMg;
	UserAddManagement addUserPage;
	UserAndGroupManagement userAndGroup;
	NotificationsAdminSeting emailNotif;
	MyNotificationsSetting myNotifPage;
	UserProfilePage myProfil;
	NotificationDatabase notiEmailData;
	
	NotificationActivity notAct;
	EmailNotifications emailNot;
	
	String password;
	
	@BeforeMethod
	public void setUpBeforeMethod() throws Exception{
		info("Start setUpBeforeMethod");
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		magAc = new ManageLogInOut(driver);
		magAc.signIn(USER_ROOT, PASS_ROOT);
		myProfile = new UserProfilePage(driver);
		navTool = new NavigationToolbar(driver);
		connMag = new ConnectionsManagement(driver);
		setSpaceMg = new SpaceSettingManagement(driver);
		emailNotif = new NotificationsAdminSeting(driver);
		hp = new HomePagePlatform(driver);
		intraNot = new IntranetNotification(driver);
		emailNot = new EmailNotifications(driver);
		notAct = new NotificationActivity(driver);
		userPage = new UserPageBase(driver);
		
		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

		notiEmailData = new NotificationDatabase();
		notiEmailData.setData(notiEmailFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		hpAct = new ActivityStream(driver);
		spaMg = new SpaceManagement(driver);
		spaHome = new SpaceHomePage(driver);

		userData = new UserDatabase();
		userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		addUserPage = new UserAddManagement(driver);
		userAndGroup = new UserAndGroupManagement(driver);
		myProfil = new UserProfilePage(driver);
		myNotifPage= new MyNotificationsSetting(driver);

		mailSuffixData = new MailSuffixDatabase();
		mailSuffixData.setMailSuffixData(mailSuffixFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		notiIntranetData = new NotificationDatabase();
		notiIntranetData.setData(notiIntranetFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		userInfoData = new UserInfoDatabase();
		userInfoData.setUserInfoData(userInfoFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		password="123456";
		
		info("End setUpBeforeMethod");
	}

	@AfterMethod
	public void afterMethod(){
		info("Start setUpAfterMethod");
		isDriver=true;
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End setUpAfterMethod");
	}

}
