package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ConnectionsManagement;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ActivityStream;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.SpaceApplicationDatabase;
import org.exoplatform.selenium.platform.social.EmailNotifications;
import org.exoplatform.selenium.platform.social.SpaceHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.selenium.platform.wiki.SourceTextEditor;
import org.exoplatform.selenium.platform.wiki.WikiHomePage;
import org.exoplatform.selenium.platform.wiki.WikiManagement;
import org.exoplatform.selenium.platform.ecms.CreateNewDocument;
import org.exoplatform.selenium.platform.ecms.SiteExplorerHome;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.objectdatabase.common.LinksDatabase;
import org.exoplatform.selenium.platform.objectdatabase.ecms.SiteExplorerDriveDatabase;
import org.exoplatform.selenium.platform.objectdatabase.ecms.SiteExplorerPathDatabase;
import org.exoplatform.selenium.platform.social.UserProfilePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SOC_TestConfig_1 extends PlatformBase {
	HomePagePlatform hp;
	WikiHomePage wHome;
	WikiManagement wikiMg;
	SourceTextEditor sourceEditor;

	SpaceApplicationDatabase spAppData;
	ActivityStream hpAct;
	ManageLogInOut magAc;
	UserProfilePage myProfile;

	NavigationToolbar navTool;
	SiteExplorerHome SEHome;
	UserAddManagement addUserPage;

	SpaceManagement spaMg;
	SpaceHomePage spaHome;

	CreateNewDocument CreNewDoc;
	TextBoxDatabase txData;
	AttachmentFileDatabase fData;
	LinksDatabase lnkData;

	SiteExplorerDriveDatabase siteExDrive;
	SiteExplorerPathDatabase siteExPath;

	Button button;
	ConnectionsManagement connMag;
	SpaceSettingManagement setSpaceMg;
	
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
		navTool = new NavigationToolbar(driver);
		SEHome = new SiteExplorerHome(driver);
		CreNewDoc = new CreateNewDocument(driver);
		connMag = new ConnectionsManagement(driver);
		setSpaceMg = new SpaceSettingManagement(driver);
		hp = new HomePagePlatform(driver);
		sourceEditor= new SourceTextEditor(driver);

		notiEmail = new EmailNotifications(driver);
		
		wHome = new WikiHomePage(driver);
		wikiMg = new WikiManagement(driver);
		
		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

		spAppData = new SpaceApplicationDatabase();
		spAppData.setAppData(spaceappFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

		fData = new AttachmentFileDatabase();
		fData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		hpAct = new ActivityStream(driver);

		spaMg = new SpaceManagement(driver);
		spaHome = new SpaceHomePage(driver);
		addUserPage = new UserAddManagement(driver);

		lnkData = new LinksDatabase();
		lnkData.setLinkData(linkPath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		
		siteExDrive = new SiteExplorerDriveDatabase();
		siteExPath = new SiteExplorerPathDatabase();

		siteExDrive.setSiteExpDriveData(siteExpDrivePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		siteExPath.setSiteExpPathData(siteExpPathPath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

		button = new Button(driver);
		password= "123456";
		info("End setUpBeforeMethod");
	}

	@AfterMethod
	public void afterMethod(){
		info("Start afterMethod");
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End setUpBeforeClass");
	}

}
