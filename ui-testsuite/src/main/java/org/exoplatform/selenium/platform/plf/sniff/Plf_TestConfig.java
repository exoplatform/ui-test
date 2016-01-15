package org.exoplatform.selenium.platform.plf.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.Branding;
import org.exoplatform.selenium.platform.ConnectionsManagement;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ActivityStream;
import org.exoplatform.selenium.platform.IDEManagement;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.QuickSearchResult;
import org.exoplatform.selenium.platform.administration.ContentSearchAdministration;
import org.exoplatform.selenium.platform.answer.AnswerHomePage;
import org.exoplatform.selenium.platform.answer.QuestionManagement;
import org.exoplatform.selenium.platform.calendar.EventManagement;
import org.exoplatform.selenium.platform.calendar.TaskManagement;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage;
import org.exoplatform.selenium.platform.calendar.CalendarManagement;
import org.exoplatform.selenium.platform.ecms.SiteExplorerHome;
import org.exoplatform.selenium.platform.forum.ForumCategoryManagement;
import org.exoplatform.selenium.platform.forum.ForumHomePage;
import org.exoplatform.selenium.platform.forum.ForumForumManagement;
import org.exoplatform.selenium.platform.forum.ForumTopicManagement;
import org.exoplatform.selenium.platform.gatein.AnswerPage;
import org.exoplatform.selenium.platform.gatein.ApplicationRegistry;
import org.exoplatform.selenium.platform.gatein.GadgetManagement;
import org.exoplatform.selenium.platform.gatein.NavigationManagement;
import org.exoplatform.selenium.platform.gatein.PortalManageSites;
import org.exoplatform.selenium.platform.gatein.PageCreationWizard;
import org.exoplatform.selenium.platform.gatein.PageEditor;
import org.exoplatform.selenium.platform.gatein.PortalManagePages;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.MailSuffixDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.ApplicationGateinDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.RemoteGadgetDatabase;
import org.exoplatform.selenium.platform.objectdatabase.plf.GettingStartedDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.SpaceApplicationDatabase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.exoplatform.selenium.platform.social.UserPageBase;
import org.exoplatform.selenium.platform.social.UserProfilePage;
import org.exoplatform.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.selenium.platform.social.SpaceHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.wiki.SourceTextEditor;
import org.exoplatform.selenium.platform.wiki.WikiHomePage;
import org.exoplatform.selenium.platform.wiki.WikiManagement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;


public class Plf_TestConfig extends PlatformBase {

	ApplicationRegistry appHP;
	AnswerHomePage aHome;
	AnswerPage aPage;
	Actions action;
	
	Branding branding;
	Button but;
	
	CalendarHomePage calHome;
	CalendarManagement calMg;
	ConnectionsManagement connMg;
	
	EventManagement eventMag;
	
	IDEManagement ide;
	GadgetManagement gadMag;
	
	ForumHomePage forumHP;
	ForumTopicManagement foTopic;
	ForumForumManagement forumMg;
	ForumCategoryManagement forumCatMag;
	
	PortalManageSites magSite;
	NavigationManagement navMag;
	ManageLogInOut magAc;
	UserProfilePage myProfile;
	UserPageBase uBase;
	ManageAlert alert;
	
	NavigationToolbar navToolBar;
	
	ActivityStream hpAct;
	HomePagePlatform hp;
	
	TaskManagement taskMag;
	
	SiteExplorerHome SEHome;
	SpaceManagement spaceMg;
	SpaceHomePage spaceHome;
	SpaceSettingManagement setMag;
	ContentSearchAdministration seaAdmin;
	
	PageEditor pagEditor;
	PortalManagePages pagMang;
	PageCreationWizard pagCW;
	
	QuickSearchResult quickSearch;
	QuestionManagement qMang;
	
	WikiHomePage wikiHome;
	WikiManagement wikiMag;
	SourceTextEditor sourceEditor;
	
	TextBoxDatabase txData;
	UserDatabase userData;
	RemoteGadgetDatabase remoteGadData;
	ApplicationGateinDatabase appGateData;
	GettingStartedDatabase getStartData;
	AttachmentFileDatabase fData;
	SpaceApplicationDatabase spAppData;
	MailSuffixDatabase mailSuffixData;
	UserAddManagement addUserPage;
	UserAndGroupManagement userAndGroup;
	@BeforeClass
	public void setUpBeforeClass() throws Exception{
		info("Start setUpBeforeClass");
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		magAc = new ManageLogInOut(driver);
		magAc.signIn(USER_ROOT,PASS_ROOT);
		
		hp = new HomePagePlatform(driver);
		navToolBar = new NavigationToolbar(driver);
		ide = new IDEManagement(driver);
		branding = new Branding(driver);
		but = new Button(driver);
		
		appHP = new ApplicationRegistry(driver);
		aPage = new AnswerPage(driver);
		aHome = new AnswerHomePage(driver);
		action = new Actions(driver);
		navMag = new NavigationManagement(driver);
		
		gadMag = new GadgetManagement(driver);
		
		magSite = new PortalManageSites(driver);
		alert = new ManageAlert(driver);
		
		pagCW =new PageCreationWizard(driver);
		pagMang = new PortalManagePages(driver);
		pagEditor = new PageEditor(driver);
		
		hpAct = new ActivityStream(driver);
		myProfile = new UserProfilePage(driver);
		uBase = new UserPageBase(driver);
		
		SEHome = new SiteExplorerHome(driver);
		spaceHome = new SpaceHomePage(driver);
		spaceMg = new SpaceManagement(driver);
		seaAdmin= new ContentSearchAdministration(driver);
		setMag = new SpaceSettingManagement(driver);
		
		forumHP = new ForumHomePage(driver);
		forumMg = new ForumForumManagement(driver);
		forumCatMag = new ForumCategoryManagement(driver);
		foTopic=new ForumTopicManagement(driver);
		
		calHome=new CalendarHomePage(driver);
		calMg = new CalendarManagement(driver);
		qMang = new QuestionManagement(driver);
		quickSearch = new QuickSearchResult(driver);
		connMg = new ConnectionsManagement(driver);
		
		//userProfile=new UserProfile(driver);
		wikiHome=new WikiHomePage(driver);
		wikiMag = new WikiManagement(driver);
		sourceEditor = new SourceTextEditor(driver);
		eventMag = new EventManagement(driver);
		taskMag = new TaskManagement(driver);
		
		addUserPage = new UserAddManagement(driver);
		userAndGroup = new UserAndGroupManagement(driver);

		txData = new TextBoxDatabase();
		userData = new UserDatabase();
		fData = new AttachmentFileDatabase();
		remoteGadData = new RemoteGadgetDatabase();
		mailSuffixData = new MailSuffixDatabase();
		mailSuffixData.setMailSuffixData(mailSuffixFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		appGateData = new ApplicationGateinDatabase();
		userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		fData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		remoteGadData.setRemoteGadgetData(remoteGadgetDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		appGateData.setApplicationGateinData(appGateinDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		getStartData = new GettingStartedDatabase();
		getStartData.setGettingStartedData(getStartFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		spAppData = new SpaceApplicationDatabase();
		spAppData.setAppData(spaceappFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		info("End setUpBeforeClass");
		
	}
	
	@AfterClass
	public void afterTest(){
		info("Start setUpBeforeClass");
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End setUpBeforeClass");
	}

	
}