package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.ActivityStream;
import org.exoplatform.selenium.platform.ConnectionsManagement;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.objectdatabase.social.ActivityMessageDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.ConnectStatusDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.NotificationDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.ProfileContactIMDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.ProfileContactPhoneDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.SpaceApplicationDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.SpaceGUIDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.SpaceNavigationDefaultNodesDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.SpaceGroupsDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.SpaceRegistrationDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.SpaceVisibilityDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.SpaceWarningMessageDatabase;
import org.exoplatform.selenium.platform.social.IntranetNotification;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting;
import org.exoplatform.selenium.platform.social.NotificationActivity;
import org.exoplatform.selenium.platform.social.NotificationsAdminSeting;
import org.exoplatform.selenium.platform.social.EmailNotifications;
import org.exoplatform.selenium.platform.social.UserProfilePage;
import org.exoplatform.selenium.platform.social.AddUsers;
import org.exoplatform.selenium.platform.social.SpaceHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.selenium.platform.social.UserPageBase;
import org.exoplatform.selenium.platform.wiki.SourceTextEditor;
import org.exoplatform.selenium.platform.wiki.WikiHomePage;
import org.exoplatform.selenium.platform.wiki.WikiManagement;
import org.exoplatform.selenium.platform.calendar.EventManagement;
import org.exoplatform.selenium.platform.chat.ChatStatus;
import org.exoplatform.selenium.platform.ecms.CreateNewDocument;
import org.exoplatform.selenium.platform.ecms.SiteExplorerHome;
import org.exoplatform.selenium.platform.forum.ForumTopicManagement;
import org.exoplatform.selenium.platform.gatein.ApplicationRegistry;
import org.exoplatform.selenium.platform.gatein.MyDashBoard;
import org.exoplatform.selenium.platform.gatein.PageCreationWizard;
import org.exoplatform.selenium.platform.gatein.PortalManagePages;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.objectdatabase.chat.ChatStatusDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.LanguageDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.LinksDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.MailSuffixDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.ecms.SiteExplorerDriveDatabase;
import org.exoplatform.selenium.platform.objectdatabase.ecms.SiteExplorerPathDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.ApplicationGateinDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.ApplicationLayoutDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.ContainersDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.GateinPortalMemberShipsPermissionDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.RemoteGadgetDatabase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SOC_TestConfig extends PlatformBase {
	
	//Platform
	ManageLogInOut magAc;
	Button button;
	ManageAlert alert;
	
	//Activity
	HomePagePlatform hp;
	ActivityStream hpAct;
	
	//Navigation 
	NavigationToolbar navTool;
	MyDashBoard myDash;
	
	//PLF
	SiteExplorerHome SEHome;
	CreateNewDocument creatDoc;
	
	//Page managements
	PortalManagePages portManagePage;
	PageCreationWizard pgCreateWiz;

	//Social
	SpaceManagement spaMg;
	SpaceHomePage spaHome;
	SpaceSettingManagement setSpaceMg;
	SpaceGUIDatabase spaceUI;
	SpaceApplicationDatabase spAppData;
	SpaceNavigationDefaultNodesDatabase spaceDefaultNodesData;
	SpaceVisibilityDatabase spVisiData;
	SpaceRegistrationDatabase spRegisData;
	SpaceGroupsDatabase spGroupsData;
	SpaceWarningMessageDatabase  spWarnMessg;
	ForumTopicManagement topicMg;
	
	//Notifications
	NotificationsAdminSeting emailNotif;
	MyNotificationsSetting myNotifPage;
	EmailNotifications notiEmail;
	IntranetNotification intraNot;
	NotificationActivity notActivity;
	
	//User management
	UserAddManagement userManage;
	UserAndGroupManagement userGroupMg;
	ConnectionsManagement connMag;
	UserAddManagement addUserPage;
	UserPageBase uBase;
	UserProfilePage myProfile;

	//Wiki 
	WikiHomePage wHome;
	WikiManagement wikiMg;
	SourceTextEditor sourceEditor;

	//Calendar
	EventManagement evMg;

	//Gatein
	ApplicationRegistry appReg;
	ApplicationLayoutDatabase appLayData;
	ApplicationGateinDatabase appGateData;
	GateinPortalMemberShipsPermissionDatabase membershipData;

	//ObjectDatabase
	RemoteGadgetDatabase remoteGadData;
	TextBoxDatabase txData;
	LinksDatabase lnkData;
	AttachmentFileDatabase atData;
	ContainersDatabase containerData;
	LanguageDatabase langData;
	MailSuffixDatabase mailSuffixData;
	ProfileContactIMDatabase contactIM;
	ProfileContactPhoneDatabase contactPhone;
	ActivityMessageDatabase activityMes;
	ConnectStatusDatabase conStatus;
	SiteExplorerPathDatabase siteExPath;
	SiteExplorerDriveDatabase siteExDrive;
	NotificationDatabase notiIntranetData;
	
	ChatStatusDatabase chatStatus;
	ChatStatus chat;
	AddUsers addUser;

	
	@BeforeMethod
	public void setUpBeforeMethod() throws Exception{
		info("Start setUpBeforeMethod");
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		magAc = new ManageLogInOut(driver);
		button = new Button(driver);
		magAc.signIn(USER_ROOT,PASS_ROOT);
		myProfile = new UserProfilePage(driver);
		uBase = new UserPageBase(driver);
		navTool = new NavigationToolbar(driver);
		setSpaceMg = new SpaceSettingManagement(driver);
		hp = new HomePagePlatform(driver);
		hpAct = new ActivityStream(driver);
		myDash = new MyDashBoard(driver);
		spaMg = new SpaceManagement(driver);
		spaHome = new SpaceHomePage(driver);
		appReg = new ApplicationRegistry(driver);
		portManagePage = new PortalManagePages(driver);
		pgCreateWiz = new PageCreationWizard(driver);

		userManage = new UserAddManagement(driver);

		topicMg = new ForumTopicManagement(driver);

		wikiMg = new WikiManagement(driver);
		wHome = new WikiHomePage(driver);
		sourceEditor = new SourceTextEditor(driver);

		SEHome = new SiteExplorerHome(driver);
		creatDoc = new CreateNewDocument(driver);
		userGroupMg = new UserAndGroupManagement(driver);
		notActivity = new NotificationActivity(driver);

		evMg = new EventManagement(driver);

		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

		atData = new AttachmentFileDatabase();
		atData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

		spAppData = new SpaceApplicationDatabase();
		spAppData.setAppData(spaceappFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

		remoteGadData = new RemoteGadgetDatabase();
		remoteGadData.setRemoteGadgetData(remoteGadgetDataFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

		appLayData = new ApplicationLayoutDatabase();
		appLayData.setApplicationLayoutData(appLayoutFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

		notiIntranetData = new NotificationDatabase();
		notiIntranetData.setData(notiIntranetFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		
		spaceUI = new SpaceGUIDatabase();
		spaceUI.setData(spaceUIFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		lnkData = new LinksDatabase();
		lnkData.setLinkData(linkPath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		containerData = new ContainersDatabase();
		containerData.setContainersData(containerFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		langData = new LanguageDatabase();
		langData.setLanguageData(languageFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		appGateData = new ApplicationGateinDatabase();
		appGateData.setApplicationGateinData(appGateinDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		spaceDefaultNodesData = new SpaceNavigationDefaultNodesDatabase();
		spaceDefaultNodesData.setSpaceNavigationDefaultNodes(spaceNavigationDefaultNodesFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);	

		spVisiData = new SpaceVisibilityDatabase();
		spVisiData.setSpaceVisibleData(spaceVisibleFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		spRegisData = new SpaceRegistrationDatabase();
		spRegisData.setSpaceRegistrationData(spaceRegistrationFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		spGroupsData = new SpaceGroupsDatabase();
		spGroupsData.setData(spaceGroupsFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		membershipData = new GateinPortalMemberShipsPermissionDatabase();
		membershipData.setData(portalPermisMemFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		spWarnMessg = new SpaceWarningMessageDatabase();
		spWarnMessg.setData(spaceWarnMessageFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		addUserPage = new UserAddManagement(driver);
		connMag = new ConnectionsManagement(driver);

		mailSuffixData = new MailSuffixDatabase();
		mailSuffixData.setMailSuffixData(mailSuffixFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		activityMes = new ActivityMessageDatabase();
		activityMes.setActivityMessageData(activityMesFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

		contactIM = new ProfileContactIMDatabase();
		contactPhone = new ProfileContactPhoneDatabase();
		contactIM.setProfileIMData(contactIMFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		contactPhone.setContactPhoneData(contactPhoneFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

		alert = new ManageAlert(driver, plfVersion);
		
		siteExDrive = new SiteExplorerDriveDatabase();
		siteExPath = new SiteExplorerPathDatabase();

		siteExDrive.setSiteExpDriveData(siteExpDrivePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		siteExPath.setSiteExpPathData(siteExpPathPath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

		conStatus = new ConnectStatusDatabase();
		conStatus.setConStatusData(conStatusFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

		chatStatus = new ChatStatusDatabase();
		chatStatus.setChatStatusData(chatStatusFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		chat = new ChatStatus(driver);
		
		
		emailNotif = new NotificationsAdminSeting(driver);
		myNotifPage= new MyNotificationsSetting(driver);
		intraNot = new IntranetNotification(driver);
		notiEmail = new EmailNotifications(driver);
		addUser = new AddUsers(driver);
		
		info("End setUpBeforeMethod");
	}

	@AfterMethod
	public void afterMethod(){
		info("Start afterMethod");
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End afterMethod");
	}

}
