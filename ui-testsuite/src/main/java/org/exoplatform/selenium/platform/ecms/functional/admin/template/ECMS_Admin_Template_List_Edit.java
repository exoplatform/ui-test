package org.exoplatform.selenium.platform.ecms.functional.admin.template;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.admin.ECMainFunction;
import org.exoplatform.selenium.platform.ecms.admin.ManageTemplate;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ECMS_Admin_Template_List_Edit  extends PlatformBase{
	//General
	ManageAccount magAcc;
	ManageAlert alt;
	Button button;

	//Ecms
	EcmsBase ecms; 
	ECMainFunction ecMain;
	ManageTemplate magTem;

	@BeforeMethod
	public void beforeMethod(){
		initSeleniumTest();
		driver.get(plfURL);
		info("Login ECMS with " + DATA_USER1);
		button = new Button(driver);
		alt = new ManageAlert(driver);
		magAcc = new ManageAccount(driver);
		ecms = new EcmsBase(driver);
		ecMain = new ECMainFunction(driver);
		magTem = new ManageTemplate(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterMethod(){
		info("Logout ECMS");
		//logoutEcms();
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
	/**
	 * Edit list template with valid value
	 * CaseId:124774 
	 * Edit Template Name of list template
	 * CaseId:124777
	 * Edit Template Type of list template
	 * CaseId:124780
	 */
	@Test
	public void test01_02_03_EditListTemplateWithValidValue(){
		String templateTitle_01 = "Ecms_Sniff_Admin_Template_0111";
		String editTemplateTitle_01 = "Edit_Ecms_Sniff_Admin_Template_0111";
		String templateTitle_02 = "Ecms_Sniff_Admin_Template_0211";
		String editTemplateTitle_02 = "Edit_Ecms_Sniff_Admin_Template_0211";
		String fileContent_02 =	"TestData/ECMS_Admin_GetMailScript_Template.txt";
		String templateTitle_03 = "Ecms_Sniff_Admin_Template_0311";
		String editTemplateTitle_03 = "Edit_Ecms_Sniff_Admin_Template_0311";
		String templateName_01  = "Ecms_Sniff_Admin_Template_0111";
		String templateName_02  = "Ecms_Sniff_Admin_Template_0211";
		String templateName_03  = "Ecms_Sniff_Admin_Template_0311";
		String templateType_01  = "Content";
		String templateType_02  = "Navigation";
		String templateType_03  = "Paginator";

		info("-- Go to [List Template] --"); 
		ecMain.goToListTemplateTab();
		
		info("-- Add a new list template --");
		magTem.addNewListTemplate(templateTitle_01, templateName_01, templateType_01, true, "List_Template_Content_01");
		magTem.addNewListTemplate(templateTitle_02, templateName_02, templateType_02, true, "List_Template_Content_02", false, true);
		magTem.addNewListTemplate(templateTitle_03, templateName_03, templateType_03, true, "List_Template_Content_03", false, true);

		info("-- Edit a list template --");
		info("Test01: edit template title");
		magTem.editListTemplate(templateTitle_01, editTemplateTitle_01, templateType_01);
		
		info("Test02: edit template content");
		magTem.editListTemplate(templateTitle_02, editTemplateTitle_02, templateType_02, true, fileContent_02, true);
		
		info("Test03: edit template type");
		magTem.editListTemplate(templateTitle_03, editTemplateTitle_03, templateType_03, false, "", false,templateType_02);

		info("-- Delete a list template --");
		magTem.deleleTemplate(editTemplateTitle_03);

		click(magTem.ELEMENT_LIST_TEMPLATE_NAVIGATION_TAB);
		magTem.deleleTemplate(editTemplateTitle_02);

		click(magTem.ELEMENT_LIST_TEMPLATE_CONTENT_TAB);
		magTem.deleleTemplate(editTemplateTitle_01);
	}
}
