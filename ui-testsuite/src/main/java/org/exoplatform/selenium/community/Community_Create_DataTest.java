package org.exoplatform.selenium.community;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.UserGroupManagement;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.admin.ECMainFunction;
import org.exoplatform.selenium.platform.ecms.admin.ManageCategory;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Community_Create_DataTest extends PlatformBase{
	ManageAccount magAccount;
	NavigationToolbar navBar;
	UserGroupManagement userGroup;
	EcmsBase ecms;
	ManageCategory magCa;
	ECMainFunction ecMain;
	ActionBar actBar;

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		magAccount = new ManageAccount(driver);
		navBar = new NavigationToolbar(driver);
		userGroup = new UserGroupManagement(driver);
		magCa = new ManageCategory(driver);
		ecms = new EcmsBase(driver);
		ecMain = new ECMainFunction(driver);
		actBar = new ActionBar(driver,this.plfVersion);
		button = new Button(driver);
		info("== Creating users to eXo Cloud ==");
		magAccount.signIn("fqa", "gtngtn");
	}

	@AfterMethod
	public void afterMethods() {
		info("== Creating users: successful ==");
		driver.quit();
	}

	@Test
	public void Create_Data_Test(){
		String file = "metro.pdf";
		String file1 = "offices.jpg";
		String file2 = "conditions.doc";
		info("Create Cateogry: Intranet/Defense/Healing");
		String categoryTreeName = "intranet";
		String categoryName = "Defense";

		info("Upload file into intranet/document");
		navBar.goToSiteExplorer();
		ecms.goToNode("intranet");
		ecms.goToNode("documents");
		ecms.uploadFile("TestData/" + file, true);
		ecms.uploadFile("TestData/" + file1, true);
		ecms.uploadFile("TestData/" + file2, true);
		ecms.goToNode(By.linkText(file1));
		actBar.publishDocument();
		actBar.goToNode(By.linkText(file2));
		actBar.publishDocument();
		actBar.goToNode(By.linkText(file));
		actBar.publishDocument();

		navBar.goToContentAdministration();
		if(isElementNotPresent(ecMain.ELEMENT_MANAGE_CATEGORIES_LINK))
			click(ELEMENT_ADVANCED_CONFIGURATION_TAB);
		click(ecMain.ELEMENT_MANAGE_CATEGORIES_LINK);
		magCa.addChildCategory(categoryTreeName, categoryName);
		magCa.addChildCategory(categoryName, "Healing",true);
		button.close();

		navBar.goToContentAdministration();
		if(isElementNotPresent(ecMain.ELEMENT_MANAGE_CATEGORIES_LINK))
			click(ELEMENT_ADVANCED_CONFIGURATION_TAB);
		Utils.pause(10000);
		click(ecMain.ELEMENT_MANAGE_CATEGORIES_LINK);
		magCa.addChildCategory(categoryTreeName, "Movement");
		button.close();

		navBar.goToContentAdministration();
		if(isElementNotPresent(ecMain.ELEMENT_MANAGE_CATEGORIES_LINK))
			click(ELEMENT_ADVANCED_CONFIGURATION_TAB);
		Utils.pause(10000);
		click(ecMain.ELEMENT_MANAGE_CATEGORIES_LINK);
		magCa.addChildCategory(categoryTreeName, "Natural Elements");
	}
}