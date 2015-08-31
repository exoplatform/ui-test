package org.exoplatform.selenium.platform.wiki.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ActivityStream;
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
import org.exoplatform.selenium.platform.wiki.SourceTextEditor;
import org.exoplatform.selenium.platform.wiki.WikiDraftPage;
import org.exoplatform.selenium.platform.wiki.WikiHomePage;
import org.exoplatform.selenium.platform.wiki.WikiLocators;
import org.exoplatform.selenium.platform.wiki.WikiManagement;
import org.exoplatform.selenium.platform.wiki.WikiPageInformation;
import org.exoplatform.selenium.platform.wiki.WikiPermission;
import org.exoplatform.selenium.platform.wiki.WikiSearch;
import org.exoplatform.selenium.platform.wiki.WikiSettingPage;
import org.testng.annotations.*;


public class Wiki_TestConfig extends PlatformBase {

	HomePagePlatform hp;
	ActivityStream hpAct;
	ManageLogInOut magAc;
	
	PlatformPermission PlfPerm;
	
	WikiManagement wikiMg;
	WikiHomePage wHome;
	WikiDraftPage wDraft;
	WikiSearch wSearchMg;
	WikiSettingPage wSettingMg;
	WikiPermission wPermission;
	WikiPageInformation wPageInfo;
	SourceTextEditor sourceEditor;
	WikiLocators wikiLocs;
	
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
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		magAc = new ManageLogInOut(driver);
		magAc.signIn(DATA_USER1, DATA_PASS);
		
		hp = new HomePagePlatform(driver);
		hpAct = new ActivityStream(driver);
		spaHome = new SpaceHomePage(driver);
		wikiLocs = new WikiLocators();
		
		PlfPerm = new PlatformPermission(driver);
		
		wikiMg = new WikiManagement(driver);
		wHome = new WikiHomePage(driver);
		wDraft = new WikiDraftPage(driver);
		wSearchMg = new WikiSearch(driver);
		wSettingMg= new WikiSettingPage(driver);
		wPermission = new WikiPermission(driver);
		wPageInfo = new WikiPageInformation(driver);
		sourceEditor = new SourceTextEditor(driver);
		
		rtMode = new RichTextEditor(driver);
		spaMg = new SpaceManagement(driver);
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
	
	@AfterMethod
	public void afterMethod(){
		info ("After Test");
		if (waitForAndGetElement(wikiLocs.ELEMENT_TEMPLATE_SELECT_FORM, 5000, 0) != null) {
			info("click Cancel Template button");
			click(wikiLocs.ELEMENT_TEMPLATE_CANCEL_BTN);
			waitForElementNotPresent(wikiLocs.ELEMENT_TEMPLATE_SELECT_FORM);
		}
	}
	
	@AfterClass
	public void afterClass(){
		info("Start setUpBeforeClass");
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End setUpBeforeClass");
	}

	
}