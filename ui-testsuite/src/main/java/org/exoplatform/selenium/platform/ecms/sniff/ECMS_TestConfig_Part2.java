package org.exoplatform.selenium.platform.ecms.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ActivityStream;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.administration.ContentAdministration;
import org.exoplatform.selenium.platform.ecms.CreateNewDocument;
import org.exoplatform.selenium.platform.ecms.ECMS_Permission;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.ecms.SiteExplorerHome;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.testng.annotations.*;


public class ECMS_TestConfig_Part2 extends PlatformBase{

	HomePagePlatform hp;
	ManageLogInOut magAc;
	SpaceManagement spManag;
	NavigationToolbar navTool;
	UserAddManagement addUserPage;
	UserAndGroupManagement userAndGroup;

	ContentAdministration caPage;
	SiteExplorerHome SEHome;
	CreateNewDocument CreNewDoc;
	
	ECMS_Permission EcmsPerm;
	ActivityStream aHome;
	
	ManageAlert alert;
	TextBoxDatabase txData;
	UserDatabase userData;
	AttachmentFileDatabase fData;
	
	@BeforeSuite(alwaysRun=true)
	public void setUpBeforSuite() throws Exception{
		info("Start setUpBeforeSuite");
		initSeleniumTest();
		caPage = new ContentAdministration(driver);
		navTool = new NavigationToolbar(driver);
		magAc = new ManageLogInOut(driver);
		magAc.signIn(USER_ROOT,PASS_ROOT);
		addActions();
		afterMethod();
		info("End setUpBeforeSuite");
	}
	
	@BeforeMethod
	public void setUpBeforeMethod() throws Exception{
		info("Start setUpBeforeClass");
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		magAc = new ManageLogInOut(driver);
		hp = new HomePagePlatform(driver);
		SEHome = new SiteExplorerHome(driver);
		CreNewDoc = new CreateNewDocument(driver);
		spManag = new SpaceManagement(driver);
		caPage = new ContentAdministration(driver);
		navTool = new NavigationToolbar(driver);
		EcmsPerm = new ECMS_Permission(driver);
		aHome = new ActivityStream(driver);
		alert = new ManageAlert(driver, this.plfVersion);
		addUserPage = new UserAddManagement(driver);
		userAndGroup = new UserAndGroupManagement(driver);
		txData = new TextBoxDatabase();
		fData = new AttachmentFileDatabase();
		userData = new UserDatabase();
		fData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		
		magAc.signIn(USER_ROOT,PASS_ROOT);
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
	 * Add manage publishtation to action bar of Web view type
	 */
	public void addActions(){
		info("Go to Explorer tab");
		navTool.goToContentAdministration();
		caPage.addAllActions();
		magAc.signIn(USER_ROOT,PASS_ROOT);
	}
	
}