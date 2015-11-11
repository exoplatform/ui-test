package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.createnode;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author vuna2
 * July, 25th, 2013
 *
 */
public class ECMS_SE_CreateNode_WebContent extends PlatformBase{
	//Platform
	Button button;
	ManageAccount magAcc;
	NavigationToolbar navToolBar;
	ActionBar actBar;
	ManageAlert magAlt;

	//Ecms
	EcmsBase ecms;
	ContextMenu cMenu;
	ContentTemplate cTemplate;
	SitesExplorer sitesExp;

	@BeforeMethod
	public void beforeMethod(){
		initSeleniumTest();
		driver.get(plfURL);
		button = new Button(driver);
		magAcc = new ManageAccount(driver);
		navToolBar = new NavigationToolbar(driver);
		actBar = new ActionBar(driver);
		ecms = new EcmsBase(driver);
		cMenu = new ContextMenu(driver);
		cTemplate = new ContentTemplate(driver,this.plfVersion);
		magAlt = new ManageAlert(driver);
		sitesExp = new SitesExplorer(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterMethod() {
		info("-- User signOut --");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Qmetry ID: 102156
	 * Create web content in Web Content folder with Name in different languages
	 * 
	 */
	@Test
	public void test01_CreateWebContentInWebContentFolderWithNameInDifferentLanguages(){
		String WEB_CONTENT_VN_TITLE = "Lập_trình_viên_eXo_01" + getRandomNumber();
		By vnTitle = By.linkText(WEB_CONTENT_VN_TITLE);

		String WEB_CONTENT_FR_TITLE = "Développer_eXo_01" + getRandomNumber();
		By frTitle = By.linkText(WEB_CONTENT_FR_TITLE);

		info("Go to Site Explorer");
		navToolBar.goToSiteExplorer();
		ecms.goToNode("acme/web contents");

		info("Add new [Free Layout Webcontent]");
		actBar.goToAddNewContent();

		info("...with name in Vietnamese");
		cTemplate.createNewWebContent(WEB_CONTENT_VN_TITLE, WEB_CONTENT_VN_TITLE, "", "", "", "", false, "vi");
		waitForAndGetElement(vnTitle);

		info("...with name in French");
		ecms.goToNode("acme/web contents");
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(WEB_CONTENT_FR_TITLE, WEB_CONTENT_FR_TITLE, "", "", "", "", false, "fr");
		waitForAndGetElement(frTitle);

		info("Restore data");
		cMenu.deleteDocument(vnTitle);
		cMenu.deleteDocument(frTitle);
	}

	/**
	 * Qmetry ID: 102191
	 * Add web content in Web Content folder
	 * 
	 */
	@Test
	public void test02_AddWebContentInWebContentFolder(){
		String WEB_CONTENT_TITLE = "ECMS_WebContent_02";
		By wTitle = By.linkText(WEB_CONTENT_TITLE);

		info("Go to Site Explorer");
		navToolBar.goToSiteExplorer();
		ecms.goToNode("acme/web contents");

		info("Add new [Free Layout Webcontent]");
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(WEB_CONTENT_TITLE, WEB_CONTENT_TITLE, "", "", "", "");
		waitForAndGetElement(wTitle);

		info("Check the status of the new webcontent");
		actBar.openManagePublicationForm();
		waitForAndGetElement(actBar.ELEMENT_FIRST_REVISION_DATE);
		button.close();
		ecms.goToNode("acme/web contents");
		WebElement element = waitForAndGetElement(sitesExp.ELEMENT_STATUS_DOCUMENT.replace("${title}", WEB_CONTENT_TITLE).replace("status","Draft"));
		String status = element.getText();
		assert status.contains("Draft") : "Check the status...Failed";
		info("Status is [Draft]...");

		driver.navigate().refresh();
		info("Restore data");
		cMenu.deleteDocument(wTitle);
	}

	/**
	 * Qmetry ID: 102192
	 * Add web content in web content folder with blank Name
	 * 
	 */
	@Test
	public void test03_AddWebContentInWebContentFolderWithBlankName(){
		String WEB_CONTENT_TITLE = "ECMS_WebContent_03" + getRandomNumber();

		info("Go to Site Explorer");
		navToolBar.goToSiteExplorer();
		ecms.goToNode("acme/web contents");

		info("Add new [Free Layout Webcontent] with blank name");
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent("", WEB_CONTENT_TITLE, "", "", "", "");
		magAlt.verifyAlertMessage(cTemplate.MESSAGE_NAME_REQUIRED_FIELD);
		button.close();
	}

	/**
	 * Qmetry ID: 102193
	 * Add web content in Web Content folder with special characters in Name
	 * error : selenium has a problem with the specials characters
	 */
	@Test(groups={"pending"})
	public void test04_AddWebContentInWebContentFolderWithSpecialCharactersInName(){
		String WEB_CONTENT_TITLE = cTemplate.DATA_SPECIAL_CHARACTER_STRING + "WebContent_04";
		String charSpecials="!@#$%&*()./:[]{}<>',;~`";

		info("Go to Site Explorer");
		navToolBar.goToSiteExplorer();
		ecms.goToNode("acme/web contents");

		info("Add new [Free Layout Webcontent] with special characters in name field");
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(charSpecials, WEB_CONTENT_TITLE, "", "", "", "");
		info("Verify showing message alerts: [Field Name contains invalid characters]");
		magAlt.verifyAlertMessage(cTemplate.MESSAGE_FIELD_NAME_INVALID_CHARS);
		button.close();
	}
}
