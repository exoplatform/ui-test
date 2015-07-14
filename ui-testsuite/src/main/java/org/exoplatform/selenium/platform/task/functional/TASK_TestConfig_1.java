package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ActivityStream;
import org.exoplatform.selenium.platform.ConnectionsManagement;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.gatein.ApplicationRegistry;
import org.exoplatform.selenium.platform.gatein.MyDashBoard;
import org.exoplatform.selenium.platform.gatein.PageCreationWizard;
import org.exoplatform.selenium.platform.gatein.PortalManagePages;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.LanguageDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.LinksDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.MailSuffixDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.ApplicationGateinDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.ApplicationLayoutDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.ContainersDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.GateinPortalMemberShipsPermissionDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.RemoteGadgetDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.SpaceApplicationDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.SpaceGUIDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.SpaceGroupsDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.SpaceNavigationDefaultNodesDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.SpaceRegistrationDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.SpaceVisibilityDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.SpaceWarningMessageDatabase;
import org.exoplatform.selenium.platform.objectdatabase.task.GroupByDatabase;
import org.exoplatform.selenium.platform.objectdatabase.task.SortByDatabase;
import org.exoplatform.selenium.platform.objectdatabase.task.WelcomeMessageDatabase;
import org.exoplatform.selenium.platform.social.SpaceHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.selenium.platform.social.UserPageBase;
import org.exoplatform.selenium.platform.social.UserProfilePage;
import org.exoplatform.selenium.platform.task.ManagementLabels;
import org.exoplatform.selenium.platform.task.ManagementProjects;
import org.exoplatform.selenium.platform.task.ManagementTasks;
import org.exoplatform.selenium.platform.task.TaskManagementHome;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TASK_TestConfig_1 extends PlatformBase {
	HomePagePlatform hp;
	ActivityStream hpAct;
	ManageLogInOut magAc;
	Button button;
	PortalManagePages portManagePage;
	PageCreationWizard pgCreateWiz;

	MyDashBoard myDash;
	NavigationToolbar navTool;

	SpaceManagement spaMg;
	SpaceHomePage spaHome;
	SpaceSettingManagement setSpaceMg;
	UserAddManagement userManage;
	UserAndGroupManagement userGroupMg;
	
	TaskManagementHome taskMgHome;
	ManagementProjects mgProject;
	ManagementLabels mgLabel;
	ManagementTasks mgTask;
	
	ApplicationRegistry appReg;
	ApplicationLayoutDatabase appLayData;
	ApplicationGateinDatabase appGateData;

	SpaceGUIDatabase spaceUI;
	SpaceApplicationDatabase spAppData;
	SpaceNavigationDefaultNodesDatabase spaceDefaultNodesData;
	SpaceVisibilityDatabase spVisiData;
	SpaceRegistrationDatabase spRegisData;
	SpaceGroupsDatabase spGroupsData;
	SpaceWarningMessageDatabase  spWarnMessg;
	GateinPortalMemberShipsPermissionDatabase membershipData;

	RemoteGadgetDatabase remoteGadData;
	TextBoxDatabase txData;
	LinksDatabase lnkData;
	AttachmentFileDatabase atData;

	ContainersDatabase containerData;
	LanguageDatabase langData;

	ConnectionsManagement connMag;
	UserAddManagement addUserPage;

	MailSuffixDatabase mailSuffixData;

	UserPageBase uBase;
	UserProfilePage myProfile;
	
	WelcomeMessageDatabase welcomeMesData;
	GroupByDatabase groupByData;
	SortByDatabase sortByData;
	
	@BeforeMethod
	public void setUpBeforeMethod() throws Exception{
		info("Start setUpBeforeMethod");
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		magAc = new ManageLogInOut(driver);
		button = new Button(driver);
		magAc.signIn(DATA_USER1, DATA_PASS);
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
		taskMgHome = new TaskManagementHome(driver);
		mgLabel = new ManagementLabels(driver);
		mgProject = new ManagementProjects(driver);
		mgTask = new ManagementTasks(driver);
		
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
		
		welcomeMesData = new WelcomeMessageDatabase();
		welcomeMesData.setWelcomeMessageData(welcomeMesFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		sortByData = new SortByDatabase();
		sortByData.setSortByData(sortByFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		groupByData = new GroupByDatabase();
		groupByData.setGroupByData(groupByFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
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



