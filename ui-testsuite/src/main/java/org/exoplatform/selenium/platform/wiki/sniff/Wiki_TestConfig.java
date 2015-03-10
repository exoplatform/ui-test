package org.exoplatform.selenium.platform.wiki.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.HomepageActivity;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.PlatformPermission;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.wiki.WikiMessageDatabase;
import org.exoplatform.selenium.platform.objectdatabase.wiki.WikiRichTextDatabase;
import org.exoplatform.selenium.platform.objectdatabase.wiki.WikiTemplateDatabase;
import org.exoplatform.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.selenium.platform.social.SpaceHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.wiki.RichTextEditor;
import org.exoplatform.selenium.platform.wiki.WikiDraftPage;
import org.exoplatform.selenium.platform.wiki.WikiHomePage;
import org.exoplatform.selenium.platform.wiki.WikiManagement;
import org.exoplatform.selenium.platform.wiki.WikiSearchManagement;
import org.exoplatform.selenium.platform.wiki.WikiSettingManagement;
import org.testng.annotations.*;


public class Wiki_TestConfig extends PlatformBase {

	HomePagePlatform hp;
	HomepageActivity hpAct;
	ManageLogInOut magAc;
	
	PlatformPermission PlfPerm;
	
	WikiManagement wikiMg;
	WikiHomePage wHome;
	WikiDraftPage wDraft;
	WikiSearchManagement wSearchMg;
	WikiSettingManagement wSettingMg;
	
	RichTextEditor rtMode;
	SpaceManagement spaMg;
	SpaceHomePage spaHome;
	SpaceSettingManagement setSpaceMg;
	
	TextBoxDatabase txData;
	AttachmentFileDatabase fData;
	WikiRichTextDatabase wData;
	WikiTemplateDatabase wTempData;
	WikiMessageDatabase  wMessage;
	
	
	@BeforeClass
	public void setUpBeforeClass() throws Exception{
		info("Start setUpBeforeClass");
//		initSeleniumTest();
		initRemoteWebDriverFF();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		magAc = new ManageLogInOut(driver);
		magAc.signIn(DATA_USER1, DATA_PASS);
		
		hp = new HomePagePlatform(driver);
		hpAct = new HomepageActivity(driver);
		
		PlfPerm = new PlatformPermission(driver);
		
		wikiMg = new WikiManagement(driver);
		wHome = new WikiHomePage(driver);
		wDraft = new WikiDraftPage(driver);
		wSearchMg = new WikiSearchManagement(driver);
		wSettingMg= new WikiSettingManagement(driver);
		
		rtMode = new RichTextEditor(driver);
		spaMg = new SpaceManagement(driver);
		spaHome = new SpaceHomePage(driver);
		setSpaceMg = new SpaceSettingManagement(driver);
		
		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		fData = new AttachmentFileDatabase();
		fData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		wData = new WikiRichTextDatabase();
		wData.setWikiData(wikiRichTextFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		wTempData = new WikiTemplateDatabase();
		wTempData.setWikiTemplateData(wikiTemplateFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
	
		wMessage = new WikiMessageDatabase();
		wMessage.setWikiMessageData(wikiMessageFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		
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