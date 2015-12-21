package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ConnectionsManagement;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ActivityStream;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.objectdatabase.chat.ChatStatusDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.MailSuffixDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.UserInfoDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.ConnectStatusDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.NotificationDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.ProfileContactIMDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.ProfileContactPhoneDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.ActivityMessageDatabase;
import org.exoplatform.selenium.platform.social.EmailNotifications;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting;
import org.exoplatform.selenium.platform.social.UserPageBase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.exoplatform.selenium.platform.social.IntranetNotification;
import org.exoplatform.selenium.platform.gatein.PageEditor;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.objectdatabase.common.LinksDatabase;
import org.exoplatform.selenium.platform.objectdatabase.ecms.SiteExplorerDriveDatabase;
import org.exoplatform.selenium.platform.objectdatabase.ecms.SiteExplorerPathDatabase;
import org.exoplatform.selenium.platform.social.UserProfilePage;
import org.exoplatform.selenium.platform.chat.ChatStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SOC_TestConfig_2 extends PlatformBase {
	HomePagePlatform hp;
	MailSuffixDatabase mailSuffixData;

	ActivityStream hpAct;
	ManageLogInOut magAc;
	UserProfilePage myProfile;
	UserPageBase uBase;
	IntranetNotification intraNot;
	MyNotificationsSetting myNotifPage;

	NavigationToolbar navTool;
	AttachmentFileDatabase atData;
	TextBoxDatabase txData;
	LinksDatabase lnkData;
	ChatStatusDatabase chatStatus;
	UserDatabase userData;
	UserInfoDatabase userInfData;

	SiteExplorerPathDatabase siteExPath;
	NotificationDatabase notiIntranetData;
	SiteExplorerDriveDatabase siteExDrive;

	ConnectionsManagement connMag;
	UserAddManagement addUserPage;
	UserAndGroupManagement userAndGroup;
	
	
	ProfileContactIMDatabase contactIM;
	ProfileContactPhoneDatabase contactPhone;
	ActivityMessageDatabase activityMes;
	ConnectStatusDatabase conStatus;

	ChatStatus chat;
	PageEditor pagEditor;
	Button button;

	EmailNotifications notiEmail;
	String password;
	
	@BeforeMethod
	public void setUpBeforeMethod() throws Exception{
		info("Start setUpBeforeMethod");
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		magAc = new ManageLogInOut(driver);
		magAc.signIn(USER_ROOT, PASS_ROOT);
		myProfile = new UserProfilePage(driver);
		uBase = new UserPageBase(driver);
		navTool = new NavigationToolbar(driver);
		connMag = new ConnectionsManagement(driver);
		chat = new ChatStatus(driver);
		pagEditor = new PageEditor(driver);
		hp = new HomePagePlatform(driver);
		intraNot = new IntranetNotification(driver);
		button = new Button(driver);
		pagEditor = new PageEditor(driver);
		myNotifPage = new MyNotificationsSetting(driver);
		notiEmail = new EmailNotifications(driver);
		
		atData = new AttachmentFileDatabase();
		atData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

		hpAct = new ActivityStream(driver);

		lnkData = new LinksDatabase();
		lnkData.setLinkData(linkPath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		userData = new UserDatabase();
		userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		userInfData = new UserInfoDatabase();
		userInfData.setUserInfoData(userInfoFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		siteExDrive = new SiteExplorerDriveDatabase();
		siteExDrive.setSiteExpDriveData(siteExpDrivePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		siteExPath = new SiteExplorerPathDatabase();
		siteExPath.setSiteExpPathData(siteExpPathPath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

		addUserPage = new UserAddManagement(driver);
		userAndGroup = new UserAndGroupManagement(driver);

		mailSuffixData = new MailSuffixDatabase();
		mailSuffixData.setMailSuffixData(mailSuffixFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		contactIM = new ProfileContactIMDatabase();
		contactPhone = new ProfileContactPhoneDatabase();
		contactIM.setProfileIMData(contactIMFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		contactPhone.setContactPhoneData(contactPhoneFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		activityMes = new ActivityMessageDatabase();
		activityMes.setActivityMessageData(activityMesFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		conStatus = new ConnectStatusDatabase();
		conStatus.setConStatusData(conStatusFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		chatStatus = new ChatStatusDatabase();
		chatStatus.setChatStatusData(chatStatusFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		notiIntranetData = new NotificationDatabase();
		notiIntranetData.setData(notiIntranetFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		siteExPath = new SiteExplorerPathDatabase();
		siteExPath.setSiteExpPathData(siteExpPathPath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		password="123456";
		/*info("Enable like and new user notifications");
		navTool.goToMyNotifications();
		myNotifPage.enableNotification(myNotiType.NewUser_intranet);
		myNotifPage.enableNotification(myNotiType.AS_Like_intranet);*/
		
		info("End setUpBeforeMethod");
	}

	@AfterMethod
	public void afterMethod(){
		info("Start setUpBeforeMethod");
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End setUpBeforeMethod");
	}

}
