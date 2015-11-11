package org.exoplatform.selenium.platform.ecms.functional.admin.template;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.admin.ECMainFunction;
import org.exoplatform.selenium.platform.ecms.admin.ManageNodeType;
import org.exoplatform.selenium.platform.ecms.admin.ManageTemplate;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ECMS_Admin_Template_Metadata extends PlatformBase{
	//General
	ManageAlert alt;
	ManageAccount magAcc;

	//Ecms
	EcmsBase ecms; 
	ECMainFunction ecMain;
	ManageTemplate magTem;
	ManageNodeType magNType;

	@BeforeMethod
	public void beforeMethod(){
		initSeleniumTest();
		driver.get(plfURL);
		info("LogIn to Intranet with User..." + DATA_USER1);
		alt = new ManageAlert(driver);
		magAcc = new ManageAccount(driver);
		ecms = new EcmsBase(driver);
		ecMain = new ECMainFunction(driver);
		magTem = new ManageTemplate(driver);
		magNType = new ManageNodeType(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterMethod(){
		info("-- LogOut --");
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
	/**
	 * Caseid: 124735 - Edit Metadata
	 * caseid: 124741 - Delete Metadata
	 * caseid: 124789 - Cancel Delete Metadata
	 */
	@Test(priority=1)
	public void test01_ViewEditAndDeleteMetadata(){
		String metadata = "metadata124741";
		String[] metadataPer = {"Platform/Administration", "*"};
		String editMetadataLabel = "Edit_Ecms_Sniff_Metadata";
		String dialogTemplate = "TestData/ECMS_Admin_GetMailScript_Template.txt";
		String viewTemplate = "TestData/ECMS_Admin_SendMailScript_Template.txt";    	 

		info("-- Prepare data --");
		ecMain.goToNodeTypeTab();
		magNType.addNewNodeType(metadata, "exo:metadata", "", true);

		info("-- View metadata --");
		ecMain.goToMetadataTab();
		magTem.actionsOnMetadata(metadata, "View");

		info("-- Edit metadata --");
		magTem.editMetadata(metadata, metadataPer, editMetadataLabel, true, dialogTemplate, true, viewTemplate);

		info("-- Cancel delete --");
		click(magTem.ELEMENT_DELETE_METADATA_TEMPLATE_ICON.replace("${templateName}", metadata));
		alt.cancelAlert();
		waitForAndGetElement(magTem.ELEMENT_DELETE_METADATA_TEMPLATE_ICON.replace("${templateName}", metadata));

		info("-- Delete metadata --");
		magTem.actionsOnMetadata(metadata, "Delete");
	}
}
